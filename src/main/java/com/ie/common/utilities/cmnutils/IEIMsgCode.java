package com.ie.common.utilities.cmnutils;


/**
 * common message code interface
 *
 * @author bradly
 * @version 1.0
 */
public interface IEIMsgCode {
    
    /**
     * handle successfully
     */
    IEIMsgCode SUCCESS = new IEIMsgCode() {
        @Override
        public long getCode() {
            return 0;
        }
        
        @Override
        public String getMsg() {
            return "SUCCESS";
        }
    };
    /**
     * handle failed
     */
    IEIMsgCode FAIL = new IEIMsgCode() {
        @Override
        public long getCode() {
            return -1;
        }
    
        @Override
        public String getMsg() {
            return "FAIL";
        }
    };
    /**
     * there are some failed validation
     */
    IEIMsgCode BAD_REQUEST = new IEIMsgCode() {
        @Override
        public long getCode() {
            return 400;
        }
    
        @Override
        public String getMsg() {
            return "BadRequest";
        }
    };
    /**
     * failed authorization
     */
    IEIMsgCode UNAUTHORIZED = new IEIMsgCode() {
        @Override
        public long getCode() {
            return 401;
        }
    
        @Override
        public String getMsg() {
            return "Unauthorized";
        }
    };
    /**
     * there is not any thing to back guest or the thing needed to handle dose not exist
     */
    IEIMsgCode NOT_FOUND = new IEIMsgCode() {
        @Override
        public long getCode() {
            return 404;
        }
    
        @Override
        public String getMsg() {
            return "NotFound";
        }
    };
    /**
     * there is some error in server
     */
    IEIMsgCode INTERNAL_ERROR = new IEIMsgCode() {
        @Override
        public long getCode() {
            return 500;
        }
    
        @Override
        public String getMsg() {
            return "Internal Server Error";
        }
    };
    
    /**
     * get message code
     *
     * @return
     */
    long getCode();
    
    /**
     * get message detail
     *
     * @return
     */
    String getMsg();
    
}
