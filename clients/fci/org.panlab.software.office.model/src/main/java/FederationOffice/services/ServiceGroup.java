/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.services;

import FederationOffice.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.services.ServiceGroup#getNum <em>Num</em>}</li>
 *   <li>{@link FederationOffice.services.ServiceGroup#getRefService <em>Ref Service</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.services.ServicesPackage#getServiceGroup()
 * @model
 * @generated
 */
public interface ServiceGroup extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Num</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Num</em>' attribute.
	 * @see #setNum(int)
	 * @see FederationOffice.services.ServicesPackage#getServiceGroup_Num()
	 * @model
	 * @generated
	 */
	int getNum();

	/**
	 * Sets the value of the '{@link FederationOffice.services.ServiceGroup#getNum <em>Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Num</em>' attribute.
	 * @see #getNum()
	 * @generated
	 */
	void setNum(int value);

	/**
	 * Returns the value of the '<em><b>Ref Service</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ref Service</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ref Service</em>' reference.
	 * @see #setRefService(OfferedService)
	 * @see FederationOffice.services.ServicesPackage#getServiceGroup_RefService()
	 * @model required="true"
	 * @generated
	 */
	OfferedService getRefService();

	/**
	 * Sets the value of the '{@link FederationOffice.services.ServiceGroup#getRefService <em>Ref Service</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ref Service</em>' reference.
	 * @see #getRefService()
	 * @generated
	 */
	void setRefService(OfferedService value);

} // ServiceGroup
