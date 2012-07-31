/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package FederationOffice.experimentRuntime;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Runtime Element Status</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see FederationOffice.experimentRuntime.ExperimentRuntimePackage#getRuntimeElementStatus()
 * @model
 * @generated
 */
public enum RuntimeElementStatus implements Enumerator {
	/**
	 * The '<em><b>NOT EXISTS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_EXISTS_VALUE
	 * @generated
	 * @ordered
	 */
	NOT_EXISTS(0, "NOT_EXISTS", "NOT_EXISTS"),

	/**
	 * The '<em><b>ONLINE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ONLINE_VALUE
	 * @generated
	 * @ordered
	 */
	ONLINE(1, "ONLINE", "ONLINE"),

	/**
	 * The '<em><b>OFFLINE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OFFLINE_VALUE
	 * @generated
	 * @ordered
	 */
	OFFLINE(2, "OFFLINE", "OFFLINE"),

	/**
	 * The '<em><b>STARTING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STARTING_VALUE
	 * @generated
	 * @ordered
	 */
	STARTING(3, "STARTING", "STARTING"),

	/**
	 * The '<em><b>STOPPING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STOPPING_VALUE
	 * @generated
	 * @ordered
	 */
	STOPPING(4, "STOPPING", "STOPPING"),

	/**
	 * The '<em><b>UPDATING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UPDATING_VALUE
	 * @generated
	 * @ordered
	 */
	UPDATING(5, "UPDATING", "UPDATING"),

	/**
	 * The '<em><b>ERROR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ERROR_VALUE
	 * @generated
	 * @ordered
	 */
	ERROR(6, "ERROR", "ERROR"),

	/**
	 * The '<em><b>UNKNOWN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN_VALUE
	 * @generated
	 * @ordered
	 */
	UNKNOWN(7, "UNKNOWN", "UNKNOWN"), /**
	 * The '<em><b>INITIALIZING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INITIALIZING_VALUE
	 * @generated
	 * @ordered
	 */
	INITIALIZING(8, "INITIALIZING", "INITIALIZING"), /**
	 * The '<em><b>WAITING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WAITING_VALUE
	 * @generated
	 * @ordered
	 */
	WAITING(9, "WAITING", "WAITING");

	/**
	 * The '<em><b>NOT EXISTS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NOT EXISTS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NOT_EXISTS
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NOT_EXISTS_VALUE = 0;

	/**
	 * The '<em><b>ONLINE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ONLINE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ONLINE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ONLINE_VALUE = 1;

	/**
	 * The '<em><b>OFFLINE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>OFFLINE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OFFLINE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int OFFLINE_VALUE = 2;

	/**
	 * The '<em><b>STARTING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STARTING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STARTING
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int STARTING_VALUE = 3;

	/**
	 * The '<em><b>STOPPING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STOPPING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STOPPING
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int STOPPING_VALUE = 4;

	/**
	 * The '<em><b>UPDATING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UPDATING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UPDATING
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UPDATING_VALUE = 5;

	/**
	 * The '<em><b>ERROR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ERROR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ERROR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ERROR_VALUE = 6;

	/**
	 * The '<em><b>UNKNOWN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNKNOWN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UNKNOWN_VALUE = 7;

	/**
	 * The '<em><b>INITIALIZING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INITIALIZING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INITIALIZING
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int INITIALIZING_VALUE = 8;

	/**
	 * The '<em><b>WAITING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WAITING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WAITING
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int WAITING_VALUE = 9;

	/**
	 * An array of all the '<em><b>Runtime Element Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final RuntimeElementStatus[] VALUES_ARRAY =
		new RuntimeElementStatus[] {
			NOT_EXISTS,
			ONLINE,
			OFFLINE,
			STARTING,
			STOPPING,
			UPDATING,
			ERROR,
			UNKNOWN,
			INITIALIZING,
			WAITING,
		};

	/**
	 * A public read-only list of all the '<em><b>Runtime Element Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<RuntimeElementStatus> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Runtime Element Status</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RuntimeElementStatus get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RuntimeElementStatus result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Runtime Element Status</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RuntimeElementStatus getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RuntimeElementStatus result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Runtime Element Status</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RuntimeElementStatus get(int value) {
		switch (value) {
			case NOT_EXISTS_VALUE: return NOT_EXISTS;
			case ONLINE_VALUE: return ONLINE;
			case OFFLINE_VALUE: return OFFLINE;
			case STARTING_VALUE: return STARTING;
			case STOPPING_VALUE: return STOPPING;
			case UPDATING_VALUE: return UPDATING;
			case ERROR_VALUE: return ERROR;
			case UNKNOWN_VALUE: return UNKNOWN;
			case INITIALIZING_VALUE: return INITIALIZING;
			case WAITING_VALUE: return WAITING;
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
	private RuntimeElementStatus(int value, String name, String literal) {
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
	
} //RuntimeElementStatus
