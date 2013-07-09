define(['require','utils',],
/**
 * @lends MainPage
 */ 
function(require,Utils,LoginPage){
	
	//console.log("mainPage.js is loaded");
	
	Main = {};
	
	initMainPage = function(){


		performScreenAdjustments();
		Utils.unhideBody();
		Utils.showCurrentTab();
		//initAsideSection();
		initUserInfoPanel();
		initManageUserProfileForm();
		initAddRemoveKeysForm();
		
		initOnWindowResizeEvents();
	};
	
	performScreenAdjustments = function(){
		initCollapseHeaders();
		
		if(Utils.isSmallScreen()){
			initForSmallScreens();
		}else{
			initForLargeScreens();
		}
		initCollapseSigns();
	};
	
	initOnWindowResizeEvents = function(){
		$(window).resize(function(){
				performScreenAdjustments();
		});			
	};
	
	collapseAsideSections = function(bool){
		var sliceList = $("#yourSliceList");
		var availableSlices  = $('#availableSlicesList');
		if(bool){
			sliceList.removeClass('in');
			availableSlices.removeClass('in');
		}else{
			if(!sliceList.hasClass('in')){
					sliceList.addClass('in');
			}
			if(!availableSlices.hasClass('in')){
					availableSlices.addClass('in');
			}	
		}
	};
	
	initCollapseHeaders = function(){
		$('.collapseHeader').on('click',function(){
			var icon = $(this).find('.collapseSign');
			window.setTimeout(function(){
				initCollapseSignFor(icon);
			},100);
		});
	};
	
	initCollapseSigns = function(){
		var icons = $('.collapseSign');
		//console.log("INIT COLLAPSE _______________");
		icons.each(function(){
			var t = $(this);
			initCollapseSignFor(t);
		});		
	};
	
	initCollapseSignFor = function(obj){
		var selector = obj.closest('div').attr('data-target');
		isOpen  = $(selector).hasClass('in');
			//console.log("selector" + selector + " is open " + isOpen);
			if(isOpen){
					obj.attr('class','');
					obj.addClass('collapseSign icon-chevron-down');
			}else{
					obj.attr('class','');
					obj.addClass('collapseSign icon-chevron-right');
		}
	};
	
	initForSmallScreens = function(){
		collapseAsideSections(true);	
		Utils.unhideElement('#toolbar .btn-navbar');		
	};
	
	initForLargeScreens = function(){
		collapseAsideSections(false);	
		Utils.hideElement('#toolbar .btn-navbar');
	};

	initAsideSection = function(){
		var height = $(window).height();
		var asideHeight = 123;
		$('#aside');
	};



	initUserInfoPanel = function(){
		console.log("init User Info Panel");
		var user = Utils.getCurrentUser();
		console.log('current user '+ Utils.userToString());
		$("#userName").text(user.firstName +" " + user.lastName);
			
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
	
		$("#inputUsername").val(user.username);
		$("#inputFirstName").val(user.firstName);
		$("#inputLastName").val(user.lastName);
		$("#inputAffiliation").val(user.affiliation);
		$("#inputEmail").val(user.email);
		$("#inputUsername").val(user.username);
		
		initSaveProfileInfoBtn();
	};
	
	enableSaveProfileBtn = function(){
		console.log("enabling");
		$('#saveProfileInfo').removeClass('disabled');
	};
	
	initSaveProfileInfoBtn = function(){
		$("#saveProfileInfo").on('click',function(){
			if(checkUserProfileEntries()){
				var msg = Utils.updateUserOnServer(getUserFromProfile(),"#saveProfileLoadingSign");
				$('#userProfileErrors').append(msg);
				initUserInfoPanel();
			}
		});
	};
	
	checkUserProfileEntries = function(){
		
		Utils.clearErrorMessagesFrom("#userProfileErrors");
		
		var fn  = Utils.checkInputField(
								"#inputFirstName",
								"#userProfileErrors",
								Validation._isName,
								Messages.emptyUsername,
								Messages.wrongUsername
								);
		var ln = Utils.checkInputField(
								"#inputLastName",
								"#userProfileErrors",
								Validation._isName,
								Messages.emptyLastName,
								Messages.wrongLastName);
								
		var aff =  Utils.checkInputField(
									"#inputAffiliation",
									"#userProfileErrors",
									Validation._isAffiliation,
									Messages.emptyAffiliation,
									Messages.wrongAffiliation
									);
		var email = Utils.checkInputField(
								"#inputEmail",
								"#userProfileErrors",
								Validation._isEmail,
								Messages.emptyEmailAddress,
								Messages.wrongEmailAddress);
								
		return (fn && ln && aff && email);
	};
	
	getUserFromProfile = function(){
		user = new Object();
		
		user.username = $('#inputUsername').val();
		user.firstName = $('#inputFirstName').val();
		user.lastName = $('#inputLastName').val();
		user.affiliation = $('#inputAffiliation').val();
		user.email = $('#inputEmail').val();
		
		return user;
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
			beforeSend: function(xhr){
				xhr.setRequestHeader("Authorization",
                "Basic " + Utils.getCredentials()); // TODO Base64 support
			},
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
		var div = $("<div>").addClass('row-fluid');
		var label = $('<label class="span2"></label>').html(labelValue);
	
		var textArea =$('<textarea style="resize:none" rows="3" class="span8" disabled ></textarea>')
						.val(textareaValue);
		
		var keyContols = $('<div>')
						.attr('id',"publicKeyControls")
						.addClass('span2 pull-right');
		
		var downloadBtn = $('<button>')
							.addClass('btn btn-success span5 ')
							.html('<i class="icon-download nomargin"></i>');
							
		downloadBtn.tooltip({'title': "Download",'placement':"top"});
		
		var deleteKey = $('<button>')
							.attr('data-number',itemNumber)
							.addClass('btn btn-danger span5 offset2 ')
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
		
		div.append(label);
		div.append(textArea);
		div.append(keyContols);
		
		return div;
		
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
	  var content = $('<textarea class="span8" rows=3 disabled style="resize:none"></textarea>');
	  content.html(text);
	  container.find('textarea').remove();
	  container.append(content);
	};

	initSignOutBtn = function(){
		$("#signOut").on('click',function(e){
			e.preventDefault();
			console.log("signOut clicked");
			Utils.resetUser();
			signOut();
		});
	};
	
	signOut = function(){
		sessionStorage.clear();
		require('loginPage').load();
	};
		
	Main.load = function(){
			console.log("loading main Page...");
			var url = "html/main.html";
			$("#navigation").load(url + " #toolbar",
				function(){
					$("#main").load(url + " #mainArea",
						function(){								
							initMainPage(); 
						});
				}
			);
	};
	
	return Main;
	
});



