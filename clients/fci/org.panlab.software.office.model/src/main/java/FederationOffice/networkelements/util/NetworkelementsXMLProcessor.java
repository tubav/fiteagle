/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.networkelements.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import FederationOffice.networkelements.NetworkelementsPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class NetworkelementsXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkelementsXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		NetworkelementsPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the NetworkelementsResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new NetworkelementsResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new NetworkelementsResourceFactoryImpl());
		}
		return registrations;
	}

} //NetworkelementsXMLProcessor
