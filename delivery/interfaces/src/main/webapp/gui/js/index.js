requirejs.config({
  shim: {
    'bootstrap'	: ['jquery','cookie'],
    'history' : ['jquery'],
    'ajaxify' : ['history'],
	'prettyCheckable' : ['jquery'],
	'scrollTo' : ['jquery']
  }
});

define(['jquery','bootstrap','loginPage','mainPage','statusPage', 'utils', 'history', 'ajaxify','fileSaver'], 

function($,Bootstrap,LoginPage,MainPage,StatusPage,Utils, UnifiPage, UnifiLogin){
	
	console.log("index.js is called");
					
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
	// new Event listener for end of the window resizing. Called : "resizeEnd"	
	 $(window).resize(function() {
        if(this.resizeTO) clearTimeout(this.resizeTO);
        this.resizeTO = setTimeout(function() {
            $(this).trigger('resizeEnd');
        }, 200);
    });

});	
	

	











