package teagle.vct.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractProperties {
	public final static String PROPERTY_DIR = System.getProperty("user.home")
			+ File.separator + ".fiteagle" + File.separator;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractProperties.class);

	public static Properties getProperties(final String propertyName) {
		final Properties properties = new Properties();
		final String propFilename = PROPERTY_DIR + propertyName;
		
		try {
			InputStream propPath = new FileInputStream(propFilename);
			properties.load(propPath);
		} catch (final IOException e) {
			LOGGER.debug("Property not found: " + propFilename);
			//throw new RuntimeException("Property: " + propFilename, e);
		}
		return properties;
	}

	protected static String cleanProperties(String property) {
		return property == null ? "" : property;
	}

}