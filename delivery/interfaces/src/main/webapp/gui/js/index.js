requirejs.config({
  shim: {
    'bootstrap': ['jquery'] 
  }
});

require(['bootstrap','loginPage','mainPage','utils'], 

function(Bootstrap,LoginPage,MainPage,Utils){
					
	$.ajaxSetup({cache:false});

	
	if(LoginPage.isUserLoggedIn()){
		MainPage.load();
	}else{
		LoginPage.load();		
	}
			
});	
	

	











