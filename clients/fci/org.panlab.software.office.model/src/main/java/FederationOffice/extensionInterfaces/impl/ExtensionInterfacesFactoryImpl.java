/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.extensionInterfaces.impl;

import FederationOffice.extensionInterfaces.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import FederationOffice.extensionInterfaces.ExtensionInterfacesFactory;
import FederationOffice.extensionInterfaces.ExtensionInterfacesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExtensionInterfacesFactoryImpl extends EFactoryImpl implements ExtensionInterfacesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ExtensionInterfacesFactory init() {
		try {
			ExtensionInterfacesFactory theExtensionInterfacesFactory = (ExtensionInterfacesFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.panlab.org/FederationOffice/model/extensionInterfaces"); 
			if (theExtensionInterfacesFactory != null) {
				return theExtensionInterfacesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ExtensionInterfacesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtensionInterfacesFactoryImpl() {
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
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtensionInterfacesPackage getExtensionInterfacesPackage() {
		return (ExtensionInterfacesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ExtensionInterfacesPackage getPackage() {
		return ExtensionInterfacesPackage.eINSTANCE;
	}

} //ExtensionInterfacesFactoryImpl
