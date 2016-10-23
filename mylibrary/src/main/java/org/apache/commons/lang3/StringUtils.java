package org.apache.commons.lang3;

public class StringUtils {
	// Performance testing notes (JDK 1.4, Jul03, scolebourne)
	// Whitespace:
	// Character.isWhitespace() is faster than WHITESPACE.indexOf()
	// where WHITESPACE is a string of all whitespace characters
	//
	// Character access:
	// String.charAt(n) versus toCharArray(), then array[n]
	// String.charAt(n) is about 15% worse for a 10K string
	// They are about equal for a length 50 string
	// String.charAt(n) is about 4 times better for a length 3 string
	// String.charAt(n) is best bet overall
	//
	// Append:
	// String.concat about twice as fast as StringBuffer.append
	// (not sure who tested this)

	/**
	 * A String for a space character.
	 *
	 * @since 3.2
	 */
	public static final String SPACE = " ";

	/**
	 * The empty String {@code ""}.
	 *
	 * @since 2.0
	 */
	public static final String EMPTY = "";

	/**
	 * A String for linefeed LF ("\n").
	 *
	 * @see <a href="http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.10.6">JLF: Escape Sequences
	 * for Character and String Literals</a>
	 * @since 3.2
	 */
	public static final String LF = "\n";

	/**
	 * A String for carriage return CR ("\r").
	 *
	 * @see <a href="http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.10.6">JLF: Escape Sequences
	 * for Character and String Literals</a>
	 * @since 3.2
	 */
	public static final String CR = "\r";

	/**
	 * Represents a failed index search.
	 *
	 * @since 2.1
	 */
	public static final int INDEX_NOT_FOUND = -1;

	/**
	 * <p>The maximum size to which the padding constant(s) can expand.</p>
	 */
	private static final int PAD_LIMIT = 8192;

	/**
	 * <p>{@code StringUtils} instances should NOT be constructed in
	 * standard programming. Instead, the class should be used as
	 * {@code StringUtils.trim(" foo ");}.</p>
	 * <p>
	 * <p>This constructor is public to permit tools that require a JavaBean
	 * instance to operate.</p>
	 */
	public StringUtils() {
		super();
	}

	// Empty checks
	//-----------------------------------------------------------------------

	/**
	 * <p>Checks if a CharSequence is empty ("") or null.</p>
	 * <p>
	 * <pre>
	 * StringUtils.isEmpty(null)      = true
	 * StringUtils.isEmpty("")        = true
	 * StringUtils.isEmpty(" ")       = false
	 * StringUtils.isEmpty("bob")     = false
	 * StringUtils.isEmpty("  bob  ") = false
	 * </pre>
	 * <p>
	 * <p>NOTE: This method changed in Lang version 2.0.
	 * It no longer trims the CharSequence.
	 * That functionality is available in isBlank().</p>
	 *
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is empty or null
	 * @since 3.0 Changed signature from isEmpty(String) to isEmpty(CharSequence)
	 */
	public static boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	/**
	 * <p>Checks if a CharSequence is not empty ("") and not null.</p>
	 * <p>
	 * <pre>
	 * StringUtils.isNotEmpty(null)      = false
	 * StringUtils.isNotEmpty("")        = false
	 * StringUtils.isNotEmpty(" ")       = true
	 * StringUtils.isNotEmpty("bob")     = true
	 * StringUtils.isNotEmpty("  bob  ") = true
	 * </pre>
	 *
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is not empty and not null
	 * @since 3.0 Changed signature from isNotEmpty(String) to isNotEmpty(CharSequence)
	 */
	public static boolean isNotEmpty(final CharSequence cs) {
		return !isEmpty(cs);
	}

	/**
	 * <p>Checks if any one of the CharSequences are empty ("") or null.</p>
	 * <p>
	 * <pre>
	 * StringUtils.isAnyEmpty(null)             = true
	 * StringUtils.isAnyEmpty(null, "foo")      = true
	 * StringUtils.isAnyEmpty("", "bar")        = true
	 * StringUtils.isAnyEmpty("bob", "")        = true
	 * StringUtils.isAnyEmpty("  bob  ", null)  = true
	 * StringUtils.isAnyEmpty(" ", "bar")       = false
	 * StringUtils.isAnyEmpty("foo", "bar")     = false
	 * </pre>
	 *
	 * @param css the CharSequences to check, may be null or empty
	 * @return {@code true} if any of the CharSequences are empty or null
	 * @since 3.2
	 */
	public static boolean isAnyEmpty(final CharSequence... css) {
		if (ArrayUtils.isEmpty(css)) {
			return true;
		}
		for (final CharSequence cs : css) {
			if (isEmpty(cs)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>Checks if none of the CharSequences are empty ("") or null.</p>
	 * <p>
	 * <pre>
	 * StringUtils.isNoneEmpty(null)             = false
	 * StringUtils.isNoneEmpty(null, "foo")      = false
	 * StringUtils.isNoneEmpty("", "bar")        = false
	 * StringUtils.isNoneEmpty("bob", "")        = false
	 * StringUtils.isNoneEmpty("  bob  ", null)  = false
	 * StringUtils.isNoneEmpty(" ", "bar")       = true
	 * StringUtils.isNoneEmpty("foo", "bar")     = true
	 * </pre>
	 *
	 * @param css the CharSequences to check, may be null or empty
	 * @return {@code true} if none of the CharSequences are empty or null
	 * @since 3.2
	 */
	public static boolean isNoneEmpty(final CharSequence... css) {
		return !isAnyEmpty(css);
	}

	/**
	 * <p>Checks if a CharSequence is whitespace, empty ("") or null.</p>
	 * <p>
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 *
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is null, empty or whitespace
	 * @since 3.0 Changed signature from isBlank(String) to isBlank(CharSequence)
	 */
	public static boolean isBlank(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>Checks if a CharSequence is not empty (""), not null and not whitespace only.</p>
	 * <p>
	 * <pre>
	 * StringUtils.isNotBlank(null)      = false
	 * StringUtils.isNotBlank("")        = false
	 * StringUtils.isNotBlank(" ")       = false
	 * StringUtils.isNotBlank("bob")     = true
	 * StringUtils.isNotBlank("  bob  ") = true
	 * </pre>
	 *
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is
	 * not empty and not null and not whitespace
	 * @since 3.0 Changed signature from isNotBlank(String) to isNotBlank(CharSequence)
	 */
	public static boolean isNotBlank(final CharSequence cs) {
		return !isBlank(cs);
	}

	/**
	 * <p>Checks if any one of the CharSequences are blank ("") or null and not whitespace only..</p>
	 * <p>
	 * <pre>
	 * StringUtils.isAnyBlank(null)             = true
	 * StringUtils.isAnyBlank(null, "foo")      = true
	 * StringUtils.isAnyBlank(null, null)       = true
	 * StringUtils.isAnyBlank("", "bar")        = true
	 * StringUtils.isAnyBlank("bob", "")        = true
	 * StringUtils.isAnyBlank("  bob  ", null)  = true
	 * StringUtils.isAnyBlank(" ", "bar")       = true
	 * StringUtils.isAnyBlank("foo", "bar")     = false
	 * </pre>
	 *
	 * @param css the CharSequences to check, may be null or empty
	 * @return {@code true} if any of the CharSequences are blank or null or whitespace only
	 * @since 3.2
	 */
	public static boolean isAnyBlank(final CharSequence... css) {
		if (ArrayUtils.isEmpty(css)) {
			return true;
		}
		for (final CharSequence cs : css) {
			if (isBlank(cs)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>Checks if none of the CharSequences are blank ("") or null and whitespace only..</p>
	 * <p>
	 * <pre>
	 * StringUtils.isNoneBlank(null)             = false
	 * StringUtils.isNoneBlank(null, "foo")      = false
	 * StringUtils.isNoneBlank(null, null)       = false
	 * StringUtils.isNoneBlank("", "bar")        = false
	 * StringUtils.isNoneBlank("bob", "")        = false
	 * StringUtils.isNoneBlank("  bob  ", null)  = false
	 * StringUtils.isNoneBlank(" ", "bar")       = false
	 * StringUtils.isNoneBlank("foo", "bar")     = true
	 * </pre>
	 *
	 * @param css the CharSequences to check, may be null or empty
	 * @return {@code true} if none of the CharSequences are blank or null or whitespace only
	 * @since 3.2
	 */
	public static boolean isNoneBlank(final CharSequence... css) {
		return !isAnyBlank(css);
	}

	// Trim
	//-----------------------------------------------------------------------

	/**
	 * <p>Removes control characters (char &lt;= 32) from both
	 * ends of this String, handling {@code null} by returning
	 * {@code null}.</p>
	 * <p>
	 * <p>The String is trimmed using {@link String#trim()}.
	 * Trim removes start and end characters &lt;= 32.
	 * <p>
	 * <p>To trim your choice of characters, use the
	 * <p>
	 * <pre>
	 * StringUtils.trim(null)          = null
	 * StringUtils.trim("")            = ""
	 * StringUtils.trim("     ")       = ""
	 * StringUtils.trim("abc")         = "abc"
	 * StringUtils.trim("    abc    ") = "abc"
	 * </pre>
	 *
	 * @param str the String to be trimmed, may be null
	 * @return the trimmed string, {@code null} if null String input
	 */
	public static String trim(final String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * <p>Removes control characters (char &lt;= 32) from both
	 * ends of this String returning {@code null} if the String is
	 * empty ("") after the trim or if it is {@code null}.
	 * <p>
	 * <p>The String is trimmed using {@link String#trim()}.
	 * Trim removes start and end characters &lt;= 32.
	 * <p>
	 * <pre>
	 * StringUtils.trimToNull(null)          = null
	 * StringUtils.trimToNull("")            = null
	 * StringUtils.trimToNull("     ")       = null
	 * StringUtils.trimToNull("abc")         = "abc"
	 * StringUtils.trimToNull("    abc    ") = "abc"
	 * </pre>
	 *
	 * @param str the String to be trimmed, may be null
	 * @return the trimmed String,
	 * {@code null} if only chars &lt;= 32, empty or null String input
	 * @since 2.0
	 */
	public static String trimToNull(final String str) {
		final String ts = trim(str);
		return isEmpty(ts) ? null : ts;
	}

	/**
	 * <p>Removes control characters (char &lt;= 32) from both
	 * ends of this String returning an empty String ("") if the String
	 * is empty ("") after the trim or if it is {@code null}.
	 * <p>
	 * <p>The String is trimmed using {@link String#trim()}.
	 * Trim removes start and end characters &lt;= 32.
	 * <p>
	 * <pre>
	 * StringUtils.trimToEmpty(null)          = ""
	 * StringUtils.trimToEmpty("")            = ""
	 * StringUtils.trimToEmpty("     ")       = ""
	 * StringUtils.trimToEmpty("abc")         = "abc"
	 * StringUtils.trimToEmpty("    abc    ") = "abc"
	 * </pre>
	 *
	 * @param str the String to be trimmed, may be null
	 * @return the trimmed String, or an empty String if {@code null} input
	 * @since 2.0
	 */
	public static String trimToEmpty(final String str) {
		return str == null ? EMPTY : str.trim();
	}

	// Equals
	//-----------------------------------------------------------------------

	/**
	 * <p>Compares two CharSequences, returning {@code true} if they represent
	 * equal sequences of characters.</p>
	 * <p>
	 * <p>{@code null}s are handled without exceptions. Two {@code null}
	 * references are considered to be equal. The comparison is case sensitive.</p>
	 * <p>
	 * <pre>
	 * StringUtils.equals(null, null)   = true
	 * StringUtils.equals(null, "abc")  = false
	 * StringUtils.equals("abc", null)  = false
	 * StringUtils.equals("abc", "abc") = true
	 * StringUtils.equals("abc", "ABC") = false
	 * </pre>
	 *
	 * @param cs1 the first CharSequence, may be {@code null}
	 * @param cs2 the second CharSequence, may be {@code null}
	 * @return {@code true} if the CharSequences are equal (case-sensitive), or both {@code null}
	 * @see Object#equals(Object)
	 * @since 3.0 Changed signature from equals(String, String) to equals(CharSequence, CharSequence)
	 */
	public static boolean equals(final CharSequence cs1, final CharSequence cs2) {
		if (cs1 == cs2) {
			return true;
		}
		if (cs1 == null || cs2 == null) {
			return false;
		}
		if (cs1 instanceof String && cs2 instanceof String) {
			return cs1.equals(cs2);
		}
		return CharSequenceUtils.regionMatches(cs1, false, 0, cs2, 0, Math.max(cs1.length(), cs2.length()));
	}

	/**
	 * <p>Compares two CharSequences, returning {@code true} if they represent
	 * equal sequences of characters, ignoring case.</p>
	 * <p>
	 * <p>{@code null}s are handled without exceptions. Two {@code null}
	 * references are considered equal. Comparison is case insensitive.</p>
	 * <p>
	 * <pre>
	 * StringUtils.equalsIgnoreCase(null, null)   = true
	 * StringUtils.equalsIgnoreCase(null, "abc")  = false
	 * StringUtils.equalsIgnoreCase("abc", null)  = false
	 * StringUtils.equalsIgnoreCase("abc", "abc") = true
	 * StringUtils.equalsIgnoreCase("abc", "ABC") = true
	 * </pre>
	 *
	 * @param str1 the first CharSequence, may be null
	 * @param str2 the second CharSequence, may be null
	 * @return {@code true} if the CharSequence are equal, case insensitive, or
	 * both {@code null}
	 * @since 3.0 Changed signature from equalsIgnoreCase(String, String) to equalsIgnoreCase(CharSequence, CharSequence)
	 */
	public static boolean equalsIgnoreCase(final CharSequence str1, final CharSequence str2) {
		if (str1 == null || str2 == null) {
			return str1 == str2;
		} else if (str1 == str2) {
			return true;
		} else if (str1.length() != str2.length()) {
			return false;
		} else {
			return CharSequenceUtils.regionMatches(str1, true, 0, str2, 0, str1.length());
		}
	}

	// IndexOf
	//-----------------------------------------------------------------------

	/**
	 * <p>Finds the first index within a CharSequence, handling {@code null}.
	 * This method uses {@link String#indexOf(int, int)} if possible.</p>
	 * <p>
	 * <p>A {@code null} or empty ("") CharSequence will return {@code INDEX_NOT_FOUND (-1)}.</p>
	 * <p>
	 * <pre>
	 * StringUtils.indexOf(null, *)         = -1
	 * StringUtils.indexOf("", *)           = -1
	 * StringUtils.indexOf("aabaabaa", 'a') = 0
	 * StringUtils.indexOf("aabaabaa", 'b') = 2
	 * </pre>
	 *
	 * @param seq        the CharSequence to check, may be null
	 * @param searchChar the character to find
	 * @return the first index of the search character,
	 * -1 if no match or {@code null} string input
	 * @since 3.0 Changed signature from indexOf(String, int) to indexOf(CharSequence, int)
	 */
	public static int indexOf(final CharSequence seq, final int searchChar) {
		if (isEmpty(seq)) {
			return INDEX_NOT_FOUND;
		}
		return CharSequenceUtils.indexOf(seq, searchChar, 0);
	}

	/**
	 * <p>Finds the first index within a CharSequence from a start position,
	 * handling {@code null}.
	 * This method uses {@link String#indexOf(int, int)} if possible.</p>
	 * <p>
	 * <p>A {@code null} or empty ("") CharSequence will return {@code (INDEX_NOT_FOUND) -1}.
	 * A negative start position is treated as zero.
	 * A start position greater than the string length returns {@code -1}.</p>
	 * <p>
	 * <pre>
	 * StringUtils.indexOf(null, *, *)          = -1
	 * StringUtils.indexOf("", *, *)            = -1
	 * StringUtils.indexOf("aabaabaa", 'b', 0)  = 2
	 * StringUtils.indexOf("aabaabaa", 'b', 3)  = 5
	 * StringUtils.indexOf("aabaabaa", 'b', 9)  = -1
	 * StringUtils.indexOf("aabaabaa", 'b', -1) = 2
	 * </pre>
	 *
	 * @param seq        the CharSequence to check, may be null
	 * @param searchChar the character to find
	 * @param startPos   the start position, negative treated as zero
	 * @return the first index of the search character (always &ge; startPos),
	 * -1 if no match or {@code null} string input
	 * @since 3.0 Changed signature from indexOf(String, int, int) to indexOf(CharSequence, int, int)
	 */
	public static int indexOf(final CharSequence seq, final int searchChar, final int startPos) {
		if (isEmpty(seq)) {
			return INDEX_NOT_FOUND;
		}
		return CharSequenceUtils.indexOf(seq, searchChar, startPos);
	}

	/**
	 * <p>Finds the first index within a CharSequence, handling {@code null}.
	 * This method uses {@link String#indexOf(String, int)} if possible.</p>
	 * <p>
	 * <p>A {@code null} CharSequence will return {@code -1}.</p>
	 * <p>
	 * <pre>
	 * StringUtils.indexOf(null, *)          = -1
	 * StringUtils.indexOf(*, null)          = -1
	 * StringUtils.indexOf("", "")           = 0
	 * StringUtils.indexOf("", *)            = -1 (except when * = "")
	 * StringUtils.indexOf("aabaabaa", "a")  = 0
	 * StringUtils.indexOf("aabaabaa", "b")  = 2
	 * StringUtils.indexOf("aabaabaa", "ab") = 1
	 * StringUtils.indexOf("aabaabaa", "")   = 0
	 * </pre>
	 *
	 * @param seq       the CharSequence to check, may be null
	 * @param searchSeq the CharSequence to find, may be null
	 * @return the first index of the search CharSequence,
	 * -1 if no match or {@code null} string input
	 * @since 3.0 Changed signature from indexOf(String, String) to indexOf(CharSequence, CharSequence)
	 */
	public static int indexOf(final CharSequence seq, final CharSequence searchSeq) {
		if (seq == null || searchSeq == null) {
			return INDEX_NOT_FOUND;
		}
		return CharSequenceUtils.indexOf(seq, searchSeq, 0);
	}

	/**
	 * <p>Finds the first index within a CharSequence, handling {@code null}.
	 * This method uses {@link String#indexOf(String, int)} if possible.</p>
	 * <p>
	 * <p>A {@code null} CharSequence will return {@code -1}.
	 * A negative start position is treated as zero.
	 * An empty ("") search CharSequence always matches.
	 * A start position greater than the string length only matches
	 * an empty search CharSequence.</p>
	 * <p>
	 * <pre>
	 * StringUtils.indexOf(null, *, *)          = -1
	 * StringUtils.indexOf(*, null, *)          = -1
	 * StringUtils.indexOf("", "", 0)           = 0
	 * StringUtils.indexOf("", *, 0)            = -1 (except when * = "")
	 * StringUtils.indexOf("aabaabaa", "a", 0)  = 0
	 * StringUtils.indexOf("aabaabaa", "b", 0)  = 2
	 * StringUtils.indexOf("aabaabaa", "ab", 0) = 1
	 * StringUtils.indexOf("aabaabaa", "b", 3)  = 5
	 * StringUtils.indexOf("aabaabaa", "b", 9)  = -1
	 * StringUtils.indexOf("aabaabaa", "b", -1) = 2
	 * StringUtils.indexOf("aabaabaa", "", 2)   = 2
	 * StringUtils.indexOf("abc", "", 9)        = 3
	 * </pre>
	 *
	 * @param seq       the CharSequence to check, may be null
	 * @param searchSeq the CharSequence to find, may be null
	 * @param startPos  the start position, negative treated as zero
	 * @return the first index of the search CharSequence (always &ge; startPos),
	 * -1 if no match or {@code null} string input
	 * @since 3.0 Changed signature from indexOf(String, String, int) to indexOf(CharSequence, CharSequence, int)
	 */
	public static int indexOf(final CharSequence seq, final CharSequence searchSeq, final int startPos) {
		if (seq == null || searchSeq == null) {
			return INDEX_NOT_FOUND;
		}
		return CharSequenceUtils.indexOf(seq, searchSeq, startPos);
	}

	/**
	 * <p>Finds the n-th index within a CharSequence, handling {@code null}.
	 * This method uses {@link String#indexOf(String)} if possible.</p>
	 * <p>
	 * <p>A {@code null} CharSequence will return {@code -1}.</p>
	 * <p>
	 * <pre>
	 * StringUtils.ordinalIndexOf(null, *, *)          = -1
	 * StringUtils.ordinalIndexOf(*, null, *)          = -1
	 * StringUtils.ordinalIndexOf("", "", *)           = 0
	 * StringUtils.ordinalIndexOf("aabaabaa", "a", 1)  = 0
	 * StringUtils.ordinalIndexOf("aabaabaa", "a", 2)  = 1
	 * StringUtils.ordinalIndexOf("aabaabaa", "b", 1)  = 2
	 * StringUtils.ordinalIndexOf("aabaabaa", "b", 2)  = 5
	 * StringUtils.ordinalIndexOf("aabaabaa", "ab", 1) = 1
	 * StringUtils.ordinalIndexOf("aabaabaa", "ab", 2) = 4
	 * StringUtils.ordinalIndexOf("aabaabaa", "", 1)   = 0
	 * StringUtils.ordinalIndexOf("aabaabaa", "", 2)   = 0
	 * </pre>
	 * <p>
	 * <p>Note that 'head(CharSequence str, int n)' may be implemented as: </p>
	 * <p>
	 * <pre>
	 *   str.substring(0, lastOrdinalIndexOf(str, "\n", n))
	 * </pre>
	 *
	 * @param str       the CharSequence to check, may be null
	 * @param searchStr the CharSequence to find, may be null
	 * @param ordinal   the n-th {@code searchStr} to find
	 * @return the n-th index of the search CharSequence,
	 * {@code -1} ({@code INDEX_NOT_FOUND}) if no match or {@code null} string input
	 * @since 3.0 Changed signature from ordinalIndexOf(String, String, int) to ordinalIndexOf(CharSequence, CharSequence, int)
	 */
	public static int ordinalIndexOf(final CharSequence str, final CharSequence searchStr, final int ordinal) {
		return ordinalIndexOf(str, searchStr, ordinal, false);
	}

	/**
	 * <p>Finds the n-th index within a String, handling {@code null}.
	 * This method uses {@link String#indexOf(String)} if possible.</p>
	 * <p>
	 * <p>A {@code null} CharSequence will return {@code -1}.</p>
	 *
	 * @param str       the CharSequence to check, may be null
	 * @param searchStr the CharSequence to find, may be null
	 * @param ordinal   the n-th {@code searchStr} to find
	 * @param lastIndex true if lastOrdinalIndexOf() otherwise false if ordinalIndexOf()
	 * @return the n-th index of the search CharSequence,
	 * {@code -1} ({@code INDEX_NOT_FOUND}) if no match or {@code null} string input
	 */
	// Shared code between ordinalIndexOf(String,String,int) and lastOrdinalIndexOf(String,String,int)
	private static int ordinalIndexOf(final CharSequence str, final CharSequence searchStr, final int ordinal, final boolean lastIndex) {
		if (str == null || searchStr == null || ordinal <= 0) {
			return INDEX_NOT_FOUND;
		}
		if (searchStr.length() == 0) {
			return lastIndex ? str.length() : 0;
		}
		int found = 0;
		int index = lastIndex ? str.length() : INDEX_NOT_FOUND;
		do {
			if (lastIndex) {
				index = CharSequenceUtils.lastIndexOf(str, searchStr, index - searchStr.length());
			} else {
				index = CharSequenceUtils.indexOf(str, searchStr, index + searchStr.length());
			}
			if (index < 0) {
				return index;
			}
			found++;
		} while (found < ordinal);
		return index;
	}

	/**
	 * <p>Case in-sensitive find of the first index within a CharSequence.</p>
	 * <p>
	 * <p>A {@code null} CharSequence will return {@code -1}.
	 * A negative start position is treated as zero.
	 * An empty ("") search CharSequence always matches.
	 * A start position greater than the string length only matches
	 * an empty search CharSequence.</p>
	 * <p>
	 * <pre>
	 * StringUtils.indexOfIgnoreCase(null, *)          = -1
	 * StringUtils.indexOfIgnoreCase(*, null)          = -1
	 * StringUtils.indexOfIgnoreCase("", "")           = 0
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "a")  = 0
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "b")  = 2
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "ab") = 1
	 * </pre>
	 *
	 * @param str       the CharSequence to check, may be null
	 * @param searchStr the CharSequence to find, may be null
	 * @return the first index of the search CharSequence,
	 * -1 if no match or {@code null} string input
	 * @since 3.0 Changed signature from indexOfIgnoreCase(String, String) to indexOfIgnoreCase(CharSequence, CharSequence)
	 */
	public static int indexOfIgnoreCase(final CharSequence str, final CharSequence searchStr) {
		return indexOfIgnoreCase(str, searchStr, 0);
	}

	/**
	 * <p>Case in-sensitive find of the first index within a CharSequence
	 * from the specified position.</p>
	 * <p>
	 * <p>A {@code null} CharSequence will return {@code -1}.
	 * A negative start position is treated as zero.
	 * An empty ("") search CharSequence always matches.
	 * A start position greater than the string length only matches
	 * an empty search CharSequence.</p>
	 * <p>
	 * <pre>
	 * StringUtils.indexOfIgnoreCase(null, *, *)          = -1
	 * StringUtils.indexOfIgnoreCase(*, null, *)          = -1
	 * StringUtils.indexOfIgnoreCase("", "", 0)           = 0
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "A", 0)  = 0
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "B", 0)  = 2
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 0) = 1
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "B", 3)  = 5
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "B", 9)  = -1
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "B", -1) = 2
	 * StringUtils.indexOfIgnoreCase("aabaabaa", "", 2)   = 2
	 * StringUtils.indexOfIgnoreCase("abc", "", 9)        = 3
	 * </pre>
	 *
	 * @param str       the CharSequence to check, may be null
	 * @param searchStr the CharSequence to find, may be null
	 * @param startPos  the start position, negative treated as zero
	 * @return the first index of the search CharSequence (always &ge; startPos),
	 * -1 if no match or {@code null} string input
	 * @since 3.0 Changed signature from indexOfIgnoreCase(String, String, int) to indexOfIgnoreCase(CharSequence, CharSequence, int)
	 */
	public static int indexOfIgnoreCase(final CharSequence str, final CharSequence searchStr, int startPos) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		if (startPos < 0) {
			startPos = 0;
		}
		final int endLimit = str.length() - searchStr.length() + 1;
		if (startPos > endLimit) {
			return INDEX_NOT_FOUND;
		}
		if (searchStr.length() == 0) {
			return startPos;
		}
		for (int i = startPos; i < endLimit; i++) {
			if (CharSequenceUtils.regionMatches(str, true, i, searchStr, 0, searchStr.length())) {
				return i;
			}
		}
		return INDEX_NOT_FOUND;
	}

	// LastIndexOf
	//-----------------------------------------------------------------------

	/**
	 * <p>Finds the last index within a CharSequence, handling {@code null}.
	 * This method uses {@link String#lastIndexOf(int)} if possible.</p>
	 * <p>
	 * <p>A {@code null} or empty ("") CharSequence will return {@code -1}.</p>
	 * <p>
	 * <pre>
	 * StringUtils.lastIndexOf(null, *)         = -1
	 * StringUtils.lastIndexOf("", *)           = -1
	 * StringUtils.lastIndexOf("aabaabaa", 'a') = 7
	 * StringUtils.lastIndexOf("aabaabaa", 'b') = 5
	 * </pre>
	 *
	 * @param seq        the CharSequence to check, may be null
	 * @param searchChar the character to find
	 * @return the last index of the search character,
	 * -1 if no match or {@code null} string input
	 * @since 3.0 Changed signature from lastIndexOf(String, int) to lastIndexOf(CharSequence, int)
	 */
	public static int lastIndexOf(final CharSequence seq, final int searchChar) {
		if (isEmpty(seq)) {
			return INDEX_NOT_FOUND;
		}
		return CharSequenceUtils.lastIndexOf(seq, searchChar, seq.length());
	}

	/**
	 * <p>Finds the last index within a CharSequence from a start position,
	 * handling {@code null}.
	 * This method uses {@link String#lastIndexOf(int, int)} if possible.</p>
	 * <p>
	 * <p>A {@code null} or empty ("") CharSequence will return {@code -1}.
	 * A negative start position returns {@code -1}.
	 * A start position greater than the string length searches the whole string.
	 * The search starts at the startPos and works backwards; matches starting after the start
	 * position are ignored.
	 * </p>
	 * <p>
	 * <pre>
	 * StringUtils.lastIndexOf(null, *, *)          = -1
	 * StringUtils.lastIndexOf("", *,  *)           = -1
	 * StringUtils.lastIndexOf("aabaabaa", 'b', 8)  = 5
	 * StringUtils.lastIndexOf("aabaabaa", 'b', 4)  = 2
	 * StringUtils.lastIndexOf("aabaabaa", 'b', 0)  = -1
	 * StringUtils.lastIndexOf("aabaabaa", 'b', 9)  = 5
	 * StringUtils.lastIndexOf("aabaabaa", 'b', -1) = -1
	 * StringUtils.lastIndexOf("aabaabaa", 'a', 0)  = 0
	 * </pre>
	 *
	 * @param seq        the CharSequence to check, may be null
	 * @param searchChar the character to find
	 * @param startPos   the start position
	 * @return the last index of the search character (always &le; startPos),
	 * -1 if no match or {@code null} string input
	 * @since 3.0 Changed signature from lastIndexOf(String, int, int) to lastIndexOf(CharSequence, int, int)
	 */
	public static int lastIndexOf(final CharSequence seq, final int searchChar, final int startPos) {
		if (isEmpty(seq)) {
			return INDEX_NOT_FOUND;
		}
		return CharSequenceUtils.lastIndexOf(seq, searchChar, startPos);
	}

	/**
	 * <p>Finds the last index within a CharSequence, handling {@code null}.
	 * This method uses {@link String#lastIndexOf(String)} if possible.</p>
	 * <p>
	 * <p>A {@code null} CharSequence will return {@code -1}.</p>
	 * <p>
	 * <pre>
	 * StringUtils.lastIndexOf(null, *)          = -1
	 * StringUtils.lastIndexOf(*, null)          = -1
	 * StringUtils.lastIndexOf("", "")           = 0
	 * StringUtils.lastIndexOf("aabaabaa", "a")  = 7
	 * StringUtils.lastIndexOf("aabaabaa", "b")  = 5
	 * StringUtils.lastIndexOf("aabaabaa", "ab") = 4
	 * StringUtils.lastIndexOf("aabaabaa", "")   = 8
	 * </pre>
	 *
	 * @param seq       the CharSequence to check, may be null
	 * @param searchSeq the CharSequence to find, may be null
	 * @return the last index of the search String,
	 * -1 if no match or {@code null} string input
	 * @since 3.0 Changed signature from lastIndexOf(String, String) to lastIndexOf(CharSequence, CharSequence)
	 */
	public static int lastIndexOf(final CharSequence seq, final CharSequence searchSeq) {
		if (seq == null || searchSeq == null) {
			return INDEX_NOT_FOUND;
		}
		return CharSequenceUtils.lastIndexOf(seq, searchSeq, seq.length());
	}

	/**
	 * <p>Finds the n-th last index within a String, handling {@code null}.
	 * This method uses {@link String#lastIndexOf(String)}.</p>
	 * <p>
	 * <p>A {@code null} String will return {@code -1}.</p>
	 * <p>
	 * <pre>
	 * StringUtils.lastOrdinalIndexOf(null, *, *)          = -1
	 * StringUtils.lastOrdinalIndexOf(*, null, *)          = -1
	 * StringUtils.lastOrdinalIndexOf("", "", *)           = 0
	 * StringUtils.lastOrdinalIndexOf("aabaabaa", "a", 1)  = 7
	 * StringUtils.lastOrdinalIndexOf("aabaabaa", "a", 2)  = 6
	 * StringUtils.lastOrdinalIndexOf("aabaabaa", "b", 1)  = 5
	 * StringUtils.lastOrdinalIndexOf("aabaabaa", "b", 2)  = 2
	 * StringUtils.lastOrdinalIndexOf("aabaabaa", "ab", 1) = 4
	 * StringUtils.lastOrdinalIndexOf("aabaabaa", "ab", 2) = 1
	 * StringUtils.lastOrdinalIndexOf("aabaabaa", "", 1)   = 8
	 * StringUtils.lastOrdinalIndexOf("aabaabaa", "", 2)   = 8
	 * </pre>
	 * <p>
	 * <p>Note that 'tail(CharSequence str, int n)' may be implemented as: </p>
	 * <p>
	 * <pre>
	 *   str.substring(lastOrdinalIndexOf(str, "\n", n) + 1)
	 * </pre>
	 *
	 * @param str       the CharSequence to check, may be null
	 * @param searchStr the CharSequence to find, may be null
	 * @param ordinal   the n-th last {@code searchStr} to find
	 * @return the n-th last index of the search CharSequence,
	 * {@code -1} ({@code INDEX_NOT_FOUND}) if no match or {@code null} string input
	 * @since 3.0 Changed signature from lastOrdinalIndexOf(String, String, int) to lastOrdinalIndexOf(CharSequence, CharSequence, int)
	 */
	public static int lastOrdinalIndexOf(final CharSequence str, final CharSequence searchStr, final int ordinal) {
		return ordinalIndexOf(str, searchStr, ordinal, true);
	}

	/**
	 * <p>Finds the last index within a CharSequence, handling {@code null}.
	 * This method uses {@link String#lastIndexOf(String, int)} if possible.</p>
	 * <p>
	 * <p>A {@code null} CharSequence will return {@code -1}.
	 * A negative start position returns {@code -1}.
	 * An empty ("") search CharSequence always matches unless the start position is negative.
	 * A start position greater than the string length searches the whole string.
	 * The search starts at the startPos and works backwards; matches starting after the start
	 * position are ignored.
	 * </p>
	 * <p>
	 * <pre>
	 * StringUtils.lastIndexOf(null, *, *)          = -1
	 * StringUtils.lastIndexOf(*, null, *)          = -1
	 * StringUtils.lastIndexOf("aabaabaa", "a", 8)  = 7
	 * StringUtils.lastIndexOf("aabaabaa", "b", 8)  = 5
	 * StringUtils.lastIndexOf("aabaabaa", "ab", 8) = 4
	 * StringUtils.lastIndexOf("aabaabaa", "b", 9)  = 5
	 * StringUtils.lastIndexOf("aabaabaa", "b", -1) = -1
	 * StringUtils.lastIndexOf("aabaabaa", "a", 0)  = 0
	 * StringUtils.lastIndexOf("aabaabaa", "b", 0)  = -1
	 * StringUtils.lastIndexOf("aabaabaa", "b", 1)  = -1
	 * StringUtils.lastIndexOf("aabaabaa", "b", 2)  = 2
	 * StringUtils.lastIndexOf("aabaabaa", "ba", 2)  = -1
	 * StringUtils.lastIndexOf("aabaabaa", "ba", 2)  = 2
	 * </pre>
	 *
	 * @param seq       the CharSequence to check, may be null
	 * @param searchSeq the CharSequence to find, may be null
	 * @param startPos  the start position, negative treated as zero
	 * @return the last index of the search CharSequence (always &le; startPos),
	 * -1 if no match or {@code null} string input
	 * @since 3.0 Changed signature from lastIndexOf(String, String, int) to lastIndexOf(CharSequence, CharSequence, int)
	 */
	public static int lastIndexOf(final CharSequence seq, final CharSequence searchSeq, final int startPos) {
		if (seq == null || searchSeq == null) {
			return INDEX_NOT_FOUND;
		}
		return CharSequenceUtils.lastIndexOf(seq, searchSeq, startPos);
	}

	/**
	 * <p>Case in-sensitive find of the last index within a CharSequence.</p>
	 * <p>
	 * <p>A {@code null} CharSequence will return {@code -1}.
	 * A negative start position returns {@code -1}.
	 * An empty ("") search CharSequence always matches unless the start position is negative.
	 * A start position greater than the string length searches the whole string.</p>
	 * <p>
	 * <pre>
	 * StringUtils.lastIndexOfIgnoreCase(null, *)          = -1
	 * StringUtils.lastIndexOfIgnoreCase(*, null)          = -1
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A")  = 7
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B")  = 5
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "AB") = 4
	 * </pre>
	 *
	 * @param str       the CharSequence to check, may be null
	 * @param searchStr the CharSequence to find, may be null
	 * @return the first index of the search CharSequence,
	 * -1 if no match or {@code null} string input
	 * @since 3.0 Changed signature from lastIndexOfIgnoreCase(String, String) to lastIndexOfIgnoreCase(CharSequence, CharSequence)
	 */
	public static int lastIndexOfIgnoreCase(final CharSequence str, final CharSequence searchStr) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		return lastIndexOfIgnoreCase(str, searchStr, str.length());
	}

	/**
	 * <p>Case in-sensitive find of the last index within a CharSequence
	 * from the specified position.</p>
	 * <p>
	 * <p>A {@code null} CharSequence will return {@code -1}.
	 * A negative start position returns {@code -1}.
	 * An empty ("") search CharSequence always matches unless the start position is negative.
	 * A start position greater than the string length searches the whole string.
	 * The search starts at the startPos and works backwards; matches starting after the start
	 * position are ignored.
	 * </p>
	 * <p>
	 * <pre>
	 * StringUtils.lastIndexOfIgnoreCase(null, *, *)          = -1
	 * StringUtils.lastIndexOfIgnoreCase(*, null, *)          = -1
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A", 8)  = 7
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 8)  = 5
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "AB", 8) = 4
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 9)  = 5
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", -1) = -1
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A", 0)  = 0
	 * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 0)  = -1
	 * </pre>
	 *
	 * @param str       the CharSequence to check, may be null
	 * @param searchStr the CharSequence to find, may be null
	 * @param startPos  the start position
	 * @return the last index of the search CharSequence (always &le; startPos),
	 * -1 if no match or {@code null} input
	 * @since 3.0 Changed signature from lastIndexOfIgnoreCase(String, String, int) to lastIndexOfIgnoreCase(CharSequence, CharSequence, int)
	 */
	public static int lastIndexOfIgnoreCase(final CharSequence str, final CharSequence searchStr, int startPos) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		if (startPos > str.length() - searchStr.length()) {
			startPos = str.length() - searchStr.length();
		}
		if (startPos < 0) {
			return INDEX_NOT_FOUND;
		}
		if (searchStr.length() == 0) {
			return startPos;
		}

		for (int i = startPos; i >= 0; i--) {
			if (CharSequenceUtils.regionMatches(str, true, i, searchStr, 0, searchStr.length())) {
				return i;
			}
		}
		return INDEX_NOT_FOUND;
	}

	// Contains
	//-----------------------------------------------------------------------

	/**
	 * <p>Checks if CharSequence contains a search character, handling {@code null}.
	 * This method uses {@link String#indexOf(int)} if possible.</p>
	 * <p>
	 * <p>A {@code null} or empty ("") CharSequence will return {@code false}.</p>
	 * <p>
	 * <pre>
	 * StringUtils.contains(null, *)    = false
	 * StringUtils.contains("", *)      = false
	 * StringUtils.contains("abc", 'a') = true
	 * StringUtils.contains("abc", 'z') = false
	 * </pre>
	 *
	 * @param seq        the CharSequence to check, may be null
	 * @param searchChar the character to find
	 * @return true if the CharSequence contains the search character,
	 * false if not or {@code null} string input
	 * @since 3.0 Changed signature from contains(String, int) to contains(CharSequence, int)
	 */
	public static boolean contains(final CharSequence seq, final int searchChar) {
		if (isEmpty(seq)) {
			return false;
		}
		return CharSequenceUtils.indexOf(seq, searchChar, 0) >= 0;
	}

	/**
	 * <p>Checks if CharSequence contains a search CharSequence, handling {@code null}.
	 * This method uses {@link String#indexOf(String)} if possible.</p>
	 * <p>
	 * <p>A {@code null} CharSequence will return {@code false}.</p>
	 * <p>
	 * <pre>
	 * StringUtils.contains(null, *)     = false
	 * StringUtils.contains(*, null)     = false
	 * StringUtils.contains("", "")      = true
	 * StringUtils.contains("abc", "")   = true
	 * StringUtils.contains("abc", "a")  = true
	 * StringUtils.contains("abc", "z")  = false
	 * </pre>
	 *
	 * @param seq       the CharSequence to check, may be null
	 * @param searchSeq the CharSequence to find, may be null
	 * @return true if the CharSequence contains the search CharSequence,
	 * false if not or {@code null} string input
	 * @since 3.0 Changed signature from contains(String, String) to contains(CharSequence, CharSequence)
	 */
	public static boolean contains(final CharSequence seq, final CharSequence searchSeq) {
		if (seq == null || searchSeq == null) {
			return false;
		}
		return CharSequenceUtils.indexOf(seq, searchSeq, 0) >= 0;
	}

	/**
	 * <p>Checks if CharSequence contains a search CharSequence irrespective of case,
	 * handling {@code null}. Case-insensitivity is defined as by
	 * {@link String#equalsIgnoreCase(String)}.
	 * <p>
	 * <p>A {@code null} CharSequence will return {@code false}.</p>
	 * <p>
	 * <pre>
	 * StringUtils.contains(null, *) = false
	 * StringUtils.contains(*, null) = false
	 * StringUtils.contains("", "") = true
	 * StringUtils.contains("abc", "") = true
	 * StringUtils.contains("abc", "a") = true
	 * StringUtils.contains("abc", "z") = false
	 * StringUtils.contains("abc", "A") = true
	 * StringUtils.contains("abc", "Z") = false
	 * </pre>
	 *
	 * @param str       the CharSequence to check, may be null
	 * @param searchStr the CharSequence to find, may be null
	 * @return true if the CharSequence contains the search CharSequence irrespective of
	 * case or false if not or {@code null} string input
	 * @since 3.0 Changed signature from containsIgnoreCase(String, String) to containsIgnoreCase(CharSequence, CharSequence)
	 */
	public static boolean containsIgnoreCase(final CharSequence str, final CharSequence searchStr) {
		if (str == null || searchStr == null) {
			return false;
		}
		final int len = searchStr.length();
		final int max = str.length() - len;
		for (int i = 0; i <= max; i++) {
			if (CharSequenceUtils.regionMatches(str, true, i, searchStr, 0, len)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check whether the given CharSequence contains any whitespace characters.
	 *
	 * @param seq the CharSequence to check (may be {@code null})
	 * @return {@code true} if the CharSequence is not empty and
	 * contains at least 1 whitespace character
	 * @see java.lang.Character#isWhitespace
	 * @since 3.0
	 */
	// From org.springframework.util.StringUtils, under Apache License 2.0
	public static boolean containsWhitespace(final CharSequence seq) {
		if (isEmpty(seq)) {
			return false;
		}
		final int strLen = seq.length();
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(seq.charAt(i))) {
				return true;
			}
		}
		return false;
	}
}
