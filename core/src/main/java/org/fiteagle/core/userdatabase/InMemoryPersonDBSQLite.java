package org.fiteagle.core.userdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InMemoryPersonDBSQLite implements PersonDB {
	
	private Connection connection = null;	
	private static final String DATABASE_PATH = System.getProperty("user.dir")+"/src/main/java/org/fiteagle/core/userdatabase/userdatabase.db";
			
	static {
        try {
            Class.forName("org.sqlite.JDBC"); 
        } catch (ClassNotFoundException e) {
        	//TODO error logging?
            System.err.println("Fehler beim Laden des JDBC-Treibers"); 
            e.printStackTrace();
        }
    }
	
	public InMemoryPersonDBSQLite() throws SQLException{
		getConnection();
		createTableUsers();
		createTableKeys();
		connection.commit();
	}

	private void createTableKeys() throws SQLException {
		Statement st = connection.createStatement();
		try{
			st.executeUpdate("CREATE TABLE Keys (UID, key)");
		}catch(SQLException e){
			if(!e.getMessage().equals("table Keys already exists")){
				throw e;
			}
		}finally{
			st.close();
		}
	}

	private void createTableUsers() throws SQLException {
		Statement st = connection.createStatement();
		try{
			st.executeUpdate("CREATE TABLE Users (UID, firstName, lastName, PRIMARY KEY (UID))");
		}catch(SQLException e){
			if(!e.getMessage().equals("table Users already exists")){
				throw e;
			}
		} finally{
			st.close();
		}
	}
	
	private void getConnection() throws SQLException{
		if(connection == null){
			connection = DriverManager.getConnection("jdbc:sqlite:"+DATABASE_PATH);
		}
		connection.setAutoCommit(false);
	}
	
	@Override
	public void add(Person p) throws DuplicateUIDException, SQLException {			
		addUserToDatabase(p);		
		addKeysToDatabase(p.getUID(),p.getPublicKeys());
		connection.commit();
	}

	private void addUserToDatabase(Person p) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO Users VALUES (?,?,?)");
		ps.setString(1, p.getUID());
		ps.setString(2, p.getFirstName());
		ps.setString(3, p.getLastName());
		try{
			ps.execute();
		} catch(SQLException e){
			throw new DuplicateUIDException();
		} finally{
			ps.close();
		}
	}

	private void addKeysToDatabase(String UID, ArrayList<String> Keys) throws SQLException {
		removeDuplicateKeys(UID, Keys);
		PreparedStatement ps = connection.prepareStatement("INSERT INTO Keys VALUES (?,?)");
		for(String key: Keys){
			ps.setString(1, UID);
			ps.setString(2, key);
			ps.addBatch();
		}		
		ps.executeBatch();		
		ps.close();
	}

	private void removeDuplicateKeys(String UID, ArrayList<String> Keys) throws SQLException {
		Person p = get(UID);
		Keys.removeAll(p.getPublicKeys());
	}

	@Override
	public void delete(Person p) throws SQLException {
		delete(p.getUID());
	}
	
	@Override
	public void delete(String UID) throws SQLException {		
		deleteUserFromDatabase(UID);		
		deleteKeysFromDatabase(UID);
		connection.commit();
	}

	private void deleteKeysFromDatabase(String UID) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("DELETE FROM Keys WHERE UID=?");
		ps.setString(1, UID);
		ps.execute();
		ps.close();
	}

	private void deleteUserFromDatabase(String UID) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("DELETE FROM Users WHERE UID=?");
		ps.setString(1, UID);
		ps.execute();
		ps.close();
	}

	@Override
	public void update(Person p) throws RecordNotFoundException, SQLException {
		updateUserInDatabase(p);
		deleteKeysFromDatabase(p.getUID());		
		addKeysToDatabase(p.getUID(), p.getPublicKeys());
		connection.commit();
	}

	private void updateUserInDatabase(Person p) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE Users SET firstName=?, lastname=? WHERE UID=?");
		ps.setString(1, p.getFirstName());
		ps.setString(2, p.getLastName());
		ps.setString(3, p.getUID());
		if(ps.executeUpdate() == 0){
			ps.close();
			throw new RecordNotFoundException();
		}
		ps.close();
	}

	@Override
	public void addKey(Person p, String key) throws SQLException {
		ArrayList<String> keys = new ArrayList<String>();
		keys.add(key);
		addKeysToDatabase(p.getUID(),keys);
		connection.commit();
	}

	@Override
	public Person get(String UID) throws RecordNotFoundException, SQLException {		
		ArrayList<String> keys = getKeysFromDatabase(UID);		
		Person person = getUserFromDatabase(UID, keys);
		return person;
	}

	private Person getUserFromDatabase(String UID, ArrayList<String> keys) throws SQLException {				
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM Users WHERE UID=?");
		ps.setString(1, UID);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			Person p = new Person(rs.getString(1), rs.getString(2), rs.getString(3), keys);
			ps.close();
			return p;
		}
		else{
			ps.close();
			throw new PersonDB.RecordNotFoundException();	
		}
	}

	private ArrayList<String> getKeysFromDatabase(String UID) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM Keys WHERE UID=?");
		ps.setString(1, UID);
		ResultSet rs = ps.executeQuery();
		ArrayList<String> keys = new ArrayList<String>();
		while(rs.next()){
			keys.add(rs.getString(2));
		}
		ps.close();
		return keys;
	}

	@Override
	public Person get(Person p) throws RecordNotFoundException, SQLException {		
		return get(p.getUID());
	}

	@Override
	public int getNumberOfUsers() throws SQLException {	
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT COUNT(*) AS NumberOfUsers FROM Users");
		int size = 0;
		if(rs.next()){
			size = rs.getInt(1);			
		}
		st.close();
		return size;
	}	

	public void deleteAllEntries() throws SQLException{	
		Statement st = connection.createStatement();
		st.executeUpdate("DELETE FROM Users");	
		st.executeUpdate("DELETE FROM Keys");	
		st.close();
		connection.commit();
	}
}