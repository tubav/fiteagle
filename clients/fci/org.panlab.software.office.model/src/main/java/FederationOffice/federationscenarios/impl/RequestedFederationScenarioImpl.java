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

import FederationOffice.VTStatus;
import FederationOffice.experimentRuntime.RuntimeElement;
import FederationOffice.federationscenarios.Credentials;
import FederationOffice.federationscenarios.FederationscenariosPackage;
import FederationOffice.federationscenarios.Import;
import FederationOffice.federationscenarios.InfrastructureRequest;
import FederationOffice.federationscenarios.RequestedFederationScenario;
import FederationOffice.federationscenarios.ScheduledPlan;
import FederationOffice.federationscenarios.ServicesRequest;
import FederationOffice.impl.NamedElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Requested Federation Scenario</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.federationscenarios.impl.RequestedFederationScenarioImpl#isIsShared <em>Is Shared</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.impl.RequestedFederationScenarioImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.impl.RequestedFederationScenarioImpl#getVTCredentials <em>VT Credentials</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.impl.RequestedFederationScenarioImpl#getServicesRequest <em>Services Request</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.impl.RequestedFederationScenarioImpl#getInfrastructureRequest <em>Infrastructure Request</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.impl.RequestedFederationScenarioImpl#getScheduledPlan <em>Scheduled Plan</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.impl.RequestedFederationScenarioImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.impl.RequestedFederationScenarioImpl#getRuntimeInfo <em>Runtime Info</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RequestedFederationScenarioImpl extends NamedElementImpl implements RequestedFederationScenario {
	/**
	 * The default value of the '{@link #isIsShared() <em>Is Shared</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsShared()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_SHARED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsShared() <em>Is Shared</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsShared()
	 * @generated
	 * @ordered
	 */
	protected boolean isShared = IS_SHARED_EDEFAULT;

	/**
	 * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected static final VTStatus STATUS_EDEFAULT = VTStatus.NEW;

	/**
	 * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected VTStatus status = STATUS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getVTCredentials() <em>VT Credentials</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVTCredentials()
	 * @generated
	 * @ordered
	 */
	protected Credentials vtCredentials;

	/**
	 * The cached value of the '{@link #getServicesRequest() <em>Services Request</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServicesRequest()
	 * @generated
	 * @ordered
	 */
	protected ServicesRequest servicesRequest;

	/**
	 * The cached value of the '{@link #getInfrastructureRequest() <em>Infrastructure Request</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInfrastructureRequest()
	 * @generated
	 * @ordered
	 */
	protected InfrastructureRequest infrastructureRequest;

	/**
	 * The cached value of the '{@link #getScheduledPlan() <em>Scheduled Plan</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScheduledPlan()
	 * @generated
	 * @ordered
	 */
	protected ScheduledPlan scheduledPlan;

	/**
	 * The cached value of the '{@link #getImports() <em>Imports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImports()
	 * @generated
	 * @ordered
	 */
	protected EList<Import> imports;

	/**
	 * The cached value of the '{@link #getRuntimeInfo() <em>Runtime Info</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRuntimeInfo()
	 * @generated
	 * @ordered
	 */
	protected RuntimeElement runtimeInfo;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RequestedFederationScenarioImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FederationscenariosPackage.Literals.REQUESTED_FEDERATION_SCENARIO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsShared() {
		return isShared;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsShared(boolean newIsShared) {
		boolean oldIsShared = isShared;
		isShared = newIsShared;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__IS_SHARED, oldIsShared, isShared));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VTStatus getStatus() {
		return status;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatus(VTStatus newStatus) {
		VTStatus oldStatus = status;
		status = newStatus == null ? STATUS_EDEFAULT : newStatus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__STATUS, oldStatus, status));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Credentials getVTCredentials() {
		if (vtCredentials != null && vtCredentials.eIsProxy()) {
			InternalEObject oldVTCredentials = (InternalEObject)vtCredentials;
			vtCredentials = (Credentials)eResolveProxy(oldVTCredentials);
			if (vtCredentials != oldVTCredentials) {
				InternalEObject newVTCredentials = (InternalEObject)vtCredentials;
				NotificationChain msgs = oldVTCredentials.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__VT_CREDENTIALS, null, null);
				if (newVTCredentials.eInternalContainer() == null) {
					msgs = newVTCredentials.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__VT_CREDENTIALS, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__VT_CREDENTIALS, oldVTCredentials, vtCredentials));
			}
		}
		return vtCredentials;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Credentials basicGetVTCredentials() {
		return vtCredentials;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVTCredentials(Credentials newVTCredentials, NotificationChain msgs) {
		Credentials oldVTCredentials = vtCredentials;
		vtCredentials = newVTCredentials;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__VT_CREDENTIALS, oldVTCredentials, newVTCredentials);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVTCredentials(Credentials newVTCredentials) {
		if (newVTCredentials != vtCredentials) {
			NotificationChain msgs = null;
			if (vtCredentials != null)
				msgs = ((InternalEObject)vtCredentials).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__VT_CREDENTIALS, null, msgs);
			if (newVTCredentials != null)
				msgs = ((InternalEObject)newVTCredentials).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__VT_CREDENTIALS, null, msgs);
			msgs = basicSetVTCredentials(newVTCredentials, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__VT_CREDENTIALS, newVTCredentials, newVTCredentials));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServicesRequest getServicesRequest() {
		if (servicesRequest != null && servicesRequest.eIsProxy()) {
			InternalEObject oldServicesRequest = (InternalEObject)servicesRequest;
			servicesRequest = (ServicesRequest)eResolveProxy(oldServicesRequest);
			if (servicesRequest != oldServicesRequest) {
				InternalEObject newServicesRequest = (InternalEObject)servicesRequest;
				NotificationChain msgs = oldServicesRequest.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SERVICES_REQUEST, null, null);
				if (newServicesRequest.eInternalContainer() == null) {
					msgs = newServicesRequest.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SERVICES_REQUEST, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SERVICES_REQUEST, oldServicesRequest, servicesRequest));
			}
		}
		return servicesRequest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServicesRequest basicGetServicesRequest() {
		return servicesRequest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetServicesRequest(ServicesRequest newServicesRequest, NotificationChain msgs) {
		ServicesRequest oldServicesRequest = servicesRequest;
		servicesRequest = newServicesRequest;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SERVICES_REQUEST, oldServicesRequest, newServicesRequest);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServicesRequest(ServicesRequest newServicesRequest) {
		if (newServicesRequest != servicesRequest) {
			NotificationChain msgs = null;
			if (servicesRequest != null)
				msgs = ((InternalEObject)servicesRequest).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SERVICES_REQUEST, null, msgs);
			if (newServicesRequest != null)
				msgs = ((InternalEObject)newServicesRequest).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SERVICES_REQUEST, null, msgs);
			msgs = basicSetServicesRequest(newServicesRequest, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SERVICES_REQUEST, newServicesRequest, newServicesRequest));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InfrastructureRequest getInfrastructureRequest() {
		if (infrastructureRequest != null && infrastructureRequest.eIsProxy()) {
			InternalEObject oldInfrastructureRequest = (InternalEObject)infrastructureRequest;
			infrastructureRequest = (InfrastructureRequest)eResolveProxy(oldInfrastructureRequest);
			if (infrastructureRequest != oldInfrastructureRequest) {
				InternalEObject newInfrastructureRequest = (InternalEObject)infrastructureRequest;
				NotificationChain msgs = oldInfrastructureRequest.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__INFRASTRUCTURE_REQUEST, null, null);
				if (newInfrastructureRequest.eInternalContainer() == null) {
					msgs = newInfrastructureRequest.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__INFRASTRUCTURE_REQUEST, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__INFRASTRUCTURE_REQUEST, oldInfrastructureRequest, infrastructureRequest));
			}
		}
		return infrastructureRequest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InfrastructureRequest basicGetInfrastructureRequest() {
		return infrastructureRequest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInfrastructureRequest(InfrastructureRequest newInfrastructureRequest, NotificationChain msgs) {
		InfrastructureRequest oldInfrastructureRequest = infrastructureRequest;
		infrastructureRequest = newInfrastructureRequest;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__INFRASTRUCTURE_REQUEST, oldInfrastructureRequest, newInfrastructureRequest);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInfrastructureRequest(InfrastructureRequest newInfrastructureRequest) {
		if (newInfrastructureRequest != infrastructureRequest) {
			NotificationChain msgs = null;
			if (infrastructureRequest != null)
				msgs = ((InternalEObject)infrastructureRequest).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__INFRASTRUCTURE_REQUEST, null, msgs);
			if (newInfrastructureRequest != null)
				msgs = ((InternalEObject)newInfrastructureRequest).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__INFRASTRUCTURE_REQUEST, null, msgs);
			msgs = basicSetInfrastructureRequest(newInfrastructureRequest, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__INFRASTRUCTURE_REQUEST, newInfrastructureRequest, newInfrastructureRequest));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScheduledPlan getScheduledPlan() {
		if (scheduledPlan != null && scheduledPlan.eIsProxy()) {
			InternalEObject oldScheduledPlan = (InternalEObject)scheduledPlan;
			scheduledPlan = (ScheduledPlan)eResolveProxy(oldScheduledPlan);
			if (scheduledPlan != oldScheduledPlan) {
				InternalEObject newScheduledPlan = (InternalEObject)scheduledPlan;
				NotificationChain msgs = oldScheduledPlan.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SCHEDULED_PLAN, null, null);
				if (newScheduledPlan.eInternalContainer() == null) {
					msgs = newScheduledPlan.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SCHEDULED_PLAN, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SCHEDULED_PLAN, oldScheduledPlan, scheduledPlan));
			}
		}
		return scheduledPlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScheduledPlan basicGetScheduledPlan() {
		return scheduledPlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScheduledPlan(ScheduledPlan newScheduledPlan, NotificationChain msgs) {
		ScheduledPlan oldScheduledPlan = scheduledPlan;
		scheduledPlan = newScheduledPlan;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SCHEDULED_PLAN, oldScheduledPlan, newScheduledPlan);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScheduledPlan(ScheduledPlan newScheduledPlan) {
		if (newScheduledPlan != scheduledPlan) {
			NotificationChain msgs = null;
			if (scheduledPlan != null)
				msgs = ((InternalEObject)scheduledPlan).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SCHEDULED_PLAN, null, msgs);
			if (newScheduledPlan != null)
				msgs = ((InternalEObject)newScheduledPlan).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SCHEDULED_PLAN, null, msgs);
			msgs = basicSetScheduledPlan(newScheduledPlan, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SCHEDULED_PLAN, newScheduledPlan, newScheduledPlan));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Import> getImports() {
		if (imports == null) {
			imports = new EObjectContainmentEList.Resolving<Import>(Import.class, this, FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__IMPORTS);
		}
		return imports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuntimeElement getRuntimeInfo() {
		if (runtimeInfo != null && runtimeInfo.eIsProxy()) {
			InternalEObject oldRuntimeInfo = (InternalEObject)runtimeInfo;
			runtimeInfo = (RuntimeElement)eResolveProxy(oldRuntimeInfo);
			if (runtimeInfo != oldRuntimeInfo) {
				InternalEObject newRuntimeInfo = (InternalEObject)runtimeInfo;
				NotificationChain msgs = oldRuntimeInfo.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__RUNTIME_INFO, null, null);
				if (newRuntimeInfo.eInternalContainer() == null) {
					msgs = newRuntimeInfo.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__RUNTIME_INFO, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__RUNTIME_INFO, oldRuntimeInfo, runtimeInfo));
			}
		}
		return runtimeInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuntimeElement basicGetRuntimeInfo() {
		return runtimeInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRuntimeInfo(RuntimeElement newRuntimeInfo, NotificationChain msgs) {
		RuntimeElement oldRuntimeInfo = runtimeInfo;
		runtimeInfo = newRuntimeInfo;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__RUNTIME_INFO, oldRuntimeInfo, newRuntimeInfo);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRuntimeInfo(RuntimeElement newRuntimeInfo) {
		if (newRuntimeInfo != runtimeInfo) {
			NotificationChain msgs = null;
			if (runtimeInfo != null)
				msgs = ((InternalEObject)runtimeInfo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__RUNTIME_INFO, null, msgs);
			if (newRuntimeInfo != null)
				msgs = ((InternalEObject)newRuntimeInfo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__RUNTIME_INFO, null, msgs);
			msgs = basicSetRuntimeInfo(newRuntimeInfo, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__RUNTIME_INFO, newRuntimeInfo, newRuntimeInfo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__VT_CREDENTIALS:
				return basicSetVTCredentials(null, msgs);
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SERVICES_REQUEST:
				return basicSetServicesRequest(null, msgs);
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__INFRASTRUCTURE_REQUEST:
				return basicSetInfrastructureRequest(null, msgs);
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SCHEDULED_PLAN:
				return basicSetScheduledPlan(null, msgs);
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__IMPORTS:
				return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__RUNTIME_INFO:
				return basicSetRuntimeInfo(null, msgs);
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
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__IS_SHARED:
				return isIsShared();
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__STATUS:
				return getStatus();
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__VT_CREDENTIALS:
				if (resolve) return getVTCredentials();
				return basicGetVTCredentials();
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SERVICES_REQUEST:
				if (resolve) return getServicesRequest();
				return basicGetServicesRequest();
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__INFRASTRUCTURE_REQUEST:
				if (resolve) return getInfrastructureRequest();
				return basicGetInfrastructureRequest();
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SCHEDULED_PLAN:
				if (resolve) return getScheduledPlan();
				return basicGetScheduledPlan();
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__IMPORTS:
				return getImports();
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__RUNTIME_INFO:
				if (resolve) return getRuntimeInfo();
				return basicGetRuntimeInfo();
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
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__IS_SHARED:
				setIsShared((Boolean)newValue);
				return;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__STATUS:
				setStatus((VTStatus)newValue);
				return;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__VT_CREDENTIALS:
				setVTCredentials((Credentials)newValue);
				return;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SERVICES_REQUEST:
				setServicesRequest((ServicesRequest)newValue);
				return;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__INFRASTRUCTURE_REQUEST:
				setInfrastructureRequest((InfrastructureRequest)newValue);
				return;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SCHEDULED_PLAN:
				setScheduledPlan((ScheduledPlan)newValue);
				return;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__IMPORTS:
				getImports().clear();
				getImports().addAll((Collection<? extends Import>)newValue);
				return;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__RUNTIME_INFO:
				setRuntimeInfo((RuntimeElement)newValue);
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
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__IS_SHARED:
				setIsShared(IS_SHARED_EDEFAULT);
				return;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__STATUS:
				setStatus(STATUS_EDEFAULT);
				return;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__VT_CREDENTIALS:
				setVTCredentials((Credentials)null);
				return;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SERVICES_REQUEST:
				setServicesRequest((ServicesRequest)null);
				return;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__INFRASTRUCTURE_REQUEST:
				setInfrastructureRequest((InfrastructureRequest)null);
				return;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SCHEDULED_PLAN:
				setScheduledPlan((ScheduledPlan)null);
				return;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__IMPORTS:
				getImports().clear();
				return;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__RUNTIME_INFO:
				setRuntimeInfo((RuntimeElement)null);
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
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__IS_SHARED:
				return isShared != IS_SHARED_EDEFAULT;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__STATUS:
				return status != STATUS_EDEFAULT;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__VT_CREDENTIALS:
				return vtCredentials != null;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SERVICES_REQUEST:
				return servicesRequest != null;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__INFRASTRUCTURE_REQUEST:
				return infrastructureRequest != null;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__SCHEDULED_PLAN:
				return scheduledPlan != null;
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__IMPORTS:
				return imports != null && !imports.isEmpty();
			case FederationscenariosPackage.REQUESTED_FEDERATION_SCENARIO__RUNTIME_INFO:
				return runtimeInfo != null;
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
		result.append(" (isShared: ");
		result.append(isShared);
		result.append(", status: ");
		result.append(status);
		result.append(')');
		return result.toString();
	}

} //RequestedFederationScenarioImpl
