define(['require','utils','server','validation','messages'],
/**
 * @lends MainPage
 */ 
function(require,Utils,Server,Validation,Messages){
	
	/** 
     * PublicKeys class
     * The PublicKey class contains functions required for the user public key management.
     * @class
     * @constructor
     * @return PublicKeys object
     */
	PublicKeys = {};
	
	PublicKeys.initForm = function(){
		initExistingPublicKeysForm();
		initNewUserKeysForm();
	};
	
	initNewUserKeysForm = function(){
		checkFileSelectSupport();
		initKeyDescriptionField();
		initUploadPublicKeyBtn();
	};
	
	initUploadPublicKeyBtn = function(){
		$('#uploadNewPublicKey').on('click',function(){
			clearPublicKeysErrors();
			if(checkKeyDescription()){
				clearPublicKeysErrors();
				var description = $("#inputKeyDescription").val();
				var publicKeyString = $('#publicKeyFromFile textarea').val();
				publicKey = createNewPublicKeyObject(description,publicKeyString);
				var errorMessage = Server.uploadNewPublicKey(publicKey, "#uploadingSing");
				if(errorMessage)
					$('#newUserKeyErrors').append(errorMessage);
				updatePublicKeyRelatedForms();
			}
		});
	};
	
	checkFileSelectSupport = function(){
		if(isFileSelectAvailable()){
			initFileSelectBtn();
		}else{
			disableUploadNewUserKeysForm();
		}
	};
	
	updatePublicKeyRelatedForms = function(){	
		initExistingPublicKeysForm();
		require('certificates').initForm();
		resetSelectedFileInfo();
	};
	
	disableUploadNewUserKeysForm = function(){
		var info = $('<span>')
			.addClass("alert alert-info span8")
			.append(Messages.fileSelectionNotSupported);
			
		$('#uploadNewUserKeysContainer').children().remove();
		$('#uploadNewUserKeysContainer').append(info);
	};
	
	isFileSelectAvailable = function(){
		var isAvailable;
		(window.File && window.FileReader && 
			window.FileList && window.Blob)?
				isAvailable = true
					:
				isAvailable = false

		return isAvailable;
	};
	
	initKeyDescriptionField = function(){		
		var placement;
								
		(Utils.isSmallScreen())? placement="top": placement = "right";					
								
		Utils.initTooltipFor(
				"#inputKeyDescription",
				Messages.keyDescription,
				placement,
				"focus");
	};

	createNewPublicKeyObject = function(description,keyString){
		var publicKey = new Object();
		publicKey.publicKeyString = keyString;
		publicKey.description = description;
		console.log(JSON.stringify(publicKey));
		return publicKey;
	};
		
	initFileSelectBtn = function(){
		$('#selectFromFile').on('click',function(){
			resetSelectedFileInfo();
			$('#selectFromFileInput').click();
		});
		$('#selectFromFileInput').on('change',function(event){
				clearPublicKeysErrors();
				handleFileSelect(event);
		});
	};
	
	checkKeyDescription = function(){
		var isValidDescription = Utils.checkInputField(
								"#inputKeyDescription",
								"#newUserKeyErrors",
								Validation._isKeyName,
								Messages.emptyKeyDescription,
								Messages.wrongKeyDescription);
								
		return isValidDescription;
	};
	
	initExistingPublicKeysForm = function(){
		user = Utils.getCurrentUser();
		//console.log("init existing key form");
		$('#publicKeysList').children().remove();
		username = user.username;
		var publicKeys = user.publicKeys;
		//console.log(publicKeys);
		if(publicKeys.length > 0){	
			for(var i = 0; i < publicKeys.length; i++ ){
				var newItem  = createNewPublicKeysListItem(publicKeys[i], i);
				$("#publicKeysList").append(newItem);
			}
		}else{
			var info = $('<span>').addClass('alert alert-info .noPublicKey span8').text(Messages.noPublicKeys);
			$("#publicKeysList").append(info);
		}
	};
	
	PublicKeys.updateExistingPublicKeyForm = function(){
		var userFromServer = Server.getUser(Utils.getCurrentUser().username);
		initExistingPublicKeysForm(userFromServer)
	};
	
	createNewPublicKeysListItem = function(publicKey, itemNumber){
		var keyDescription = publicKey.description;
		var keyString = publicKey.publicKeyString;
		var div = $("<div>").addClass('row-fluid publicKey');
		var label = $('<label class="span2"></label>').html('<b>Public key: </b>' + keyDescription);
		var d = $('<div>').addClass('span8');
		var textArea =$('<textarea style="resize:none" class="span12" rows="6"  disabled ></textarea>')
						.val(keyString);
		d.append(textArea);
		var keyContols = $('<div>')
						.attr('id',"publicKeyControls")
						.addClass('span2 pull-right');
						
		var downloadBtn = createPublicKeyDownloadBtn(keyDescription,keyString);
		
		var deleteKey = $('<button>')
							.attr('data-number',itemNumber)
							.addClass('btn btn-inverse span5 offset2 ')
							.html('<i class="icon-remove icon-large nopadding"></i>');
							
		deleteKey.tooltip({'title':"Remove", 'placement':'top'});
							
		deleteKey.on('click',function(event){
			onDeleteKeyPressed(keyDescription);
		});					
		
		keyContols.append(downloadBtn);
		keyContols.append(deleteKey);
		
		div.append(label);
		div.append(d);
		div.append(keyContols);
		
		return div;
		
	};
	
	onDeleteKeyPressed = function(keyDescription){
		Utils.createConfirmModal('deleteKeyConfirmation','deleteKeyConfirmedBtn',Messages.confirmKeyDeletionQuestion +"<br/>"+keyDescription);
		Utils.showModal('#deleteKeyConfirmation');
		$('#deleteKeyConfirmedBtn').on('click',function(){
			var msg = Server.deletePublicKey(keyDescription);
			isSuccessMsg = msg.find('span').hasClass('alert-success');
			clearPublicKeysErrors();
			(isSuccessMsg)? 
						Utils.showSuccessModal(msg)
							:
						$('#existingUserKeyErrors').append(msg);
	
			PublicKeys.initForm();
			require('certificates').initForm();
			Utils.hideModal('#deleteKeyConfirmation');
		});
	};
	
	deleteUserOnServer = function(){
		var msg = Server.deletePublicKey(publicKey.description);
		isSuccessMsg = msg.find('span').hasClass('alert-success');
		clearPublicKeysErrors();
		(isSuccessMsg)? 
					Utils.showSuccessModal(msg)
						:
					$('#existingUserKeyErrors').append(msg);

		PublicKeys.initForm();
		require('certificates').initForm();
	}
	
	createPublicKeyDownloadBtn = function(keyDescription,keyString){
		
		var appendix = btoa(keyString);
		var filename = keyDescription+".pub";
		var hattr = "data:application/octet-stream;charset=utf-8;base64," + appendix;
		
		var downloadBtn = $('<a>')
							.addClass('btn btn-inverse span5 ')
							.html('<i class="icon-download icon-large nopadding"></i>')
							.attr('download',filename)
							.attr('href',hattr);
							
		downloadBtn.tooltip({'title': "Download",'placement':"top"});
		
		return downloadBtn;
	};
	
	clearPublicKeysErrors = function(){
		Utils.clearErrorMessagesFrom("#newUserKeyErrors");
		Utils.clearErrorMessagesFrom("#existingUserKeyErrors");
	};
	
	handleFileSelect = function(evt){
	    evt.stopPropagation();
		evt.preventDefault();
		var f = evt.target.files[0]; // FileList object	
		var output;			
		if (!f) {
			Utils.setErrorMessageTo("#newUserKeyErrors",Messages.failToLoadFile);
		}else {			
			var checked = checkPublicKeyFile(f);
			if(checked)output = readFile(f); // correct file selected
		}
	};
	
	readFile = function(file){
		var reader = new FileReader();
		reader.readAsText(file);
		reader.onload = function(e){
			contents = e.target.result;
			setPublicKeyFromFile(contents);
		};		  
		showSelectedFileInfo(file);		
	};
	
	showSelectedFileInfo = function(file){
		// files is a FileList of File objects. List some properties.
		var output = [];
		output.push('<strong>', escape(file.name), '</strong> (', file.type || 'n/a', ') - ',
			file.size, ' bytes, last modified: ',
			file.lastModifiedDate ? file.lastModifiedDate.toLocaleDateString() : 'n/a');
			
		if(output.length > 0){
			$('#currentFileInfo').html(
				'<span class="alert alert-info centered span12">Currently selected: '+ output.join('')+'</span>'
			);
			$('#uploadNewPublicKey').removeClass('hidden');
		}
	};
	
	resetSelectedFileInfo = function(){
		$('#currentFileInfo').children().remove();
		$('#currentFileInfo').append('<span id="currentFileName" class="alert alert-info span3 centered">No file selected</span>');
		$('#publicKeyFromFile').children().remove();
		$('#currentFileName').html('No file selected.');
		$('#uploadNewPublicKey').addClass('hidden');
	}
	
	checkPublicKeyFile = function(f){
		Utils.clearErrorMessagesFrom('#newUserKeyErrors');
		var isValid = checkPublicKeyFileExtension(f) && checkPublicKeyFileSize(f);
		return isValid;
	};
	
	checkPublicKeyFileExtension = function(file){	
		var fileExt = file.name.split('.').pop();
		var isValid = Validation._isValidPublicKeyFileExtension(fileExt);
		if(!isValid)Utils.addErrorMessageTo('#newUserKeyErrors',Messages.wrongPublicKeyFileExt);
		return isValid;
	};
	
	checkPublicKeyFileSize = function(file){
		var fileSize = file.size;
		var isValid = Validation._isValidPublicKeyFileSize(fileSize);
		if(!isValid)Utils.addErrorMessageTo('#newUserKeyErrors',Messages.wrongPublicKeyFileSize);
		return isValid;
	};
  
  	setPublicKeyFromFile = function(text){
	  var container = $("#publicKeyFromFile");
	  var content = $('<textarea class="span12" rows=6 disabled style="resize:none"></textarea>');
	  content.html(text);
	  container.find('textarea').remove();
	  container.append(content);
	};
	
	return PublicKeys;

});
