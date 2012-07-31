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
 * A representation of the model object '<em><b>Site Location</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.providersite.SiteLocation#getAddress <em>Address</em>}</li>
 *   <li>{@link FederationOffice.providersite.SiteLocation#getGeocoords <em>Geocoords</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.providersite.ProvidersitePackage#getSiteLocation()
 * @model
 * @generated
 */
public interface SiteLocation extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Address</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Address</em>' attribute.
	 * @see #setAddress(String)
	 * @see FederationOffice.providersite.ProvidersitePackage#getSiteLocation_Address()
	 * @model
	 * @generated
	 */
	String getAddress();

	/**
	 * Sets the value of the '{@link FederationOffice.providersite.SiteLocation#getAddress <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Address</em>' attribute.
	 * @see #getAddress()
	 * @generated
	 */
	void setAddress(String value);

	/**
	 * Returns the value of the '<em><b>Geocoords</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Geocoords</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Geocoords</em>' attribute.
	 * @see #setGeocoords(String)
	 * @see FederationOffice.providersite.ProvidersitePackage#getSiteLocation_Geocoords()
	 * @model
	 * @generated
	 */
	String getGeocoords();

	/**
	 * Sets the value of the '{@link FederationOffice.providersite.SiteLocation#getGeocoords <em>Geocoords</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Geocoords</em>' attribute.
	 * @see #getGeocoords()
	 * @generated
	 */
	void setGeocoords(String value);

} // SiteLocation
