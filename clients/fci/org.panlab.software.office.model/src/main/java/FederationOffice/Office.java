/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice;

import org.eclipse.emf.common.util.EList;

import FederationOffice.availabilityContract.ResourceServiceContract;
import FederationOffice.federationscenarios.RequestedFederationScenario;
import FederationOffice.services.OfferedService;
import FederationOffice.services.ServiceComposition;
import FederationOffice.services.Taxonomy;
import FederationOffice.slareservations.SLA;
import FederationOffice.users.OfficeUser;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Office</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The class representing an office. Usually will be a singleton class
 * It could be also from NDL: Administrative Domain, the organization that actually controls and provisions the resources, such as network resources and computing resources. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.Office#getRegisteredUsers <em>Registered Users</em>}</li>
 *   <li>{@link FederationOffice.Office#getContributedTaxonomies <em>Contributed Taxonomies</em>}</li>
 *   <li>{@link FederationOffice.Office#getOfferedServices <em>Offered Services</em>}</li>
 *   <li>{@link FederationOffice.Office#getAvailableFederationScenarios <em>Available Federation Scenarios</em>}</li>
 *   <li>{@link FederationOffice.Office#getOfferedServiceCompositions <em>Offered Service Compositions</em>}</li>
 *   <li>{@link FederationOffice.Office#getResourceServiceContracts <em>Resource Service Contracts</em>}</li>
 *   <li>{@link FederationOffice.Office#getSLAs <em>SL As</em>}</li>
 *   <li>{@link FederationOffice.Office#getResourceURI <em>Resource URI</em>}</li>
 *   <li>{@link FederationOffice.Office#getAPIGateway <em>API Gateway</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.FederationOfficePackage#getOffice()
 * @model
 * @generated
 */
public interface Office extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Registered Users</b></em>' containment reference list.
	 * The list contents are of type {@link FederationOffice.users.OfficeUser}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Registered Users</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Registered Users</em>' containment reference list.
	 * @see FederationOffice.FederationOfficePackage#getOffice_RegisteredUsers()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<OfficeUser> getRegisteredUsers();

	/**
	 * Returns the value of the '<em><b>Contributed Taxonomies</b></em>' containment reference list.
	 * The list contents are of type {@link FederationOffice.services.Taxonomy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contributed Taxonomies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contributed Taxonomies</em>' containment reference list.
	 * @see FederationOffice.FederationOfficePackage#getOffice_ContributedTaxonomies()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Taxonomy> getContributedTaxonomies();

	/**
	 * Returns the value of the '<em><b>Offered Services</b></em>' containment reference list.
	 * The list contents are of type {@link FederationOffice.services.OfferedService}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Offered Services</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Offered Services</em>' containment reference list.
	 * @see FederationOffice.FederationOfficePackage#getOffice_OfferedServices()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<OfferedService> getOfferedServices();

	/**
	 * Returns the value of the '<em><b>Available Federation Scenarios</b></em>' containment reference list.
	 * The list contents are of type {@link FederationOffice.federationscenarios.RequestedFederationScenario}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Available Federation Scenarios</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Available Federation Scenarios</em>' containment reference list.
	 * @see FederationOffice.FederationOfficePackage#getOffice_AvailableFederationScenarios()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<RequestedFederationScenario> getAvailableFederationScenarios();

	/**
	 * Returns the value of the '<em><b>Offered Service Compositions</b></em>' containment reference list.
	 * The list contents are of type {@link FederationOffice.services.ServiceComposition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Offered Service Compositions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Offered Service Compositions</em>' containment reference list.
	 * @see FederationOffice.FederationOfficePackage#getOffice_OfferedServiceCompositions()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<ServiceComposition> getOfferedServiceCompositions();

	/**
	 * Returns the value of the '<em><b>Resource Service Contracts</b></em>' containment reference list.
	 * The list contents are of type {@link FederationOffice.availabilityContract.ResourceServiceContract}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Service Contracts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Service Contracts</em>' containment reference list.
	 * @see FederationOffice.FederationOfficePackage#getOffice_ResourceServiceContracts()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<ResourceServiceContract> getResourceServiceContracts();

	/**
	 * Returns the value of the '<em><b>SL As</b></em>' containment reference list.
	 * The list contents are of type {@link FederationOffice.slareservations.SLA}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SL As</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>SL As</em>' containment reference list.
	 * @see FederationOffice.FederationOfficePackage#getOffice_SLAs()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<SLA> getSLAs();

	/**
	 * Returns the value of the '<em><b>Resource URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource URI</em>' attribute.
	 * @see #setResourceURI(String)
	 * @see FederationOffice.FederationOfficePackage#getOffice_ResourceURI()
	 * @model
	 * @generated
	 */
	String getResourceURI();

	/**
	 * Sets the value of the '{@link FederationOffice.Office#getResourceURI <em>Resource URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource URI</em>' attribute.
	 * @see #getResourceURI()
	 * @generated
	 */
	void setResourceURI(String value);

	/**
	 * Returns the value of the '<em><b>API Gateway</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>API Gateway</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>API Gateway</em>' attribute.
	 * @see #setAPIGateway(String)
	 * @see FederationOffice.FederationOfficePackage#getOffice_APIGateway()
	 * @model
	 * @generated
	 */
	String getAPIGateway();

	/**
	 * Sets the value of the '{@link FederationOffice.Office#getAPIGateway <em>API Gateway</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>API Gateway</em>' attribute.
	 * @see #getAPIGateway()
	 * @generated
	 */
	void setAPIGateway(String value);

} // Office
