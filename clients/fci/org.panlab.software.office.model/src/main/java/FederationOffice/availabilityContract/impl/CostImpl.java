/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.availabilityContract.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import FederationOffice.availabilityContract.AvailabilityContractPackage;
import FederationOffice.availabilityContract.Cost;
import FederationOffice.availabilityContract.costUnit;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cost</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.availabilityContract.impl.CostImpl#getPerUnit <em>Per Unit</em>}</li>
 *   <li>{@link FederationOffice.availabilityContract.impl.CostImpl#getAmount <em>Amount</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CostImpl extends EObjectImpl implements Cost {
	/**
	 * The default value of the '{@link #getPerUnit() <em>Per Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerUnit()
	 * @generated
	 * @ordered
	 */
	protected static final costUnit PER_UNIT_EDEFAULT = costUnit.MIN;

	/**
	 * The cached value of the '{@link #getPerUnit() <em>Per Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerUnit()
	 * @generated
	 * @ordered
	 */
	protected costUnit perUnit = PER_UNIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getAmount() <em>Amount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAmount()
	 * @generated
	 * @ordered
	 */
	protected static final Double AMOUNT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAmount() <em>Amount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAmount()
	 * @generated
	 * @ordered
	 */
	protected Double amount = AMOUNT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CostImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AvailabilityContractPackage.Literals.COST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAmount(Double newAmount) {
		Double oldAmount = amount;
		amount = newAmount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AvailabilityContractPackage.COST__AMOUNT, oldAmount, amount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public costUnit getPerUnit() {
		return perUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPerUnit(costUnit newPerUnit) {
		costUnit oldPerUnit = perUnit;
		perUnit = newPerUnit == null ? PER_UNIT_EDEFAULT : newPerUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AvailabilityContractPackage.COST__PER_UNIT, oldPerUnit, perUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AvailabilityContractPackage.COST__PER_UNIT:
				return getPerUnit();
			case AvailabilityContractPackage.COST__AMOUNT:
				return getAmount();
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
			case AvailabilityContractPackage.COST__PER_UNIT:
				setPerUnit((costUnit)newValue);
				return;
			case AvailabilityContractPackage.COST__AMOUNT:
				setAmount((Double)newValue);
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
			case AvailabilityContractPackage.COST__PER_UNIT:
				setPerUnit(PER_UNIT_EDEFAULT);
				return;
			case AvailabilityContractPackage.COST__AMOUNT:
				setAmount(AMOUNT_EDEFAULT);
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
			case AvailabilityContractPackage.COST__PER_UNIT:
				return perUnit != PER_UNIT_EDEFAULT;
			case AvailabilityContractPackage.COST__AMOUNT:
				return AMOUNT_EDEFAULT == null ? amount != null : !AMOUNT_EDEFAULT.equals(amount);
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
		result.append(" (perUnit: ");
		result.append(perUnit);
		result.append(", Amount: ");
		result.append(amount);
		result.append(')');
		return result.toString();
	}

} //CostImpl
