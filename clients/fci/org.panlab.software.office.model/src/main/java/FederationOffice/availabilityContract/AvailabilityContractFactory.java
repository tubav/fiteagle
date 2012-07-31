/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.availabilityContract;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see FederationOffice.availabilityContract.AvailabilityContractPackage
 * @generated
 */
public interface AvailabilityContractFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AvailabilityContractFactory eINSTANCE = FederationOffice.availabilityContract.impl.AvailabilityContractFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Resource Service Contract</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Service Contract</em>'.
	 * @generated
	 */
	ResourceServiceContract createResourceServiceContract();

	/**
	 * Returns a new object of class '<em>Availability</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Availability</em>'.
	 * @generated
	 */
	Availability createAvailability();

	/**
	 * Returns a new object of class '<em>Cost</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cost</em>'.
	 * @generated
	 */
	Cost createCost();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	AvailabilityContractPackage getAvailabilityContractPackage();

} //AvailabilityContractFactory
