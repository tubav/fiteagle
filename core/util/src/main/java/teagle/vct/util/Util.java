package teagle.vct.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.management.ReflectionException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.mapper.Mapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;

public class Util {
	/**
	 * The equivalent to class.getSimpleName(), if you already have getName()
	 */
	public static String finalName(final String className) {
		final String[] elems = className.split(".");
		return elems[elems.length - 1];
	}

	/**
	 * Shorthand for the getClass-getField-get list of calls, that only throws
	 * one type of exception.
	 */
	public static Object getField(final Object o, final String name)
			throws ReflectionException {
		try {
			return o.getClass().getField(name).get(o);
		} catch (final SecurityException e) {
			throw new ReflectionException(e);
		} catch (final NoSuchFieldException e) {
			throw new ReflectionException(e);
		} catch (final IllegalArgumentException e) {
			throw new ReflectionException(e);
		} catch (final IllegalAccessException e) {
			throw new ReflectionException(e);
		}
	}

	/**
	 * Shorthand for the getClass-getField-set list of calls, that only throws
	 * one type of exception.
	 */
	public static void setField(final Object o, final String name,
			final Object value) throws ReflectionException {
		try {
			o.getClass().getField(name).set(o, value);
		} catch (final SecurityException e) {
			throw new ReflectionException(e);
		} catch (final NoSuchFieldException e) {
			throw new ReflectionException(e);
		} catch (final IllegalArgumentException e) {
			throw new ReflectionException(e);
		} catch (final IllegalAccessException e) {
			throw new ReflectionException(e);
		}
	}

	/**
	 * Instantiates o.name, to whatever its declared type is.
	 */
	public static void instantiate(final Object o, final String name)
			throws ReflectionException, NoSuchFieldException {
		try {
			final Field f = o.getClass().getField(name);
			f.set(o, f.getType().newInstance());
		} catch (final SecurityException e) {
			throw new ReflectionException(e);
			// } catch (NoSuchFieldException e) { throw new
			// ReflectionException(e);
		} catch (final IllegalArgumentException e) {
			throw new ReflectionException(e);
		} catch (final IllegalAccessException e) {
			throw new ReflectionException(e);
		} catch (final InstantiationException e) {
			throw new ReflectionException(e);
		}
	}

	/**
	 * Instantiates o.fieldName, and all its fields, recursively.
	 */
	public static void deepInstantiate(final Object o, final String fieldName)
			throws ReflectionException, SecurityException, NoSuchFieldException {

		final Class<?> fieldType = o.getClass().getField(fieldName).getType();

		Util.setField(o, fieldName, Util.doDeepInstantiate(fieldType));
	}

	/**
	 * Returns a newly instantiated object of type clazz (all its fields are
	 * instantiated recursively as well).
	 */
	public static Object doDeepInstantiate(final Class<?> clazz)
			throws ReflectionException {
		try {
			final Object value = clazz.newInstance();
			for (final Field f : clazz.getFields())
				if (!f.getType().isPrimitive()
						&& !Modifier.isStatic(f.getModifiers())
						&& (f.get(value) == null))
					f.set(value, Util.doDeepInstantiate(f.getType()));

			return value;
		} catch (final InstantiationException e) {
			throw new ReflectionException(e);
		} catch (final IllegalAccessException e) {
			throw new ReflectionException(e);
		}
	}

	/**
	 * Shorthand for showing an SWT message box.
	 * 
	 * @param parent
	 *            a Control (typically a Shell)
	 * @param style
	 *            one of SWT.ICON_INFORMATION/WARNING/...
	 * @param title
	 *            Dialog title
	 * @param message
	 *            Dialog message
	 * @return
	 */
	public static int showMsg(final Control parent, final int style,
			final String title, final String message) {
		final MessageBox box = new MessageBox(parent.getShell(), style
				| SWT.PRIMARY_MODAL);
		box.setText(title);
		box.setMessage(message);
		return box.open();
	}

	/**
	 * Runs the message loop of a shell until it completes.
	 */
	public static void waitShell(final Shell shell) {
		while (!shell.isDisposed())
			if (!shell.getDisplay().readAndDispatch())
				shell.getDisplay().sleep();
	}

	public static Point[] bezierCubic(final Point p0, final Point p3,
			final int step) {
		final Point p1 = new Point(p0.x + 100, p0.y);
		final Point p2 = new Point(p3.x - 100, p3.y);
		if (p0.y == p3.y) {
			p1.y -= 80;
			p2.y -= 80;
		}
		final Point[] p = { p0, p1, p2, p3 };

		final int mdist = Math.abs(p0.x - p3.x) + Math.abs(p0.y - p3.y);
		final int pts = mdist / step;
		final Point[] curve = new Point[pts + 1];

		for (int k = 0; k <= pts; k++) {
			final double t = ((double) k) / pts;
			final double t1 = 1.0 - t;
			final double[] c = { t1 * t1 * t1, 3 * t1 * t1 * t, 3 * t1 * t * t,
					t * t * t };
			final double x = (c[0] * p[0].x) + (c[1] * p[1].x)
					+ (c[2] * p[2].x) + (c[3] * p[3].x);
			final double y = (c[0] * p[0].y) + (c[1] * p[1].y)
					+ (c[2] * p[2].y) + (c[3] * p[3].y);
			curve[k] = new Point((int) x, (int) y);
		}

		return curve;
	}

	public static void showException(final Control parent, final String title,
			final Throwable t) {
		t.printStackTrace();
		Util.showMsg(parent, SWT.ICON_ERROR | SWT.OK, title, t.getClass()
				.getSimpleName()
				+ ": "
				+ t.getMessage()
				+ " (java console has more details)");
	}

	public static void showException(final Control parent, final Throwable e) {
		Util.showException(parent, "PTM Error", e);
	}

	public static InputStream resourceStream(final String name) {
		// Util.class.getResourceAsStream ?
		return Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(name);
	}

	public static String readStream(final InputStream i) throws IOException {
		final ByteArrayOutputStream o = new ByteArrayOutputStream();
		final byte[] buf = new byte[1024];
		int len;
		while ((len = i.read(buf, 0, 1024)) > 0)
			o.write(buf, 0, len);
		return new String(o.toByteArray());
	}

	public static String nlEncode(String text) {
		text = text.replace("\\", "\\\\");
		text = text.replace("\n", "\\n");
		return text;
	}

	// Logging. Feel free to use log4j or whatever instead of this.
	// Why reinvent the wheel ? Because the various logging libs
	// make you add that static field to each class. And when you just
	// want to print that 'x', it gets annoying.

	private static final int LOG_ERROR = 1;
	private static final int LOG_WARN = 2;
	private static final int LOG_INFO = 3;
	private static final int LOG_DEBUG = 4;

	private static final String[] logLevels = { "NONE", "ERROR", "WARN",
			"INFO", "DEBUG" };

	private static Map<String, Integer> logPrefixes = new HashMap<String, Integer>();

	static {
		Util.logPrefixes.put("default", Util.LOG_WARN);

		try {
			final Properties tmp = new Properties();
			final InputStream is = Util.resourceStream("log.properties");
			if (is == null)
				throw new IOException("can't find resource");
			tmp.load(is);

			for (final Map.Entry<Object, Object> entry : tmp.entrySet()) {
				final String prefix = (String) entry.getKey();
				final String levelStr = ((String) entry.getValue())
						.toUpperCase();
				int level = -1;

				for (int i = 0; i < Util.logLevels.length; i++)
					if (levelStr.equals(Util.logLevels[i]))
						level = i;

				if (level < 0) {
					System.out.println("Unknown log level: " + levelStr);
					level = Util.LOG_WARN;
				}

				Util.logPrefixes.put(prefix, level);
			}
		} catch (final IOException e) {
			System.out.println("Util: can't read log.properties resource");
		}
	}

	private static DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static void log(final int level, final String txt) {
		try {
			StackTraceElement e = null;
			boolean foundUtil = false;
			final StackTraceElement[] trace = Thread.currentThread()
					.getStackTrace();

			for (int i = 0; (e == null) && (i < trace.length); i++) {
				final String simpleName = Class
						.forName(trace[i].getClassName()).getSimpleName();
				final boolean equalsUtil = simpleName.equals("Util");
				foundUtil |= equalsUtil;
				if (foundUtil && !equalsUtil)
					e = trace[i];
			}

			if (e == null)
				return;

			final String clazz = Class.forName(e.getClassName())
					.getSimpleName();
			final String method = e.getMethodName();
			final String location = clazz + "." + method;

			String longestPrefix = "";
			for (final String prefix : Util.logPrefixes.keySet())
				if (location.startsWith(prefix)
						&& (prefix.length() > longestPrefix.length()))
					longestPrefix = prefix;

			if (longestPrefix.equals(""))
				longestPrefix = "default";

			// System.out.println("clazz=" + clazz + " level=" + level +
			// "longestPrefix=" + longestPrefix);
			if (level <= Util.logPrefixes.get(longestPrefix)) {
				final String date = Util.dateFormat.format(new Date());
				System.out.println(date + " " + location + " "
						+ Util.logLevels[level] + ": " + txt);
			}
		} catch (final ClassNotFoundException e1) {
		}
	}

	public static void error(final String txt) {
		Util.log(Util.LOG_ERROR, txt);
	}

	public static void warn(final String txt) {
		Util.log(Util.LOG_WARN, txt);
	}

	public static void info(final String txt) {
		Util.log(Util.LOG_INFO, txt);
	}

	public static void debug(final String txt) {
		Util.log(Util.LOG_DEBUG, txt);
	}

	public static void p(final String txt) {
		Util.log(Util.LOG_WARN, txt);
	}

	public static String stripWhitepace(final String text) {
		int s = 0, e = text.length() - 1;

		while (s <= e) {
			final int c = text.charAt(s);
			if (!((c == ' ') || (c == '\t') || (c == '\r') || (c == '\n')))
				break;
			s++;
		}

		while (e >= s) {
			final int c = text.charAt(e);
			if (!((c == ' ') || (c == '\t') || (c == '\r') || (c == '\n')))
				break;
			e--;
		}

		return (s <= e) ? text.substring(s, e + 1) : "";
	}

	private static Document parseXml(final InputSource source)
			throws ParserConfigurationException, SAXException, IOException {
		final DocumentBuilderFactory domFactory = DocumentBuilderFactory
				.newInstance();
		domFactory.setNamespaceAware(true);
		final DocumentBuilder builder = domFactory.newDocumentBuilder();

		return builder.parse(source);
	}

	public static Document parseXml(final String text)
			throws ParserConfigurationException, SAXException, IOException {
		return Util.parseXml(new InputSource(new StringReader(text)));
	}

	public static Document parseXml(final InputStream stream)
			throws ParserConfigurationException, SAXException, IOException {
		return Util.parseXml(new InputSource(stream));
	}

	public static void writeXml(final Node node, final Writer writer)
			throws TransformerException {
		final TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		try {
			transformerFactory.setAttribute("indent-number", 2);
		} catch (final IllegalArgumentException e) {
		}

		final Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

		final StreamResult result = new StreamResult(writer);
		transformer.transform(new DOMSource(node), result);
	}

	public static XStream newXstream() {
		return new XStream(new DomDriver(null, new XmlFriendlyNameCoder(
				"SYMDOLLAR", "_"))) {
			@Override
			protected MapperWrapper wrapMapper(final MapperWrapper next) {
				// System.out.println("Returning MapperWrapper");
				return new MapperWrapper(next) {
					@Override
					public boolean shouldSerializeMember(
							@SuppressWarnings("rawtypes") final Class definedIn,
							final String fieldName) {
						// System.out.println("Shouldserialize? " + fieldName);
						if (definedIn == Object.class)
							return false;
						return super
								.shouldSerializeMember(definedIn, fieldName);
					}
				};
			}
		};

		// return new XStream(new DomDriver(null, new
		// XmlFriendlyReplacer("SYMDOLLAR", "_")));
	}

	public static XStream newXstream(final Mapper mapper) {
		return new XStream(null, new DomDriver(null, new XmlFriendlyNameCoder(
				"SYMDOLLAR", "_")), Util.class.getClassLoader(), mapper);
	}

	public static String mayBeNull(final String s) {
		return (s != null) ? s : "N/A";
	}
}
