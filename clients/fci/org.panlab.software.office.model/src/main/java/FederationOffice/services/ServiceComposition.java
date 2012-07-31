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
 * A representation of the model object '<em><b>Service Composition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Representing Offered ready composed services by the office.
 * e.g. an IMS network and services. The IMS has a list of services like an HSS , presence server, etc. 
 * When a user asks for an IMS service, the fed will locate any of each service (HSS , presence server) even on different provider locations although might give priority on an IMS ServiceComposition from a single provide to enhance speed, provisioning, etc
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.services.ServiceComposition#getHasServices <em>Has Services</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.services.ServicesPackage#getServiceComposition()
 * @model
 * @generated
 */
public interface ServiceComposition extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Has Services</b></em>' reference list.
	 * The list contents are of type {@link FederationOffice.services.Service}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Services</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Services</em>' reference list.
	 * @see FederationOffice.services.ServicesPackage#getServiceComposition_HasServices()
	 * @model
	 * @generated
	 */
	EList<Service> getHasServices();

} // ServiceComposition
