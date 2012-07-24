package teagle.vct.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.tools.ant.util.FileUtils;

public class FileUtilities {
	
	private FileUtilities() {
		//utility class
	}

	public static InputStream getAsResourceStream(final String filename) throws FileNotFoundException {
		final String correctFilename = FileUtilities.correctFilename(filename);
		final InputStream stream = FileUtilities.class
				.getResourceAsStream(correctFilename);
		if (null == stream) {
			throw new FileNotFoundException(correctFilename);
		}

		return stream;
	}
	
	public static String readFileAsString(final InputStream inputStream)
			throws IOException {
		final Reader reader = new InputStreamReader(inputStream, "UTF-8");
		return FileUtils.readFully(reader);
	}

	public static String readFileAsString(final String filename)
			throws IOException {
		return FileUtilities.readFileAsString(FileUtilities.getAsResourceStream(filename));
	}

	private static String correctFilename(final String filename) {
		return filename.startsWith("/") ? filename : "/" + filename;
	}
}
