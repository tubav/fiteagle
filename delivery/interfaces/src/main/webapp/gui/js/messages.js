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
	
	Messages.usernameHint = "The username must be between 5 and 15 characters, dot and underscore are allowed, the first letter should be number or character";
	
	Messages.firstNameHint = "Acceptable first names include compound names with a hyphen or a space in them.";
	
	Messages.lastNameHint ="Acceptable last names include compound names with a hyphen or a space in them.";
	
	Messages.emailHint = "Please enter a valid email address.";
	
	Messages.affiliationHint = "The affiliation should be between 2 and 20 digits, underscore, hyphen, apostrophe and dots are allowed. First letter can be only a character.";
	
	Messages.passwordHint = "Password must be between 4 and 8 digits long and include at least one numeric digit.";
	
	Messages.confirmPasswordHint = "Please enter password confirmation.";
	
	Messages.keyValueHint = "The public key length should be between 100 and 500 digits.";
	
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
	
	Messages.wrongAffiliation = "Wrong Affiliation syntax";
	
	Messages.wrongPassword = "The given password syntax is invalid";
	
	Messages.wrongConfirmPassword = "Confirm password is too short."; 
	
	Messages.passwordsAreInconsistent = "Password are inconsistent.";
	
	Messages.userNotFound = "Current user isn't found.";
	
	Messages.wrongPasswordKey = "Wrong password.";
		
	
	
/*_____________ MAIN PAGE FORM MESSAGES _____________________________ */

	Messages.uploadNewPublicKey = "Click to upload the new public key.";

	Messages.noPublicKeys = "No public keys are available";

	Messages.emptyKeyDescription = "Public key description is empty.";

	Messages.keyDescription = "The public key description should be from 3 to 15 digits long. The first letter has to be a character. Only alphanumeric characters are allowed.";
	
	Messages.newKeyDescription = "The new public key description should be from 3 to 15 digits long. The first letter has to be a character. Only alphanumeric characters are allowed.";
	
	Messages.emptyKeyValue = "Public key value is empty";
	
	Messages.wrongKeyValue = "The given key value syntax is wrong";
	
	Messages.clickToChangeKeyDescription = "Click to change the public key description.";
	
	Messages.wrongKeyDescription = "The given key description is wrong.";
	
	Messages.fileSelectionNotSupported = "Upload New User Keys is disabled because file selection is not supported on your system.";
	
	Messages.failToLoadFile = "Failed to load file";
	
	Messages.generateCertificate = "Please wait while new certificate for the specified public key is being generated.";
	
	Messages.generateKeyPairAndCertificate = 'Generate new key pair and the certificate for it';
	
	Messages.generateCertificateFromKey = "Generate certificate from the selected public key";
	
	Messages.emptyPassphrase = "The pass-phrase is empty.";
	
	Messages.wrongPassphrase = "The given pass-phrase syntax is invalid.";
	
	Messages.passphraseHint = "Password must be at least 4 characters, no more than 8 characters, and must include at least one lower case letter, and one numeric digit.";
	
	Messages.generateNewKeyAndCertificate = "Please wait until new key pair and certificate are being generated.";
	
	Messages.wrongPublicKeyFileExt = "Wrong public key file extension. The valid public key extension is 'pub'.";
	
	Messages.wrongPublicKeyFileSize = "Wrong file size. Please select another public key file";
	
	Messages.confirmKeyDeletionQuestion = "Are you sure you want to delete this public key ?"; 
	
	Messages.confirmUserDeletionQuestion = "Are you sure you want to delete your user profile?";
	
	Messages.userDeleted = "Current user profile has been successfully deleted";
	
	Messages.signOutConfirm = "Are you sure you want to sign out ?";
	
	return Messages;

});
