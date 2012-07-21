package teagle.reqprocessor.vct.validate;


public class ValidateActions {

	private String PE_Endpoint = null; 
	
	private boolean PE_FIRE_TEAGLE = false;
	
	public ValidateActions (String pe_endpoint){
		PE_Endpoint = pe_endpoint;
		if(pe_endpoint != null && pe_endpoint.contains("www.fire-teagle.org"))
			PE_FIRE_TEAGLE = true;
		else{
			PE_Endpoint = pe_endpoint;
			PE_FIRE_TEAGLE = false;		
		}
	}
/*	
	
	
	public  boolean validateVCT(String userName, VCT vct,
			EvaluationHandler handler, TSSGResourceInstanceManager ptmManager, TSSGResourceInstanceManager rimanager) throws RepositoryException {
		if (PE_Endpoint == null || PE_Endpoint.equals(""))
			return true;
		return evaluateResourcesBooking(userName, vct, handler, ptmManager) && 
		evaluateResourcesConnection(userName, vct, handler, ptmManager, rimanager) && 
		evaluatebookVCT(userName, vct, handler, ptmManager, rimanager);
	}

	private  boolean evaluateResourcesBooking(String userName, VCT vct,
			EvaluationHandler handler, TSSGResourceInstanceManager ptmManager) {
		if (PE_Endpoint == null || PE_Endpoint.equals(""))
			return true;
		PolicyEvaluation polev = newPolicyEvaluationClient();
		Map<Integer, FOKUSSOAPInputTemplateType> map = new HashMap<Integer, FOKUSSOAPInputTemplateType>();

		ResourceInstance[] riList = vct.getResourceInstances();

		for (int i = 0; i < riList.length; i++) {
			ResourceInstance ri = riList[i];
			Resource resource = ri.getType();

			FOKUSSOAPInputTemplateType template = polev
					.createRequestInputTemplate();
			template.setId((new Integer(i)).toString());
			map.put(i, template);

			PolicyIdentifiers polids = new PolicyIdentifiers();
			polids.setOriginatorID(userName);
			polids.setOriginatorIDType("user");
			polids.getTargetID().add(resource.getName());
			polids.setTargetIDType("resource");
			template.setPolicyIdentifiers(polids);

			Event event = new Event();
			event.setName("bookResource");
			template.setEvent(event);

			List<EventParameter> opList = event.getEventParameter();

			EventParameter param = new EventParameter();
			opList.add(param);
			param.setName("user");
			param.setValue(userName);

			param = new EventParameter();
			opList.add(param);
			param.setName("resource/owner");
			param.setValue(resource.getOwner());

			param = new EventParameter();
			opList.add(param);
			param.setName("resource/provider");
			param.setValue(resource.getProvider());

			param = new EventParameter();
			opList.add(param);
			param.setName("resource/type");
			param.setValue(ri.getTypename());

			param = new EventParameter();
			opList.add(param);
			param.setName("resource/price");
			param.setValue(new Double(resource.getPrice()).toString());

			param = new EventParameter();
			opList.add(param);
			param.setName("resource/ptm");
			param.setValue(getPTM(ri, ptmManager));
			polev.addInputTemplate(template);
		}

		boolean res = polev.evaluate(handler);
		handler.setMessage(genResult(polev.getPolicyOutputData(), map));
		return res;
	}

	private  boolean evaluateResourcesConnection(String userName, VCT vct,
			EvaluationHandler handler, TSSGResourceInstanceManager ptmManager,
			TSSGResourceInstanceManager rimanager) throws RepositoryException {
		if (PE_Endpoint == null || PE_Endpoint.equals(""))
			return true;
		PolicyEvaluation polev = newPolicyEvaluationClient();
		Map<Integer, FOKUSSOAPInputTemplateType> map = new HashMap<Integer, FOKUSSOAPInputTemplateType>();

		ResourceInstance[] instances =  vct.getResourceInstances();
		for (int i = 0; i < instances.length; ++i )
		{
			ResourceInstance dri = instances[i];
			ResourceInstance sri = dri.getParentInstance();
			if (sri == null)
				continue;
			Resource src = sri.getType();
			Resource dst = dri.getType();

			FOKUSSOAPInputTemplateType template = polev
					.createRequestInputTemplate();
			template.setId(new Integer(i).toString());
			polev.addInputTemplate(template);
			map.put(i, template);

			PolicyIdentifiers polids = new PolicyIdentifiers();
			polids.setOriginatorID(src.getType());
			polids.setOriginatorIDType("resource");
			polids.getTargetID().add(dst.getType());
			polids.setTargetIDType("resource");
			template.setPolicyIdentifiers(polids);

			Event event = new Event();
			event.setName("connectResources");
			template.setEvent(event);

			List<EventParameter> opList = event.getEventParameter();

			EventParameter param = new EventParameter();
			opList.add(param);
			param.setName("user");
			param.setValue(userName);

			param = new EventParameter();
			opList.add(param);
			param.setName("connectionType");
			param.setValue("contains");

			param = new EventParameter();
			opList.add(param);
			param.setName("source/resource/type");
			param.setValue(src.getType());

			param = new EventParameter();
			opList.add(param);
			param.setName("source/resource/owner");
			param.setValue(src.getOwner());

			param = new EventParameter();
			opList.add(param);
			param.setName("source/resource/provider");
			param.setValue(src.getProvider());

			param = new EventParameter();
			opList.add(param);
			param.setName("source/resource/ptm");
			param.setValue(getPTM(sri, ptmManager));

			param = new EventParameter();
			opList.add(param);
			param.setName("destination/resource/type");
			param.setValue(dst.getType());

			param = new EventParameter();
			opList.add(param);
			param.setName("destination/resource/provider");
			param.setValue(dst.getProvider());

			param = new EventParameter();
			opList.add(param);
			param.setName("destination/resource/owner");
			param.setValue(dst.getOwner());

			param = new EventParameter();
			opList.add(param);
			param.setName("destination/resource/ptm");
			param.setValue(getPTM(sri, ptmManager));

		}

		boolean res = polev.evaluate(handler);
		handler.setMessage(genResult(polev.getPolicyOutputData(), map));
		return res;
	}

	private  boolean evaluatebookVCT(String userName, VCT vct,
			EvaluationHandler handler, TSSGResourceInstanceManager ptmManager,
			TSSGResourceInstanceManager rimanager) {
		if (PE_Endpoint == null || PE_Endpoint.equals(""))
			return true;
		PolicyEvaluation polev = newPolicyEvaluationClient();
		FOKUSSOAPInputTemplateType template = polev
				.createRequestInputTemplate();
		template.setId("0");

		Event event = new Event();
		event.setName("bookVct");
		template.setEvent(event);

		List<EventParameter> opList = event.getEventParameter();
		EventParameter param = new EventParameter();
		opList.add(param);
		param.setName("user");
		param.setValue(userName);
		List<String> targets = new ArrayList<String>();

		ResourceInstance[] riList = vct.getResourceInstances();

		for (ResourceInstance ri : riList) {
			Resource resource = ri.getType();

			param = new EventParameter();
			opList.add(param);
			param.setName("resource/owner");
			param.setValue(resource.getOwner());

			param = new EventParameter();
			opList.add(param);
			param.setName("resource/provider");
			param.setValue(resource.getProvider());

			param = new EventParameter();
			opList.add(param);
			param.setName("resource/type");
			param.setValue(resource.getType());

			param = new EventParameter();
			opList.add(param);
			param.setName("resource/price");
			param.setValue(new Double(resource.getPrice()).toString());

			param = new EventParameter();
			opList.add(param);
			param.setName("resource/ptm");
			param.setValue(getPTM(ri, ptmManager));

			if (!targets.contains(resource.getType()))
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

	
	private PolicyEvaluation newPolicyEvaluationClient()
	{
		if (PE_Endpoint == null || PE_Endpoint.equals(""))
			return null;
		PolicyEvaluation polev = null;
		if(PE_FIRE_TEAGLE)
			polev = new PolicyEvaluationFactory().createRequestPolicyEvaluation();		
		else
			polev = new PolicyEvaluationFactory(PE_Endpoint).createRequestPolicyEvaluation();
		return polev;
	}
	
	private  String getPTM(ResourceInstance ri, TSSGResourceInstanceManager ptmmanager) {
		try {
			String ptm = ptmmanager.getPTMByResource(ri.getType()).name;
			return ptm == null ? "" : ptm;
		} catch (RepositoryException e) {
			return "";
		}
	}

	private  String genResult(PolicyOutputData pod,
			Map<Integer, FOKUSSOAPInputTemplateType> map) {
		List<OutputTemplateType> ottList = pod.getPolicyOutputTemplate();

		for (OutputTemplateType outputTemplate : ottList) {
			FOKUSOutputTemplateType ot = (FOKUSOutputTemplateType) outputTemplate;

			switch (ot.getStatusCode()) {
			case 2401:
				int index = Integer.parseInt(ot.getId());
				FOKUSSOAPInputTemplateType template = map.get(index);
				if (template == null)
					return "Errors while retrieving info";
				StringBuffer res = new StringBuffer();
				PolicyIdentifiers pi = template.getPolicyIdentifiers();
				res.append("O: " + pi.getOriginatorIDType() + "/"
						+ pi.getOriginatorID());
				res.append(";");

				res.append("T: " + pi.getTargetIDType() + "/");
				for (String target : pi.getTargetID()) {
					res.append(target);
					res.append(",");
				}
				res.append(";");
				res.append("Op:" + template.getEvent().getName());
				res.append("</br>");
				res.append(getEvaluationInformation(ot.getEnforcementData()));
				res.append("</br></br>");
				return res.toString();
			case 2101:
				break;
			default:
			}
		}
		return "";
	}

	private  String getEvaluationInformation(EnforcementData enfD) {
		if (enfD != null) {
			List<EnforcementAction> lst = enfD.getEnforcementAction();
			if (lst.size() != 0) {
				String actionDescription = lst.get(0)
						.getEnforcementActionDescription();
				if (actionDescription != null) {
					return actionDescription;
				}
			}
		}
		return "No further details available";
	}
*/
}
