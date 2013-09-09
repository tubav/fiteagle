package org.fiteagle.core.util;

public class BooleanStringConverter<T> implements StringConverter<T> {

	@Override
	public T convert(String s) {
		return (T) new Boolean(s);
	}

}
