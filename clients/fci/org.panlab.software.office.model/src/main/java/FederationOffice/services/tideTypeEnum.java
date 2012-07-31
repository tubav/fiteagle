/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.services;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>tide Type Enum</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.services.tideTypeEnum#getTideEnumlist <em>Tide Enumlist</em>}</li>
 *   <li>{@link FederationOffice.services.tideTypeEnum#getDefaultValue <em>Default Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.services.ServicesPackage#gettideTypeEnum()
 * @model
 * @generated
 */
public interface tideTypeEnum extends SettingType {
	/**
	 * Returns the value of the '<em><b>Tide Enumlist</b></em>' containment reference list.
	 * The list contents are of type {@link FederationOffice.services.tideTypeEnumItem}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tide Enumlist</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tide Enumlist</em>' containment reference list.
	 * @see FederationOffice.services.ServicesPackage#gettideTypeEnum_TideEnumlist()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<tideTypeEnumItem> getTideEnumlist();

	/**
	 * Returns the value of the '<em><b>Default Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Value</em>' reference.
	 * @see #setDefaultValue(tideTypeEnumItem)
	 * @see FederationOffice.services.ServicesPackage#gettideTypeEnum_DefaultValue()
	 * @model
	 * @generated
	 */
	tideTypeEnumItem getDefaultValue();

	/**
	 * Sets the value of the '{@link FederationOffice.services.tideTypeEnum#getDefaultValue <em>Default Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Value</em>' reference.
	 * @see #getDefaultValue()
	 * @generated
	 */
	void setDefaultValue(tideTypeEnumItem value);

} // tideTypeEnum
