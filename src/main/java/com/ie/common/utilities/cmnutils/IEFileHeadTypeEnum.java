package com.ie.common.utilities.cmnutils;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件十六进制头与文件类型匹配 枚举类
 * @author KANG
 *
 */
public enum IEFileHeadTypeEnum {
	
	/**
     * JEPG.
     */
    JPEG("FFD8FF", "jpeg"),
     
    /**
     * PNG.
     */
    PNG("89504E47", "png"),
     
    /**
     * GIF.
     */
    GIF("47494638", "gif"),
     
    /**
     * WEBP
     */
    WEBP("52494646", "webp"),
    /**
     * TIFF.
     */
    TIFF("49492A00", "tiff"),
     
    /**
     * Windows Bitmap.
     */
    BMP("424D", "bmp"),
     
    /**
     * CAD.
     */
    DWG("41433130", "dwg"),
     
    /**
     * Adobe Photoshop.
     */
    PSD("38425053", "psd"),
     
    /**
     * Rich Text Format.
     */
    RTF("7B5C727466", "rtf"),
     
    /**
     * XML.
     */
    XML("3C3F786D6C", "xml"),
     
    /**
     * HTML.
     */
    HTML("68746D6C3E", "html"),
   
    /**
     * HTML.
     */
    HTML2("3C21646F63", "html"),
     
    /**
     * Email [thorough only].
     */
    EML("44656C69766572792D646174653A", "eml"),
     
    /**
     * Outlook Express.
     */
    DBX("CFAD12FEC5FD746F", "dbx"),
     
    /**
     * Outlook (pst).
     */
    PST("2142444E", "pst"),
     
    /**
     * MS Word/Excel.
     */
    XLS_DOC("D0CF11E0", "xls_doc"),
     
    /**
     * MS Access.
     */
    MDB("5374616E64617264204A", "mdb"),
     
    /**
     * WordPerfect.
     */
    WPD("FF575043", "pwd"),
     
    /**
     * Postscript.
     */
    EPS("252150532D41646F6265", "eps"),
     
    /**
     * Adobe Acrobat.
     */
    PDF("255044462D312E", "pdf"),
     
    /**
     * Quicken.
     */
    QDF("AC9EBD8F", "qdf"),
     
    /**
     * Windows Password.
     */
    PWL("E3828596", "pwl"),
     
    /**
     * ZIP Archive.
     */
    ZIP("504B03040A", "zip"),
    
    /**
     * XLXS, DOCX
     */
    XLXS("504B030414", "xlxs_docx"),
    
    /**
     * RAR Archive.
     */
    RAR("526172211A", "rar"),
     
    /**
     * Wave.
     */
    WAV("57415645", "wav"),
     
    /**
     * AVI.
     */
    AVI("41564920", "avi"),
     
    /**
     * Real Audio.
     */
    RAM("2E7261FD", "ram"),
     
    /**
     * Real Media.
     */
    RM("2E524D46", "rm"),
     
    /**
     * MPEG (mpg).
     */
    MPG("000001BA", "mpg"),
    /**
     * MPEG (mpg).
     */
    MPEG("000001B3", "mpeg"),
     
    /**
     * Quicktime.
     */
    MOV("6D6F6F76", "mov"),
     
    /**
     * Windows Media.
     */
    ASF("3026B2758E66CF11", "asf"),
     
    /**
     * MIDI.
     */
    MID("4D546864", "mid")
	
    ;
	
	private String hex;
	
	private String extension;
	
	IEFileHeadTypeEnum(String hex, String extension){
		this.hex = hex;
		this.extension = extension;
	}
	
	private static Map<String , IEFileHeadTypeEnum> lookups = new HashMap<>();
	
	static{
    	for(IEFileHeadTypeEnum f : IEFileHeadTypeEnum.values()){
    		lookups.put(f.getHex(), f);
    	}
    }
	
	public static IEFileHeadTypeEnum getType(String hex){
		return lookups.get(hex);
	}
	
	public String getHex() {
		return hex;
	}

	public String getExtension() {
		return extension;
	}
}
