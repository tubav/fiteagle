/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.experimentRuntime.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import FederationOffice.FederationOfficePackage;
import FederationOffice.availabilityContract.AvailabilityContractPackage;
import FederationOffice.availabilityContract.impl.AvailabilityContractPackageImpl;
import FederationOffice.experimentRuntime.ExperimentRuntimeFactory;
import FederationOffice.experimentRuntime.ExperimentRuntimePackage;
import FederationOffice.experimentRuntime.RunningScenarios;
import FederationOffice.experimentRuntime.RuntimeElement;
import FederationOffice.experimentRuntime.RuntimeElementStatus;
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
public class ExperimentRuntimePackageImpl extends EPackageImpl implements ExperimentRuntimePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass runtimeElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass runningScenariosEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum runtimeElementStatusEEnum = null;

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
	 * @see FederationOffice.experimentRuntime.ExperimentRuntimePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ExperimentRuntimePackageImpl() {
		super(eNS_URI, ExperimentRuntimeFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ExperimentRuntimePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ExperimentRuntimePackage init() {
		if (isInited) return (ExperimentRuntimePackage)EPackage.Registry.INSTANCE.getEPackage(ExperimentRuntimePackage.eNS_URI);

		// Obtain or create and register package
		ExperimentRuntimePackageImpl theExperimentRuntimePackage = (ExperimentRuntimePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ExperimentRuntimePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ExperimentRuntimePackageImpl());

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
		AvailabilityContractPackageImpl theAvailabilityContractPackage = (AvailabilityContractPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AvailabilityContractPackage.eNS_URI) instanceof AvailabilityContractPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AvailabilityContractPackage.eNS_URI) : AvailabilityContractPackage.eINSTANCE);
		UiObjectsPackageImpl theUiObjectsPackage = (UiObjectsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UiObjectsPackage.eNS_URI) instanceof UiObjectsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UiObjectsPackage.eNS_URI) : UiObjectsPackage.eINSTANCE);
		ExtensionInterfacesPackageImpl theExtensionInterfacesPackage = (ExtensionInterfacesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExtensionInterfacesPackage.eNS_URI) instanceof ExtensionInterfacesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExtensionInterfacesPackage.eNS_URI) : ExtensionInterfacesPackage.eINSTANCE);

		// Create package meta-data objects
		theExperimentRuntimePackage.createPackageContents();
		theFederationOfficePackage.createPackageContents();
		theUsersPackage.createPackageContents();
		theResourcesPackage.createPackageContents();
		theNetworkelementsPackage.createPackageContents();
		theProvidersitePackage.createPackageContents();
		theSlareservationsPackage.createPackageContents();
		theFederationscenariosPackage.createPackageContents();
		theServicesPackage.createPackageContents();
		theAvailabilityContractPackage.createPackageContents();
		theUiObjectsPackage.createPackageContents();
		theExtensionInterfacesPackage.createPackageContents();

		// Initialize created meta-data
		theExperimentRuntimePackage.initializePackageContents();
		theFederationOfficePackage.initializePackageContents();
		theUsersPackage.initializePackageContents();
		theResourcesPackage.initializePackageContents();
		theNetworkelementsPackage.initializePackageContents();
		theProvidersitePackage.initializePackageContents();
		theSlareservationsPackage.initializePackageContents();
		theFederationscenariosPackage.initializePackageContents();
		theServicesPackage.initializePackageContents();
		theAvailabilityContractPackage.initializePackageContents();
		theUiObjectsPackage.initializePackageContents();
		theExtensionInterfacesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theExperimentRuntimePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ExperimentRuntimePackage.eNS_URI, theExperimentRuntimePackage);
		return theExperimentRuntimePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRuntimeElement() {
		return runtimeElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRuntimeElement_Status() {
		return (EAttribute)runtimeElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRuntimeElement_GUID() {
		return (EAttribute)runtimeElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRuntimeElement_Context() {
		return (EAttribute)runtimeElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRunningScenarios() {
		return runningScenariosEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRunningScenarios_RequestedScenarios() {
		return (EReference)runningScenariosEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getRuntimeElementStatus() {
		return runtimeElementStatusEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExperimentRuntimeFactory getExperimentRuntimeFactory() {
		return (ExperimentRuntimeFactory)getEFactoryInstance();
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
		runtimeElementEClass = createEClass(RUNTIME_ELEMENT);
		createEAttribute(runtimeElementEClass, RUNTIME_ELEMENT__STATUS);
		createEAttribute(runtimeElementEClass, RUNTIME_ELEMENT__GUID);
		createEAttribute(runtimeElementEClass, RUNTIME_ELEMENT__CONTEXT);

		runningScenariosEClass = createEClass(RUNNING_SCENARIOS);
		createEReference(runningScenariosEClass, RUNNING_SCENARIOS__REQUESTED_SCENARIOS);

		// Create enums
		runtimeElementStatusEEnum = createEEnum(RUNTIME_ELEMENT_STATUS);
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
		FederationscenariosPackage theFederationscenariosPackage = (FederationscenariosPackage)EPackage.Registry.INSTANCE.getEPackage(FederationscenariosPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		runtimeElementEClass.getESuperTypes().add(theFederationOfficePackage.getNamedElement());

		// Initialize classes and features; add operations and parameters
		initEClass(runtimeElementEClass, RuntimeElement.class, "RuntimeElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRuntimeElement_Status(), this.getRuntimeElementStatus(), "status", null, 0, 1, RuntimeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRuntimeElement_GUID(), ecorePackage.getEString(), "GUID", null, 0, 1, RuntimeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRuntimeElement_Context(), ecorePackage.getEString(), "context", null, 0, 1, RuntimeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(runningScenariosEClass, RunningScenarios.class, "RunningScenarios", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRunningScenarios_RequestedScenarios(), theFederationscenariosPackage.getRequestedFederationScenario(), null, "requestedScenarios", null, 0, -1, RunningScenarios.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(runtimeElementStatusEEnum, RuntimeElementStatus.class, "RuntimeElementStatus");
		addEEnumLiteral(runtimeElementStatusEEnum, RuntimeElementStatus.NOT_EXISTS);
		addEEnumLiteral(runtimeElementStatusEEnum, RuntimeElementStatus.ONLINE);
		addEEnumLiteral(runtimeElementStatusEEnum, RuntimeElementStatus.OFFLINE);
		addEEnumLiteral(runtimeElementStatusEEnum, RuntimeElementStatus.STARTING);
		addEEnumLiteral(runtimeElementStatusEEnum, RuntimeElementStatus.STOPPING);
		addEEnumLiteral(runtimeElementStatusEEnum, RuntimeElementStatus.UPDATING);
		addEEnumLiteral(runtimeElementStatusEEnum, RuntimeElementStatus.ERROR);
		addEEnumLiteral(runtimeElementStatusEEnum, RuntimeElementStatus.UNKNOWN);
		addEEnumLiteral(runtimeElementStatusEEnum, RuntimeElementStatus.INITIALIZING);
		addEEnumLiteral(runtimeElementStatusEEnum, RuntimeElementStatus.WAITING);
	}

} //ExperimentRuntimePackageImpl
