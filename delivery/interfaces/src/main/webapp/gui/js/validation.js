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
	* @param {String} text - to be checked
	* @public
	* @name Validation#_isEmpty
	* @function
	**/
	Validation._isEmpty = function(text){
		if(!text || text.length == 0)
			return true;
		return false;
	};

	/**
	* Checks if the given text is a human name or surname
	* @param {String} name - string name to be checked
	* @public
	* @name Validation#_isName
	* @function
	**/
	Validation._isName = function(name){
		var nameRegex = /^[a-zA-Z0-9]*[a-zA-Z]+[a-zA-Z0-9]*$/;
		return (nameRegex.test(name));
	};

	/**
	* Checks if the given text is a valid email address
	* @param {String} email - email to be validated
	* @public
	* @name Validation#_isEmail
	* @function
	**/
	Validation._isEmail = function(email){
		if(Validation._isEmpty(email)) return false;
		var regEmailExp = /^\w+([\.-]?\w)*@\w+([\.-]?\w)*(\.\w{2,4})+$/;
		return regEmailExp.test(email);
	};


	/**
	* Checks if the given password is at least six characters long
	* @param {String} pwd - password string to be validated
	* @public
	* @name Validation#_isPassword
	* @function
	**/
	Validation._isPassword = function(pwd){
		var isValidPassword = false;
		if(pwd.length > 3){ isValidPassword = true;}	
		//log("password is valid "+ isValidPassword);
		return isValidPassword;
	};
	
	/**
	* Checks if the given affiliation has a valid syntax.
	* @param {String} affiliation - affiliation value to be validated
	* @public
	* @name Validation#_isAffiliation
	* @function
	**/
	Validation._isAffiliation = function(affiliation){
		return true; //TODO
	};
	
	
	/**
	* Checks if the given public key description name has a valid syntax.
	* @param {String} keyDescription - public key description value to be validated
	* @public
	* @name Validation#_isKeyName
	* @function
	**/	
	Validation._isKeyName = function(key){
		return true;
	};
	
	
	/**
	* Checks if the given public key value has a valid syntax.
	* @param {String} keyValue - public key value value to be validated
	* @public
	* @name Validation#_isKeyValue
	* @function
	**/
	Validation._isKeyValue = function(keyValue){
		// TODO Add appropriate public key validation
		var isValid = false;
		var keyLength = keyValue.length;
		if( 50 < keyLength && keyLength < 500 ) isValid = true;
		return isValid;
	}
	
	
	/**
	* Checks if the given password phrase is strong enough.
	* @param {String} phrase - password phrase to be validated
	* @public
	* @name Validation#_isPassphrase
	* @function
	**/
	Validation._isPassphrase = function(phrase){
		if(phrase.length > 3) return true;
		return false;
	};
	
	/**
	* Checks if the given public file extension is a valid one.
	* @param {String} extension - public key file extension to be validated
	* @public
	* @name Validation#_isValidPublicKeyFileExtension
	* @function
	**/
	Validation._isValidPublicKeyFileExtension = function(ext){
		if(ext === 'pub') return true;
		return false;
	};
	
	/**
	* Checks if the given public file size has a valid length.
	* @param {String} file_size - public key file size to be validated
	* @public
	* @name Validation#_isValidPublicKeyFileSize
	* @function
	**/
	Validation._isValidPublicKeyFileSize = function(size){
		var minSize = 100;
		var maxSize = 400;
		if( !(minSize < size&&size < maxSize)) return false;
		return true;
	};

	return Validation;
});

