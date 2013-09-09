package org.fiteagle.core.userdatabase;

import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import net.iharder.Base64;

import org.fiteagle.core.aaa.CertificateAuthority;
import org.fiteagle.core.aaa.KeyManagement;
import org.fiteagle.core.aaa.x509.X509Util;
import org.fiteagle.core.config.FiteaglePreferences;
import org.fiteagle.core.config.FiteaglePreferencesXML;
import org.fiteagle.core.config.InterfaceConfiguration;
import org.fiteagle.core.userdatabase.UserPersistable.DatabaseException;
import org.fiteagle.core.userdatabase.UserPersistable.DuplicateEmailException;
import org.fiteagle.core.userdatabase.UserPersistable.DuplicatePublicKeyException;
import org.fiteagle.core.userdatabase.UserPersistable.DuplicateUsernameException;
import org.fiteagle.core.userdatabase.UserPersistable.InValidAttributeException;
import org.fiteagle.core.userdatabase.UserPersistable.NotEnoughAttributesException;
import org.fiteagle.core.userdatabase.UserPersistable.UserNotFoundException;
import org.fiteagle.core.userdatabase.UserPersistable.PublicKeyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDBManager {

	private UserPersistable database;
	private FiteaglePreferences preferences = new FiteaglePreferencesXML(
			this.getClass());

	private static enum databaseType {
		InMemory, SQLite
	}

	private static final String DEFAULT_DATABASE_TYPE = databaseType.SQLite
			.name();
	private static UserDBManager dbManager = null;
	static Logger log = LoggerFactory.getLogger(UserDBManager.class);
	KeyManagement keyManager = null;

	public static UserDBManager getInstance() {
		if (dbManager == null) {
			try {
				dbManager = new UserDBManager();
			} catch (DatabaseException | SQLException e) {
				log.error(e.getMessage(), e);
			}
		}
		return dbManager;
	}

	private UserDBManager() throws DatabaseException, SQLException {
		keyManager = KeyManagement.getInstance();
		if (preferences.get("databaseType") == null) {
			preferences.put("databaseType", DEFAULT_DATABASE_TYPE);
		}
		if (preferences.get("databaseType").equals(databaseType.SQLite.name())) {
			database = SQLiteUserDB.getInstance();
		} else {
			database = new InMemoryUserDB();
		}
	}

	public void add(User u) throws DuplicateUsernameException,
			DuplicateEmailException, DatabaseException,
			NotEnoughAttributesException, InValidAttributeException,
			DuplicatePublicKeyException {
		String username = addDomain(u.getUsername());
		u.setUsername(username);
		database.add(u);
	}

	public void delete(String username) throws DatabaseException {
		username = addDomain(username);
		database.delete(username);
	}

	public void delete(User u) throws DatabaseException {
		String username = addDomain(u.getUsername());
		u.setUsername(username);
		database.delete(u);
	}

	public void update(String username, String firstName, String lastName, String email, String affiliation, String password, List<UserPublicKey> publicKeys) throws UserNotFoundException,
			DuplicateEmailException, DatabaseException,
			NotEnoughAttributesException, InValidAttributeException,
			DuplicatePublicKeyException {
		username = addDomain(username);
		database.update(username, firstName, lastName, email, affiliation, password, publicKeys);
	}

	public void addKey(String username, UserPublicKey key)
			throws UserNotFoundException, DatabaseException,
			InValidAttributeException, DuplicatePublicKeyException {
		username = addDomain(username);
		database.addKey(username, key);
	}

	public void deleteKey(String username, String description)
			throws UserNotFoundException, DatabaseException {
		username = addDomain(username);
		database.deleteKey(username, description);
	}

	public void renameKey(String username, String description,
			String newDescription) throws UserNotFoundException,
			DatabaseException, DuplicatePublicKeyException,
			InValidAttributeException, PublicKeyNotFoundException {
		username = addDomain(username);
		database.renameKey(username, description, newDescription);
	}

	public User get(String username) throws UserNotFoundException,
			DatabaseException {
		username = addDomain(username);
		return database.get(username);
	}

	public User get(User u) throws UserNotFoundException, DatabaseException {
		String username = addDomain(u.getUsername());
		u.setUsername(username);
		return database.get(u);
	}

	public User getUserFromCert(X509Certificate userCert) {
		try {
			String username = "";
			username = X509Util.getUserNameFromX509Certificate(userCert);

			User identifiedUser = get(username);
			return identifiedUser;
		} catch (CertificateParsingException e1) {
			throw new RuntimeException(e1);
		}
	}

	public boolean verifyPassword(String password, String passwordHash,
			String passwordSalt) throws IOException, NoSuchAlgorithmException {
		byte[] passwordHashBytes = Base64.decode(passwordHash);
		byte[] passwordSaltBytes = Base64.decode(passwordSalt);
		byte[] proposedDigest = createHash(passwordSaltBytes, password);
		return Arrays.equals(passwordHashBytes, proposedDigest);
	}

	public boolean verifyCredentials(String username, String password)
			throws NoSuchAlgorithmException, IOException,
			UserNotFoundException, DatabaseException {
		username = addDomain(username);
		User user = get(username);
		return verifyPassword(password, user.getPasswordHash(),
				user.getPasswordSalt());
	}

	private String createUserCertificate(String username, PublicKey publicKey)
			throws Exception {
		User u = get(username);
		CertificateAuthority ca = CertificateAuthority.getInstance();
		X509Certificate cert = ca.createCertificate(u, publicKey);
		return X509Util.getCertficateEncoded(cert);
	}

	private byte[] createHash(byte[] salt, String password)
			throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
		digest.update(salt);
		return digest.digest(password.getBytes());
	}

	public String createUserPrivateKeyAndCertAsString(String username,
			String passphrase) throws Exception {
		username = addDomain(username);
		KeyPair keypair = keyManager.generateKeyPair();
		String privateKeyEncoded = keyManager.encryptPrivateKey(
				keypair.getPrivate(), passphrase);
		String pubKeyEncoded = keyManager.encodePublicKey(keypair.getPublic());
		addKey(username, new UserPublicKey(pubKeyEncoded, "created at "
				+ System.currentTimeMillis()));
		String userCertString = createUserCertificate(username,
				keypair.getPublic());
		return privateKeyEncoded + "\n" + userCertString;
	}

	public String createUserCertificateForPublicKey(String username,
			String description) throws Exception, PublicKeyNotFoundException {
		username = addDomain(username);
		PublicKey publicKey = get(username).getPublicKey(description)
				.getPublicKey();
		return createUserCertificate(username, publicKey);
	}

	private String addDomain(String username) {

		InterfaceConfiguration configuration = null;
		if (!username.contains("@")) {
			configuration = InterfaceConfiguration.getInstance();
			username = username + "@" + configuration.getDomain();
		}
		return username;
	}
}
