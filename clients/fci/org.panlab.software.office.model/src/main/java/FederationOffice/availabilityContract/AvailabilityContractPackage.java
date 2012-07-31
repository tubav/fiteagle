/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.availabilityContract;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see FederationOffice.availabilityContract.AvailabilityContractFactory
 * @model kind="package"
 * @generated
 */
public interface AvailabilityContractPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "availabilityContract";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.panlab.org/FederationOffice/model/availabilitycontract";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.panlab.FederationOffice.model.availabilitycontract";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AvailabilityContractPackage eINSTANCE = FederationOffice.availabilityContract.impl.AvailabilityContractPackageImpl.init();

	/**
	 * The meta object id for the '{@link FederationOffice.availabilityContract.impl.ResourceServiceContractImpl <em>Resource Service Contract</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.availabilityContract.impl.ResourceServiceContractImpl
	 * @see FederationOffice.availabilityContract.impl.AvailabilityContractPackageImpl#getResourceServiceContract()
	 * @generated
	 */
	int RESOURCE_SERVICE_CONTRACT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SERVICE_CONTRACT__NAME = FederationOfficePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SERVICE_CONTRACT__ID = FederationOfficePackage.NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SERVICE_CONTRACT__DESCRIPTION = FederationOfficePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SERVICE_CONTRACT__UNIQUE_ID = FederationOfficePackage.NAMED_ELEMENT__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>For Offered Service</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SERVICE_CONTRACT__FOR_OFFERED_SERVICE = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>For Offered Resource</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SERVICE_CONTRACT__FOR_OFFERED_RESOURCE = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SERVICE_CONTRACT__START_DATE = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>End Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SERVICE_CONTRACT__END_DATE = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Availability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SERVICE_CONTRACT__AVAILABILITY = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Resource Service Contract</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SERVICE_CONTRACT_FEATURE_COUNT = FederationOfficePackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link FederationOffice.availabilityContract.impl.AvailabilityImpl <em>Availability</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.availabilityContract.impl.AvailabilityImpl
	 * @see FederationOffice.availabilityContract.impl.AvailabilityContractPackageImpl#getAvailability()
	 * @generated
	 */
	int AVAILABILITY = 1;

	/**
	 * The feature id for the '<em><b>From Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY__FROM_TIME = 0;

	/**
	 * The feature id for the '<em><b>To Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY__TO_TIME = 1;

	/**
	 * The feature id for the '<em><b>Cost</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY__COST = 2;

	/**
	 * The feature id for the '<em><b>From Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY__FROM_DATE = 3;

	/**
	 * The feature id for the '<em><b>To Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY__TO_DATE = 4;

	/**
	 * The feature id for the '<em><b>Repeatability</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY__REPEATABILITY = 5;

	/**
	 * The feature id for the '<em><b>Repeat</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY__REPEAT = 6;

	/**
	 * The feature id for the '<em><b>Repeat Until</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY__REPEAT_UNTIL = 7;

	/**
	 * The number of structural features of the '<em>Availability</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link FederationOffice.availabilityContract.impl.CostImpl <em>Cost</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.availabilityContract.impl.CostImpl
	 * @see FederationOffice.availabilityContract.impl.AvailabilityContractPackageImpl#getCost()
	 * @generated
	 */
	int COST = 2;

	/**
	 * The feature id for the '<em><b>Per Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COST__PER_UNIT = 0;

	/**
	 * The feature id for the '<em><b>Amount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COST__AMOUNT = 1;

	/**
	 * The number of structural features of the '<em>Cost</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COST_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link FederationOffice.availabilityContract.costUnit <em>cost Unit</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.availabilityContract.costUnit
	 * @see FederationOffice.availabilityContract.impl.AvailabilityContractPackageImpl#getcostUnit()
	 * @generated
	 */
	int COST_UNIT = 3;

	/**
	 * The meta object id for the '{@link FederationOffice.availabilityContract.RepatabilityScheme <em>Repatability Scheme</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FederationOffice.availabilityContract.RepatabilityScheme
	 * @see FederationOffice.availabilityContract.impl.AvailabilityContractPackageImpl#getRepatabilityScheme()
	 * @generated
	 */
	int REPATABILITY_SCHEME = 4;


	/**
	 * Returns the meta object for class '{@link FederationOffice.availabilityContract.ResourceServiceContract <em>Resource Service Contract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Service Contract</em>'.
	 * @see FederationOffice.availabilityContract.ResourceServiceContract
	 * @generated
	 */
	EClass getResourceServiceContract();

	/**
	 * Returns the meta object for the reference '{@link FederationOffice.availabilityContract.ResourceServiceContract#getForOfferedService <em>For Offered Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>For Offered Service</em>'.
	 * @see FederationOffice.availabilityContract.ResourceServiceContract#getForOfferedService()
	 * @see #getResourceServiceContract()
	 * @generated
	 */
	EReference getResourceServiceContract_ForOfferedService();

	/**
	 * Returns the meta object for the reference '{@link FederationOffice.availabilityContract.ResourceServiceContract#getForOfferedResource <em>For Offered Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>For Offered Resource</em>'.
	 * @see FederationOffice.availabilityContract.ResourceServiceContract#getForOfferedResource()
	 * @see #getResourceServiceContract()
	 * @generated
	 */
	EReference getResourceServiceContract_ForOfferedResource();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.availabilityContract.ResourceServiceContract#getStartDate <em>Start Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Date</em>'.
	 * @see FederationOffice.availabilityContract.ResourceServiceContract#getStartDate()
	 * @see #getResourceServiceContract()
	 * @generated
	 */
	EAttribute getResourceServiceContract_StartDate();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.availabilityContract.ResourceServiceContract#getEndDate <em>End Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End Date</em>'.
	 * @see FederationOffice.availabilityContract.ResourceServiceContract#getEndDate()
	 * @see #getResourceServiceContract()
	 * @generated
	 */
	EAttribute getResourceServiceContract_EndDate();

	/**
	 * Returns the meta object for the containment reference '{@link FederationOffice.availabilityContract.ResourceServiceContract#getAvailability <em>Availability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Availability</em>'.
	 * @see FederationOffice.availabilityContract.ResourceServiceContract#getAvailability()
	 * @see #getResourceServiceContract()
	 * @generated
	 */
	EReference getResourceServiceContract_Availability();

	/**
	 * Returns the meta object for class '{@link FederationOffice.availabilityContract.Availability <em>Availability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Availability</em>'.
	 * @see FederationOffice.availabilityContract.Availability
	 * @generated
	 */
	EClass getAvailability();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.availabilityContract.Availability#getFromTime <em>From Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>From Time</em>'.
	 * @see FederationOffice.availabilityContract.Availability#getFromTime()
	 * @see #getAvailability()
	 * @generated
	 */
	EAttribute getAvailability_FromTime();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.availabilityContract.Availability#getToTime <em>To Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To Time</em>'.
	 * @see FederationOffice.availabilityContract.Availability#getToTime()
	 * @see #getAvailability()
	 * @generated
	 */
	EAttribute getAvailability_ToTime();

	/**
	 * Returns the meta object for the containment reference '{@link FederationOffice.availabilityContract.Availability#getCost <em>Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cost</em>'.
	 * @see FederationOffice.availabilityContract.Availability#getCost()
	 * @see #getAvailability()
	 * @generated
	 */
	EReference getAvailability_Cost();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.availabilityContract.Availability#getFromDate <em>From Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>From Date</em>'.
	 * @see FederationOffice.availabilityContract.Availability#getFromDate()
	 * @see #getAvailability()
	 * @generated
	 */
	EAttribute getAvailability_FromDate();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.availabilityContract.Availability#getToDate <em>To Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To Date</em>'.
	 * @see FederationOffice.availabilityContract.Availability#getToDate()
	 * @see #getAvailability()
	 * @generated
	 */
	EAttribute getAvailability_ToDate();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.availabilityContract.Availability#getRepeatability <em>Repeatability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repeatability</em>'.
	 * @see FederationOffice.availabilityContract.Availability#getRepeatability()
	 * @see #getAvailability()
	 * @generated
	 */
	EAttribute getAvailability_Repeatability();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.availabilityContract.Availability#isRepeat <em>Repeat</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repeat</em>'.
	 * @see FederationOffice.availabilityContract.Availability#isRepeat()
	 * @see #getAvailability()
	 * @generated
	 */
	EAttribute getAvailability_Repeat();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.availabilityContract.Availability#getRepeatUntil <em>Repeat Until</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repeat Until</em>'.
	 * @see FederationOffice.availabilityContract.Availability#getRepeatUntil()
	 * @see #getAvailability()
	 * @generated
	 */
	EAttribute getAvailability_RepeatUntil();

	/**
	 * Returns the meta object for class '{@link FederationOffice.availabilityContract.Cost <em>Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cost</em>'.
	 * @see FederationOffice.availabilityContract.Cost
	 * @generated
	 */
	EClass getCost();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.availabilityContract.Cost#getAmount <em>Amount</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Amount</em>'.
	 * @see FederationOffice.availabilityContract.Cost#getAmount()
	 * @see #getCost()
	 * @generated
	 */
	EAttribute getCost_Amount();

	/**
	 * Returns the meta object for the attribute '{@link FederationOffice.availabilityContract.Cost#getPerUnit <em>Per Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Per Unit</em>'.
	 * @see FederationOffice.availabilityContract.Cost#getPerUnit()
	 * @see #getCost()
	 * @generated
	 */
	EAttribute getCost_PerUnit();

	/**
	 * Returns the meta object for enum '{@link FederationOffice.availabilityContract.costUnit <em>cost Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>cost Unit</em>'.
	 * @see FederationOffice.availabilityContract.costUnit
	 * @generated
	 */
	EEnum getcostUnit();

	/**
	 * Returns the meta object for enum '{@link FederationOffice.availabilityContract.RepatabilityScheme <em>Repatability Scheme</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Repatability Scheme</em>'.
	 * @see FederationOffice.availabilityContract.RepatabilityScheme
	 * @generated
	 */
	EEnum getRepatabilityScheme();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AvailabilityContractFactory getAvailabilityContractFactory();

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
		 * The meta object literal for the '{@link FederationOffice.availabilityContract.impl.ResourceServiceContractImpl <em>Resource Service Contract</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.availabilityContract.impl.ResourceServiceContractImpl
		 * @see FederationOffice.availabilityContract.impl.AvailabilityContractPackageImpl#getResourceServiceContract()
		 * @generated
		 */
		EClass RESOURCE_SERVICE_CONTRACT = eINSTANCE.getResourceServiceContract();

		/**
		 * The meta object literal for the '<em><b>For Offered Service</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_SERVICE_CONTRACT__FOR_OFFERED_SERVICE = eINSTANCE.getResourceServiceContract_ForOfferedService();

		/**
		 * The meta object literal for the '<em><b>For Offered Resource</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_SERVICE_CONTRACT__FOR_OFFERED_RESOURCE = eINSTANCE.getResourceServiceContract_ForOfferedResource();

		/**
		 * The meta object literal for the '<em><b>Start Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_SERVICE_CONTRACT__START_DATE = eINSTANCE.getResourceServiceContract_StartDate();

		/**
		 * The meta object literal for the '<em><b>End Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_SERVICE_CONTRACT__END_DATE = eINSTANCE.getResourceServiceContract_EndDate();

		/**
		 * The meta object literal for the '<em><b>Availability</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_SERVICE_CONTRACT__AVAILABILITY = eINSTANCE.getResourceServiceContract_Availability();

		/**
		 * The meta object literal for the '{@link FederationOffice.availabilityContract.impl.AvailabilityImpl <em>Availability</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.availabilityContract.impl.AvailabilityImpl
		 * @see FederationOffice.availabilityContract.impl.AvailabilityContractPackageImpl#getAvailability()
		 * @generated
		 */
		EClass AVAILABILITY = eINSTANCE.getAvailability();

		/**
		 * The meta object literal for the '<em><b>From Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AVAILABILITY__FROM_TIME = eINSTANCE.getAvailability_FromTime();

		/**
		 * The meta object literal for the '<em><b>To Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AVAILABILITY__TO_TIME = eINSTANCE.getAvailability_ToTime();

		/**
		 * The meta object literal for the '<em><b>Cost</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AVAILABILITY__COST = eINSTANCE.getAvailability_Cost();

		/**
		 * The meta object literal for the '<em><b>From Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AVAILABILITY__FROM_DATE = eINSTANCE.getAvailability_FromDate();

		/**
		 * The meta object literal for the '<em><b>To Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AVAILABILITY__TO_DATE = eINSTANCE.getAvailability_ToDate();

		/**
		 * The meta object literal for the '<em><b>Repeatability</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AVAILABILITY__REPEATABILITY = eINSTANCE.getAvailability_Repeatability();

		/**
		 * The meta object literal for the '<em><b>Repeat</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AVAILABILITY__REPEAT = eINSTANCE.getAvailability_Repeat();

		/**
		 * The meta object literal for the '<em><b>Repeat Until</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AVAILABILITY__REPEAT_UNTIL = eINSTANCE.getAvailability_RepeatUntil();

		/**
		 * The meta object literal for the '{@link FederationOffice.availabilityContract.impl.CostImpl <em>Cost</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.availabilityContract.impl.CostImpl
		 * @see FederationOffice.availabilityContract.impl.AvailabilityContractPackageImpl#getCost()
		 * @generated
		 */
		EClass COST = eINSTANCE.getCost();

		/**
		 * The meta object literal for the '<em><b>Amount</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COST__AMOUNT = eINSTANCE.getCost_Amount();

		/**
		 * The meta object literal for the '<em><b>Per Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COST__PER_UNIT = eINSTANCE.getCost_PerUnit();

		/**
		 * The meta object literal for the '{@link FederationOffice.availabilityContract.costUnit <em>cost Unit</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.availabilityContract.costUnit
		 * @see FederationOffice.availabilityContract.impl.AvailabilityContractPackageImpl#getcostUnit()
		 * @generated
		 */
		EEnum COST_UNIT = eINSTANCE.getcostUnit();

		/**
		 * The meta object literal for the '{@link FederationOffice.availabilityContract.RepatabilityScheme <em>Repatability Scheme</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see FederationOffice.availabilityContract.RepatabilityScheme
		 * @see FederationOffice.availabilityContract.impl.AvailabilityContractPackageImpl#getRepatabilityScheme()
		 * @generated
		 */
		EEnum REPATABILITY_SCHEME = eINSTANCE.getRepatabilityScheme();

	}

} //AvailabilityContractPackage
