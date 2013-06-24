package org.fiteagle.interactors.configuration.fiteagleResourceDefinition;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fiteagle.core.userdatabase.User;

@Path("/rspec")
public class ResourceDefinition {
	
	String resourceXsd = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
			"<schema targetNamespace=\"https://www.fiteagle.org/api/v1/rspec/ext/1\" elementFormDefault=\"qualified\" xmlns=\"http://www.w3.org/2001/XMLSchema\" xmlns:tns=\"https://www.fiteagle.org/api/v1/rspec/ext/1\">\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"    <complexType name=\"parameter\">\n" + 
			"    	<attribute name=\"name\" type=\"string\"></attribute>\n" + 
			"    	<attribute name=\"type\" type=\"string\"></attribute>\n" + 
			"    </complexType>\n" + 
			"\n" + 
			"    <complexType name=\"property\">\n" + 
			"    	<attribute name=\"name\" type=\"string\"></attribute>\n" + 
			"    	<attribute name=\"type\" type=\"string\"></attribute>\n" + 
			"    	<attribute name=\"value\" type=\"string\"></attribute>\n" + 
			"    </complexType>\n" + 
			"\n" + 
			"\n" + 
			"    <element name=\"parameter\" type=\"tns:parameter\"></element>\n" + 
			"\n" + 
			"    <element name=\"property\" type=\"tns:property\"></element>\n" + 
			"\n" + 
			"    <complexType name=\"method\">\n" + 
			"    	<sequence>\n" + 
			"    		<element ref=\"tns:parameter\" maxOccurs=\"unbounded\" minOccurs=\"0\"></element>\n" + 
			"    	</sequence>\n" + 
			"    	<attribute name=\"name\" type=\"string\"></attribute>\n" + 
			"    	<attribute name=\"returnType\" type=\"string\"></attribute>\n" + 
			"    </complexType>\n" + 
			"\n" + 
			"    <complexType name=\"resource\">\n" + 
			"    	<sequence>\n" + 
			"    		<element ref=\"tns:property\" maxOccurs=\"unbounded\" minOccurs=\"0\"></element>\n" + 
			"    		<element ref=\"tns:method\" maxOccurs=\"unbounded\" minOccurs=\"0\"></element>\n" + 
			"    	</sequence>\n" + 
			"    	<attribute name=\"name\" type=\"string\"></attribute>\n" + 
			"    </complexType>\n" + 
			"\n" + 
			"    <element name=\"method\" type=\"tns:method\"></element>\n" + 
			"\n" + 
			"\n" + 
			"    <element name=\"resource\" type=\"tns:resource\"></element>\n" + 
			"</schema>";
	
	@GET
	@Path("/ext/1")
	@Produces(MediaType.APPLICATION_XML)
	public String getResourceDefinition() {
		return resourceXsd;
	}

}
