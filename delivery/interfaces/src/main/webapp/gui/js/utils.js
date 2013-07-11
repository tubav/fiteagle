define([],
/**
 * @lends Utils
 */ 
function(){ 
	
	Utils = {};
	
	/**
	* Adds on "enter" button click event for the given element in order to trigger "on click event" for the other specified element
	* @param {JQuery Selector} selectorOn - selector of an element to apply on "enter" button click event
	* @param {JQuery Selector} triggerOn - selector of an element to trigger "on click" event
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
	* @param {JQuery Selector} selectorFrom - selector of an element to apply on "enter" button click event
	* @param {JQuery Selector} selectorTo - selector of an element to set focus to
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
	* @param {JQuery Selector} selector - selector of an element to add/remove .invalid class
	* @param {JQuery Selector} bool - boolean value. If the value is true then ".invalid" class is added otherwise it is removed.
	**/
	Utils.highlightField =function(selector,bool){
		if(!bool){
			$(selector).removeClass("invalid");
		}else{
			$(selector).addClass("invalid");
		}
	};

	Utils.addErrorMessageTo = function(selector, message){
		console.log('adding error message ['+message+']'+" to "+selector);
		var errorDIV = $("<div>").addClass("row-fluid errorMessage");
		var errorMessage =  $("<span>").addClass("alert alert-error").text(message);
		errorDIV.append(errorMessage);
		console.log("found "+ $(selector));
		$(selector).append(errorDIV);
	};

	Utils.clearErrorMessagesFrom = function(selector){
		console.log("Clearing all error messages from "+ selector);
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
	
	Utils.updateInfoPanel = function(){
		user = Utils.getCurrentUser();
		console.log('current user is set to: '+ Utils.userToString(user));
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
	
	Utils.listCookies = function() {
		/*var theCookies = document.cookie.split(';');
		console.log("Total cookie number " + theCookies.length);U
		var aString = '';
		for (var i = 1 ; i <= theCookies.length; i++) {
			aString += i + ' ' + theCookies[i-1] + "\n";
		} */
		
		var aString = "Cookie on site: " + document.cookie;
		
		return aString;
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
			if(typeof tab == "undefined"){
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
	
	Utils.setCredentials = function(username,password){
		sessionStorage.credentials = btoa(username + ":" + password);
	}; 
	
	Utils.getCredentials = function(){
		return sessionStorage.credentials;
	};
	
	
	Utils.getUserFromServer = function(username){
		
		var userFromServer = null;
		
		$.ajax({
			cache: false,
			type: "GET",
			async: false,
			url : "/api/v1/user/"+username,
			beforeSend: function(xhr){
			},
			success: function(user,status,xhr){
				userFromServer = user;
			},
			error: function(xhr,status,thrown){
				console.log("Response " + xhr.responseText);
				console.log(status);
				console.log(thrown);
			}
		});
	
		return userFromServer;
	};
	
	Utils.updateUserOnServer = function(userToUpdate, loadingSign){
		//console.log("credentials" + Utils.getCredentials());
		console.log("Updating user on the server...");
		var data = JSON.stringify(userToUpdate);
		var message;
		$.ajax({
			cache: false,
			type: "POST",
			async: false,
			url: "/api/v1/user/"+userToUpdate.username,
			data: data,
			contentType: "application/json",
			dataType: "json",
			beforeSend: function(xhr){
				Utils.unhideElement(loadingSign);
				xhr.setRequestHeader("Authorization",
                "Basic " + Utils.getCredentials()); // TODO Base64 support
			},
			success: function(data,status){
				console.log("UPDATING DATA " + data);
				console.log(status);
			},
			error: function(xhl,status){
				console.log(xhl.responseText);
				console.log(status);
			},
			statusCode:{			
				200: function(){
					var msg = "User has been successfully updated on server";
					Utils.setCurrentUser(Utils.getUserFromServer(userToUpdate.username));
					message = createSuccessMessage(msg);
				}
			},
			complete: function(){
				Utils.hideElement(loadingSign);
			}
		});
		
		return message;
	};
	
	Utils.uploadNewPublicKey = function(publicKey, uploadingSign){
		
		var user = Utils.getCurrentUser();
		var username = user.username;
		var message;
		$.ajax({
			cache: false,
			type: "POST",
			async: false,
			url: "/api/v1/user/"+username+'/pubkey',
			data: JSON.stringify(publicKey),
			contentType: "application/json",
			dataType: "json",
			beforeSend: function(xhr){
				Utils.unhideElement(uploadingSign);
				xhr.setRequestHeader("Authorization",
                "Basic " + Utils.getCredentials()); // TODO Base64 support
			},
			success: function(data,status){
				console.log(data);
				console.log(status);
			},
			error: function(xhl,status){
				message = createErrorMessage(xhl.responseText);
				console.log(status);
			},
			statusCode:{			
				200: function(){
					var updatedUser = Utils.getUserFromServer(username);
					Utils.setCurrentUser(updatedUser);
					message = createSuccessMessage("New public key has been successfully uploaded");
				}
			},
			complete: function(){
				Utils.hideElement(uploadingSign);
			}
		});
		
		return message;
		
	};
	
	Utils.deletePublicKey = function(keyDescription){
		var user = Utils.getCurrentUser();
		var username = user.username;
		var message;
		$.ajax({
			cache: false,
			type: "DELETE",
			async: false,
			url: "/api/v1/user/"+username+'/pubkey/'+keyDescription,
			beforeSend: function(xhr){
				xhr.setRequestHeader("Authorization",
                "Basic " + Utils.getCredentials()); // TODO Base64 support
			},
			success: function(data,status){
				console.log(data);
				console.log(status);
			},
			error: function(xhl,status){
				message = createErrorMessage(xhl.responseText);
				console.log(status);
			},
			statusCode:{			
				200: function(){
					var updatedUser = Utils.getUserFromServer(username);
					Utils.setCurrentUser(updatedUser);
					message = createSuccessMessage("Public key has been successfully deleted");
				}
			},
			complete: function(){
				Utils.hideElement(uploadingSign);
			}
		});
		
		return message;
	};
	
	createSuccessMessage = function(msg){
		var successMsg = createMessage(msg);
		successMsg.find('span').addClass("alert-success");
		return successMsg;
	};
	
	createErrorMessage = function(msg){
		var errorMsg = createMessage(msg);
		errorMsg.find('span').addClass("alert-error");
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
	
	return Utils;
});



