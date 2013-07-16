define(['require','utils','server'],
/**
 * @lends MainPage
 */ 
function(require,Utils,Server){
	
	Profile = {};
	
	Profile.initForm = function(){
		Utils.clearErrorMessagesFrom("#manageProfile .errorMessages");
		setProfileFields(Utils.getCurrentUser());		
		initSaveProfileInfoBtn();
		initDeleteUserProfileBtn();
		defineOnEnterClicks();
	};
	
	defineOnEnterClicks = function(){
		Utils.changeFocusOnEnterClick("#inputFirstName","#inputLastName");
		Utils.changeFocusOnEnterClick("#inputLastName","#inputAffiliation");
		Utils.changeFocusOnEnterClick("#inputAffiliation","#inputEmail");
		Utils.addOnEnterClickEvent("#inputEmail","#saveProfileInfo");
	};
	
	setProfileFields = function(user){
		$("#inputUsername").val(user.username);
		$("#inputFirstName").val(user.firstName);
		$("#inputLastName").val(user.lastName);
		$("#inputAffiliation").val(user.affiliation);
		$("#inputEmail").val(user.email);
		$("#inputUsername").val(user.username);
	};
	
	
	initSaveProfileInfoBtn = function(){
		$("#saveProfileInfo").on('click',function(){
			Utils.unhideElement('#saveProfileLoadingSign');
			if(checkUserProfileEntries()){	
				var msg = Server.updateUser(getUserFromProfileForm(),"#saveProfileLoadingSign");
				setProfileFields(Utils.getCurrentUser());
				Utils.clearErrorMessagesFrom('#userProfileErrors');
				$('#userProfileErrors').append(msg);
				Utils.updateInfoPanel();
			}
			
			window.setTimeout(function(){
				Utils.hideElement('#saveProfileLoadingSign');
			},200);
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
	
	
		
	getUserFromProfileForm = function(){
		user = new Object();		
		user.username = $('#inputUsername').val();
		user.firstName = $('#inputFirstName').val();
		user.lastName = $('#inputLastName').val();
		user.affiliation = $('#inputAffiliation').val();
		user.email = $('#inputEmail').val();	
		return user;
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
