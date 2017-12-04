package com.ie.common.utilities.cmnutils;

import com.ie.common.utilities.cmnutils.exception.IEObjectCastException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


/**
 * 对象处理工具类
 * @author bradly
 * @version 1.0
 *
 */
public class IEObjectUtils {

	private IEObjectUtils() {
	}

	// Retrieve
	public static <K, V> V getValue(final Map<K, V> map, final K key) {
		if (map == null) {
			return null;
		}
		return map.get(key);
	}

	public static <K, V> String getString(final Map<K, V> map, final K key) {
		V value = getValue(map, key);
		if (value == null) {
			return null;
		}
		return value.toString();
	}
	
	public static <K, V> byte[] getBytes(final Map<K, V> map, final K key){
		V value = getValue(map, key);
		if(value == null){
			return null;
		}
		return castToBytes(value);
	}

	public static <K, V> Byte getByte(final Map<K, V> map, final K key) {
		V value = getValue(map, key);
		return castToByte(value);
	}
	
	public static <K, V> byte getByteValue(final Map<K, V> map, final K key, final byte defaultValue){
		V value = getValue(map, key);
		
		if(value == null){
			return defaultValue;
		}
		return castToByte(value).byteValue();
	}

	public static <K, V> Short getShort(final Map<K, V> map, final K key) {
		V value = getValue(map, key);
		return castToShort(value);
	}

	public static <K, V> short getShortValue(final Map<K, V> map, final K key, final short defaultValue) {
		V value = getValue(map, key);
		if (value == null) {
			return defaultValue;
		}
		return castToShort(value).shortValue();
	}

	public static <K, V> Integer getInteger(final Map<K, V> map, final K key) {
		V value = getValue(map, key);
		return castToInt(value);
	}

	public static <K, V> int getIntValue(final Map<K, V> map, final K key, final int defaultValue) {
		V value = getValue(map, key);
		if (value == null) {
			return defaultValue;
		}
		return castToInt(value).intValue();
	}

	public static <K, V> Float getFloat(final Map<K, V> map, final K key) {
		V value = getValue(map, key);
		return castToFloat(value);
	}

	public static <K, V> float getFloatValue(final Map<K, V> map, final K key, final float defaultValue) {
		V value = getValue(map, key);
		if (value == null) {
			return defaultValue;
		}
		return castToFloat(value);
	}

	public static <K, V> Double getDouble(final Map<K, V> map, final K key) {
		V value = getValue(map, key);
		return castToDouble(value);
	}

	public static <K, V> Double getDoubleValue(final Map<K, V> map, final K key, final double defaultValue) {
		V value = getValue(map, key);
		if (value == null) {
			return defaultValue;
		}
		return castToDouble(value);
	}

	public static <K, V> Long getLong(final Map<K, V> map, final K key) {
		V value = getValue(map, key);
		return castToLong(value);
	}

	public static <K, V> long getLongValue(final Map<K, V> map, final K key, final long defaultValue) {
		V value = getValue(map, key);
		if (value == null) {
			return defaultValue;
		}
		return castToLong(value).longValue();
	}

	public static <K, V> BigInteger getBigInteger(final Map<K, V> map, final K key) {
		V value = getValue(map, key);
		return castToBigInteger(value);
	}

	public static <K, V> BigDecimal getBigDecimal(final Map<K, V> map, final K key) {
		V value = getValue(map, key);
		return castToBigDecimal(value);
	}

	public static <K, V> Boolean getBoolean(final Map<K, V> map, final K key) {
		V value = getValue(map, key);
		if (value == null) {
			return null;
		}
		return castToBoolean(value);
	}

	public static <K, V> boolean getBooleanValue(final Map<K, V> map, final K key) {
		V value = getValue(map, key);
		if (value == null) {
			return false;
		}
		return castToBoolean(value).booleanValue();
	}

	public static <K, V> Date getDate(final Map<K, V> map, final K key) {
		V value = getValue(map, key);
		return castToDate(value);
	}

	public static <K, V> Date getDate(final Map<K, V> map, final K key, final String pattern, final String timezoneID) {
		String value = getString(map,key);
		if (value == null) {
			return null;
		}
		try {
			return IEDateUtils.parse(timezoneID, pattern, value);
		} catch (ParseException e) {
			throw new IEObjectCastException("can not cast to Date, value : " + value);
		}
	}

	// Cast
	public static String castToString(final Object value) {
		if (value == null) {
			return null;
		}

		return value.toString();
	}

	public static Byte castToByte(final Object value) {
		if (value == null) {
			return null;
		}

		if (value instanceof Number) {
			return ((Number) value).byteValue();
		}

		if (value instanceof String) {
			String strVal = (String) value;
			if (strVal.length() == 0) {
				return null;
			}
			return Byte.parseByte(strVal);
		}

		throw new IEObjectCastException("can not cast to byte, value : " + value);
	}

	public static Character castToChar(final Object value) {
		if (value == null) {
			return null;
		}

		if (value instanceof Character) {
			return (Character) value;
		}

		if (value instanceof String) {
			String strVal = (String) value;

			if (strVal.length() == 0) {
				return null;
			}

			if (strVal.length() != 1) {
				throw new IEObjectCastException("can not cast to byte, value : " + value);
			}

			return strVal.charAt(0);
		}

		throw new IEObjectCastException("can not cast to byte, value : " + value);
	}

	public static Short castToShort(final Object value) {
		if (value == null) {
			return null;
		}

		if (value instanceof Number) {
			return ((Number) value).shortValue();
		}

		if (value instanceof String) {
			String strVal = (String) value;
			if (strVal.length() == 0) {
				return null;
			}
			return Short.parseShort(strVal);
		}

		throw new IEObjectCastException("can not cast to short, value : " + value);
	}

	public static BigDecimal castToBigDecimal(final Object value) {
		if (value == null) {
			return null;
		}

		if (value instanceof BigDecimal) {
			return (BigDecimal) value;
		}

		if (value instanceof BigInteger) {
			return new BigDecimal((BigInteger) value);
		}

		String strVal = value.toString();
		if (strVal.length() == 0) {
			return null;
		}

		return new BigDecimal(strVal);
	}

	public static BigInteger castToBigInteger(final Object value) {
		if (value == null) {
			return null;
		}

		if (value instanceof BigInteger) {
			return (BigInteger) value;
		}

		if (value instanceof Float || value instanceof Double) {
			return BigInteger.valueOf(((Number) value).longValue());
		}

		String strVal = value.toString();
		if (strVal.length() == 0) {
			return null;
		}

		return new BigInteger(strVal);
	}

	public static Float castToFloat(final Object value) {
		if (value == null) {
			return null;
		}

		if (value instanceof Number) {
			return ((Number) value).floatValue();
		}

		if (value instanceof String) {
			String strVal = value.toString();
			if (strVal.length() == 0) {
				return null;
			}

			return Float.parseFloat(strVal);
		}

		throw new IEObjectCastException("can not cast to float, value : " + value);
	}

	public static Double castToDouble(final Object value) {
		if (value == null) {
			return null;
		}

		if (value instanceof Number) {
			return ((Number) value).doubleValue();
		}

		if (value instanceof String) {
			String strVal = value.toString();
			if (strVal.length() == 0) {
				return null;
			}
			return Double.parseDouble(strVal);
		}

		throw new IEObjectCastException("can not cast to double, value : " + value);
	}

	public static Date castToDate(final Object value) {
		if (value == null) {
			return null;
		}

		if (value instanceof Calendar) {
			return ((Calendar) value).getTime();
		}

		if (value instanceof Date) {
			return (Date) value;
		}

		long longValue = -1;

		if (value instanceof Number) {
			longValue = ((Number) value).longValue();
		}

		if (value instanceof String) {
			String strVal = (String) value;

			if (strVal.indexOf('-') != -1) {
				String format;
				if (strVal.length() == "yyyy-MM-dd HH:mm:ss".length()) {
					format = "yyyy-MM-dd HH:mm:ss";
				} else if (strVal.length() == "yyyy-MM-dd".length()) {
					format = "yyyy-MM-dd";
				} else {
					format = "yyyy-MM-dd HH:mm:ss.SSS";
				}

				SimpleDateFormat dateFormat = new SimpleDateFormat(format);
				try {
					return dateFormat.parse(strVal);
				} catch (ParseException e) {
					throw new IEObjectCastException("can not cast to Date, value : " + strVal);
				}
			}

			if (strVal.length() == 0) {
				return null;
			}

			longValue = Long.parseLong(strVal);
		}

		if (longValue < 0) {
			throw new IEObjectCastException("can not cast to Date, value : " + value);
		}

		return new Date(longValue);
	}

	public static Long castToLong(final Object value) {
		if (value == null) {
			return null;
		}

		if (value instanceof Number) {
			return ((Number) value).longValue();
		}

		if (value instanceof String) {
			String strVal = (String) value;
			if (strVal.length() == 0) {
				return null;
			}

			try {
				return Long.parseLong(strVal);
			} catch (NumberFormatException ex) {
				throw new IEObjectCastException("can not cast to long, value : " + value);
			}
		}
		throw new IEObjectCastException("can not cast to long, value : " + value);
	}

	public static Integer castToInt(final Object value) {
		if (value == null) {
			return null;
		}

		if (value instanceof Integer) {
			return (Integer) value;
		}

		if (value instanceof Number) {
			return ((Number) value).intValue();
		}

		if (value instanceof String) {
			String strVal = (String) value;
			if (strVal.length() == 0) {
				return null;
			}

			return Integer.parseInt(strVal);
		}

		throw new IEObjectCastException("can not cast to int, value : " + value);
	}

	public static byte[] castToBytes(final Object value) {
		if (value instanceof byte[]) {
			return (byte[]) value;
		}

		if (value instanceof String) {
			return ((String) value).getBytes();
		}
		throw new IEObjectCastException("can not cast to bytes, value : " + value);
	}

	public static Boolean castToBoolean(final Object value) {
		if (value == null) {
			return null;
		}

		if (value instanceof Boolean) {
			return (Boolean) value;
		}

		if (value instanceof Number) {
			return ((Number) value).intValue() == 1;
		}

		if (value instanceof String) {
			String str = (String) value;
			if (str.length() == 0) {
				return null;
			}
			str = str.trim().toUpperCase();
			if ("TRUE".equals(str)) {
				return Boolean.TRUE;
			}
			if ("FALSE".equals(str)) {
				return Boolean.FALSE;
			}

		}

		throw new IEObjectCastException("can not cast to boolean, value : " + value);
	}

	/**
	 * 
	 * @param object
	 * @param clazz  
	 * Support Class : 
	 * 1. Byte, byte, Short, short, Integer, int, Long, long, Float, float, Double, double, 
	 * 2. BigInteger, BigDecimal, Character, char, Boolean, boolean, Date, String
 	 * @return Object ,not primitive class -> int.class -> return Integer.class
	 */
	@SuppressWarnings("unchecked")
	public static <T> T castObject(final Object object, final Class<T> clazz){
		if(clazz.equals(Byte.class) || clazz.equals(Byte.TYPE)){
			return (T) IEObjectUtils.castToByte(object);
		}else if(clazz.equals(Short.class) || clazz.equals(Short.TYPE)){
			return (T) IEObjectUtils.castToShort(object);
		}else if(clazz.equals(Integer.class) || clazz.equals(Integer.TYPE)){
			return (T) IEObjectUtils.castToInt(object);
		}else if(clazz.equals(Long.class) || clazz.equals(Long.TYPE)){
			return (T) IEObjectUtils.castToLong(object);
		}else if(clazz.equals(Float.class) || clazz.equals(Float.TYPE)){
			return (T) IEObjectUtils.castToFloat(object);
		}else if(clazz.equals(Double.class) || clazz.equals(Double.TYPE)){
			return (T) IEObjectUtils.castToDouble(object);
		}else if(clazz.equals(BigInteger.class)){
			return (T) IEObjectUtils.castToBigInteger(object);
		}else if(clazz.equals(BigDecimal.class)){
			return (T) IEObjectUtils.castToBigDecimal(object);
		}else if(clazz.equals(Character.class) || clazz.equals(Character.TYPE)){
			return (T) IEObjectUtils.castToChar(object);
		}else if(clazz.equals(Boolean.class) || clazz.equals(Boolean.TYPE)){
			return (T) IEObjectUtils.castToBoolean(object);
		}else if(clazz.equals(Date.class)){
			return (T) IEObjectUtils.castToDate(object);
		}else if(clazz.equals(String.class)){
			return (T) IEObjectUtils.castToString(object);
		}else{
			throw new IEObjectCastException(String.format("It dosen't support %s", clazz.getName()));
		}
	}
	
}
