package org.teagle.vcttool.app;

import java.util.ArrayList;
import java.util.List;

import oma.xml.fokus.pem1_input_template.PolicyIdentifiers;
import oma.xml.fokus.soap_pem1_input_template.Event;
import oma.xml.fokus.soap_pem1_input_template.EventParameter;
import oma.xml.fokus.soap_pem1_input_template.FOKUSSOAPInputTemplateType;
import teagle.vct.model.Connection;
import teagle.vct.model.ModelManager;
import teagle.vct.model.Ptm;
import teagle.vct.model.ResourceInstance;
import teagle.vct.model.ResourceSpec;
import teagle.vct.model.Vct;
import de.fhg.fokus.ngni.openpe.pem1.EvaluationHandler;
import de.fhg.fokus.ngni.openpe.pem1.PolicyEvaluation;
import de.fhg.fokus.ngni.openpe.pem1.PolicyEvaluationFactory;
/**
 * Validates the usage of the VCT Tool against policies defined at the Policy Engine side. Validation is performed when :<br/>
 * 	<ul>
 * 	<li>user selects resources from the left menu (operation "bookResource")</li>
 *  <li>interconnects two resources on the tool canvas (operation "connectResources")</li>
 *  <li>is booking a VCT (operation "bookVCT")</li>
 *  </ul>
 * @author ibo irina.c.boldea@tu-berlin.de
 */

/*
 *   Please adapt the PE_Endpoint and PE_FIRE_TEAGLE according to be following instructions:
 *   1. In case the PE_FIRE_TEAGLE is true it will fetch the WSDL from http://www.fire-teagle.org/wsdl/OMA-SUP-WSDL-PEM_1_REQ-V1_0-20071121-D.wsdl. 
 *      This solution overcomes the FOKUS proxy. If you want to change this url the openpe.pem1 needs to be recompiled.
 *   2. Otherwise the explicit configured endpoint will be used. 
 */
public class ValidateActions {
	
	private static String userName;
	
	private static VctToolApp vctToolApp;
	 
	private static String PE_Endpoint = null; 
	
	private static boolean PE_FIRE_TEAGLE = false;
	
	public static void init(String username, String pe_endpoint, VctToolApp vctToolApp0)
	{	
		userName = username;
		vctToolApp = vctToolApp0;
		if(pe_endpoint.contains("www.fire-teagle.org"))
			PE_FIRE_TEAGLE = true;
		else{
			PE_Endpoint = pe_endpoint;
			PE_FIRE_TEAGLE = false;
		}
	}
	
	public static VctToolApp getVctToolApp()
	{
		return vctToolApp;
	}

	public static boolean validateResourceUsage(ResourceSpec resourceSpec, String commonName,EvaluationHandler handler)
	{	
		if (PE_Endpoint == null || PE_Endpoint.equals(""))
			return true;
		
			PolicyEvaluation polev = newPolicyEvaluationClient();
			FOKUSSOAPInputTemplateType inputTemplate = generateResourceSelectionTemplate(polev, resourceSpec, "bookResource", 0, commonName);		
			return polev.evaluate(inputTemplate, handler);
	}

	public static boolean validateConnection(ResourceInstance src, ResourceInstance dst, Connection.Type availableConnectionType, EvaluationHandler handler)
	{		
		if (PE_Endpoint == null || PE_Endpoint.equals(""))
			return true;
		PolicyEvaluation polev = newPolicyEvaluationClient();
		FOKUSSOAPInputTemplateType inputTemplate = generateConnectionTemplate(polev, src, dst, 0, availableConnectionType.toString(), "connectResources");
		return polev.evaluate(inputTemplate, handler);
	}

	public static boolean validateConnection(ResourceInstance src, ResourceInstance dst, String availableConnectionType, EvaluationHandler handler)
	{	
		if (PE_Endpoint == null || PE_Endpoint.equals(""))
			return true;
		PolicyEvaluation polev = newPolicyEvaluationClient();
		FOKUSSOAPInputTemplateType inputTemplate = generateConnectionTemplate(polev, src, dst, 0, availableConnectionType, "connectResources");
		return polev.evaluate(inputTemplate, handler);
	}
	
	public static boolean validateVCT(Vct vct, EvaluationHandler handler) 
	{
		if (PE_Endpoint == null || PE_Endpoint.equals(""))
			return true;
		PolicyEvaluation polev = newPolicyEvaluationClient();
		
		FOKUSSOAPInputTemplateType template = polev.createRequestInputTemplate();		
		template.setId("0");		

		Event event = new Event();
		event.setName("bookVct");
		template.setEvent(event);

		List<EventParameter> opList = event.getEventParameter();
		EventParameter param = new EventParameter();
		opList.add(param);
		param.setName("user"); param.setValue(userName);
		List<String> targets = new ArrayList<String>();
		
		List<? extends ResourceInstance> resourceInstanceList = vct.getResourceInstances();
		for (ResourceInstance resInst: resourceInstanceList)
		{
			ResourceSpec resource = resInst.getResourceSpec();

			param = new EventParameter();
			opList.add(param);
			param.setName("resource/provider"); param.setValue(resource.getProvider());

			param = new EventParameter();
			opList.add(param);
			param.setName("resource/type"); param.setValue(resource.getType());

			param = new EventParameter();
			opList.add(param);
			param.setName("resource/price"); param.setValue("100");
			
			param = new EventParameter();
			opList.add(param);
			param.setName("resource/ptm"); param.setValue(getPTM(resInst.getCommonName()));
			
			if(!targets.contains(resource.getType()))
				targets.add(resource.getType());			
		}		
		
		PolicyIdentifiers polids = new PolicyIdentifiers();
		polids.setOriginatorID(userName);
		polids.setOriginatorIDType("user");		
		polids.getTargetID().addAll(targets);
		polids.setTargetIDType("resource");
		template.setPolicyIdentifiers(polids);	
		
		return polev.evaluate(template, handler);				
	}
	
	
	private static PolicyEvaluation newPolicyEvaluationClient()
	{
		PolicyEvaluation polev = null;
		if(PE_FIRE_TEAGLE)
			polev = new PolicyEvaluationFactory().createRequestPolicyEvaluation();		
		else
			polev = new PolicyEvaluationFactory(PE_Endpoint).createRequestPolicyEvaluation();
		return polev;
	}
	
	private static String getPTM(String commonName)
	{	
		String ptmName = null;
		int pos = commonName.indexOf('.');
		if (pos > 0) {
			ptmName = commonName.substring(0, pos);
		}
		
		if (ptmName != null) {
			List<? extends Ptm> ptms = ModelManager.getInstance().listPtms();
			for (Ptm ptm : ptms) {
				if (ptm.getCommonName().equals(ptmName)) {
					return ptmName;
				}
			}
		}	
		return "";
	}
	

	private static FOKUSSOAPInputTemplateType generateResourceSelectionTemplate(PolicyEvaluation polev, ResourceSpec resource, String operationName, int id, String commonName)
	{
		FOKUSSOAPInputTemplateType template = polev.createRequestInputTemplate();		
		template.setId((new Integer(id)).toString());		

		PolicyIdentifiers polids = new PolicyIdentifiers();
		polids.setOriginatorID(userName);
		polids.setOriginatorIDType("user");
		polids.getTargetID().add(resource.getCommonName());
		polids.setTargetIDType("resource");
		template.setPolicyIdentifiers(polids);	

		Event event = new Event();
		event.setName(operationName);
		template.setEvent(event);
		
		List<EventParameter> opList = event.getEventParameter();

		EventParameter param = new EventParameter();
		opList.add(param);
		param.setName("user"); param.setValue(userName);

		param = new EventParameter();
		opList.add(param);
		param.setName("resource/provider"); param.setValue(resource.getProvider());

		param = new EventParameter();
		opList.add(param);
		param.setName("resource/type"); param.setValue(resource.getType());

		param = new EventParameter();
		opList.add(param);
		param.setName("resource/price"); param.setValue("100");
		
		param = new EventParameter();
		opList.add(param);
		param.setName("resource/ptm"); param.setValue(getPTM(commonName));

		return template;
	}

	private static FOKUSSOAPInputTemplateType generateConnectionTemplate(PolicyEvaluation polev, ResourceInstance sinstance, ResourceInstance dinstance, int id, String connectionType,  String operationName)
	{
		FOKUSSOAPInputTemplateType template = polev.createRequestInputTemplate();		
		template.setId((new Integer(id)).toString());		

		ResourceSpec src = sinstance.getResourceSpec();
		ResourceSpec dst = dinstance.getResourceSpec();
		
		PolicyIdentifiers polids = new PolicyIdentifiers();
		polids.setOriginatorID(src.getType());
		polids.setOriginatorIDType("resource");
		polids.getTargetID().add(dst.getType());
		polids.setTargetIDType("resource");		
		template.setPolicyIdentifiers(polids);	
		
		Event event = new Event();
		event.setName(operationName);
		template.setEvent(event);

		List<EventParameter> opList = event.getEventParameter();

		EventParameter param = new EventParameter();
		opList.add(param);
		param.setName("user"); param.setValue(userName);

		param = new EventParameter();
		opList.add(param);
		param.setName("connectionType"); param.setValue(connectionType);

		param = new EventParameter();
		opList.add(param);
		param.setName("source/resource/type"); param.setValue(src.getType());

		param = new EventParameter();
		opList.add(param);
		param.setName("source/resource/provider"); param.setValue(src.getProvider());

		param = new EventParameter();
		opList.add(param);
		param.setName("source/resource/ptm"); param.setValue(getPTM(sinstance.getCommonName()));
		
		param = new EventParameter();
		opList.add(param);
		param.setName("destination/resource/type"); param.setValue(dst.getType());

		param = new EventParameter();
		opList.add(param);
		param.setName("destination/resource/provider"); param.setValue(dst.getProvider());
		
		param = new EventParameter();
		opList.add(param);
		param.setName("destination/resource/ptm"); param.setValue(getPTM(dinstance.getCommonName()));

		return template;
	}


	
}
