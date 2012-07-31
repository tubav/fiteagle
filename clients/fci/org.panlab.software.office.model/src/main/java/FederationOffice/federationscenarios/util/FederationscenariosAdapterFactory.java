/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.federationscenarios.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import FederationOffice.NamedElement;
import FederationOffice.federationscenarios.*;
import FederationOffice.federationscenarios.Credentials;
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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see FederationOffice.federationscenarios.FederationscenariosPackage
 * @generated
 */
public class FederationscenariosAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static FederationscenariosPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FederationscenariosAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = FederationscenariosPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FederationscenariosSwitch<Adapter> modelSwitch =
		new FederationscenariosSwitch<Adapter>() {
			@Override
			public Adapter caseRequestedFederationScenario(RequestedFederationScenario object) {
				return createRequestedFederationScenarioAdapter();
			}
			@Override
			public Adapter caseServiceRequest(ServiceRequest object) {
				return createServiceRequestAdapter();
			}
			@Override
			public Adapter caseResourceRequest(ResourceRequest object) {
				return createResourceRequestAdapter();
			}
			@Override
			public Adapter caseSettingInstance(SettingInstance object) {
				return createSettingInstanceAdapter();
			}
			@Override
			public Adapter caseServiceSettingInstance(ServiceSettingInstance object) {
				return createServiceSettingInstanceAdapter();
			}
			@Override
			public Adapter caseResourceSettingInstance(ResourceSettingInstance object) {
				return createResourceSettingInstanceAdapter();
			}
			@Override
			public Adapter caseCredentials(Credentials object) {
				return createCredentialsAdapter();
			}
			@Override
			public Adapter caseServicesRequest(ServicesRequest object) {
				return createServicesRequestAdapter();
			}
			@Override
			public Adapter caseInfrastructureRequest(InfrastructureRequest object) {
				return createInfrastructureRequestAdapter();
			}
			@Override
			public Adapter caseScheduledPlan(ScheduledPlan object) {
				return createScheduledPlanAdapter();
			}
			@Override
			public Adapter caseImport(Import object) {
				return createImportAdapter();
			}
			@Override
			public Adapter caseResourceGroup(ResourceGroup object) {
				return createResourceGroupAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object) {
				return createNamedElementAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.federationscenarios.RequestedFederationScenario <em>Requested Federation Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.federationscenarios.RequestedFederationScenario
	 * @generated
	 */
	public Adapter createRequestedFederationScenarioAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.federationscenarios.ServiceRequest <em>Service Request</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.federationscenarios.ServiceRequest
	 * @generated
	 */
	public Adapter createServiceRequestAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.federationscenarios.ResourceRequest <em>Resource Request</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.federationscenarios.ResourceRequest
	 * @generated
	 */
	public Adapter createResourceRequestAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.federationscenarios.SettingInstance <em>Setting Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.federationscenarios.SettingInstance
	 * @generated
	 */
	public Adapter createSettingInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.federationscenarios.ServiceSettingInstance <em>Service Setting Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.federationscenarios.ServiceSettingInstance
	 * @generated
	 */
	public Adapter createServiceSettingInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.federationscenarios.ResourceSettingInstance <em>Resource Setting Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.federationscenarios.ResourceSettingInstance
	 * @generated
	 */
	public Adapter createResourceSettingInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.federationscenarios.Credentials <em>Credentials</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.federationscenarios.Credentials
	 * @generated
	 */
	public Adapter createCredentialsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.federationscenarios.ServicesRequest <em>Services Request</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.federationscenarios.ServicesRequest
	 * @generated
	 */
	public Adapter createServicesRequestAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.federationscenarios.InfrastructureRequest <em>Infrastructure Request</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.federationscenarios.InfrastructureRequest
	 * @generated
	 */
	public Adapter createInfrastructureRequestAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.federationscenarios.ScheduledPlan <em>Scheduled Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.federationscenarios.ScheduledPlan
	 * @generated
	 */
	public Adapter createScheduledPlanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.federationscenarios.Import <em>Import</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.federationscenarios.Import
	 * @generated
	 */
	public Adapter createImportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.federationscenarios.ResourceGroup <em>Resource Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.federationscenarios.ResourceGroup
	 * @generated
	 */
	public Adapter createResourceGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link FederationOffice.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see FederationOffice.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //FederationscenariosAdapterFactory
