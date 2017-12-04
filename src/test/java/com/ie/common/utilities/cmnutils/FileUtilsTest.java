package com.ie.common.utilities.cmnutils;

import org.junit.Test;

import java.io.File;

public class FileUtilsTest {

    @Test
    public void testCreateFile(){

        File file = new File(ClassLoader.getSystemResource(".").getFile(),"test_directory");
        System.out.println(file.getAbsolutePath());
        boolean result = IEFileUtils.createFile(file, false);
        System.out.println(result);
    }

    @Test
    public void testDeleteFile(){
        File file = new File(ClassLoader.getSystemResource(".").getFile(), "test_directory");
        boolean result = IEFileUtils.deleteFile(file);
        System.out.println(result);
    }


}
