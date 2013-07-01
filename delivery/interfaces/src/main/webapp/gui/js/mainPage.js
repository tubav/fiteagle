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
		initUserInfoPanel();
		initManageUserProfileForm();
		initAddRemoveKeysForm();
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
		var user = Utils.getCurrentUser();
		var publicKeys = user.publicKeys;
		var publicList = $("#publicKeysList");
		
		/*
		 
			<li>
				<label class="span2" for="publicKeyArea">Public Key</label>
				<textarea id="publicKeyArea" row="1" class="span8" placeholder="Public Key" disabled ></textarea>
				<button class="btn btn-inverse downloadCertificat pull-right">
					<i class="icon-download">Download</i></button>
			</li>
		
		*/
		
		for(var i = 0; i < publicKeys.length; i++ ){
			var newItem  = createNewPublicKeysListItem('Public Key ['+(i+1)+']',publicKeys[i]);
			publicList.append(newItem);
		}
		
		$('#selectFromFile').on('click',function(){
			$('#selectFromFileInput').click();
		});
		
		$('#selectFromFileInput').on('change',function(event){
				handleFileSelect(event);
		});
	};
	
	createNewPublicKeysListItem = function(labelValue,textareaValue){
		var li = $("<li>");
		var label = $('<label class="span2"></label>').html(labelValue);
	
		var textArea =$('<textarea rows="2" class="span8" disabled ></textarea>')
						.val(textareaValue);
		
		var downloadBtn = $('<button>')
							.addClass('btn btn-inverse span2 pull-right')
							.html('<i class="icon-download"> Download</i>');
		li.append(label);
		li.append(textArea);
		li.append(downloadBtn);
		
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
    }
    
    if(output.length > 0){
		$('#currentFileName').html('Currently selected: '+ output.join(''));
		$('#uploadNewPublicKey').removeClass('hidden');
	}else{
		$('#currentFileName').html('No file is selected.');
		$('#uploadNewPublicKey').addClass('hidden');
	}
  }

  // document.getElementById('files').addEventListener('change', handleFileSelect, false);

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



