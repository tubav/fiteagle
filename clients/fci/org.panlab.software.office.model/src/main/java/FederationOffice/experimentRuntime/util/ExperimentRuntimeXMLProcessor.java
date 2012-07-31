/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.experimentRuntime.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import FederationOffice.experimentRuntime.ExperimentRuntimePackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ExperimentRuntimeXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExperimentRuntimeXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		ExperimentRuntimePackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the ExperimentRuntimeResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new ExperimentRuntimeResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new ExperimentRuntimeResourceFactoryImpl());
		}
		return registrations;
	}

} //ExperimentRuntimeXMLProcessor
