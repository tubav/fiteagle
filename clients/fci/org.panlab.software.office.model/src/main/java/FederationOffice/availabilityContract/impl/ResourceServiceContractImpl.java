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

import FederationOffice.availabilityContract.Availability;
import FederationOffice.availabilityContract.AvailabilityContractPackage;
import FederationOffice.availabilityContract.ResourceServiceContract;
import FederationOffice.impl.NamedElementImpl;
import FederationOffice.resources.OfferedResource;
import FederationOffice.services.Service;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Service Contract</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.availabilityContract.impl.ResourceServiceContractImpl#getForOfferedService <em>For Offered Service</em>}</li>
 *   <li>{@link FederationOffice.availabilityContract.impl.ResourceServiceContractImpl#getForOfferedResource <em>For Offered Resource</em>}</li>
 *   <li>{@link FederationOffice.availabilityContract.impl.ResourceServiceContractImpl#getStartDate <em>Start Date</em>}</li>
 *   <li>{@link FederationOffice.availabilityContract.impl.ResourceServiceContractImpl#getEndDate <em>End Date</em>}</li>
 *   <li>{@link FederationOffice.availabilityContract.impl.ResourceServiceContractImpl#getAvailability <em>Availability</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceServiceContractImpl extends NamedElementImpl implements ResourceServiceContract {
	/**
	 * The cached value of the '{@link #getForOfferedService() <em>For Offered Service</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForOfferedService()
	 * @generated
	 * @ordered
	 */
	protected Service forOfferedService;

	/**
	 * The cached value of the '{@link #getForOfferedResource() <em>For Offered Resource</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForOfferedResource()
	 * @generated
	 * @ordered
	 */
	protected OfferedResource forOfferedResource;

	/**
	 * The default value of the '{@link #getStartDate() <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date START_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStartDate() <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartDate()
	 * @generated
	 * @ordered
	 */
	protected Date startDate = START_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getEndDate() <em>End Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date END_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEndDate() <em>End Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndDate()
	 * @generated
	 * @ordered
	 */
	protected Date endDate = END_DATE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAvailability() <em>Availability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailability()
	 * @generated
	 * @ordered
	 */
	protected Availability availability;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceServiceContractImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AvailabilityContractPackage.Literals.RESOURCE_SERVICE_CONTRACT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Service getForOfferedService() {
		if (forOfferedService != null && forOfferedService.eIsProxy()) {
			InternalEObject oldForOfferedService = (InternalEObject)forOfferedService;
			forOfferedService = (Service)eResolveProxy(oldForOfferedService);
			if (forOfferedService != oldForOfferedService) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__FOR_OFFERED_SERVICE, oldForOfferedService, forOfferedService));
			}
		}
		return forOfferedService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Service basicGetForOfferedService() {
		return forOfferedService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForOfferedService(Service newForOfferedService) {
		Service oldForOfferedService = forOfferedService;
		forOfferedService = newForOfferedService;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__FOR_OFFERED_SERVICE, oldForOfferedService, forOfferedService));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OfferedResource getForOfferedResource() {
		if (forOfferedResource != null && forOfferedResource.eIsProxy()) {
			InternalEObject oldForOfferedResource = (InternalEObject)forOfferedResource;
			forOfferedResource = (OfferedResource)eResolveProxy(oldForOfferedResource);
			if (forOfferedResource != oldForOfferedResource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__FOR_OFFERED_RESOURCE, oldForOfferedResource, forOfferedResource));
			}
		}
		return forOfferedResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OfferedResource basicGetForOfferedResource() {
		return forOfferedResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForOfferedResource(OfferedResource newForOfferedResource) {
		OfferedResource oldForOfferedResource = forOfferedResource;
		forOfferedResource = newForOfferedResource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__FOR_OFFERED_RESOURCE, oldForOfferedResource, forOfferedResource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartDate(Date newStartDate) {
		Date oldStartDate = startDate;
		startDate = newStartDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__START_DATE, oldStartDate, startDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndDate(Date newEndDate) {
		Date oldEndDate = endDate;
		endDate = newEndDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__END_DATE, oldEndDate, endDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Availability getAvailability() {
		if (availability != null && availability.eIsProxy()) {
			InternalEObject oldAvailability = (InternalEObject)availability;
			availability = (Availability)eResolveProxy(oldAvailability);
			if (availability != oldAvailability) {
				InternalEObject newAvailability = (InternalEObject)availability;
				NotificationChain msgs = oldAvailability.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__AVAILABILITY, null, null);
				if (newAvailability.eInternalContainer() == null) {
					msgs = newAvailability.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__AVAILABILITY, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__AVAILABILITY, oldAvailability, availability));
			}
		}
		return availability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Availability basicGetAvailability() {
		return availability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAvailability(Availability newAvailability, NotificationChain msgs) {
		Availability oldAvailability = availability;
		availability = newAvailability;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__AVAILABILITY, oldAvailability, newAvailability);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAvailability(Availability newAvailability) {
		if (newAvailability != availability) {
			NotificationChain msgs = null;
			if (availability != null)
				msgs = ((InternalEObject)availability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__AVAILABILITY, null, msgs);
			if (newAvailability != null)
				msgs = ((InternalEObject)newAvailability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__AVAILABILITY, null, msgs);
			msgs = basicSetAvailability(newAvailability, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__AVAILABILITY, newAvailability, newAvailability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__AVAILABILITY:
				return basicSetAvailability(null, msgs);
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
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__FOR_OFFERED_SERVICE:
				if (resolve) return getForOfferedService();
				return basicGetForOfferedService();
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__FOR_OFFERED_RESOURCE:
				if (resolve) return getForOfferedResource();
				return basicGetForOfferedResource();
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__START_DATE:
				return getStartDate();
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__END_DATE:
				return getEndDate();
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__AVAILABILITY:
				if (resolve) return getAvailability();
				return basicGetAvailability();
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
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__FOR_OFFERED_SERVICE:
				setForOfferedService((Service)newValue);
				return;
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__FOR_OFFERED_RESOURCE:
				setForOfferedResource((OfferedResource)newValue);
				return;
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__START_DATE:
				setStartDate((Date)newValue);
				return;
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__END_DATE:
				setEndDate((Date)newValue);
				return;
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__AVAILABILITY:
				setAvailability((Availability)newValue);
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
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__FOR_OFFERED_SERVICE:
				setForOfferedService((Service)null);
				return;
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__FOR_OFFERED_RESOURCE:
				setForOfferedResource((OfferedResource)null);
				return;
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__START_DATE:
				setStartDate(START_DATE_EDEFAULT);
				return;
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__END_DATE:
				setEndDate(END_DATE_EDEFAULT);
				return;
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__AVAILABILITY:
				setAvailability((Availability)null);
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
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__FOR_OFFERED_SERVICE:
				return forOfferedService != null;
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__FOR_OFFERED_RESOURCE:
				return forOfferedResource != null;
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__START_DATE:
				return START_DATE_EDEFAULT == null ? startDate != null : !START_DATE_EDEFAULT.equals(startDate);
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__END_DATE:
				return END_DATE_EDEFAULT == null ? endDate != null : !END_DATE_EDEFAULT.equals(endDate);
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT__AVAILABILITY:
				return availability != null;
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
		result.append(" (StartDate: ");
		result.append(startDate);
		result.append(", EndDate: ");
		result.append(endDate);
		result.append(')');
		return result.toString();
	}

} //ResourceServiceContractImpl
