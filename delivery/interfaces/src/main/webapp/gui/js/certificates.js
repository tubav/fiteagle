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
	
	
	/**
	* Triggers functions for the certificate management form initialization. It initializes public key select element that contains all the public keys 
	* from the current user profile. It defines the behaviour for the certificate generation from the existing public key as well as generation of the certificate and 
    * the corresponding certificate.	
	* @public
	* @name Certificates#initForm
	* @function
	**/
	Certificates.initForm = function(){
		Certificates.initPublicKeySelect();	
		initGenerateCertificatesBtn();
		initPassphraseField();
		initGenerateKeyAndCertificateBtn();
		$(window).bind('resizeEnd',function(){
			initPassphraseField();
		});
	};
	
	/**
	* Initializes the behaviour after clicking on the button for the generation of the certificate from the currently selected public key.
    * @private
	* @memberOf Certificates#
	*/
	initGenerateCertificatesBtn = function(){
		$("#genPubKey").on('click',function(){
			var selectedKeyDescription = $("#publicKeySetSelect option:selected").html();
			//console.log("KEY DESCRIPTION: " + selectedKeyDescription);
			var pubString = Server.generateCertificateForPiblicKey(selectedKeyDescription);
			if(pubString){
					addCertificateTextarea(pubString);
			}
		}).tooltip({title: Messages.generateCertificateFromKey});
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
		}).tooltip({title: Messages.generateKeyPairAndCertificate});
	};
	
	afterSuccessGeneration = function(){
			//console.log('after generation');
			require('publicKeys').updateExistingPublicKeyForm();
			Certificates.initPublicKeySelect();
	};
	
	addKeyAndCertificateTextarea = function(keyAndCertificate){
		var beginCertIndex =  keyAndCertificate.indexOf("-----BEGIN CERTIFICATE-----");
		var privateKey = keyAndCertificate.substr(0,beginCertIndex);
		var certificate = keyAndCertificate.substr(beginCertIndex+1);

		$('#generatedKeyAndCertificate').children().remove();
		
		var privateKeyContainer = 
			createTextAreaContainer('privateKeyAndCertificateContainer',
								'New generated Private Key and Cerificate',
								keyAndCertificate
								);
			
		$('#generatedKeyAndCertificate')
			.append(privateKeyContainer)
			.append(
				$('<div class="row-fluid certificateControls"></div>')
					.append(createDownloadCertificateBtn('certificate.crt', certificate))
					.append(createDownloadPrivateKeyBtn('privateKey.ppk',privateKey))
				);
	};
	
	createTextAreaContainer = function(containerID, headerText, content){
		var container = $('<div>').attr('id',containerID).addClass('row-fluid');
		var textarea = $(
			'<textarea id="generatedPrivateKey" rows=20 style="resize:none" disabled></textarea>'
			).addClass("span10").val(content);
		var header = $('<h5>').text(headerText);
		container
			.append(header)
			.append(textarea).append('<br/>');
			
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
				
		//console.log(hattr);
		
		var downloadBtn = $('<a>')
				.addClass('btn btn-inverse span2 nomargin')
				.html('<i class="icon-file-text-alt"></i>Download Certificate')
				.on('click',function(){
					var blob = new Blob([text], {type: "text/plain;charset=utf-8"});
					saveAs(blob, filename);
				});
		
		return downloadBtn;
	}
	
	createDownloadPrivateKeyBtn = function(filename,text){
				
		//console.log(hattr);
		
		var downloadBtn = $('<a>')
				.addClass('btn btn-inverse span2 offset1 ')
				.html('<i class="icon-file"></i>Download Private Key')
				.on('click',function(){
					var blob = new Blob([text], {type: "text/plain;charset=utf-8"});
					saveAs(blob, filename);
				});
		
		return downloadBtn;
	}

	Certificates.initPublicKeySelect = function(updateUser){
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
