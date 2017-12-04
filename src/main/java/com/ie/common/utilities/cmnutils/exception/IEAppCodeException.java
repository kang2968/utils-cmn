package com.ie.common.utilities.cmnutils.exception;

import com.ie.common.utilities.cmnutils.IEIMsgCode;
import com.ie.common.utilities.cmnutils.IEPrintUtils;

/**
 * code exception in application level.
 * @author bradly
 * @version 1.0
 */
public class IEAppCodeException extends RuntimeException {

    private final IEIMsgCode msgCode;

    public IEAppCodeException(IEIMsgCode msgCode) {
        super(IEPrintUtils.printCode(msgCode));
        this.msgCode = msgCode;
    }

    public IEIMsgCode getMsgCode() {
        return msgCode;
    }

}
