/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.providersite;

import org.eclipse.emf.common.util.EList;

import FederationOffice.NamedElement;
import FederationOffice.resources.OfferedResource;
import FederationOffice.users.ResourcesProvider;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Site</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.providersite.Site#getPtm <em>Ptm</em>}</li>
 *   <li>{@link FederationOffice.providersite.Site#getIgwlist <em>Igwlist</em>}</li>
 *   <li>{@link FederationOffice.providersite.Site#getLocatedAt <em>Located At</em>}</li>
 *   <li>{@link FederationOffice.providersite.Site#getOfferedResourcesList <em>Offered Resources List</em>}</li>
 *   <li>{@link FederationOffice.providersite.Site#getBelongsToProvider <em>Belongs To Provider</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.providersite.ProvidersitePackage#getSite()
 * @model
 * @generated
 */
public interface Site extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Ptm</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link FederationOffice.providersite.PTM#getBelongsToSite <em>Belongs To Site</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ptm</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ptm</em>' containment reference.
	 * @see #setPtm(PTM)
	 * @see FederationOffice.providersite.ProvidersitePackage#getSite_Ptm()
	 * @see FederationOffice.providersite.PTM#getBelongsToSite
	 * @model opposite="belongsToSite" containment="true" resolveProxies="true" required="true"
	 * @generated
	 */
	PTM getPtm();

	/**
	 * Sets the value of the '{@link FederationOffice.providersite.Site#getPtm <em>Ptm</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ptm</em>' containment reference.
	 * @see #getPtm()
	 * @generated
	 */
	void setPtm(PTM value);

	/**
	 * Returns the value of the '<em><b>Igwlist</b></em>' containment reference list.
	 * The list contents are of type {@link FederationOffice.providersite.IGW}.
	 * It is bidirectional and its opposite is '{@link FederationOffice.providersite.IGW#getBelongsToSite <em>Belongs To Site</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Igwlist</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Igwlist</em>' containment reference list.
	 * @see FederationOffice.providersite.ProvidersitePackage#getSite_Igwlist()
	 * @see FederationOffice.providersite.IGW#getBelongsToSite
	 * @model opposite="belongsToSite" containment="true" resolveProxies="true" required="true"
	 * @generated
	 */
	EList<IGW> getIgwlist();

	/**
	 * Returns the value of the '<em><b>Located At</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Located At</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Located At</em>' containment reference.
	 * @see #setLocatedAt(SiteLocation)
	 * @see FederationOffice.providersite.ProvidersitePackage#getSite_LocatedAt()
	 * @model containment="true" resolveProxies="true" required="true"
	 * @generated
	 */
	SiteLocation getLocatedAt();

	/**
	 * Sets the value of the '{@link FederationOffice.providersite.Site#getLocatedAt <em>Located At</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Located At</em>' containment reference.
	 * @see #getLocatedAt()
	 * @generated
	 */
	void setLocatedAt(SiteLocation value);

	/**
	 * Returns the value of the '<em><b>Offered Resources List</b></em>' containment reference list.
	 * The list contents are of type {@link FederationOffice.resources.OfferedResource}.
	 * It is bidirectional and its opposite is '{@link FederationOffice.resources.OfferedResource#getBelongsToSite <em>Belongs To Site</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Offered Resources List</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Offered Resources List</em>' containment reference list.
	 * @see FederationOffice.providersite.ProvidersitePackage#getSite_OfferedResourcesList()
	 * @see FederationOffice.resources.OfferedResource#getBelongsToSite
	 * @model opposite="belongsToSite" containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<OfferedResource> getOfferedResourcesList();

	/**
	 * Returns the value of the '<em><b>Belongs To Provider</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link FederationOffice.users.ResourcesProvider#getOfferedSiteList <em>Offered Site List</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Belongs To Provider</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Belongs To Provider</em>' container reference.
	 * @see #setBelongsToProvider(ResourcesProvider)
	 * @see FederationOffice.providersite.ProvidersitePackage#getSite_BelongsToProvider()
	 * @see FederationOffice.users.ResourcesProvider#getOfferedSiteList
	 * @model opposite="offeredSiteList" transient="false"
	 * @generated
	 */
	ResourcesProvider getBelongsToProvider();

	/**
	 * Sets the value of the '{@link FederationOffice.providersite.Site#getBelongsToProvider <em>Belongs To Provider</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Belongs To Provider</em>' container reference.
	 * @see #getBelongsToProvider()
	 * @generated
	 */
	void setBelongsToProvider(ResourcesProvider value);

} // Site
