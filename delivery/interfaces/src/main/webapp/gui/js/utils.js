define([],
/**
 * @lends Utils
 */ 
function(){ 
	
	/** 
     * Utils class contains common used functions in order to ease the programming experience. 
     * @class
     * @constructor
     * @return Utils object
     */
	Utils = {};
	
	/**
	* Adds on "enter" button click event for the given element in order to trigger "on click event" for the other specified element
	* @param {String} selectorOn - selector of an element to apply on "enter" button click event
	* @param {String} triggerOn - selector of an element to trigger "on click" event
	* @public
	* @name Utils#addOnEnterClickEvent
	* @function
	*/
	Utils.addOnEnterClickEvent = function (selectorOn, triggerOn){
		$(selectorOn).keyup(function(event){
			if(event.keyCode == 13){
				$(triggerOn).click();
				$(triggerOn).focus();
			}
		});
	};

	/**
	* Changes focus after on "enter" button click event to the other specified element
	* @param {String} selectorFrom - selector of an element to apply on "enter" button click event
	* @param {String} selectorTo - selector of an element to set focus to
	* @public
	* @name Utils#changeFocusOnEnterClick
	* @function
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
	* @public
	* @name Utils#highlightField
	* @function
	**/
	Utils.highlightField =function(selector,bool){
		if(!bool){
			$(selector).removeClass("invalid");
		}else{
			$(selector).addClass("invalid");
		}
	};
	
	/**
	* Creates an error message with the specified text and adds it to the given filed found by the provided selector. 
	* The error message is an twitter bootstrap "alert alert-danger" span field.
	* @param {String} selector - selector of an element to add/remove .invalid class
	* @param {String} bool - boolean value. If the value is true then ".invalid" class is added otherwise it is removed.
	* @public
	* @name Utils#addErrorMessageTo
	* @function
	**/
	Utils.addErrorMessageTo = function(selector, message){
		$(selector).append(Utils.createErrorMessage(message));
	};
	
	/**
	* Creates the specified error message and sets it as only one message in the specified error container.
	* @param {String} selector of a container to set the error messages to.
	* @param {String} message text to create an error message and add it to the error container.
	* @public
	* @name Utils#setErrorMessageTo
	* @function
	*/
	Utils.setErrorMessageTo = function(selector,message){
		Utils.clearErrorMessagesFrom(selector);
		Utils.addErrorMessageTo(selector,message);
	};

	/**
	* Removes all previous shown error messages from the specified container.
	* @param {String} selector of a container to remove error messages for.
	* @public
	* @name Utils#clearErrorMessagesFrom
	* @function
	*/
	Utils.clearErrorMessagesFrom = function(selector){
		//console.log("Clearing all error messages from "+ selector);
		var errorMessages = $(selector).find(".errorMessage");
		errorMessages.remove();
	};	
	
	/**
	* Triggers update of the user info panel. The user's first and last name will be set to the values 
	* from the current user profile.
	* @public
	* @name Utils#updateUserInfoPanel
	* @function
	*/
	Utils.updateUserInfoPanel = function(){
		var user = Utils.getCurrentUser();
		//console.log('current user is set to: '+ Utils.userToString(user));
		$("#userName").text(user.firstName +" " + user.lastName);
	};

	/**
	* Unhides the entire body content of the current HTML page.
	* @public
	* @name Utils#unhideBody
	* @function
	*/
	Utils.unhideBody = function(){
		this.unhideElement('#fiteagle');
	};	

	/**
	* Hides the entire body content of the current HTML page.
	* @public
	* @name Utils#hideBody
	* @function
	*/
	Utils.hideBody = function(){
		this.hideElement("#fiteagle");
	};
	
	/**
	* Hides the specified element by adding a 'hidden' class. Note that the class has to be available
	* in the appropriate css file. 
	* @example ".hidden {display:none}"
	* @param {String} selector of an element to be hidden.
	* @public
	* @name Utils#hideElement
	* @function
	*/
	Utils.hideElement = function(selector){
		var toHide = $(selector);
		//console.log("Hiding Element"+ selector);
		if(!toHide.hasClass('hidden')){
				toHide.addClass('hidden');			
		}
	};
	
	/**
	* Unhides the specified element by removing a 'hidden' class. Note that the class has to be available
	* in the appropriate css file. 
	* @example ".hidden{display:none}"
	* @param {String} selector of an element to be unhidden.
	* @public
	* @name Utils#unhideElement
	* @function
	*/
	Utils.unhideElement = function(selector){
		//console.log("Unhiding Element " + selector);
		$(selector).removeClass('hidden');
	};

	/**
	* Sets the given user object to store in a session storage in order to have a quick access to the user profile.
	* @param {Object} user object to be stored in a session storage.
	* @public
	* @name Utils#setCurrentUser
	* @function
	*/
	Utils.setCurrentUser = function(user){
				
		var userJSON = JSON.stringify(user);
		
		if(typeof(Storage)!=="undefined"){
			sessionStorage.user = userJSON;
		  }
		else{
			console.log("Session storage is no supported !");
		}
	};
	
	/**
	* Stores in a session storage the flag that the current user has to be remembered.
	* @public
	* @name Utils#thisUserToBeRemembered
	* @function
	*/
	Utils.thisUserToBeRemembered = function(){
		toBeRemembered = true;
		sessionStorage.remember = toBeRemembered;
	};
	
	
	/**
	* Checks if the current user is marked as a user that has to be remembered.
	* @return {Boolean} true marked as user to be remembered, false otherwise.
	* @public
	* @name Utils#isThisUserToBeRemembered
	* @function
	*/
	Utils.isThisUserToBeRemembered = function(){
		var result = false;
		if(sessionStorage.remember) 
			result = JSON.parse(sessionStorage.remember);
		return result;
	};
	
	/**
	* Gets a current user profile representation from a session storage in JSON format.
	* @public
	* @name Utils#getCurrentUser
	* @return {String} JSON representation of the user stored in a session storage. 
	* "null" is returned if no user is stored in a session storage.
	* @function
	*/
	Utils.getCurrentUser = function(){		
			if (sessionStorage.user != undefined){
				return JSON.parse(sessionStorage.user);
			}
			return null;
	};
	
		
	/**
	* Removes the all user specific information from a session storage by entirely session storage clearing.
	* @public
	* @name Utils#resetUser
	* @function
	*/
	Utils.resetUser = function(){
		sessionStorage.clear();
	};

	/**
	* Converts a user object to its textual representation.
	* @param  {Object} user profile object
	* @return {String} user profile textual representation
	* @public
	* @name Utils#userToString
	* @function
	*/
	Utils.userToString = function(user){
			var userToString = '';
			if(user !=null){
				userToString = user.firstName + " " + user.lastName + " " + user.email;
			}else{
				userToString = "no user is set";
			}
			return userToString;
	};
	
	/**
	* Stores the specified hash tag in a session storage.
	* @param {String} hash tag to be stored.
	* @public
	* @name Utils#storeHashTag
	* @function
	*/
	Utils.storeHashTag = function(tag){
		sessionStorage.hashTag = tag;
	};
	
	/**
	* Gets a currently stored hash tag from a session storage.
	* @public
	* @name Utils#getStoredHashTag
	* @function
	*/
	Utils.getStoredHashTag = function(){
		return sessionStorage.hashTag;
	};
	
	/**
	* Initializes a twitter bootstrap tooltip for the specified element.
	* @param {String} selector of an element to init a the tooltip for.
	* @param {String} text to be shown within the tooltip body.
	* @param {String} placement of a tooltip bubble. Value: "top", "right", "left", "bottom"
	* @param {String} trigger event after that the tooltip is shown. Values: "focus" , "hover", "click".
	* @see Twitter Bootstrap Tooltip for more informatio.
	* @public
	* @name Utils#initTooltipFor
	* @function
	*/
	Utils.initTooltipFor = function(selector,title,placement,trigger){
		if(selector){
			var s = $(selector);
//			s.tooltip('destroy');
			//console.log("Tooltip for " + selector + ' placement '+ placement);
			s.tooltip({
				'title': title,
				'placement':placement, 
				'trigger' : trigger	
			});
		}
	};
	
	/**
	* Triggers procedure for a validation of the entered value into the input field. 
	* shows an appropriate message and highlights the input field.
	* @param {String} inputFieldSelector is a selector of an input field to be validated
	* @param {String} errorFieldSelector is a selector of an error container to place validation messages.
	* @param {Function} validationFunction is a function to apply validation method.
	* @param {String} emptyFieldMsg is a message to be shown if the value is empty
	* @param {String} validationErrorMessage is a message to be shown if the value is wrong.
	* @return {Boolean} true if the input value is valid and false otherwise
	* @public
	* @name Utils#checkInputField
	* @function
	*/
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

	
	/**
	* Creates a success message object based on Twitter Bootstrap alert alert-success. 
	* @param  {String} message text to be put into the created success message object. 
    * @return {Object} success message object
	* @public
	* @name Utils#createSuccessMessage
	* @function
	*/
	Utils.createSuccessMessage = function(msg){
		var successMsg = createMessage(msg);
		successMsg.find('span').addClass("alert-success span12 centered");
		return successMsg;
	};
	
	/**
	* Creates an error message object based on Twitter Bootstrap alert alert-error. 
	* @param  {String} message text to be put into the created error message object. 
    * @return {Object} error message object
	* @public
	* @name Utils#createErrorMessage
	* @function
	*/
	Utils.createErrorMessage = function(msg){
		var errorMsg = createMessage(msg);
		errorMsg.find('span').addClass("alert-error span12 centered");
		return errorMsg;
		
	};
	
	/**
	* Creates an error message container.
	* @param {String} msg - message to be put into the error container body
	* @return {Object} error container object
	* @private
	* @memberOf Utils#
	*/
	createMessage = function(msg){
		var div = $('<div>').addClass("row-fluid errorMessage");
		var span = $('<span>').addClass('alert').html(msg);
		div.append(span);
		return div;
	};
	
	/**
	* Create new user object that can be stringified and used in some requests towards 
	* the FITeagle REST API.
	* @param {String} firstName 
	* @param {String} lastName 
	* @param {String} affiliation 
	* @param {String} password
	* @param {String} email
	* @return {Object} newUser object representing a new user profile without username in it.
	* @public
	* @name Utils#createNewUser
	* @function
	*/
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
	
	/**
	* Checks if the current screen is a "small" screen meaning. The small screens are the screens where the
	* twitter bootstrap responsive navigation menu collapses. It happens for all screens with 
	* the screen width smaller then 979 pixels.
	* @public
	* @name Utils#isSmallScreen
	* @function
	* @return {Boolean} true is it is a small scree and false otherwise
	*/
	Utils.isSmallScreen = function(){
		var width = $(window).width();
		if(width < 979){
			return true;
		}
		return false;
	};
	
	/**
	* Creates a custom Twitter Bootstrap modal with specified identifier, header, body and footer.
	* @param {String} id - is an identifier of the created modal
	* @param {Object} modalHeader object carries custom HTML structure to be placed into the modal header section. 
	* @param {Object} modalBody object carries custom HTML structure to be placed into the modal body section.
	* @param {Object} modalFooter bject carries custom HTML structure to be placed into the modal footer section.
	* @public 
	* @name Utils#createCustomModal
	* @function
	* @return {Object} custom twitter bootstrap modal object that can be append to the body and triggered to be shown.
	* @see Twitter Bootstrap modal for more information
	*/
	Utils.createCustomModal = function(id, modalHeader, modalBody, modalFooter){
		$("#"+id).remove();
		var modal = createModal(id);
		var head = $('<div>').addClass('modal-header');
		var body = $('<div>').addClass('modal-body');
		var foot = $('<div>').addClass('modal-footer');
		if(modalHeader) modal.append(head.append(modalHeader));
		if(modalBody) modal.append(body.append(modalBody));
		if(modalFooter) modal.append(foot.append(modalFooter));
		$('body').append(modal);
		return modal;
	};
	
	/**
	* Creates a twitter bootstrap confirmation modal object
	* @param {String} id - is an identifier of the created confirmation modal container object
	* @param {String} okId - is an identifier of the confirmation button
	* @param {String} okBtnText - is an label of the confirmation button
	* @param {String} closeBtnText - is an label of the dismiss button
	* @param {Object} body object carries custom HTML structure to be placed into the confirmation modal body section. 
	* @public 
	* @name Utils#createConfirmModal
	* @function
	* @return {Object} twitter bootstrap confirmation modal object
	* @see Twitter Bootstrap modal for more information
	*/
	Utils.createConfirmModal = function(id,okId,okBtnText,closeBtnText,body){
		var ok = $('<button>')
				.attr('id',okId)
				.addClass('btn btn-success')
				.append('<i class="icon-ok"></i>'+okBtnText);
				
		var cancel  = $('<button>')
				.addClass('btn btn-danger left40')
				.attr('data-dismiss','modal')
				.attr('aria-hidden','true')
				.append('<i class="icon-remove-sign"></i>'+closeBtnText);
				
		var footer = $('<div>').addClass('centered')
					.append(ok)
					.append(cancel);
			
		return Utils.createCustomModal(id, null,body, footer);
	};
	
	
	/**
	* Creates a twitter bootstrap modal object with the specified container identifier.
	* @param  {String} id - is an identifier of the created modal container
	* @return {}
	* @private
	* @memberOf Utils#
	*/
	createModal = function(id){
		var  modal = $('<div>')
			.attr('id',id)
			.addClass('modal hide fade')
			.attr('tabindex','-1')
			.attr('role','dialog')
			.attr('aria-labelledby','progressBarLabel')
			.attr('aria-hidden','true');		
		return modal;
	};
	
	/**
	* Shows a modal specified by a selector. 
	* NOTE: The modal with the given selector has to be appended to the DOM before in order it can be shown.
	* @param {String} selector of a modal to be shown.
	* @public  
	* @name Utils#showModal
	* @function
	*/
	Utils.showModal = function(selector){
		$(selector).modal({
			backdrop: 'static',
			keyboard: false
		});
	};
	
	/**
	* Creates a success modal with the specified message located in the modal body and shows the modal.
	* @param {String} successMsg - message to be put into the success modal body.
	* @param {String} okId - is an identifier of the dismiss success modal button.
	* @public  
	* @name Utils#showSuccessModal
	* @function
	*/
	Utils.showSuccessModal = function(successMsg,okId){
		var foot = $('<div>').addClass('centered')
			.append(
				$('<button>').addClass('btn btn-success btn-large')
				.attr('id',okId)
				.attr('data-dismiss','modal')
				.attr('aria-hidden','true')
				.append('<i class="icon-ok"></i>OK')
			);
			
		var body = $('<div>').addClass('centered').append(successMsg);
		
		var modal = Utils.createCustomModal('successModal',null,body,foot);
		Utils.showModal('#successModal');
	};
	
	/**
	* Hides a modal specified by a given selector.
	* @param {String} selector of a modal container to be closed.
	* @public  
	* @name Utils#hideModal
	* @function
	*/
	Utils.hideModal = function(selector){
		$(selector).modal('hide');
	};
	
	return Utils;
});



