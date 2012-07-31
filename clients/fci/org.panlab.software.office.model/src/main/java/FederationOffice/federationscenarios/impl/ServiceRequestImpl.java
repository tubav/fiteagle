/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.federationscenarios.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import FederationOffice.federationscenarios.FederationscenariosPackage;
import FederationOffice.federationscenarios.ServiceRequest;
import FederationOffice.federationscenarios.ServiceSettingInstance;
import FederationOffice.impl.NamedElementImpl;
import FederationOffice.services.Service;
import FederationOffice.users.ResourcesProvider;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Request</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.federationscenarios.impl.ServiceRequestImpl#getRefService <em>Ref Service</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.impl.ServiceRequestImpl#getOfferedByProviders <em>Offered By Providers</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.impl.ServiceRequestImpl#getReqServiceSettings <em>Req Service Settings</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.impl.ServiceRequestImpl#getNumOfServices <em>Num Of Services</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceRequestImpl extends NamedElementImpl implements ServiceRequest {
	/**
	 * The cached value of the '{@link #getRefService() <em>Ref Service</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefService()
	 * @generated
	 * @ordered
	 */
	protected Service refService;

	/**
	 * The cached value of the '{@link #getOfferedByProviders() <em>Offered By Providers</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOfferedByProviders()
	 * @generated
	 * @ordered
	 */
	protected ResourcesProvider offeredByProviders;

	/**
	 * The cached value of the '{@link #getReqServiceSettings() <em>Req Service Settings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReqServiceSettings()
	 * @generated
	 * @ordered
	 */
	protected EList<ServiceSettingInstance> reqServiceSettings;

	/**
	 * The default value of the '{@link #getNumOfServices() <em>Num Of Services</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumOfServices()
	 * @generated
	 * @ordered
	 */
	protected static final int NUM_OF_SERVICES_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNumOfServices() <em>Num Of Services</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumOfServices()
	 * @generated
	 * @ordered
	 */
	protected int numOfServices = NUM_OF_SERVICES_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServiceRequestImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FederationscenariosPackage.Literals.SERVICE_REQUEST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Service getRefService() {
		if (refService != null && refService.eIsProxy()) {
			InternalEObject oldRefService = (InternalEObject)refService;
			refService = (Service)eResolveProxy(oldRefService);
			if (refService != oldRefService) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FederationscenariosPackage.SERVICE_REQUEST__REF_SERVICE, oldRefService, refService));
			}
		}
		return refService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Service basicGetRefService() {
		return refService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRefService(Service newRefService) {
		Service oldRefService = refService;
		refService = newRefService;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.SERVICE_REQUEST__REF_SERVICE, oldRefService, refService));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourcesProvider getOfferedByProviders() {
		if (offeredByProviders != null && offeredByProviders.eIsProxy()) {
			InternalEObject oldOfferedByProviders = (InternalEObject)offeredByProviders;
			offeredByProviders = (ResourcesProvider)eResolveProxy(oldOfferedByProviders);
			if (offeredByProviders != oldOfferedByProviders) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FederationscenariosPackage.SERVICE_REQUEST__OFFERED_BY_PROVIDERS, oldOfferedByProviders, offeredByProviders));
			}
		}
		return offeredByProviders;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourcesProvider basicGetOfferedByProviders() {
		return offeredByProviders;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOfferedByProviders(ResourcesProvider newOfferedByProviders) {
		ResourcesProvider oldOfferedByProviders = offeredByProviders;
		offeredByProviders = newOfferedByProviders;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.SERVICE_REQUEST__OFFERED_BY_PROVIDERS, oldOfferedByProviders, offeredByProviders));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ServiceSettingInstance> getReqServiceSettings() {
		if (reqServiceSettings == null) {
			reqServiceSettings = new EObjectContainmentEList.Resolving<ServiceSettingInstance>(ServiceSettingInstance.class, this, FederationscenariosPackage.SERVICE_REQUEST__REQ_SERVICE_SETTINGS);
		}
		return reqServiceSettings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumOfServices() {
		return numOfServices;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumOfServices(int newNumOfServices) {
		int oldNumOfServices = numOfServices;
		numOfServices = newNumOfServices;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.SERVICE_REQUEST__NUM_OF_SERVICES, oldNumOfServices, numOfServices));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FederationscenariosPackage.SERVICE_REQUEST__REQ_SERVICE_SETTINGS:
				return ((InternalEList<?>)getReqServiceSettings()).basicRemove(otherEnd, msgs);
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
			case FederationscenariosPackage.SERVICE_REQUEST__REF_SERVICE:
				if (resolve) return getRefService();
				return basicGetRefService();
			case FederationscenariosPackage.SERVICE_REQUEST__OFFERED_BY_PROVIDERS:
				if (resolve) return getOfferedByProviders();
				return basicGetOfferedByProviders();
			case FederationscenariosPackage.SERVICE_REQUEST__REQ_SERVICE_SETTINGS:
				return getReqServiceSettings();
			case FederationscenariosPackage.SERVICE_REQUEST__NUM_OF_SERVICES:
				return getNumOfServices();
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
			case FederationscenariosPackage.SERVICE_REQUEST__REF_SERVICE:
				setRefService((Service)newValue);
				return;
			case FederationscenariosPackage.SERVICE_REQUEST__OFFERED_BY_PROVIDERS:
				setOfferedByProviders((ResourcesProvider)newValue);
				return;
			case FederationscenariosPackage.SERVICE_REQUEST__REQ_SERVICE_SETTINGS:
				getReqServiceSettings().clear();
				getReqServiceSettings().addAll((Collection<? extends ServiceSettingInstance>)newValue);
				return;
			case FederationscenariosPackage.SERVICE_REQUEST__NUM_OF_SERVICES:
				setNumOfServices((Integer)newValue);
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
			case FederationscenariosPackage.SERVICE_REQUEST__REF_SERVICE:
				setRefService((Service)null);
				return;
			case FederationscenariosPackage.SERVICE_REQUEST__OFFERED_BY_PROVIDERS:
				setOfferedByProviders((ResourcesProvider)null);
				return;
			case FederationscenariosPackage.SERVICE_REQUEST__REQ_SERVICE_SETTINGS:
				getReqServiceSettings().clear();
				return;
			case FederationscenariosPackage.SERVICE_REQUEST__NUM_OF_SERVICES:
				setNumOfServices(NUM_OF_SERVICES_EDEFAULT);
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
			case FederationscenariosPackage.SERVICE_REQUEST__REF_SERVICE:
				return refService != null;
			case FederationscenariosPackage.SERVICE_REQUEST__OFFERED_BY_PROVIDERS:
				return offeredByProviders != null;
			case FederationscenariosPackage.SERVICE_REQUEST__REQ_SERVICE_SETTINGS:
				return reqServiceSettings != null && !reqServiceSettings.isEmpty();
			case FederationscenariosPackage.SERVICE_REQUEST__NUM_OF_SERVICES:
				return numOfServices != NUM_OF_SERVICES_EDEFAULT;
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
		result.append(" (numOfServices: ");
		result.append(numOfServices);
		result.append(')');
		return result.toString();
	}

} //ServiceRequestImpl
