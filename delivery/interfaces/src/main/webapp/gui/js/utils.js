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

	Utils.updateUsersData = function(newFirstName, newLastName, newAffiliation, newEmail){
		Session.set("profileEditing", true);
		Meteor.users.update(Meteor.userId(), 
			{
				$set:{
					/*emails : [{
						address: newEmail,
						verified: false		
					}],*/
					profile: {
						firstName : newFirstName,
						lastName  : newLastName,
						affiliation : newAffiliation
					}
				}
			},
		//calback after updating
		function(error){
			Session.set("profileEditing", false);
			if (!error) {
				Template.userProfileInformation.error = function(){
					return false;
				};
			} else {
				Session.set('profile_edit', 'error');
				Template.userProfileInformation.error = function(){
					return true;
				};
				Template.userProfileInformation.errorMessage = function() {
					return error.reason;
				};
			}
		}
	);
	}

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
		$("#fiteagle").removeClass("hidden");
	};	

	Utils.hideBody = function(){
		$("#fiteagle").addClass("hidden");
	};


	Utils.setCurrentUser = function(user){
				
		var userJSON = JSON.stringify(user);
		
		if(typeof(Storage)!=="undefined"){
			sessionStorage.user = userJSON;
		  }
		else{
			console.log("Session storage is no supported !");
		}
		
		console.log("Set user " + userJSON);
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

	Utils.userToString = function(){
			var user = this.getCurrentUser();
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
		console.log("Total cookie number " + theCookies.length);
		var aString = '';
		for (var i = 1 ; i <= theCookies.length; i++) {
			aString += i + ' ' + theCookies[i-1] + "\n";
		} */
		
		var aString = "Cookie on site: " + document.cookie;
		
		return aString;
	};
	
	
	Utils.initTooltipFor = function(selector,title,placement,trigger){
		if(selector){
			$(selector).tooltip({
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
			dataType: "json",
			url : "/api/v1/user/"+username,
			beforeSend: function(xhr){
				xhr.setRequestHeader("Authorization",
                "Basic " + Utils.getCredentials()); // TODO Base64 support
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
	
	Utils.updateUserOnServer = function(user){
		$.ajax({
			cache: false,
			type: "POST",
			async: false,
			url: "/api/v1/user/"+user.username,
			data: JSON.stringify(user),
			contentType: "application/json",
			dataType: "json",
			beforeSend: function(xhr){
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
					console.log("User has been successfully updated on server");
					var updatedUser = Utils.getUserFromServer(user.username);
					Utils.setCurrentUser(updatedUser);
					initAddRemoveKeysForm();
				}
			}
		});
	};	
	
	return Utils;
});



