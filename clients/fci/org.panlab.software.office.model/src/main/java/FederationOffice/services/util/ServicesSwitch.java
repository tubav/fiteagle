/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.services.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import FederationOffice.NamedElement;
import FederationOffice.services.*;
import FederationOffice.services.AbstractSetting;
import FederationOffice.services.OfferedService;
import FederationOffice.services.Service;
import FederationOffice.services.ServiceComposition;
import FederationOffice.services.ServiceSetting;
import FederationOffice.services.ServicesPackage;
import FederationOffice.services.SettingConstraint;
import FederationOffice.services.SettingType;
import FederationOffice.services.Taxonomy;
import FederationOffice.services.tideTypeEnum;
import FederationOffice.services.tideTypeEnumItem;
import FederationOffice.services.tideTypeList;
import FederationOffice.services.tideTypeString;
import FederationOffice.services.tideTypeTideElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see FederationOffice.services.ServicesPackage
 * @generated
 */
public class ServicesSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ServicesPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServicesSwitch() {
		if (modelPackage == null) {
			modelPackage = ServicesPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ServicesPackage.SERVICE: {
				Service service = (Service)theEObject;
				T result = caseService(service);
				if (result == null) result = caseNamedElement(service);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServicesPackage.SERVICE_SETTING: {
				ServiceSetting serviceSetting = (ServiceSetting)theEObject;
				T result = caseServiceSetting(serviceSetting);
				if (result == null) result = caseAbstractSetting(serviceSetting);
				if (result == null) result = caseNamedElement(serviceSetting);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServicesPackage.OFFERED_SERVICE: {
				OfferedService offeredService = (OfferedService)theEObject;
				T result = caseOfferedService(offeredService);
				if (result == null) result = caseService(offeredService);
				if (result == null) result = caseNamedElement(offeredService);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServicesPackage.SERVICE_COMPOSITION: {
				ServiceComposition serviceComposition = (ServiceComposition)theEObject;
				T result = caseServiceComposition(serviceComposition);
				if (result == null) result = caseNamedElement(serviceComposition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServicesPackage.SETTING_TYPE: {
				SettingType settingType = (SettingType)theEObject;
				T result = caseSettingType(settingType);
				if (result == null) result = caseNamedElement(settingType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServicesPackage.TIDE_TYPE_STRING: {
				tideTypeString tideTypeString = (tideTypeString)theEObject;
				T result = casetideTypeString(tideTypeString);
				if (result == null) result = caseSettingType(tideTypeString);
				if (result == null) result = caseNamedElement(tideTypeString);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServicesPackage.TIDE_TYPE_ENUM: {
				tideTypeEnum tideTypeEnum = (tideTypeEnum)theEObject;
				T result = casetideTypeEnum(tideTypeEnum);
				if (result == null) result = caseSettingType(tideTypeEnum);
				if (result == null) result = caseNamedElement(tideTypeEnum);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServicesPackage.TIDE_TYPE_LIST: {
				tideTypeList tideTypeList = (tideTypeList)theEObject;
				T result = casetideTypeList(tideTypeList);
				if (result == null) result = caseSettingType(tideTypeList);
				if (result == null) result = caseNamedElement(tideTypeList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServicesPackage.TIDE_TYPE_ENUM_ITEM: {
				tideTypeEnumItem tideTypeEnumItem = (tideTypeEnumItem)theEObject;
				T result = casetideTypeEnumItem(tideTypeEnumItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServicesPackage.TIDE_TYPE_TIDE_ELEMENT: {
				tideTypeTideElement tideTypeTideElement = (tideTypeTideElement)theEObject;
				T result = casetideTypeTideElement(tideTypeTideElement);
				if (result == null) result = caseSettingType(tideTypeTideElement);
				if (result == null) result = caseNamedElement(tideTypeTideElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServicesPackage.TAXONOMY: {
				Taxonomy taxonomy = (Taxonomy)theEObject;
				T result = caseTaxonomy(taxonomy);
				if (result == null) result = caseNamedElement(taxonomy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServicesPackage.ABSTRACT_SETTING: {
				AbstractSetting abstractSetting = (AbstractSetting)theEObject;
				T result = caseAbstractSetting(abstractSetting);
				if (result == null) result = caseNamedElement(abstractSetting);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ServicesPackage.SETTING_CONSTRAINT: {
				SettingConstraint settingConstraint = (SettingConstraint)theEObject;
				T result = caseSettingConstraint(settingConstraint);
				if (result == null) result = caseNamedElement(settingConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseService(Service object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service Setting</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service Setting</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseServiceSetting(ServiceSetting object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Offered Service</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Offered Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOfferedService(OfferedService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service Composition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service Composition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseServiceComposition(ServiceComposition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Setting Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Setting Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSettingType(SettingType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>tide Type String</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>tide Type String</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casetideTypeString(tideTypeString object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>tide Type Enum</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>tide Type Enum</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casetideTypeEnum(tideTypeEnum object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>tide Type List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>tide Type List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casetideTypeList(tideTypeList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>tide Type Enum Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>tide Type Enum Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casetideTypeEnumItem(tideTypeEnumItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>tide Type Tide Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>tide Type Tide Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casetideTypeTideElement(tideTypeTideElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Taxonomy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Taxonomy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTaxonomy(Taxonomy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Setting</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Setting</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractSetting(AbstractSetting object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Setting Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Setting Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSettingConstraint(SettingConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //ServicesSwitch
