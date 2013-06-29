define(['require','validation','registration','utils','cookie','messages'],

/** @lends Login */ 
function(require,Validation,Registration,Utils,Cookie,Messages){
	
	console.log("loginPage.js is loaded");
	
	 /** 
     * Login class
     * This is the interface class for user login
     * @class Login
     * @constructor
     * @return Login Object
     */
	Login = {}; 

	/**
      * .... description goes here ...
      * @function 
      *
      * @param {Number} channel  ..... 
      * @param {String} subscription ..............
      * @example
      * add the sample code here if relevent.
      * 
      */  
	Login.load = function(){
			console.log("loading Login Page...");
			var url = "html/login.html";

			$("#navigation").load(url + " #navs",
				function(){
					$("#main").load(url + " #loginPages",
						function(){
							Login.initLoginPage();
						});
				}
			);
	};
	
	Login._getUsername = function(){		
		return $("#username").val();
	};

	Login._getPassword = function(){
		return $("#password").val();
	};

	Login.checkUsername = function(){
		//log("checking email");
		
		var isValidUsername = Utils.checkInputField(
								"#username",
								"#loginErrors",
								Validation._isName,
								Messages.emptyUsername,
								Messages.wrongUsername
		);
		
		return isValidUsername;
	};

	Login.checkPassword = function(){
		//log("checking password");
		var isValidPassword = Utils.checkInputField(
								"#password",
								"#loginErrors",
								Validation._isPassword,
								Messages.emptyPassword,
								Messages.wrongPassword
		);
		return isValidPassword;
	};

	Login.loginUser = function(){
		
		console.log('trying to login user...');
		
		Login.clearAllErrorMessages();
		
		var isValidUsername = Login.checkUsername();
		var isValidPassword = Login.checkPassword();
		
		if(isValidUsername && isValidPassword){
				console.log("email and password are correct");			
				
				var username = Login._getUsername();
				var password = Login._getPassword();
				
				Login.sendLoginInformation(username,password);
				
		}else{
				console.log("username or password are NOT correct");	
				if(!isValidUsername){
					Login.showErrorMessage("Wrong username syntax");
				}
				if(!isValidPassword){
					Login.showErrorMessage("Password too short");
				}
			}
	};
	
	Login.clearAllErrorMessages = function(){
		Utils.clearErrorMessagesFrom("#loginForm .errorMessages");
	};
	
	Login.sendLoginInformation = function(username, password){
		
		console.log("Sending login information to the server...");
		$.ajax({
			cache: false,
			type: "GET",
			async: false,
			dataType: "json",
			url : "/api/v1/user/"+username,
			beforeSend: function(xhr){
				Login.showLoadingSign();
				xhr.setRequestHeader("Authorization",
                "Basic " + btoa(username + ":" + password)); // TODO Base64 support
			},
			complete: function(){
				Login.hideLoadingSign();
			},
			success: function(user,status,xhr){
				Utils.setCurrentUser(user);						
				require('mainPage').load();
			},
			error: function(xhr,status,thrown){
				console.log("Response " + xhr.responseText);
				console.log(status);
				console.log(thrown);
			},
			statusCode:{		
				404: function(){
					Utils.addErrorMessageTo("#loginErrors", Messages.userNotFound);	
				}
			}
		});
	};


	Login.initLoginPage = function(){
		this.initRegisterLink();
		this.initLoginForm();
		this.initSignInBtn();
		this.initShowCookie();
		Registration.initRegistrationForm();
	};
	
	Login.initLoginForm = function(){
		$("#fiteagle").removeClass("hidden");
		Utils.changeFocusOnEnterClick("#username","#password");
		Utils.addOnEnterClickEvent("#password","#signIn");
		
		var screenWidth = $(window).width();
		var position;
		
		(screenWidth < 767) ? position = "top" : position = "right";
				
		Utils.initTooltipFor("#username",Messages.usernameHint,position,"focus");
		Utils.initTooltipFor("#password",Messages.passwordHint,position,"focus");
	};
	
	
	Login.initRegisterLink = function(){
		$("#registrationLink").on('click',function(e){
			e.preventDefault();
			$("#registrationTab").click();
		});
	}
	
	Login.initSignInBtn = function(){	
		$("#signIn").on('click',function(){
			 console.log("SignIn button clicked !");
			 Utils.clearErrorMessagesFrom("#loginErrors");
			 Login.loginUser();
		});	
	};

	Login.isUserLoggedIn = function(){
		var user = Utils.getCurrentUser();
		console.log('is logged in ' + Utils.userToString());
		if(user != null){
				return true;
		}
		return false;
	};
	
	Login.showLoadingSign = function(){	
		$("#loadingSign").removeClass('hidden');
	};
	
	Login.hideLoadingSign = function(){
		$("#loadingSign").addClass('hidden');
	};
	
	
	Login.initShowCookie= function(){
		
		$("#showCookie").on('click',function(){
				console.log(Messages.wrongCredentials);
				alert(document.cookie.length);
		});
	};

	
	return Login;

});
	







