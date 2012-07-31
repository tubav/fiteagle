/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.services;

import org.eclipse.emf.common.util.EList;

import FederationOffice.NamedElement;
import FederationOffice.federationscenarios.RequestedFederationScenario;
import FederationOffice.resources.ResourceCategory;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Taxonomy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.services.Taxonomy#getTaxonomies <em>Taxonomies</em>}</li>
 *   <li>{@link FederationOffice.services.Taxonomy#getCategories <em>Categories</em>}</li>
 *   <li>{@link FederationOffice.services.Taxonomy#getHasScenarios <em>Has Scenarios</em>}</li>
 *   <li>{@link FederationOffice.services.Taxonomy#getHasServices <em>Has Services</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.services.ServicesPackage#getTaxonomy()
 * @model
 * @generated
 */
public interface Taxonomy extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Taxonomies</b></em>' containment reference list.
	 * The list contents are of type {@link FederationOffice.services.Taxonomy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Taxonomies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Taxonomies</em>' containment reference list.
	 * @see FederationOffice.services.ServicesPackage#getTaxonomy_Taxonomies()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Taxonomy> getTaxonomies();

	/**
	 * Returns the value of the '<em><b>Categories</b></em>' containment reference list.
	 * The list contents are of type {@link FederationOffice.resources.ResourceCategory}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Categories</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Categories</em>' containment reference list.
	 * @see FederationOffice.services.ServicesPackage#getTaxonomy_Categories()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<ResourceCategory> getCategories();

	/**
	 * Returns the value of the '<em><b>Has Scenarios</b></em>' reference list.
	 * The list contents are of type {@link FederationOffice.federationscenarios.RequestedFederationScenario}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Scenarios</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Scenarios</em>' reference list.
	 * @see FederationOffice.services.ServicesPackage#getTaxonomy_HasScenarios()
	 * @model
	 * @generated
	 */
	EList<RequestedFederationScenario> getHasScenarios();

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
	 * @see FederationOffice.services.ServicesPackage#getTaxonomy_HasServices()
	 * @model
	 * @generated
	 */
	EList<Service> getHasServices();

} // Taxonomy
