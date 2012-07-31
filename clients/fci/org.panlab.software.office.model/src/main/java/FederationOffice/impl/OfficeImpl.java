/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import FederationOffice.FederationOfficePackage;
import FederationOffice.Office;
import FederationOffice.availabilityContract.ResourceServiceContract;
import FederationOffice.federationscenarios.RequestedFederationScenario;
import FederationOffice.services.OfferedService;
import FederationOffice.services.ServiceComposition;
import FederationOffice.services.Taxonomy;
import FederationOffice.slareservations.SLA;
import FederationOffice.users.OfficeUser;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Office</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.impl.OfficeImpl#getRegisteredUsers <em>Registered Users</em>}</li>
 *   <li>{@link FederationOffice.impl.OfficeImpl#getContributedTaxonomies <em>Contributed Taxonomies</em>}</li>
 *   <li>{@link FederationOffice.impl.OfficeImpl#getOfferedServices <em>Offered Services</em>}</li>
 *   <li>{@link FederationOffice.impl.OfficeImpl#getAvailableFederationScenarios <em>Available Federation Scenarios</em>}</li>
 *   <li>{@link FederationOffice.impl.OfficeImpl#getOfferedServiceCompositions <em>Offered Service Compositions</em>}</li>
 *   <li>{@link FederationOffice.impl.OfficeImpl#getResourceServiceContracts <em>Resource Service Contracts</em>}</li>
 *   <li>{@link FederationOffice.impl.OfficeImpl#getSLAs <em>SL As</em>}</li>
 *   <li>{@link FederationOffice.impl.OfficeImpl#getResourceURI <em>Resource URI</em>}</li>
 *   <li>{@link FederationOffice.impl.OfficeImpl#getAPIGateway <em>API Gateway</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OfficeImpl extends NamedElementImpl implements Office {
	/**
	 * The cached value of the '{@link #getRegisteredUsers() <em>Registered Users</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegisteredUsers()
	 * @generated
	 * @ordered
	 */
	protected EList<OfficeUser> registeredUsers;

	/**
	 * The cached value of the '{@link #getContributedTaxonomies() <em>Contributed Taxonomies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContributedTaxonomies()
	 * @generated
	 * @ordered
	 */
	protected EList<Taxonomy> contributedTaxonomies;

	/**
	 * The cached value of the '{@link #getOfferedServices() <em>Offered Services</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOfferedServices()
	 * @generated
	 * @ordered
	 */
	protected EList<OfferedService> offeredServices;

	/**
	 * The cached value of the '{@link #getAvailableFederationScenarios() <em>Available Federation Scenarios</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailableFederationScenarios()
	 * @generated
	 * @ordered
	 */
	protected EList<RequestedFederationScenario> availableFederationScenarios;

	/**
	 * The cached value of the '{@link #getOfferedServiceCompositions() <em>Offered Service Compositions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOfferedServiceCompositions()
	 * @generated
	 * @ordered
	 */
	protected EList<ServiceComposition> offeredServiceCompositions;

	/**
	 * The cached value of the '{@link #getResourceServiceContracts() <em>Resource Service Contracts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceServiceContracts()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceServiceContract> resourceServiceContracts;

	/**
	 * The cached value of the '{@link #getSLAs() <em>SL As</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSLAs()
	 * @generated
	 * @ordered
	 */
	protected EList<SLA> slAs;

	/**
	 * The default value of the '{@link #getResourceURI() <em>Resource URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceURI()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOURCE_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResourceURI() <em>Resource URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceURI()
	 * @generated
	 * @ordered
	 */
	protected String resourceURI = RESOURCE_URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getAPIGateway() <em>API Gateway</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAPIGateway()
	 * @generated
	 * @ordered
	 */
	protected static final String API_GATEWAY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAPIGateway() <em>API Gateway</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAPIGateway()
	 * @generated
	 * @ordered
	 */
	protected String apiGateway = API_GATEWAY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OfficeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FederationOfficePackage.Literals.OFFICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OfficeUser> getRegisteredUsers() {
		if (registeredUsers == null) {
			registeredUsers = new EObjectContainmentEList.Resolving<OfficeUser>(OfficeUser.class, this, FederationOfficePackage.OFFICE__REGISTERED_USERS);
		}
		return registeredUsers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Taxonomy> getContributedTaxonomies() {
		if (contributedTaxonomies == null) {
			contributedTaxonomies = new EObjectContainmentEList.Resolving<Taxonomy>(Taxonomy.class, this, FederationOfficePackage.OFFICE__CONTRIBUTED_TAXONOMIES);
		}
		return contributedTaxonomies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OfferedService> getOfferedServices() {
		if (offeredServices == null) {
			offeredServices = new EObjectContainmentEList.Resolving<OfferedService>(OfferedService.class, this, FederationOfficePackage.OFFICE__OFFERED_SERVICES);
		}
		return offeredServices;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RequestedFederationScenario> getAvailableFederationScenarios() {
		if (availableFederationScenarios == null) {
			availableFederationScenarios = new EObjectContainmentEList.Resolving<RequestedFederationScenario>(RequestedFederationScenario.class, this, FederationOfficePackage.OFFICE__AVAILABLE_FEDERATION_SCENARIOS);
		}
		return availableFederationScenarios;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ServiceComposition> getOfferedServiceCompositions() {
		if (offeredServiceCompositions == null) {
			offeredServiceCompositions = new EObjectContainmentEList.Resolving<ServiceComposition>(ServiceComposition.class, this, FederationOfficePackage.OFFICE__OFFERED_SERVICE_COMPOSITIONS);
		}
		return offeredServiceCompositions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResourceServiceContract> getResourceServiceContracts() {
		if (resourceServiceContracts == null) {
			resourceServiceContracts = new EObjectContainmentEList.Resolving<ResourceServiceContract>(ResourceServiceContract.class, this, FederationOfficePackage.OFFICE__RESOURCE_SERVICE_CONTRACTS);
		}
		return resourceServiceContracts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SLA> getSLAs() {
		if (slAs == null) {
			slAs = new EObjectContainmentEList.Resolving<SLA>(SLA.class, this, FederationOfficePackage.OFFICE__SL_AS);
		}
		return slAs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResourceURI() {
		return resourceURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceURI(String newResourceURI) {
		String oldResourceURI = resourceURI;
		resourceURI = newResourceURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FederationOfficePackage.OFFICE__RESOURCE_URI, oldResourceURI, resourceURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAPIGateway() {
		return apiGateway;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAPIGateway(String newAPIGateway) {
		String oldAPIGateway = apiGateway;
		apiGateway = newAPIGateway;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FederationOfficePackage.OFFICE__API_GATEWAY, oldAPIGateway, apiGateway));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FederationOfficePackage.OFFICE__REGISTERED_USERS:
				return ((InternalEList<?>)getRegisteredUsers()).basicRemove(otherEnd, msgs);
			case FederationOfficePackage.OFFICE__CONTRIBUTED_TAXONOMIES:
				return ((InternalEList<?>)getContributedTaxonomies()).basicRemove(otherEnd, msgs);
			case FederationOfficePackage.OFFICE__OFFERED_SERVICES:
				return ((InternalEList<?>)getOfferedServices()).basicRemove(otherEnd, msgs);
			case FederationOfficePackage.OFFICE__AVAILABLE_FEDERATION_SCENARIOS:
				return ((InternalEList<?>)getAvailableFederationScenarios()).basicRemove(otherEnd, msgs);
			case FederationOfficePackage.OFFICE__OFFERED_SERVICE_COMPOSITIONS:
				return ((InternalEList<?>)getOfferedServiceCompositions()).basicRemove(otherEnd, msgs);
			case FederationOfficePackage.OFFICE__RESOURCE_SERVICE_CONTRACTS:
				return ((InternalEList<?>)getResourceServiceContracts()).basicRemove(otherEnd, msgs);
			case FederationOfficePackage.OFFICE__SL_AS:
				return ((InternalEList<?>)getSLAs()).basicRemove(otherEnd, msgs);
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
			case FederationOfficePackage.OFFICE__REGISTERED_USERS:
				return getRegisteredUsers();
			case FederationOfficePackage.OFFICE__CONTRIBUTED_TAXONOMIES:
				return getContributedTaxonomies();
			case FederationOfficePackage.OFFICE__OFFERED_SERVICES:
				return getOfferedServices();
			case FederationOfficePackage.OFFICE__AVAILABLE_FEDERATION_SCENARIOS:
				return getAvailableFederationScenarios();
			case FederationOfficePackage.OFFICE__OFFERED_SERVICE_COMPOSITIONS:
				return getOfferedServiceCompositions();
			case FederationOfficePackage.OFFICE__RESOURCE_SERVICE_CONTRACTS:
				return getResourceServiceContracts();
			case FederationOfficePackage.OFFICE__SL_AS:
				return getSLAs();
			case FederationOfficePackage.OFFICE__RESOURCE_URI:
				return getResourceURI();
			case FederationOfficePackage.OFFICE__API_GATEWAY:
				return getAPIGateway();
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
			case FederationOfficePackage.OFFICE__REGISTERED_USERS:
				getRegisteredUsers().clear();
				getRegisteredUsers().addAll((Collection<? extends OfficeUser>)newValue);
				return;
			case FederationOfficePackage.OFFICE__CONTRIBUTED_TAXONOMIES:
				getContributedTaxonomies().clear();
				getContributedTaxonomies().addAll((Collection<? extends Taxonomy>)newValue);
				return;
			case FederationOfficePackage.OFFICE__OFFERED_SERVICES:
				getOfferedServices().clear();
				getOfferedServices().addAll((Collection<? extends OfferedService>)newValue);
				return;
			case FederationOfficePackage.OFFICE__AVAILABLE_FEDERATION_SCENARIOS:
				getAvailableFederationScenarios().clear();
				getAvailableFederationScenarios().addAll((Collection<? extends RequestedFederationScenario>)newValue);
				return;
			case FederationOfficePackage.OFFICE__OFFERED_SERVICE_COMPOSITIONS:
				getOfferedServiceCompositions().clear();
				getOfferedServiceCompositions().addAll((Collection<? extends ServiceComposition>)newValue);
				return;
			case FederationOfficePackage.OFFICE__RESOURCE_SERVICE_CONTRACTS:
				getResourceServiceContracts().clear();
				getResourceServiceContracts().addAll((Collection<? extends ResourceServiceContract>)newValue);
				return;
			case FederationOfficePackage.OFFICE__SL_AS:
				getSLAs().clear();
				getSLAs().addAll((Collection<? extends SLA>)newValue);
				return;
			case FederationOfficePackage.OFFICE__RESOURCE_URI:
				setResourceURI((String)newValue);
				return;
			case FederationOfficePackage.OFFICE__API_GATEWAY:
				setAPIGateway((String)newValue);
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
			case FederationOfficePackage.OFFICE__REGISTERED_USERS:
				getRegisteredUsers().clear();
				return;
			case FederationOfficePackage.OFFICE__CONTRIBUTED_TAXONOMIES:
				getContributedTaxonomies().clear();
				return;
			case FederationOfficePackage.OFFICE__OFFERED_SERVICES:
				getOfferedServices().clear();
				return;
			case FederationOfficePackage.OFFICE__AVAILABLE_FEDERATION_SCENARIOS:
				getAvailableFederationScenarios().clear();
				return;
			case FederationOfficePackage.OFFICE__OFFERED_SERVICE_COMPOSITIONS:
				getOfferedServiceCompositions().clear();
				return;
			case FederationOfficePackage.OFFICE__RESOURCE_SERVICE_CONTRACTS:
				getResourceServiceContracts().clear();
				return;
			case FederationOfficePackage.OFFICE__SL_AS:
				getSLAs().clear();
				return;
			case FederationOfficePackage.OFFICE__RESOURCE_URI:
				setResourceURI(RESOURCE_URI_EDEFAULT);
				return;
			case FederationOfficePackage.OFFICE__API_GATEWAY:
				setAPIGateway(API_GATEWAY_EDEFAULT);
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
			case FederationOfficePackage.OFFICE__REGISTERED_USERS:
				return registeredUsers != null && !registeredUsers.isEmpty();
			case FederationOfficePackage.OFFICE__CONTRIBUTED_TAXONOMIES:
				return contributedTaxonomies != null && !contributedTaxonomies.isEmpty();
			case FederationOfficePackage.OFFICE__OFFERED_SERVICES:
				return offeredServices != null && !offeredServices.isEmpty();
			case FederationOfficePackage.OFFICE__AVAILABLE_FEDERATION_SCENARIOS:
				return availableFederationScenarios != null && !availableFederationScenarios.isEmpty();
			case FederationOfficePackage.OFFICE__OFFERED_SERVICE_COMPOSITIONS:
				return offeredServiceCompositions != null && !offeredServiceCompositions.isEmpty();
			case FederationOfficePackage.OFFICE__RESOURCE_SERVICE_CONTRACTS:
				return resourceServiceContracts != null && !resourceServiceContracts.isEmpty();
			case FederationOfficePackage.OFFICE__SL_AS:
				return slAs != null && !slAs.isEmpty();
			case FederationOfficePackage.OFFICE__RESOURCE_URI:
				return RESOURCE_URI_EDEFAULT == null ? resourceURI != null : !RESOURCE_URI_EDEFAULT.equals(resourceURI);
			case FederationOfficePackage.OFFICE__API_GATEWAY:
				return API_GATEWAY_EDEFAULT == null ? apiGateway != null : !API_GATEWAY_EDEFAULT.equals(apiGateway);
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
		result.append(" (resourceURI: ");
		result.append(resourceURI);
		result.append(", APIGateway: ");
		result.append(apiGateway);
		result.append(')');
		return result.toString();
	}

} //OfficeImpl
