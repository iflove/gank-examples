package org.apache.commons.lang3;

/**
 * <p>Operations on boolean primitives and Boolean objects.</p>
 * <p>
 * <p>This class tries to handle {@code null} input gracefully.
 * An exception will not be thrown for a {@code null} input.
 * Each method documents its behaviour in more detail.</p>
 * <p>
 * <p>#ThreadSafe#</p>
 *
 * @version $Id: BooleanUtils.java 1632874 2014-10-19 05:52:37Z djones $
 * @since 2.0
 */
public class BooleanUtils {

	/**
	 * <p>{@code BooleanUtils} instances should NOT be constructed in standard programming.
	 * Instead, the class should be used as {@code BooleanUtils.negate(true);}.</p>
	 * <p/>
	 * <p>This constructor is public to permit tools that require a JavaBean instance
	 * to operate.</p>
	 */
	public BooleanUtils() {
		super();
	}

	// boolean Boolean methods
	//-----------------------------------------------------------------------

	/**
	 * <p>Checks if a {@code Boolean} value is {@code true},
	 * handling {@code null} by returning {@code false}.</p>
	 * <p>
	 * <pre>
	 *   BooleanUtils.isTrue(Boolean.TRUE)  = true
	 *   BooleanUtils.isTrue(Boolean.FALSE) = false
	 *   BooleanUtils.isTrue(null)          = false
	 * </pre>
	 *
	 * @param bool the boolean to check, null returns {@code false}
	 * @return {@code true} only if the input is non-null and true
	 * @since 2.1
	 */
	public static boolean isTrue(final Boolean bool) {
		return Boolean.TRUE.equals(bool);
	}

	/**
	 * <p>Checks if a {@code Boolean} value is <i>not</i> {@code true},
	 * handling {@code null} by returning {@code true}.</p>
	 * <p>
	 * <pre>
	 *   BooleanUtils.isNotTrue(Boolean.TRUE)  = false
	 *   BooleanUtils.isNotTrue(Boolean.FALSE) = true
	 *   BooleanUtils.isNotTrue(null)          = true
	 * </pre>
	 *
	 * @param bool the boolean to check, null returns {@code true}
	 * @return {@code true} if the input is null or false
	 * @since 2.3
	 */
	public static boolean isNotTrue(final Boolean bool) {
		return !isTrue(bool);
	}

	/**
	 * <p>Checks if a {@code Boolean} value is {@code false},
	 * handling {@code null} by returning {@code false}.</p>
	 * <p>
	 * <pre>
	 *   BooleanUtils.isFalse(Boolean.TRUE)  = false
	 *   BooleanUtils.isFalse(Boolean.FALSE) = true
	 *   BooleanUtils.isFalse(null)          = false
	 * </pre>
	 *
	 * @param bool the boolean to check, null returns {@code false}
	 * @return {@code true} only if the input is non-null and false
	 * @since 2.1
	 */
	public static boolean isFalse(final Boolean bool) {
		return Boolean.FALSE.equals(bool);
	}

	/**
	 * <p>Checks if a {@code Boolean} value is <i>not</i> {@code false},
	 * handling {@code null} by returning {@code true}.</p>
	 * <p>
	 * <pre>
	 *   BooleanUtils.isNotFalse(Boolean.TRUE)  = true
	 *   BooleanUtils.isNotFalse(Boolean.FALSE) = false
	 *   BooleanUtils.isNotFalse(null)          = true
	 * </pre>
	 *
	 * @param bool the boolean to check, null returns {@code true}
	 * @return {@code true} if the input is null or true
	 * @since 2.3
	 */
	public static boolean isNotFalse(final Boolean bool) {
		return !isFalse(bool);
	}

	//-----------------------------------------------------------------------

	/**
	 * <p>Converts a Boolean to a boolean handling {@code null}
	 * by returning {@code false}.</p>
	 * <p>
	 * <pre>
	 *   BooleanUtils.toBoolean(Boolean.TRUE)  = true
	 *   BooleanUtils.toBoolean(Boolean.FALSE) = false
	 *   BooleanUtils.toBoolean(null)          = false
	 * </pre>
	 *
	 * @param bool the boolean to convert
	 * @return {@code true} or {@code false}, {@code null} returns {@code false}
	 */
	public static boolean toBoolean(final Boolean bool) {
		return bool != null && bool.booleanValue();
	}

	// logical operations
	// ----------------------------------------------------------------------

	/**
	 * <p>Performs an and on a set of booleans.</p>
	 * <p>
	 * <pre>
	 *   BooleanUtils.and(true, true)         = true
	 *   BooleanUtils.and(false, false)       = false
	 *   BooleanUtils.and(true, false)        = false
	 *   BooleanUtils.and(true, true, false)  = false
	 *   BooleanUtils.and(true, true, true)   = true
	 * </pre>
	 *
	 * @param array an array of {@code boolean}s
	 * @return {@code true} if the and is successful.
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 * @throws IllegalArgumentException if {@code array} is empty.
	 * @since 3.0.1
	 */
	public static boolean and(final boolean... array) {
		// Validates input
		if (array == null) {
			throw new IllegalArgumentException("The Array must not be null");
		}
		if (array.length == 0) {
			throw new IllegalArgumentException("Array is empty");
		}
		for (final boolean element : array) {
			if (!element) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>Performs an and on an array of Booleans.</p>
	 * <p>
	 * <pre>
	 *   BooleanUtils.and(Boolean.TRUE, Boolean.TRUE)                 = Boolean.TRUE
	 *   BooleanUtils.and(Boolean.FALSE, Boolean.FALSE)               = Boolean.FALSE
	 *   BooleanUtils.and(Boolean.TRUE, Boolean.FALSE)                = Boolean.FALSE
	 *   BooleanUtils.and(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE)   = Boolean.TRUE
	 *   BooleanUtils.and(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE) = Boolean.FALSE
	 *   BooleanUtils.and(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE)  = Boolean.FALSE
	 * </pre>
	 *
	 * @param array an array of {@code Boolean}s
	 * @return {@code true} if the and is successful.
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 * @throws IllegalArgumentException if {@code array} is empty.
	 * @throws IllegalArgumentException if {@code array} contains a {@code null}
	 * @since 3.0.1
	 */
	public static Boolean and(final Boolean... array) {
		if (array == null) {
			throw new IllegalArgumentException("The Array must not be null");
		}
		if (array.length == 0) {
			throw new IllegalArgumentException("Array is empty");
		}
		try {
			final boolean[] primitive = ArrayUtils.toPrimitive(array);
			return and(primitive) ? Boolean.TRUE : Boolean.FALSE;
		} catch (final NullPointerException ex) {
			throw new IllegalArgumentException("The array must not contain any null elements");
		}
	}

	/**
	 * <p>Performs an or on a set of booleans.</p>
	 * <p>
	 * <pre>
	 *   BooleanUtils.or(true, true)          = true
	 *   BooleanUtils.or(false, false)        = false
	 *   BooleanUtils.or(true, false)         = true
	 *   BooleanUtils.or(true, true, false)   = true
	 *   BooleanUtils.or(true, true, true)    = true
	 *   BooleanUtils.or(false, false, false) = false
	 * </pre>
	 *
	 * @param array an array of {@code boolean}s
	 * @return {@code true} if the or is successful.
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 * @throws IllegalArgumentException if {@code array} is empty.
	 * @since 3.0.1
	 */
	public static boolean or(final boolean... array) {
		// Validates input
		if (array == null) {
			throw new IllegalArgumentException("The Array must not be null");
		}
		if (array.length == 0) {
			throw new IllegalArgumentException("Array is empty");
		}
		for (final boolean element : array) {
			if (element) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>Performs an or on an array of Booleans.</p>
	 * <p>
	 * <pre>
	 *   BooleanUtils.or(Boolean.TRUE, Boolean.TRUE)                  = Boolean.TRUE
	 *   BooleanUtils.or(Boolean.FALSE, Boolean.FALSE)                = Boolean.FALSE
	 *   BooleanUtils.or(Boolean.TRUE, Boolean.FALSE)                 = Boolean.TRUE
	 *   BooleanUtils.or(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE)    = Boolean.TRUE
	 *   BooleanUtils.or(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE)  = Boolean.TRUE
	 *   BooleanUtils.or(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE)   = Boolean.TRUE
	 *   BooleanUtils.or(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE) = Boolean.FALSE
	 * </pre>
	 *
	 * @param array an array of {@code Boolean}s
	 * @return {@code true} if the or is successful.
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 * @throws IllegalArgumentException if {@code array} is empty.
	 * @throws IllegalArgumentException if {@code array} contains a {@code null}
	 * @since 3.0.1
	 */
	public static Boolean or(final Boolean... array) {
		if (array == null) {
			throw new IllegalArgumentException("The Array must not be null");
		}
		if (array.length == 0) {
			throw new IllegalArgumentException("Array is empty");
		}
		try {
			final boolean[] primitive = ArrayUtils.toPrimitive(array);
			return or(primitive) ? Boolean.TRUE : Boolean.FALSE;
		} catch (final NullPointerException ex) {
			throw new IllegalArgumentException("The array must not contain any null elements");
		}
	}
}