package com.ie.common.utilities.cmnutils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * common string utilities
 * @author bradly
 * @version 1.0
 */
public class IEStringUtils {

    private IEStringUtils(){}

    /**
     * trim string
     * @param value
     * @return it will be null when value is null
     */
    public static String trim(final String value){
        return value == null ? null : value.trim();
    }

    /**
     * fetch string length
     * @param value
     * @return it will be 0 when value is null or empty
     */
    public static int length(final String value){
        return value == null ? 0 : value.length();
    }
    
    /**
     * blank when value is null
     *
     * @param value
     * @return
     */
    public static String nullToBlank(final String value) {
        return value == null ? "" : value;
    }
    
    /**
     * check whether charSequence is blank
     * @param value
     * @return
     */
    public static boolean isBlank(final CharSequence value) {
        if (value != null && value.length() != 0) {
            for (int i = 0; i < value.length(); i++) {
                if (Character.isWhitespace(value.charAt(i)) == false) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * check whether charSequence is not blank
     * @param value
     * @return
     */
    public static boolean isNotBlank(final CharSequence value){
        return !isBlank(value);
    }

    private static String[] toArray(final String input, final String pattern, final int limit){
        IEValidateUtils.notEmpty(pattern, "The pattern can't be empty");
        if(input == null){
            return null;
        }
        return input.split(pattern, limit);
    }

    private static String[] toArray(final String input, final String pattern){
        return toArray(input, pattern, -1);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(final String input, final String pattern, final Class<T> clazz){
        String[] arrs = toArray(input, pattern);
        if(arrs == null){
            return null;
        }
        T[] result = (T[]) Array.newInstance(clazz, arrs.length);
        for(int i = 0 ; i < arrs.length ; i++){
            result[i] = IEObjectUtils.castObject(arrs[i], clazz);
        }
        return result;
    }

    public static <T> List<T> toList(final String input, final String pattern, final Class<T> clazz){
        String[] arrs = toArray(input, pattern);
        if(arrs == null){
            return null;
        }
        List<T> result = new ArrayList<>(arrs.length);
        for(String s : arrs){
            result.add(IEObjectUtils.castObject(s, clazz));
        }
        return result;
    }

    public static <K, V> Map<K, V> toMap(final String input, final String firstPattern, final String secondPattern, final Class<K> clazzK, final Class<V> clazzV){
        String[] arrs = toArray(input, firstPattern);
        if(arrs == null){
            return null;
        }
        Map<K, V> result = new LinkedHashMap<>();
        String[] innerArras = null;
        for(String s : arrs){
            innerArras = toArray(s, secondPattern, 2);
            if(innerArras != null && innerArras.length == 2){
                result.put(IEObjectUtils.castObject(innerArras[0], clazzK), IEObjectUtils.castObject(innerArras[1], clazzV));
            }
        }
        return result;
    }

    public static <K, V> Map<K, List<V>> toListMap(final String input, final String firstPattern, final String secondPattern, final String thirdPattern, final Class<K> clazzK, final Class<V> clazzV){
        IEValidateUtils.notEmpty(firstPattern, "The first pattern can't be empty");
        IEValidateUtils.notEmpty(secondPattern, "The second pattern can't be empty");
        IEValidateUtils.notEmpty(thirdPattern, "The third pattern can't be empty");
        String[] arrs = toArray(input, firstPattern);
        if(arrs == null){
            return null;
        }
        Map<K, List<V>> result = new LinkedHashMap<>();
        String[] innerArras = null;
        List<V> list = null;
        for(String s : arrs){
            innerArras = toArray(s, secondPattern, 2);
            if(innerArras != null && innerArras.length == 2){
                list = toList(innerArras[1], thirdPattern, clazzV);
                result.put(IEObjectUtils.castObject(innerArras[0], clazzK), list);
            }
        }
        return result;
    }

    public static <K, V> Map<K, V[]> toArrayMap(final String input, final String firstPattern, final String secondPattern, final String thirdPattern, final Class<K> clazzK, final Class<V> clazzV){
        IEValidateUtils.notEmpty(firstPattern, "The first pattern can't be empty");
        IEValidateUtils.notEmpty(secondPattern, "The second pattern can't be empty");
        IEValidateUtils.notEmpty(thirdPattern, "The third pattern can't be empty");
        String[] arrs = toArray(input, firstPattern);
        if(arrs == null){
            return null;
        }
        Map<K, V[]> result = new LinkedHashMap<>();
        String[] innerArras = null;
        V[] arr = null;
        for(String s : arrs){
            innerArras = toArray(s, secondPattern, 2);
            if(innerArras != null && innerArras.length == 2){
                arr = toArray(innerArras[1], thirdPattern, clazzV);
                result.put(IEObjectUtils.castObject(innerArras[0], clazzK), arr);
            }
        }
        return result;
    }

}
