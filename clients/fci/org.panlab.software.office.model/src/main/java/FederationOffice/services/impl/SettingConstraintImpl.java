/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.services.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import FederationOffice.impl.NamedElementImpl;
import FederationOffice.services.ServiceResourceOperation;
import FederationOffice.services.ServicesPackage;
import FederationOffice.services.SettingConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Setting Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.services.impl.SettingConstraintImpl#getForOperation <em>For Operation</em>}</li>
 *   <li>{@link FederationOffice.services.impl.SettingConstraintImpl#isAvailableAfterOperation <em>Available After Operation</em>}</li>
 *   <li>{@link FederationOffice.services.impl.SettingConstraintImpl#isRequiredBeforeOperation <em>Required Before Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SettingConstraintImpl extends NamedElementImpl implements SettingConstraint {
	/**
	 * The default value of the '{@link #getForOperation() <em>For Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForOperation()
	 * @generated
	 * @ordered
	 */
	protected static final ServiceResourceOperation FOR_OPERATION_EDEFAULT = ServiceResourceOperation.OP_CREATE;

	/**
	 * The cached value of the '{@link #getForOperation() <em>For Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForOperation()
	 * @generated
	 * @ordered
	 */
	protected ServiceResourceOperation forOperation = FOR_OPERATION_EDEFAULT;

	/**
	 * The default value of the '{@link #isAvailableAfterOperation() <em>Available After Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAvailableAfterOperation()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AVAILABLE_AFTER_OPERATION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAvailableAfterOperation() <em>Available After Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAvailableAfterOperation()
	 * @generated
	 * @ordered
	 */
	protected boolean availableAfterOperation = AVAILABLE_AFTER_OPERATION_EDEFAULT;

	/**
	 * The default value of the '{@link #isRequiredBeforeOperation() <em>Required Before Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequiredBeforeOperation()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REQUIRED_BEFORE_OPERATION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRequiredBeforeOperation() <em>Required Before Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequiredBeforeOperation()
	 * @generated
	 * @ordered
	 */
	protected boolean requiredBeforeOperation = REQUIRED_BEFORE_OPERATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SettingConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ServicesPackage.Literals.SETTING_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceResourceOperation getForOperation() {
		return forOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForOperation(ServiceResourceOperation newForOperation) {
		ServiceResourceOperation oldForOperation = forOperation;
		forOperation = newForOperation == null ? FOR_OPERATION_EDEFAULT : newForOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServicesPackage.SETTING_CONSTRAINT__FOR_OPERATION, oldForOperation, forOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAvailableAfterOperation() {
		return availableAfterOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAvailableAfterOperation(boolean newAvailableAfterOperation) {
		boolean oldAvailableAfterOperation = availableAfterOperation;
		availableAfterOperation = newAvailableAfterOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServicesPackage.SETTING_CONSTRAINT__AVAILABLE_AFTER_OPERATION, oldAvailableAfterOperation, availableAfterOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRequiredBeforeOperation() {
		return requiredBeforeOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRequiredBeforeOperation(boolean newRequiredBeforeOperation) {
		boolean oldRequiredBeforeOperation = requiredBeforeOperation;
		requiredBeforeOperation = newRequiredBeforeOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServicesPackage.SETTING_CONSTRAINT__REQUIRED_BEFORE_OPERATION, oldRequiredBeforeOperation, requiredBeforeOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ServicesPackage.SETTING_CONSTRAINT__FOR_OPERATION:
				return getForOperation();
			case ServicesPackage.SETTING_CONSTRAINT__AVAILABLE_AFTER_OPERATION:
				return isAvailableAfterOperation();
			case ServicesPackage.SETTING_CONSTRAINT__REQUIRED_BEFORE_OPERATION:
				return isRequiredBeforeOperation();
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
			case ServicesPackage.SETTING_CONSTRAINT__FOR_OPERATION:
				setForOperation((ServiceResourceOperation)newValue);
				return;
			case ServicesPackage.SETTING_CONSTRAINT__AVAILABLE_AFTER_OPERATION:
				setAvailableAfterOperation((Boolean)newValue);
				return;
			case ServicesPackage.SETTING_CONSTRAINT__REQUIRED_BEFORE_OPERATION:
				setRequiredBeforeOperation((Boolean)newValue);
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
			case ServicesPackage.SETTING_CONSTRAINT__FOR_OPERATION:
				setForOperation(FOR_OPERATION_EDEFAULT);
				return;
			case ServicesPackage.SETTING_CONSTRAINT__AVAILABLE_AFTER_OPERATION:
				setAvailableAfterOperation(AVAILABLE_AFTER_OPERATION_EDEFAULT);
				return;
			case ServicesPackage.SETTING_CONSTRAINT__REQUIRED_BEFORE_OPERATION:
				setRequiredBeforeOperation(REQUIRED_BEFORE_OPERATION_EDEFAULT);
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
			case ServicesPackage.SETTING_CONSTRAINT__FOR_OPERATION:
				return forOperation != FOR_OPERATION_EDEFAULT;
			case ServicesPackage.SETTING_CONSTRAINT__AVAILABLE_AFTER_OPERATION:
				return availableAfterOperation != AVAILABLE_AFTER_OPERATION_EDEFAULT;
			case ServicesPackage.SETTING_CONSTRAINT__REQUIRED_BEFORE_OPERATION:
				return requiredBeforeOperation != REQUIRED_BEFORE_OPERATION_EDEFAULT;
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
		result.append(" (ForOperation: ");
		result.append(forOperation);
		result.append(", AvailableAfterOperation: ");
		result.append(availableAfterOperation);
		result.append(", RequiredBeforeOperation: ");
		result.append(requiredBeforeOperation);
		result.append(')');
		return result.toString();
	}

} //SettingConstraintImpl
