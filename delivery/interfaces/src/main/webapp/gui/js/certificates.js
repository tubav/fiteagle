define(['validation','utils','mainPage','messages'],
/**
 * @lends Registration
 */ 
function(Validation, Utils, MainPage,Messages){

	Certificates = {}
	
	Certificates.initForm = function(){
		
			initPublicKeySelect("#pubicKeySet");	
			initGenerateCertificatesBtn();			
			
	};
	
	initGenerateCertificatesBtn = function(){
		$("#genPubKey").on('click',function(){
			var selectedKeyDescription = $("#publicKeySetSelect option:selected").html();
			console.log("KEY DESCRIPTION: " + selectedKeyDescription);
			var pubString = Certificates.getPublicKeyByDescription(selectedKeyDescription);
			if(pubString){
					addCertificateTextarea(pubString);
			}
		});
	};
	
	addCertificateTextarea = function(pubKeyString){
		$('#generatedCertificate').children().remove();
		var area = $('<textarea rows=24 style="resize:none" disabled></textarea>').addClass("span10");		
		area.val(pubKeyString);
		
		$('#generatedCertificate')
			.append(area)
			.append('<br/>')
			.append(createDownloadCertificateBtn(pubKeyString));
		
	};
	
	createDownloadCertificateBtn = function(text){
		
		var appendix = btoa(text);
		
		var hattr = "data:application/octet-stream;charset=utf-8;base64," + appendix;
		
		console.log(hattr);
		
		var downloadBtn = $('<a>')
				.addClass('btn btn-inverse')
				.html('<i class="icon-download-alt"></i>Download')
				.attr('download','certificate.crt')
				.attr('href',hattr);
		
		return downloadBtn;
	}

	initPublicKeySelect = function(selector){
		console.log("Init public Key Select");
		var publicKeys = getAllUserPublicKeys();
		if(publicKeys.length == 0){ // if no keys
			Utils.hideElement("#pubicKeySetLabel");
			$(selector).append('<p>'+Messages.noPublicKeys+"</p>");
			$("#genPubKey").addClass('disabled');
		}else{ // if keys exists
			$("#genPubKey").removeClass('disabled');
			var sel = $('<select>').attr("id","publicKeySetSelect");
			//sel.append('<option>Select public key</option>');
			for(var i = 0; i < publicKeys.length; i ++){
				var description = publicKeys[i].description;
				sel.append(createOptionItem(description));
			}
			$(selector).append(sel);
		}			
	};
	
	Certificates.getPublicKeyByDescription = function(description){

		var username = Utils.getCurrentUser().username;
		var certificat;
		$.ajax({
			cache: false,
			type: "GET",
			async: false,
			url : "/api/v1/user/"+username+"/pubkey/"+description+"/certificate",
			beforeSend: function(xhr){
				
				xhr.setRequestHeader("Authorization",
                "Basic " + Utils.getCredentials()); // TODO Base64 support
			},
			success: function(cert,status,xhr){
				certificat = cert;
				console.log(":SUCCESS");
			},
			error: function(xhr,status,thrown){
				console.log("Response " + xhr.responseText);
				console.log(status);
				console.log(thrown);
			},
			complete: function(){
				console.log("ENDE");
			}
		});

		return certificat;
	};
	
	getAllUserPublicKeys = function(){
		var user = Utils.getCurrentUser();	
		return user.publicKeys;
	}
	
	createOptionItem = function(keyDescription){	
		console.log("Creating option");
		var opt  = $('<option>').html(keyDescription);
		return opt;
	};
		
	return Certificates;
});
