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
 * A representation of the model object '<em><b>tide Type Tide Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.services.tideTypeTideElement#getOfTideElement <em>Of Tide Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.services.ServicesPackage#gettideTypeTideElement()
 * @model
 * @generated
 */
public interface tideTypeTideElement extends SettingType {
	/**
	 * Returns the value of the '<em><b>Of Tide Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Of Tide Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Of Tide Element</em>' reference.
	 * @see #setOfTideElement(NamedElement)
	 * @see FederationOffice.services.ServicesPackage#gettideTypeTideElement_OfTideElement()
	 * @model
	 * @generated
	 */
	NamedElement getOfTideElement();

	/**
	 * Sets the value of the '{@link FederationOffice.services.tideTypeTideElement#getOfTideElement <em>Of Tide Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Of Tide Element</em>' reference.
	 * @see #getOfTideElement()
	 * @generated
	 */
	void setOfTideElement(NamedElement value);

} // tideTypeTideElement
