/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.federationscenarios;

import org.eclipse.emf.common.util.EList;

import FederationOffice.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Setting Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.federationscenarios.SettingInstance#getStaticValue <em>Static Value</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.SettingInstance#getAssignSetting <em>Assign Setting</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.federationscenarios.FederationscenariosPackage#getSettingInstance()
 * @model
 * @generated
 */
public interface SettingInstance extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Static Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Static Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static Value</em>' attribute.
	 * @see #setStaticValue(String)
	 * @see FederationOffice.federationscenarios.FederationscenariosPackage#getSettingInstance_StaticValue()
	 * @model
	 * @generated
	 */
	String getStaticValue();

	/**
	 * Sets the value of the '{@link FederationOffice.federationscenarios.SettingInstance#getStaticValue <em>Static Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static Value</em>' attribute.
	 * @see #getStaticValue()
	 * @generated
	 */
	void setStaticValue(String value);

	/**
	 * Returns the value of the '<em><b>Assign Setting</b></em>' reference list.
	 * The list contents are of type {@link FederationOffice.federationscenarios.SettingInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assign Setting</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Assignments work differently from VCT tool. in vct tool the arrows are references.. where in us assign means that the assignedSetting value is the source and the current element Setting is the consumer target
	 * This allows us to assign in a Setting Instance many source Setting values from other Services/Resource
	 * For example: Assume That we have a Setting of a Service that accepts an arbitrary list of IPs. Then we can have
	 * Setting "myIPList": myIPlist assign  "machine1.IP", "machine2.IP", "machine3.IP"
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Assign Setting</em>' reference list.
	 * @see FederationOffice.federationscenarios.FederationscenariosPackage#getSettingInstance_AssignSetting()
	 * @model
	 * @generated
	 */
	EList<SettingInstance> getAssignSetting();

} // SettingInstance
