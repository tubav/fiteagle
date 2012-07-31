/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.experimentRuntime.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import FederationOffice.experimentRuntime.ExperimentRuntimePackage;
import FederationOffice.experimentRuntime.RunningScenarios;
import FederationOffice.federationscenarios.RequestedFederationScenario;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Running Scenarios</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link FederationOffice.experimentRuntime.impl.RunningScenariosImpl#getRequestedScenarios <em>Requested Scenarios</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RunningScenariosImpl extends EObjectImpl implements RunningScenarios {
	/**
	 * The cached value of the '{@link #getRequestedScenarios() <em>Requested Scenarios</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequestedScenarios()
	 * @generated
	 * @ordered
	 */
	protected EList<RequestedFederationScenario> requestedScenarios;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RunningScenariosImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExperimentRuntimePackage.Literals.RUNNING_SCENARIOS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RequestedFederationScenario> getRequestedScenarios() {
		if (requestedScenarios == null) {
			requestedScenarios = new EObjectResolvingEList<RequestedFederationScenario>(RequestedFederationScenario.class, this, ExperimentRuntimePackage.RUNNING_SCENARIOS__REQUESTED_SCENARIOS);
		}
		return requestedScenarios;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ExperimentRuntimePackage.RUNNING_SCENARIOS__REQUESTED_SCENARIOS:
				return getRequestedScenarios();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ExperimentRuntimePackage.RUNNING_SCENARIOS__REQUESTED_SCENARIOS:
				getRequestedScenarios().clear();
				getRequestedScenarios().addAll((Collection<? extends RequestedFederationScenario>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ExperimentRuntimePackage.RUNNING_SCENARIOS__REQUESTED_SCENARIOS:
				getRequestedScenarios().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ExperimentRuntimePackage.RUNNING_SCENARIOS__REQUESTED_SCENARIOS:
				return requestedScenarios != null && !requestedScenarios.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //RunningScenariosImpl
