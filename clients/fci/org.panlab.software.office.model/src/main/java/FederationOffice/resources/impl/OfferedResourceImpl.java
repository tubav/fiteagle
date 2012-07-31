/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.resources.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import FederationOffice.providersite.ProvidersitePackage;
import FederationOffice.providersite.Site;
import FederationOffice.resources.OfferedResource;
import FederationOffice.resources.ResourceType;
import FederationOffice.resources.ResourcesPackage;
import FederationOffice.services.Service;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Offered Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.resources.impl.OfferedResourceImpl#getResourceType <em>Resource Type</em>}</li>
 *   <li>{@link FederationOffice.resources.impl.OfferedResourceImpl#getBelongsToSite <em>Belongs To Site</em>}</li>
 *   <li>{@link FederationOffice.resources.impl.OfferedResourceImpl#getImplOfferedService <em>Impl Offered Service</em>}</li>
 *   <li>{@link FederationOffice.resources.impl.OfferedResourceImpl#getMultitonMaxOccur <em>Multiton Max Occur</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OfferedResourceImpl extends ResourceImpl implements OfferedResource {
	/**
	 * The default value of the '{@link #getResourceType() <em>Resource Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceType()
	 * @generated
	 * @ordered
	 */
	protected static final ResourceType RESOURCE_TYPE_EDEFAULT = ResourceType.SINGLETON;

	/**
	 * The cached value of the '{@link #getResourceType() <em>Resource Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceType()
	 * @generated
	 * @ordered
	 */
	protected ResourceType resourceType = RESOURCE_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getImplOfferedService() <em>Impl Offered Service</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplOfferedService()
	 * @generated
	 * @ordered
	 */
	protected Service implOfferedService;

	/**
	 * The default value of the '{@link #getMultitonMaxOccur() <em>Multiton Max Occur</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultitonMaxOccur()
	 * @generated
	 * @ordered
	 */
	protected static final int MULTITON_MAX_OCCUR_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getMultitonMaxOccur() <em>Multiton Max Occur</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultitonMaxOccur()
	 * @generated
	 * @ordered
	 */
	protected int multitonMaxOccur = MULTITON_MAX_OCCUR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OfferedResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ResourcesPackage.Literals.OFFERED_RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceType getResourceType() {
		return resourceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceType(ResourceType newResourceType) {
		ResourceType oldResourceType = resourceType;
		resourceType = newResourceType == null ? RESOURCE_TYPE_EDEFAULT : newResourceType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcesPackage.OFFERED_RESOURCE__RESOURCE_TYPE, oldResourceType, resourceType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMultitonMaxOccur() {
		return multitonMaxOccur;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMultitonMaxOccur(int newMultitonMaxOccur) {
		int oldMultitonMaxOccur = multitonMaxOccur;
		multitonMaxOccur = newMultitonMaxOccur;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcesPackage.OFFERED_RESOURCE__MULTITON_MAX_OCCUR, oldMultitonMaxOccur, multitonMaxOccur));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Site getBelongsToSite() {
		if (eContainerFeatureID() != ResourcesPackage.OFFERED_RESOURCE__BELONGS_TO_SITE) return null;
		return (Site)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Site basicGetBelongsToSite() {
		if (eContainerFeatureID() != ResourcesPackage.OFFERED_RESOURCE__BELONGS_TO_SITE) return null;
		return (Site)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBelongsToSite(Site newBelongsToSite, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newBelongsToSite, ResourcesPackage.OFFERED_RESOURCE__BELONGS_TO_SITE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBelongsToSite(Site newBelongsToSite) {
		if (newBelongsToSite != eInternalContainer() || (eContainerFeatureID() != ResourcesPackage.OFFERED_RESOURCE__BELONGS_TO_SITE && newBelongsToSite != null)) {
			if (EcoreUtil.isAncestor(this, newBelongsToSite))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBelongsToSite != null)
				msgs = ((InternalEObject)newBelongsToSite).eInverseAdd(this, ProvidersitePackage.SITE__OFFERED_RESOURCES_LIST, Site.class, msgs);
			msgs = basicSetBelongsToSite(newBelongsToSite, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcesPackage.OFFERED_RESOURCE__BELONGS_TO_SITE, newBelongsToSite, newBelongsToSite));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Service getImplOfferedService() {
		if (implOfferedService != null && implOfferedService.eIsProxy()) {
			InternalEObject oldImplOfferedService = (InternalEObject)implOfferedService;
			implOfferedService = (Service)eResolveProxy(oldImplOfferedService);
			if (implOfferedService != oldImplOfferedService) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ResourcesPackage.OFFERED_RESOURCE__IMPL_OFFERED_SERVICE, oldImplOfferedService, implOfferedService));
			}
		}
		return implOfferedService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Service basicGetImplOfferedService() {
		return implOfferedService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplOfferedService(Service newImplOfferedService) {
		Service oldImplOfferedService = implOfferedService;
		implOfferedService = newImplOfferedService;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcesPackage.OFFERED_RESOURCE__IMPL_OFFERED_SERVICE, oldImplOfferedService, implOfferedService));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ResourcesPackage.OFFERED_RESOURCE__BELONGS_TO_SITE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetBelongsToSite((Site)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ResourcesPackage.OFFERED_RESOURCE__BELONGS_TO_SITE:
				return basicSetBelongsToSite(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ResourcesPackage.OFFERED_RESOURCE__BELONGS_TO_SITE:
				return eInternalContainer().eInverseRemove(this, ProvidersitePackage.SITE__OFFERED_RESOURCES_LIST, Site.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ResourcesPackage.OFFERED_RESOURCE__RESOURCE_TYPE:
				return getResourceType();
			case ResourcesPackage.OFFERED_RESOURCE__BELONGS_TO_SITE:
				if (resolve) return getBelongsToSite();
				return basicGetBelongsToSite();
			case ResourcesPackage.OFFERED_RESOURCE__IMPL_OFFERED_SERVICE:
				if (resolve) return getImplOfferedService();
				return basicGetImplOfferedService();
			case ResourcesPackage.OFFERED_RESOURCE__MULTITON_MAX_OCCUR:
				return getMultitonMaxOccur();
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
			case ResourcesPackage.OFFERED_RESOURCE__RESOURCE_TYPE:
				setResourceType((ResourceType)newValue);
				return;
			case ResourcesPackage.OFFERED_RESOURCE__BELONGS_TO_SITE:
				setBelongsToSite((Site)newValue);
				return;
			case ResourcesPackage.OFFERED_RESOURCE__IMPL_OFFERED_SERVICE:
				setImplOfferedService((Service)newValue);
				return;
			case ResourcesPackage.OFFERED_RESOURCE__MULTITON_MAX_OCCUR:
				setMultitonMaxOccur((Integer)newValue);
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
			case ResourcesPackage.OFFERED_RESOURCE__RESOURCE_TYPE:
				setResourceType(RESOURCE_TYPE_EDEFAULT);
				return;
			case ResourcesPackage.OFFERED_RESOURCE__BELONGS_TO_SITE:
				setBelongsToSite((Site)null);
				return;
			case ResourcesPackage.OFFERED_RESOURCE__IMPL_OFFERED_SERVICE:
				setImplOfferedService((Service)null);
				return;
			case ResourcesPackage.OFFERED_RESOURCE__MULTITON_MAX_OCCUR:
				setMultitonMaxOccur(MULTITON_MAX_OCCUR_EDEFAULT);
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
			case ResourcesPackage.OFFERED_RESOURCE__RESOURCE_TYPE:
				return resourceType != RESOURCE_TYPE_EDEFAULT;
			case ResourcesPackage.OFFERED_RESOURCE__BELONGS_TO_SITE:
				return basicGetBelongsToSite() != null;
			case ResourcesPackage.OFFERED_RESOURCE__IMPL_OFFERED_SERVICE:
				return implOfferedService != null;
			case ResourcesPackage.OFFERED_RESOURCE__MULTITON_MAX_OCCUR:
				return multitonMaxOccur != MULTITON_MAX_OCCUR_EDEFAULT;
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
		result.append(" (resourceType: ");
		result.append(resourceType);
		result.append(", multitonMaxOccur: ");
		result.append(multitonMaxOccur);
		result.append(')');
		return result.toString();
	}

} //OfferedResourceImpl
