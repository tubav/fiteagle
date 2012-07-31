/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.services;

import org.eclipse.emf.common.util.EList;

import FederationOffice.resources.Resource;
import FederationOffice.resources.ResourceSetting;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Setting</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Service setting that a service has.
 * For example "MySQLserver" service has a setting "mUser".
 * This setting later can be mapped to another Offered resource's setting.
 * Example:
 * The "MySQLserver" service might be "providedByResources" (anyone of) uopMySQL, FokusMySQL, etc
 * So if uopMySQL offeredResource, has a setting "uopMyUser" then"mUser" is mapped to "uopMyUser" of uopMySQL.
 * Also  if FokusMySQL offeredResource, has a setting "fokusMyUser" then"mUser" is mapped to "fokusMyUser" of FokusMySQL.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.services.ServiceSetting#getMappedToResourceSettings <em>Mapped To Resource Settings</em>}</li>
 *   <li>{@link FederationOffice.services.ServiceSetting#getProvidedByResources <em>Provided By Resources</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.services.ServicesPackage#getServiceSetting()
 * @model
 * @generated
 */
public interface ServiceSetting extends AbstractSetting {
	/**
	 * Returns the value of the '<em><b>Mapped To Resource Settings</b></em>' reference list.
	 * The list contents are of type {@link FederationOffice.resources.ResourceSetting}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mapped To Resource Settings</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mapped To Resource Settings</em>' reference list.
	 * @see FederationOffice.services.ServicesPackage#getServiceSetting_MappedToResourceSettings()
	 * @model
	 * @generated
	 */
	EList<ResourceSetting> getMappedToResourceSettings();

	/**
	 * Returns the value of the '<em><b>Provided By Resources</b></em>' reference list.
	 * The list contents are of type {@link FederationOffice.resources.Resource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provided By Resources</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provided By Resources</em>' reference list.
	 * @see FederationOffice.services.ServicesPackage#getServiceSetting_ProvidedByResources()
	 * @model
	 * @generated
	 */
	EList<Resource> getProvidedByResources();

} // ServiceSetting
