/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.services.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import FederationOffice.federationscenarios.RequestedFederationScenario;
import FederationOffice.impl.NamedElementImpl;
import FederationOffice.resources.ResourceCategory;
import FederationOffice.services.Service;
import FederationOffice.services.ServicesPackage;
import FederationOffice.services.Taxonomy;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Taxonomy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.services.impl.TaxonomyImpl#getTaxonomies <em>Taxonomies</em>}</li>
 *   <li>{@link FederationOffice.services.impl.TaxonomyImpl#getCategories <em>Categories</em>}</li>
 *   <li>{@link FederationOffice.services.impl.TaxonomyImpl#getHasScenarios <em>Has Scenarios</em>}</li>
 *   <li>{@link FederationOffice.services.impl.TaxonomyImpl#getHasServices <em>Has Services</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaxonomyImpl extends NamedElementImpl implements Taxonomy {
	/**
	 * The cached value of the '{@link #getTaxonomies() <em>Taxonomies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaxonomies()
	 * @generated
	 * @ordered
	 */
	protected EList<Taxonomy> taxonomies;

	/**
	 * The cached value of the '{@link #getCategories() <em>Categories</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategories()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceCategory> categories;

	/**
	 * The cached value of the '{@link #getHasScenarios() <em>Has Scenarios</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasScenarios()
	 * @generated
	 * @ordered
	 */
	protected EList<RequestedFederationScenario> hasScenarios;

	/**
	 * The cached value of the '{@link #getHasServices() <em>Has Services</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasServices()
	 * @generated
	 * @ordered
	 */
	protected EList<Service> hasServices;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TaxonomyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ServicesPackage.Literals.TAXONOMY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Taxonomy> getTaxonomies() {
		if (taxonomies == null) {
			taxonomies = new EObjectContainmentEList.Resolving<Taxonomy>(Taxonomy.class, this, ServicesPackage.TAXONOMY__TAXONOMIES);
		}
		return taxonomies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResourceCategory> getCategories() {
		if (categories == null) {
			categories = new EObjectContainmentEList.Resolving<ResourceCategory>(ResourceCategory.class, this, ServicesPackage.TAXONOMY__CATEGORIES);
		}
		return categories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RequestedFederationScenario> getHasScenarios() {
		if (hasScenarios == null) {
			hasScenarios = new EObjectResolvingEList<RequestedFederationScenario>(RequestedFederationScenario.class, this, ServicesPackage.TAXONOMY__HAS_SCENARIOS);
		}
		return hasScenarios;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Service> getHasServices() {
		if (hasServices == null) {
			hasServices = new EObjectResolvingEList<Service>(Service.class, this, ServicesPackage.TAXONOMY__HAS_SERVICES);
		}
		return hasServices;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ServicesPackage.TAXONOMY__TAXONOMIES:
				return ((InternalEList<?>)getTaxonomies()).basicRemove(otherEnd, msgs);
			case ServicesPackage.TAXONOMY__CATEGORIES:
				return ((InternalEList<?>)getCategories()).basicRemove(otherEnd, msgs);
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
			case ServicesPackage.TAXONOMY__TAXONOMIES:
				return getTaxonomies();
			case ServicesPackage.TAXONOMY__CATEGORIES:
				return getCategories();
			case ServicesPackage.TAXONOMY__HAS_SCENARIOS:
				return getHasScenarios();
			case ServicesPackage.TAXONOMY__HAS_SERVICES:
				return getHasServices();
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
			case ServicesPackage.TAXONOMY__TAXONOMIES:
				getTaxonomies().clear();
				getTaxonomies().addAll((Collection<? extends Taxonomy>)newValue);
				return;
			case ServicesPackage.TAXONOMY__CATEGORIES:
				getCategories().clear();
				getCategories().addAll((Collection<? extends ResourceCategory>)newValue);
				return;
			case ServicesPackage.TAXONOMY__HAS_SCENARIOS:
				getHasScenarios().clear();
				getHasScenarios().addAll((Collection<? extends RequestedFederationScenario>)newValue);
				return;
			case ServicesPackage.TAXONOMY__HAS_SERVICES:
				getHasServices().clear();
				getHasServices().addAll((Collection<? extends Service>)newValue);
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
			case ServicesPackage.TAXONOMY__TAXONOMIES:
				getTaxonomies().clear();
				return;
			case ServicesPackage.TAXONOMY__CATEGORIES:
				getCategories().clear();
				return;
			case ServicesPackage.TAXONOMY__HAS_SCENARIOS:
				getHasScenarios().clear();
				return;
			case ServicesPackage.TAXONOMY__HAS_SERVICES:
				getHasServices().clear();
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
			case ServicesPackage.TAXONOMY__TAXONOMIES:
				return taxonomies != null && !taxonomies.isEmpty();
			case ServicesPackage.TAXONOMY__CATEGORIES:
				return categories != null && !categories.isEmpty();
			case ServicesPackage.TAXONOMY__HAS_SCENARIOS:
				return hasScenarios != null && !hasScenarios.isEmpty();
			case ServicesPackage.TAXONOMY__HAS_SERVICES:
				return hasServices != null && !hasServices.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TaxonomyImpl
