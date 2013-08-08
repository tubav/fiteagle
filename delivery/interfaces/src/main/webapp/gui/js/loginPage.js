define(['require','validation','registration','utils','messages','history','ajaxify','prettyCheckable'],

function(require,Validation,Registration,Utils,Messages){
	
	//console.log("loginPage.js is loaded");
	
	 /** 
     * Login class
     * This Login class contains functions required for initialization of the Forms and Elements located on the login page.
     * @class
     * @constructor
     * @return Login Object
     */
	Login = {}; 
	
	/**
	  * Initialization of the Login page elements and related events. The function unveils the wrapper container,
	  * so all of the page element are shown. Initiates on window resize events for better page representation on a different
	  * devices, from a small screened phones to the large screen desktops. 
	  * @public
	  * @name Login#initLoginPage
	  * @function	  
	  */  
	Login.initLoginPage = function(){
		$('#fiteagle').removeClass('mainWindow');
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
	
	/* Currently not used
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
	}; */
	
	/**
      * Stores current Tab name in a session storage
	  * @private
	  * @memberOf Login#
      * @param {String} currentTab - tab name to be stored in a session Storage
      * @example
      * setCurrentTab('homeTab')
      */ 
	setCurrentTab = function(currentTab){
		if(typeof(Storage)!=="undefined"){
			sessionStorage.currentTab = currentTab;
		  }
		else{
			console.log("Session storage is not supported !");
		}
	};
	
	/**
      * Returns current tab name from a session storage
	  * @private
	  * @memberOf Login#
      * @return {String} currentTab - tab name stored in a session Storage
      */ 
	getCurrentTab = function(){
		return sessionStorage.currentTab;
	};
	
	/**
      * Creates on click event listener for each tab in the navigation menu, so its value is stored in the session storage. 
	  * The value can be used in order to know last selected tab, before the page was reloaded.
	  * @private
	  * @memberOf Login#
      */ 
	initNavigationMenu = function(){
		toggleNavigationBtn();
		$("#navigation ul li a").on('click',function(){
			Utils.setCurrentTab("#"+$(this).attr("id"));
		});	
	};
	
	/**
      * Toggles navigation button depending on the screen size width. 
	  * It is shown on a small screen window and hidden on a large one.
	  * @private
	  * @memberOf Login#
      * @function 
      */ 
	toggleNavigationBtn = function(){
		if(Utils.isSmallScreen()){
			$('.btn-navbar').removeClass('hidden');
		}else{
			$('.btn-navbar').addClass('hidden');
		}
	};

	/**
      * Loads the HTML for the loading page into the DOM and triggers the loading page initialization function.
	  * @public
	  * @name Login#load
      * @function 
	  * @see initLoginPage()
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

	/**
      * Validates the value entered in the username field on the login form. Creates appropriate error message if the value is 
	  * empty or wrong and shows it within the login form. 
	  * @private
	  * @memberOf Login#
      * @see Validation._isName for more information about the applied validation rule
	  * @see Messages.emptyUsername for more information about the empty password error message
      * @see Messages.wrongUsername for more information about the wrong password error message 
      */ 
	checkUsername = function(){
		//log("checking email");	
		return Utils.checkInputField(
								"#username",
								"#loginErrors",
								Validation._isName,
								Messages.emptyUsername,
								Messages.wrongUsername
		);
	};

	/**
      * Validates the value entered in the password field on the login form. Creates appropriate error message if the value is 
	  * empty or wrong and shows it within the login form. 
	  * @private
	  * @memberOf Login#
      * @see Validation._isPassword for more information about the applied validation rule
      * @see Messages.emptyPassword for more information about the empty password error message
      * @see Messages.wrongPassword for more information about the wrong password error message  
      */ 
	checkPassword = function(){
		//log("checking password");
		return Utils.checkInputField(
								"#password",
								"#loginErrors",
								Validation._isPassword,
								Messages.emptyPassword,
								Messages.wrongPassword
		);
	};
	
	/**
      * Collects user login information sends it to the server for authentication.
	  * The function validates the login information provided by a user in the login form and shows the appropriate error
	  * message in case the error occurs on the server or while validation. 
	  * @public
	  * @name Login#loginUser
      * @function 
	  * @see Server.loginUser for more information about communication with server communication for authentication.
	  * @see checkUsername for username validation
	  * @see checkPassword for password validation
      */ 
	Login.loginUser = function(){
		//console.log('trying to login user...');
		//console.log('trying to login user...');
		Utils.clearErrorMessagesFrom("#loginErrors");
		if(checkUsername() & checkPassword()){
			//console.log("email and password are correct");
			var rememberMe = $("#rememberMeCheckbox").is(":checked");
			var username = $('#username').val();
			var pswd = $('#password').val();
			var errorMessage = Server.loginUser(username,pswd,rememberMe);
			if(errorMessage)Utils.addErrorMessageTo("#loginErrors",errorMessage);	
		}
	};

	/**
      * Initiates on window resize event that toggles navigation button visibility, re-initiates tooltips for the login and registration form
	  * depending on a current screen size.  
	  * @private
	  * @memberOf Login#
      * @see Registration.initRegistrationFormHints for more information about the tooltip initialization for the registration form
	  */
	initOnWindowResizeEvent = function(){
		$(window).resize(function(){
			toggleNavigationBtn();
			initLoginFormHints();
			Registration.initRegistrationFormHints();
		});
	}
	
	/**
      * Initiates the login form. It Defines the focus change for enter click events for the login form fields.
	  * Styles the remember me checkbox by utilizing prettyCheckable plugin functions and images as well as 
	  * initiates login form field hints with the twitter bootstrap tooltips.
	  * @private
	  * @memberOf Login#
      * @see http://arthurgouveia.com/prettyCheckable/
	  */
	initLoginForm = function(){
		Utils.changeFocusOnEnterClick("#username","#password");
		Utils.addOnEnterClickEvent("#password","#signIn");

		$('#rememberMeCheckbox').prettyCheckable({color:'yellow'});
		initLoginFormHints();
	};
	
	/**
      * Initiates the login form hints. The hints are bootstrap tooltips with "top" position triggered on "focus".
	  * @private
	  * @memberOf Login#
      * @see http://twitter.github.io/bootstrap/javascript.html#tooltips
	  */
	initLoginFormHints = function(){
		var position ="top";
		//(Utils.isSmallScreen()) ? position = "top" : position = "right";			
		Utils.initTooltipFor("#username",Messages.usernameHint,position,"focus");
		Utils.initTooltipFor("#password",Messages.passwordHint,position,"focus");
	};
	
	/**
      * Initiates on enter click event for "register" link identified by #registrationLink on the Home page. 
	  * The link opens the registration tab on the login page.
	  * @private
	  * @memberOf Login#
	  */
	initRegisterLink = function(){
		$("#registrationLink").on('click',function(e){
			e.preventDefault();
			$("#registrationTab").click();
		});
	}
	
	/**
      * Initiates on click event for "signIn" button on the Home page identified by #signIn. 
	  * The Function Login.loginUser() is called after clicking on this button. 
	  * @private
	  * @memberOf Login#
	  * @see Login.loginUser
	  */
	initSignInBtn = function(){	
		$("#signIn").on('click',function(){
			 //console.log("SignIn button clicked !");
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
	
	/**
      * Returns the username of the remembered user by a login process.  
	  * @public
	  * @name Login#getRememberedUsername
      * @function 
	  * @return username of the remembered user or "undefined" if no user is remembered 
	  * @see https://github.com/carhartl/jquery-cookie
      */ 
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
	







