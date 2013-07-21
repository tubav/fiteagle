define(['require','utils'],
/**
 * @lends MainPage
 */ 
function(require,Utils){
	
	/** 
     * Server class
     * The Server class contains functions needed for the remote communication with the FITeagle server. 
	 * Such as user profile, public key and certificate management. 
     * @class
     * @constructor
     * @return Server object
     */
	Server = {};
	
		
	Server.loginUser = function(username,password,rememberMe){		
		//console.log("Sending login information to the server...");
		var msg;
		var signInBtn = $('#signIn');
		setCookie = "";
		if(rememberMe){
			setCookie="setCookie=true";
			Utils.thisUserToBeRemembered();
		}
		$.ajax({
			cache: false,
			type: "GET",
			async: false,
			data: setCookie,
			url : "/api/v1/user/"+username,
			beforeSend: function(xhr){
				signInBtn.hide();
				Utils.unhideElement('#loginForm .progress');
				xhr.setRequestHeader("Authorization",
                "Basic " + btoa(username + ":" + password)); // TODO Base64 support
			},
			success: function(user,status,xhr){			
				Utils.setCurrentUser(user);
				$('#loginForm').modal('hide');
				require('mainPage').load();
			},
			error: function(xhr,status,thrown){		
				msg = thrown;
				console.log("Response " + xhr.responseText);
				console.log(status);
				console.log(thrown);
			},
			complete: function(){
				setTimeout(function(){
					signInBtn.show();
					Utils.hideElement('#loginForm .progress');
				},100);
			},
			statusCode:{				
				404: function(){
					msg = Messages.userNotFound;	
				},
				401 : function(){
					msg = Messages.wrongPasswordKey;
				}
			}
		});
		
		return msg;
	};
	
	Server.registerUser = function(newUser,newUsername,successFunction){	
		console.log("Registering a new user on a server...");
		var userToJSON = JSON.stringify(newUser);
		var message;
		//console.log("New USER "+ userToJSON);			
		$.ajax({
			cache: false,
			type: "PUT",
			async: false,
			url: "/api/v1/user/"+newUsername,
			data: userToJSON,
			contentType: "application/json",
			dataType: "json",
			success: function(data,status){

			},
			error: function(xhl,status){
				message = Utils.createErrorMessage(xhl.responseText);
				console.log(status);
			},
			statusCode:{				
				200: function(){
					console.log("New user is successfully registered");		
				},			
				201: function(){
					console.log("New user: "+ newUser.firstName +" "+newUser.lastName+ " has been successfully created.");				
					newUser.username = newUsername;
					Utils.setCurrentUser(newUser);
					Server.loginUser(newUser.username, newUser.password,false,successFunction);
				}
			}
		});
		
		return message;
	};
	
	Server.getUser = function(username){	
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
				Utils.setCurrentUser(userFromServer);
			},
			error: function(xhr,status,thrown){
				console.log("Response " + xhr.responseText);
				console.log(status);
				console.log(thrown);
			},
			statusCode:{			
				401: function(){
					console.log("Unathorized access. To be signed out")
					require('mainPage').signOut();
				}
			},
				
		});
		
		return userFromServer;
	};
	
	Server.updateUser = function(updateInformation){
		//console.log("credentials" + Utils.getCredentials());
		console.log("Updating user on the server...");
		var data = JSON.stringify(updateInformation);
		var user = Utils.getCurrentUser(); 
		console.log(user);
		var message;
		$.ajax({
			cache: false,
			type: "POST",
			async: false,
			url: "/api/v1/user/"+user.username,
			data: data,
			contentType: "application/json",
			dataType: "json",
			beforeSend: function(xhr){
			},
			success: function(data,status){
				console.log("UPDATING DATA " + data);
				console.log(status);
			},
			error: function(xhl,status){
				message = Utils.createErrorMessage(xhl.responseText);
				console.log(status);
			},
			statusCode:{			
				200: function(){
					var msg = "User has been successfully updated on the server";
					Utils.setCurrentUser(Server.getUser(user.username));
					message = Utils.createSuccessMessage(msg);
					$('#saveProfileInfo').addClass('disabled');
				}
			}
		});
		
		return message;
	};
	
	
	Server.uploadNewPublicKey = function(publicKey, uploadingSign){
		
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
			},
			success: function(data,status){
				console.log(data);
				console.log(status);
			},
			error: function(xhl,status){
				message = xhl.responseText;
				console.log(status);
			},
			statusCode:{			
				200: function(){
					var updatedUser = Server.getUser(username);
					Utils.setCurrentUser(updatedUser);
					Utils.showSuccessModal(
						Utils.createSuccessMessage("New public key with description: "
																+publicKey.description+
															" has been successfully uploaded")
					);
					return ;
				},
				
				409 : function(){
						return Utils.createErrorMessage(message);
				}
			},
			complete: function(){
				Utils.hideElement(uploadingSign);
			}
		});		
	};
	
	Server.generateCertificateForPiblicKey = function(publicKeyDescription){

		var username = Utils.getCurrentUser().username;
		var certificat;
		$.ajax({
			cache: false,
			type: "GET",
			async: false,
			url : "/api/v1/user/"+username+"/pubkey/"+publicKeyDescription+"/certificate",
			beforeSend: function(xhr){
				//Utils.showProgressbarModal(Messages.generateCertificate);
			},
			success: function(cert,status,xhr){
				certificat = cert;
				//console.log(xhr.responseText);
			},
			error: function(xhr,status,thrown){
				console.log("Response " + xhr.responseText);
				console.log(status);
				console.log(thrown);
			},
			complete: function(){
				//Utils.hideProgressbarModal(Messages.generateCertificate);
			}
		});

		return certificat;
	};
	
	Server.generatePublicKeyAndCertificate = function(passphrase){
		var username = Utils.getCurrentUser().username;
		var keyAndCertificate;
		var errorMessage;
		$.ajax({
			cache: false,
			type: "POST",
			async: false,
			url: "/api/v1/user/"+username+'/certificate',
			data: passphrase,
			contentType: "text/plain",
			beforeSend: function(xhr){
				//Utils.showProgressbarModal(Messages.generateNewKeyAndCertificate);
			},
			success: function(data,status){
				keyAndCertificate = data;
			},
			error: function(xhl,status){
				errorMessage = xhl.responseText;
				console.log(xhl.responseText);
				console.log(status);
			},
			complete: function(){
				//Utils.hideProgressbarModal();
			}
		});
		
		return [keyAndCertificate,errorMessage];
	};
	
	Server.invalidateCookie = function(username){
		if(!username)username = Utils.getCurrentUser().username;
		isSuccessful = false;
		$.ajax({
			cache: false,
			type: "DELETE",
			async: false,
			url : "/api/v1/user/"+username+"/cookie",
			beforeSend: function(xhr){

			},
			success: function(answer,status,xhr){
				isSuccessful = true;
			},
			error: function(xhr,status,thrown){
				console.log("Response " + xhr.responseText);
				console.log(status);
				console.log(thrown);
			},
			complete: function(){
			},
		});
		
		return isSuccessful;
	};
	
	Server.deletePublicKey = function(keyDescription){
		var user = Utils.getCurrentUser();
		var username = user.username;
		var message;
		$.ajax({
			cache: false,
			type: "DELETE",
			async: false,
			url: "/api/v1/user/"+username+'/pubkey/'+keyDescription,
			beforeSend: function(xhr){
				//xhr.setRequestHeader("Authorization",
                //"Basic " + Utils.getCredentials()); // TODO Base64 support
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
					var updatedUser = Server.getUser(username);
					Utils.setCurrentUser(updatedUser);
					message = Utils.createSuccessMessage("Public key " + keyDescription+" has been successfully removed.");
				}
			},
			complete: function(){
				Utils.hideElement(uploadingSign);
			}
		});
		
		return message;
	};
		
	Server.deleteUser = function(afterDeleteFunction){
		var user = Utils.getCurrentUser();
		var username = user.username;
		var message;
		$.ajax({
			cache: false,
			type: "DELETE",
			async: false,
			url: "/api/v1/user/"+username,
			beforeSend: function(xhr){
				
			},
			success: function(data,status){
				console.log(data);
				console.log(status);
				message = Utils.createSuccessMessage('Current User has been successfully deleted');
			},
			error: function(xhl,status){
				message = Utils.createErrorMessage(xhl.responseText);
				console.log(status);
			},
			statusCode:{			
				200: function(){		
					//require('profile').afterDeleteFunction();
					afterDeleteFunction();
				}
			},
			complete: function(){
				//Utils.hideElement(deletingSign);
			}
		});
		
		return message;
	};
		
	return Server;

});
