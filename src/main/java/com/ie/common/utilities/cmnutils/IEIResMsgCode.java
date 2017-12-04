package com.ie.common.utilities.cmnutils;

/**
 * common response message code interface
 * @author bradly
 * @version 1.0
 */
public interface IEIResMsgCode<T> extends IEIMsgCode {

    default T getData(){ return null;}

}
