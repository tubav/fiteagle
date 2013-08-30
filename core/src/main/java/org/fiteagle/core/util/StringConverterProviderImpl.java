package org.fiteagle.core.util;

public class StringConverterProviderImpl implements StringConverterProvider {

	public <T> StringConverter<T> getConverterFor(Class<T> clazz) {
		if(clazz.equals(Integer.class) || clazz.equals(int.class)){
			return new IntegerStringConverter();
		}
		if(clazz.equals(Boolean.class)){
			return new BooleanStringConverter();
		}
		return null;
	}

}
