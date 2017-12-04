package com.ie.common.utilities.cmnutils.exception;

import com.ie.common.utilities.cmnutils.IEIMsgCode;
import com.ie.common.utilities.cmnutils.IEPrintUtils;

/**
 * common validation failed exception
 * @author bradly
 * @version 1.0
 */
public class IEValidateCodeException extends RuntimeException {

    private final IEIMsgCode msgCode;

    public IEValidateCodeException(IEIMsgCode msgCode) {
        super(IEPrintUtils.printCode(msgCode));
        this.msgCode = msgCode;
    }

    public IEIMsgCode getMsgCode() {
        return msgCode;
    }

}
