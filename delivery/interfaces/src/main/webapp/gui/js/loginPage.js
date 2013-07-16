define(['require','validation','registration','utils','messages','history','ajaxify','prettyCheckable'],

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
		Utils.unhideBody();
		initOnWindowResizeEvent();
		initNavigationMenu();
		initRegisterLink();
		initLoginForm();
		initSignInBtn();
		Registration.initRegistrationForm();	
		Utils.showCurrentTab();
		initOnWindowResizeEvent();
		
	};
	
	initHistory  = function(){
		History.Adapter.bind(window,'statechange',function(){ // Note: We are using statechange instead of popstate
			var State = History.getState(); // Note: We are using History.getState() instead of event.state
			console.log('STATE:' + State)
		});
		
		$('#registrationTab').on('click',function(){
			// Change our States
			console.log("SFFdsfsdf");
			History.pushState({state:1}, "State 1", "/here"); // logs {state:1}, "State 1", "?state=1"
		});
	};
	
	adjustBtnHeight = function(){
		$(function(){
			Utils.unhideBody();
					var max = 0;
		var btns = $('#fancyLoginList').find('button');
		btns.each(function(){
			//console.log($(this));
			var btnH = $(this).outerHeight();
			//console.log(btnH);
			if(max < btnH ) max = btnH;
		});
		
		});

	};
	
	initHistory = function(){
		console.log("HERE");
			// Bind to StateChange Event

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
			//console.log("loading Login Page...");
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
				//console.log("email and password are correct");
				var rememberMe = $("#rememberMeCheckbox").is(":checked");			
				var errorMessage = Server.loginUser(Login._getUsername(),Login._getPassword(),rememberMe);
				if(errorMessage)Utils.addErrorMessageTo("#loginErrors",errorMessage);
				
		}
	};
	
	Login.clearAllErrorMessages = function(){
		Utils.clearErrorMessagesFrom("#loginErrors");
	};


	initOnWindowResizeEvent = function(){
		$(window).resize(function(){
			toggleNavigationBtn();
			initLoginFormHints();
			Registration.initRegistrationFormHints();
		});
	}
	
	initLoginForm = function(){
		$('#fiteagleLoginBtn').on('click',function(){
			window.setTimeout(function(){
				Utils.changeFocusOnEnterClick("#username","#password");
				Utils.addOnEnterClickEvent("#password","#signIn");
				$('#username').focus();					
			},200);
		});
		initRememberMeCheckbox();
		initLoginFormHints();
	};
	
	initRememberMeCheckbox = function(){
		$('#rememberMeCheckbox').prettyCheckable({color:'yellow'});
	};	
	
	initLoginFormHints = function(){
		var position ="top";
		//(Utils.isSmallScreen()) ? position = "top" : position = "right";			
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
		//console.log('Current User: ' + Utils.userToString());
		if(!user){
				return false;
		}
		return true;
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
	
	initHistory = function(){
		
		$('#registrationTab').on('click',function(){
			
		});
	};
	
	return Login;

});
	







