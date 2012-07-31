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
import FederationOffice.federationscenarios.ServiceSettingInstance;
import FederationOffice.services.ServiceSetting;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Setting Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.federationscenarios.impl.ServiceSettingInstanceImpl#getRefServiceSetting <em>Ref Service Setting</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceSettingInstanceImpl extends SettingInstanceImpl implements ServiceSettingInstance {
	/**
	 * The cached value of the '{@link #getRefServiceSetting() <em>Ref Service Setting</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefServiceSetting()
	 * @generated
	 * @ordered
	 */
	protected ServiceSetting refServiceSetting;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServiceSettingInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FederationscenariosPackage.Literals.SERVICE_SETTING_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceSetting getRefServiceSetting() {
		if (refServiceSetting != null && refServiceSetting.eIsProxy()) {
			InternalEObject oldRefServiceSetting = (InternalEObject)refServiceSetting;
			refServiceSetting = (ServiceSetting)eResolveProxy(oldRefServiceSetting);
			if (refServiceSetting != oldRefServiceSetting) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FederationscenariosPackage.SERVICE_SETTING_INSTANCE__REF_SERVICE_SETTING, oldRefServiceSetting, refServiceSetting));
			}
		}
		return refServiceSetting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceSetting basicGetRefServiceSetting() {
		return refServiceSetting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRefServiceSetting(ServiceSetting newRefServiceSetting) {
		ServiceSetting oldRefServiceSetting = refServiceSetting;
		refServiceSetting = newRefServiceSetting;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.SERVICE_SETTING_INSTANCE__REF_SERVICE_SETTING, oldRefServiceSetting, refServiceSetting));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FederationscenariosPackage.SERVICE_SETTING_INSTANCE__REF_SERVICE_SETTING:
				if (resolve) return getRefServiceSetting();
				return basicGetRefServiceSetting();
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
			case FederationscenariosPackage.SERVICE_SETTING_INSTANCE__REF_SERVICE_SETTING:
				setRefServiceSetting((ServiceSetting)newValue);
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
			case FederationscenariosPackage.SERVICE_SETTING_INSTANCE__REF_SERVICE_SETTING:
				setRefServiceSetting((ServiceSetting)null);
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
			case FederationscenariosPackage.SERVICE_SETTING_INSTANCE__REF_SERVICE_SETTING:
				return refServiceSetting != null;
		}
		return super.eIsSet(featureID);
	}

} //ServiceSettingInstanceImpl
