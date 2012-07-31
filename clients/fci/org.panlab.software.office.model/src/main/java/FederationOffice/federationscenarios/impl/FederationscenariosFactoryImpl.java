/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.federationscenarios.impl;

import FederationOffice.federationscenarios.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import FederationOffice.federationscenarios.Credentials;
import FederationOffice.federationscenarios.FederationscenariosFactory;
import FederationOffice.federationscenarios.FederationscenariosPackage;
import FederationOffice.federationscenarios.Import;
import FederationOffice.federationscenarios.InfrastructureRequest;
import FederationOffice.federationscenarios.RequestedFederationScenario;
import FederationOffice.federationscenarios.ResourceGroup;
import FederationOffice.federationscenarios.ResourceRequest;
import FederationOffice.federationscenarios.ResourceSettingInstance;
import FederationOffice.federationscenarios.ScheduledPlan;
import FederationOffice.federationscenarios.ServiceRequest;
import FederationOffice.federationscenarios.ServiceSettingInstance;
import FederationOffice.federationscenarios.ServicesRequest;
import FederationOffice.federationscenarios.SettingInstance;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FederationscenariosFactoryImpl extends EFactoryImpl implements FederationscenariosFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FederationscenariosFactory init() {
		try {
			FederationscenariosFactory theFederationscenariosFactory = (FederationscenariosFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.panlab.org/FederationOffice/model/federationscenarios"); 
			if (theFederationscenariosFactory != null) {
				return theFederationscenariosFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new FederationscenariosFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FederationscenariosFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO: return createRequestedFederationScenario();
			case FederationscenariosPackage.SERVICE_REQUEST: return createServiceRequest();
			case FederationscenariosPackage.RESOURCE_REQUEST: return createResourceRequest();
			case FederationscenariosPackage.SETTING_INSTANCE: return createSettingInstance();
			case FederationscenariosPackage.SERVICE_SETTING_INSTANCE: return createServiceSettingInstance();
			case FederationscenariosPackage.RESOURCE_SETTING_INSTANCE: return createResourceSettingInstance();
			case FederationscenariosPackage.CREDENTIALS: return createCredentials();
			case FederationscenariosPackage.SERVICES_REQUEST: return createServicesRequest();
			case FederationscenariosPackage.INFRASTRUCTURE_REQUEST: return createInfrastructureRequest();
			case FederationscenariosPackage.SCHEDULED_PLAN: return createScheduledPlan();
			case FederationscenariosPackage.IMPORT: return createImport();
			case FederationscenariosPackage.RESOURCE_GROUP: return createResourceGroup();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequestedFederationScenario createRequestedFederationScenario() {
		RequestedFederationScenarioImpl requestedFederationScenario = new RequestedFederationScenarioImpl();
		return requestedFederationScenario;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceRequest createServiceRequest() {
		ServiceRequestImpl serviceRequest = new ServiceRequestImpl();
		return serviceRequest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceRequest createResourceRequest() {
		ResourceRequestImpl resourceRequest = new ResourceRequestImpl();
		return resourceRequest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SettingInstance createSettingInstance() {
		SettingInstanceImpl settingInstance = new SettingInstanceImpl();
		return settingInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceSettingInstance createServiceSettingInstance() {
		ServiceSettingInstanceImpl serviceSettingInstance = new ServiceSettingInstanceImpl();
		return serviceSettingInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceSettingInstance createResourceSettingInstance() {
		ResourceSettingInstanceImpl resourceSettingInstance = new ResourceSettingInstanceImpl();
		return resourceSettingInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Credentials createCredentials() {
		CredentialsImpl credentials = new CredentialsImpl();
		return credentials;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServicesRequest createServicesRequest() {
		ServicesRequestImpl servicesRequest = new ServicesRequestImpl();
		return servicesRequest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InfrastructureRequest createInfrastructureRequest() {
		InfrastructureRequestImpl infrastructureRequest = new InfrastructureRequestImpl();
		return infrastructureRequest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScheduledPlan createScheduledPlan() {
		ScheduledPlanImpl scheduledPlan = new ScheduledPlanImpl();
		return scheduledPlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Import createImport() {
		ImportImpl import_ = new ImportImpl();
		return import_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceGroup createResourceGroup() {
		ResourceGroupImpl resourceGroup = new ResourceGroupImpl();
		return resourceGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FederationscenariosPackage getFederationscenariosPackage() {
		return (FederationscenariosPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static FederationscenariosPackage getPackage() {
		return FederationscenariosPackage.eINSTANCE;
	}

} //FederationscenariosFactoryImpl
