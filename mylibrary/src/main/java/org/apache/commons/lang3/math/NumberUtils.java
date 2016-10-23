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
package org.apache.commons.lang3.math;

/**
 * <p>Provides extra functionality for Java Number classes.</p>
 *
 * @version $Id: NumberUtils.java 1663129 2015-03-01 16:48:22Z britter $
 * @since 2.0
 */
public class NumberUtils {

	/**
	 * Reusable Long constant for zero.
	 */
	public static final Long LONG_ZERO = Long.valueOf(0L);
	/**
	 * Reusable Long constant for one.
	 */
	public static final Long LONG_ONE = Long.valueOf(1L);
	/**
	 * Reusable Long constant for minus one.
	 */
	public static final Long LONG_MINUS_ONE = Long.valueOf(-1L);
	/**
	 * Reusable Integer constant for zero.
	 */
	public static final Integer INTEGER_ZERO = Integer.valueOf(0);
	/**
	 * Reusable Integer constant for one.
	 */
	public static final Integer INTEGER_ONE = Integer.valueOf(1);
	/**
	 * Reusable Integer constant for minus one.
	 */
	public static final Integer INTEGER_MINUS_ONE = Integer.valueOf(-1);
	/**
	 * Reusable Short constant for zero.
	 */
	public static final Short SHORT_ZERO = Short.valueOf((short) 0);
	/**
	 * Reusable Short constant for one.
	 */
	public static final Short SHORT_ONE = Short.valueOf((short) 1);
	/**
	 * Reusable Short constant for minus one.
	 */
	public static final Short SHORT_MINUS_ONE = Short.valueOf((short) -1);
	/**
	 * Reusable Byte constant for zero.
	 */
	public static final Byte BYTE_ZERO = Byte.valueOf((byte) 0);
	/**
	 * Reusable Byte constant for one.
	 */
	public static final Byte BYTE_ONE = Byte.valueOf((byte) 1);
	/**
	 * Reusable Byte constant for minus one.
	 */
	public static final Byte BYTE_MINUS_ONE = Byte.valueOf((byte) -1);
	/**
	 * Reusable Double constant for zero.
	 */
	public static final Double DOUBLE_ZERO = Double.valueOf(0.0d);
	/**
	 * Reusable Double constant for one.
	 */
	public static final Double DOUBLE_ONE = Double.valueOf(1.0d);
	/**
	 * Reusable Double constant for minus one.
	 */
	public static final Double DOUBLE_MINUS_ONE = Double.valueOf(-1.0d);
	/**
	 * Reusable Float constant for zero.
	 */
	public static final Float FLOAT_ZERO = Float.valueOf(0.0f);
	/**
	 * Reusable Float constant for one.
	 */
	public static final Float FLOAT_ONE = Float.valueOf(1.0f);
	/**
	 * Reusable Float constant for minus one.
	 */
	public static final Float FLOAT_MINUS_ONE = Float.valueOf(-1.0f);

	/**
	 * <p><code>NumberUtils</code> instances should NOT be constructed in standard programming.
	 * Instead, the class should be used as <code>NumberUtils.toInt("6");</code>.</p>
	 * <p>
	 * <p>This constructor is public to permit tools that require a JavaBean instance
	 * to operate.</p>
	 */
	public NumberUtils() {
		super();
	}

}
