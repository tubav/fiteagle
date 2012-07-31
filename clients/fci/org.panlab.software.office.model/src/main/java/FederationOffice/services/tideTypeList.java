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
 * A representation of the model object '<em><b>tide Type List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.services.tideTypeList#getContainsElementsOf <em>Contains Elements Of</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.services.ServicesPackage#gettideTypeList()
 * @model
 * @generated
 */
public interface tideTypeList extends SettingType {
	/**
	 * Returns the value of the '<em><b>Contains Elements Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contains Elements Of</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contains Elements Of</em>' reference.
	 * @see #setContainsElementsOf(NamedElement)
	 * @see FederationOffice.services.ServicesPackage#gettideTypeList_ContainsElementsOf()
	 * @model
	 * @generated
	 */
	NamedElement getContainsElementsOf();

	/**
	 * Sets the value of the '{@link FederationOffice.services.tideTypeList#getContainsElementsOf <em>Contains Elements Of</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contains Elements Of</em>' reference.
	 * @see #getContainsElementsOf()
	 * @generated
	 */
	void setContainsElementsOf(NamedElement value);

} // tideTypeList
