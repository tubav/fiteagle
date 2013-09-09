package org.fiteagle.core.util;
import java.lang.Integer;

public class IntegerStringConverter<T> implements StringConverter<T> {

	public T convert(String s) {
		return (T) new Integer(s);
		
	}

	

}
