/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.resources.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import FederationOffice.impl.NamedElementImpl;
import FederationOffice.resources.Resource;
import FederationOffice.resources.ResourceCategory;
import FederationOffice.resources.ResourcesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Category</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.resources.impl.ResourceCategoryImpl#getResourcelist <em>Resourcelist</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceCategoryImpl extends NamedElementImpl implements ResourceCategory {
	/**
	 * The cached value of the '{@link #getResourcelist() <em>Resourcelist</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourcelist()
	 * @generated
	 * @ordered
	 */
	protected EList<Resource> resourcelist;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceCategoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ResourcesPackage.Literals.RESOURCE_CATEGORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Resource> getResourcelist() {
		if (resourcelist == null) {
			resourcelist = new EObjectWithInverseResolvingEList.ManyInverse<Resource>(Resource.class, this, ResourcesPackage.RESOURCE_CATEGORY__RESOURCELIST, ResourcesPackage.RESOURCE__CONTRIBUTES_TO_CATEGORIES);
		}
		return resourcelist;
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
			case ResourcesPackage.RESOURCE_CATEGORY__RESOURCELIST:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getResourcelist()).basicAdd(otherEnd, msgs);
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
			case ResourcesPackage.RESOURCE_CATEGORY__RESOURCELIST:
				return ((InternalEList<?>)getResourcelist()).basicRemove(otherEnd, msgs);
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
			case ResourcesPackage.RESOURCE_CATEGORY__RESOURCELIST:
				return getResourcelist();
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
			case ResourcesPackage.RESOURCE_CATEGORY__RESOURCELIST:
				getResourcelist().clear();
				getResourcelist().addAll((Collection<? extends Resource>)newValue);
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
			case ResourcesPackage.RESOURCE_CATEGORY__RESOURCELIST:
				getResourcelist().clear();
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
			case ResourcesPackage.RESOURCE_CATEGORY__RESOURCELIST:
				return resourcelist != null && !resourcelist.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ResourceCategoryImpl
