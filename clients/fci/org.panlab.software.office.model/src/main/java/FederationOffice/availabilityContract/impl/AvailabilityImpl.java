/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.availabilityContract.impl;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import FederationOffice.availabilityContract.Availability;
import FederationOffice.availabilityContract.AvailabilityContractPackage;
import FederationOffice.availabilityContract.Cost;
import FederationOffice.availabilityContract.RepatabilityScheme;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Availability</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.availabilityContract.impl.AvailabilityImpl#getFromTime <em>From Time</em>}</li>
 *   <li>{@link FederationOffice.availabilityContract.impl.AvailabilityImpl#getToTime <em>To Time</em>}</li>
 *   <li>{@link FederationOffice.availabilityContract.impl.AvailabilityImpl#getCost <em>Cost</em>}</li>
 *   <li>{@link FederationOffice.availabilityContract.impl.AvailabilityImpl#getFromDate <em>From Date</em>}</li>
 *   <li>{@link FederationOffice.availabilityContract.impl.AvailabilityImpl#getToDate <em>To Date</em>}</li>
 *   <li>{@link FederationOffice.availabilityContract.impl.AvailabilityImpl#getRepeatability <em>Repeatability</em>}</li>
 *   <li>{@link FederationOffice.availabilityContract.impl.AvailabilityImpl#isRepeat <em>Repeat</em>}</li>
 *   <li>{@link FederationOffice.availabilityContract.impl.AvailabilityImpl#getRepeatUntil <em>Repeat Until</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AvailabilityImpl extends EObjectImpl implements Availability {
	/**
	 * The default value of the '{@link #getFromTime() <em>From Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFromTime()
	 * @generated
	 * @ordered
	 */
	protected static final Date FROM_TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFromTime() <em>From Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFromTime()
	 * @generated
	 * @ordered
	 */
	protected Date fromTime = FROM_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getToTime() <em>To Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToTime()
	 * @generated
	 * @ordered
	 */
	protected static final Date TO_TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getToTime() <em>To Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToTime()
	 * @generated
	 * @ordered
	 */
	protected Date toTime = TO_TIME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCost() <em>Cost</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCost()
	 * @generated
	 * @ordered
	 */
	protected Cost cost;

	/**
	 * The default value of the '{@link #getFromDate() <em>From Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFromDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date FROM_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFromDate() <em>From Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFromDate()
	 * @generated
	 * @ordered
	 */
	protected Date fromDate = FROM_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getToDate() <em>To Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date TO_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getToDate() <em>To Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToDate()
	 * @generated
	 * @ordered
	 */
	protected Date toDate = TO_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRepeatability() <em>Repeatability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepeatability()
	 * @generated
	 * @ordered
	 */
	protected static final RepatabilityScheme REPEATABILITY_EDEFAULT = RepatabilityScheme.DAILY;

	/**
	 * The cached value of the '{@link #getRepeatability() <em>Repeatability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepeatability()
	 * @generated
	 * @ordered
	 */
	protected RepatabilityScheme repeatability = REPEATABILITY_EDEFAULT;

	/**
	 * The default value of the '{@link #isRepeat() <em>Repeat</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRepeat()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REPEAT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRepeat() <em>Repeat</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRepeat()
	 * @generated
	 * @ordered
	 */
	protected boolean repeat = REPEAT_EDEFAULT;

	/**
	 * The default value of the '{@link #getRepeatUntil() <em>Repeat Until</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepeatUntil()
	 * @generated
	 * @ordered
	 */
	protected static final Date REPEAT_UNTIL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRepeatUntil() <em>Repeat Until</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepeatUntil()
	 * @generated
	 * @ordered
	 */
	protected Date repeatUntil = REPEAT_UNTIL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AvailabilityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AvailabilityContractPackage.Literals.AVAILABILITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getFromTime() {
		return fromTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFromTime(Date newFromTime) {
		Date oldFromTime = fromTime;
		fromTime = newFromTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AvailabilityContractPackage.AVAILABILITY__FROM_TIME, oldFromTime, fromTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getToTime() {
		return toTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setToTime(Date newToTime) {
		Date oldToTime = toTime;
		toTime = newToTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AvailabilityContractPackage.AVAILABILITY__TO_TIME, oldToTime, toTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Cost getCost() {
		if (cost != null && cost.eIsProxy()) {
			InternalEObject oldCost = (InternalEObject)cost;
			cost = (Cost)eResolveProxy(oldCost);
			if (cost != oldCost) {
				InternalEObject newCost = (InternalEObject)cost;
				NotificationChain msgs = oldCost.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AvailabilityContractPackage.AVAILABILITY__COST, null, null);
				if (newCost.eInternalContainer() == null) {
					msgs = newCost.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AvailabilityContractPackage.AVAILABILITY__COST, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AvailabilityContractPackage.AVAILABILITY__COST, oldCost, cost));
			}
		}
		return cost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Cost basicGetCost() {
		return cost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCost(Cost newCost, NotificationChain msgs) {
		Cost oldCost = cost;
		cost = newCost;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AvailabilityContractPackage.AVAILABILITY__COST, oldCost, newCost);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCost(Cost newCost) {
		if (newCost != cost) {
			NotificationChain msgs = null;
			if (cost != null)
				msgs = ((InternalEObject)cost).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AvailabilityContractPackage.AVAILABILITY__COST, null, msgs);
			if (newCost != null)
				msgs = ((InternalEObject)newCost).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AvailabilityContractPackage.AVAILABILITY__COST, null, msgs);
			msgs = basicSetCost(newCost, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AvailabilityContractPackage.AVAILABILITY__COST, newCost, newCost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFromDate(Date newFromDate) {
		Date oldFromDate = fromDate;
		fromDate = newFromDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AvailabilityContractPackage.AVAILABILITY__FROM_DATE, oldFromDate, fromDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setToDate(Date newToDate) {
		Date oldToDate = toDate;
		toDate = newToDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AvailabilityContractPackage.AVAILABILITY__TO_DATE, oldToDate, toDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RepatabilityScheme getRepeatability() {
		return repeatability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRepeatability(RepatabilityScheme newRepeatability) {
		RepatabilityScheme oldRepeatability = repeatability;
		repeatability = newRepeatability == null ? REPEATABILITY_EDEFAULT : newRepeatability;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AvailabilityContractPackage.AVAILABILITY__REPEATABILITY, oldRepeatability, repeatability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRepeat() {
		return repeat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRepeat(boolean newRepeat) {
		boolean oldRepeat = repeat;
		repeat = newRepeat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AvailabilityContractPackage.AVAILABILITY__REPEAT, oldRepeat, repeat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getRepeatUntil() {
		return repeatUntil;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRepeatUntil(Date newRepeatUntil) {
		Date oldRepeatUntil = repeatUntil;
		repeatUntil = newRepeatUntil;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AvailabilityContractPackage.AVAILABILITY__REPEAT_UNTIL, oldRepeatUntil, repeatUntil));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AvailabilityContractPackage.AVAILABILITY__COST:
				return basicSetCost(null, msgs);
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
			case AvailabilityContractPackage.AVAILABILITY__FROM_TIME:
				return getFromTime();
			case AvailabilityContractPackage.AVAILABILITY__TO_TIME:
				return getToTime();
			case AvailabilityContractPackage.AVAILABILITY__COST:
				if (resolve) return getCost();
				return basicGetCost();
			case AvailabilityContractPackage.AVAILABILITY__FROM_DATE:
				return getFromDate();
			case AvailabilityContractPackage.AVAILABILITY__TO_DATE:
				return getToDate();
			case AvailabilityContractPackage.AVAILABILITY__REPEATABILITY:
				return getRepeatability();
			case AvailabilityContractPackage.AVAILABILITY__REPEAT:
				return isRepeat();
			case AvailabilityContractPackage.AVAILABILITY__REPEAT_UNTIL:
				return getRepeatUntil();
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
			case AvailabilityContractPackage.AVAILABILITY__FROM_TIME:
				setFromTime((Date)newValue);
				return;
			case AvailabilityContractPackage.AVAILABILITY__TO_TIME:
				setToTime((Date)newValue);
				return;
			case AvailabilityContractPackage.AVAILABILITY__COST:
				setCost((Cost)newValue);
				return;
			case AvailabilityContractPackage.AVAILABILITY__FROM_DATE:
				setFromDate((Date)newValue);
				return;
			case AvailabilityContractPackage.AVAILABILITY__TO_DATE:
				setToDate((Date)newValue);
				return;
			case AvailabilityContractPackage.AVAILABILITY__REPEATABILITY:
				setRepeatability((RepatabilityScheme)newValue);
				return;
			case AvailabilityContractPackage.AVAILABILITY__REPEAT:
				setRepeat((Boolean)newValue);
				return;
			case AvailabilityContractPackage.AVAILABILITY__REPEAT_UNTIL:
				setRepeatUntil((Date)newValue);
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
			case AvailabilityContractPackage.AVAILABILITY__FROM_TIME:
				setFromTime(FROM_TIME_EDEFAULT);
				return;
			case AvailabilityContractPackage.AVAILABILITY__TO_TIME:
				setToTime(TO_TIME_EDEFAULT);
				return;
			case AvailabilityContractPackage.AVAILABILITY__COST:
				setCost((Cost)null);
				return;
			case AvailabilityContractPackage.AVAILABILITY__FROM_DATE:
				setFromDate(FROM_DATE_EDEFAULT);
				return;
			case AvailabilityContractPackage.AVAILABILITY__TO_DATE:
				setToDate(TO_DATE_EDEFAULT);
				return;
			case AvailabilityContractPackage.AVAILABILITY__REPEATABILITY:
				setRepeatability(REPEATABILITY_EDEFAULT);
				return;
			case AvailabilityContractPackage.AVAILABILITY__REPEAT:
				setRepeat(REPEAT_EDEFAULT);
				return;
			case AvailabilityContractPackage.AVAILABILITY__REPEAT_UNTIL:
				setRepeatUntil(REPEAT_UNTIL_EDEFAULT);
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
			case AvailabilityContractPackage.AVAILABILITY__FROM_TIME:
				return FROM_TIME_EDEFAULT == null ? fromTime != null : !FROM_TIME_EDEFAULT.equals(fromTime);
			case AvailabilityContractPackage.AVAILABILITY__TO_TIME:
				return TO_TIME_EDEFAULT == null ? toTime != null : !TO_TIME_EDEFAULT.equals(toTime);
			case AvailabilityContractPackage.AVAILABILITY__COST:
				return cost != null;
			case AvailabilityContractPackage.AVAILABILITY__FROM_DATE:
				return FROM_DATE_EDEFAULT == null ? fromDate != null : !FROM_DATE_EDEFAULT.equals(fromDate);
			case AvailabilityContractPackage.AVAILABILITY__TO_DATE:
				return TO_DATE_EDEFAULT == null ? toDate != null : !TO_DATE_EDEFAULT.equals(toDate);
			case AvailabilityContractPackage.AVAILABILITY__REPEATABILITY:
				return repeatability != REPEATABILITY_EDEFAULT;
			case AvailabilityContractPackage.AVAILABILITY__REPEAT:
				return repeat != REPEAT_EDEFAULT;
			case AvailabilityContractPackage.AVAILABILITY__REPEAT_UNTIL:
				return REPEAT_UNTIL_EDEFAULT == null ? repeatUntil != null : !REPEAT_UNTIL_EDEFAULT.equals(repeatUntil);
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
		result.append(" (FromTime: ");
		result.append(fromTime);
		result.append(", ToTime: ");
		result.append(toTime);
		result.append(", FromDate: ");
		result.append(fromDate);
		result.append(", ToDate: ");
		result.append(toDate);
		result.append(", Repeatability: ");
		result.append(repeatability);
		result.append(", Repeat: ");
		result.append(repeat);
		result.append(", RepeatUntil: ");
		result.append(repeatUntil);
		result.append(')');
		return result.toString();
	}

} //AvailabilityImpl
