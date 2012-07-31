/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.networkelements.impl;

import FederationOffice.networkelements.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import FederationOffice.networkelements.Device;
import FederationOffice.networkelements.NetworkElement;
import FederationOffice.networkelements.NetworkelementsFactory;
import FederationOffice.networkelements.NetworkelementsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class NetworkelementsFactoryImpl extends EFactoryImpl implements NetworkelementsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NetworkelementsFactory init() {
		try {
			NetworkelementsFactory theNetworkelementsFactory = (NetworkelementsFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.panlab.org/FederationOffice/model/networkelements"); 
			if (theNetworkelementsFactory != null) {
				return theNetworkelementsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new NetworkelementsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkelementsFactoryImpl() {
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
			case NetworkelementsPackage.DEVICE: return createDevice();
			case NetworkelementsPackage.NETWORK_ELEMENT: return createNetworkElement();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Device createDevice() {
		DeviceImpl device = new DeviceImpl();
		return device;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkElement createNetworkElement() {
		NetworkElementImpl networkElement = new NetworkElementImpl();
		return networkElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkelementsPackage getNetworkelementsPackage() {
		return (NetworkelementsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static NetworkelementsPackage getPackage() {
		return NetworkelementsPackage.eINSTANCE;
	}

} //NetworkelementsFactoryImpl
