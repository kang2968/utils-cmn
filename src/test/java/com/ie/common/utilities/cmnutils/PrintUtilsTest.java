package com.ie.common.utilities.cmnutils;

import org.junit.Test;

public class PrintUtilsTest {

    @Test
    public void testPrintCode(){
        System.out.println(IEPrintUtils.printMsg(100, "error"));
    }

    @Test
    public void testPrintInsCode(){
        System.out.println(IEPrintUtils.printCode(new IEIMsgCode() {
            @Override
            public long getCode() {
                return 1000;
            }

            @Override
            public String getMsg() {
                return "msg info";
            }
        }));
    }

    @Test
    public void testPrintError(){
        System.out.println(IEPrintUtils.printTrace(new RuntimeException("runttimee")));
    }

}
