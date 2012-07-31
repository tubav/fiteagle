/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.resources.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import FederationOffice.resources.Resource;
import FederationOffice.resources.ResourceSetting;
import FederationOffice.resources.ResourcesPackage;
import FederationOffice.services.ServiceSetting;
import FederationOffice.services.impl.AbstractSettingImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Setting</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.resources.impl.ResourceSettingImpl#getOnlyConfiguredByResources <em>Only Configured By Resources</em>}</li>
 *   <li>{@link FederationOffice.resources.impl.ResourceSettingImpl#getImplServiceSetting <em>Impl Service Setting</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceSettingImpl extends AbstractSettingImpl implements ResourceSetting {
	/**
	 * The cached value of the '{@link #getOnlyConfiguredByResources() <em>Only Configured By Resources</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnlyConfiguredByResources()
	 * @generated
	 * @ordered
	 */
	protected EList<Resource> onlyConfiguredByResources;

	/**
	 * The cached value of the '{@link #getImplServiceSetting() <em>Impl Service Setting</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplServiceSetting()
	 * @generated
	 * @ordered
	 */
	protected ServiceSetting implServiceSetting;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceSettingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ResourcesPackage.Literals.RESOURCE_SETTING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Resource> getOnlyConfiguredByResources() {
		if (onlyConfiguredByResources == null) {
			onlyConfiguredByResources = new EObjectResolvingEList<Resource>(Resource.class, this, ResourcesPackage.RESOURCE_SETTING__ONLY_CONFIGURED_BY_RESOURCES);
		}
		return onlyConfiguredByResources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceSetting getImplServiceSetting() {
		if (implServiceSetting != null && implServiceSetting.eIsProxy()) {
			InternalEObject oldImplServiceSetting = (InternalEObject)implServiceSetting;
			implServiceSetting = (ServiceSetting)eResolveProxy(oldImplServiceSetting);
			if (implServiceSetting != oldImplServiceSetting) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ResourcesPackage.RESOURCE_SETTING__IMPL_SERVICE_SETTING, oldImplServiceSetting, implServiceSetting));
			}
		}
		return implServiceSetting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceSetting basicGetImplServiceSetting() {
		return implServiceSetting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplServiceSetting(ServiceSetting newImplServiceSetting) {
		ServiceSetting oldImplServiceSetting = implServiceSetting;
		implServiceSetting = newImplServiceSetting;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcesPackage.RESOURCE_SETTING__IMPL_SERVICE_SETTING, oldImplServiceSetting, implServiceSetting));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ResourcesPackage.RESOURCE_SETTING__ONLY_CONFIGURED_BY_RESOURCES:
				return getOnlyConfiguredByResources();
			case ResourcesPackage.RESOURCE_SETTING__IMPL_SERVICE_SETTING:
				if (resolve) return getImplServiceSetting();
				return basicGetImplServiceSetting();
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
			case ResourcesPackage.RESOURCE_SETTING__ONLY_CONFIGURED_BY_RESOURCES:
				getOnlyConfiguredByResources().clear();
				getOnlyConfiguredByResources().addAll((Collection<? extends Resource>)newValue);
				return;
			case ResourcesPackage.RESOURCE_SETTING__IMPL_SERVICE_SETTING:
				setImplServiceSetting((ServiceSetting)newValue);
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
			case ResourcesPackage.RESOURCE_SETTING__ONLY_CONFIGURED_BY_RESOURCES:
				getOnlyConfiguredByResources().clear();
				return;
			case ResourcesPackage.RESOURCE_SETTING__IMPL_SERVICE_SETTING:
				setImplServiceSetting((ServiceSetting)null);
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
			case ResourcesPackage.RESOURCE_SETTING__ONLY_CONFIGURED_BY_RESOURCES:
				return onlyConfiguredByResources != null && !onlyConfiguredByResources.isEmpty();
			case ResourcesPackage.RESOURCE_SETTING__IMPL_SERVICE_SETTING:
				return implServiceSetting != null;
		}
		return super.eIsSet(featureID);
	}

} //ResourceSettingImpl
