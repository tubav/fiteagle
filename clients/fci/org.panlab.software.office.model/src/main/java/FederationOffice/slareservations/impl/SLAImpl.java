/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.slareservations.impl;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import FederationOffice.federationscenarios.RequestedFederationScenario;
import FederationOffice.impl.NamedElementImpl;
import FederationOffice.slareservations.ReservedResourceContract;
import FederationOffice.slareservations.SLA;
import FederationOffice.slareservations.SlareservationsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SLA</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.slareservations.impl.SLAImpl#getReservedRsources <em>Reserved Rsources</em>}</li>
 *   <li>{@link FederationOffice.slareservations.impl.SLAImpl#getForVT <em>For VT</em>}</li>
 *   <li>{@link FederationOffice.slareservations.impl.SLAImpl#getValidFrom <em>Valid From</em>}</li>
 *   <li>{@link FederationOffice.slareservations.impl.SLAImpl#getValidUntil <em>Valid Until</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SLAImpl extends NamedElementImpl implements SLA {
	/**
	 * The cached value of the '{@link #getReservedRsources() <em>Reserved Rsources</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReservedRsources()
	 * @generated
	 * @ordered
	 */
	protected EList<ReservedResourceContract> reservedRsources;

	/**
	 * The cached value of the '{@link #getForVT() <em>For VT</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForVT()
	 * @generated
	 * @ordered
	 */
	protected RequestedFederationScenario forVT;

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
	protected SLAImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SlareservationsPackage.Literals.SLA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ReservedResourceContract> getReservedRsources() {
		if (reservedRsources == null) {
			reservedRsources = new EObjectContainmentEList.Resolving<ReservedResourceContract>(ReservedResourceContract.class, this, SlareservationsPackage.SLA__RESERVED_RSOURCES);
		}
		return reservedRsources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequestedFederationScenario getForVT() {
		if (forVT != null && forVT.eIsProxy()) {
			InternalEObject oldForVT = (InternalEObject)forVT;
			forVT = (RequestedFederationScenario)eResolveProxy(oldForVT);
			if (forVT != oldForVT) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SlareservationsPackage.SLA__FOR_VT, oldForVT, forVT));
			}
		}
		return forVT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequestedFederationScenario basicGetForVT() {
		return forVT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForVT(RequestedFederationScenario newForVT) {
		RequestedFederationScenario oldForVT = forVT;
		forVT = newForVT;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SlareservationsPackage.SLA__FOR_VT, oldForVT, forVT));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SlareservationsPackage.SLA__VALID_FROM, oldValidFrom, validFrom));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SlareservationsPackage.SLA__VALID_UNTIL, oldValidUntil, validUntil));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SlareservationsPackage.SLA__RESERVED_RSOURCES:
				return ((InternalEList<?>)getReservedRsources()).basicRemove(otherEnd, msgs);
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
			case SlareservationsPackage.SLA__RESERVED_RSOURCES:
				return getReservedRsources();
			case SlareservationsPackage.SLA__FOR_VT:
				if (resolve) return getForVT();
				return basicGetForVT();
			case SlareservationsPackage.SLA__VALID_FROM:
				return getValidFrom();
			case SlareservationsPackage.SLA__VALID_UNTIL:
				return getValidUntil();
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
			case SlareservationsPackage.SLA__RESERVED_RSOURCES:
				getReservedRsources().clear();
				getReservedRsources().addAll((Collection<? extends ReservedResourceContract>)newValue);
				return;
			case SlareservationsPackage.SLA__FOR_VT:
				setForVT((RequestedFederationScenario)newValue);
				return;
			case SlareservationsPackage.SLA__VALID_FROM:
				setValidFrom((Date)newValue);
				return;
			case SlareservationsPackage.SLA__VALID_UNTIL:
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
			case SlareservationsPackage.SLA__RESERVED_RSOURCES:
				getReservedRsources().clear();
				return;
			case SlareservationsPackage.SLA__FOR_VT:
				setForVT((RequestedFederationScenario)null);
				return;
			case SlareservationsPackage.SLA__VALID_FROM:
				setValidFrom(VALID_FROM_EDEFAULT);
				return;
			case SlareservationsPackage.SLA__VALID_UNTIL:
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
			case SlareservationsPackage.SLA__RESERVED_RSOURCES:
				return reservedRsources != null && !reservedRsources.isEmpty();
			case SlareservationsPackage.SLA__FOR_VT:
				return forVT != null;
			case SlareservationsPackage.SLA__VALID_FROM:
				return VALID_FROM_EDEFAULT == null ? validFrom != null : !VALID_FROM_EDEFAULT.equals(validFrom);
			case SlareservationsPackage.SLA__VALID_UNTIL:
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

} //SLAImpl
