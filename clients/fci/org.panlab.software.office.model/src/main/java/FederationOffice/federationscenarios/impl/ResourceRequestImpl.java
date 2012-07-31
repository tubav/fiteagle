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

import FederationOffice.experimentRuntime.RuntimeElement;
import FederationOffice.federationscenarios.FederationscenariosPackage;
import FederationOffice.federationscenarios.ResourceRequest;
import FederationOffice.federationscenarios.ResourceSettingInstance;
import FederationOffice.impl.NamedElementImpl;
import FederationOffice.resources.OfferedResource;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Request</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.federationscenarios.impl.ResourceRequestImpl#getRefOfferedResource <em>Ref Offered Resource</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.impl.ResourceRequestImpl#getReqResourceSettings <em>Req Resource Settings</em>}</li>
 *   <li>{@link FederationOffice.federationscenarios.impl.ResourceRequestImpl#getRuntimeInfo <em>Runtime Info</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceRequestImpl extends NamedElementImpl implements ResourceRequest {
	/**
	 * The cached value of the '{@link #getRefOfferedResource() <em>Ref Offered Resource</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefOfferedResource()
	 * @generated
	 * @ordered
	 */
	protected OfferedResource refOfferedResource;

	/**
	 * The cached value of the '{@link #getReqResourceSettings() <em>Req Resource Settings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReqResourceSettings()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceSettingInstance> reqResourceSettings;

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
	protected ResourceRequestImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FederationscenariosPackage.Literals.RESOURCE_REQUEST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OfferedResource getRefOfferedResource() {
		if (refOfferedResource != null && refOfferedResource.eIsProxy()) {
			InternalEObject oldRefOfferedResource = (InternalEObject)refOfferedResource;
			refOfferedResource = (OfferedResource)eResolveProxy(oldRefOfferedResource);
			if (refOfferedResource != oldRefOfferedResource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FederationscenariosPackage.RESOURCE_REQUEST__REF_OFFERED_RESOURCE, oldRefOfferedResource, refOfferedResource));
			}
		}
		return refOfferedResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OfferedResource basicGetRefOfferedResource() {
		return refOfferedResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRefOfferedResource(OfferedResource newRefOfferedResource) {
		OfferedResource oldRefOfferedResource = refOfferedResource;
		refOfferedResource = newRefOfferedResource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.RESOURCE_REQUEST__REF_OFFERED_RESOURCE, oldRefOfferedResource, refOfferedResource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResourceSettingInstance> getReqResourceSettings() {
		if (reqResourceSettings == null) {
			reqResourceSettings = new EObjectContainmentEList.Resolving<ResourceSettingInstance>(ResourceSettingInstance.class, this, FederationscenariosPackage.RESOURCE_REQUEST__REQ_RESOURCE_SETTINGS);
		}
		return reqResourceSettings;
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
				NotificationChain msgs = oldRuntimeInfo.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.RESOURCE_REQUEST__RUNTIME_INFO, null, null);
				if (newRuntimeInfo.eInternalContainer() == null) {
					msgs = newRuntimeInfo.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.RESOURCE_REQUEST__RUNTIME_INFO, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FederationscenariosPackage.RESOURCE_REQUEST__RUNTIME_INFO, oldRuntimeInfo, runtimeInfo));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.RESOURCE_REQUEST__RUNTIME_INFO, oldRuntimeInfo, newRuntimeInfo);
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
				msgs = ((InternalEObject)runtimeInfo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.RESOURCE_REQUEST__RUNTIME_INFO, null, msgs);
			if (newRuntimeInfo != null)
				msgs = ((InternalEObject)newRuntimeInfo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FederationscenariosPackage.RESOURCE_REQUEST__RUNTIME_INFO, null, msgs);
			msgs = basicSetRuntimeInfo(newRuntimeInfo, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FederationscenariosPackage.RESOURCE_REQUEST__RUNTIME_INFO, newRuntimeInfo, newRuntimeInfo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FederationscenariosPackage.RESOURCE_REQUEST__REQ_RESOURCE_SETTINGS:
				return ((InternalEList<?>)getReqResourceSettings()).basicRemove(otherEnd, msgs);
			case FederationscenariosPackage.RESOURCE_REQUEST__RUNTIME_INFO:
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
			case FederationscenariosPackage.RESOURCE_REQUEST__REF_OFFERED_RESOURCE:
				if (resolve) return getRefOfferedResource();
				return basicGetRefOfferedResource();
			case FederationscenariosPackage.RESOURCE_REQUEST__REQ_RESOURCE_SETTINGS:
				return getReqResourceSettings();
			case FederationscenariosPackage.RESOURCE_REQUEST__RUNTIME_INFO:
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
			case FederationscenariosPackage.RESOURCE_REQUEST__REF_OFFERED_RESOURCE:
				setRefOfferedResource((OfferedResource)newValue);
				return;
			case FederationscenariosPackage.RESOURCE_REQUEST__REQ_RESOURCE_SETTINGS:
				getReqResourceSettings().clear();
				getReqResourceSettings().addAll((Collection<? extends ResourceSettingInstance>)newValue);
				return;
			case FederationscenariosPackage.RESOURCE_REQUEST__RUNTIME_INFO:
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
			case FederationscenariosPackage.RESOURCE_REQUEST__REF_OFFERED_RESOURCE:
				setRefOfferedResource((OfferedResource)null);
				return;
			case FederationscenariosPackage.RESOURCE_REQUEST__REQ_RESOURCE_SETTINGS:
				getReqResourceSettings().clear();
				return;
			case FederationscenariosPackage.RESOURCE_REQUEST__RUNTIME_INFO:
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
			case FederationscenariosPackage.RESOURCE_REQUEST__REF_OFFERED_RESOURCE:
				return refOfferedResource != null;
			case FederationscenariosPackage.RESOURCE_REQUEST__REQ_RESOURCE_SETTINGS:
				return reqResourceSettings != null && !reqResourceSettings.isEmpty();
			case FederationscenariosPackage.RESOURCE_REQUEST__RUNTIME_INFO:
				return runtimeInfo != null;
		}
		return super.eIsSet(featureID);
	}

} //ResourceRequestImpl
