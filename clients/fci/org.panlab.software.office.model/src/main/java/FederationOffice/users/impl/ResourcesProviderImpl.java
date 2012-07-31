/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.users.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import FederationOffice.providersite.ProvidersitePackage;
import FederationOffice.providersite.Site;
import FederationOffice.users.ResourcesProvider;
import FederationOffice.users.UsersPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resources Provider</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.users.impl.ResourcesProviderImpl#getOfferedSiteList <em>Offered Site List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourcesProviderImpl extends OfficeUserImpl implements ResourcesProvider {
	/**
	 * The cached value of the '{@link #getOfferedSiteList() <em>Offered Site List</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOfferedSiteList()
	 * @generated
	 * @ordered
	 */
	protected EList<Site> offeredSiteList;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourcesProviderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsersPackage.Literals.RESOURCES_PROVIDER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Site> getOfferedSiteList() {
		if (offeredSiteList == null) {
			offeredSiteList = new EObjectContainmentWithInverseEList.Resolving<Site>(Site.class, this, UsersPackage.RESOURCES_PROVIDER__OFFERED_SITE_LIST, ProvidersitePackage.SITE__BELONGS_TO_PROVIDER);
		}
		return offeredSiteList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UsersPackage.RESOURCES_PROVIDER__OFFERED_SITE_LIST:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOfferedSiteList()).basicAdd(otherEnd, msgs);
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
			case UsersPackage.RESOURCES_PROVIDER__OFFERED_SITE_LIST:
				return ((InternalEList<?>)getOfferedSiteList()).basicRemove(otherEnd, msgs);
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
			case UsersPackage.RESOURCES_PROVIDER__OFFERED_SITE_LIST:
				return getOfferedSiteList();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case UsersPackage.RESOURCES_PROVIDER__OFFERED_SITE_LIST:
				getOfferedSiteList().clear();
				getOfferedSiteList().addAll((Collection<? extends Site>)newValue);
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
			case UsersPackage.RESOURCES_PROVIDER__OFFERED_SITE_LIST:
				getOfferedSiteList().clear();
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
			case UsersPackage.RESOURCES_PROVIDER__OFFERED_SITE_LIST:
				return offeredSiteList != null && !offeredSiteList.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ResourcesProviderImpl
