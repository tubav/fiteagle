/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.providersite;

import FederationOffice.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IGW</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.providersite.IGW#getBelongsToSite <em>Belongs To Site</em>}</li>
 *   <li>{@link FederationOffice.providersite.IGW#getIP <em>IP</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.providersite.ProvidersitePackage#getIGW()
 * @model
 * @generated
 */
public interface IGW extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Belongs To Site</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link FederationOffice.providersite.Site#getIgwlist <em>Igwlist</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Belongs To Site</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Belongs To Site</em>' container reference.
	 * @see #setBelongsToSite(Site)
	 * @see FederationOffice.providersite.ProvidersitePackage#getIGW_BelongsToSite()
	 * @see FederationOffice.providersite.Site#getIgwlist
	 * @model opposite="igwlist" transient="false"
	 * @generated
	 */
	Site getBelongsToSite();

	/**
	 * Sets the value of the '{@link FederationOffice.providersite.IGW#getBelongsToSite <em>Belongs To Site</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Belongs To Site</em>' container reference.
	 * @see #getBelongsToSite()
	 * @generated
	 */
	void setBelongsToSite(Site value);

	/**
	 * Returns the value of the '<em><b>IP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>IP</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>IP</em>' attribute.
	 * @see #setIP(String)
	 * @see FederationOffice.providersite.ProvidersitePackage#getIGW_IP()
	 * @model
	 * @generated
	 */
	String getIP();

	/**
	 * Sets the value of the '{@link FederationOffice.providersite.IGW#getIP <em>IP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>IP</em>' attribute.
	 * @see #getIP()
	 * @generated
	 */
	void setIP(String value);

} // IGW
