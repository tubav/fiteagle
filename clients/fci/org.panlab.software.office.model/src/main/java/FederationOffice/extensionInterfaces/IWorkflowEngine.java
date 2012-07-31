/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.extensionInterfaces;

import org.eclipse.emf.ecore.EObject;

import FederationOffice.experimentRuntime.RunningScenarios;
import FederationOffice.federationscenarios.RequestedFederationScenario;
import FederationOffice.federationscenarios.ResourceRequest;
import FederationOffice.federationscenarios.ResourceSettingInstance;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IWorkflow Engine</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see FederationOffice.extensionInterfaces.ExtensionInterfacesPackage#getIWorkflowEngine()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IWorkflowEngine extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getEngineName();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	RunningScenarios getRunningScenarios();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void ScheduleScenario(RequestedFederationScenario scenario);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void ShutDownScenario(RequestedFederationScenario scenario);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void DeletePermanentlyScenario(RequestedFederationScenario scenario);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String ShutDownResource(RequestedFederationScenario scenario, ResourceRequest resourceReq);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String UpdateResource(RequestedFederationScenario scenario, ResourceRequest resourceReq, ResourceSettingInstance assignedSetting);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String ReadResource(RequestedFederationScenario scenario, ResourceRequest resourceReq, ResourceSettingInstance assignedSetting, boolean forceUpdate);

} // IWorkflowEngine
