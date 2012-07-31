/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.slareservations.impl;

import FederationOffice.slareservations.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import FederationOffice.slareservations.ReservedResourceContract;
import FederationOffice.slareservations.SLA;
import FederationOffice.slareservations.SlareservationsFactory;
import FederationOffice.slareservations.SlareservationsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SlareservationsFactoryImpl extends EFactoryImpl implements SlareservationsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SlareservationsFactory init() {
		try {
			SlareservationsFactory theSlareservationsFactory = (SlareservationsFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.panlab.org/FederationOffice/model/slareservations"); 
			if (theSlareservationsFactory != null) {
				return theSlareservationsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SlareservationsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SlareservationsFactoryImpl() {
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
			case SlareservationsPackage.SLA: return createSLA();
			case SlareservationsPackage.RESERVED_RESOURCE_CONTRACT: return createReservedResourceContract();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SLA createSLA() {
		SLAImpl sla = new SLAImpl();
		return sla;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReservedResourceContract createReservedResourceContract() {
		ReservedResourceContractImpl reservedResourceContract = new ReservedResourceContractImpl();
		return reservedResourceContract;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SlareservationsPackage getSlareservationsPackage() {
		return (SlareservationsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SlareservationsPackage getPackage() {
		return SlareservationsPackage.eINSTANCE;
	}

} //SlareservationsFactoryImpl
