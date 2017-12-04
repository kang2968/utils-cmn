package com.ie.common.utilities.cmnutils;

import com.ie.common.utilities.cmnutils.exception.IEValidateCodeException;
import com.ie.common.utilities.cmnutils.exception.IEValidateMsgException;

import java.util.Collection;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * common validation utilities
 * @author bradly
 * @version 1.0
 */
public class IEValidateUtils {

    private IEValidateUtils(){}

    private static final String mail_pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * throw IEValidateCodeException error when object is null
     * @param value
     * @param msgCode
     */
    public static void notNull(final Object value, final IEIMsgCode msgCode){
        if(value == null){
            throw new IEValidateCodeException(msgCode);
        }
    }

    /**
     * throw IEValidateMsgException error when object is null
     * @param value
     * @param template
     * @param params
     */
    public static void notNull(final Object value, final String template, final Object ... params){
        if(value == null){
            throw new IEValidateMsgException(String.format(template, params));
        }
    }

    /**
     * throw IEValidateCodeException error when charSequence is blank
     * @param value
     * @param msgCode
     */
    public static void notBlank(final CharSequence value, final IEIMsgCode msgCode){
        if(IEStringUtils.isBlank(value)){
            throw new IEValidateCodeException(msgCode);
        }
    }

    /**
     * throw IEValidateMsgException error when charSequence is blank
     * @param value
     * @param template
     * @param params
     */
    public static void notBlank(final CharSequence value, final String template, final Object ... params){
        if(IEStringUtils.isBlank(value)){
            throw new IEValidateMsgException(String.format(template, params));
        }
    }

    /**
     * throw IEValidateCodeException error when value is null or false
     * @param value
     * @param msgCode
     */
    public static void isTrue(final Boolean value, final IEIMsgCode msgCode){
        if(value == null || !value){
            throw new IEValidateCodeException(msgCode);
        }
    }

    /**
     * throw IEValidateMsgException error when value is null or false
     * @param value
     * @param template
     * @param params
     */
    public static  void isTrue(final Boolean value, final String template, final Object ... params){
        if(value == null || !value){
            throw new IEValidateMsgException(String.format(template, params));
        }
    }

    /**
     * throw IEValidateCodeException error when value is null or the length is zero
     * @param value
     * @param msgCode
     */
    public static void notEmpty(final CharSequence value, final IEIMsgCode msgCode){
        if(value == null || value.length() == 0){
            throw new IEValidateCodeException(msgCode);
        }
    }

    /**
     * throw IEValidateCodeException error when value is null or the size is 0
     * @param value
     * @param msgCode
     */
    public static void notEmpty(final Collection<?> value, final IEIMsgCode msgCode){
        if(value == null || value.size() == 0){
            throw new IEValidateCodeException(msgCode);
        }
    }

    /**
     * throw IEValidateCodeException error when value is null or the size is 0
     * @param value
     * @param msgCode
     */
    public static void notEmpty(final Set<?> value, final IEIMsgCode msgCode){
        if(value == null || value.size() == 0){
            throw new IEValidateCodeException(msgCode);
        }
    }

    /**
     * throw IEValidateMsgException when value is null or the length is 0
     * @param value
     * @param template
     * @param params
     */
    public static void notEmpty(final CharSequence value, final String template, final Object ... params){
        if(value == null || value.length() == 0){
            throw new IEValidateMsgException(String.format(template, params));
        }
    }

    /**
     * throw IEValidateMsgException when value is null or the size is 0
     * @param value
     * @param template
     * @param params
     */
    public static void notEmpty(final Collection<?> value, final String template, final Object ... params){
        if(value == null || value.size() == 0){
            throw new IEValidateMsgException(String.format(template, params));
        }
    }

    /**
     * throw IEValidateMsgException when value is null or the size is 0
     * @param value
     * @param template
     * @param params
     */
    public static void notEmpty(final Set<?> value, final String template, final Object ... params){
        if(value == null || value.size() == 0){
            throw new IEValidateMsgException(String.format(template, params));
        }
    }

    /**
     * throw IEValidateCodeException when the value doesn't match pattern
     * @param value
     * @param pattern
     * @param msgCode
     */
    public static void matchPattern(final CharSequence value, final String pattern, final IEIMsgCode msgCode){
        if(!Pattern.matches(pattern, value)){
            throw new IEValidateCodeException(msgCode);
        }
    }

    /**
     * throw IEValidateMsgException when the value doesn't match pattern
     * @param value
     * @param pattern
     * @param template
     * @param params
     */
    public static void matchPattern(final CharSequence value, final String pattern, final String template, final Object ... params){
        if(!Pattern.matches(pattern, value)){
            throw new IEValidateMsgException(String.format(template, params));
        }
    }

    /**
     * throw IEValidateCodeException when value is a invalid email
     * @param value
     * @param msgCode
     */
    public static void isEmail(final CharSequence value, final IEIMsgCode msgCode){
        matchPattern(value, mail_pattern, msgCode);
    }

    /**
     * throw IEValidateMsgException when value is a invalid email
     * @param value
     * @param template
     * @param params
     */
    public static void isEmail(final CharSequence value, final String template, final Object ... params){
        matchPattern(value, mail_pattern, template, params);
    }

    /**
     * long number compare
     * @param value
     * @param compareValue
     * @param msgCode
     */
    public static void isGreaterThan(final long value, final long compareValue, IEIMsgCode msgCode){
        if (value <= compareValue) {
            throw new IEValidateCodeException(msgCode);
        }
    }

    public static void isLittleThan(final long value, final long compareValue, IEIMsgCode msgCode){
        if (value >= compareValue) {
            throw new IEValidateCodeException(msgCode);
        }
    }

    public static void isGreaterThanEq(final long value, final long compareValue, IEIMsgCode msgCode){
        if (value < compareValue) {
            throw new IEValidateCodeException(msgCode);
        }
    }

    public static void isLittleThanEq(final long value, final long compareValue, IEIMsgCode msgCode) {
        if (value > compareValue) {
            throw new IEValidateCodeException(msgCode);
        }
    }

    public static void isGreaterThan(final long value, final long compareValue, final String template, final Object ... params){
        if (value <= compareValue) {
            throw new IEValidateMsgException(String.format(template, params));
        }
    }

    public static void isLittleThan(final long value, final long compareValue, final String template, final Object ... params){
        if (value >= compareValue) {
            throw new IEValidateMsgException(String.format(template, params));
        }
    }

    public static void isGreaterThanEq(final long value, final long compareValue, final String template, final Object ... params){
        if (value < compareValue) {
            throw new IEValidateMsgException(String.format(template, params));
        }
    }

    public static void isLittleThanEq(final long value, final long compareValue, final String template, final Object ... params) {
        if (value > compareValue) {
            throw new IEValidateMsgException(String.format(template, params));
        }
    }

    /**
     * double number compare
     * @param value
     * @param compareValue
     * @param msgCode
     */
    public static void isGreaterThan(final double value, final double compareValue, IEIMsgCode msgCode){
        if (value <= compareValue) {
            throw new IEValidateCodeException(msgCode);
        }
    }

    public static void isLittleThan(final double value, final double compareValue, IEIMsgCode msgCode){
        if (value >= compareValue) {
            throw new IEValidateCodeException(msgCode);
        }
    }

    public static void isGreaterThanEq(final double value, final double compareValue, IEIMsgCode msgCode){
        if (value < compareValue) {
            throw new IEValidateCodeException(msgCode);
        }
    }

    public static void isLittleThanEq(final double value, final double compareValue, IEIMsgCode msgCode) {
        if (value > compareValue) {
            throw new IEValidateCodeException(msgCode);
        }
    }

    public static void isGreaterThan(final double value, final double compareValue, final String template, final Object ... params){
        if (value <= compareValue) {
            throw new IEValidateMsgException(String.format(template, params));
        }
    }

    public static void isLittleThan(final double value, final double compareValue, final String template, final Object ... params){
        if (value >= compareValue) {
            throw new IEValidateMsgException(String.format(template, params));
        }
    }

    public static void isGreaterThanEq(final double value, final double compareValue, final String template, final Object ... params){
        if (value < compareValue) {
            throw new IEValidateMsgException(String.format(template, params));
        }
    }

    public static void isLittleThanEq(final double value, final double compareValue, final String template, final Object ... params) {
        if (value > compareValue) {
            throw new IEValidateMsgException(String.format(template, params));
        }
    }
}
