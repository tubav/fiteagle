/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.providersite.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import FederationOffice.impl.NamedElementImpl;
import FederationOffice.providersite.IGW;
import FederationOffice.providersite.ProvidersitePackage;
import FederationOffice.providersite.Site;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IGW</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.providersite.impl.IGWImpl#getBelongsToSite <em>Belongs To Site</em>}</li>
 *   <li>{@link FederationOffice.providersite.impl.IGWImpl#getIP <em>IP</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IGWImpl extends NamedElementImpl implements IGW {
	/**
	 * The default value of the '{@link #getIP() <em>IP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIP()
	 * @generated
	 * @ordered
	 */
	protected static final String IP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIP() <em>IP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIP()
	 * @generated
	 * @ordered
	 */
	protected String ip = IP_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IGWImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProvidersitePackage.Literals.IGW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Site getBelongsToSite() {
		if (eContainerFeatureID() != ProvidersitePackage.IGW__BELONGS_TO_SITE) return null;
		return (Site)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Site basicGetBelongsToSite() {
		if (eContainerFeatureID() != ProvidersitePackage.IGW__BELONGS_TO_SITE) return null;
		return (Site)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBelongsToSite(Site newBelongsToSite, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newBelongsToSite, ProvidersitePackage.IGW__BELONGS_TO_SITE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBelongsToSite(Site newBelongsToSite) {
		if (newBelongsToSite != eInternalContainer() || (eContainerFeatureID() != ProvidersitePackage.IGW__BELONGS_TO_SITE && newBelongsToSite != null)) {
			if (EcoreUtil.isAncestor(this, newBelongsToSite))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBelongsToSite != null)
				msgs = ((InternalEObject)newBelongsToSite).eInverseAdd(this, ProvidersitePackage.SITE__IGWLIST, Site.class, msgs);
			msgs = basicSetBelongsToSite(newBelongsToSite, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProvidersitePackage.IGW__BELONGS_TO_SITE, newBelongsToSite, newBelongsToSite));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIP() {
		return ip;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIP(String newIP) {
		String oldIP = ip;
		ip = newIP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProvidersitePackage.IGW__IP, oldIP, ip));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProvidersitePackage.IGW__BELONGS_TO_SITE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetBelongsToSite((Site)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProvidersitePackage.IGW__BELONGS_TO_SITE:
				return basicSetBelongsToSite(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ProvidersitePackage.IGW__BELONGS_TO_SITE:
				return eInternalContainer().eInverseRemove(this, ProvidersitePackage.SITE__IGWLIST, Site.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ProvidersitePackage.IGW__BELONGS_TO_SITE:
				if (resolve) return getBelongsToSite();
				return basicGetBelongsToSite();
			case ProvidersitePackage.IGW__IP:
				return getIP();
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
			case ProvidersitePackage.IGW__BELONGS_TO_SITE:
				setBelongsToSite((Site)newValue);
				return;
			case ProvidersitePackage.IGW__IP:
				setIP((String)newValue);
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
			case ProvidersitePackage.IGW__BELONGS_TO_SITE:
				setBelongsToSite((Site)null);
				return;
			case ProvidersitePackage.IGW__IP:
				setIP(IP_EDEFAULT);
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
			case ProvidersitePackage.IGW__BELONGS_TO_SITE:
				return basicGetBelongsToSite() != null;
			case ProvidersitePackage.IGW__IP:
				return IP_EDEFAULT == null ? ip != null : !IP_EDEFAULT.equals(ip);
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
		result.append(" (IP: ");
		result.append(ip);
		result.append(')');
		return result.toString();
	}

} //IGWImpl
