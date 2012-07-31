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
 * A representation of the model object '<em><b>PTM</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.providersite.PTM#getIP <em>IP</em>}</li>
 *   <li>{@link FederationOffice.providersite.PTM#getBelongsToSite <em>Belongs To Site</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.providersite.ProvidersitePackage#getPTM()
 * @model
 * @generated
 */
public interface PTM extends NamedElement {
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
	 * @see FederationOffice.providersite.ProvidersitePackage#getPTM_IP()
	 * @model
	 * @generated
	 */
	String getIP();

	/**
	 * Sets the value of the '{@link FederationOffice.providersite.PTM#getIP <em>IP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>IP</em>' attribute.
	 * @see #getIP()
	 * @generated
	 */
	void setIP(String value);

	/**
	 * Returns the value of the '<em><b>Belongs To Site</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link FederationOffice.providersite.Site#getPtm <em>Ptm</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Belongs To Site</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Belongs To Site</em>' container reference.
	 * @see #setBelongsToSite(Site)
	 * @see FederationOffice.providersite.ProvidersitePackage#getPTM_BelongsToSite()
	 * @see FederationOffice.providersite.Site#getPtm
	 * @model opposite="ptm" transient="false"
	 * @generated
	 */
	Site getBelongsToSite();

	/**
	 * Sets the value of the '{@link FederationOffice.providersite.PTM#getBelongsToSite <em>Belongs To Site</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Belongs To Site</em>' container reference.
	 * @see #getBelongsToSite()
	 * @generated
	 */
	void setBelongsToSite(Site value);

} // PTM
