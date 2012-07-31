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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IProvisioning Job Event</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see FederationOffice.extensionInterfaces.ExtensionInterfacesPackage#getIProvisioningJobEvent()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IProvisioningJobEvent extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	RequestedFederationScenario statusChangedOnScenario();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ResourceRequest statusChangedOnResource();

} // IProvisioningJobEvent
