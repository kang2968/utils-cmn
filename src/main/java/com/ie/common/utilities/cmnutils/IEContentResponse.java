package com.ie.common.utilities.cmnutils;

/**
 * Common Content Response
 *
 * @param <T>
 * @author bradly
 * @version 1.0
 */
public class IEContentResponse<T> implements IEIResMsgCode<T> {
    
    /**
     * response error code
     */
    private long code;
    
    /**
     * response error message
     */
    private String msg;
    
    /**
     * response data detail
     */
    private T data;
    
    public IEContentResponse(IEIMsgCode msgCode) {
        this.code = msgCode.getCode();
        this.msg = msgCode.getMsg();
    }
    
    public IEContentResponse(IEIMsgCode msgCode, T data) {
        this(msgCode);
        this.data = data;
    }
    
    /**
     * default response is success
     *
     * @param data
     */
    public IEContentResponse(T data) {
        this(IEIMsgCode.SUCCESS);
        this.data = data;
    }
    
    @Override
    public long getCode() {
        return this.code;
    }
    
    @Override
    public String getMsg() {
        return this.msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    @Override
    public T getData() {
        return this.data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("IEContentResponse:{");
        builder.append("code=").append(this.code).append(", ");
        builder.append("msg=").append(this.msg).append(", ");
        builder.append("data=").append(this.data);
        builder.append("}");
        return builder.toString();
    }
}
