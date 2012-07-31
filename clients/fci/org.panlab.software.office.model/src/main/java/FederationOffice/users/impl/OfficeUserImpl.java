/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.users.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import FederationOffice.impl.NamedElementImpl;
import FederationOffice.users.Account;
import FederationOffice.users.OfficeUser;
import FederationOffice.users.UsersPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Office User</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.users.impl.OfficeUserImpl#getHasAccount <em>Has Account</em>}</li>
 *   <li>{@link FederationOffice.users.impl.OfficeUserImpl#getOrganization <em>Organization</em>}</li>
 *   <li>{@link FederationOffice.users.impl.OfficeUserImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link FederationOffice.users.impl.OfficeUserImpl#getTelephone <em>Telephone</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class OfficeUserImpl extends NamedElementImpl implements OfficeUser {
	/**
	 * The cached value of the '{@link #getHasAccount() <em>Has Account</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasAccount()
	 * @generated
	 * @ordered
	 */
	protected Account hasAccount;

	/**
	 * The default value of the '{@link #getOrganization() <em>Organization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrganization()
	 * @generated
	 * @ordered
	 */
	protected static final String ORGANIZATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOrganization() <em>Organization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrganization()
	 * @generated
	 * @ordered
	 */
	protected String organization = ORGANIZATION_EDEFAULT;

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
	 * The default value of the '{@link #getTelephone() <em>Telephone</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTelephone()
	 * @generated
	 * @ordered
	 */
	protected static final String TELEPHONE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTelephone() <em>Telephone</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTelephone()
	 * @generated
	 * @ordered
	 */
	protected String telephone = TELEPHONE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OfficeUserImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsersPackage.Literals.OFFICE_USER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Account getHasAccount() {
		if (hasAccount != null && hasAccount.eIsProxy()) {
			InternalEObject oldHasAccount = (InternalEObject)hasAccount;
			hasAccount = (Account)eResolveProxy(oldHasAccount);
			if (hasAccount != oldHasAccount) {
				InternalEObject newHasAccount = (InternalEObject)hasAccount;
				NotificationChain msgs = oldHasAccount.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UsersPackage.OFFICE_USER__HAS_ACCOUNT, null, null);
				if (newHasAccount.eInternalContainer() == null) {
					msgs = newHasAccount.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UsersPackage.OFFICE_USER__HAS_ACCOUNT, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UsersPackage.OFFICE_USER__HAS_ACCOUNT, oldHasAccount, hasAccount));
			}
		}
		return hasAccount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Account basicGetHasAccount() {
		return hasAccount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHasAccount(Account newHasAccount, NotificationChain msgs) {
		Account oldHasAccount = hasAccount;
		hasAccount = newHasAccount;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UsersPackage.OFFICE_USER__HAS_ACCOUNT, oldHasAccount, newHasAccount);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasAccount(Account newHasAccount) {
		if (newHasAccount != hasAccount) {
			NotificationChain msgs = null;
			if (hasAccount != null)
				msgs = ((InternalEObject)hasAccount).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UsersPackage.OFFICE_USER__HAS_ACCOUNT, null, msgs);
			if (newHasAccount != null)
				msgs = ((InternalEObject)newHasAccount).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UsersPackage.OFFICE_USER__HAS_ACCOUNT, null, msgs);
			msgs = basicSetHasAccount(newHasAccount, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.OFFICE_USER__HAS_ACCOUNT, newHasAccount, newHasAccount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOrganization() {
		return organization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrganization(String newOrganization) {
		String oldOrganization = organization;
		organization = newOrganization;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.OFFICE_USER__ORGANIZATION, oldOrganization, organization));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.OFFICE_USER__ADDRESS, oldAddress, address));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTelephone(String newTelephone) {
		String oldTelephone = telephone;
		telephone = newTelephone;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsersPackage.OFFICE_USER__TELEPHONE, oldTelephone, telephone));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UsersPackage.OFFICE_USER__HAS_ACCOUNT:
				return basicSetHasAccount(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UsersPackage.OFFICE_USER__HAS_ACCOUNT:
				if (resolve) return getHasAccount();
				return basicGetHasAccount();
			case UsersPackage.OFFICE_USER__ORGANIZATION:
				return getOrganization();
			case UsersPackage.OFFICE_USER__ADDRESS:
				return getAddress();
			case UsersPackage.OFFICE_USER__TELEPHONE:
				return getTelephone();
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
			case UsersPackage.OFFICE_USER__HAS_ACCOUNT:
				setHasAccount((Account)newValue);
				return;
			case UsersPackage.OFFICE_USER__ORGANIZATION:
				setOrganization((String)newValue);
				return;
			case UsersPackage.OFFICE_USER__ADDRESS:
				setAddress((String)newValue);
				return;
			case UsersPackage.OFFICE_USER__TELEPHONE:
				setTelephone((String)newValue);
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
			case UsersPackage.OFFICE_USER__HAS_ACCOUNT:
				setHasAccount((Account)null);
				return;
			case UsersPackage.OFFICE_USER__ORGANIZATION:
				setOrganization(ORGANIZATION_EDEFAULT);
				return;
			case UsersPackage.OFFICE_USER__ADDRESS:
				setAddress(ADDRESS_EDEFAULT);
				return;
			case UsersPackage.OFFICE_USER__TELEPHONE:
				setTelephone(TELEPHONE_EDEFAULT);
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
			case UsersPackage.OFFICE_USER__HAS_ACCOUNT:
				return hasAccount != null;
			case UsersPackage.OFFICE_USER__ORGANIZATION:
				return ORGANIZATION_EDEFAULT == null ? organization != null : !ORGANIZATION_EDEFAULT.equals(organization);
			case UsersPackage.OFFICE_USER__ADDRESS:
				return ADDRESS_EDEFAULT == null ? address != null : !ADDRESS_EDEFAULT.equals(address);
			case UsersPackage.OFFICE_USER__TELEPHONE:
				return TELEPHONE_EDEFAULT == null ? telephone != null : !TELEPHONE_EDEFAULT.equals(telephone);
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
		result.append(" (organization: ");
		result.append(organization);
		result.append(", address: ");
		result.append(address);
		result.append(", telephone: ");
		result.append(telephone);
		result.append(')');
		return result.toString();
	}

} //OfficeUserImpl
