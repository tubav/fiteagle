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
 * A representation of the model object '<em><b>Office Customer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.users.OfficeCustomer#getUtilizesVirtualTestbed <em>Utilizes Virtual Testbed</em>}</li>
 *   <li>{@link FederationOffice.users.OfficeCustomer#getHasTestbedDesigner <em>Has Testbed Designer</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.users.UsersPackage#getOfficeCustomer()
 * @model
 * @generated
 */
public interface OfficeCustomer extends OfficeUser {
	/**
	 * Returns the value of the '<em><b>Utilizes Virtual Testbed</b></em>' reference list.
	 * The list contents are of type {@link FederationOffice.federationscenarios.RequestedFederationScenario}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Utilizes Virtual Testbed</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Utilizes Virtual Testbed</em>' reference list.
	 * @see FederationOffice.users.UsersPackage#getOfficeCustomer_UtilizesVirtualTestbed()
	 * @model
	 * @generated
	 */
	EList<RequestedFederationScenario> getUtilizesVirtualTestbed();

	/**
	 * Returns the value of the '<em><b>Has Testbed Designer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Testbed Designer</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Testbed Designer</em>' reference.
	 * @see #setHasTestbedDesigner(TestbedDesigner)
	 * @see FederationOffice.users.UsersPackage#getOfficeCustomer_HasTestbedDesigner()
	 * @model
	 * @generated
	 */
	TestbedDesigner getHasTestbedDesigner();

	/**
	 * Sets the value of the '{@link FederationOffice.users.OfficeCustomer#getHasTestbedDesigner <em>Has Testbed Designer</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Testbed Designer</em>' reference.
	 * @see #getHasTestbedDesigner()
	 * @generated
	 */
	void setHasTestbedDesigner(TestbedDesigner value);

} // OfficeCustomer
