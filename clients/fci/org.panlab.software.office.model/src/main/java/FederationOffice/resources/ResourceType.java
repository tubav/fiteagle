/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Resource Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see FederationOffice.resources.ResourcesPackage#getResourceType()
 * @model
 * @generated
 */
public enum ResourceType implements Enumerator {
	/**
	 * The '<em><b>SINGLETON</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SINGLETON_VALUE
	 * @generated
	 * @ordered
	 */
	SINGLETON(0, "SINGLETON", "SINGLETON"),

	/**
	 * The '<em><b>MULTITON</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MULTITON_VALUE
	 * @generated
	 * @ordered
	 */
	MULTITON(1, "MULTITON", "MULTITON");

	/**
	 * The '<em><b>SINGLETON</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Singleton Resource:
	 * One VT can use it each time; 
	 * CAN be reserved; 
	 * installation fee + rent fee
	 * For example Asterisk server of UoP 
	 * 
	 * 
	 * <!-- end-model-doc -->
	 * @see #SINGLETON
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SINGLETON_VALUE = 0;

	/**
	 * The '<em><b>MULTITON</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Multiton resource:
	 * Many testbeds can "instantiate" the same resource, but when instantiated each instance is unique; 
	 * CANNOT be reserved; Although availability might be affected by multitonMaxOccur 
	 * optional installation fee  +  optional rent fee
	 * For example VOIP_UA , or a resource which installs a software on a machine which can be anywhere just given only ie ssh access
	 * <!-- end-model-doc -->
	 * @see #MULTITON
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MULTITON_VALUE = 1;

	/**
	 * An array of all the '<em><b>Resource Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ResourceType[] VALUES_ARRAY =
		new ResourceType[] {
			SINGLETON,
			MULTITON,
		};

	/**
	 * A public read-only list of all the '<em><b>Resource Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ResourceType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Resource Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResourceType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ResourceType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Resource Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResourceType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ResourceType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Resource Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResourceType get(int value) {
		switch (value) {
			case SINGLETON_VALUE: return SINGLETON;
			case MULTITON_VALUE: return MULTITON;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ResourceType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ResourceType
