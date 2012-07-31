/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.federationscenarios.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import FederationOffice.federationscenarios.FederationscenariosPackage;
import FederationOffice.federationscenarios.SettingInstance;
import FederationOffice.impl.NamedElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Setting Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.federationscenarios.impl.SettingInstanceImpl#getStaticValue <em>Static Value</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.impl.SettingInstanceImpl#getAssignSetting <em>Assign Setting</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SettingInstanceImpl extends NamedElementImpl implements SettingInstance {
	/**
	 * The default value of the '{@link #getStaticValue() <em>Static Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStaticValue()
	 * @generated
	 * @ordered
	 */
	protected static final String STATIC_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStaticValue() <em>Static Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStaticValue()
	 * @generated
	 * @ordered
	 */
	protected String staticValue = STATIC_VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAssignSetting() <em>Assign Setting</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssignSetting()
	 * @generated
	 * @ordered
	 */
	protected EList<SettingInstance> assignSetting;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SettingInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FederationscenariosPackage.Literals.SETTING_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStaticValue() {
		return staticValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStaticValue(String newStaticValue) {
		String oldStaticValue = staticValue;
		staticValue = newStaticValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.SETTING_INSTANCE__STATIC_VALUE, oldStaticValue, staticValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SettingInstance> getAssignSetting() {
		if (assignSetting == null) {
			assignSetting = new EObjectResolvingEList<SettingInstance>(SettingInstance.class, this, FederationscenariosPackage.SETTING_INSTANCE__ASSIGN_SETTING);
		}
		return assignSetting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FederationscenariosPackage.SETTING_INSTANCE__STATIC_VALUE:
				return getStaticValue();
			case FederationscenariosPackage.SETTING_INSTANCE__ASSIGN_SETTING:
				return getAssignSetting();
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
			case FederationscenariosPackage.SETTING_INSTANCE__STATIC_VALUE:
				setStaticValue((String)newValue);
				return;
			case FederationscenariosPackage.SETTING_INSTANCE__ASSIGN_SETTING:
				getAssignSetting().clear();
				getAssignSetting().addAll((Collection<? extends SettingInstance>)newValue);
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
			case FederationscenariosPackage.SETTING_INSTANCE__STATIC_VALUE:
				setStaticValue(STATIC_VALUE_EDEFAULT);
				return;
			case FederationscenariosPackage.SETTING_INSTANCE__ASSIGN_SETTING:
				getAssignSetting().clear();
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
			case FederationscenariosPackage.SETTING_INSTANCE__STATIC_VALUE:
				return STATIC_VALUE_EDEFAULT == null ? staticValue != null : !STATIC_VALUE_EDEFAULT.equals(staticValue);
			case FederationscenariosPackage.SETTING_INSTANCE__ASSIGN_SETTING:
				return assignSetting != null && !assignSetting.isEmpty();
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
		result.append(" (staticValue: ");
		result.append(staticValue);
		result.append(')');
		return result.toString();
	}

} //SettingInstanceImpl
