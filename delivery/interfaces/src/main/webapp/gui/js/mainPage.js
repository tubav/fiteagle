define(['require','utils','profile','publicKeys', 'certificates'],
/**
 * @lends MainPage
 */ 
function(require,Utils,Profile,PublicKeys,Certificates){
	
	//console.log("mainPage.js is loaded");
	
	Main = {};
	
	initMainPage = function(){

		performScreenAdjustments();	
		//initAsideSection();
		initUserInfoPanel();
		
		Profile.initForm();
		PublicKeys.initForm();
		Certificates.initForm();
		Utils.updateInfoPanel();
		initOnWindowResizeEvents();

	};
	
	performScreenAdjustments = function(){
		Utils.unhideBody();
		Utils.showCurrentTab();
		initCollapseHeaders();
		
		if(Utils.isSmallScreen()){
			initForSmallScreens();
		}else{
			initForLargeScreens();
		}
		initCollapseSigns();
	};
	
	initOnWindowResizeEvents = function(){
		$(window).resize(function(){
				performScreenAdjustments();
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
	
	initForSmallScreens = function(){
		collapseAsideSections(true);	
		Utils.unhideElement('#toolbar .btn-navbar');		
	};
	
	initForLargeScreens = function(){
		collapseAsideSections(false);	
		Utils.hideElement('#toolbar .btn-navbar');
	};


	initUserInfoPanel = function(){
			
		// workaroud for BOOTSTRAP's DropDown bug ("active" class for li elements removed)
		$("#userInfoDropdown a").click(function(){
			
			var linkID  = $(this).attr("id");
			Utils.setCurrentTab("#"+linkID);
			var lis = $("#userInfoDropdown li");
			lis.removeClass("active");
		});
		
		initSignOutBtn();
	};

	initSignOutBtn = function(){
		$("#signOut").on('click',function(e){
			e.preventDefault();
			//console.log("signOut clicked");		
			invalidateServerCookie(signOut);
			
		});
	};
	
	signOut = function(){
		Utils.resetUser();
		require('loginPage').load();
	};
	
	invalidateServerCookie = function(successFunction){
		var username = Utils.getCurrentUser().username;
		$.ajax({
			cache: false,
			type: "DELETE",
			async: false,
			url : "/api/v1/user/"+username+"/cookie",
			beforeSend: function(xhr){
				xhr.setRequestHeader("Authorization",
                "Basic " + Utils.getCredentials()); // TODO Base64 support
			},
			success: function(answer,status,xhr){
				console.log(":SUCCESS" + answer) ;
				successFunction();
			},
			error: function(xhr,status,thrown){
				console.log("Response " + xhr.responseText);
				console.log(status);
				console.log(thrown);
			},
			complete: function(){
				console.log("ENDE DELETING");
			},
		});
	};
		
	Main.load = function(){
			console.log("loading main Page...");
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



