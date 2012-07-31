/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.slareservations;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import FederationOffice.FederationOfficePackage;

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
 * @see FederationOffice.slareservations.SlareservationsFactory
 * @model kind="package"
 * @generated
 */
public interface SlareservationsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "slareservations";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.panlab.org/FederationOffice/model/slareservations";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.panlab.FederationOffice.model.slareservations";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SlareservationsPackage eINSTANCE = FederationOffice.slareservations.impl.SlareservationsPackageImpl.init();

	/**
	 * The meta object id for the '{@link FederationOffice.slareservations.impl.SLAImpl <em>SLA</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.slareservations.impl.SLAImpl
	 * @see FederationOffice.slareservations.impl.SlareservationsPackageImpl#getSLA()
	 * @generated
	 */
	int SLA = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLA__NAME = FederationOfficePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLA__ID = FederationOfficePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLA__DESCRIPTION = FederationOfficePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLA__UNIQUE_ID = FederationOfficePackage.NAMED_ELEMENT__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Reserved Rsources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLA__RESERVED_RSOURCES = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>For VT</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLA__FOR_VT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Valid From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLA__VALID_FROM = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Valid Until</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLA__VALID_UNTIL = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>SLA</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SLA_FEATURE_COUNT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link FederationOffice.slareservations.impl.ReservedResourceContractImpl <em>Reserved Resource Contract</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.slareservations.impl.ReservedResourceContractImpl
	 * @see FederationOffice.slareservations.impl.SlareservationsPackageImpl#getReservedResourceContract()
	 * @generated
	 */
	int RESERVED_RESOURCE_CONTRACT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESERVED_RESOURCE_CONTRACT__NAME = FederationOfficePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESERVED_RESOURCE_CONTRACT__ID = FederationOfficePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESERVED_RESOURCE_CONTRACT__DESCRIPTION = FederationOfficePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESERVED_RESOURCE_CONTRACT__UNIQUE_ID = FederationOfficePackage.NAMED_ELEMENT__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Valid From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESERVED_RESOURCE_CONTRACT__VALID_FROM = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>For Resource</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESERVED_RESOURCE_CONTRACT__FOR_RESOURCE = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Valid Until</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESERVED_RESOURCE_CONTRACT__VALID_UNTIL = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Reserved Resource Contract</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESERVED_RESOURCE_CONTRACT_FEATURE_COUNT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;


	/**
	 * Returns the meta object for class '{@link FederationOffice.slareservations.SLA <em>SLA</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SLA</em>'.
	 * @see FederationOffice.slareservations.SLA
	 * @generated
	 */
	EClass getSLA();

	/**
	 * Returns the meta object for the containment reference list '{@link FederationOffice.slareservations.SLA#getReservedRsources <em>Reserved Rsources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Reserved Rsources</em>'.
	 * @see FederationOffice.slareservations.SLA#getReservedRsources()
	 * @see #getSLA()
	 * @generated
	 */
	EReference getSLA_ReservedRsources();

	/**
	 * Returns the meta object for the reference '{@link FederationOffice.slareservations.SLA#getForVT <em>For VT</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>For VT</em>'.
	 * @see FederationOffice.slareservations.SLA#getForVT()
	 * @see #getSLA()
	 * @generated
	 */
	EReference getSLA_ForVT();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.slareservations.SLA#getValidFrom <em>Valid From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid From</em>'.
	 * @see FederationOffice.slareservations.SLA#getValidFrom()
	 * @see #getSLA()
	 * @generated
	 */
	EAttribute getSLA_ValidFrom();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.slareservations.SLA#getValidUntil <em>Valid Until</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid Until</em>'.
	 * @see FederationOffice.slareservations.SLA#getValidUntil()
	 * @see #getSLA()
	 * @generated
	 */
	EAttribute getSLA_ValidUntil();

	/**
	 * Returns the meta object for class '{@link FederationOffice.slareservations.ReservedResourceContract <em>Reserved Resource Contract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reserved Resource Contract</em>'.
	 * @see FederationOffice.slareservations.ReservedResourceContract
	 * @generated
	 */
	EClass getReservedResourceContract();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.slareservations.ReservedResourceContract#getValidFrom <em>Valid From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid From</em>'.
	 * @see FederationOffice.slareservations.ReservedResourceContract#getValidFrom()
	 * @see #getReservedResourceContract()
	 * @generated
	 */
	EAttribute getReservedResourceContract_ValidFrom();

	/**
	 * Returns the meta object for the reference '{@link FederationOffice.slareservations.ReservedResourceContract#getForResource <em>For Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>For Resource</em>'.
	 * @see FederationOffice.slareservations.ReservedResourceContract#getForResource()
	 * @see #getReservedResourceContract()
	 * @generated
	 */
	EReference getReservedResourceContract_ForResource();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.slareservations.ReservedResourceContract#getValidUntil <em>Valid Until</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid Until</em>'.
	 * @see FederationOffice.slareservations.ReservedResourceContract#getValidUntil()
	 * @see #getReservedResourceContract()
	 * @generated
	 */
	EAttribute getReservedResourceContract_ValidUntil();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SlareservationsFactory getSlareservationsFactory();

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
		 * The meta object literal for the '{@link FederationOffice.slareservations.impl.SLAImpl <em>SLA</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.slareservations.impl.SLAImpl
		 * @see FederationOffice.slareservations.impl.SlareservationsPackageImpl#getSLA()
		 * @generated
		 */
		EClass SLA = eINSTANCE.getSLA();

		/**
		 * The meta object literal for the '<em><b>Reserved Rsources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SLA__RESERVED_RSOURCES = eINSTANCE.getSLA_ReservedRsources();

		/**
		 * The meta object literal for the '<em><b>For VT</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SLA__FOR_VT = eINSTANCE.getSLA_ForVT();

		/**
		 * The meta object literal for the '<em><b>Valid From</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SLA__VALID_FROM = eINSTANCE.getSLA_ValidFrom();

		/**
		 * The meta object literal for the '<em><b>Valid Until</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SLA__VALID_UNTIL = eINSTANCE.getSLA_ValidUntil();

		/**
		 * The meta object literal for the '{@link FederationOffice.slareservations.impl.ReservedResourceContractImpl <em>Reserved Resource Contract</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.slareservations.impl.ReservedResourceContractImpl
		 * @see FederationOffice.slareservations.impl.SlareservationsPackageImpl#getReservedResourceContract()
		 * @generated
		 */
		EClass RESERVED_RESOURCE_CONTRACT = eINSTANCE.getReservedResourceContract();

		/**
		 * The meta object literal for the '<em><b>Valid From</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESERVED_RESOURCE_CONTRACT__VALID_FROM = eINSTANCE.getReservedResourceContract_ValidFrom();

		/**
		 * The meta object literal for the '<em><b>For Resource</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESERVED_RESOURCE_CONTRACT__FOR_RESOURCE = eINSTANCE.getReservedResourceContract_ForResource();

		/**
		 * The meta object literal for the '<em><b>Valid Until</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESERVED_RESOURCE_CONTRACT__VALID_UNTIL = eINSTANCE.getReservedResourceContract_ValidUntil();

	}

} //SlareservationsPackage
