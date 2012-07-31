/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.federationscenarios.impl;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import FederationOffice.federationscenarios.FederationscenariosPackage;
import FederationOffice.federationscenarios.ScheduledPlan;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scheduled Plan</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.federationscenarios.impl.ScheduledPlanImpl#getValidFrom <em>Valid From</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.impl.ScheduledPlanImpl#getValidUntil <em>Valid Until</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScheduledPlanImpl extends EObjectImpl implements ScheduledPlan {
	/**
	 * The default value of the '{@link #getValidFrom() <em>Valid From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidFrom()
	 * @generated
	 * @ordered
	 */
	protected static final Date VALID_FROM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValidFrom() <em>Valid From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidFrom()
	 * @generated
	 * @ordered
	 */
	protected Date validFrom = VALID_FROM_EDEFAULT;

	/**
	 * The default value of the '{@link #getValidUntil() <em>Valid Until</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidUntil()
	 * @generated
	 * @ordered
	 */
	protected static final Date VALID_UNTIL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValidUntil() <em>Valid Until</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidUntil()
	 * @generated
	 * @ordered
	 */
	protected Date validUntil = VALID_UNTIL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScheduledPlanImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FederationscenariosPackage.Literals.SCHEDULED_PLAN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getValidFrom() {
		return validFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValidFrom(Date newValidFrom) {
		Date oldValidFrom = validFrom;
		validFrom = newValidFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.SCHEDULED_PLAN__VALID_FROM, oldValidFrom, validFrom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getValidUntil() {
		return validUntil;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValidUntil(Date newValidUntil) {
		Date oldValidUntil = validUntil;
		validUntil = newValidUntil;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.SCHEDULED_PLAN__VALID_UNTIL, oldValidUntil, validUntil));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FederationscenariosPackage.SCHEDULED_PLAN__VALID_FROM:
				return getValidFrom();
			case FederationscenariosPackage.SCHEDULED_PLAN__VALID_UNTIL:
				return getValidUntil();
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
			case FederationscenariosPackage.SCHEDULED_PLAN__VALID_FROM:
				setValidFrom((Date)newValue);
				return;
			case FederationscenariosPackage.SCHEDULED_PLAN__VALID_UNTIL:
				setValidUntil((Date)newValue);
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
			case FederationscenariosPackage.SCHEDULED_PLAN__VALID_FROM:
				setValidFrom(VALID_FROM_EDEFAULT);
				return;
			case FederationscenariosPackage.SCHEDULED_PLAN__VALID_UNTIL:
				setValidUntil(VALID_UNTIL_EDEFAULT);
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
			case FederationscenariosPackage.SCHEDULED_PLAN__VALID_FROM:
				return VALID_FROM_EDEFAULT == null ? validFrom != null : !VALID_FROM_EDEFAULT.equals(validFrom);
			case FederationscenariosPackage.SCHEDULED_PLAN__VALID_UNTIL:
				return VALID_UNTIL_EDEFAULT == null ? validUntil != null : !VALID_UNTIL_EDEFAULT.equals(validUntil);
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
		result.append(" (ValidFrom: ");
		result.append(validFrom);
		result.append(", ValidUntil: ");
		result.append(validUntil);
		result.append(')');
		return result.toString();
	}

} //ScheduledPlanImpl
