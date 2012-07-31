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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import FederationOffice.impl.NamedElementImpl;
import FederationOffice.services.AbstractSetting;
import FederationOffice.services.ServicesPackage;
import FederationOffice.services.SettingConstraint;
import FederationOffice.services.SettingType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Setting</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.services.impl.AbstractSettingImpl#getSettingType <em>Setting Type</em>}</li>
 *   <li>{@link FederationOffice.services.impl.AbstractSettingImpl#isUserExposed <em>User Exposed</em>}</li>
 *   <li>{@link FederationOffice.services.impl.AbstractSettingImpl#isUserEditable <em>User Editable</em>}</li>
 *   <li>{@link FederationOffice.services.impl.AbstractSettingImpl#isCanBePublished <em>Can Be Published</em>}</li>
 *   <li>{@link FederationOffice.services.impl.AbstractSettingImpl#isReadable <em>Readable</em>}</li>
 *   <li>{@link FederationOffice.services.impl.AbstractSettingImpl#isWritable <em>Writable</em>}</li>
 *   <li>{@link FederationOffice.services.impl.AbstractSettingImpl#getSettingConstraints <em>Setting Constraints</em>}</li>
 *   <li>{@link FederationOffice.services.impl.AbstractSettingImpl#getRequiresParams <em>Requires Params</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AbstractSettingImpl extends NamedElementImpl implements AbstractSetting {
	/**
	 * The cached value of the '{@link #getSettingType() <em>Setting Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSettingType()
	 * @generated
	 * @ordered
	 */
	protected SettingType settingType;

	/**
	 * The default value of the '{@link #isUserExposed() <em>User Exposed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUserExposed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean USER_EXPOSED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUserExposed() <em>User Exposed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUserExposed()
	 * @generated
	 * @ordered
	 */
	protected boolean userExposed = USER_EXPOSED_EDEFAULT;

	/**
	 * The default value of the '{@link #isUserEditable() <em>User Editable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUserEditable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean USER_EDITABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUserEditable() <em>User Editable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUserEditable()
	 * @generated
	 * @ordered
	 */
	protected boolean userEditable = USER_EDITABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isCanBePublished() <em>Can Be Published</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCanBePublished()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CAN_BE_PUBLISHED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCanBePublished() <em>Can Be Published</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCanBePublished()
	 * @generated
	 * @ordered
	 */
	protected boolean canBePublished = CAN_BE_PUBLISHED_EDEFAULT;

	/**
	 * The default value of the '{@link #isReadable() <em>Readable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReadable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean READABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReadable() <em>Readable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReadable()
	 * @generated
	 * @ordered
	 */
	protected boolean readable = READABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isWritable() <em>Writable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWritable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean WRITABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isWritable() <em>Writable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWritable()
	 * @generated
	 * @ordered
	 */
	protected boolean writable = WRITABLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSettingConstraints() <em>Setting Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSettingConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<SettingConstraint> settingConstraints;

	/**
	 * The cached value of the '{@link #getRequiresParams() <em>Requires Params</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiresParams()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractSetting> requiresParams;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractSettingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ServicesPackage.Literals.ABSTRACT_SETTING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SettingType getSettingType() {
		if (settingType != null && settingType.eIsProxy()) {
			InternalEObject oldSettingType = (InternalEObject)settingType;
			settingType = (SettingType)eResolveProxy(oldSettingType);
			if (settingType != oldSettingType) {
				InternalEObject newSettingType = (InternalEObject)settingType;
				NotificationChain msgs = oldSettingType.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ServicesPackage.ABSTRACT_SETTING__SETTING_TYPE, null, null);
				if (newSettingType.eInternalContainer() == null) {
					msgs = newSettingType.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ServicesPackage.ABSTRACT_SETTING__SETTING_TYPE, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ServicesPackage.ABSTRACT_SETTING__SETTING_TYPE, oldSettingType, settingType));
			}
		}
		return settingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SettingType basicGetSettingType() {
		return settingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSettingType(SettingType newSettingType, NotificationChain msgs) {
		SettingType oldSettingType = settingType;
		settingType = newSettingType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ServicesPackage.ABSTRACT_SETTING__SETTING_TYPE, oldSettingType, newSettingType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSettingType(SettingType newSettingType) {
		if (newSettingType != settingType) {
			NotificationChain msgs = null;
			if (settingType != null)
				msgs = ((InternalEObject)settingType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ServicesPackage.ABSTRACT_SETTING__SETTING_TYPE, null, msgs);
			if (newSettingType != null)
				msgs = ((InternalEObject)newSettingType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ServicesPackage.ABSTRACT_SETTING__SETTING_TYPE, null, msgs);
			msgs = basicSetSettingType(newSettingType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServicesPackage.ABSTRACT_SETTING__SETTING_TYPE, newSettingType, newSettingType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUserExposed() {
		return userExposed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserExposed(boolean newUserExposed) {
		boolean oldUserExposed = userExposed;
		userExposed = newUserExposed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServicesPackage.ABSTRACT_SETTING__USER_EXPOSED, oldUserExposed, userExposed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUserEditable() {
		return userEditable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserEditable(boolean newUserEditable) {
		boolean oldUserEditable = userEditable;
		userEditable = newUserEditable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServicesPackage.ABSTRACT_SETTING__USER_EDITABLE, oldUserEditable, userEditable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCanBePublished() {
		return canBePublished;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCanBePublished(boolean newCanBePublished) {
		boolean oldCanBePublished = canBePublished;
		canBePublished = newCanBePublished;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServicesPackage.ABSTRACT_SETTING__CAN_BE_PUBLISHED, oldCanBePublished, canBePublished));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReadable() {
		return readable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReadable(boolean newReadable) {
		boolean oldReadable = readable;
		readable = newReadable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServicesPackage.ABSTRACT_SETTING__READABLE, oldReadable, readable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isWritable() {
		return writable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWritable(boolean newWritable) {
		boolean oldWritable = writable;
		writable = newWritable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServicesPackage.ABSTRACT_SETTING__WRITABLE, oldWritable, writable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SettingConstraint> getSettingConstraints() {
		if (settingConstraints == null) {
			settingConstraints = new EObjectContainmentEList.Resolving<SettingConstraint>(SettingConstraint.class, this, ServicesPackage.ABSTRACT_SETTING__SETTING_CONSTRAINTS);
		}
		return settingConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractSetting> getRequiresParams() {
		if (requiresParams == null) {
			requiresParams = new EObjectResolvingEList<AbstractSetting>(AbstractSetting.class, this, ServicesPackage.ABSTRACT_SETTING__REQUIRES_PARAMS);
		}
		return requiresParams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ServicesPackage.ABSTRACT_SETTING__SETTING_TYPE:
				return basicSetSettingType(null, msgs);
			case ServicesPackage.ABSTRACT_SETTING__SETTING_CONSTRAINTS:
				return ((InternalEList<?>)getSettingConstraints()).basicRemove(otherEnd, msgs);
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
			case ServicesPackage.ABSTRACT_SETTING__SETTING_TYPE:
				if (resolve) return getSettingType();
				return basicGetSettingType();
			case ServicesPackage.ABSTRACT_SETTING__USER_EXPOSED:
				return isUserExposed();
			case ServicesPackage.ABSTRACT_SETTING__USER_EDITABLE:
				return isUserEditable();
			case ServicesPackage.ABSTRACT_SETTING__CAN_BE_PUBLISHED:
				return isCanBePublished();
			case ServicesPackage.ABSTRACT_SETTING__READABLE:
				return isReadable();
			case ServicesPackage.ABSTRACT_SETTING__WRITABLE:
				return isWritable();
			case ServicesPackage.ABSTRACT_SETTING__SETTING_CONSTRAINTS:
				return getSettingConstraints();
			case ServicesPackage.ABSTRACT_SETTING__REQUIRES_PARAMS:
				return getRequiresParams();
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
			case ServicesPackage.ABSTRACT_SETTING__SETTING_TYPE:
				setSettingType((SettingType)newValue);
				return;
			case ServicesPackage.ABSTRACT_SETTING__USER_EXPOSED:
				setUserExposed((Boolean)newValue);
				return;
			case ServicesPackage.ABSTRACT_SETTING__USER_EDITABLE:
				setUserEditable((Boolean)newValue);
				return;
			case ServicesPackage.ABSTRACT_SETTING__CAN_BE_PUBLISHED:
				setCanBePublished((Boolean)newValue);
				return;
			case ServicesPackage.ABSTRACT_SETTING__READABLE:
				setReadable((Boolean)newValue);
				return;
			case ServicesPackage.ABSTRACT_SETTING__WRITABLE:
				setWritable((Boolean)newValue);
				return;
			case ServicesPackage.ABSTRACT_SETTING__SETTING_CONSTRAINTS:
				getSettingConstraints().clear();
				getSettingConstraints().addAll((Collection<? extends SettingConstraint>)newValue);
				return;
			case ServicesPackage.ABSTRACT_SETTING__REQUIRES_PARAMS:
				getRequiresParams().clear();
				getRequiresParams().addAll((Collection<? extends AbstractSetting>)newValue);
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
			case ServicesPackage.ABSTRACT_SETTING__SETTING_TYPE:
				setSettingType((SettingType)null);
				return;
			case ServicesPackage.ABSTRACT_SETTING__USER_EXPOSED:
				setUserExposed(USER_EXPOSED_EDEFAULT);
				return;
			case ServicesPackage.ABSTRACT_SETTING__USER_EDITABLE:
				setUserEditable(USER_EDITABLE_EDEFAULT);
				return;
			case ServicesPackage.ABSTRACT_SETTING__CAN_BE_PUBLISHED:
				setCanBePublished(CAN_BE_PUBLISHED_EDEFAULT);
				return;
			case ServicesPackage.ABSTRACT_SETTING__READABLE:
				setReadable(READABLE_EDEFAULT);
				return;
			case ServicesPackage.ABSTRACT_SETTING__WRITABLE:
				setWritable(WRITABLE_EDEFAULT);
				return;
			case ServicesPackage.ABSTRACT_SETTING__SETTING_CONSTRAINTS:
				getSettingConstraints().clear();
				return;
			case ServicesPackage.ABSTRACT_SETTING__REQUIRES_PARAMS:
				getRequiresParams().clear();
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
			case ServicesPackage.ABSTRACT_SETTING__SETTING_TYPE:
				return settingType != null;
			case ServicesPackage.ABSTRACT_SETTING__USER_EXPOSED:
				return userExposed != USER_EXPOSED_EDEFAULT;
			case ServicesPackage.ABSTRACT_SETTING__USER_EDITABLE:
				return userEditable != USER_EDITABLE_EDEFAULT;
			case ServicesPackage.ABSTRACT_SETTING__CAN_BE_PUBLISHED:
				return canBePublished != CAN_BE_PUBLISHED_EDEFAULT;
			case ServicesPackage.ABSTRACT_SETTING__READABLE:
				return readable != READABLE_EDEFAULT;
			case ServicesPackage.ABSTRACT_SETTING__WRITABLE:
				return writable != WRITABLE_EDEFAULT;
			case ServicesPackage.ABSTRACT_SETTING__SETTING_CONSTRAINTS:
				return settingConstraints != null && !settingConstraints.isEmpty();
			case ServicesPackage.ABSTRACT_SETTING__REQUIRES_PARAMS:
				return requiresParams != null && !requiresParams.isEmpty();
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
		result.append(" (userExposed: ");
		result.append(userExposed);
		result.append(", userEditable: ");
		result.append(userEditable);
		result.append(", canBePublished: ");
		result.append(canBePublished);
		result.append(", Readable: ");
		result.append(readable);
		result.append(", Writable: ");
		result.append(writable);
		result.append(')');
		return result.toString();
	}

} //AbstractSettingImpl
