/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.users;

import org.eclipse.emf.common.util.EList;

import FederationOffice.federationscenarios.RequestedFederationScenario;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Testbed Designer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.users.TestbedDesigner#getDesignsVirtualTestbeds <em>Designs Virtual Testbeds</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.users.UsersPackage#getTestbedDesigner()
 * @model
 * @generated
 */
public interface TestbedDesigner extends OfficeUser {
	/**
	 * Returns the value of the '<em><b>Designs Virtual Testbeds</b></em>' reference list.
	 * The list contents are of type {@link FederationOffice.federationscenarios.RequestedFederationScenario}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Designs Virtual Testbeds</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Designs Virtual Testbeds</em>' reference list.
	 * @see FederationOffice.users.UsersPackage#getTestbedDesigner_DesignsVirtualTestbeds()
	 * @model
	 * @generated
	 */
	EList<RequestedFederationScenario> getDesignsVirtualTestbeds();

} // TestbedDesigner
