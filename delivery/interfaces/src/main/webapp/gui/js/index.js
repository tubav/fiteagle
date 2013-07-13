requirejs.config({
  shim: {
    'bootstrap': ['jquery','cookie']
  }
});

require(['bootstrap','loginPage','mainPage','utils'], 

function(Bootstrap,LoginPage,MainPage,Utils){
					
	$.ajaxSetup({cache:false});		

		if(LoginPage.isUserLoggedIn()){
			MainPage.load();
		}else{
			var rememberedUser = Login.getRememberedUsername();
			//console.log("REMEMBERED: " + rememberedUser);
			if(rememberedUser){
				var user = Server.getUser(rememberedUser);
				if(user){
					Utils.setCurrentUser(user);
					MainPage.load();
				}
			}else{
				LoginPage.load();		
			}
		}		
});	
	

	











