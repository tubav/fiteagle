define(['require','utils',],
/**
 * @lends MainPage
 */ 
function(require,Utils,LoginPage){
	
	console.log("mainPage.js is loaded");
	
	Main = {};
	
	initMainPage = function(){
		Utils.unhideBody();

		Utils.showCurrentTab();
		//initAsideSection();
		initUserInfoPanel();
		initManageUserProfileForm();
		initAddRemoveKeysForm();
	};

	initAsideSection = function(){
		var height = $(window).height();
		var asideHeight = 123;
		$('#aside');
	};

	signOut = function(){
		sessionStorage.clear();
		require('loginPage').load();
	};

	initUserInfoPanel = function(){
		console.log("init User Info Panel");
		var user = Utils.getCurrentUser();
		console.log('current user '+ Utils.userToString());
		$("#userName").text(user.email);
			
		// workaroud for BOOTSTRAP's DropDown bug ("active" class for li elements removed)
		$("#userInfoDropdown a").click(function(){
			
			var linkID  = $(this).attr("id");
			Utils.setCurrentTab("#"+linkID);
			var lis = $("#userInfoDropdown li");
			lis.removeClass("active");
		});
		
		initSignOutBtn();
	};


	initManageUserProfileForm = function(){
		Utils.clearErrorMessagesFrom("#manageProfile .errorMessages");
		
		var user = Utils.getCurrentUser();
		
		console.log(user);
		$("#inputUsername").val(user.username);
		$("#inputFirstName").val(user.firstName);
		$("#inputLastName").val(user.lastName);
		$("#inputAffiliation").val(user.affiliation);
		$("#inputEmail").val(user.email);
		$("#inputUsername").val(user.username);
	};
	
	initAddRemoveKeysForm = function(){
		initExistingPublicKeysForm();
		initNewUserKeysForm();
	};
	
	initNewUserKeysForm = function(){
		initFileSelectBtn();
		
		$('#uploadNewPublicKey').on('click',function(){
			var publicKey = $('#publicKeyFromFile textarea').val();
			uploadNewPublicKey(publicKey);
		});
	};
	
	uploadNewPublicKey = function(publicKey){
		
		var user = Utils.getCurrentUser();
		var username = user.username;
		
		$.ajax({
			cache: false,
			type: "POST",
			async: false,
			url: "/api/v1/user/"+username+'/pubkey',
			data: publicKey,
			contentType: "text/plain",
			dataType: "json",
			success: function(data,status){
				console.log(data);
				console.log(status);
			},
			error: function(xhl,status){
				console.log(xhl.responseText);
				console.log(status);
			},
			statusCode:{			
				200: function(){
					console.log("New public key has been successfully uploaded");
					var updatedUser = Utils.getUserFromServer(username);
					Utils.setCurrentUser(updatedUser);
					initAddRemoveKeysForm();
				}
			}
		});
		
	};
	
	initFileSelectBtn = function(){
		$('#selectFromFile').on('click',function(){
			$('#selectFromFileInput').click();
		});
		$('#selectFromFileInput').on('change',function(event){
				handleFileSelect(event);
		});
	};
	
	initExistingPublicKeysForm = function(){
		$('#publicKeysList li').remove();
		var publicKeys = Utils.getCurrentUser().publicKeys;	
		for(var i = 0; i < publicKeys.length; i++ ){
			var newItem  = createNewPublicKeysListItem('Public Key ['+(i+1)+']',publicKeys[i], i);
			$("#publicKeysList").append(newItem);
		}
	};
	
	createNewPublicKeysListItem = function(labelValue,textareaValue, itemNumber){
		var li = $("<li>");
		var label = $('<label class="span2"></label>').html(labelValue);
	
		var textArea =$('<textarea style="resize:none" rows="3" class="span8" disabled ></textarea>')
						.val(textareaValue);
		
		var keyContols = $('<div>')
						.attr('id',"publicKeyControls")
						.addClass('span2 pull-right');
		
		var downloadBtn = $('<button>')
							.addClass('btn btn-success span3 ')
							.html('<i class="icon-download nomargin"></i>');
							
		downloadBtn.tooltip({'title': "Download",'placement':"top"});
		
		var deleteKey = $('<button>')
							.attr('data-number',itemNumber)
							.addClass('btn btn-danger span3 offset2 ')
							.html('<i class="icon-remove nomargin"></i>');
							
		deleteKey.tooltip({'title':"Remove", 'placement':'top'});
							
		deleteKey.on('click',function(event){
			var user = Utils.getCurrentUser();
			var keyNumber = $(this).attr('data-number');
			
			if(user.publicKeys.length == 1){ 
				user.publicKeys=[];
			}else{
				user.publicKeys.splice(keyNumber,1);
			}
			Utils.updateUserOnServer(user);
		});					
		
		keyContols.append(downloadBtn);
		keyContols.append(deleteKey);
		
		li.append(label);
		li.append(textArea);
		li.append(keyContols);
		
		return li;
		
	};
	
	handleFileSelect = function(evt){
	
		console.log("File handling");
		
		var files = evt.target.files; // FileList object
		
		// files is a FileList of File objects. List some properties.
		var output = [];
		for (var i = 0, f; f = files[i]; i++) {
			output.push('<strong>', escape(f.name), '</strong> (', f.type || 'n/a', ') - ',
					  f.size, ' bytes, last modified: ',
					  f.lastModifiedDate ? f.lastModifiedDate.toLocaleDateString() : 'n/a');
			
			var reader = new FileReader();
			reader.readAsText(f);
			reader.onload = function(e){
				contents = e.target.result;
				setPublicKeyFromFile(contents);
			};
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
	  var content = $('<textarea class="span8 offset2" rows=3 disabled style="resize:none"></div>');
	  content.html(text);
	  container.find('textarea').remove();
	  container.append(content);
	};

	initSignOutBtn = function(){
		$("#signOut").click(function(){
			console.log("signOut clicked");
			Utils.resetUser();
			signOut();
		});
	};
		
	Main.load = function(){
			console.log("loading main Page...");
			//Login.showLoadingSign();
			var url = "html/main.html";
			$("#navigation").load(url + " #toolbar",
				function(){
					//Login.hideLoadingSign();
					$("#main").load(url + " #mainArea",
						function(){								
							initMainPage(); 
						});
				}
			);
	};
	
	return Main;
	
});



