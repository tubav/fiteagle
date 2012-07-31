/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.services;

import org.eclipse.emf.common.util.EList;

import FederationOffice.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Setting</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.services.AbstractSetting#getSettingType <em>Setting Type</em>}</li>
 *   <li>{@link FederationOffice.services.AbstractSetting#isUserExposed <em>User Exposed</em>}</li>
 *   <li>{@link FederationOffice.services.AbstractSetting#isUserEditable <em>User Editable</em>}</li>
 *   <li>{@link FederationOffice.services.AbstractSetting#isCanBePublished <em>Can Be Published</em>}</li>
 *   <li>{@link FederationOffice.services.AbstractSetting#isReadable <em>Readable</em>}</li>
 *   <li>{@link FederationOffice.services.AbstractSetting#isWritable <em>Writable</em>}</li>
 *   <li>{@link FederationOffice.services.AbstractSetting#getSettingConstraints <em>Setting Constraints</em>}</li>
 *   <li>{@link FederationOffice.services.AbstractSetting#getRequiresParams <em>Requires Params</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.services.ServicesPackage#getAbstractSetting()
 * @model
 * @generated
 */
public interface AbstractSetting extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Setting Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Setting Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Setting Type</em>' containment reference.
	 * @see #setSettingType(SettingType)
	 * @see FederationOffice.services.ServicesPackage#getAbstractSetting_SettingType()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	SettingType getSettingType();

	/**
	 * Sets the value of the '{@link FederationOffice.services.AbstractSetting#getSettingType <em>Setting Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Setting Type</em>' containment reference.
	 * @see #getSettingType()
	 * @generated
	 */
	void setSettingType(SettingType value);

	/**
	 * Returns the value of the '<em><b>User Exposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Exposed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Exposed</em>' attribute.
	 * @see #setUserExposed(boolean)
	 * @see FederationOffice.services.ServicesPackage#getAbstractSetting_UserExposed()
	 * @model
	 * @generated
	 */
	boolean isUserExposed();

	/**
	 * Sets the value of the '{@link FederationOffice.services.AbstractSetting#isUserExposed <em>User Exposed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Exposed</em>' attribute.
	 * @see #isUserExposed()
	 * @generated
	 */
	void setUserExposed(boolean value);

	/**
	 * Returns the value of the '<em><b>User Editable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Editable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Editable</em>' attribute.
	 * @see #setUserEditable(boolean)
	 * @see FederationOffice.services.ServicesPackage#getAbstractSetting_UserEditable()
	 * @model
	 * @generated
	 */
	boolean isUserEditable();

	/**
	 * Sets the value of the '{@link FederationOffice.services.AbstractSetting#isUserEditable <em>User Editable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Editable</em>' attribute.
	 * @see #isUserEditable()
	 * @generated
	 */
	void setUserEditable(boolean value);

	/**
	 * Returns the value of the '<em><b>Can Be Published</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Can Be Published</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Can Be Published</em>' attribute.
	 * @see #setCanBePublished(boolean)
	 * @see FederationOffice.services.ServicesPackage#getAbstractSetting_CanBePublished()
	 * @model
	 * @generated
	 */
	boolean isCanBePublished();

	/**
	 * Sets the value of the '{@link FederationOffice.services.AbstractSetting#isCanBePublished <em>Can Be Published</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Can Be Published</em>' attribute.
	 * @see #isCanBePublished()
	 * @generated
	 */
	void setCanBePublished(boolean value);

	/**
	 * Returns the value of the '<em><b>Readable</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Readable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Readable</em>' attribute.
	 * @see #setReadable(boolean)
	 * @see FederationOffice.services.ServicesPackage#getAbstractSetting_Readable()
	 * @model default="false"
	 * @generated
	 */
	boolean isReadable();

	/**
	 * Sets the value of the '{@link FederationOffice.services.AbstractSetting#isReadable <em>Readable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Readable</em>' attribute.
	 * @see #isReadable()
	 * @generated
	 */
	void setReadable(boolean value);

	/**
	 * Returns the value of the '<em><b>Writable</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Writable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Writable</em>' attribute.
	 * @see #setWritable(boolean)
	 * @see FederationOffice.services.ServicesPackage#getAbstractSetting_Writable()
	 * @model default="false"
	 * @generated
	 */
	boolean isWritable();

	/**
	 * Sets the value of the '{@link FederationOffice.services.AbstractSetting#isWritable <em>Writable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Writable</em>' attribute.
	 * @see #isWritable()
	 * @generated
	 */
	void setWritable(boolean value);

	/**
	 * Returns the value of the '<em><b>Setting Constraints</b></em>' containment reference list.
	 * The list contents are of type {@link FederationOffice.services.SettingConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Setting Constraints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Setting Constraints</em>' containment reference list.
	 * @see FederationOffice.services.ServicesPackage#getAbstractSetting_SettingConstraints()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<SettingConstraint> getSettingConstraints();

	/**
	 * Returns the value of the '<em><b>Requires Params</b></em>' reference list.
	 * The list contents are of type {@link FederationOffice.services.AbstractSetting}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * -	A parameter Pa of a resource/service Ra requires a parameter(s) Pb of another Resource/Service Rb
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Requires Params</em>' reference list.
	 * @see FederationOffice.services.ServicesPackage#getAbstractSetting_RequiresParams()
	 * @model
	 * @generated
	 */
	EList<AbstractSetting> getRequiresParams();

} // AbstractSetting
