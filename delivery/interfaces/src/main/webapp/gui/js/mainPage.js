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
    * @private
	* @memberOf Main#
	*/
	initMainPage = function(){
		$('#fiteagle').addClass('mainWindow'); // class in order to distinguish between main and login pages
		performScreenAdjustments();	
		initUserInfoPanel();		
		Profile.initForm();
		PublicKeys.initForm();
		Certificates.initForm();
		Utils.showCurrentTab();
	};
	
	/**
	* Triggers functions for adjusting the site view for better representing depending on the current screen size such as:
	* collapsing of the opened sections for small screen devices and opening for a large ones and other related tasks.
    * @private
	* @memberOf Main#
	*/
	performScreenAdjustments = function(){
		Utils.unhideBody();
		initCollapseHeaders();
		if(Utils.isSmallScreen()){
			initForSmallScreens();
		}else{
			initForLargeScreens();
		}
		initCollapseSigns();
	};
	
	
	/**
	* Collapses sections identified by "#yourSliceList" and "#availableSlicesList" selectors
	* @param boolean value triggers collapse function. True value collapses the container, false otherwise opens it.
	* @private
	* @memberOf Main#
	*/
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
	
	/**
	* Initializes change of the icon sign for all of the headers with the class with  the ".collapseHeader" selector after clicking on it.
	* @private
	* @memberOf Main#
	*/
	initCollapseHeaders = function(){
		$('.collapseHeader').on('click',function(){
			var icon = $(this).find('.collapseSign');
			window.setTimeout(function(){
				initCollapseSignFor(icon);
			},100);
		});
	};
	
	/**
	* Initializes change of the icon sign for all of the elements with the class with  the ".collapseSign" selector.
	* @private
	* @memberOf Main#
	*/
	initCollapseSigns = function(){
		var icons = $('.collapseSign');
		icons.each(function(){
			var t = $(this);
			initCollapseSignFor(t);
		});		
	};
	
	/**
	* Replaces the section header icon sigh according to the current section state. 
	* Icon chevron right from the font awesome icons is shown if the section is collapsed and icon chevron right is section is opened.
 	* @param icon_object is an item object to change the icon sign after the section is opened or collapsed.
	* @private
	* @memberOf Main#
	*/
	initCollapseSignFor = function(icon_object){
		var selector = icon_object.closest('div').attr('data-target');
		isOpen  = $(selector).hasClass('in');
			//console.log("selector" + selector + " is open " + isOpen);
			if(isOpen){
					icon_object.attr('class','');
					icon_object.addClass('collapseSign icon-chevron-down');
			}else{
					icon_object.attr('class','');
					icon_object.addClass('collapseSign icon-chevron-right');
		}
	};
	
	/**
	* Defines the behaviour for the small size devises. Collapses Aside sections for better representation on the narrow window screen.
	* Unhides small screen navigation toolbar.
    * @private
	* @memberOf Main#
	*/
	initForSmallScreens = function(){
		collapseAsideSections(true);	
		Utils.unhideElement('#toolbar .btn-navbar');		
	};
	
	/**
	* Defines the behaviour for the large size devises. Opens Aside sections for better representation on the wide window screen.
	* Hides small screen navigation toolbar.
    * @private
	* @memberOf Main#
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
						 scrollTop: scrollTo.offset().top-12-$('#navigation').height()
					}, 1000);
				},100)
			}
		});
		Utils.updateUserInfoPanel();
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
			var modal = createSignOutModal();
			modal.modal('show');
			$('#signOutOkBtn').on('click',function(){
				var isCookieDeleted = Server.invalidateCookie();
				if(isCookieDeleted) Main.signOut();
			});
		});
	};
	
	
	/**
	* Creates and appends to the body confirmation modal to be shown before signing out. 
	* The user is signed out only after confirming his decision.
    * @private
    * @memberOf Main#
	* @return sign out modal object
	*/
	createSignOutModal = function(){
		var modalBody = $('<div>').addClass('centered').append('<h5>'+Messages.signOutConfirm+'</h5>')
		var signOutModal = Utils.createConfirmModal('signOutModal','signOutOkBtn','YES','NO',modalBody);
		$('body').append(signOutModal);
		return signOutModal;
	}
	
	
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



