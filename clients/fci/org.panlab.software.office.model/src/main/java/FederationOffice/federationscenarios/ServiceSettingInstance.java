/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.federationscenarios;

import FederationOffice.services.ServiceSetting;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Setting Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.federationscenarios.ServiceSettingInstance#getRefServiceSetting <em>Ref Service Setting</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.federationscenarios.FederationscenariosPackage#getServiceSettingInstance()
 * @model
 * @generated
 */
public interface ServiceSettingInstance extends SettingInstance {
	/**
	 * Returns the value of the '<em><b>Ref Service Setting</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ref Service Setting</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ref Service Setting</em>' reference.
	 * @see #setRefServiceSetting(ServiceSetting)
	 * @see FederationOffice.federationscenarios.FederationscenariosPackage#getServiceSettingInstance_RefServiceSetting()
	 * @model required="true"
	 * @generated
	 */
	ServiceSetting getRefServiceSetting();

	/**
	 * Sets the value of the '{@link FederationOffice.federationscenarios.ServiceSettingInstance#getRefServiceSetting <em>Ref Service Setting</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ref Service Setting</em>' reference.
	 * @see #getRefServiceSetting()
	 * @generated
	 */
	void setRefServiceSetting(ServiceSetting value);

} // ServiceSettingInstance
