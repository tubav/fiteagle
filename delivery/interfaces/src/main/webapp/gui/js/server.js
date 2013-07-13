define(['require','utils'],
/**
 * @lends MainPage
 */ 
function(require,Utils){
	
	Server = {};
	
		
	Server.loginUser = function(username,password,rememberMe){
		
		console.log("Sending login information to the server...");
		setCookie = "";
		if(rememberMe)setCookie="setCookie=true";			
		$.ajax({
			cache: false,
			type: "GET",
			async: false,
			data: setCookie,
			url : "/api/v1/user/"+username,
			beforeSend: function(xhr){
				Login.showLoadingSign();
				xhr.setRequestHeader("Authorization",
                "Basic " + btoa(username + ":" + password)); // TODO Base64 support
			},
			complete: function(){
				Login.hideLoadingSign();
			},
			success: function(user,status,xhr){							
				require('mainPage').load();
				Utils.setCurrentUser(user);	
			},
			error: function(xhr,status,thrown){
				console.log("Response " + xhr.responseText);
				console.log(status);
				console.log(thrown);
			},
			statusCode:{
				401: function(){
					Utils.addErrorMessageTo("#loginErrors",Messages.wrongPasswordKey);
				},
				404: function(){
					Utils.addErrorMessageTo("#loginErrors", Messages.userNotFound);	
				}
			}
		});
	};
	
	Server.registerUser = function(newUser,newUsername){	
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
					Server.loginUser(newUser.username, newUser.password);
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
			},
			error: function(xhr,status,thrown){
				console.log("Response " + xhr.responseText);
				console.log(status);
				console.log(thrown);
			}	
		});
		
		return userFromServer;
	};
	
	Server.updateUser = function(userToUpdate, loadingSign){
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
					Utils.setCurrentUser(Server.getUser(userToUpdate.username));
					message = Utils.createSuccessMessage(msg);
				}
			},
			complete: function(){
				window.setTimeout(function(){
					Utils.hideElement(loadingSign);
				},200);
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
				xhr.setRequestHeader("Authorization",
                "Basic " + Utils.getCredentials()); // TODO Base64 support
			},
			success: function(data,status){
				console.log(data);
				console.log(status);
			},
			error: function(xhl,status){
				message = Utils.createErrorMessage(xhl.responseText);
				console.log(status);
			},
			statusCode:{			
				200: function(){
					var updatedUser = Server.getUser(username);
					Utils.setCurrentUser(updatedUser);
					message = Utils.createSuccessMessage("New public key has been successfully uploaded");
				}
			},
			complete: function(){
				Utils.hideElement(uploadingSign);
			}
		});
		
		return message;
		
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
				
				xhr.setRequestHeader("Authorization",
                "Basic " + Utils.getCredentials()); // TODO Base64 support
			},
			success: function(cert,status,xhr){
				certificat = cert;
				console.log(xhr.responseText);
			},
			error: function(xhr,status,thrown){
				console.log("Response " + xhr.responseText);
				console.log(status);
				console.log(thrown);
			},
			complete: function(){
				//console.log("ENDE");
			}
		});

		return certificat;
	};
	
	Server.generatePublicKeyAndCertificate = function(passphrase,generatingSign,successFunction){
		var user = Utils.getCurrentUser();
		var username = user.username;
		var keyAndCertificate;
		$.ajax({
			cache: false,
			type: "POST",
			async: false,
			url: "/api/v1/user/"+username+'/certificate',
			data: passphrase,
			contentType: "text/plain",
			beforeSend: function(xhr){
				Utils.unhideElement(generatingSign);
			},
			success: function(data,status){
				keyAndCertificate = data;
				successFunction();
				//console.log(data);
				//console.log(status);
			},
			error: function(xhl,status){
				//message = Utils.createErrorMessage();
				console.log(xhl.responseText);
				console.log(status);
			},
			complete: function(){
				Utils.hideElement(generatingSign);
			}
		});
		
		return keyAndCertificate;
	};
	
	Server.invalidateCookie = function(){
		var username = Utils.getCurrentUser().username;
		$.ajax({
			cache: false,
			type: "DELETE",
			async: false,
			url : "/api/v1/user/"+username+"/cookie",
			beforeSend: function(xhr){

			},
			success: function(answer,status,xhr){
				//console.log(":SUCCESS" + answer) ;
				require('mainPage').signOut();
			},
			error: function(xhr,status,thrown){
				console.log("Response " + xhr.responseText);
				console.log(status);
				console.log(thrown);
			},
			complete: function(){
				//console.log("ENDE COOKIE INVALIDATION");
			},
		});
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
					message = Utils.createSuccessMessage("Public key has been successfully deleted");
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
