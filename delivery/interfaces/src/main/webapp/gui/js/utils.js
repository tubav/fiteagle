define([],
/**
 * @lends Utils
 */ 
function(){ 
	
	/** 
     * Utils class
     * The Utils class contains common used functions in order to ease the programming experience. 
     * @class
     * @constructor
     * @return Utils object
     */
	Utils = {};
	
	/**
	* Adds on "enter" button click event for the given element in order to trigger "on click event" for the other specified element
	* @param {String} selectorOn - selector of an element to apply on "enter" button click event
	* @param {String} triggerOn - selector of an element to trigger "on click" event
	**/
	Utils.addOnEnterClickEvent = function (selectorOn, triggerOn){
		$(selectorOn).keyup(function(event){
			if(event.keyCode == 13){
				$(triggerOn).click();
			}
		});
		
		$(selectorOn).focus();
	};

	/**
	* Changes focus after on "enter" button click event to the other specified element
	* @param {String} selectorFrom - selector of an element to apply on "enter" button click event
	* @param {String} selectorTo - selector of an element to set focus to
	**/
	Utils.changeFocusOnEnterClick = function(selectorFrom, selectorTo){
		$(selectorFrom).keyup(function(event){
			if(event.keyCode == 13){
				$(selectorTo).focus();
			}
		});
	};

	/**
	* Adds or removes .invalid class to/from the element specified by the given selector. The ".invalid" class can be used together with appropriate CSS in order to highlight the element.
	* @param {String} selector - selector of an element to add/remove .invalid class
	* @param {String} bool - boolean value. If the value is true then ".invalid" class is added otherwise it is removed.
	**/
	Utils.highlightField =function(selector,bool){
		if(!bool){
			$(selector).removeClass("invalid");
		}else{
			$(selector).addClass("invalid");
		}
	};

	Utils.addErrorMessageTo = function(selector, message){
		$(selector).append(Utils.createErrorMessage(message));
	};
	
	Utils.setErrorMessageTo = function(selector,message){
		Utils.clearErrorMessagesFrom(selector);
		Utils.addErrorMessageTo(selector,message);
	};

	Utils.clearErrorMessagesFrom = function(selector){
		//console.log("Clearing all error messages from "+ selector);
		var errorMessages = $(selector).find(".errorMessage");
		errorMessages.remove();
	};	


	Utils.unhideBody = function(){
		this.unhideElement('#fiteagle');
	};	

	Utils.hideBody = function(){
		this.hideElement("#fiteagle")
	};
	
	Utils.hideElement = function(selector){
		var toHide = $(selector);
		//console.log("Hiding Element"+ selector);
		if(!toHide.hasClass('hidden')){
				toHide.addClass('hidden');			
		}
	};
	
	Utils.unhideElement = function(selector){
		//console.log("Unhiding Element " + selector);
		$(selector).removeClass('hidden');
	};

	Utils.setCurrentUser = function(user){
				
		var userJSON = JSON.stringify(user);
		
		if(typeof(Storage)!=="undefined"){
			sessionStorage.user = userJSON;
		  }
		else{
			console.log("Session storage is no supported !");
		}
	};
	
	Utils.thisUserToBeRemembered = function(){
		toBeRemembered = true;
		sessionStorage.remember = toBeRemembered;
	}
	
	Utils.isThisUserToBeRemembered = function(){
		var result = false;
		if(sessionStorage.remember) 
			result = JSON.parse(sessionStorage.remember);
		return result;
	}
	
	Utils.updateInfoPanel = function(){
		user = Utils.getCurrentUser();
		//console.log('current user is set to: '+ Utils.userToString(user));
		$("#userName").text(user.firstName +" " + user.lastName);
	};

	Utils.getCurrentUser = function(){		
			if (sessionStorage.user != undefined){
				return JSON.parse(sessionStorage.user);
			}
			return null;
	};
	

	Utils.resetUser = function(){
		sessionStorage.clear();
	};

	Utils.userToString = function(user){
			var userToString = '';
			if(user !=null){
				userToString = user.firstName + " " + user.lastName + " " + user.email;
			}else{
				userToString = "no user is set";
			}
			return userToString;
	};
	
	Utils.initTooltipFor = function(selector,title,placement,trigger){
		if(selector){
			var s = $(selector);
			s.tooltip('destroy');
			//console.log("Tooltip for " + selector + ' placement '+ placement);
			s.tooltip({
				'title': title,
				'placement':placement, 
				'trigger' : trigger	
			});
		}
	};
	
	Utils.checkInputField = function(inputFieldSelector,errorFieldSelector,validationFunction,emptyFieldMsg,validationErrorMessage){
		
		var inputFieldValue = $(inputFieldSelector).val();
		
		if(Validation._isEmpty(inputFieldValue)){		
			Utils.highlightField(inputFieldSelector,true);
			Utils.addErrorMessageTo(errorFieldSelector,emptyFieldMsg);
		}else{
			var isValid = validationFunction(inputFieldValue);
			if(!isValid){
				Utils.addErrorMessageTo(errorFieldSelector,validationErrorMessage);
				Utils.highlightField(inputFieldSelector,true);	
			}else{
				Utils.highlightField(inputFieldSelector,false);	
			}
		}		
		return isValid;		
	};
	
	Utils.getCurrentTab = function(){
		var tab;
		if(typeof(Storage)!=="undefined"){
			tab = sessionStorage.currentTab;
			if(!tab){
				 tab = "#manageProfileMenu";
			}
		}else{
			console.log("Session storage is not supported !");
		}
		return tab;
	};
	
	Utils.setCurrentTab = function(currentTab){
		sessionStorage.currentTab = currentTab;
	};
	
	Utils.showCurrentTab = function(){
		//console.log("CURRENT " +this.getCurrentTab());
		$(this.getCurrentTab()).click();
	};

	
	Utils.createSuccessMessage = function(msg){
		var successMsg = createMessage(msg);
		successMsg.find('span').addClass("alert-success span12 centered");
		return successMsg;
	};
	
	Utils.createErrorMessage = function(msg){
		var errorMsg = createMessage(msg);
		errorMsg.find('span').addClass("alert-error span12 centered");
		return errorMsg;
		
	};
	
	createMessage = function(msg){
		var div = $('<div>').addClass("row-fluid errorMessage");
		var span = $('<span>').addClass('alert').html(msg);
		div.append(span);
		return div;
	};
	
	Utils.createNewUser = function(firstName,lastName,affiliation,password,email){		
		
		var newUser = new Object();
		
		newUser.firstName = firstName;
		newUser.lastName = lastName;
		newUser.email = email;
		newUser.affiliation = affiliation;
		newUser.password = password;
		newUser.publicKeys = [];

		//console.log(JSON.stringify(newUser));
		
		return newUser;
	};
	
	Utils.isSmallScreen = function(){
		var width = $(window).width();
		if(width < 979){
			return true;
		}
		return false;
	};
	
	Utils.showProgressbarModal = function(message){
		initProgressbarModal();
		$('#progressBarModal').find('.progressMessage').text(message);
		$('#progressBarModal').modal({
			backdrop: 'static',
			keyboard: false
		});
	};
	
	initProgressbarModal = function(){
		$('#progressBarModal').remove();
		var modal = createModal('progressBarModal');
		var modalBody = $('<div>').addClass('modal-body')
					.append('<h4 class="centered progressMessage"></h4>');
		Utils.addProgressbarTo(modalBody);
		modal.append(modalBody);
		$('body').append(modal);
	};
	
	createModal = function(id){
		var  modal = $('<div>')
			.attr('id',id)
			.addClass('modal hide fade')
			.attr('tabindex','-1')
			.attr('role','dialog')
			.attr('aria-labelledby','progressBarLabel')
			.attr('aria-hidden','true');		
		return modal;
	}
	
	Utils.showSuccessModal = function(successMsg){
		$('#successModal').remove();
		var modal = createModal('successModal');
		var okBtn  = $('<button>').addClass('btn btn-inverse btn-large')
						.attr('data-dismiss','modal')
						.attr('aria-hidden','true')
						.append('<i class="icon-ok"></i>OK');
		var modalBody = $('<div>').addClass('modal-body')
					.append(successMsg);	
					
		var modalFooter = $('<div>').addClass('modal-footer centered')
						.append(okBtn);
						
		modal.append(modalBody).append(modalFooter);
		$('body').append(modal);	
		$('#successModal').modal({
			backdrop: 'static',
			keyboard: false
		});
	}
	
	Utils.addProgressbarTo = function(object){
		object.find('.progress').remove();
		var progressBar = $('<div>').append(
					"<div class='top20 progress progress-striped active'>"+
						"<div class='bar' style='width: 100%;'></div>"+
					"</div>"
					);
		object.append(progressBar);
	};
	
	Utils.hideModal = function(selector){
		$(selector).modal('hide');
	};
	
	Utils.waitForFinalEvent = function () {
		var timers = {};
		return function (callback, ms, uniqueId) {
			if (!uniqueId) {
				uniqueId = "Don't call this twice without a uniqueId";
			}
			if (timers[uniqueId]) {
				clearTimeout (timers[uniqueId]);
			}
			timers[uniqueId] = setTimeout(callback, ms);
		};
	};
	
	return Utils;
});



