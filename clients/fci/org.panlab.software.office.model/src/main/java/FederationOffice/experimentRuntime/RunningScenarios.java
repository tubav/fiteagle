/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.experimentRuntime;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import FederationOffice.federationscenarios.RequestedFederationScenario;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Running Scenarios</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link FederationOffice.experimentRuntime.RunningScenarios#getRequestedScenarios <em>Requested Scenarios</em>}</li>
 * </ul>
 * </p>
 *
 * @see FederationOffice.experimentRuntime.ExperimentRuntimePackage#getRunningScenarios()
 * @model
 * @generated
 */
public interface RunningScenarios extends EObject {
	/**
	 * Returns the value of the '<em><b>Requested Scenarios</b></em>' reference list.
	 * The list contents are of type {@link FederationOffice.federationscenarios.RequestedFederationScenario}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requested Scenarios</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requested Scenarios</em>' reference list.
	 * @see FederationOffice.experimentRuntime.ExperimentRuntimePackage#getRunningScenarios_RequestedScenarios()
	 * @model
	 * @generated
	 */
	EList<RequestedFederationScenario> getRequestedScenarios();

} // RunningScenarios
