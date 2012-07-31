/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.providersite.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import FederationOffice.impl.NamedElementImpl;
import FederationOffice.providersite.IGW;
import FederationOffice.providersite.PTM;
import FederationOffice.providersite.ProvidersitePackage;
import FederationOffice.providersite.Site;
import FederationOffice.providersite.SiteLocation;
import FederationOffice.resources.OfferedResource;
import FederationOffice.resources.ResourcesPackage;
import FederationOffice.users.ResourcesProvider;
import FederationOffice.users.UsersPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Site</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.providersite.impl.SiteImpl#getPtm <em>Ptm</em>}</li>
 *   <li>{@link FederationOffice.providersite.impl.SiteImpl#getIgwlist <em>Igwlist</em>}</li>
 *   <li>{@link FederationOffice.providersite.impl.SiteImpl#getLocatedAt <em>Located At</em>}</li>
 *   <li>{@link FederationOffice.providersite.impl.SiteImpl#getOfferedResourcesList <em>Offered Resources List</em>}</li>
 *   <li>{@link FederationOffice.providersite.impl.SiteImpl#getBelongsToProvider <em>Belongs To Provider</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SiteImpl extends NamedElementImpl implements Site {
	/**
	 * The cached value of the '{@link #getPtm() <em>Ptm</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPtm()
	 * @generated
	 * @ordered
	 */
	protected PTM ptm;

	/**
	 * The cached value of the '{@link #getIgwlist() <em>Igwlist</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIgwlist()
	 * @generated
	 * @ordered
	 */
	protected EList<IGW> igwlist;

	/**
	 * The cached value of the '{@link #getLocatedAt() <em>Located At</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocatedAt()
	 * @generated
	 * @ordered
	 */
	protected SiteLocation locatedAt;

	/**
	 * The cached value of the '{@link #getOfferedResourcesList() <em>Offered Resources List</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOfferedResourcesList()
	 * @generated
	 * @ordered
	 */
	protected EList<OfferedResource> offeredResourcesList;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SiteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProvidersitePackage.Literals.SITE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTM getPtm() {
		if (ptm != null && ptm.eIsProxy()) {
			InternalEObject oldPtm = (InternalEObject)ptm;
			ptm = (PTM)eResolveProxy(oldPtm);
			if (ptm != oldPtm) {
				InternalEObject newPtm = (InternalEObject)ptm;
				NotificationChain msgs =  oldPtm.eInverseRemove(this, ProvidersitePackage.PTM__BELONGS_TO_SITE, PTM.class, null);
				if (newPtm.eInternalContainer() == null) {
					msgs =  newPtm.eInverseAdd(this, ProvidersitePackage.PTM__BELONGS_TO_SITE, PTM.class, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProvidersitePackage.SITE__PTM, oldPtm, ptm));
			}
		}
		return ptm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PTM basicGetPtm() {
		return ptm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPtm(PTM newPtm, NotificationChain msgs) {
		PTM oldPtm = ptm;
		ptm = newPtm;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProvidersitePackage.SITE__PTM, oldPtm, newPtm);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPtm(PTM newPtm) {
		if (newPtm != ptm) {
			NotificationChain msgs = null;
			if (ptm != null)
				msgs = ((InternalEObject)ptm).eInverseRemove(this, ProvidersitePackage.PTM__BELONGS_TO_SITE, PTM.class, msgs);
			if (newPtm != null)
				msgs = ((InternalEObject)newPtm).eInverseAdd(this, ProvidersitePackage.PTM__BELONGS_TO_SITE, PTM.class, msgs);
			msgs = basicSetPtm(newPtm, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProvidersitePackage.SITE__PTM, newPtm, newPtm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IGW> getIgwlist() {
		if (igwlist == null) {
			igwlist = new EObjectContainmentWithInverseEList.Resolving<IGW>(IGW.class, this, ProvidersitePackage.SITE__IGWLIST, ProvidersitePackage.IGW__BELONGS_TO_SITE);
		}
		return igwlist;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SiteLocation getLocatedAt() {
		if (locatedAt != null && locatedAt.eIsProxy()) {
			InternalEObject oldLocatedAt = (InternalEObject)locatedAt;
			locatedAt = (SiteLocation)eResolveProxy(oldLocatedAt);
			if (locatedAt != oldLocatedAt) {
				InternalEObject newLocatedAt = (InternalEObject)locatedAt;
				NotificationChain msgs = oldLocatedAt.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProvidersitePackage.SITE__LOCATED_AT, null, null);
				if (newLocatedAt.eInternalContainer() == null) {
					msgs = newLocatedAt.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProvidersitePackage.SITE__LOCATED_AT, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProvidersitePackage.SITE__LOCATED_AT, oldLocatedAt, locatedAt));
			}
		}
		return locatedAt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SiteLocation basicGetLocatedAt() {
		return locatedAt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLocatedAt(SiteLocation newLocatedAt, NotificationChain msgs) {
		SiteLocation oldLocatedAt = locatedAt;
		locatedAt = newLocatedAt;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProvidersitePackage.SITE__LOCATED_AT, oldLocatedAt, newLocatedAt);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocatedAt(SiteLocation newLocatedAt) {
		if (newLocatedAt != locatedAt) {
			NotificationChain msgs = null;
			if (locatedAt != null)
				msgs = ((InternalEObject)locatedAt).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProvidersitePackage.SITE__LOCATED_AT, null, msgs);
			if (newLocatedAt != null)
				msgs = ((InternalEObject)newLocatedAt).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProvidersitePackage.SITE__LOCATED_AT, null, msgs);
			msgs = basicSetLocatedAt(newLocatedAt, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProvidersitePackage.SITE__LOCATED_AT, newLocatedAt, newLocatedAt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OfferedResource> getOfferedResourcesList() {
		if (offeredResourcesList == null) {
			offeredResourcesList = new EObjectContainmentWithInverseEList.Resolving<OfferedResource>(OfferedResource.class, this, ProvidersitePackage.SITE__OFFERED_RESOURCES_LIST, ResourcesPackage.OFFERED_RESOURCE__BELONGS_TO_SITE);
		}
		return offeredResourcesList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourcesProvider getBelongsToProvider() {
		if (eContainerFeatureID() != ProvidersitePackage.SITE__BELONGS_TO_PROVIDER) return null;
		return (ResourcesProvider)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourcesProvider basicGetBelongsToProvider() {
		if (eContainerFeatureID() != ProvidersitePackage.SITE__BELONGS_TO_PROVIDER) return null;
		return (ResourcesProvider)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBelongsToProvider(ResourcesProvider newBelongsToProvider, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newBelongsToProvider, ProvidersitePackage.SITE__BELONGS_TO_PROVIDER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBelongsToProvider(ResourcesProvider newBelongsToProvider) {
		if (newBelongsToProvider != eInternalContainer() || (eContainerFeatureID() != ProvidersitePackage.SITE__BELONGS_TO_PROVIDER && newBelongsToProvider != null)) {
			if (EcoreUtil.isAncestor(this, newBelongsToProvider))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBelongsToProvider != null)
				msgs = ((InternalEObject)newBelongsToProvider).eInverseAdd(this, UsersPackage.RESOURCES_PROVIDER__OFFERED_SITE_LIST, ResourcesProvider.class, msgs);
			msgs = basicSetBelongsToProvider(newBelongsToProvider, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProvidersitePackage.SITE__BELONGS_TO_PROVIDER, newBelongsToProvider, newBelongsToProvider));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProvidersitePackage.SITE__PTM:
				if (ptm != null)
					msgs = ((InternalEObject)ptm).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProvidersitePackage.SITE__PTM, null, msgs);
				return basicSetPtm((PTM)otherEnd, msgs);
			case ProvidersitePackage.SITE__IGWLIST:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIgwlist()).basicAdd(otherEnd, msgs);
			case ProvidersitePackage.SITE__OFFERED_RESOURCES_LIST:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOfferedResourcesList()).basicAdd(otherEnd, msgs);
			case ProvidersitePackage.SITE__BELONGS_TO_PROVIDER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetBelongsToProvider((ResourcesProvider)otherEnd, msgs);
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
			case ProvidersitePackage.SITE__PTM:
				return basicSetPtm(null, msgs);
			case ProvidersitePackage.SITE__IGWLIST:
				return ((InternalEList<?>)getIgwlist()).basicRemove(otherEnd, msgs);
			case ProvidersitePackage.SITE__LOCATED_AT:
				return basicSetLocatedAt(null, msgs);
			case ProvidersitePackage.SITE__OFFERED_RESOURCES_LIST:
				return ((InternalEList<?>)getOfferedResourcesList()).basicRemove(otherEnd, msgs);
			case ProvidersitePackage.SITE__BELONGS_TO_PROVIDER:
				return basicSetBelongsToProvider(null, msgs);
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
			case ProvidersitePackage.SITE__BELONGS_TO_PROVIDER:
				return eInternalContainer().eInverseRemove(this, UsersPackage.RESOURCES_PROVIDER__OFFERED_SITE_LIST, ResourcesProvider.class, msgs);
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
			case ProvidersitePackage.SITE__PTM:
				if (resolve) return getPtm();
				return basicGetPtm();
			case ProvidersitePackage.SITE__IGWLIST:
				return getIgwlist();
			case ProvidersitePackage.SITE__LOCATED_AT:
				if (resolve) return getLocatedAt();
				return basicGetLocatedAt();
			case ProvidersitePackage.SITE__OFFERED_RESOURCES_LIST:
				return getOfferedResourcesList();
			case ProvidersitePackage.SITE__BELONGS_TO_PROVIDER:
				if (resolve) return getBelongsToProvider();
				return basicGetBelongsToProvider();
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
			case ProvidersitePackage.SITE__PTM:
				setPtm((PTM)newValue);
				return;
			case ProvidersitePackage.SITE__IGWLIST:
				getIgwlist().clear();
				getIgwlist().addAll((Collection<? extends IGW>)newValue);
				return;
			case ProvidersitePackage.SITE__LOCATED_AT:
				setLocatedAt((SiteLocation)newValue);
				return;
			case ProvidersitePackage.SITE__OFFERED_RESOURCES_LIST:
				getOfferedResourcesList().clear();
				getOfferedResourcesList().addAll((Collection<? extends OfferedResource>)newValue);
				return;
			case ProvidersitePackage.SITE__BELONGS_TO_PROVIDER:
				setBelongsToProvider((ResourcesProvider)newValue);
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
			case ProvidersitePackage.SITE__PTM:
				setPtm((PTM)null);
				return;
			case ProvidersitePackage.SITE__IGWLIST:
				getIgwlist().clear();
				return;
			case ProvidersitePackage.SITE__LOCATED_AT:
				setLocatedAt((SiteLocation)null);
				return;
			case ProvidersitePackage.SITE__OFFERED_RESOURCES_LIST:
				getOfferedResourcesList().clear();
				return;
			case ProvidersitePackage.SITE__BELONGS_TO_PROVIDER:
				setBelongsToProvider((ResourcesProvider)null);
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
			case ProvidersitePackage.SITE__PTM:
				return ptm != null;
			case ProvidersitePackage.SITE__IGWLIST:
				return igwlist != null && !igwlist.isEmpty();
			case ProvidersitePackage.SITE__LOCATED_AT:
				return locatedAt != null;
			case ProvidersitePackage.SITE__OFFERED_RESOURCES_LIST:
				return offeredResourcesList != null && !offeredResourcesList.isEmpty();
			case ProvidersitePackage.SITE__BELONGS_TO_PROVIDER:
				return basicGetBelongsToProvider() != null;
		}
		return super.eIsSet(featureID);
	}

} //SiteImpl
