package teagle.repository;

/**
 * Simple placeholder interface for configuration data. Currently it's being shared between all the components:
 * the web frontend (java server pages), the repository web service and the VCT tool.
 * TODO: Plans to break this into a real class instance. 
 * @author alexandru.mihaiuc@fokus.fraunhofer.de
 *
 */
public interface Config {
	final String DIGEST_USER = "teagle";
	final String DIGEST_PASS = "*4teagle#";
		
	//final String EXIST_URI = "xmldb:exist://193.175.134.195:18080/exist/xmlrpc";
	//final String EXIST_URI = "xmldb:exist://193.175.134.134/repo/exist/xmlrpc";
	//final String EXIST_URI = "xmldb:exist://127.0.0.1:18080/exist/xmlrpc";
	//final String EXIST_USER = "admin";
	//final String EXIST_PASSWORD = "123parola";
	
	//final String XMLDB_DRIVER = "org.exist.xmldb.DatabaseImpl";
	
	final String RR_COLLECTION_RRI = "teagle/registry";	// for the resource repository
	final String RR_RESOURCE = "resources.xml";
	final String RR_RESOURCE_INSTANCES = "resource_instances.xml";
	//final String RESOURCE_CODEBASE = "http://pike/~bodi/teagle/library/bin/";
	
	final String U_CONFIG_RRI = "teagle";	// contains the user configuration data; TODO: must move to plain SQL
	final String U_CONFIGURATIONS = "configurations.xml";
	
	final String RA_COLLECTION_RRI = "teagle/registry";	// for resource availability
	//final String RA_RESOURCE = "availabilities.xml";
	final String RR_VCT_RRI = "vcts.xml";	// resource registry - vct resource identifier
	final String RR_RR_RRI = "reservations.xml";	// resource registry - resource reservation identifier
	final String RR_ORGANIZATIONS_RRI = "organizations.xml";	// the list of organizations at the moment
	final String RR_PTMS_RRI = "ptms.xml";	// the list of PTMs and their mappings to the organizations / users
	
	final String URL_AVAILABILITY_SERVICE = "http://www.fire-teagle.org/repo/axis2/services/RepositoryManager";
	//final String URL_AVAILABILITY_SERVICE = "http://193.175.134.134/repo/axis2/services/RepositoryManager";
	//final String URL_AVAILABILITY_SERVICE = "http://127.0.0.1:18080/axis2/services/RepositoryManager";
	final String PATH_AVAILABILITY_TIMETABLES = "webapps/axis2/WEB-INF/services/RepositoryManager/data";
	
	final String NOTIFY_URL = "http://pike/~bodi/teagle/vct/notify.php";
	
	final String REPOSITORY_SRC_DIR = "/usr/share/apache-tomcat-5.5.27/webapps/teagle-vct/library/partners";
	final String REPOSITORY_LIB_DIR = "/usr/share/apache-tomcat-5.5.27/webapps/teagle-vct";
	
	// added for replacing $ and _ in the XML representation through XStream
	// by default, it replaces $ with _- and _ with __
	// $ appears in representing subclasses or java identifiers
	// _ is more common in identifiers
	final String REPLACEMENT_DOLLAR = "--"; // XStream generates constructs like <string-array>, so double - needed
	final String REPLACEMENT_UNDERSCORE = "_";
}
