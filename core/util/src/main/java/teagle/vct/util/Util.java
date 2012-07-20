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

public class Util
{
	/**
	 * The equivalent to class.getSimpleName(), if you already have getName()
	 */
	public static String finalName(String className)
	{
		String[] elems = className.split(".");
		return elems[elems.length - 1];
	}

	/**
	 * Shorthand for the getClass-getField-get list of calls, that only throws
	 * one type of exception.
	 */
	public static Object getField(Object o, String name)
			throws ReflectionException
	{
		try
		{
			return o.getClass().getField(name).get(o);
		}
		catch (SecurityException e)
		{
			throw new ReflectionException(e);
		}
		catch (NoSuchFieldException e)
		{
			throw new ReflectionException(e);
		}
		catch (IllegalArgumentException e)
		{
			throw new ReflectionException(e);
		}
		catch (IllegalAccessException e)
		{
			throw new ReflectionException(e);
		}
	}

	/**
	 * Shorthand for the getClass-getField-set list of calls, that only throws
	 * one type of exception.
	 */
	public static void setField(Object o, String name, Object value)
			throws ReflectionException
	{
		try
		{
			o.getClass().getField(name).set(o, value);
		}
		catch (SecurityException e)
		{
			throw new ReflectionException(e);
		}
		catch (NoSuchFieldException e)
		{
			throw new ReflectionException(e);
		}
		catch (IllegalArgumentException e)
		{
			throw new ReflectionException(e);
		}
		catch (IllegalAccessException e)
		{
			throw new ReflectionException(e);
		}
	}

	/**
	 * Instantiates o.name, to whatever its declared type is.
	 */
	public static void instantiate(Object o, String name)
			throws ReflectionException, NoSuchFieldException
	{
		try
		{
			Field f = o.getClass().getField(name);
			f.set(o, f.getType().newInstance());
		}
		catch (SecurityException e)
		{
			throw new ReflectionException(e);
			// } catch (NoSuchFieldException e) { throw new
			// ReflectionException(e);
		}
		catch (IllegalArgumentException e)
		{
			throw new ReflectionException(e);
		}
		catch (IllegalAccessException e)
		{
			throw new ReflectionException(e);
		}
		catch (InstantiationException e)
		{
			throw new ReflectionException(e);
		}
	}

	/**
	 * Instantiates o.fieldName, and all its fields, recursively.
	 */
	public static void deepInstantiate(Object o, String fieldName)
			throws ReflectionException, SecurityException, NoSuchFieldException
	{

		Class<?> fieldType = o.getClass().getField(fieldName).getType();

		Util.setField(o, fieldName, doDeepInstantiate(fieldType));
	}

	/**
	 * Returns a newly instantiated object of type clazz (all its fields are
	 * instantiated recursively as well).
	 */
	public static Object doDeepInstantiate(Class<?> clazz)
			throws ReflectionException
	{
		try
		{
			Object value = clazz.newInstance();
			for (Field f : clazz.getFields())
				if (!f.getType().isPrimitive()
						&& !Modifier.isStatic(f.getModifiers())
						&& f.get(value) == null)
					f.set(value, doDeepInstantiate(f.getType()));

			return value;
		}
		catch (InstantiationException e)
		{
			throw new ReflectionException(e);
		}
		catch (IllegalAccessException e)
		{
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
	public static int showMsg(Control parent, int style, String title,
			String message)
	{
		MessageBox box = new MessageBox(parent.getShell(), style
				| SWT.PRIMARY_MODAL);
		box.setText(title);
		box.setMessage(message);
		return box.open();
	}

	/**
	 * Runs the message loop of a shell until it completes.
	 */
	public static void waitShell(Shell shell)
	{
		while (!shell.isDisposed())
			if (!shell.getDisplay().readAndDispatch())
				shell.getDisplay().sleep();
	}

	public static Point[] bezierCubic(Point p0, Point p3, int step)
	{
		Point p1 = new Point(p0.x + 100, p0.y);
		Point p2 = new Point(p3.x - 100, p3.y);
		if (p0.y == p3.y)
		{
			p1.y -= 80;
			p2.y -= 80;
		}
		Point[] p = { p0, p1, p2, p3 };

		int mdist = Math.abs(p0.x - p3.x) + Math.abs(p0.y - p3.y);
		int pts = mdist / step;
		Point[] curve = new Point[pts + 1];

		for (int k = 0; k <= pts; k++)
		{
			double t = ((double) k) / pts;
			double t1 = 1.0 - t;
			double[] c = { t1 * t1 * t1, 3 * t1 * t1 * t, 3 * t1 * t * t,
					t * t * t };
			double x = c[0] * p[0].x + c[1] * p[1].x + c[2] * p[2].x + c[3]
					* p[3].x;
			double y = c[0] * p[0].y + c[1] * p[1].y + c[2] * p[2].y + c[3]
					* p[3].y;
			curve[k] = new Point((int) x, (int) y);
		}

		return curve;
	}

	public static void showException(Control parent, String title, Throwable t)
	{
		t.printStackTrace();
		Util.showMsg(parent, SWT.ICON_ERROR | SWT.OK, title, t.getClass()
				.getSimpleName()
				+ ": "
				+ t.getMessage()
				+ " (java console has more details)");
	}

	public static void showException(Control parent, Throwable e)
	{
		Util.showException(parent, "PTM Error", e);
	}

	public static InputStream resourceStream(String name)
	{
		// Util.class.getResourceAsStream ?
		return Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(name);
	}

	public static String readStream(InputStream i) throws IOException
	{
		ByteArrayOutputStream o = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len;
		while ((len = i.read(buf, 0, 1024)) > 0)
			o.write(buf, 0, len);
		return new String(o.toByteArray());
	}

	public static String nlEncode(String text)
	{
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

	static
	{
		logPrefixes.put("default", LOG_WARN);

		try
		{
			Properties tmp = new Properties();
			InputStream is = resourceStream("log.properties");
			if (is == null)
				throw new IOException("can't find resource");
			tmp.load(is);

			for (Map.Entry<Object, Object> entry : tmp.entrySet())
			{
				String prefix = (String) entry.getKey();
				String levelStr = ((String) entry.getValue()).toUpperCase();
				int level = -1;

				for (int i = 0; i < logLevels.length; i++)
					if (levelStr.equals(logLevels[i]))
						level = i;

				if (level < 0)
				{
					System.out.println("Unknown log level: " + levelStr);
					level = LOG_WARN;
				}

				logPrefixes.put(prefix, level);
			}
		}
		catch (IOException e)
		{
			System.out.println("Util: can't read log.properties resource");
		}
	}

	private static DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static void log(int level, String txt)
	{
		try
		{
			StackTraceElement e = null;
			boolean foundUtil = false;
			StackTraceElement[] trace = Thread.currentThread().getStackTrace();

			for (int i = 0; e == null && i < trace.length; i++)
			{
				String simpleName = Class.forName(trace[i].getClassName())
						.getSimpleName();
				boolean equalsUtil = simpleName.equals("Util");
				foundUtil |= equalsUtil;
				if (foundUtil && !equalsUtil)
					e = trace[i];
			}

			if (e == null)
				return;

			String clazz = Class.forName(e.getClassName()).getSimpleName();
			String method = e.getMethodName();
			String location = clazz + "." + method;

			String longestPrefix = "";
			for (String prefix : logPrefixes.keySet())
				if (location.startsWith(prefix)
						&& prefix.length() > longestPrefix.length())
					longestPrefix = prefix;

			if (longestPrefix.equals(""))
				longestPrefix = "default";

			// System.out.println("clazz=" + clazz + " level=" + level +
			// "longestPrefix=" + longestPrefix);
			if (level <= logPrefixes.get(longestPrefix))
			{
				String date = dateFormat.format(new Date());
				System.out.println(date + " " + location + " "
						+ logLevels[level] + ": " + txt);
			}
		}
		catch (ClassNotFoundException e1)
		{
		}
	}

	public static void error(String txt)
	{
		log(LOG_ERROR, txt);
	}

	public static void warn(String txt)
	{
		log(LOG_WARN, txt);
	}

	public static void info(String txt)
	{
		log(LOG_INFO, txt);
	}

	public static void debug(String txt)
	{
		log(LOG_DEBUG, txt);
	}

	public static void p(String txt)
	{
		log(LOG_WARN, txt);
	}

	public static String stripWhitepace(String text)
	{
		int s = 0, e = text.length() - 1;

		while (s <= e)
		{
			int c = text.charAt(s);
			if (!(c == ' ' || c == '\t' || c == '\r' || c == '\n'))
				break;
			s++;
		}

		while (e >= s)
		{
			int c = text.charAt(e);
			if (!(c == ' ' || c == '\t' || c == '\r' || c == '\n'))
				break;
			e--;
		}

		return (s <= e) ? text.substring(s, e + 1) : "";
	}

	private static Document parseXml(InputSource source)
			throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory domFactory = DocumentBuilderFactory
				.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();

		return builder.parse(source);
	}

	public static Document parseXml(String text)
			throws ParserConfigurationException, SAXException, IOException
	{
		return parseXml(new InputSource(new StringReader(text)));
	}

	public static Document parseXml(InputStream stream)
			throws ParserConfigurationException, SAXException, IOException
	{
		return parseXml(new InputSource(stream));
	}

	public static void writeXml(Node node, Writer writer)
			throws TransformerException
	{
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		try
		{
			transformerFactory.setAttribute("indent-number", 2);
		}
		catch (IllegalArgumentException e)
		{
		}

		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

		StreamResult result = new StreamResult(writer);
		transformer.transform(new DOMSource(node), result);
	}

	public static XStream newXstream()
	{
		return new XStream(new DomDriver(null, new XmlFriendlyNameCoder(
				"SYMDOLLAR", "_")))
		{
			@Override
			protected MapperWrapper wrapMapper(MapperWrapper next)
			{
				// System.out.println("Returning MapperWrapper");
				return new MapperWrapper(next)
				{
					@Override
					public boolean shouldSerializeMember(@SuppressWarnings("rawtypes") Class definedIn,
							String fieldName)
					{
						// System.out.println("Shouldserialize? " + fieldName);
						if (definedIn == Object.class)
						{
							return false;
						}
						return super
								.shouldSerializeMember(definedIn, fieldName);
					}
				};
			}
		};

		// return new XStream(new DomDriver(null, new
		// XmlFriendlyReplacer("SYMDOLLAR", "_")));
	}

	public static XStream newXstream(Mapper mapper)
	{
		return new XStream(null, new DomDriver(null, new XmlFriendlyNameCoder(
				"SYMDOLLAR", "_")), Util.class.getClassLoader(), mapper);
	}

	public static String mayBeNull(String s)
	{
		return (s != null) ? s : "N/A";
	}
}
