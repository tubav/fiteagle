define(['require','utils',],
/**
 * @lends MainPage
 */ 
function(require,Utils,LoginPage){
	
	//console.log("MainPage.js:" + LoginPage);
	
	console.log("mainPage.js is loaded");
	
	Main = {};
	
	Main.initMainPage = function(){
		Utils.unhideBody();
		this.initUserInfoPanel();
		this.initManageUserProfileForm();
	};

	Main.signOut = function(){
		sessionStorage.clear();
		require('loginPage').load();
	};

	Main.initUserInfoPanel = function(){
		console.log("init User Info Panel");
		var user = Utils.getCurrentUser();
		console.log('current user '+ Utils.userToString());
		$("#userName").text(user.email);
		
		// workaroud for BOOTSTRAP's DropDown bug ("active" class for li elements removed)
		$("#userInfoDropdown a").click(function(){
			var lis = $("#userInfoDropdown li");
			lis.removeClass("active");
		});
		
		this.initSignOutBtn();
	};


	Main.initManageUserProfileForm = function(){
		Utils.clearErrorMessagesFrom("#manageProfile .errorMessages");
		
		var user = Utils.getCurrentUser();
		
		console.log(user);
		$("#inputFirstName").val(user.firstName);
		$("#inputLastName").val(user.lastName);
		$("#inputEmail").val(user.email);
		$("#inputUsername").val(user.username);
	};

	Main.initSignOutBtn = function(){
		$("#signOut").click(function(){
			console.log("signOut clicked");
			Utils.resetUser();
			Main.signOut();
		});
	};
	
	Main.load = function(){
			console.log("loading main Page...");
			//Login.showLoadingSign();
			var url = "html/main.html";
			$("#navigation").load(url + " #toolbar",
				function(){
					//Login.hideLoadingSign();
					$("#main").load(url + " #mainArea",
						function(){								
							Main.initMainPage(); 
						});
				}
			);
	};
	
	return Main;
	
});



