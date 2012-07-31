/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see FederationOffice.FederationOfficeFactory
 * @model kind="package"
 * @generated
 */
public interface FederationOfficePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "FederationOffice";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.panlab.org/FederationOffice";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.panlab.FederationOffice";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FederationOfficePackage eINSTANCE = FederationOffice.impl.FederationOfficePackageImpl.init();

	/**
	 * The meta object id for the '{@link FederationOffice.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.impl.NamedElementImpl
	 * @see FederationOffice.impl.FederationOfficePackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__ID = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__UNIQUE_ID = 3;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link FederationOffice.impl.OfficeImpl <em>Office</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.impl.OfficeImpl
	 * @see FederationOffice.impl.FederationOfficePackageImpl#getOffice()
	 * @generated
	 */
	int OFFICE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE__ID = NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE__UNIQUE_ID = NAMED_ELEMENT__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Registered Users</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE__REGISTERED_USERS = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Contributed Taxonomies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE__CONTRIBUTED_TAXONOMIES = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Offered Services</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE__OFFERED_SERVICES = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Available Federation Scenarios</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE__AVAILABLE_FEDERATION_SCENARIOS = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Offered Service Compositions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE__OFFERED_SERVICE_COMPOSITIONS = NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Resource Service Contracts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE__RESOURCE_SERVICE_CONTRACTS = NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>SL As</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE__SL_AS = NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Resource URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE__RESOURCE_URI = NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>API Gateway</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE__API_GATEWAY = NAMED_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Office</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OFFICE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link FederationOffice.VTStatus <em>VT Status</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.VTStatus
	 * @see FederationOffice.impl.FederationOfficePackageImpl#getVTStatus()
	 * @generated
	 */
	int VT_STATUS = 2;


	/**
	 * Returns the meta object for class '{@link FederationOffice.Office <em>Office</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Office</em>'.
	 * @see FederationOffice.Office
	 * @generated
	 */
	EClass getOffice();

	/**
	 * Returns the meta object for the containment reference list '{@link FederationOffice.Office#getRegisteredUsers <em>Registered Users</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Registered Users</em>'.
	 * @see FederationOffice.Office#getRegisteredUsers()
	 * @see #getOffice()
	 * @generated
	 */
	EReference getOffice_RegisteredUsers();

	/**
	 * Returns the meta object for the containment reference list '{@link FederationOffice.Office#getContributedTaxonomies <em>Contributed Taxonomies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contributed Taxonomies</em>'.
	 * @see FederationOffice.Office#getContributedTaxonomies()
	 * @see #getOffice()
	 * @generated
	 */
	EReference getOffice_ContributedTaxonomies();

	/**
	 * Returns the meta object for the containment reference list '{@link FederationOffice.Office#getOfferedServices <em>Offered Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Offered Services</em>'.
	 * @see FederationOffice.Office#getOfferedServices()
	 * @see #getOffice()
	 * @generated
	 */
	EReference getOffice_OfferedServices();

	/**
	 * Returns the meta object for the containment reference list '{@link FederationOffice.Office#getAvailableFederationScenarios <em>Available Federation Scenarios</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Available Federation Scenarios</em>'.
	 * @see FederationOffice.Office#getAvailableFederationScenarios()
	 * @see #getOffice()
	 * @generated
	 */
	EReference getOffice_AvailableFederationScenarios();

	/**
	 * Returns the meta object for the containment reference list '{@link FederationOffice.Office#getOfferedServiceCompositions <em>Offered Service Compositions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Offered Service Compositions</em>'.
	 * @see FederationOffice.Office#getOfferedServiceCompositions()
	 * @see #getOffice()
	 * @generated
	 */
	EReference getOffice_OfferedServiceCompositions();

	/**
	 * Returns the meta object for the containment reference list '{@link FederationOffice.Office#getResourceServiceContracts <em>Resource Service Contracts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Service Contracts</em>'.
	 * @see FederationOffice.Office#getResourceServiceContracts()
	 * @see #getOffice()
	 * @generated
	 */
	EReference getOffice_ResourceServiceContracts();

	/**
	 * Returns the meta object for the containment reference list '{@link FederationOffice.Office#getSLAs <em>SL As</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>SL As</em>'.
	 * @see FederationOffice.Office#getSLAs()
	 * @see #getOffice()
	 * @generated
	 */
	EReference getOffice_SLAs();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.Office#getResourceURI <em>Resource URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource URI</em>'.
	 * @see FederationOffice.Office#getResourceURI()
	 * @see #getOffice()
	 * @generated
	 */
	EAttribute getOffice_ResourceURI();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.Office#getAPIGateway <em>API Gateway</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>API Gateway</em>'.
	 * @see FederationOffice.Office#getAPIGateway()
	 * @see #getOffice()
	 * @generated
	 */
	EAttribute getOffice_APIGateway();

	/**
	 * Returns the meta object for class '{@link FederationOffice.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see FederationOffice.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see FederationOffice.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.NamedElement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see FederationOffice.NamedElement#getId()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Id();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.NamedElement#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see FederationOffice.NamedElement#getDescription()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Description();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.NamedElement#getUniqueID <em>Unique ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unique ID</em>'.
	 * @see FederationOffice.NamedElement#getUniqueID()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_UniqueID();

	/**
	 * Returns the meta object for enum '{@link FederationOffice.VTStatus <em>VT Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>VT Status</em>'.
	 * @see FederationOffice.VTStatus
	 * @generated
	 */
	EEnum getVTStatus();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	FederationOfficeFactory getFederationOfficeFactory();

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
		 * The meta object literal for the '{@link FederationOffice.impl.OfficeImpl <em>Office</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.impl.OfficeImpl
		 * @see FederationOffice.impl.FederationOfficePackageImpl#getOffice()
		 * @generated
		 */
		EClass OFFICE = eINSTANCE.getOffice();

		/**
		 * The meta object literal for the '<em><b>Registered Users</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OFFICE__REGISTERED_USERS = eINSTANCE.getOffice_RegisteredUsers();

		/**
		 * The meta object literal for the '<em><b>Contributed Taxonomies</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OFFICE__CONTRIBUTED_TAXONOMIES = eINSTANCE.getOffice_ContributedTaxonomies();

		/**
		 * The meta object literal for the '<em><b>Offered Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OFFICE__OFFERED_SERVICES = eINSTANCE.getOffice_OfferedServices();

		/**
		 * The meta object literal for the '<em><b>Available Federation Scenarios</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OFFICE__AVAILABLE_FEDERATION_SCENARIOS = eINSTANCE.getOffice_AvailableFederationScenarios();

		/**
		 * The meta object literal for the '<em><b>Offered Service Compositions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OFFICE__OFFERED_SERVICE_COMPOSITIONS = eINSTANCE.getOffice_OfferedServiceCompositions();

		/**
		 * The meta object literal for the '<em><b>Resource Service Contracts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OFFICE__RESOURCE_SERVICE_CONTRACTS = eINSTANCE.getOffice_ResourceServiceContracts();

		/**
		 * The meta object literal for the '<em><b>SL As</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OFFICE__SL_AS = eINSTANCE.getOffice_SLAs();

		/**
		 * The meta object literal for the '<em><b>Resource URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OFFICE__RESOURCE_URI = eINSTANCE.getOffice_ResourceURI();

		/**
		 * The meta object literal for the '<em><b>API Gateway</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OFFICE__API_GATEWAY = eINSTANCE.getOffice_APIGateway();

		/**
		 * The meta object literal for the '{@link FederationOffice.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.impl.NamedElementImpl
		 * @see FederationOffice.impl.FederationOfficePackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__ID = eINSTANCE.getNamedElement_Id();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__DESCRIPTION = eINSTANCE.getNamedElement_Description();

		/**
		 * The meta object literal for the '<em><b>Unique ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__UNIQUE_ID = eINSTANCE.getNamedElement_UniqueID();

		/**
		 * The meta object literal for the '{@link FederationOffice.VTStatus <em>VT Status</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.VTStatus
		 * @see FederationOffice.impl.FederationOfficePackageImpl#getVTStatus()
		 * @generated
		 */
		EEnum VT_STATUS = eINSTANCE.getVTStatus();

	}

} //FederationOfficePackage
