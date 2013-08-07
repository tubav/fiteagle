define(['validation','utils','mainPage','messages'],
function(Validation, Utils, MainPage,Messages){
	
	/** 
     * Registration class
     * The Registration class contains functions for user registration form initialisation. 
     * @class
     * @constructor
     * @return Registration object
     */
	Registration = {};
	
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
	* Gets an email address value provided by the user within a registration form. 
	* @returns value from an element by '#inputEmail'.
	*/
	Registration._getEmail = function(){
		var _email = $('#inputEmail').val();
		return _email;
	};
	
	/**
	* Gets an affiliation value provided by the user within a registration form. 
	* @returns value from an element by '#inputAffiliation'.
	*/
	Registration._getAffiliation = function(){
		var _affiliation = $("#inputAffiliation").val();
		return _affiliation;
	};
			
	/**
	* Gets a password value provided by the user within a registration form. 
	* @returns value from an element by '#inputPassword'.
	*/
	Registration._getPassword = function(){
		var pwd = $('#inputPassword').val();
		return pwd;
	};
			
	/**
	* Gets a password confirmation value provided by the user within a registration form. 
	* @returns value from an element by '#inputConfirmPassword'.
	*/	
	Registration._getConfirmPassword = function(){
		var confPwd = $('#inputConfirmPassword').val();
		return confPwd;
	};
	
	
	/**
	* Checks if the username is valid. Highlights the appopriate input element '#inputUsername' if it's not correct.
	* @returns true if the username is valid (word length between three and 20 signs, point sign is alowed), otherwise false is returned.
	**/
	Registration.checkUsername = function(){
		
		var isValidUsername = Utils.checkInputField(
								"#inputUsername",
								"#registrationErrors",
								Validation._isName,
								Messages.emptyUsername,
								Messages.wrongUsername
								);
		
		return isValidUsername;	
	};

	

			
	/**
	* Checks if the user's first name is valid. Highlights the appopriate input element '#inputFirstName' if it's not correct.
	* @returns true if the first name is valid (not empty word without digits and special signs), otherwise false is returned.
	**/
	Registration.checkFirstName = function(){
		
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
	* @returns true if the last name is valid (not empty word without digits and special signs), otherwise false is returned.
	**/
	Registration.checkLastName = function(){
		
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
	* @returns true if the user's email address is valid one, otherwise false is returned.
	**/	
	Registration.checkEmail = function(){
		
		var isValidEmail = Utils.checkInputField(
								"#inputEmail",
								"#registrationErrors",
								Validation._isEmail,
								Messages.emptyEmailAddress,
								Messages.wrongEmailAddress);
		
		return isValidEmail;	
	};
	
	
	Registration.checkAffiliation = function(){
		
		var isValidAffiliation = Utils.checkInputField(
									"#inputAffiliation",
									"#registrationErrors",
									Validation._isAffiliation,
									Messages.emptyAffiliation,
									Messages.wrongAffiliation
									);
		return isValidAffiliation;
	};
	
	Registration.enableRegisterBtn = function(){
		$("#registerBtn").removeClass("disabled");
	};
	
	Registration.disableRegisterBtn = function(){
		var regBtn = $("#registerBtn");
		if(!regBtn.hasClass("disabled")){
			$("#registerBtn").addClass("disabled");
		}
	};
	
	
	Registration.checkPassword = function(){
		
		var isValidPassword = Utils.checkInputField(
								"#inputPassword",
								"#registrationErrors",
								Validation._isPassword,
								Messages.emptyPassword,
								Messages.wrongPassword
								);
								
		return isValidPassword;
							
	};
	
	Registration.checkConfirmPassword = function(){
		
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
	* Checks if password and its confirmation providede by a user consits. 
	* Highlights the appopriate input elements "#inputPassword" and "#inputConfirmPassword" if they are not provided at all or not consist.
	* @returns true if the passwords consists and not empty, otherwise false is returned.
	**/		
	Registration._arePasswordInputsConsist = function(){
		var p1 = this._getPassword();
		var p2 = this._getConfirmPassword();
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
			
	Registration.checkUserPasswords = function(){
		
		var areValid = false;
		
		if(this.checkPassword() & this.checkConfirmPassword()){
			var areValid = this._arePasswordInputsConsist();
		}

		return areValid;
	};

	/**
	* Triggers the validation of the all values provided by the user within the registration form: user's first and last name, email, affiliation
	* as well as password comparison and confirmation. 
	* @returns true is all of the field values are valid and false otherwise.
	*/
	Registration.checkRequiredUserEntries = function(){
		
		var allEntriesValid = 
					this.checkUsername()     &
					this.checkEmail()        &
					this.checkFirstName()    &
					this.checkLastName()     &
					this.checkAffiliation()  &
					this.checkUserPasswords();
					
		return allEntriesValid; 
	};

	Registration.registerNewUser = function(){
		//console.log("Registration clicked ");
		
		Utils.unhideElement('#registerSpinner');
		var allEntriesValid = this.checkRequiredUserEntries();
		//console.log("All entries are valid !" + allEntriesValid);
		if(allEntriesValid){		
			var newUserInfo = Utils.createNewUser(
							this._getFirstName(),
							this._getLastName(),
							this._getAffiliation(),
							this._getPassword(),
							this._getEmail()
						  );
						  
			
			var errorMessage = Server.registerUser(
											newUserInfo,
											this._getUsername(),
											showNewUserProfile);
			
			if(errorMessage){
					setTimeout(function(){
						$('#registrationErrors').append(errorMessage)
					},100);
			}
		}
		setTimeout(function(){
			Utils.hideElement('#registerSpinner');
		},200)
	};
	
	showNewUserProfile = function(){
		console.log('clicking on !!!');
		console.log($('#manageProfileMenu'));
		$('#manageProfileMenu').click();
	};

	Registration.initRegistrationForm = function(){
		Utils.changeFocusOnEnterClick("#inputFirstName",'#inputLastName');
		Utils.changeFocusOnEnterClick('#inputLastName','#inputAffiliation');
		Utils.changeFocusOnEnterClick('#inputAffiliation','#inputEmail');
		Utils.changeFocusOnEnterClick('#inputEmail','#inputPassword');
		Utils.changeFocusOnEnterClick('#inputPassword','#inputConfirmPassword');
		Utils.addOnEnterClickEvent('#inputConfirmPassword',"#registerBtn");
		
		Registration.initRegistrationFormHints();
		Registration.initRegisterNewUserButton();

	};
	
	Registration.initRegisterNewUserButton = function(){
		$("#registerBtn").click(function(){
			Registration.clearAllErrorMessages();
			Registration.registerNewUser();
		});
	};
	
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
	}

	return Registration;
});
	


