package com.ie.common.utilities.cmnutils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *  print object or message
 * @author bradly
 * @version 1.0
 */
public class IEPrintUtils {

    private IEPrintUtils(){}


    /**
     * print message code
     * @param msgCode
     * @return
     */
    public static String printCode(final IEIMsgCode msgCode){
        return printMsg(msgCode.getCode(), msgCode.getMsg());
    }

    /**
     * print normal message
     * @param code
     * @param msg
     * @return
     */
    public static String printMsg(final String code, final String msg){
        return String.format("[Code = %s, Msg = %s]", code, msg);
    }

    /**
     * print normal message
     * @param code
     * @param msg
     * @return
     */
    public static String printMsg(final long code, final String msg){
        return printMsg(String.valueOf(code), msg);
    }

    /**
     * print normal stack trace
     * @param e
     * @return
     */
    public static String printTrace(final Throwable e){
        try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw, true)){
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            return sw.toString();
        } catch (Exception e1) {
            throw new RuntimeException("Print Trace Failed", e1);
        }
    }

}
