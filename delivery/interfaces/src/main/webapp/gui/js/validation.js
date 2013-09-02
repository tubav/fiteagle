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
	* Checks if the given text is a valid username
	* The username must be between 5 and 15 characters, dot and underscore are allowed, the first letter should be number or character.
	* @param {String} username - to be checked
	* @public
	* @name Validation#_isEmpty
	* @function
	**/
	Validation._isUsername = function(username){
		var usernameRegex = /[a-zA-Z0-9][a-zA-Z0-9_.@]/;
		return (usernameRegex.test(username));
	};
	

	/**
	* Checks if the given text is a human name or surname.
	* Acceptable names include compound names with a hyphen or a space in them.
	* @param {String} name - string name to be checked
	* @public
	* @name Validation#_isName
	* @function
	**/
	Validation._isName = function(name){
		var nameRegex = /^[a-zA-Z]+((\s|\-)[a-zA-Z]+)?$/;
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
	* Checks if the given password is strong enough.
	* Password must be between 4 and 8 digits long and include at least one numeric digit.
	* @param {String} pwd - password string to be validated
	* @public
	* @name Validation#_isPassword
	* @function
	**/
	Validation._isPassword = function(pwd){		
		var strongPwd = /^(?=.*\d).{4,8}$/;
		return (strongPwd.test(pwd));
	};
	
	/**
	* Checks if the given affiliation has a valid syntax.
	* The affiliation should be between 2 and 20 digits, underscore, hyphen, apostrophe and dots are allowed. First letter can be only character.
	* @param {String} affiliation - affiliation value to be validated
	* @public
	* @name Validation#_isAffiliation
	* @function
	**/
	Validation._isAffiliation = function(affiliation){
		var affReg =  /^[a-zA-Z]+([a-zA-Z0-9\s_'-.]{2,20})$/;
		return (affReg.test(affiliation));
	};
	
	
	/**
	* Checks if the given public key description name has a valid syntax.
	* The public key description should be from 3 to 15 digits long. The first letter has to be a character. Only alphanumeric characters are allowed.
	* @param {String} keyDescription - public key description value to be validated
	* @public
	* @name Validation#_isKeyName
	* @function
	**/	
	Validation._isKeyName = function(key){
		var keyNameReg =  /^[a-zA-Z]+([a-zA-Z0-9\s]{3,15})$/;
		return (keyNameReg.test(key));
	};
	
	
	/**
	* Checks if the given public key value has a valid syntax.
	* Client side validation is just checking for valid length. The public key length should be between 100 and 500 digits.
	* @param {String} keyValue - public key value value to be validated
	* @public
	* @name Validation#_isKeyValue
	* @function
	**/
	Validation._isKeyValue = function(keyValue){
		// TODO Add appropriate public key validation
		var isValid = false;
		var keyLength = keyValue.length;
		if( 100 < keyLength && keyLength < 500 ) isValid = true;
		return isValid;
	}
	
	
	/**
	* Checks if the given password phrase is strong enough.
	* Password must be at least 4 characters, no more than 8 characters, and must include at least one lower case letter, and one numeric digit.
	* @param {String} phrase - password phrase to be validated
	* @public
	* @name Validation#_isPassphrase
	* @function
	**/
	Validation._isPassphrase = function(phrase){
		var strongPwd = /^(?=.*\d)(?=.*[a-z]).{4,8}$/;
		return (strongPwd.test(phrase));
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

