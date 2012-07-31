/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.federationscenarios;

import org.eclipse.emf.common.util.EList;

import FederationOffice.NamedElement;
import FederationOffice.experimentRuntime.RuntimeElement;
import FederationOffice.resources.OfferedResource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Request</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.federationscenarios.ResourceRequest#getRefOfferedResource <em>Ref Offered Resource</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.ResourceRequest#getReqResourceSettings <em>Req Resource Settings</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.ResourceRequest#getRuntimeInfo <em>Runtime Info</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.federationscenarios.FederationscenariosPackage#getResourceRequest()
 * @model
 * @generated
 */
public interface ResourceRequest extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Ref Offered Resource</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ref Offered Resource</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ref Offered Resource</em>' reference.
	 * @see #setRefOfferedResource(OfferedResource)
	 * @see FederationOffice.federationscenarios.FederationscenariosPackage#getResourceRequest_RefOfferedResource()
	 * @model required="true"
	 * @generated
	 */
	OfferedResource getRefOfferedResource();

	/**
	 * Sets the value of the '{@link FederationOffice.federationscenarios.ResourceRequest#getRefOfferedResource <em>Ref Offered Resource</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ref Offered Resource</em>' reference.
	 * @see #getRefOfferedResource()
	 * @generated
	 */
	void setRefOfferedResource(OfferedResource value);

	/**
	 * Returns the value of the '<em><b>Req Resource Settings</b></em>' containment reference list.
	 * The list contents are of type {@link FederationOffice.federationscenarios.ResourceSettingInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Req Resource Settings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Req Resource Settings</em>' containment reference list.
	 * @see FederationOffice.federationscenarios.FederationscenariosPackage#getResourceRequest_ReqResourceSettings()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<ResourceSettingInstance> getReqResourceSettings();

	/**
	 * Returns the value of the '<em><b>Runtime Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Runtime Info</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Runtime Info</em>' containment reference.
	 * @see #setRuntimeInfo(RuntimeElement)
	 * @see FederationOffice.federationscenarios.FederationscenariosPackage#getResourceRequest_RuntimeInfo()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	RuntimeElement getRuntimeInfo();

	/**
	 * Sets the value of the '{@link FederationOffice.federationscenarios.ResourceRequest#getRuntimeInfo <em>Runtime Info</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Runtime Info</em>' containment reference.
	 * @see #getRuntimeInfo()
	 * @generated
	 */
	void setRuntimeInfo(RuntimeElement value);

} // ResourceRequest
