/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.availabilityContract.impl;

import FederationOffice.availabilityContract.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import FederationOffice.availabilityContract.Availability;
import FederationOffice.availabilityContract.AvailabilityContractFactory;
import FederationOffice.availabilityContract.AvailabilityContractPackage;
import FederationOffice.availabilityContract.Cost;
import FederationOffice.availabilityContract.RepatabilityScheme;
import FederationOffice.availabilityContract.ResourceServiceContract;
import FederationOffice.availabilityContract.costUnit;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AvailabilityContractFactoryImpl extends EFactoryImpl implements AvailabilityContractFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AvailabilityContractFactory init() {
		try {
			AvailabilityContractFactory theAvailabilityContractFactory = (AvailabilityContractFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.panlab.org/FederationOffice/model/availabilitycontract"); 
			if (theAvailabilityContractFactory != null) {
				return theAvailabilityContractFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AvailabilityContractFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AvailabilityContractFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case AvailabilityContractPackage.RESOURCE_SERVICE_CONTRACT: return createResourceServiceContract();
			case AvailabilityContractPackage.AVAILABILITY: return createAvailability();
			case AvailabilityContractPackage.COST: return createCost();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case AvailabilityContractPackage.COST_UNIT:
				return createcostUnitFromString(eDataType, initialValue);
			case AvailabilityContractPackage.REPATABILITY_SCHEME:
				return createRepatabilitySchemeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case AvailabilityContractPackage.COST_UNIT:
				return convertcostUnitToString(eDataType, instanceValue);
			case AvailabilityContractPackage.REPATABILITY_SCHEME:
				return convertRepatabilitySchemeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceServiceContract createResourceServiceContract() {
		ResourceServiceContractImpl resourceServiceContract = new ResourceServiceContractImpl();
		return resourceServiceContract;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Availability createAvailability() {
		AvailabilityImpl availability = new AvailabilityImpl();
		return availability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Cost createCost() {
		CostImpl cost = new CostImpl();
		return cost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public costUnit createcostUnitFromString(EDataType eDataType, String initialValue) {
		costUnit result = costUnit.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertcostUnitToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RepatabilityScheme createRepatabilitySchemeFromString(EDataType eDataType, String initialValue) {
		RepatabilityScheme result = RepatabilityScheme.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRepatabilitySchemeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AvailabilityContractPackage getAvailabilityContractPackage() {
		return (AvailabilityContractPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AvailabilityContractPackage getPackage() {
		return AvailabilityContractPackage.eINSTANCE;
	}

} //AvailabilityContractFactoryImpl
