requirejs.config({
  shim: {
    'bootstrap': ['jquery'] 
  }
});

require(['bootstrap','statusPage'], 

function(Bootstrap,StatusPage){
					
	$.ajaxSetup({cache:false});

	StatusPage.init();
			
});	