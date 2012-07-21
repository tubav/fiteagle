package teagle.repository;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Class loader that provides a simple name of instantiating classes currently over HTTP.
 * Used for the various resource classes from the library subproject.
 * @author alexandru.mihaiuc@fokus.fraunhofer.de
 * 
 * TODO: bogdan.vasile.harjoc@fokus.fraunhofer.de: get it to try loading from the parent before trying the URLs
 */
public class Loader extends URLClassLoader {
	public Loader(ClassLoader parent, String codebase) throws MalformedURLException {
		super(new URL[]{new URL(codebase)}, parent);
		Util.info(codebase);
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		try {
			return getParent().loadClass(name);
		} catch (ClassNotFoundException e) {
			return super.loadClass(name);
		}
	}
}
