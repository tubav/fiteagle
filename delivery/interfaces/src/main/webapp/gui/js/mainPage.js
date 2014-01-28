define(['require','utils','profile','publicKeys', 'certificates','server','index'],
/**
 * @lends MainPage
 */ 
function(require,Utils,Profile,PublicKeys,Certificates,Server){
	
//	console.log("mainPage.js is loaded");
	 /** 
	 * The FITeagle main page class contains functions required for initialization of the 
	 * main page forms and elements located on the page.
     * @class
     * @constructor
     * @return Main Object
     */
	Main = {};
	
	/**
	* Checks if any hashtag is stored before. In case the tag is found the function triggers the tab opening according to this tag.
	* @private
	* @memberOf Main#
	*/
	checkForStoredHashTags = function(){
		var tag = Utils.getStoredHashTag();
		if(tag && tag.length > 1){
			openDesktopTab(tag); // trying to open a tab for the tag
		}else{
			window.location.hash = "#manage";
			openDesktopTab('#manage');
		}
	};
		
	
	/**
	* Initializes change of the icon sign for all of the headers with the class with  the ".collapseHeader" selector after clicking on it.
	* @private
	* @memberOf Main#
	*/
	initCollapseHeaders = function(){
		$('.collapseHeader').click(function(event){
			var header = event.currentTarget;
			var icon = header.previousElementSibling;
			window.setTimeout(function(){
				switchCollapseSignFor($(icon));
			},100);
		});
	};
			
	/**
	* Initializes the icon sign replacement within the given container according to the current section state. 
	* Icon chevron right from the font awesome icons is shown if the section is collapsed and icon chevron right is section is opened.
 	* @param {Object} icon_object is a container object to change the icon sign after the section is opened or collapsed.
	* @private
	* @memberOf Main#
	*/
	switchCollapseSignFor = function(icon_object){
		var selector = $(icon_object[0].nextElementSibling).attr("data-target");
		var isOpen  = $(selector).hasClass('in');
			if(isOpen){
				icon_object.attr('class','');
				icon_object.addClass('collapseSign fa fa-caret-down fa-li');
			}else{
				icon_object.attr('class','');
				icon_object.addClass('collapseSign fa fa-caret-right fa-li');
		}
	};
	
	/**
	* Defines the behaviour for clicking on "back" or "next" browser buttons aka browser history.
	* The function opens the appropriate form views.
	* @private
	* @memberOf Main#
	*/
	initHashtagChange = function(){
		$(window).unbind();
		$(window).on('popstate hashchange',function(){
			var state = window.location.hash;
			openDesktopTab(state);
		});
	};

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
		removeUnusedElements();
		checkForStoredHashTags();
		
		require(["jsPlumb"], function(jsPlumb) {
			initResourceButtons();
		});
	};
	
	removeUnusedElements = function(){
		switch(Utils.getCurrentUser().role){
		case "ADMIN":
			$("#task").remove();
			$("#usercourse").remove();
			$("#addcourse").remove();
			$("#userAside").remove();
			$("#tbownerAside").remove();
			$("#usertaskaside").remove();
			$("#usertasksaside").remove();
			$("#openepcqosuser").remove();
			break;
		case "TBOWNER":
			$("#task").remove();
			$("#usercourse").remove();
			$("#addcourse").remove();
			$("#createtestbed").remove();
			$("#userAside").remove();
			$("#adminAside").remove();
			$("#usertaskaside").remove();
			$("#usertasksaside").remove();
			$("#openepcqosuser").remove();
			break;
		default:
			$("#course").remove();
			$("#testbed").remove();
			$("#newtask").remove();
			$("#createtestbed").remove();
			$("#createcourse").remove();
			$("#taskaside").remove();
			$("#tasksaside").remove();
			$("#adminAside").remove();
			$("#tbownerAside").remove();
			$("#openepcqos").remove();
			$("#fusecoplayground").remove();
		}
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
		var navLinks = $(".navigationLink a");
		navLinks.off();
		navLinks.not('#signOut').on('click',function(e){
			e.preventDefault();
//			$("#taskAsides").addClass("hidden");
//			$("#homeAsides").removeClass("hidden");
			
			var t = $(this);
			var linkHref = t.attr('href');
			//TODO: for non-collapseHeaders
			//initScrollToForm(linkHref+' h4.collapseHeader');
			var hash = linkHref.toLowerCase();
			history.pushState(linkHref, "page "+linkHref, "/"+hash);
			openDesktopTab(hash);
			
			if($(this.parentNode).hasClass("taskLink")){
				$("#tasksAsides").addClass("hidden");
				$("#taskAsides").removeClass("hidden");
			}
			
		});
		
		var courseLinks = $(".courseLink a");
		courseLinks.off();
		courseLinks.on('click',function(e){
			e.preventDefault();
//			var t = $(this);
//			var linkHref = t.attr('href');
//			var hash = linkHref.toLowerCase();
//			
//			var course = $(hash);
//			if(course != null){
//				course.removeClass("hidden");
//			}
			$("#homeAsides").addClass("hidden");
			$("#tasksAsides").removeClass("hidden");
		});
		
		$(".allCoursesLink").on('click',function(e){
			e.preventDefault();
			$("#tasksAsides").addClass("hidden");
			$("#homeAsides").removeClass("hidden");
		});
		
		$(".allTasksLink").on('click',function(e){
			e.preventDefault();
			$("#taskAsides").addClass("hidden");
			$("#tasksAsides").removeClass("hidden");			
//			var courseAside = this.parentNode.id.replace("task","tasks");
//			$("#"+courseAside).removeClass("hidden");
		});
		
		Utils.updateUserInfoPanel();
		initSignOutBtn();
		initHashtagChange();
	};
	
	
	/**
	* Loads HTML for the FITeagle main page dynamically and triggers the page initialization after the loading is successfully completed.
	* @public
	* @name Main#load
	* @function
	**/
	Main.load = function(){
		var url = "main.html";
		
		$("#navigation").load(url + " #toolbar",
			function(){
				$("#main").load(url + " #mainArea",
					function(){
						initMainPage();
					});
			}
		);
	};
	
	/**
	* Opens the appropriate tab from the user info dropdown menu on the main page according to the
	* hash tag entered in the browser's url field. The function searches within a "user info dropdown" menu
	* for a link with the reference showing to the specified container.
	* If no tab is found for a hash tag then "manage profile" tab is opened per default.
	* @see Twitter Bootstrap tab documentation for more information.
	* @param {String} hash - tag to opening a form for.
	* @example openTab('#keys') tries to open a tab identified by a "#keys" selector
	* @private
	* @memberOf Main#
	*/
	openDesktopTab = function(hash){
		if(hash != null){
			var navLinks = $(".navigationLink li");
			navLinks.removeClass("active");
			
			var a = $('.navigationLink [href$='+hash+']');
			if(a.length != 0){
				a.tab('show'); // if found show the tab
				Utils.storeHashTag(hash);
			}
			else{
//				$('[href$=#task]').tab('show'); // if not found show task tab
			}
		}
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
	};
	
	
	/**
	* Defines the behaviour for the large size devises. Opens Aside sections for better representation on the wide window screen.
	* Hides small screen navigation toolbar.
    * @private
	* @memberOf Main#
	*/
	initForLargeScreens = function(){
		//collapseAsideSections(false);	
		Utils.hideElement('#toolbar .btn-navbar');
	};
	
	/**
	* Defines the behaviour for the small size devises. Collapses Aside sections for better representation on the narrow window screen.
	* Unhides small screen navigation toolbar.
    * @private
	* @memberOf Main#
	*/
	initForSmallScreens = function(){
		//collapseAsideSections(true);	
		Utils.unhideElement('#toolbar .btn-navbar');		
	};
	
	//	/**
	//	* Initializes scrolling for small screen devices to the container specified by a selector. 
	//	* The scrolling lasts one second and has an offset of navigation menu height.
	//	* @param selector - container selector to scroll to
	// 	* @private
	//	* @memberOf Main#
	//	*/
	//	initScrollToForm = function(selector){
	//		var scrollTo = $(selector);
	//		if(Utils.isSmallScreen()){
	//			setTimeout(function(){
	//				$('html, body').animate({
	//					 scrollTop: scrollTo.offset().top-15-$('#navigation').height()
	//				}, 1000);
	//			},100);
	//		}	
	//	};
	
	/**
     * Defines the behaviour after clicking on the singOut button: Cookie invalidation on the server and singing out of the current user. 
	  * @private
	  * @memberOf Main#
    */ 
	initSignOutBtn = function(){
		$("#signOut").on('click',function(e){
			e.preventDefault();
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
		history.pushState('', "page ", "/"); // change url hash to "home"
		require('loginPage').load();
	};
	
	return Main;
	
});



