/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.uiObjects.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import FederationOffice.FederationOfficePackage;
import FederationOffice.availabilityContract.AvailabilityContractPackage;
import FederationOffice.availabilityContract.impl.AvailabilityContractPackageImpl;
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
import FederationOffice.uiObjects.OfficeManager;
import FederationOffice.uiObjects.UiObjectsFactory;
import FederationOffice.uiObjects.UiObjectsPackage;
import FederationOffice.uiObjects.ui_TaxonomyTreeItem;
import FederationOffice.users.UsersPackage;
import FederationOffice.users.impl.UsersPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UiObjectsPackageImpl extends EPackageImpl implements UiObjectsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ui_TaxonomyTreeItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass officeManagerEClass = null;

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
	 * @see FederationOffice.uiObjects.UiObjectsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private UiObjectsPackageImpl() {
		super(eNS_URI, UiObjectsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link UiObjectsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static UiObjectsPackage init() {
		if (isInited) return (UiObjectsPackage)EPackage.Registry.INSTANCE.getEPackage(UiObjectsPackage.eNS_URI);

		// Obtain or create and register package
		UiObjectsPackageImpl theUiObjectsPackage = (UiObjectsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof UiObjectsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new UiObjectsPackageImpl());

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
		ExperimentRuntimePackageImpl theExperimentRuntimePackage = (ExperimentRuntimePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExperimentRuntimePackage.eNS_URI) instanceof ExperimentRuntimePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExperimentRuntimePackage.eNS_URI) : ExperimentRuntimePackage.eINSTANCE);
		ExtensionInterfacesPackageImpl theExtensionInterfacesPackage = (ExtensionInterfacesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExtensionInterfacesPackage.eNS_URI) instanceof ExtensionInterfacesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExtensionInterfacesPackage.eNS_URI) : ExtensionInterfacesPackage.eINSTANCE);

		// Create package meta-data objects
		theUiObjectsPackage.createPackageContents();
		theFederationOfficePackage.createPackageContents();
		theUsersPackage.createPackageContents();
		theResourcesPackage.createPackageContents();
		theNetworkelementsPackage.createPackageContents();
		theProvidersitePackage.createPackageContents();
		theSlareservationsPackage.createPackageContents();
		theFederationscenariosPackage.createPackageContents();
		theServicesPackage.createPackageContents();
		theAvailabilityContractPackage.createPackageContents();
		theExperimentRuntimePackage.createPackageContents();
		theExtensionInterfacesPackage.createPackageContents();

		// Initialize created meta-data
		theUiObjectsPackage.initializePackageContents();
		theFederationOfficePackage.initializePackageContents();
		theUsersPackage.initializePackageContents();
		theResourcesPackage.initializePackageContents();
		theNetworkelementsPackage.initializePackageContents();
		theProvidersitePackage.initializePackageContents();
		theSlareservationsPackage.initializePackageContents();
		theFederationscenariosPackage.initializePackageContents();
		theServicesPackage.initializePackageContents();
		theAvailabilityContractPackage.initializePackageContents();
		theExperimentRuntimePackage.initializePackageContents();
		theExtensionInterfacesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUiObjectsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(UiObjectsPackage.eNS_URI, theUiObjectsPackage);
		return theUiObjectsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getui_TaxonomyTreeItem() {
		return ui_TaxonomyTreeItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getui_TaxonomyTreeItem_ParentNode() {
		return (EReference)ui_TaxonomyTreeItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getui_TaxonomyTreeItem_DisplayName() {
		return (EAttribute)ui_TaxonomyTreeItemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getui_TaxonomyTreeItem_ChildNodes() {
		return (EReference)ui_TaxonomyTreeItemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getui_TaxonomyTreeItem_OfficeElement() {
		return (EReference)ui_TaxonomyTreeItemEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOfficeManager() {
		return officeManagerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOfficeManager_OfficesRef() {
		return (EReference)officeManagerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UiObjectsFactory getUiObjectsFactory() {
		return (UiObjectsFactory)getEFactoryInstance();
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
		ui_TaxonomyTreeItemEClass = createEClass(UI_TAXONOMY_TREE_ITEM);
		createEReference(ui_TaxonomyTreeItemEClass, UI_TAXONOMY_TREE_ITEM__PARENT_NODE);
		createEAttribute(ui_TaxonomyTreeItemEClass, UI_TAXONOMY_TREE_ITEM__DISPLAY_NAME);
		createEReference(ui_TaxonomyTreeItemEClass, UI_TAXONOMY_TREE_ITEM__CHILD_NODES);
		createEReference(ui_TaxonomyTreeItemEClass, UI_TAXONOMY_TREE_ITEM__OFFICE_ELEMENT);

		officeManagerEClass = createEClass(OFFICE_MANAGER);
		createEReference(officeManagerEClass, OFFICE_MANAGER__OFFICES_REF);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(ui_TaxonomyTreeItemEClass, ui_TaxonomyTreeItem.class, "ui_TaxonomyTreeItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getui_TaxonomyTreeItem_ParentNode(), this.getui_TaxonomyTreeItem(), null, "parentNode", null, 0, 1, ui_TaxonomyTreeItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getui_TaxonomyTreeItem_DisplayName(), ecorePackage.getEString(), "DisplayName", null, 0, 1, ui_TaxonomyTreeItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getui_TaxonomyTreeItem_ChildNodes(), this.getui_TaxonomyTreeItem(), null, "childNodes", null, 0, -1, ui_TaxonomyTreeItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getui_TaxonomyTreeItem_OfficeElement(), theFederationOfficePackage.getNamedElement(), null, "officeElement", null, 0, 1, ui_TaxonomyTreeItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(officeManagerEClass, OfficeManager.class, "OfficeManager", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOfficeManager_OfficesRef(), theFederationOfficePackage.getOffice(), null, "officesRef", null, 0, -1, OfficeManager.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
	}

} //UiObjectsPackageImpl
