define(['require','utils','server','validation','messages'],
/**
 * @lends MainPage
 */ 
function(require,Utils,Server,Validation,Messages){
	
	PublicKeys = {};
	
	PublicKeys.initForm = function(){
		initExistingPublicKeysForm();
		initNewUserKeysForm();
	};
	
	initNewUserKeysForm = function(){
		initKeyDescriptionField();
		
		if(isFileSelectAvailable()){
			initFileSelectBtn();
		}else{
			disableUploadNewUserKeysForm();
		}
		
		$('#uploadNewPublicKey').on('click',function(){
			if(checkKeyDescription()){
				var description = $("#inputKeyDescription").val();
				var publicKeyString = $('#publicKeyFromFile textarea').val();
				publicKey = createNewPublicKeyObject(description,publicKeyString);
				var msg = Server.uploadNewPublicKey(publicKey, "#uploadingSing");
				clearPublicKeysErrors();
				Utils.addErrorMessageTo('#newUserKeyErrors',msg);
				PublicKeys.initForm();
				require('certificates').initForm();
			}

		});
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
		publicKey.description = description;
		publicKey.publicKeyString = keyString;
		return publicKey;
	};
	
	
	initFileSelectBtn = function(){
		$('#selectFromFile').on('click',function(){
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
			var msg = Server.deletePublicKey(publicKey.description);
			clearPublicKeysErrors();
			$('#existingUserKeyErrors').append(msg);
			PublicKeys.initForm();
			require('certificates').initForm();
		});					
		
		keyContols.append(downloadBtn);
		keyContols.append(deleteKey);
		
		div.append(label);
		div.append(d);
		div.append(keyContols);
		
		return div;
		
	};
	
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
	
		//console.log("File handling");
		
		var f = evt.target.files[0]; // FileList object
		
		// files is a FileList of File objects. List some properties.
		var output = [];
		
		var fileExt = f.name.split('.').pop();
		
		if (!f) {
			Utils.setErrorMessageTo("#newUserKeyErrors",Messages.failToLoadFile);
		}else {
			
			checkFileExtension()
				
			var reader = new FileReader();
			reader.readAsText(f);
			reader.onload = function(e){
				contents = e.target.result;
				setPublicKeyFromFile(contents);
			};
			output.push('<strong>', escape(f.name), '</strong> (', f.type || 'n/a', ') - ',
				  f.size, ' bytes, last modified: ',
				  f.lastModifiedDate ? f.lastModifiedDate.toLocaleDateString() : 'n/a');
		}
		
		if(output.length > 0){
			$('#currentFileInfo').html(
				'<span class="alert alert-info centered span12">Currently selected: '+ output.join('')+'</span>'
			);
			$('#uploadNewPublicKey').removeClass('hidden');
		}else{
			$('#currentFileName').html('No file selected.');
			$('#uploadNewPublicKey').addClass('hidden');
		}
	};
	
	checkFileExtension = function(){
		console.log("TODO CHECK FILE EXTENSION");
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
