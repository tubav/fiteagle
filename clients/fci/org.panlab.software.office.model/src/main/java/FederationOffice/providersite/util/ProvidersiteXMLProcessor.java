/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.providersite.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import FederationOffice.providersite.ProvidersitePackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ProvidersiteXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProvidersiteXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		ProvidersitePackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the ProvidersiteResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new ProvidersiteResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new ProvidersiteResourceFactoryImpl());
		}
		return registrations;
	}

} //ProvidersiteXMLProcessor
