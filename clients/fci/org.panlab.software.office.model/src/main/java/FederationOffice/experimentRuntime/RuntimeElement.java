/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.experimentRuntime;

import FederationOffice.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Runtime Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.experimentRuntime.RuntimeElement#getStatus <em>Status</em>}</li>
 *   <li>{@link FederationOffice.experimentRuntime.RuntimeElement#getGUID <em>GUID</em>}</li>
 *   <li>{@link FederationOffice.experimentRuntime.RuntimeElement#getContext <em>Context</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.experimentRuntime.ExperimentRuntimePackage#getRuntimeElement()
 * @model
 * @generated
 */
public interface RuntimeElement extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * The literals are from the enumeration {@link FederationOffice.experimentRuntime.RuntimeElementStatus}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see FederationOffice.experimentRuntime.RuntimeElementStatus
	 * @see #setStatus(RuntimeElementStatus)
	 * @see FederationOffice.experimentRuntime.ExperimentRuntimePackage#getRuntimeElement_Status()
	 * @model
	 * @generated
	 */
	RuntimeElementStatus getStatus();

	/**
	 * Sets the value of the '{@link FederationOffice.experimentRuntime.RuntimeElement#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see FederationOffice.experimentRuntime.RuntimeElementStatus
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(RuntimeElementStatus value);

	/**
	 * Returns the value of the '<em><b>GUID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>GUID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>GUID</em>' attribute.
	 * @see #setGUID(String)
	 * @see FederationOffice.experimentRuntime.ExperimentRuntimePackage#getRuntimeElement_GUID()
	 * @model
	 * @generated
	 */
	String getGUID();

	/**
	 * Sets the value of the '{@link FederationOffice.experimentRuntime.RuntimeElement#getGUID <em>GUID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>GUID</em>' attribute.
	 * @see #getGUID()
	 * @generated
	 */
	void setGUID(String value);

	/**
	 * Returns the value of the '<em><b>Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context</em>' attribute.
	 * @see #setContext(String)
	 * @see FederationOffice.experimentRuntime.ExperimentRuntimePackage#getRuntimeElement_Context()
	 * @model
	 * @generated
	 */
	String getContext();

	/**
	 * Sets the value of the '{@link FederationOffice.experimentRuntime.RuntimeElement#getContext <em>Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context</em>' attribute.
	 * @see #getContext()
	 * @generated
	 */
	void setContext(String value);

} // RuntimeElement
