/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.extensionInterfaces;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see FederationOffice.extensionInterfaces.ExtensionInterfacesFactory
 * @model kind="package"
 * @generated
 */
public interface ExtensionInterfacesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "extensionInterfaces";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.panlab.org/FederationOffice/model/extensionInterfaces";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.panlab.FederationOffice.model.extensionInterfaces";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExtensionInterfacesPackage eINSTANCE = FederationOffice.extensionInterfaces.impl.ExtensionInterfacesPackageImpl.init();

	/**
	 * The meta object id for the '{@link FederationOffice.extensionInterfaces.IOfficeRepository <em>IOffice Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.extensionInterfaces.IOfficeRepository
	 * @see FederationOffice.extensionInterfaces.impl.ExtensionInterfacesPackageImpl#getIOfficeRepository()
	 * @generated
	 */
	int IOFFICE_REPOSITORY = 0;

	/**
	 * The number of structural features of the '<em>IOffice Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IOFFICE_REPOSITORY_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link FederationOffice.extensionInterfaces.IOfficeRepositoryListener <em>IOffice Repository Listener</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.extensionInterfaces.IOfficeRepositoryListener
	 * @see FederationOffice.extensionInterfaces.impl.ExtensionInterfacesPackageImpl#getIOfficeRepositoryListener()
	 * @generated
	 */
	int IOFFICE_REPOSITORY_LISTENER = 1;

	/**
	 * The number of structural features of the '<em>IOffice Repository Listener</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IOFFICE_REPOSITORY_LISTENER_FEATURE_COUNT = 0;


	/**
	 * The meta object id for the '{@link FederationOffice.extensionInterfaces.IProvisionResource <em>IProvision Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.extensionInterfaces.IProvisionResource
	 * @see FederationOffice.extensionInterfaces.impl.ExtensionInterfacesPackageImpl#getIProvisionResource()
	 * @generated
	 */
	int IPROVISION_RESOURCE = 2;

	/**
	 * The number of structural features of the '<em>IProvision Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPROVISION_RESOURCE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link FederationOffice.extensionInterfaces.IWorkflowEngine <em>IWorkflow Engine</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.extensionInterfaces.IWorkflowEngine
	 * @see FederationOffice.extensionInterfaces.impl.ExtensionInterfacesPackageImpl#getIWorkflowEngine()
	 * @generated
	 */
	int IWORKFLOW_ENGINE = 3;

	/**
	 * The number of structural features of the '<em>IWorkflow Engine</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IWORKFLOW_ENGINE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link FederationOffice.extensionInterfaces.IProvisioningJobListener <em>IProvisioning Job Listener</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.extensionInterfaces.IProvisioningJobListener
	 * @see FederationOffice.extensionInterfaces.impl.ExtensionInterfacesPackageImpl#getIProvisioningJobListener()
	 * @generated
	 */
	int IPROVISIONING_JOB_LISTENER = 4;

	/**
	 * The number of structural features of the '<em>IProvisioning Job Listener</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPROVISIONING_JOB_LISTENER_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link FederationOffice.extensionInterfaces.IProvisioningJobEvent <em>IProvisioning Job Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.extensionInterfaces.IProvisioningJobEvent
	 * @see FederationOffice.extensionInterfaces.impl.ExtensionInterfacesPackageImpl#getIProvisioningJobEvent()
	 * @generated
	 */
	int IPROVISIONING_JOB_EVENT = 5;

	/**
	 * The number of structural features of the '<em>IProvisioning Job Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPROVISIONING_JOB_EVENT_FEATURE_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link FederationOffice.extensionInterfaces.IOfficeRepository <em>IOffice Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IOffice Repository</em>'.
	 * @see FederationOffice.extensionInterfaces.IOfficeRepository
	 * @generated
	 */
	EClass getIOfficeRepository();

	/**
	 * Returns the meta object for class '{@link FederationOffice.extensionInterfaces.IOfficeRepositoryListener <em>IOffice Repository Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IOffice Repository Listener</em>'.
	 * @see FederationOffice.extensionInterfaces.IOfficeRepositoryListener
	 * @generated
	 */
	EClass getIOfficeRepositoryListener();

	/**
	 * Returns the meta object for class '{@link FederationOffice.extensionInterfaces.IProvisionResource <em>IProvision Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IProvision Resource</em>'.
	 * @see FederationOffice.extensionInterfaces.IProvisionResource
	 * @generated
	 */
	EClass getIProvisionResource();

	/**
	 * Returns the meta object for class '{@link FederationOffice.extensionInterfaces.IWorkflowEngine <em>IWorkflow Engine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IWorkflow Engine</em>'.
	 * @see FederationOffice.extensionInterfaces.IWorkflowEngine
	 * @generated
	 */
	EClass getIWorkflowEngine();

	/**
	 * Returns the meta object for class '{@link FederationOffice.extensionInterfaces.IProvisioningJobListener <em>IProvisioning Job Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IProvisioning Job Listener</em>'.
	 * @see FederationOffice.extensionInterfaces.IProvisioningJobListener
	 * @generated
	 */
	EClass getIProvisioningJobListener();

	/**
	 * Returns the meta object for class '{@link FederationOffice.extensionInterfaces.IProvisioningJobEvent <em>IProvisioning Job Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IProvisioning Job Event</em>'.
	 * @see FederationOffice.extensionInterfaces.IProvisioningJobEvent
	 * @generated
	 */
	EClass getIProvisioningJobEvent();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ExtensionInterfacesFactory getExtensionInterfacesFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link FederationOffice.extensionInterfaces.IOfficeRepository <em>IOffice Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.extensionInterfaces.IOfficeRepository
		 * @see FederationOffice.extensionInterfaces.impl.ExtensionInterfacesPackageImpl#getIOfficeRepository()
		 * @generated
		 */
		EClass IOFFICE_REPOSITORY = eINSTANCE.getIOfficeRepository();

		/**
		 * The meta object literal for the '{@link FederationOffice.extensionInterfaces.IOfficeRepositoryListener <em>IOffice Repository Listener</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.extensionInterfaces.IOfficeRepositoryListener
		 * @see FederationOffice.extensionInterfaces.impl.ExtensionInterfacesPackageImpl#getIOfficeRepositoryListener()
		 * @generated
		 */
		EClass IOFFICE_REPOSITORY_LISTENER = eINSTANCE.getIOfficeRepositoryListener();

		/**
		 * The meta object literal for the '{@link FederationOffice.extensionInterfaces.IProvisionResource <em>IProvision Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.extensionInterfaces.IProvisionResource
		 * @see FederationOffice.extensionInterfaces.impl.ExtensionInterfacesPackageImpl#getIProvisionResource()
		 * @generated
		 */
		EClass IPROVISION_RESOURCE = eINSTANCE.getIProvisionResource();

		/**
		 * The meta object literal for the '{@link FederationOffice.extensionInterfaces.IWorkflowEngine <em>IWorkflow Engine</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.extensionInterfaces.IWorkflowEngine
		 * @see FederationOffice.extensionInterfaces.impl.ExtensionInterfacesPackageImpl#getIWorkflowEngine()
		 * @generated
		 */
		EClass IWORKFLOW_ENGINE = eINSTANCE.getIWorkflowEngine();

		/**
		 * The meta object literal for the '{@link FederationOffice.extensionInterfaces.IProvisioningJobListener <em>IProvisioning Job Listener</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.extensionInterfaces.IProvisioningJobListener
		 * @see FederationOffice.extensionInterfaces.impl.ExtensionInterfacesPackageImpl#getIProvisioningJobListener()
		 * @generated
		 */
		EClass IPROVISIONING_JOB_LISTENER = eINSTANCE.getIProvisioningJobListener();

		/**
		 * The meta object literal for the '{@link FederationOffice.extensionInterfaces.IProvisioningJobEvent <em>IProvisioning Job Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.extensionInterfaces.IProvisioningJobEvent
		 * @see FederationOffice.extensionInterfaces.impl.ExtensionInterfacesPackageImpl#getIProvisioningJobEvent()
		 * @generated
		 */
		EClass IPROVISIONING_JOB_EVENT = eINSTANCE.getIProvisioningJobEvent();

	}

} //ExtensionInterfacesPackage
