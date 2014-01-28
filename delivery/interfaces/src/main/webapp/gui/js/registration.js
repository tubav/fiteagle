define(['validation','utils','mainPage','messages'],
function(Validation, Utils, MainPage,Messages){

//	console.log("registration.js is called");

	Registration = {};
	domain = "";
	/**
	* 
	* Gets the username from the ristration form
	* @returns value from an element with the id "inputFirstName" 
	*/
	Registration._getUsername = function(){
		var _username = $('#inputUsername').val();
		return _username;
	};
	
	/**
	* 
	* Gets the user's first name from the ristration form
	* @returns value from an element with the id "inputFirstName" 
	*/
	Registration._getFirstName = function(){
		var _firstName = $('#inputFirstName').val();
		return _firstName;
	};
			
	/**
	* Gets the user's last name from the registration form.
	* @returns value from an element by '#inputLastName'.
	*/
	Registration._getLastName = function(){
		var _lastName = $('#inputLastName').val();
		return _lastName;
	};
			

			
	
	/**
	* Checks if the username entered by a user in the registration form is a valid one. 
	* Highlights the appropriate input element '#inputUsername' if it's not correct.
	* @returns true if the user name is valid, otherwise false is returned.
	* @see Validation#_isUsername for more information about the username validation rule.
	* @private
	* @memberOf Registration#
	**/
	checkRegUsername = function(){	
		var isValidUsername = Utils.checkInputField(
								"#inputUsername",
								"#registrationErrors",
								Validation._isUsername,
								Messages.emptyUsername,
								Messages.wrongUsername
								);	
		return isValidUsername;	
	};

			
	/**
	* Checks if the user's first name is valid. Highlights the appopriate input element '#inputFirstName' if it's not correct.
	* @returns true if the first name is valid (not empty word without digits and special signs), otherwise false is returned.
	* @see Validation#isName for user's name validation rules.
	* @private
	* @memberOf Registration#
	**/
	checkFirstName = function(){	
		var isValidFirstName = Utils.checkInputField(
								"#inputFirstName",
								"#registrationErrors",
								Validation._isName,
								Messages.emptyFirstName,
								Messages.wrongFirstName);
		return isValidFirstName;		
	};
			
	/**
	* Checks if the user's last name is valid. Highlights the appopriate input element '#inputLastName' if it's not correct.
	* @see Validation#isName for user's name validation rules.
	* @private
	* @memberOf Registration#
	* @returns true if the last name is valid (not empty word without digits and special signs), otherwise false is returned.
	**/
	checkLastName = function(){	
		var isValidLastName = Utils.checkInputField(
								"#inputLastName",
								"#registrationErrors",
								Validation._isName,
								Messages.emptyLastName,
								Messages.wrongLastName);
		return isValidLastName;	
	};

	/**
	* Checks if the user's email address is valid. Highlights the appopriate input element '#inputEmail' if it's not correct.
	* @see Validation#isName for user's email validation rules.
	* @private
	* @memberOf Registration#
	* @returns true if the user's email address is valid one, otherwise false is returned.
	**/	
	checkEmail = function(){		
		var isValidEmail = Utils.checkInputField(
								"#inputEmail",
								"#registrationErrors",
								Validation._isEmail,
								Messages.emptyEmailAddress,
								Messages.wrongEmailAddress);
		return isValidEmail;	
	};
	
	
	/**
	* Checks if the affiliation given by a user is a valid one and shows an appropriate error message if 
	* the value occurs wrong after the validation process.
	* @see Validation#_isAffiliation for affiliation validation rule.
	* @private
	* @memberOf Registration#
	* @returns true if the affiliation value is valid one, otherwise false is returned.
	*/
	checkAffiliation = function(){		
		var isValidAffiliation = Utils.checkInputField(
									"#inputAffiliation",
									"#registrationErrors",
									Validation._isAffiliation,
									Messages.emptyAffiliation,
									Messages.wrongAffiliation
									);
		return isValidAffiliation;
	};
	
	/**
	* Checks if the password entered by a user in the registration form is a valid one 
	* and shows an appropriate error message if the value occurs wrong after the its validation is performed.
	* @see Validation#_isPassword for password validation rule.
	* @private
	* @memberOf Registration#
	* @returns true if the password is valid one, otherwise false is returned.
	*/
	checkRegPassword = function(){
		
		var isValidPassword = Utils.checkInputField(
								"#inputPassword",
								"#registrationErrors",
								Validation._isPassword,
								Messages.emptyPassword,
								Messages.wrongPassword
								);
//		console.log("PSW: " + isValidPassword);		
			
		return isValidPassword;
							
	};
	
	
	/**
	* Checks if the password confirmation entered by a user is also a valid password and shows an appropriate error message if 
	* the value occurs wrong after the validation process.
	* @see Validation#_isPassword for password validation rule.
	* @private
	* @memberOf Registration#
	* @returns true if the confirmation password is valid one, otherwise false is returned.
	*/
	checkConfirmPassword = function(){	
		var isValidConfirmPassword = Utils.checkInputField(
									"#inputConfirmPassword",
									"#registrationErrors",
									Validation._isPassword,
									Messages.emptyConfirmPassword,
									Messages.wrongConfirmPassword	
									);
		return isValidConfirmPassword;
	};
	
	
			
	/**
	* Checks if password and its confirmation provided by a user consist. 
	* Highlights the appropriate input elements "#inputPassword" and "#inputConfirmPassword" if they are not provided at all or not consistent.
	* In case the passwords are inconsistent or one of them is empty the appropriate error is shown to the user.
	* @see Utils#highlightField for highlighting of an input element.
	* @private
	* @memberOf Registration#
	* @returns true if the passwords and it confirmation are not empty and consistent, otherwise false is returned.
	**/		
	arePasswordInputsConsist = function(){
		var p1 = $('#inputPassword').val(); // get password entered by a user
		var p2 = $('#inputConfirmPassword').val(); // get password confirmation entered by a user
		var areConsists;
		if(p1 && p2 && p1 === p2){ 
			Utils.highlightField("#inputPassword",false);
			Utils.highlightField("#inputConfirmPassword",false);
			areConsists = true;	
		}else{
			Utils.addErrorMessageTo("#registrationErrors",Messages.passwordsAreInconsistent);
			Utils.highlightField("#inputPassword",true);
			Utils.highlightField("#inputConfirmPassword",true);
			areConsists = false;
		}	
		return areConsists;
	};
	
	/**
	* Triggers the password and password confirmation value validation process.
	* @private
	* @memberOf Registration#
	* @returns true if both of the passwords provided by a user are valid password values. 
	*/
	checkUserPasswords = function(){		
		var areValid = false;
		var isPwd = checkRegPassword();
		var isConfPwd = checkConfirmPassword();
		if(isPwd & isConfPwd){
			var areValid = arePasswordInputsConsist();
		}
		return areValid;
	};

	/**
	* Triggers the validation of the all values provided by the user within the registration form: user's first and last name, email, affiliation
	* password, password confirmation as well as its comparison. 
	* @private
	* @memberOf Registration#
	* @returns true is all of the field values are valid and false otherwise.
	*/
	checkRequiredUserEntries = function(){
		
		var allEntriesValid = 
					checkRegUsername()     &
					checkEmail()        &
					checkFirstName()    &
					checkLastName()     &
					checkAffiliation()  &
					checkUserPasswords();
					
		return allEntriesValid; 
	};

	
	/**
	* Triggers the validation of the all required information from a registration form for a user registration.
	* Creates a user object for registration an a server then sends the request towards the server.
	* Evaluates a response messages and shows it to the user. Toggles icon spinner visibility while communicating with a server .
	* @private
	* @memberOf Registration#
	*/
	registerNewUser = function(){
		//console.log("Registration clicked ");	
		Utils.unhideElement('#registerSpinner');
		var allEntriesValid = checkRequiredUserEntries();
		//console.log("All entries are valid !" + allEntriesValid);
		if(allEntriesValid){		
			var newUserInfo = Utils.createNewUser(
							$('#inputFirstName').val(), // get user's first name
							$('#inputLastName').val(), // get last name
							$("#inputAffiliation").val(), // get affiliation
							$('#inputPassword').val(), // get password
							$('#inputEmail').val() // get user's email
						  );
						  
			
			var errorMessage = Server.registerUser(
											newUserInfo,
											$('#inputUsername').val() // get username
											/*showNewUserProfile*/);
			
			if(errorMessage){
				setTimeout(function(){
					$('#registrationErrors').append(errorMessage);
				},100);
			}
		}
		setTimeout(function(){
			Utils.hideElement('#registerSpinner');
		},200);
	};

	/**
	* Triggers registration form initialization. It defines focus change after clicking on "enter" keyboard button
	* to next form form field or clicking on the register button if it's the last form input field. It initializes the 
	* hints for the form fields and buttons. The hist is shown in a twitter bootstrap tooltip. 
	* It starts initialization process for the register button located in the form.
	* @public
	* @name Registration#initRegistrationForm
	* @function	
	*/
	Registration.initRegistrationForm = function(){
		Utils.changeFocusOnEnterClick("#inputUsername",'#inputFirstName');
		Utils.changeFocusOnEnterClick("#inputFirstName",'#inputLastName');
		Utils.changeFocusOnEnterClick('#inputLastName','#inputAffiliation');
		Utils.changeFocusOnEnterClick('#inputAffiliation','#inputEmail');
		Utils.changeFocusOnEnterClick('#inputEmail','#inputPassword');
		Utils.changeFocusOnEnterClick('#inputPassword','#inputConfirmPassword');
		Utils.addOnEnterClickEvent('#inputConfirmPassword',"#registerBtn");	
		Registration.initRegistrationFormHints();
		Registration.initRegisterNewUserButton();

		

	};
	
	
	/**
	* Defines the behaviour after clicking on the register new user button within registration form.
	* It clears all error messages shown to the user before and triggers  the user registration process.
	* @private
	* @memberOf Registration#
	*/
	Registration.initRegisterNewUserButton = function(){
		$("#registerBtn").click(function(){
			Utils.clearErrorMessagesFrom("#registrationErrors");
			registerNewUser();
		});
	};
	
	/**
	* Triggers form hints initialization process for the registration form input values 
	* depending on a current screen size.
	* The hits are shown within a tooltip on top of the field for small devices and on the right side for a large ones.
	* @see  Utils.initTooltipFor for tooltip initialization process.
	* @public
	* @name Registration.initRegistrationFormHints
	* @function	
	*/
	Registration.initRegistrationFormHints = function(){	
		var position;
		var trigger = "focus";
		(Utils.isSmallScreen())? position = "top":position = "right";		
		selectors = [
			"#inputUsername","#inputFirstName","#inputLastName","#inputAffiliation",
			"#inputEmail","#inputPassword","#inputConfirmPassword"
		];
		
		messages=[
			Messages.usernameHint, Messages.firstNameHint, Messages.lastNameHint,
			Messages.affiliationHint, Messages.emailHint, Messages.passwordHint,
			Messages.confirmPasswordHint		
		];
				
		for(var i=0; i < selectors.length; i++){
			//console.log(messages[i]);
			Utils.initTooltipFor(selectors[i],messages[i],position,trigger);
		}
	};

	
	Registration.clearAllErrorMessages = function(){
		Utils.clearErrorMessagesFrom("#registrationErrors");
	};
	

	return Registration;
});
	


