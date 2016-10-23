/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.lang3;

import java.util.Collection;
import java.util.Map;

/**
 * <p>This class assists in validating arguments. The validation methods are
 * based along the following principles:
 * <ul>
 * <li>An invalid {@code null} argument causes a {@link NullPointerException}.</li>
 * <li>A non-{@code null} argument causes an {@link IllegalArgumentException}.</li>
 * <li>An invalid index into an array/collection/map/string causes an {@link IndexOutOfBoundsException}.</li>
 * </ul>
 * <p>
 * <p>All exceptions messages are
 * <a href="http://docs.oracle.com/javase/1.5.0/docs/api/java/util/Formatter.html#syntax">format strings</a>
 * as defined by the Java platform. For example:</p>
 * <p>
 * <pre>
 * Validate.isTrue(i &gt; 0, "The value must be greater than zero: %d", i);
 * Validate.notNull(surname, "The surname must not be %s", null);
 * </pre>
 * <p>
 * <p>#ThreadSafe#</p>
 *
 * @version $Id: Validate.java 1606051 2014-06-27 12:22:17Z ggregory $
 * @see java.lang.String#format(String, Object...)
 * @since 2.0
 */
public class Validate {

	private static final String DEFAULT_EXCLUSIVE_BETWEEN_EX_MESSAGE =
			"The value %s is not in the specified exclusive range of %s to %s";
	private static final String DEFAULT_INCLUSIVE_BETWEEN_EX_MESSAGE =
			"The value %s is not in the specified inclusive range of %s to %s";
	private static final String DEFAULT_MATCHES_PATTERN_EX = "The string %s does not match the pattern %s";
	private static final String DEFAULT_IS_NULL_EX_MESSAGE = "The validated object is null";
	private static final String DEFAULT_IS_TRUE_EX_MESSAGE = "The validated expression is false";
	private static final String DEFAULT_NO_NULL_ELEMENTS_ARRAY_EX_MESSAGE =
			"The validated array contains null element at index: %d";
	private static final String DEFAULT_NO_NULL_ELEMENTS_COLLECTION_EX_MESSAGE =
			"The validated collection contains null element at index: %d";
	private static final String DEFAULT_NOT_BLANK_EX_MESSAGE = "The validated character sequence is blank";
	private static final String DEFAULT_NOT_EMPTY_ARRAY_EX_MESSAGE = "The validated array is empty";
	private static final String DEFAULT_NOT_EMPTY_CHAR_SEQUENCE_EX_MESSAGE =
			"The validated character sequence is empty";
	private static final String DEFAULT_NOT_EMPTY_COLLECTION_EX_MESSAGE = "The validated collection is empty";
	private static final String DEFAULT_NOT_EMPTY_MAP_EX_MESSAGE = "The validated map is empty";
	private static final String DEFAULT_VALID_INDEX_ARRAY_EX_MESSAGE = "The validated array index is invalid: %d";
	private static final String DEFAULT_VALID_INDEX_CHAR_SEQUENCE_EX_MESSAGE =
			"The validated character sequence index is invalid: %d";
	private static final String DEFAULT_VALID_INDEX_COLLECTION_EX_MESSAGE =
			"The validated collection index is invalid: %d";
	private static final String DEFAULT_VALID_STATE_EX_MESSAGE = "The validated state is false";
	private static final String DEFAULT_IS_ASSIGNABLE_EX_MESSAGE = "Cannot assign a %s to a %s";
	private static final String DEFAULT_IS_INSTANCE_OF_EX_MESSAGE = "Expected type: %s, actual: %s";

	/**
	 * Constructor. This class should not normally be instantiated.
	 */
	public Validate() {
		super();
	}

	// isTrue
	//---------------------------------------------------------------------------------

	/**
	 * <p>Validate that the argument condition is {@code true}; otherwise
	 * throwing an exception with the specified message. This method is useful when
	 * validating according to an arbitrary boolean expression, such as validating a
	 * primitive number or using your own custom validation expression.</p>
	 * <p>
	 * <pre>Validate.isTrue(i &gt; 0.0, "The value must be greater than zero: &#37;d", i);</pre>
	 *
	 * <p>For performance reasons, the long value is passed as a separate parameter and
	 * appended to the exception message only in the case of an error.</p>
	 *
	 * @param expression the boolean expression to check
	 * @param message    the {@link String#format(String, Object...)} exception message if invalid, not null
	 * @param value      the value to append to the message when invalid
	 * @throws IllegalArgumentException if expression is {@code false}
	 * @see #isTrue(boolean)
	 * @see #isTrue(boolean, String, double)
	 * @see #isTrue(boolean, String, Object...)
	 */
	public static void isTrue(final boolean expression, final String message, final long value) {
		if (expression == false) {
			throw new IllegalArgumentException(String.format(message, Long.valueOf(value)));
		}
	}

	/**
	 * <p>Validate that the argument condition is {@code true}; otherwise
	 * throwing an exception with the specified message. This method is useful when
	 * validating according to an arbitrary boolean expression, such as validating a
	 * primitive number or using your own custom validation expression.</p>
	 * <p>
	 * <pre>Validate.isTrue(d &gt; 0.0, "The value must be greater than zero: &#37;s", d);</pre>
	 *
	 * <p>For performance reasons, the double value is passed as a separate parameter and
	 * appended to the exception message only in the case of an error.</p>
	 *
	 * @param expression the boolean expression to check
	 * @param message    the {@link String#format(String, Object...)} exception message if invalid, not null
	 * @param value      the value to append to the message when invalid
	 * @throws IllegalArgumentException if expression is {@code false}
	 * @see #isTrue(boolean)
	 * @see #isTrue(boolean, String, long)
	 * @see #isTrue(boolean, String, Object...)
	 */
	public static void isTrue(final boolean expression, final String message, final double value) {
		if (expression == false) {
			throw new IllegalArgumentException(String.format(message, Double.valueOf(value)));
		}
	}

	/**
	 * <p>Validate that the argument condition is {@code true}; otherwise
	 * throwing an exception with the specified message. This method is useful when
	 * validating according to an arbitrary boolean expression, such as validating a
	 * primitive number or using your own custom validation expression.</p>
	 * <p>
	 * <pre>
	 * Validate.isTrue(i &gt;= min &amp;&amp; i &lt;= max, "The value must be between &#37;d and &#37;d", min, max);
	 * Validate.isTrue(myObject.isOk(), "The object is not okay");</pre>
	 *
	 * @param expression the boolean expression to check
	 * @param message    the {@link String#format(String, Object...)} exception message if invalid, not null
	 * @param values     the optional values for the formatted exception message, null array not recommended
	 * @throws IllegalArgumentException if expression is {@code false}
	 * @see #isTrue(boolean)
	 * @see #isTrue(boolean, String, long)
	 * @see #isTrue(boolean, String, double)
	 */
	public static void isTrue(final boolean expression, final String message, final Object... values) {
		if (expression == false) {
			throw new IllegalArgumentException(String.format(message, values));
		}
	}

	/**
	 * <p>Validate that the argument condition is {@code true}; otherwise
	 * throwing an exception. This method is useful when validating according
	 * to an arbitrary boolean expression, such as validating a
	 * primitive number or using your own custom validation expression.</p>
	 * <p>
	 * <pre>
	 * Validate.isTrue(i &gt; 0);
	 * Validate.isTrue(myObject.isOk());</pre>
	 * <p>
	 * <p>The message of the exception is &quot;The validated expression is
	 * false&quot;.</p>
	 *
	 * @param expression the boolean expression to check
	 * @throws IllegalArgumentException if expression is {@code false}
	 * @see #isTrue(boolean, String, long)
	 * @see #isTrue(boolean, String, double)
	 * @see #isTrue(boolean, String, Object...)
	 */
	public static void isTrue(final boolean expression) {
		if (expression == false) {
			throw new IllegalArgumentException(DEFAULT_IS_TRUE_EX_MESSAGE);
		}
	}

	// notNull
	//---------------------------------------------------------------------------------

	/**
	 * <p>Validate that the specified argument is not {@code null};
	 * otherwise throwing an exception.
	 * <p>
	 * <pre>Validate.notNull(myObject, "The object must not be null");</pre>
	 *
	 * <p>The message of the exception is &quot;The validated object is
	 * null&quot;.</p>
	 *
	 * @param <T>    the object type
	 * @param object the object to check
	 * @return the validated object (never {@code null} for method chaining)
	 * @throws NullPointerException if the object is {@code null}
	 * @see #notNull(Object, String, Object...)
	 */
	public static <T> T notNull(final T object) {
		return notNull(object, DEFAULT_IS_NULL_EX_MESSAGE);
	}

	/**
	 * <p>Validate that the specified argument is not {@code null};
	 * otherwise throwing an exception with the specified message.
	 * <p>
	 * <pre>Validate.notNull(myObject, "The object must not be null");</pre>
	 *
	 * @param <T>     the object type
	 * @param object  the object to check
	 * @param message the {@link String#format(String, Object...)} exception message if invalid, not null
	 * @param values  the optional values for the formatted exception message
	 * @return the validated object (never {@code null} for method chaining)
	 * @throws NullPointerException if the object is {@code null}
	 * @see #notNull(Object)
	 */
	public static <T> T notNull(final T object, final String message, final Object... values) {
		if (object == null) {
			throw new NullPointerException(String.format(message, values));
		}
		return object;
	}

	// notEmpty array
	//---------------------------------------------------------------------------------

	/**
	 * <p>Validate that the specified argument array is neither {@code null}
	 * nor a length of zero (no elements); otherwise throwing an exception
	 * with the specified message.
	 * <p>
	 * <pre>Validate.notEmpty(myArray, "The array must not be empty");</pre>
	 *
	 * @param <T>     the array type
	 * @param array   the array to check, validated not null by this method
	 * @param message the {@link String#format(String, Object...)} exception message if invalid, not null
	 * @param values  the optional values for the formatted exception message, null array not recommended
	 * @return the validated array (never {@code null} method for chaining)
	 * @throws NullPointerException     if the array is {@code null}
	 * @throws IllegalArgumentException if the array is empty
	 * @see #notEmpty(Object[])
	 */
	public static <T> T[] notEmpty(final T[] array, final String message, final Object... values) {
		if (array == null) {
			throw new NullPointerException(String.format(message, values));
		}
		if (array.length == 0) {
			throw new IllegalArgumentException(String.format(message, values));
		}
		return array;
	}

	/**
	 * <p>Validate that the specified argument array is neither {@code null}
	 * nor a length of zero (no elements); otherwise throwing an exception.
	 * <p>
	 * <pre>Validate.notEmpty(myArray);</pre>
	 *
	 * <p>The message in the exception is &quot;The validated array is
	 * empty&quot;.
	 *
	 * @param <T>   the array type
	 * @param array the array to check, validated not null by this method
	 * @return the validated array (never {@code null} method for chaining)
	 * @throws NullPointerException     if the array is {@code null}
	 * @throws IllegalArgumentException if the array is empty
	 * @see #notEmpty(Object[], String, Object...)
	 */
	public static <T> T[] notEmpty(final T[] array) {
		return notEmpty(array, DEFAULT_NOT_EMPTY_ARRAY_EX_MESSAGE);
	}

	// notEmpty collection
	//---------------------------------------------------------------------------------

	/**
	 * <p>Validate that the specified argument collection is neither {@code null}
	 * nor a size of zero (no elements); otherwise throwing an exception
	 * with the specified message.
	 * <p>
	 * <pre>Validate.notEmpty(myCollection, "The collection must not be empty");</pre>
	 *
	 * @param <T>        the collection type
	 * @param collection the collection to check, validated not null by this method
	 * @param message    the {@link String#format(String, Object...)} exception message if invalid, not null
	 * @param values     the optional values for the formatted exception message, null array not recommended
	 * @return the validated collection (never {@code null} method for chaining)
	 * @throws NullPointerException     if the collection is {@code null}
	 * @throws IllegalArgumentException if the collection is empty
	 * @see #notEmpty(Object[])
	 */
	public static <T extends Collection<?>> T notEmpty(final T collection, final String message, final Object... values) {
		if (collection == null) {
			throw new NullPointerException(String.format(message, values));
		}
		if (collection.isEmpty()) {
			throw new IllegalArgumentException(String.format(message, values));
		}
		return collection;
	}

	/**
	 * <p>Validate that the specified argument collection is neither {@code null}
	 * nor a size of zero (no elements); otherwise throwing an exception.
	 * <p>
	 * <pre>Validate.notEmpty(myCollection);</pre>
	 *
	 * <p>The message in the exception is &quot;The validated collection is
	 * empty&quot;.</p>
	 *
	 * @param <T>        the collection type
	 * @param collection the collection to check, validated not null by this method
	 * @return the validated collection (never {@code null} method for chaining)
	 * @throws NullPointerException     if the collection is {@code null}
	 * @throws IllegalArgumentException if the collection is empty
	 * @see #notEmpty(Collection, String, Object...)
	 */
	public static <T extends Collection<?>> T notEmpty(final T collection) {
		return notEmpty(collection, DEFAULT_NOT_EMPTY_COLLECTION_EX_MESSAGE);
	}

	// notEmpty map
	//---------------------------------------------------------------------------------

	/**
	 * <p>Validate that the specified argument map is neither {@code null}
	 * nor a size of zero (no elements); otherwise throwing an exception
	 * with the specified message.
	 * <p>
	 * <pre>Validate.notEmpty(myMap, "The map must not be empty");</pre>
	 *
	 * @param <T>     the map type
	 * @param map     the map to check, validated not null by this method
	 * @param message the {@link String#format(String, Object...)} exception message if invalid, not null
	 * @param values  the optional values for the formatted exception message, null array not recommended
	 * @return the validated map (never {@code null} method for chaining)
	 * @throws NullPointerException     if the map is {@code null}
	 * @throws IllegalArgumentException if the map is empty
	 * @see #notEmpty(Object[])
	 */
	public static <T extends Map<?, ?>> T notEmpty(final T map, final String message, final Object... values) {
		if (map == null) {
			throw new NullPointerException(String.format(message, values));
		}
		if (map.isEmpty()) {
			throw new IllegalArgumentException(String.format(message, values));
		}
		return map;
	}

	/**
	 * <p>Validate that the specified argument map is neither {@code null}
	 * nor a size of zero (no elements); otherwise throwing an exception.
	 * <p>
	 * <pre>Validate.notEmpty(myMap);</pre>
	 *
	 * <p>The message in the exception is &quot;The validated map is
	 * empty&quot;.</p>
	 *
	 * @param <T> the map type
	 * @param map the map to check, validated not null by this method
	 * @return the validated map (never {@code null} method for chaining)
	 * @throws NullPointerException     if the map is {@code null}
	 * @throws IllegalArgumentException if the map is empty
	 * @see #notEmpty(Map, String, Object...)
	 */
	public static <T extends Map<?, ?>> T notEmpty(final T map) {
		return notEmpty(map, DEFAULT_NOT_EMPTY_MAP_EX_MESSAGE);
	}

	// notEmpty string
	//---------------------------------------------------------------------------------

	/**
	 * <p>Validate that the specified argument character sequence is
	 * neither {@code null} nor a length of zero (no characters);
	 * otherwise throwing an exception with the specified message.
	 * <p>
	 * <pre>Validate.notEmpty(myString, "The string must not be empty");</pre>
	 *
	 * @param <T>     the character sequence type
	 * @param chars   the character sequence to check, validated not null by this method
	 * @param message the {@link String#format(String, Object...)} exception message if invalid, not null
	 * @param values  the optional values for the formatted exception message, null array not recommended
	 * @return the validated character sequence (never {@code null} method for chaining)
	 * @throws NullPointerException     if the character sequence is {@code null}
	 * @throws IllegalArgumentException if the character sequence is empty
	 * @see #notEmpty(CharSequence)
	 */
	public static <T extends CharSequence> T notEmpty(final T chars, final String message, final Object... values) {
		if (chars == null) {
			throw new NullPointerException(String.format(message, values));
		}
		if (chars.length() == 0) {
			throw new IllegalArgumentException(String.format(message, values));
		}
		return chars;
	}

	/**
	 * <p>Validate that the specified argument character sequence is
	 * neither {@code null} nor a length of zero (no characters);
	 * otherwise throwing an exception with the specified message.
	 * <p>
	 * <pre>Validate.notEmpty(myString);</pre>
	 *
	 * <p>The message in the exception is &quot;The validated
	 * character sequence is empty&quot;.</p>
	 *
	 * @param <T>   the character sequence type
	 * @param chars the character sequence to check, validated not null by this method
	 * @return the validated character sequence (never {@code null} method for chaining)
	 * @throws NullPointerException     if the character sequence is {@code null}
	 * @throws IllegalArgumentException if the character sequence is empty
	 * @see #notEmpty(CharSequence, String, Object...)
	 */
	public static <T extends CharSequence> T notEmpty(final T chars) {
		return notEmpty(chars, DEFAULT_NOT_EMPTY_CHAR_SEQUENCE_EX_MESSAGE);
	}

	// notBlank string
	//---------------------------------------------------------------------------------

	/**
	 * <p>Validate that the specified argument character sequence is
	 * neither {@code null}, a length of zero (no characters), empty
	 * nor whitespace; otherwise throwing an exception with the specified
	 * message.
	 * <p>
	 * <pre>Validate.notBlank(myString, "The string must not be blank");</pre>
	 *
	 * @param <T>     the character sequence type
	 * @param chars   the character sequence to check, validated not null by this method
	 * @param message the {@link String#format(String, Object...)} exception message if invalid, not null
	 * @param values  the optional values for the formatted exception message, null array not recommended
	 * @return the validated character sequence (never {@code null} method for chaining)
	 * @throws NullPointerException     if the character sequence is {@code null}
	 * @throws IllegalArgumentException if the character sequence is blank
	 * @see #notBlank(CharSequence)
	 * @since 3.0
	 */
	public static <T extends CharSequence> T notBlank(final T chars, final String message, final Object... values) {
		if (chars == null) {
			throw new NullPointerException(String.format(message, values));
		}
		if (StringUtils.isBlank(chars)) {
			throw new IllegalArgumentException(String.format(message, values));
		}
		return chars;
	}

	/**
	 * <p>Validate that the specified argument character sequence is
	 * neither {@code null}, a length of zero (no characters), empty
	 * nor whitespace; otherwise throwing an exception.
	 * <p>
	 * <pre>Validate.notBlank(myString);</pre>
	 *
	 * <p>The message in the exception is &quot;The validated character
	 * sequence is blank&quot;.</p>
	 *
	 * @param <T>   the character sequence type
	 * @param chars the character sequence to check, validated not null by this method
	 * @return the validated character sequence (never {@code null} method for chaining)
	 * @throws NullPointerException     if the character sequence is {@code null}
	 * @throws IllegalArgumentException if the character sequence is blank
	 * @see #notBlank(CharSequence, String, Object...)
	 * @since 3.0
	 */
	public static <T extends CharSequence> T notBlank(final T chars) {
		return notBlank(chars, DEFAULT_NOT_BLANK_EX_MESSAGE);
	}

}
