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
 * A representation of the model object '<em><b>Setting Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.services.SettingConstraint#getForOperation <em>For Operation</em>}</li>
 *   <li>{@link FederationOffice.services.SettingConstraint#isAvailableAfterOperation <em>Available After Operation</em>}</li>
 *   <li>{@link FederationOffice.services.SettingConstraint#isRequiredBeforeOperation <em>Required Before Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.services.ServicesPackage#getSettingConstraint()
 * @model
 * @generated
 */
public interface SettingConstraint extends NamedElement {
	/**
	 * Returns the value of the '<em><b>For Operation</b></em>' attribute.
	 * The literals are from the enumeration {@link FederationOffice.services.ServiceResourceOperation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>For Operation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>For Operation</em>' attribute.
	 * @see FederationOffice.services.ServiceResourceOperation
	 * @see #setForOperation(ServiceResourceOperation)
	 * @see FederationOffice.services.ServicesPackage#getSettingConstraint_ForOperation()
	 * @model
	 * @generated
	 */
	ServiceResourceOperation getForOperation();

	/**
	 * Sets the value of the '{@link FederationOffice.services.SettingConstraint#getForOperation <em>For Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>For Operation</em>' attribute.
	 * @see FederationOffice.services.ServiceResourceOperation
	 * @see #getForOperation()
	 * @generated
	 */
	void setForOperation(ServiceResourceOperation value);

	/**
	 * Returns the value of the '<em><b>Available After Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * -	if set A parameter is available only after a certain CRUD operation
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Available After Operation</em>' attribute.
	 * @see #setAvailableAfterOperation(boolean)
	 * @see FederationOffice.services.ServicesPackage#getSettingConstraint_AvailableAfterOperation()
	 * @model
	 * @generated
	 */
	boolean isAvailableAfterOperation();

	/**
	 * Sets the value of the '{@link FederationOffice.services.SettingConstraint#isAvailableAfterOperation <em>Available After Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Available After Operation</em>' attribute.
	 * @see #isAvailableAfterOperation()
	 * @generated
	 */
	void setAvailableAfterOperation(boolean value);

	/**
	 * Returns the value of the '<em><b>Required Before Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * -	If set A parameter must be present, must have a not null value before a CRUD operation
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Required Before Operation</em>' attribute.
	 * @see #setRequiredBeforeOperation(boolean)
	 * @see FederationOffice.services.ServicesPackage#getSettingConstraint_RequiredBeforeOperation()
	 * @model
	 * @generated
	 */
	boolean isRequiredBeforeOperation();

	/**
	 * Sets the value of the '{@link FederationOffice.services.SettingConstraint#isRequiredBeforeOperation <em>Required Before Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Required Before Operation</em>' attribute.
	 * @see #isRequiredBeforeOperation()
	 * @generated
	 */
	void setRequiredBeforeOperation(boolean value);

} // SettingConstraint
