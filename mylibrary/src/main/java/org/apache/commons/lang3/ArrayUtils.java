package org.apache.commons.lang3;

/**
 * <p>Operations on arrays, primitive arrays (like {@code int[]}) and
 * primitive wrapper arrays (like {@code Integer[]}).</p>
 * <p>
 * <p>This class tries to handle {@code null} input gracefully.
 * An exception will not be thrown for a {@code null}
 * array input. However, an Object array that contains a {@code null}
 * element may throw an exception. Each method documents its behaviour.</p>
 * <p>
 * <p>#ThreadSafe#</p>
 *
 * @version $Id: ArrayUtils.java 1645483 2014-12-14 18:22:06Z kinow $
 * @since 2.0
 */
public class ArrayUtils {

	/**
	 * An empty immutable {@code boolean} array.
	 */
	public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];

	// Generic array
	//-----------------------------------------------------------------------

	/**
	 * <p>Create a type-safe generic array.</p>
	 * <p>
	 * <p>The Java language does not allow an array to be created from a generic type:</p>
	 * <p>
	 * <pre>
	 * public static &lt;T&gt; T[] createAnArray(int size) {
	 * return new T[size]; // compiler error here
	 * }
	 * public static &lt;T&gt; T[] createAnArray(int size) {
	 * return (T[])new Object[size]; // ClassCastException at runtime
	 * }
	 * </pre>
	 * <p>
	 * <p>Therefore new arrays of generic types can be created with this method.
	 * For example, an array of Strings can be created:</p>
	 * <p>
	 * <pre>
	 * String[] array = ArrayUtils.toArray("1", "2");
	 * String[] emptyArray = ArrayUtils.&lt;String&gt;toArray();
	 * </pre>
	 * <p>
	 * <p>The method is typically used in scenarios, where the caller itself uses generic types
	 * that have to be combined into an array.</p>
	 * <p>
	 * <p>Note, this method makes only sense to provide arguments of the same type so that the
	 * compiler can deduce the type of the array itself. While it is possible to select the
	 * type explicitly like in
	 * <code>Number[] array = ArrayUtils.&lt;Number&gt;toArray(Integer.valueOf(42), Double.valueOf(Math.PI))</code>,
	 * there is no real advantage when compared to
	 * <code>new Number[] {Integer.valueOf(42), Double.valueOf(Math.PI)}</code>.</p>
	 *
	 * @param <T>   the array's element type
	 * @param items the varargs array items, null allowed
	 * @return the array, not null unless a null array is passed in
	 * @since 3.0
	 */
	public static <T> T[] toArray(final T... items) {
		return items;
	}

	// Clone
	//-----------------------------------------------------------------------

	/**
	 * <p>Shallow clones an array returning a typecast result and handling
	 * {@code null}.</p>
	 * <p>
	 * <p>The objects in the array are not cloned, thus there is no special
	 * handling for multi-dimensional arrays.</p>
	 * <p>
	 * <p>This method returns {@code null} for a {@code null} input array.</p>
	 *
	 * @param <T>   the component type of the array
	 * @param array the array to shallow clone, may be {@code null}
	 * @return the cloned array, {@code null} if {@code null} input
	 */
	public static <T> T[] clone(final T[] array) {
		if (array == null) {
			return null;
		}
		return array.clone();
	}

	/**
	 * <p>Clones an array returning a typecast result and handling
	 * {@code null}.</p>
	 * <p>
	 * <p>This method returns {@code null} for a {@code null} input array.</p>
	 *
	 * @param array the array to clone, may be {@code null}
	 * @return the cloned array, {@code null} if {@code null} input
	 */
	public static long[] clone(final long[] array) {
		if (array == null) {
			return null;
		}
		return array.clone();
	}

	/**
	 * <p>Clones an array returning a typecast result and handling
	 * {@code null}.</p>
	 * <p>
	 * <p>This method returns {@code null} for a {@code null} input array.</p>
	 *
	 * @param array the array to clone, may be {@code null}
	 * @return the cloned array, {@code null} if {@code null} input
	 */
	public static int[] clone(final int[] array) {
		if (array == null) {
			return null;
		}
		return array.clone();
	}

	/**
	 * <p>Clones an array returning a typecast result and handling
	 * {@code null}.</p>
	 * <p>
	 * <p>This method returns {@code null} for a {@code null} input array.</p>
	 *
	 * @param array the array to clone, may be {@code null}
	 * @return the cloned array, {@code null} if {@code null} input
	 */
	public static short[] clone(final short[] array) {
		if (array == null) {
			return null;
		}
		return array.clone();
	}

	/**
	 * <p>Clones an array returning a typecast result and handling
	 * {@code null}.</p>
	 * <p>
	 * <p>This method returns {@code null} for a {@code null} input array.</p>
	 *
	 * @param array the array to clone, may be {@code null}
	 * @return the cloned array, {@code null} if {@code null} input
	 */
	public static char[] clone(final char[] array) {
		if (array == null) {
			return null;
		}
		return array.clone();
	}

	/**
	 * <p>Clones an array returning a typecast result and handling
	 * {@code null}.</p>
	 * <p>
	 * <p>This method returns {@code null} for a {@code null} input array.</p>
	 *
	 * @param array the array to clone, may be {@code null}
	 * @return the cloned array, {@code null} if {@code null} input
	 */
	public static byte[] clone(final byte[] array) {
		if (array == null) {
			return null;
		}
		return array.clone();
	}

	/**
	 * <p>Clones an array returning a typecast result and handling
	 * {@code null}.</p>
	 * <p>
	 * <p>This method returns {@code null} for a {@code null} input array.</p>
	 *
	 * @param array the array to clone, may be {@code null}
	 * @return the cloned array, {@code null} if {@code null} input
	 */
	public static double[] clone(final double[] array) {
		if (array == null) {
			return null;
		}
		return array.clone();
	}

	/**
	 * <p>Clones an array returning a typecast result and handling
	 * {@code null}.</p>
	 * <p>
	 * <p>This method returns {@code null} for a {@code null} input array.</p>
	 *
	 * @param array the array to clone, may be {@code null}
	 * @return the cloned array, {@code null} if {@code null} input
	 */
	public static float[] clone(final float[] array) {
		if (array == null) {
			return null;
		}
		return array.clone();
	}

	/**
	 * <p>Clones an array returning a typecast result and handling
	 * {@code null}.</p>
	 * <p>
	 * <p>This method returns {@code null} for a {@code null} input array.</p>
	 *
	 * @param array the array to clone, may be {@code null}
	 * @return the cloned array, {@code null} if {@code null} input
	 */
	public static boolean[] clone(final boolean[] array) {
		if (array == null) {
			return null;
		}
		return array.clone();
	}

	// Is same length
	//-----------------------------------------------------------------------

	/**
	 * <p>Checks whether two arrays are the same length, treating
	 * {@code null} arrays as length {@code 0}.
	 * <p>
	 * <p>Any multi-dimensional aspects of the arrays are ignored.</p>
	 *
	 * @param array1 the first array, may be {@code null}
	 * @param array2 the second array, may be {@code null}
	 * @return {@code true} if length of arrays matches, treating
	 * {@code null} as an empty array
	 */
	public static boolean isSameLength(final Object[] array1, final Object[] array2) {
		if ((array1 == null && array2 != null && array2.length > 0) ||
				(array2 == null && array1 != null && array1.length > 0) ||
				(array1 != null && array2 != null && array1.length != array2.length)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>Checks whether two arrays are the same length, treating
	 * {@code null} arrays as length {@code 0}.</p>
	 *
	 * @param array1 the first array, may be {@code null}
	 * @param array2 the second array, may be {@code null}
	 * @return {@code true} if length of arrays matches, treating
	 * {@code null} as an empty array
	 */
	public static boolean isSameLength(final long[] array1, final long[] array2) {
		if ((array1 == null && array2 != null && array2.length > 0) ||
				(array2 == null && array1 != null && array1.length > 0) ||
				(array1 != null && array2 != null && array1.length != array2.length)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>Checks whether two arrays are the same length, treating
	 * {@code null} arrays as length {@code 0}.</p>
	 *
	 * @param array1 the first array, may be {@code null}
	 * @param array2 the second array, may be {@code null}
	 * @return {@code true} if length of arrays matches, treating
	 * {@code null} as an empty array
	 */
	public static boolean isSameLength(final int[] array1, final int[] array2) {
		if ((array1 == null && array2 != null && array2.length > 0) ||
				(array2 == null && array1 != null && array1.length > 0) ||
				(array1 != null && array2 != null && array1.length != array2.length)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>Checks whether two arrays are the same length, treating
	 * {@code null} arrays as length {@code 0}.</p>
	 *
	 * @param array1 the first array, may be {@code null}
	 * @param array2 the second array, may be {@code null}
	 * @return {@code true} if length of arrays matches, treating
	 * {@code null} as an empty array
	 */
	public static boolean isSameLength(final short[] array1, final short[] array2) {
		if ((array1 == null && array2 != null && array2.length > 0) ||
				(array2 == null && array1 != null && array1.length > 0) ||
				(array1 != null && array2 != null && array1.length != array2.length)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>Checks whether two arrays are the same length, treating
	 * {@code null} arrays as length {@code 0}.</p>
	 *
	 * @param array1 the first array, may be {@code null}
	 * @param array2 the second array, may be {@code null}
	 * @return {@code true} if length of arrays matches, treating
	 * {@code null} as an empty array
	 */
	public static boolean isSameLength(final char[] array1, final char[] array2) {
		if ((array1 == null && array2 != null && array2.length > 0) ||
				(array2 == null && array1 != null && array1.length > 0) ||
				(array1 != null && array2 != null && array1.length != array2.length)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>Checks whether two arrays are the same length, treating
	 * {@code null} arrays as length {@code 0}.</p>
	 *
	 * @param array1 the first array, may be {@code null}
	 * @param array2 the second array, may be {@code null}
	 * @return {@code true} if length of arrays matches, treating
	 * {@code null} as an empty array
	 */
	public static boolean isSameLength(final byte[] array1, final byte[] array2) {
		if ((array1 == null && array2 != null && array2.length > 0) ||
				(array2 == null && array1 != null && array1.length > 0) ||
				(array1 != null && array2 != null && array1.length != array2.length)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>Checks whether two arrays are the same length, treating
	 * {@code null} arrays as length {@code 0}.</p>
	 *
	 * @param array1 the first array, may be {@code null}
	 * @param array2 the second array, may be {@code null}
	 * @return {@code true} if length of arrays matches, treating
	 * {@code null} as an empty array
	 */
	public static boolean isSameLength(final double[] array1, final double[] array2) {
		if ((array1 == null && array2 != null && array2.length > 0) ||
				(array2 == null && array1 != null && array1.length > 0) ||
				(array1 != null && array2 != null && array1.length != array2.length)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>Checks whether two arrays are the same length, treating
	 * {@code null} arrays as length {@code 0}.</p>
	 *
	 * @param array1 the first array, may be {@code null}
	 * @param array2 the second array, may be {@code null}
	 * @return {@code true} if length of arrays matches, treating
	 * {@code null} as an empty array
	 */
	public static boolean isSameLength(final float[] array1, final float[] array2) {
		if ((array1 == null && array2 != null && array2.length > 0) ||
				(array2 == null && array1 != null && array1.length > 0) ||
				(array1 != null && array2 != null && array1.length != array2.length)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>Checks whether two arrays are the same length, treating
	 * {@code null} arrays as length {@code 0}.</p>
	 *
	 * @param array1 the first array, may be {@code null}
	 * @param array2 the second array, may be {@code null}
	 * @return {@code true} if length of arrays matches, treating
	 * {@code null} as an empty array
	 */
	public static boolean isSameLength(final boolean[] array1, final boolean[] array2) {
		if ((array1 == null && array2 != null && array2.length > 0) ||
				(array2 == null && array1 != null && array1.length > 0) ||
				(array1 != null && array2 != null && array1.length != array2.length)) {
			return false;
		}
		return true;
	}

	// ----------------------------------------------------------------------

	/**
	 * <p>Checks if an array of Objects is empty or {@code null}.</p>
	 *
	 * @param array the array to test
	 * @return {@code true} if the array is empty or {@code null}
	 * @since 2.1
	 */
	public static boolean isEmpty(final Object[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * <p>Checks if an array of primitive longs is empty or {@code null}.</p>
	 *
	 * @param array the array to test
	 * @return {@code true} if the array is empty or {@code null}
	 * @since 2.1
	 */
	public static boolean isEmpty(final long[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * <p>Checks if an array of primitive ints is empty or {@code null}.</p>
	 *
	 * @param array the array to test
	 * @return {@code true} if the array is empty or {@code null}
	 * @since 2.1
	 */
	public static boolean isEmpty(final int[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * <p>Checks if an array of primitive shorts is empty or {@code null}.</p>
	 *
	 * @param array the array to test
	 * @return {@code true} if the array is empty or {@code null}
	 * @since 2.1
	 */
	public static boolean isEmpty(final short[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * <p>Checks if an array of primitive chars is empty or {@code null}.</p>
	 *
	 * @param array the array to test
	 * @return {@code true} if the array is empty or {@code null}
	 * @since 2.1
	 */
	public static boolean isEmpty(final char[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * <p>Checks if an array of primitive bytes is empty or {@code null}.</p>
	 *
	 * @param array the array to test
	 * @return {@code true} if the array is empty or {@code null}
	 * @since 2.1
	 */
	public static boolean isEmpty(final byte[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * <p>Checks if an array of primitive doubles is empty or {@code null}.</p>
	 *
	 * @param array the array to test
	 * @return {@code true} if the array is empty or {@code null}
	 * @since 2.1
	 */
	public static boolean isEmpty(final double[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * <p>Checks if an array of primitive floats is empty or {@code null}.</p>
	 *
	 * @param array the array to test
	 * @return {@code true} if the array is empty or {@code null}
	 * @since 2.1
	 */
	public static boolean isEmpty(final float[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * <p>Checks if an array of primitive booleans is empty or {@code null}.</p>
	 *
	 * @param array the array to test
	 * @return {@code true} if the array is empty or {@code null}
	 * @since 2.1
	 */
	public static boolean isEmpty(final boolean[] array) {
		return array == null || array.length == 0;
	}

	// ----------------------------------------------------------------------

	/**
	 * <p>Checks if an array of Objects is not empty or not {@code null}.</p>
	 *
	 * @param <T>   the component type of the array
	 * @param array the array to test
	 * @return {@code true} if the array is not empty or not {@code null}
	 * @since 2.5
	 */
	public static <T> boolean isNotEmpty(final T[] array) {
		return (array != null && array.length != 0);
	}

	/**
	 * <p>Checks if an array of primitive longs is not empty or not {@code null}.</p>
	 *
	 * @param array the array to test
	 * @return {@code true} if the array is not empty or not {@code null}
	 * @since 2.5
	 */
	public static boolean isNotEmpty(final long[] array) {
		return (array != null && array.length != 0);
	}

	/**
	 * <p>Checks if an array of primitive ints is not empty or not {@code null}.</p>
	 *
	 * @param array the array to test
	 * @return {@code true} if the array is not empty or not {@code null}
	 * @since 2.5
	 */
	public static boolean isNotEmpty(final int[] array) {
		return (array != null && array.length != 0);
	}

	/**
	 * <p>Checks if an array of primitive shorts is not empty or not {@code null}.</p>
	 *
	 * @param array the array to test
	 * @return {@code true} if the array is not empty or not {@code null}
	 * @since 2.5
	 */
	public static boolean isNotEmpty(final short[] array) {
		return (array != null && array.length != 0);
	}

	/**
	 * <p>Checks if an array of primitive chars is not empty or not {@code null}.</p>
	 *
	 * @param array the array to test
	 * @return {@code true} if the array is not empty or not {@code null}
	 * @since 2.5
	 */
	public static boolean isNotEmpty(final char[] array) {
		return (array != null && array.length != 0);
	}

	/**
	 * <p>Checks if an array of primitive bytes is not empty or not {@code null}.</p>
	 *
	 * @param array the array to test
	 * @return {@code true} if the array is not empty or not {@code null}
	 * @since 2.5
	 */
	public static boolean isNotEmpty(final byte[] array) {
		return (array != null && array.length != 0);
	}

	/**
	 * <p>Checks if an array of primitive doubles is not empty or not {@code null}.</p>
	 *
	 * @param array the array to test
	 * @return {@code true} if the array is not empty or not {@code null}
	 * @since 2.5
	 */
	public static boolean isNotEmpty(final double[] array) {
		return (array != null && array.length != 0);
	}

	/**
	 * <p>Checks if an array of primitive floats is not empty or not {@code null}.</p>
	 *
	 * @param array the array to test
	 * @return {@code true} if the array is not empty or not {@code null}
	 * @since 2.5
	 */
	public static boolean isNotEmpty(final float[] array) {
		return (array != null && array.length != 0);
	}

	/**
	 * <p>Checks if an array of primitive booleans is not empty or not {@code null}.</p>
	 *
	 * @param array the array to test
	 * @return {@code true} if the array is not empty or not {@code null}
	 * @since 2.5
	 */
	public static boolean isNotEmpty(final boolean[] array) {
		return (array != null && array.length != 0);
	}

	// Boolean array converters
	// ----------------------------------------------------------------------

	/**
	 * <p>Converts an array of object Booleans to primitives.</p>
	 * <p>
	 * <p>This method returns {@code null} for a {@code null} input array.</p>
	 *
	 * @param array a {@code Boolean} array, may be {@code null}
	 * @return a {@code boolean} array, {@code null} if null array input
	 * @throws NullPointerException if array content is {@code null}
	 */
	public static boolean[] toPrimitive(final Boolean[] array) {
		if (array == null) {
			return null;
		} else if (array.length == 0) {
			return EMPTY_BOOLEAN_ARRAY;
		}
		final boolean[] result = new boolean[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i].booleanValue();
		}
		return result;
	}
}