define(['validation','utils','messages','server'],
function(Validation, Utils,Messages,Server){

	/** 
     * Certificates class.
     * The Certificates class contains functions for existing public key form initialisation 
	 * as well as for certificate generation.
     * @class
     * @constructor
     * @return Certificates object
     */
	Certificates = {}
	

	Certificates.initForm = function(){
		
		initPublicKeySelect();	
		initGenerateCertificatesBtn();
		initPassphraseField();
		initGenerateKeyAndCertificateBtn();
		$(window).bind('resizeEnd',function(){
			initPassphraseField();
		});
			
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
	
	initPassphraseField = function(){
		
		Utils.addOnEnterClickEvent("#inputPassphrase","#genKeyAndCertificate");
		
		var placement;
		(Utils.isSmallScreen())? placement ="top" : placement = "right";
		Utils.initTooltipFor("#inputPassphrase",Messages.passphraseHint,placement,"focus");
	};
	
	initGenerateKeyAndCertificateBtn = function(){
		$('#genKeyAndCertificate').on('click',function(){
			Utils.clearErrorMessagesFrom('#newKeypairAndCertificateErrors');
			var passphrase = $('#inputPassphrase').val();
			var isPassphraseValid = Utils.checkInputField(
										"#inputPassphrase",
										"#newKeypairAndCertificateErrors",
										Validation._isPassphrase,
										Messages.emptyPassphrase,
										Messages.wrongPassphrase
									);
			if(isPassphraseValid){		
				var repsonse = Server.
							generatePublicKeyAndCertificate(
								passphrase,"#genKeyAndCertificateSign");
				
				var keyAndCertificate = repsonse[0];
				console.log(keyAndCertificate);
				
				var errorMessage = repsonse[1];				
				console.log(errorMessage);
				if(!errorMessage){			
					addKeyAndCertificateTextarea(keyAndCertificate);
					afterSuccessGeneration();
				}else{
					Utils.addErrorMessageTo('#newKeypairAndCertificateErrors');
				}
			}
		});
	};
	
	afterSuccessGeneration = function(){
			//console.log('after generation');
			require('publicKeys').updateExistingPublicKeyForm();
			initPublicKeySelect();
	};
	
	addKeyAndCertificateTextarea = function(keyAndCertificate){
		var beginCertIndex =  keyAndCertificate.indexOf("-----BEGIN CERTIFICATE-----");
		var privateKey = keyAndCertificate.substr(0,beginCertIndex);
		var certificate = keyAndCertificate.substr(beginCertIndex+1);

		$('#generatedKeyAndCertificate').children().remove();
		
		var privateKeyContainer = 
			createTextAreaContainer('privateKeyContainer',
								'New generated Private Key',
								privateKey,
								'private_key.key'
								);
		var certificateContainer = 
				createTextAreaContainer('privateCertificateContainer',
										'Generated certificate',
										certificate,
										'private_certificate.crt')
			
		$('#generatedKeyAndCertificate')
			.append(certificateContainer)
			.prepend(privateKeyContainer);
	};
	
	createTextAreaContainer = function(containerID, headerText, content, filename){
		var container = $('<div>').attr('id',containerID).addClass('row-fluid');
		var textarea = $(
			'<textarea id="generatedPrivateKey" rows=20 style="resize:none" disabled></textarea>'
			).addClass("span10").val(content);
		var header = $('<h5>').text(headerText);
		container
			.append(header)
			.append(textarea).append('<br/>')
			.append(createDownloadCertificateBtn(filename,content)); 
			
		return container;
	};
	
	addCertificateTextarea = function(pubKeyString){
		$('#generatedCertificate').children().remove();
		var textarea = $('<textarea rows=20 style="resize:none" disabled></textarea>').addClass("span10");
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

	initPublicKeySelect = function(updateUser){
		var selectPubKey = $('#selectKeyForGeneration');
		selectPubKey.children().remove();
		var publicKeys = getUserPublicKeysFromServer();
		var label;
		if(publicKeys.length == 0){ // if no keys	
			Utils.hideElement("#pubicKeySetLabel");
			selectPubKey.append('<span class="alert alert-info span8">'+Messages.noPublicKeys+"</span>");
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
			selectPubKey.append(label);
			selectPubKey.append(selDiv);
		}			
	};
	
	getUserPublicKeysFromServer = function(){
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
