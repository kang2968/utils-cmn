package com.ie.common.utilities.cmnutils;

import com.ie.common.utilities.cmnutils.page.IEPageWrapper;

/**
 * @author bradly
 * @version 1.0.0
 * @date 1/15/18 10:22 AM
 */
public class IEPageResponse<T> extends IEContentResponse<IEPageWrapper<T>> {
    
    public IEPageResponse(IEIMsgCode msgCode) {
        super(msgCode);
    }
    
    public IEPageResponse(IEIMsgCode msgCode, IEPageWrapper<T> wrapper) {
        super(msgCode, wrapper);
    }
    
    /**
     * default response is success
     *
     * @param wrapper
     */
    public IEPageResponse(IEPageWrapper<T> wrapper) {
        super(wrapper);
    }
    
}
