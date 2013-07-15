define([],function(){
	
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
	
	Messages.emptyKeyDescription = "Public key description is empty.";
	

	
	
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
	
	Messages.wrongKeyDescription = "Wrong key description.";	
	
	Messages.noPublicKeys = "No public keys are available";
	
	return Messages;

});
