/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.federationscenarios.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
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
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see FederationOffice.federationscenarios.FederationscenariosPackage
 * @generated
 */
public class FederationscenariosSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static FederationscenariosPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FederationscenariosSwitch() {
		if (modelPackage == null) {
			modelPackage = FederationscenariosPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO: {
				RequestedFederationScenario requestedFederationScenario = (RequestedFederationScenario)theEObject;
				T result = caseRequestedFederationScenario(requestedFederationScenario);
				if (result == null) result = caseNamedElement(requestedFederationScenario);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FederationscenariosPackage.SERVICE_REQUEST: {
				ServiceRequest serviceRequest = (ServiceRequest)theEObject;
				T result = caseServiceRequest(serviceRequest);
				if (result == null) result = caseNamedElement(serviceRequest);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FederationscenariosPackage.RESOURCE_REQUEST: {
				ResourceRequest resourceRequest = (ResourceRequest)theEObject;
				T result = caseResourceRequest(resourceRequest);
				if (result == null) result = caseNamedElement(resourceRequest);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FederationscenariosPackage.SETTING_INSTANCE: {
				SettingInstance settingInstance = (SettingInstance)theEObject;
				T result = caseSettingInstance(settingInstance);
				if (result == null) result = caseNamedElement(settingInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FederationscenariosPackage.SERVICE_SETTING_INSTANCE: {
				ServiceSettingInstance serviceSettingInstance = (ServiceSettingInstance)theEObject;
				T result = caseServiceSettingInstance(serviceSettingInstance);
				if (result == null) result = caseSettingInstance(serviceSettingInstance);
				if (result == null) result = caseNamedElement(serviceSettingInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FederationscenariosPackage.RESOURCE_SETTING_INSTANCE: {
				ResourceSettingInstance resourceSettingInstance = (ResourceSettingInstance)theEObject;
				T result = caseResourceSettingInstance(resourceSettingInstance);
				if (result == null) result = caseSettingInstance(resourceSettingInstance);
				if (result == null) result = caseNamedElement(resourceSettingInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FederationscenariosPackage.CREDENTIALS: {
				Credentials credentials = (Credentials)theEObject;
				T result = caseCredentials(credentials);
				if (result == null) result = caseNamedElement(credentials);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FederationscenariosPackage.SERVICES_REQUEST: {
				ServicesRequest servicesRequest = (ServicesRequest)theEObject;
				T result = caseServicesRequest(servicesRequest);
				if (result == null) result = caseNamedElement(servicesRequest);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FederationscenariosPackage.INFRASTRUCTURE_REQUEST: {
				InfrastructureRequest infrastructureRequest = (InfrastructureRequest)theEObject;
				T result = caseInfrastructureRequest(infrastructureRequest);
				if (result == null) result = caseNamedElement(infrastructureRequest);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FederationscenariosPackage.SCHEDULED_PLAN: {
				ScheduledPlan scheduledPlan = (ScheduledPlan)theEObject;
				T result = caseScheduledPlan(scheduledPlan);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FederationscenariosPackage.IMPORT: {
				Import import_ = (Import)theEObject;
				T result = caseImport(import_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FederationscenariosPackage.RESOURCE_GROUP: {
				ResourceGroup resourceGroup = (ResourceGroup)theEObject;
				T result = caseResourceGroup(resourceGroup);
				if (result == null) result = caseNamedElement(resourceGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Requested Federation Scenario</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Requested Federation Scenario</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRequestedFederationScenario(RequestedFederationScenario object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service Request</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service Request</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseServiceRequest(ServiceRequest object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Request</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Request</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceRequest(ResourceRequest object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Setting Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Setting Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSettingInstance(SettingInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service Setting Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service Setting Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseServiceSettingInstance(ServiceSettingInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Setting Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Setting Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceSettingInstance(ResourceSettingInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Credentials</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Credentials</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCredentials(Credentials object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Services Request</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Services Request</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseServicesRequest(ServicesRequest object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Infrastructure Request</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Infrastructure Request</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInfrastructureRequest(InfrastructureRequest object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scheduled Plan</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scheduled Plan</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScheduledPlan(ScheduledPlan object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Import</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Import</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImport(Import object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceGroup(ResourceGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //FederationscenariosSwitch
