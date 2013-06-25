define(['validation','utils','mainPage'],
/**
 * @lends Registration
 */ 
function(Validation, Utils, MainPage){
	
	Registration = {};
	
	/**
	* 
	* Gets the username from the ristration form
	* @returns value from an element with the id "inputFirstName" 
	*/
	Registration.getUsername = function(){
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
	* Gets an username value provided by the user within a registration form.
	* @returns value from an element by '#inputUsername'.
	*/
	Registration._getUsername = function(){
		var _username = $('#inputUsername').val();
		return _username;
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
		var isValid = Validation._isName(this._getUsername());
		Utils.highlightField('#inputUsername',isValid);
		return isValid;	
	};
			
	/**
	* Checks if the user's first name is valid. Highlights the appopriate input element '#inputFirstName' if it's not correct.
	* @returns true if the first name is valid (not empty word without digits and special signs), otherwise false is returned.
	**/
	Registration.checkFirstName = function(){
		var isValid = Validation._isName(this._getFirstName());
		Utils.highlightField('#inputFirstName',isValid);
		return isValid;		
	};
			
	/**
	* Checks if the user's last name is valid. Highlights the appopriate input element '#inputLastName' if it's not correct.
	* @returns true if the last name is valid (not empty word without digits and special signs), otherwise false is returned.
	**/
	Registration.checkLastName = function(){
		var isValid = Validation._isName(this._getLastName());
		Utils.highlightField('#inputLastName',isValid);
		return isValid;
	};

	/**
	* Checks if the user's email address is valid. Highlights the appopriate input element '#inputEmail' if it's not correct.
	* @returns true if the user's email address is valid one, otherwise false is returned.
	**/	
	Registration.checkEmail = function(){
		var isValid = Validation._isEmail(this._getEmail());
		Utils.highlightField('#inputEmail',isValid);
		return isValid;	
	};
			
	/**
	* Checks if password and its confirmation providede by a user consits. 
	* Highlights the appopriate input elements "#inputPassword" and "#inputConfirmPassword" if they are not provided at all or not consist.
	* @returns true if the passwords consists and not empty, otherwise false is returned.
	**/		
	Registration._arePasswordInputsConsist = function(){
		var p1 = this._getPassword();
		var p2 = this._getConfirmPassword();
		if(p1 && p2 && p1 === p2) return true;		
		return false;
	};
			
	Registration.checkUserPasswords = function(){
		var areConsists = this._arePasswordInputsConsist();
		Utils.highlightField("#inputPassword",areConsists);
		Utils.highlightField("#inputConfirmPassword",areConsists);
		return areConsists;
	};

	Registration.checkUserEntries = function(){
		this.checkUsername();
		this.checkFirstName();
		this.checkLastName();
		this.checkEmail();
	};
			
	Registration.areAllEntriesValid = function(){		
		var invalids = $("#registrationForm").find(".invalid");
			if(invalids.length > 0) return false;	
		return true;
	};

	Registration.registerNewUser = function(){
		console.log("Registration clicked ");
		this.checkUserEntries();
		this.checkUserPasswords();
		if(this.areAllEntriesValid()){
			var firstName = this._getFirstName();
			var lastName = this._getLastName();
			var password = this._getPassword();
			var email = this._getEmail();
			
			var newUser = this._createNewUser(firstName,lastName,password,email);
					
			Registration.registerUserOnServer(newUser);
		}
	};
	
	Registration.registerUserOnServer = function(newUser){
		
		console.log("Registering a new user on a server...");
		
		var userName = Registration._getUsername();
				
		$.ajax({
			cache: false,
			type: "PUT",
			async: false,
			url: "https://localhost:8443/api/v1/user/"+userName,
			data: JSON.stringify(newUser),
			contentType: "application/json",
			dataType: "json",
			success: function(data,status){
				console.log(data);
				console.log(status);
			},
			error: function(xhl,status){
				console.log(xhl.responseText);
								console.log(status);
			},
			statusCode:{
				200: function(){
					console.log("New user is successfully registered");
				},
				201: function(){
					console.log("New user: "+ newUser.firstName +" "+newUser.lastName+ " has been successfully created.");
					Utils.setCurrentUser(newUser);
					MainPage.load();
					
				},
				401: function(){
					 console.log("Unauthorized access by user registration");
				},
				
				409: function(){
					console.log("User already exists");
				},
				
				500 : function(){
					console.log("Internal Server Error");
				}
			}
		});
		
	};
		
	Registration._createNewUser = function(firstName,lastName,password,email){		
		var newUser = new Object();
		newUser.firstName = firstName;
		newUser.lastName = lastName;
		newUser.password = password;
		newUser.email = email;
		newUser.publicKeys = [];
		console.log(newUser);
		console.log(JSON.stringify(newUser));
		return newUser;
	};

	Registration.initRegistrationForm = function(){
		Utils.changeFocusOnEnterClick("#inputFirstName",'#inputLastName');
		Utils.changeFocusOnEnterClick('#inputLastName','#inputAffiliation');
		Utils.changeFocusOnEnterClick('#inputAffiliation','#inputEmail');
		Utils.changeFocusOnEnterClick('#inputEmail','#inputPassword');
		Utils.changeFocusOnEnterClick('#inputPassword','#inputConfirmPassword');
		Utils.addOnEnterClickEvent('#inputConfirmPassword',"#registerBtn");
		
		$("#registerBtn").click(function(){
			Registration.registerNewUser();
		});
	};

	return Registration;
});
	


