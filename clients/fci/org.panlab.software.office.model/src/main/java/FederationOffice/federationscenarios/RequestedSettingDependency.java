/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.federationscenarios;

import FederationOffice.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Requested Setting Dependency</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This class keeps a pair of a source (producer) RequestedSetting and a target (consumer) RequestedSetting
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.federationscenarios.RequestedSettingDependency#getSource <em>Source</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.RequestedSettingDependency#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.federationscenarios.FederationscenariosPackage#getRequestedSettingDependency()
 * @model
 * @generated
 */
public interface RequestedSettingDependency extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(SettingInstance)
	 * @see FederationOffice.federationscenarios.FederationscenariosPackage#getRequestedSettingDependency_Source()
	 * @model
	 * @generated
	 */
	SettingInstance getSource();

	/**
	 * Sets the value of the '{@link FederationOffice.federationscenarios.RequestedSettingDependency#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(SettingInstance value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(SettingInstance)
	 * @see FederationOffice.federationscenarios.FederationscenariosPackage#getRequestedSettingDependency_Target()
	 * @model
	 * @generated
	 */
	SettingInstance getTarget();

	/**
	 * Sets the value of the '{@link FederationOffice.federationscenarios.RequestedSettingDependency#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(SettingInstance value);

} // RequestedSettingDependency
