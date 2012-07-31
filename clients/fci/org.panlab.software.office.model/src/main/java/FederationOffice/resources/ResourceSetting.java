/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.resources;

import org.eclipse.emf.common.util.EList;

import FederationOffice.services.AbstractSetting;
import FederationOffice.services.ServiceSetting;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Setting</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.resources.ResourceSetting#getOnlyConfiguredByResources <em>Only Configured By Resources</em>}</li>
 *   <li>{@link FederationOffice.resources.ResourceSetting#getImplServiceSetting <em>Impl Service Setting</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.resources.ResourcesPackage#getResourceSetting()
 * @model
 * @generated
 */
public interface ResourceSetting extends AbstractSetting {
	/**
	 * Returns the value of the '<em><b>Only Configured By Resources</b></em>' reference list.
	 * The list contents are of type {@link FederationOffice.resources.Resource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * OnlyConfiguredByResources shows that this ResourceSetting can be configured by only other ResourceSetting  of the OnlyConfiguredByResources  list
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Only Configured By Resources</em>' reference list.
	 * @see FederationOffice.resources.ResourcesPackage#getResourceSetting_OnlyConfiguredByResources()
	 * @model
	 * @generated
	 */
	EList<Resource> getOnlyConfiguredByResources();

	/**
	 * Returns the value of the '<em><b>Impl Service Setting</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Impl Service Setting</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Impl Service Setting</em>' reference.
	 * @see #setImplServiceSetting(ServiceSetting)
	 * @see FederationOffice.resources.ResourcesPackage#getResourceSetting_ImplServiceSetting()
	 * @model
	 * @generated
	 */
	ServiceSetting getImplServiceSetting();

	/**
	 * Sets the value of the '{@link FederationOffice.resources.ResourceSetting#getImplServiceSetting <em>Impl Service Setting</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Impl Service Setting</em>' reference.
	 * @see #getImplServiceSetting()
	 * @generated
	 */
	void setImplServiceSetting(ServiceSetting value);

} // ResourceSetting
