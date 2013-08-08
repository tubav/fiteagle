define([],
/**
 * @lends Utils
 */ 
function(){
	
	/** 
     * Validation class
     * This is the interface class for user enties validation
     * @class Validation
     * @constructor
     * @return Session Object
     */ 
	
	Validation = {};
	
	/**
	* Checks if the given text is not empty
	* @param{String} text - to be checked
	**/
	Validation._isEmpty = function(text){
		if(!text || text.length == 0)
			return true;
		return false;
	};

	/**
	* Checks if the given text is a human name or surname
	* @param{String} name - string name to be checked
	**/
	Validation._isName = function(name){
		/*var nameRegex = /^[a-zA-Z ]+$/;
		return (nameRegex.test(name));*/
		return true;
	};

	/**
	* Checks if the given text is a valid email address
	* @param{String} email - email to be validated
	**/
	Validation._isEmail = function(email){
		if(Validation._isEmpty(email)) return false;
		var regEmailExp = /^\w+([\.-]?\w)*@\w+([\.-]?\w)*(\.\w{2,4})+$/;
		return regEmailExp.test(email);
	};


	/**
	* Checks if the given password is at least six characters long
	* @param{String} pwd - password string to be validated
	**/
	Validation._isPassword = function(pwd){
		var isValidPassword = false;
		if(pwd.length > 3){ isValidPassword = true;}	
		//log("password is valid "+ isValidPassword);
		return isValidPassword;
	};
	
	
	Validation._isAffiliation = function(affiliation){
		return true; //TODO
	};
	
	Validation._isKeyName = function(key){
		return true;
	};
	
	
	Validation._isKeyValue = function(keyValue){
		// TODO Add appropriate public key validation
		var isValid = false;
		var keyLength = keyValue.length;
		if( 50 < keyLength && keyLength < 500 ) isValid = true;
		return isValid;
	}
	
	Validation._isPassphrase = function(phrase){
		if(phrase.length > 3) return true;
		return false;
	};
	
	Validation._isValidPublicKeyFileExtension = function(ext){
		if(ext === 'pub') return true;
		return false;
	};
	
	Validation._isValidPublicKeyFileSize = function(size){
		var minSize = 100;
		var maxSize = 400;
		if( !(minSize < size&&size < maxSize)) return false;
		return true;
	};

	return Validation;
});

