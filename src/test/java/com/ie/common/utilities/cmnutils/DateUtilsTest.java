package com.ie.common.utilities.cmnutils;

import com.ie.common.utilities.cmnutils.exception.IEValidateMsgException;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtilsTest {


    @Test
    public void testShowAllTimeZone(){
        String[] ids = TimeZone.getAvailableIDs();
        for(String s : ids){
            System.out.println(s);
        }
    }

    @Test
    public void testGetFormatInstance(){
        SimpleDateFormat sdf = null;
        try{
            sdf = IEDateUtils.getFormatInstance(null, null);
            Assert.fail("Missing timezoneID validation");
        }catch (IEValidateMsgException e){
            Assert.assertEquals("The timezoneID can't be blank", e.getMessage());
        }
        try{
            sdf = IEDateUtils.getFormatInstance("Asia/Kuala_Lumpur", null);
            Assert.fail("Missing pattern validation");
        }catch (IEValidateMsgException e){
            Assert.assertEquals("The pattern can't be blank", e.getMessage());
        }
        sdf = null;
        sdf = IEDateUtils.getFormatInstance("Asia/Kuala_Lumpur", "yyyy-MM-dd");
        Assert.assertNotNull(sdf);
        Assert.assertEquals("Asia/Kuala_Lumpur", sdf.getTimeZone().getID());
        Assert.assertEquals("yyyy-MM-dd", sdf.toPattern());
    }

    @Test
    public void testFormat() {
        try{
            IEDateUtils.format("Asia/Kuala_Lumpur", "yyyy-MM-dd", null);
            Assert.fail("Missing date validation");
        }catch (IEValidateMsgException e){
            Assert.assertEquals("The date can't be null", e.getMessage());
        }
        String result = IEDateUtils.format("Asia/Kuala_Lumpur", "yyyy-MM-dd", new Date(1512052409364L));
        Assert.assertEquals("2017-11-30", result);

    }
    @Test
    public void testParse(){

        try {
            IEDateUtils.parse("Asia/Kuala_Lumpur","yyyy-MM-dd", null);
            Assert.fail("Missing date validation");
        } catch (IEValidateMsgException e) {
            Assert.assertEquals("The string value of date can't be blank", e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
            Assert.fail();
        }

        try {
            IEDateUtils.parse("Asia/Kuala_Lumpur", "yyyy-MM-dd", "20170101");
            Assert.fail("There should be a parse exception");
        } catch (ParseException e) {
            Assert.assertEquals("Unparseable date: \"20170101\"", e.getMessage());
        }

        Date result = null;
        try {
            result = IEDateUtils.parse("Asia/Kuala_Lumpur", "yyyy-MM-dd", "2017-11-30");
            Assert.assertEquals(1511971200000L, result.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void testDayStart(){
        Date result = IEDateUtils.dayStart("Asia/Kuala_Lumpur", new Date(1511971200100L));
        Assert.assertEquals(1511971200000L, result.getTime());
    }

    @Test
    public void testDayEnd(){
        Date result = IEDateUtils.dayEnd("Asia/Kuala_Lumpur", new Date(1511971200001L));
        Assert.assertEquals(1511971200000L + 24 * 60 * 60 * 1000L -1L, result.getTime());
    }

}
