define([],function(){
	
	/** 
     * Messages class
     * The Messages class contains messages for the user interaction. 
     * @class
     * @constructor
     * @return Messages object
     */
	Messages = {};
	
/*________ REGISTRATION FORM HINT MESSAGES _______*/
	
	Messages.usernameHint = "Please enter valid username. Only symbol characters are allowed.";
	
	Messages.firstNameHint = "Please enter user's first name.";
	
	Messages.lastNameHint ="Please enter user's last name.";
	
	Messages.emailHint = "Please enter user's email address.";
	
	Messages.affiliationHint = "Please enter your affiliation.";
	
	Messages.passwordHint = "Please enter a password that at least four characters long.";
	
	Messages.confirmPasswordHint = "Please enter password confirmation.";
	
/*_________ LOGIN AND REGISTRATION FORM WARNING MESSAGES _________ */
	
	Messages.emptyUsername = "Username is empty.";
	
	Messages.emptyFirstName = "User's first name is empty.";
	
	Messages.emptyLastName = "User's last name is empty.";
	
	Messages.emptyEmailAddress = "Email address is empty.";
	
	Messages.emptyAffiliation = "Affiliation is empty.";
	
	Messages.emptyPassword = "Password is empty.";
	
	Messages.emptyConfirmPassword = "Confirm password is empty." ;
	

	
	
	Messages.wrongUsername = "Wrong username syntax.";
	
	Messages.wrongFirstName = "Wrong first name syntax.";
	
	Messages.wrongLastName = "Wrong last name syntax.";
	
	Messages.wrongEmailAddress = "Wrong email address.";
	
	Messages.wrongAffiliation = "Wrong Affiliation name.";
	
	Messages.wrongPassword = "Password is too short.";
	
	Messages.wrongConfirmPassword = "Confirm password is too short."; 
	
	Messages.passwordsAreInconsistent = "Passwors are inconsistent.";
	
	Messages.userNotFound = "Current user isn't found.";
	
	Messages.wrongPasswordKey = "Wrong password.";
		
	
	
/*_____________ MAIN PAGE FORM MESSAGES _____________________________ */

	Messages.noPublicKeys = "No public keys are available";

	Messages.emptyKeyDescription = "Public key description is empty.";

	Messages.keyDescription = "Please enter a description for the new pubic key.";
	
	Messages.wrongKeyDescription = "The given key description is wrong.";
	
	Messages.fileSelectionNotSupported = "Upload New User Keys is disabled because file selection is not supported on your system.";
	
	Messages.failToLoadFile = "Failed to load file";
	
	Messages.generateCertificate = "Please wait while new certificate for the specified public key is being generated.";
	
	Messages.emptyPassphrase = "The pass-phrase is empty.";
	
	Messages.wrongPassphrase = "The given pass-phrase is invalid";
	
	Messages.passphraseHint = "Please enter a pass-phrase for new public key and certificate.";
	
	Messages.generateNewKeyAndCertificate = "Please wait until new key pair and certificate are being generated.";
	
	Messages.wrongPublicKeyFileExt = "Wrong public key file extension. Please select file with correct extension ";
	
	Messages.wrongPublicKeyFileSize = "Wrong file size. Please select another public key file";
	
	Messages.confirmKeyDeletionQuestion = "Are you sure you want to delete this public key ?"; 
	
	Messages.confirmUserDeletionQuestion = "Are you sure you want to delete your user profile?";
	
	Messages.userDeleted = "Current user profile has been successfully deleted";
	
	return Messages;

});
