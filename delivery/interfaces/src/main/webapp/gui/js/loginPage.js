define(['require','validation','registration','utils','cookie'],

/** @lends Login */ 
function(require,Validation,Registration,Utils,Cookie){
	
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
		var isValidUsername = Validation._isName(this._getUsername());
		Utils.highlightField("#username",isValidUsername);
		return isValidUsername;
	};

	Login.checkPassword = function(){
		//log("checking password");
		var isValidPassword = Validation._isPassword(this._getPassword());
		Utils.highlightField("#password",isValidPassword);
		return isValidPassword;
	};

	Login.loginUser = function(){
		
		console.log('trying to login user...');
		
		Utils.clearErrorMessagesFrom("#loginForm .errorMessages");
		
		var isValidEmail = Login.checkUsername();
		var isValidPassword = Login.checkPassword();
		
		if(isValidEmail && isValidPassword){
				console.log("email and password are correct");			
				var username = null;
				username = Login._getUsername();
				var password = null;
				password = Login._getPassword();
				window.setTimeout(function(){
						Login.sendLoginInformation(username,password);
				},200);
		}else{
				console.log("username or password are NOT correct");	
				if(!isValidEmail){
					Utils.addErrorMessageTo("#loginForm .errorMessages", "Wrong username");
				}
				if(!isValidPassword){
					Utils.addErrorMessageTo("#loginForm .errorMessages", "Password too short");
				}
			}
	};
	
	Login.sendLoginInformation = function(username, password){
		
		console.log("Sending login information to the server...");
		console.log("username " + username);
		console.log("password "  + password);
		$.ajax({
			cache: false,
			type: "GET",
			async: false,
			dataType: "json",
			url : "https://localhost:8443/api/v1/user/"+username,
			beforeSend: function(xhr){
            xhr.setRequestHeader("Authorization",
                "Basic " + btoa(username + ":" + password)); // TODO Base64 support
			},
			success: function(user,status,xhr){
				console.log(user);
				Utils.setCurrentUser(user);
				console.log(status);
				console.log(xhr.responseText);
				console.log("SET COOKIE ===> " + xhr.getResponseHeader('Set-Cookie'));
								
				require('mainPage').load();
			},
			error: function(xhr,status,thrown){
				console.log("Response " + xhr.responseText);
				console.log(status);
				console.log(thrown);
			}
		});
	};


	Login.initLoginPage = function(){
		$("#fiteagle").removeClass("hidden");
		Utils.changeFocusOnEnterClick("#username","#password");
		Utils.addOnEnterClickEvent("#password","#signIn");
		
		Login.initRegisterLink();
		Login.initSignInBtn();
		Login.initShowCookie();

		Registration.initRegistrationForm();
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
				console.log(Utils.listCookies());
		});
	};
	
	
	return Login;

});
	







