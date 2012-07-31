/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.experimentRuntime;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see FederationOffice.experimentRuntime.ExperimentRuntimePackage
 * @generated
 */
public interface ExperimentRuntimeFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExperimentRuntimeFactory eINSTANCE = FederationOffice.experimentRuntime.impl.ExperimentRuntimeFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Runtime Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Runtime Element</em>'.
	 * @generated
	 */
	RuntimeElement createRuntimeElement();

	/**
	 * Returns a new object of class '<em>Running Scenarios</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Running Scenarios</em>'.
	 * @generated
	 */
	RunningScenarios createRunningScenarios();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ExperimentRuntimePackage getExperimentRuntimePackage();

} //ExperimentRuntimeFactory
