/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.slareservations.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import FederationOffice.slareservations.SlareservationsPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SlareservationsXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SlareservationsXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		SlareservationsPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the SlareservationsResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new SlareservationsResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new SlareservationsResourceFactoryImpl());
		}
		return registrations;
	}

} //SlareservationsXMLProcessor
