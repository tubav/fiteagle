define(['require','validation','registration','utils','messages'],

/** @lends Login */ 
function(require,Validation,Registration,Utils,Messages){
	
	//console.log("loginPage.js is loaded");
	
	 /** 
     * Login class
     * This is the interface class for user login
     * @class Login
     * @constructor
     * @return Login Object
     */
	Login = {}; 
	
	
	
	Login.initLoginPage = function(){
		disableFederatedLinks();
		initOnWindowResizeEvent();
		initNavigationMenu();
		initRegisterLink();
		initLoginForm();
		initSignInBtn();
		Registration.initRegistrationForm();	
		Utils.showCurrentTab();
		
		initOnWindowResizeEvent();
		
	};
	
	setCurrentTab = function(currentTab){
		if(typeof(Storage)!=="undefined"){
			sessionStorage.currentTab = currentTab;
		  }
		else{
			console.log("Session storage is not supported !");
		}
	};
	
	getCurrentTab = function(){
		return sessionStorage.currentTab;
	};
	
	
	disableFederatedLinks = function(){
		var a = $("#fancyLoginList").find('li a');
		a.each(function(){
			$(this).on('click',function(e){
				e.preventDefault();
			});
		});

	};
	
	initNavigationMenu = function(){
		toggleNavigationBtn();
		$("#navigation ul li a").on('click',function(){
			Utils.setCurrentTab("#"+$(this).attr("id"));
		});	
	};
	
	toggleNavigationBtn = function(){
		if(Utils.isSmallScreen()){
			$('.btn-navbar').removeClass('hidden');
		}else{
			$('.btn-navbar').addClass('hidden');
		}
	};

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
		
		if(this.checkUsername() & Login.checkPassword()){
				console.log("email and password are correct");
				Utils.setCredentials(Login._getUsername(),Login._getPassword());			
				Login.sendLoginInformation(Login._getUsername(),Login._getPassword());
				
		}
	};
	
	Login.clearAllErrorMessages = function(){
		Utils.clearErrorMessagesFrom("#loginErrors");
	};
	
	
	Login.sendLoginInformation = function(username,password){
		
		console.log("Sending login information to the server...");
		setCookie = "";
		if($("#rememberMeCheckbox").is(":checked"))setCookie="setCookie=true";			
		$.ajax({
			cache: false,
			type: "GET",
			async: false,
			data: setCookie,
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
				require('mainPage').load();
				Utils.setCurrentUser(user);	
			},
			error: function(xhr,status,thrown){
				console.log("Response " + xhr.responseText);
				console.log(status);
				console.log(thrown);
			},
			statusCode:{
				401: function(){
					Utils.addErrorMessageTo("#loginErrors",Messages.wrongPasswordKey);
				},
				404: function(){
					Utils.addErrorMessageTo("#loginErrors", Messages.userNotFound);	
				}
			}
		});
	};

	initOnWindowResizeEvent = function(){
		$(window).resize(function(){
			toggleNavigationBtn();
			initLoginFormHints();
			Registration.initRegistrationFormHints();
		});
	}
	
	initLoginForm = function(){
		Utils.unhideBody();
		Utils.changeFocusOnEnterClick("#username","#password");
		Utils.addOnEnterClickEvent("#password","#signIn");
		$('#main').scrollTop(0);
		$('#username').focus();
		
		$(function() { // when the DOM is ready...
			//  Move the window's scrollTop to the offset position of #now
			$(window).scrollTop($('#header').offset().top);
		});

		initLoginFormHints();
		
		$('#main').addClass('row-fluid');
	};
	
	initLoginFormHints = function(){
		var position;
		(Utils.isSmallScreen()) ? position = "top" : position = "right";			
		Utils.initTooltipFor("#username",Messages.usernameHint,position,"focus");
		Utils.initTooltipFor("#password",Messages.passwordHint,position,"focus");
	};
	
	initRegisterLink = function(){
		$("#registrationLink").on('click',function(e){
			e.preventDefault();
			$("#registrationTab").click();
		});
	}
	
	initSignInBtn = function(){	
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
		window.setTimeout(function(){
			$("#loadingSign").addClass('hidden');
		},200);
	};
	
	
	Login.getRememberedUsername = function(){
		var username;
		var cookie = $.cookie('fiteagle_user_cookie');
		if(cookie){
			var cookieVal = atob(cookie);
			var start = cookieVal.indexOf("username:");
			if(start > -1){
				username = cookieVal.substring(start+9);
			}
		}
		return username;
	};
	
	return Login;

});
	







