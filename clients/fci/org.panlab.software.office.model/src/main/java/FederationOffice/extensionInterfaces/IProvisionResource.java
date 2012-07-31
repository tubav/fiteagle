/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.extensionInterfaces;

import org.eclipse.emf.ecore.EObject;

import FederationOffice.federationscenarios.RequestedFederationScenario;
import FederationOffice.federationscenarios.ResourceRequest;
import FederationOffice.federationscenarios.ResourceSettingInstance;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IProvision Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see FederationOffice.extensionInterfaces.ExtensionInterfacesPackage#getIProvisionResource()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IProvisionResource extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String provisionResource(String officeName, RequestedFederationScenario scenario, ResourceRequest resourceReq);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean supportsOffice(String officeName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getSupportedOfficeName();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String deleteResource(String officeName, RequestedFederationScenario scenario, ResourceRequest resourceReq);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String updateResource(String officeName, RequestedFederationScenario scenario, ResourceRequest resourceReq, ResourceSettingInstance assignedSetting);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String readResource(String officeName, RequestedFederationScenario scenario, ResourceRequest resourceReq, ResourceSettingInstance assignedSetting, boolean forceUpdate);

} // IProvisionResource
