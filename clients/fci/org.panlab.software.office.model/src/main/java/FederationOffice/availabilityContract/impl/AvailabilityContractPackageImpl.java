/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.availabilityContract.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import FederationOffice.FederationOfficePackage;
import FederationOffice.availabilityContract.Availability;
import FederationOffice.availabilityContract.AvailabilityContractFactory;
import FederationOffice.availabilityContract.AvailabilityContractPackage;
import FederationOffice.availabilityContract.Cost;
import FederationOffice.availabilityContract.RepatabilityScheme;
import FederationOffice.availabilityContract.ResourceServiceContract;
import FederationOffice.availabilityContract.costUnit;
import FederationOffice.experimentRuntime.ExperimentRuntimePackage;
import FederationOffice.experimentRuntime.impl.ExperimentRuntimePackageImpl;
import FederationOffice.extensionInterfaces.ExtensionInterfacesPackage;
import FederationOffice.extensionInterfaces.impl.ExtensionInterfacesPackageImpl;
import FederationOffice.federationscenarios.FederationscenariosPackage;
import FederationOffice.federationscenarios.impl.FederationscenariosPackageImpl;
import FederationOffice.impl.FederationOfficePackageImpl;
import FederationOffice.networkelements.NetworkelementsPackage;
import FederationOffice.networkelements.impl.NetworkelementsPackageImpl;
import FederationOffice.providersite.ProvidersitePackage;
import FederationOffice.providersite.impl.ProvidersitePackageImpl;
import FederationOffice.resources.ResourcesPackage;
import FederationOffice.resources.impl.ResourcesPackageImpl;
import FederationOffice.services.ServicesPackage;
import FederationOffice.services.impl.ServicesPackageImpl;
import FederationOffice.slareservations.SlareservationsPackage;
import FederationOffice.slareservations.impl.SlareservationsPackageImpl;
import FederationOffice.uiObjects.UiObjectsPackage;
import FederationOffice.uiObjects.impl.UiObjectsPackageImpl;
import FederationOffice.users.UsersPackage;
import FederationOffice.users.impl.UsersPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AvailabilityContractPackageImpl extends EPackageImpl implements AvailabilityContractPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceServiceContractEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass availabilityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass costEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum costUnitEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum repatabilitySchemeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see FederationOffice.availabilityContract.AvailabilityContractPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AvailabilityContractPackageImpl() {
		super(eNS_URI, AvailabilityContractFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link AvailabilityContractPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AvailabilityContractPackage init() {
		if (isInited) return (AvailabilityContractPackage)EPackage.Registry.INSTANCE.getEPackage(AvailabilityContractPackage.eNS_URI);

		// Obtain or create and register package
		AvailabilityContractPackageImpl theAvailabilityContractPackage = (AvailabilityContractPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof AvailabilityContractPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new AvailabilityContractPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		FederationOfficePackageImpl theFederationOfficePackage = (FederationOfficePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FederationOfficePackage.eNS_URI) instanceof FederationOfficePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FederationOfficePackage.eNS_URI) : FederationOfficePackage.eINSTANCE);
		UsersPackageImpl theUsersPackage = (UsersPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UsersPackage.eNS_URI) instanceof UsersPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UsersPackage.eNS_URI) : UsersPackage.eINSTANCE);
		ResourcesPackageImpl theResourcesPackage = (ResourcesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ResourcesPackage.eNS_URI) instanceof ResourcesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ResourcesPackage.eNS_URI) : ResourcesPackage.eINSTANCE);
		NetworkelementsPackageImpl theNetworkelementsPackage = (NetworkelementsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(NetworkelementsPackage.eNS_URI) instanceof NetworkelementsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(NetworkelementsPackage.eNS_URI) : NetworkelementsPackage.eINSTANCE);
		ProvidersitePackageImpl theProvidersitePackage = (ProvidersitePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ProvidersitePackage.eNS_URI) instanceof ProvidersitePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ProvidersitePackage.eNS_URI) : ProvidersitePackage.eINSTANCE);
		SlareservationsPackageImpl theSlareservationsPackage = (SlareservationsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SlareservationsPackage.eNS_URI) instanceof SlareservationsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SlareservationsPackage.eNS_URI) : SlareservationsPackage.eINSTANCE);
		FederationscenariosPackageImpl theFederationscenariosPackage = (FederationscenariosPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FederationscenariosPackage.eNS_URI) instanceof FederationscenariosPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FederationscenariosPackage.eNS_URI) : FederationscenariosPackage.eINSTANCE);
		ServicesPackageImpl theServicesPackage = (ServicesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ServicesPackage.eNS_URI) instanceof ServicesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ServicesPackage.eNS_URI) : ServicesPackage.eINSTANCE);
		UiObjectsPackageImpl theUiObjectsPackage = (UiObjectsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UiObjectsPackage.eNS_URI) instanceof UiObjectsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UiObjectsPackage.eNS_URI) : UiObjectsPackage.eINSTANCE);
		ExperimentRuntimePackageImpl theExperimentRuntimePackage = (ExperimentRuntimePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExperimentRuntimePackage.eNS_URI) instanceof ExperimentRuntimePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExperimentRuntimePackage.eNS_URI) : ExperimentRuntimePackage.eINSTANCE);
		ExtensionInterfacesPackageImpl theExtensionInterfacesPackage = (ExtensionInterfacesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExtensionInterfacesPackage.eNS_URI) instanceof ExtensionInterfacesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExtensionInterfacesPackage.eNS_URI) : ExtensionInterfacesPackage.eINSTANCE);

		// Create package meta-data objects
		theAvailabilityContractPackage.createPackageContents();
		theFederationOfficePackage.createPackageContents();
		theUsersPackage.createPackageContents();
		theResourcesPackage.createPackageContents();
		theNetworkelementsPackage.createPackageContents();
		theProvidersitePackage.createPackageContents();
		theSlareservationsPackage.createPackageContents();
		theFederationscenariosPackage.createPackageContents();
		theServicesPackage.createPackageContents();
		theUiObjectsPackage.createPackageContents();
		theExperimentRuntimePackage.createPackageContents();
		theExtensionInterfacesPackage.createPackageContents();

		// Initialize created meta-data
		theAvailabilityContractPackage.initializePackageContents();
		theFederationOfficePackage.initializePackageContents();
		theUsersPackage.initializePackageContents();
		theResourcesPackage.initializePackageContents();
		theNetworkelementsPackage.initializePackageContents();
		theProvidersitePackage.initializePackageContents();
		theSlareservationsPackage.initializePackageContents();
		theFederationscenariosPackage.initializePackageContents();
		theServicesPackage.initializePackageContents();
		theUiObjectsPackage.initializePackageContents();
		theExperimentRuntimePackage.initializePackageContents();
		theExtensionInterfacesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAvailabilityContractPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AvailabilityContractPackage.eNS_URI, theAvailabilityContractPackage);
		return theAvailabilityContractPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceServiceContract() {
		return resourceServiceContractEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceServiceContract_ForOfferedService() {
		return (EReference)resourceServiceContractEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceServiceContract_ForOfferedResource() {
		return (EReference)resourceServiceContractEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceServiceContract_StartDate() {
		return (EAttribute)resourceServiceContractEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceServiceContract_EndDate() {
		return (EAttribute)resourceServiceContractEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceServiceContract_Availability() {
		return (EReference)resourceServiceContractEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAvailability() {
		return availabilityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAvailability_FromTime() {
		return (EAttribute)availabilityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAvailability_ToTime() {
		return (EAttribute)availabilityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAvailability_Cost() {
		return (EReference)availabilityEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAvailability_FromDate() {
		return (EAttribute)availabilityEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAvailability_ToDate() {
		return (EAttribute)availabilityEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAvailability_Repeatability() {
		return (EAttribute)availabilityEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAvailability_Repeat() {
		return (EAttribute)availabilityEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAvailability_RepeatUntil() {
		return (EAttribute)availabilityEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCost() {
		return costEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCost_Amount() {
		return (EAttribute)costEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCost_PerUnit() {
		return (EAttribute)costEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getcostUnit() {
		return costUnitEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getRepatabilityScheme() {
		return repatabilitySchemeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AvailabilityContractFactory getAvailabilityContractFactory() {
		return (AvailabilityContractFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		resourceServiceContractEClass = createEClass(RESOURCE_SERVICE_CONTRACT);
		createEReference(resourceServiceContractEClass, RESOURCE_SERVICE_CONTRACT__FOR_OFFERED_SERVICE);
		createEReference(resourceServiceContractEClass, RESOURCE_SERVICE_CONTRACT__FOR_OFFERED_RESOURCE);
		createEAttribute(resourceServiceContractEClass, RESOURCE_SERVICE_CONTRACT__START_DATE);
		createEAttribute(resourceServiceContractEClass, RESOURCE_SERVICE_CONTRACT__END_DATE);
		createEReference(resourceServiceContractEClass, RESOURCE_SERVICE_CONTRACT__AVAILABILITY);

		availabilityEClass = createEClass(AVAILABILITY);
		createEAttribute(availabilityEClass, AVAILABILITY__FROM_TIME);
		createEAttribute(availabilityEClass, AVAILABILITY__TO_TIME);
		createEReference(availabilityEClass, AVAILABILITY__COST);
		createEAttribute(availabilityEClass, AVAILABILITY__FROM_DATE);
		createEAttribute(availabilityEClass, AVAILABILITY__TO_DATE);
		createEAttribute(availabilityEClass, AVAILABILITY__REPEATABILITY);
		createEAttribute(availabilityEClass, AVAILABILITY__REPEAT);
		createEAttribute(availabilityEClass, AVAILABILITY__REPEAT_UNTIL);

		costEClass = createEClass(COST);
		createEAttribute(costEClass, COST__PER_UNIT);
		createEAttribute(costEClass, COST__AMOUNT);

		// Create enums
		costUnitEEnum = createEEnum(COST_UNIT);
		repatabilitySchemeEEnum = createEEnum(REPATABILITY_SCHEME);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		FederationOfficePackage theFederationOfficePackage = (FederationOfficePackage)EPackage.Registry.INSTANCE.getEPackage(FederationOfficePackage.eNS_URI);
		ServicesPackage theServicesPackage = (ServicesPackage)EPackage.Registry.INSTANCE.getEPackage(ServicesPackage.eNS_URI);
		ResourcesPackage theResourcesPackage = (ResourcesPackage)EPackage.Registry.INSTANCE.getEPackage(ResourcesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		resourceServiceContractEClass.getESuperTypes().add(theFederationOfficePackage.getNamedElement());

		// Initialize classes and features; add operations and parameters
		initEClass(resourceServiceContractEClass, ResourceServiceContract.class, "ResourceServiceContract", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResourceServiceContract_ForOfferedService(), theServicesPackage.getService(), null, "forOfferedService", null, 1, 1, ResourceServiceContract.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceServiceContract_ForOfferedResource(), theResourcesPackage.getOfferedResource(), null, "forOfferedResource", null, 1, 1, ResourceServiceContract.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceServiceContract_StartDate(), ecorePackage.getEDate(), "StartDate", null, 1, 1, ResourceServiceContract.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceServiceContract_EndDate(), ecorePackage.getEDate(), "EndDate", null, 1, 1, ResourceServiceContract.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceServiceContract_Availability(), this.getAvailability(), null, "availability", null, 0, 1, ResourceServiceContract.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(availabilityEClass, Availability.class, "Availability", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAvailability_FromTime(), ecorePackage.getEDate(), "FromTime", null, 0, 1, Availability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAvailability_ToTime(), ecorePackage.getEDate(), "ToTime", null, 0, 1, Availability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAvailability_Cost(), this.getCost(), null, "cost", null, 0, 1, Availability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAvailability_FromDate(), ecorePackage.getEDate(), "FromDate", null, 0, 1, Availability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAvailability_ToDate(), ecorePackage.getEDate(), "ToDate", null, 0, 1, Availability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAvailability_Repeatability(), this.getRepatabilityScheme(), "Repeatability", null, 0, 1, Availability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAvailability_Repeat(), ecorePackage.getEBoolean(), "Repeat", null, 0, 1, Availability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAvailability_RepeatUntil(), ecorePackage.getEDate(), "RepeatUntil", null, 0, 1, Availability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(costEClass, Cost.class, "Cost", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCost_PerUnit(), this.getcostUnit(), "perUnit", null, 0, 1, Cost.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCost_Amount(), ecorePackage.getEDoubleObject(), "Amount", null, 0, 1, Cost.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(costUnitEEnum, costUnit.class, "costUnit");
		addEEnumLiteral(costUnitEEnum, costUnit.MIN);
		addEEnumLiteral(costUnitEEnum, costUnit.HOUR);
		addEEnumLiteral(costUnitEEnum, costUnit.DAY);
		addEEnumLiteral(costUnitEEnum, costUnit.USAGE);
		addEEnumLiteral(costUnitEEnum, costUnit.OTHER);

		initEEnum(repatabilitySchemeEEnum, RepatabilityScheme.class, "RepatabilityScheme");
		addEEnumLiteral(repatabilitySchemeEEnum, RepatabilityScheme.DAILY);
		addEEnumLiteral(repatabilitySchemeEEnum, RepatabilityScheme.WEEKLY);
		addEEnumLiteral(repatabilitySchemeEEnum, RepatabilityScheme.MONTHLY);
		addEEnumLiteral(repatabilitySchemeEEnum, RepatabilityScheme.YEARLY);
	}

} //AvailabilityContractPackageImpl
