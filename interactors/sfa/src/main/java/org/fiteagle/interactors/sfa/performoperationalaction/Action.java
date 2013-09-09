package org.fiteagle.interactors.sfa.performoperationalaction;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import org.fiteagle.core.util.StringConverter;
import org.fiteagle.core.util.StringConverterProvider;

public class Action {

	private Object object;
	private List<Object> parameters;
	private Method method;
	private List<String> parameterValues;

	public Action(String string, Object object) {
		this.object = object;
		String name = getMethodName(string);
		parameterValues = getParameterStrings(string);
		method = getMethod(name);
		parameters = getParameters(parameterValues);

	}

	private Method getMethod(String name) {
		Method[] declaredMethods = object.getClass().getMethods();
		for (Method m : declaredMethods) {
			if (m.getName().equals(name)
					&& m.getParameterTypes().length == parameterValues.size()) {
				return m;
			}
		}
		throw new MethodNotFound(name);
	}

	private <T> T convert(String s, Class<T> t,
			ServiceLoader<StringConverterProvider> loader) {

		for (StringConverterProvider provider : loader) {
			StringConverter<T> converter = provider.getConverterFor(t);
			if (converter != null) {
				return converter.convert(s);
			}
		}
		return null;

	}

	private List<Object> getParameters(List<String> parameterStrings) {
		Class[] parameterTypes = method.getParameterTypes();
		ServiceLoader<StringConverterProvider> loader = ServiceLoader
				.load(StringConverterProvider.class);
		List<Object> returnList = null;
		if (parameterStrings.size() > 0)
			returnList = new ArrayList<>();
		for (int i = 0; i < parameterStrings.size(); i++) {
			if (parameterTypes[i].equals(String.class)) {
				returnList.add(parameterStrings.get(i));
			} else {
				Object o = convert(parameterStrings.get(i), parameterTypes[i],
						loader);
				returnList.add(o);
			}
			
		}
		return returnList;
	}

	private List<String> getParameterStrings(String string) {
		List<String> paraStrings = new ArrayList<>();
		if (string.contains("(")) {
			String insideTheBrackets = getInsideOfBrackets(string);
			String[] parametersArray = insideTheBrackets.split(",");

			for (String s : parametersArray) {
				paraStrings.add(s);
			}
		}
		return paraStrings;
	}

	private String getInsideOfBrackets(String string) {
		return string.substring(string.indexOf("(") + 1, string.indexOf(")"));
	}

	private String getMethodName(String string) {
		if (string.contains("("))
			return string.substring(0, string.indexOf("("));

		return string;
	}

	public <T> T doAction() throws InvocationTargetException, IllegalArgumentException, IllegalAccessException {

		T returnValue = null;
		
			Object[] methodParameters = new Object[] {};
			if (parameters != null)
				methodParameters = parameters.toArray();
			returnValue = (T) method.invoke(object, methodParameters);
		
		return returnValue;
	}

	public class MethodNotFound extends RuntimeException {

		public MethodNotFound(String name) {
			super(name);
		}

	}
}
