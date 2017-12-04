package com.ie.common.utilities.cmnutils.exception;

/**
 * Object 对象转化异常
 * @author bradly
 * @version 1.0
 */
public class IEObjectCastException extends RuntimeException {

	public IEObjectCastException(String message, Throwable cause) {
		super(message, cause);
	}

	public IEObjectCastException(String message) {
		super(message);
	}

}
