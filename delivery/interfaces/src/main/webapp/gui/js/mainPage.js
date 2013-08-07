define(['require','utils','profile','publicKeys', 'certificates','server'],
/**
 * @lends MainPage
 */ 
function(require,Utils,Profile,PublicKeys,Certificates,Server){
	
	//console.log("mainPage.js is loaded");
	 /** 
     * Main class
     * This Main Page class contains functions required for initialization of the Forms and Elements located on the FITeagle main page.
     * @class
     * @constructor
     * @return Main Object
     */
	Main = {};
	
	/**
	* Triggers functions for main Page initialization such as: screen adjustments by resizing, initialization of user panel, profile form,
	* public keys form, manage certificates form, and showing of the last opened tab.
	*/
	initMainPage = function(){
		performScreenAdjustments();	
		//initAsideSection();
		initUserInfoPanel();		
		Profile.initForm();
		PublicKeys.initForm();
		Certificates.initForm();
		Utils.showCurrentTab();
	};
	
	/**
	* Triggers functions for adjusting the site view for better representing depending on the current screen size such as:
	* collapsing of the opened sections for small screen devices and opening for a large ones and other related tasks.
	*/
	performScreenAdjustments = function(){
		Utils.unhideBody();
		initCollapseHeaders();
		initOnWindowResizeEvents();
		if(Utils.isSmallScreen()){
			initForSmallScreens();
		}else{
			initForLargeScreens();
		}
		initCollapseSigns();
	};
	
	initOnWindowResizeEvents = function(){
		$(window).resize(function(){
				//performScreenAdjustments();
		});			
	};
	
	collapseAsideSections = function(bool){
		var sliceList = $("#yourSliceList");
		var availableSlices  = $('#availableSlicesList');
		if(bool){
			sliceList.removeClass('in');
			availableSlices.removeClass('in');
		}else{
			if(!sliceList.hasClass('in')){
					sliceList.addClass('in');
			}
			if(!availableSlices.hasClass('in')){
					availableSlices.addClass('in');
			}	
		}
	};
	
	initCollapseHeaders = function(){
		$('.collapseHeader').on('click',function(){
			var icon = $(this).find('.collapseSign');
			window.setTimeout(function(){
				initCollapseSignFor(icon);
			},100);
		});
	};
	
	initCollapseSigns = function(){
		var icons = $('.collapseSign');
		//console.log("INIT COLLAPSE _______________");
		icons.each(function(){
			var t = $(this);
			initCollapseSignFor(t);
		});		
	};
	
	initCollapseSignFor = function(obj){
		var selector = obj.closest('div').attr('data-target');
		isOpen  = $(selector).hasClass('in');
			//console.log("selector" + selector + " is open " + isOpen);
			if(isOpen){
					obj.attr('class','');
					obj.addClass('collapseSign icon-chevron-down');
			}else{
					obj.attr('class','');
					obj.addClass('collapseSign icon-chevron-right');
		}
	};
	
	/**
	* Defines the behaviour for the small size devises. Collapses Aside sections for better representation on the narrow window screen.
	* Unhides small screen navigation toolbar.
	*/
	initForSmallScreens = function(){
		collapseAsideSections(true);	
		Utils.unhideElement('#toolbar .btn-navbar');		
	};
	
	/**
	* Defines the behaviour for the large size devises. Opens Aside sections for better representation on the wide window screen.
	* Hides small screen navigation toolbar.
	*/
	initForLargeScreens = function(){
		collapseAsideSections(false);	
		Utils.hideElement('#toolbar .btn-navbar');
	};

	/**
      * Initiates user info panel located in the main page header section. Defines the behaviour for clicking on the panel items: 
	  * opening the corresponding window. Sets current user first and last name in it.
	  * Scrolls to the appropriate fields to the top after they are selected from the menu.
	  * Triggers sign out button initialization.
	  * @private
	  * @memberOf Main#
      */ 
	initUserInfoPanel = function(){		
		// workaroud for BOOTSTRAP's DropDown bug ("active" class for li elements removed)
		$("#userInfoDropdown a").click(function(){
			var t = $(this);
			var linkID  = t.attr("id");
			var linkHref = t.attr('href');
			Utils.setCurrentTab("#"+linkID);
			var lis = $("#userInfoDropdown li");
			lis.removeClass("active");
			var scrollTo = $(linkHref).find('h4.collapseHeader');
			if(Utils.isSmallScreen()){
				setTimeout(function(){
					$('html, body').animate({
						 scrollTop: scrollTo.offset().top-10
					}, 1000);
				},100)
			}
		});
		
		var user = Utils.getCurrentUser();
		//console.log('current user is set to: '+ Utils.userToString(user));
		$("#userName").text(user.firstName +" " + user.lastName);
		initSignOutBtn();
	};

	/**
      * Defines the behaviour after clicking on the singOut button: Cookie invalidation on the server and singing out of the current user. 
	  * @private
	  * @memberOf Main#
     */ 
	initSignOutBtn = function(){
		$("#signOut").on('click',function(e){
			e.preventDefault();
			//console.log("signOut clicked");		
			var isCookieDeleted = Server.invalidateCookie();
			if(isCookieDeleted) Main.signOut();
		});
	};
	
	
	/**
	* Signs out the current user. Resets its data in the session storage and loads the login page.  
	* @public
	* @name Main#signOut
	* @function
	**/
	Main.signOut = function(){
		Utils.resetUser();
		require('loginPage').load();
	};
	
	/**
	* Loads HTML for the FITeagle main page dynamically and triggers the page initialization after the loading is successfully completed.
	* @public
	* @name Main#load
	* @function
	**/
	Main.load = function(){
			//console.log("loading main Page...");
			var url = "html/main.html";
			$("#navigation").load(url + " #toolbar",
				function(){
					$("#main").load(url + " #mainArea",
						function(){								
							initMainPage(); 
						});
				}
			);
	};
	
	return Main;
	
});



