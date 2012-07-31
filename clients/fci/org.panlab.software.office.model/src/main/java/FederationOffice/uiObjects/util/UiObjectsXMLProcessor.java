/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.uiObjects.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import FederationOffice.uiObjects.UiObjectsPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class UiObjectsXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UiObjectsXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		UiObjectsPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the UiObjectsResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new UiObjectsResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new UiObjectsResourceFactoryImpl());
		}
		return registrations;
	}

} //UiObjectsXMLProcessor
