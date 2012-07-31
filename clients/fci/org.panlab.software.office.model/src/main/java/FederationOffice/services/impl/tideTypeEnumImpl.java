/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.services.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import FederationOffice.services.ServicesPackage;
import FederationOffice.services.tideTypeEnum;
import FederationOffice.services.tideTypeEnumItem;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>tide Type Enum</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.services.impl.tideTypeEnumImpl#getTideEnumlist <em>Tide Enumlist</em>}</li>
 *   <li>{@link FederationOffice.services.impl.tideTypeEnumImpl#getDefaultValue <em>Default Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class tideTypeEnumImpl extends SettingTypeImpl implements tideTypeEnum {
	/**
	 * The cached value of the '{@link #getTideEnumlist() <em>Tide Enumlist</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTideEnumlist()
	 * @generated
	 * @ordered
	 */
	protected EList<tideTypeEnumItem> tideEnumlist;

	/**
	 * The cached value of the '{@link #getDefaultValue() <em>Default Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected tideTypeEnumItem defaultValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected tideTypeEnumImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ServicesPackage.Literals.TIDE_TYPE_ENUM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<tideTypeEnumItem> getTideEnumlist() {
		if (tideEnumlist == null) {
			tideEnumlist = new EObjectContainmentEList.Resolving<tideTypeEnumItem>(tideTypeEnumItem.class, this, ServicesPackage.TIDE_TYPE_ENUM__TIDE_ENUMLIST);
		}
		return tideEnumlist;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public tideTypeEnumItem getDefaultValue() {
		if (defaultValue != null && defaultValue.eIsProxy()) {
			InternalEObject oldDefaultValue = (InternalEObject)defaultValue;
			defaultValue = (tideTypeEnumItem)eResolveProxy(oldDefaultValue);
			if (defaultValue != oldDefaultValue) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ServicesPackage.TIDE_TYPE_ENUM__DEFAULT_VALUE, oldDefaultValue, defaultValue));
			}
		}
		return defaultValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public tideTypeEnumItem basicGetDefaultValue() {
		return defaultValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultValue(tideTypeEnumItem newDefaultValue) {
		tideTypeEnumItem oldDefaultValue = defaultValue;
		defaultValue = newDefaultValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServicesPackage.TIDE_TYPE_ENUM__DEFAULT_VALUE, oldDefaultValue, defaultValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ServicesPackage.TIDE_TYPE_ENUM__TIDE_ENUMLIST:
				return ((InternalEList<?>)getTideEnumlist()).basicRemove(otherEnd, msgs);
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
			case ServicesPackage.TIDE_TYPE_ENUM__TIDE_ENUMLIST:
				return getTideEnumlist();
			case ServicesPackage.TIDE_TYPE_ENUM__DEFAULT_VALUE:
				if (resolve) return getDefaultValue();
				return basicGetDefaultValue();
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
			case ServicesPackage.TIDE_TYPE_ENUM__TIDE_ENUMLIST:
				getTideEnumlist().clear();
				getTideEnumlist().addAll((Collection<? extends tideTypeEnumItem>)newValue);
				return;
			case ServicesPackage.TIDE_TYPE_ENUM__DEFAULT_VALUE:
				setDefaultValue((tideTypeEnumItem)newValue);
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
			case ServicesPackage.TIDE_TYPE_ENUM__TIDE_ENUMLIST:
				getTideEnumlist().clear();
				return;
			case ServicesPackage.TIDE_TYPE_ENUM__DEFAULT_VALUE:
				setDefaultValue((tideTypeEnumItem)null);
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
			case ServicesPackage.TIDE_TYPE_ENUM__TIDE_ENUMLIST:
				return tideEnumlist != null && !tideEnumlist.isEmpty();
			case ServicesPackage.TIDE_TYPE_ENUM__DEFAULT_VALUE:
				return defaultValue != null;
		}
		return super.eIsSet(featureID);
	}

} //tideTypeEnumImpl
