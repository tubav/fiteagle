define(['validation','utils','messages','server'],
/**
 * @lends Registration
 */ 
function(Validation, Utils,Messages,Server){

	Certificates = {}
	

	
	Certificates.initForm = function(){
		
			initPublicKeySelect("#selectKeyForGeneration");	
			initGenerateCertificatesBtn();		
			initGenerateKeyAndCertificateBtn();	
			
	};
	
	initGenerateCertificatesBtn = function(){
		$("#genPubKey").on('click',function(){
			var selectedKeyDescription = $("#publicKeySetSelect option:selected").html();
			//console.log("KEY DESCRIPTION: " + selectedKeyDescription);
			var pubString = Server.generateCertificateForPiblicKey(selectedKeyDescription);
			if(pubString){
					addCertificateTextarea(pubString);
			}
		});
	};
	
	initGenerateKeyAndCertificateBtn = function(){
		$('#genKeyAndCertificate').on('click',function(){
			var passphrase = $('#inputPassphrase').val();
			if(passphrase.length > 0){
				var keyAndCertificate = 
					Server.generatePublicKeyAndCertificate(
						passphrase,"#genKeyAndCertificateSign",afterSuccessGeneration);
				addKeyAndCertificateTextarea(keyAndCertificate);
			}
		});
	};
	
	afterSuccessGeneration = function(){
			console.log('after generation');
			require('publicKeys').initExistingPublicKeysForm();
			Certificates.initForm();
	};
	
	addKeyAndCertificateTextarea = function(keyAndCertificate){
		$('#generatedKeyAndCertificate').children().remove();
		var textarea = $('<textarea rows=30 style="resize:none" disabled></textarea>').addClass("span10");
		textarea.val(keyAndCertificate);
		$('#generatedKeyAndCertificate').append(textarea)
			.append('<br/>')
			.append(createDownloadCertificateBtn('key_and_certificate.crt',keyAndCertificate));
	};
	
	addCertificateTextarea = function(pubKeyString){
		$('#generatedCertificate').children().remove();
		var textarea = $('<textarea rows=30 style="resize:none" disabled></textarea>').addClass("span10");
		textarea.val(pubKeyString);
		$('#generatedCertificate')
			.append(textarea)
			.append('<br/>')
			.append(createDownloadCertificateBtn('certificate.crt',pubKeyString));
		
	};
	
	createDownloadCertificateBtn = function(filename,text){
		
		var appendix = btoa(text);
		
		var hattr = "data:application/octet-stream;charset=utf-8;base64," + appendix;
		
		//console.log(hattr);
		
		var downloadBtn = $('<a>')
				.addClass('btn btn-inverse span2 nomargin')
				.html('<i class="icon-download-alt"></i>Download')
				.attr('download',filename)
				.attr('href',hattr);
		
		return downloadBtn;
	}

	initPublicKeySelect = function(selector){
		console.log("Init public Key Select");
		$(selector).children().remove();
		var publicKeys = getAllUserPublicKeys();
		var label;
		if(publicKeys.length == 0){ // if no keys	
			Utils.hideElement("#pubicKeySetLabel");
			$(selector).append('<span class="alert alert-info span8">'+Messages.noPublicKeys+"</span>");
			$("#genPubKey").addClass('disabled');
			
		}else{ // if keys exists
		
			$("#genPubKey").removeClass('disabled');
			label = $('<label>')
				.addClass('span2')
				.html("Please select a public key");
			var sel = $('<select>')
				.addClass('span12')
				.attr("id","publicKeySetSelect");
			
			var selDiv = $('<div>').addClass('span8').append(sel);	
			
			for(var i = 0; i < publicKeys.length; i ++){
				var description = publicKeys[i].description;
				sel.append(createOptionItem(description));
			}
			$(selector).append(label);
			$(selector).append(selDiv);
		}			
	};
	
	getAllUserPublicKeys = function(){
		var username = Utils.getCurrentUser().username;
		var user = Server.getUser(username);	
		return user.publicKeys;
	}
	
	createOptionItem = function(keyDescription){	
		//console.log("Creating option");
		var opt  = $('<option>').html(keyDescription);
		return opt;
	};
		
	return Certificates;
});
