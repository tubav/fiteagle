package org.fiteagle.core.util;

public interface StringConverterProvider {
	<T> StringConverter<T> getConverterFor(Class<T> clazz);

}
