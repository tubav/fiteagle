define(['require','utils','server'], 
function(require,Utils,Server){
	
	/** 
     * Profile class.
     * The Profile class contains functions for user profile form initialisation and management
     * @class
     * @constructor
     * @return Profile object
     */
	Profile = {};
	
	Profile.initForm = function(){
		initProfileFields();		
		initSaveProfileInfoBtn();
		initDeleteUserProfileBtn();

	};
	
	initProfileFields = function(){
		Utils.clearErrorMessagesFrom("#manageProfile .errorMessages");
		setProfileFields(Utils.getCurrentUser());
		defineOnEnterClicks();
		defineOnFieldValueChange();
	};
	
	defineOnFieldValueChange = function(){
		var inputs = $('#manageProfileContainer').find('input');
		inputs.each(function(){
			$(this).on('keyup',function(){
				checkIfChanged($(this));
				toggleSaveBtn();
			});
		});
	};
	
	toggleSaveBtn = function(){
		var saveBtn = $('#saveProfileInfo');
		if(isAnyFieldChanges()){
			saveBtn.removeClass('disabled');			
		}else{
			(!saveBtn.hasClass('disabled')) ? saveBtn.addClass('disabled') : "";	
		}
	};
		
	checkIfChanged = function(object){
		var t = object;
		var currentValue = t.val();
		var defaultValue = t.attr('data-default');		
		if(currentValue != defaultValue){ 
			if(!t.hasClass('changed'))t.addClass('changed');	
		}else{
			t.removeClass('changed');
			
		}
	};
	
	isAnyFieldChanges =function(){
		var result = false;
		var changes = $('#manageProfileContainer').find('input').filter('.changed');
		if(changes.length != 0) result = true;
		return result;
	};
	
	defineOnEnterClicks = function(){
		Utils.changeFocusOnEnterClick("#inputFirstName","#inputLastName");
		Utils.changeFocusOnEnterClick("#inputLastName","#inputAffiliation");
		Utils.changeFocusOnEnterClick("#inputAffiliation","#inputEmail");
		Utils.addOnEnterClickEvent("#inputEmail","#saveProfileInfo");
	};
	
	setProfileFields = function(user){
		$("#inputUsername")
			.val(user.username)
			.attr('data-default',user.username);
		$("#inputFirstName")
			.val(user.firstName)
			.attr('data-default',user.firstName);
		$("#inputLastName")
			.val(user.lastName)
			.attr('data-default',user.lastName);
		$("#inputAffiliation")
			.val(user.affiliation)
			.attr('data-default',user.affiliation);
		$("#inputEmail")
			.val(user.email)
			.attr('data-default',user.email);
	};
	
	
	initSaveProfileInfoBtn = function(){
		var saveBtn = $("#saveProfileInfo");
		saveBtn.on('click',function(){		
			if(checkUserProfileEntries() && !saveBtn.hasClass('disabled')){
				Utils.unhideElement('#saveProfileLoadingSign');
				console.log(JSON.stringify(getUserProfileChanges()));
				var msg = Server.updateUser(getUserProfileChanges(),"#saveProfileLoadingSign");
				afterProfileUpdate(msg);
			}
		});
	};
	
	afterProfileUpdate = function(msg){
		setProfileFields(Utils.getCurrentUser());
		isSuccessMsg = msg.find('span').hasClass('alert-success');
		if(isSuccessMsg){
			Utils.showSuccessModal(msg);
		}else{
			Utils.clearErrorMessagesFrom('#userProfileErrors');
			$('#userProfileErrors').append(msg);
		}
		Utils.updateInfoPanel();
		Utils.hideElement('#saveProfileLoadingSign');
		
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
	
	
		
	getUserProfileChanges = function(){
		profileChanges = new Object();		
		($('#inputUsername').hasClass('changed'))    ?   profileChanges.username = $('#inputUsername').val()   : "";
		($('#inputFirstName').hasClass('changed'))   ?   profileChanges.firstName = $('#inputFirstName').val() : "";
		($('#inputLastName').hasClass('changed'))    ?   profileChanges.lastName = $('#inputLastName').val()   : "";
		($('#inputAffiliation').hasClass('changed')) ?   profileChanges.affiliation = $('#inputAffiliation').val() : "";
		($('#inputEmail').hasClass('changed'))       ?   profileChanges.email = $('#inputEmail').val() : "";	
		return profileChanges;
	};
	
	enableSaveProfileBtn = function(){
		console.log("enabling");
		$('#saveProfileInfo').removeClass('disabled');
	};
	
	initDeleteUserProfileBtn = function(){
		$('#deleteUserProfileBtn').on('click',function(){
			Server.deleteUser(afterDeleteFunction);	
		});
	};
	
	afterDeleteFunction = function(){
		//console.log("Afte DELETE function");
		var modal = $('#deleteUserProfileModal');
		var modalHeaderCloseBtn = modal.find('.modal-header .close');
		var modalBody = modal.find('.modal-body');
		var modalFooter = modal.find('.modal-footer');
		modalHeaderCloseBtn.remove();
		modalBody.children().remove();
		modalFooter.children().remove();
		modalBody.append($('<span class="span10 offset1 alert alert-info">Current user has been successfully removed</span>'));
		var okBtn = $('<button id="deleteUserModalOkBtn" class="btn btn-success span2">OK</button>');
		okBtn.on('click',function(){
			$('#deleteUserProfileModal').modal('hide');
			require('mainPage').signOut();
		});
		modalFooter.append(okBtn);
	};
	
	return Profile;

});
