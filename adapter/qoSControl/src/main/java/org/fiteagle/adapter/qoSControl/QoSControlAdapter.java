package org.fiteagle.adapter.qoSControl;

import java.util.LinkedList;
import java.util.List;

import org.csapi.schema.parlayx.adq.v4_0.QoSFeatureProperties;
import org.csapi.schema.parlayx.common.v4_0.NameValuePair;
import org.csapi.schema.parlayx.common.v4_0.TimeMetric;
import org.fiteagle.adapter.common.AdapterConfiguration;
import org.fiteagle.adapter.common.Named;
import org.fiteagle.adapter.common.Publish;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named(name="QoSControl")
public class QoSControlAdapter extends ResourceAdapter {
Logger log = LoggerFactory.getLogger(getClass());
private QoSAdapterPreferences preferences;
private QoSClient client;
private String username = "001011234567890";
private String clientIP;
	public QoSControlAdapter() {
		super();
		preferences = new QoSAdapterPreferences();
	}

//	public static final URI BASE_URI = getBaseURI();

//	private static URI getBaseURI() {
//		return UriBuilder.fromUri("http://localhost/").port(40301).build();
//	}
//
//	protected static HttpServer startServer() throws IOException {
//		System.out.println("Starting grizzly...");
//		ResourceConfig rc = new PackagesResourceConfig(
//				"org.fiteagle.adapter.qoSControl.tmp");
//		return GrizzlyServerFactory.createHttpServer(BASE_URI, rc);
//	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public void configure(AdapterConfiguration configuration) {
		String url = 	"http://" + preferences.getIP() + ":" + preferences.getPort()+"/ngsi.applicationDrivenQoS/rest/1/QoSManager";
		log.info("creating client for: "+ url);
		client = new QoSClient(url);
		clientIP = preferences.getClientIP();
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub

	}

	
	public static List<ResourceAdapter> getJavaInstances() {
		LinkedList<ResourceAdapter> adapers = new LinkedList<>();

		adapers.add(new QoSControlAdapter());
		return adapers;
	}

	@Override
	public boolean isLoaded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setLoaded(boolean loaded) {
		// TODO Auto-generated method stub

	}
	
	@Publish
	public void setQoS(@Named(name="ruleId")String ruleId){
		log.info("Adapter received action setQoS: "+ruleId);
		client.applyQoSRule(null, username, ruleId);
	}
	
	@Publish
	public void setBandwidthDL(@Named(name="source_ip")String ip, @Named(name="bandwidth") String bandwitdh ){
		log.info("Adapter received action setBandwidth");
		if(client == null)
		log.info("client is not ready ... aboarting");
		
		
		QoSFeatureProperties props = new QoSFeatureProperties();
		List<NameValuePair> otherProps = props.getOtherProperties();
//		
//		NameValuePair nvp = new NameValuePair();
//		nvp.setName("ServiceIdentifier");
//		nvp.setValue("www.youtube.com");
//		otherProps.add(nvp);
		props.setDownStreamSpeedRate(bandwitdh);
//		props.setUpStreamSpeedRate("300000");
		TimeMetric duration = new TimeMetric();
		duration.setUnits(100000);
		props.setDuration(duration);
		NameValuePair source_ip = new NameValuePair();
		NameValuePair source_port = new NameValuePair();
		NameValuePair destination_ip = new NameValuePair();
	//	NameValuePair destination_port = new NameValuePair();
		NameValuePair mediatype = new NameValuePair();
		NameValuePair protocol = new NameValuePair();
		NameValuePair framedIp = new NameValuePair();
		NameValuePair direction = new NameValuePair();
		
		direction.setName("Direction");
		direction.setValue("out");
		framedIp.setName("Framed_IP");
		framedIp.setValue(clientIP);
		mediatype.setName("MediaType");
		mediatype.setValue("DATA");
		source_ip.setName("Source_ip");
		source_ip.setValue(ip); //ip mdf
//		source_port.setName("Source_port");
//		source_port.setValue("22");
		destination_ip.setName("Destination_ip");
		destination_ip.setValue(clientIP); //ip alice
//		destination_port.setName("Destination_port");
//		destination_port.setValue("1234");
		protocol.setName("Protocol");
		protocol.setValue("tcp");
		otherProps.add(source_ip);
//		otherProps.add(source_port);
		otherProps.add(destination_ip);
	//	otherProps.add(destination_port);
		otherProps.add(protocol);
		otherProps.add(framedIp);
		otherProps.add(direction);
		client.startSession("", username, props);
	}

	@Publish
	public void setBandwidthUL(@Named(name="source_ip")String ip, @Named(name="bandwidth") int bandwitdh ){
		
	}
}
