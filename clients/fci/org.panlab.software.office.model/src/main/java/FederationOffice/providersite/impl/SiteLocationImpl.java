/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.providersite.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import FederationOffice.impl.NamedElementImpl;
import FederationOffice.providersite.ProvidersitePackage;
import FederationOffice.providersite.SiteLocation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Site Location</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.providersite.impl.SiteLocationImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link FederationOffice.providersite.impl.SiteLocationImpl#getGeocoords <em>Geocoords</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SiteLocationImpl extends NamedElementImpl implements SiteLocation {
	/**
	 * The default value of the '{@link #getAddress() <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddress()
	 * @generated
	 * @ordered
	 */
	protected static final String ADDRESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAddress() <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddress()
	 * @generated
	 * @ordered
	 */
	protected String address = ADDRESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getGeocoords() <em>Geocoords</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeocoords()
	 * @generated
	 * @ordered
	 */
	protected static final String GEOCOORDS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGeocoords() <em>Geocoords</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeocoords()
	 * @generated
	 * @ordered
	 */
	protected String geocoords = GEOCOORDS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SiteLocationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProvidersitePackage.Literals.SITE_LOCATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAddress(String newAddress) {
		String oldAddress = address;
		address = newAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProvidersitePackage.SITE_LOCATION__ADDRESS, oldAddress, address));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGeocoords() {
		return geocoords;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGeocoords(String newGeocoords) {
		String oldGeocoords = geocoords;
		geocoords = newGeocoords;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProvidersitePackage.SITE_LOCATION__GEOCOORDS, oldGeocoords, geocoords));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ProvidersitePackage.SITE_LOCATION__ADDRESS:
				return getAddress();
			case ProvidersitePackage.SITE_LOCATION__GEOCOORDS:
				return getGeocoords();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ProvidersitePackage.SITE_LOCATION__ADDRESS:
				setAddress((String)newValue);
				return;
			case ProvidersitePackage.SITE_LOCATION__GEOCOORDS:
				setGeocoords((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ProvidersitePackage.SITE_LOCATION__ADDRESS:
				setAddress(ADDRESS_EDEFAULT);
				return;
			case ProvidersitePackage.SITE_LOCATION__GEOCOORDS:
				setGeocoords(GEOCOORDS_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ProvidersitePackage.SITE_LOCATION__ADDRESS:
				return ADDRESS_EDEFAULT == null ? address != null : !ADDRESS_EDEFAULT.equals(address);
			case ProvidersitePackage.SITE_LOCATION__GEOCOORDS:
				return GEOCOORDS_EDEFAULT == null ? geocoords != null : !GEOCOORDS_EDEFAULT.equals(geocoords);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (address: ");
		result.append(address);
		result.append(", geocoords: ");
		result.append(geocoords);
		result.append(')');
		return result.toString();
	}

} //SiteLocationImpl
