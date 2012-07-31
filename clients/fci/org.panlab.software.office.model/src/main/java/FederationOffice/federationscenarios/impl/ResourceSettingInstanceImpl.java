/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.federationscenarios.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import FederationOffice.federationscenarios.FederationscenariosPackage;
import FederationOffice.federationscenarios.ResourceSettingInstance;
import FederationOffice.resources.ResourceSetting;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Setting Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.federationscenarios.impl.ResourceSettingInstanceImpl#getRefResourceSetting <em>Ref Resource Setting</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceSettingInstanceImpl extends SettingInstanceImpl implements ResourceSettingInstance {
	/**
	 * The cached value of the '{@link #getRefResourceSetting() <em>Ref Resource Setting</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefResourceSetting()
	 * @generated
	 * @ordered
	 */
	protected ResourceSetting refResourceSetting;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceSettingInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FederationscenariosPackage.Literals.RESOURCE_SETTING_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceSetting getRefResourceSetting() {
		if (refResourceSetting != null && refResourceSetting.eIsProxy()) {
			InternalEObject oldRefResourceSetting = (InternalEObject)refResourceSetting;
			refResourceSetting = (ResourceSetting)eResolveProxy(oldRefResourceSetting);
			if (refResourceSetting != oldRefResourceSetting) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FederationscenariosPackage.RESOURCE_SETTING_INSTANCE__REF_RESOURCE_SETTING, oldRefResourceSetting, refResourceSetting));
			}
		}
		return refResourceSetting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceSetting basicGetRefResourceSetting() {
		return refResourceSetting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRefResourceSetting(ResourceSetting newRefResourceSetting) {
		ResourceSetting oldRefResourceSetting = refResourceSetting;
		refResourceSetting = newRefResourceSetting;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.RESOURCE_SETTING_INSTANCE__REF_RESOURCE_SETTING, oldRefResourceSetting, refResourceSetting));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FederationscenariosPackage.RESOURCE_SETTING_INSTANCE__REF_RESOURCE_SETTING:
				if (resolve) return getRefResourceSetting();
				return basicGetRefResourceSetting();
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
			case FederationscenariosPackage.RESOURCE_SETTING_INSTANCE__REF_RESOURCE_SETTING:
				setRefResourceSetting((ResourceSetting)newValue);
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
			case FederationscenariosPackage.RESOURCE_SETTING_INSTANCE__REF_RESOURCE_SETTING:
				setRefResourceSetting((ResourceSetting)null);
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
			case FederationscenariosPackage.RESOURCE_SETTING_INSTANCE__REF_RESOURCE_SETTING:
				return refResourceSetting != null;
		}
		return super.eIsSet(featureID);
	}

} //ResourceSettingInstanceImpl
