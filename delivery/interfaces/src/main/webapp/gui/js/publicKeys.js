define(['require','utils',],
/**
 * @lends MainPage
 */ 
function(require,Utils,LoginPage){
	
	PublicKeys = {};
	
	PublicKeys.initForm = function(){
		initExistingPublicKeysForm();
		initNewUserKeysForm();
	};
	
	initNewUserKeysForm = function(){
		initFileSelectBtn();
		
		$('#uploadNewPublicKey').on('click',function(){
			if(checkKeyDescription()){
				var description = $("#inputKeyDescription").val();
				var publicKeyString = $('#publicKeyFromFile textarea').val();
				publicKey = createNewPublicKeyObject(description,publicKeyString);
				var msg = Utils.uploadNewPublicKey(publicKey, "#uploadingSing");
				clearPublicKeysErrors();
				$('#newUserKeyErrors').append(msg);
				initExistingPublicKeysForm();
			}

		});
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
		console.log("init existing key form");
		emptyPublicKeyList();
		var publicKeys = Utils.getCurrentUser().publicKeys;
		if(publicKeys.length > 0){	
			for(var i = 0; i < publicKeys.length; i++ ){
				var newItem  = createNewPublicKeysListItem(publicKeys[i], i);
				$("#publicKeysList").append(newItem);
			}
		}else{
			var p = $('<label>').addClass('.noPublicKey').html(Messages.noPublicKeys);
			$("#publicKeysList").append(p);
		}
	};
	
	emptyPublicKeyList = function(){
		$('#publicKeysList').children().remove();
	};
	
	createNewPublicKeysListItem = function(publicKey, itemNumber){
		var keyDescription = publicKey.description;
		var keyString = publicKey.publicKeyString;
		var div = $("<div>").addClass('row-fluid publicKey');
		var label = $('<label class="span2"></label>').html('Public key: ' + keyDescription);
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
							.addClass('btn btn-danger span5 offset2 ')
							.html('<i class="icon-remove nomargin"></i>');
							
		deleteKey.tooltip({'title':"Remove", 'placement':'top'});
							
		deleteKey.on('click',function(event){
			var msg = Utils.deletePublicKey(publicKey.description);
			clearPublicKeysErrors();
			$('#existingUserKeyErrors').append(msg);
			initExistingPublicKeysForm();
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
							.addClass('btn btn-success span5 ')
							.html('<i class="icon-download nomargin"></i>')
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
	
		console.log("File handling");
		
		var f = evt.target.files[0]; // FileList object
		
		// files is a FileList of File objects. List some properties.
		var output = [];
		
		if (!f) {
			alert("Failed to load file");
		}else {	
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
			$('#currentFileName').html('Currently selected: '+ output.join(''));
			$('#uploadNewPublicKey').removeClass('hidden');
		}else{
			$('#currentFileName').html('No file selected.');
			$('#uploadNewPublicKey').addClass('hidden');
		}
	};
  
  
  	setPublicKeyFromFile = function(text){
	  var container = $("#publicKeyFromFile");
	  var content = $('<textarea class="span12" rows=3 disabled style="resize:none"></textarea>');
	  content.html(text);
	  container.find('textarea').remove();
	  container.append(content);
	};
	
	return PublicKeys;

});
