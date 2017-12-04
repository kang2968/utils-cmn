package com.ie.common.utilities.cmnutils;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 生成随机数工具类
 * @author bradly
 *
 */
public class IERandUtils {
	
	private static String WORD = "RYcUPJXmZoAyiDBwprGvla2ge6xLbHu3ET8dhj0IFCzQ7N5MsV914qSOWknftK";
	
	private IERandUtils() { }
	
	/**
	 * 随机从给定的序列seq中获取length长度的字符串
	 * @param seq
	 * @param length
	 * @return
	 */
	public static String stringRand(final CharSequence seq, final int length){
		if(seq == null || seq.length() == 0 || length <= 0){
			return "";
		}
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < length ; i++){
			sb.append(seq.charAt(rand.nextInt(seq.length())));
		}
		return sb.toString();
	}

	/**
	 * 随机获取length长度的字符串
	 * @param length
	 * @return
	 */
	public static String stringRand(final int length){
		return stringRand(WORD, length);
	}
	
	public static String guidRand(){
		return guidRand(null);
	}
	
	public static String guidRand(final String sid){
		return new Guid(sid).getRand(false);
	}
	
	public static String guidSecureRand(){
		return guidSecureRand(null);
	}
	
	public static String guidSecureRand(final String sid){
		return new Guid(sid).getRand(true);
	}
	
	private static Random myRand;
	private static SecureRandom mySecureRand;
	
	static {
		mySecureRand = new SecureRandom();
		myRand = new Random(mySecureRand.nextLong());
	}
	
	private static class Guid{

		private String l_s_id = "127.0.0.1";
		
		public Guid(String sid){
			if(sid != null){
				l_s_id = sid;
			}
		}
		
		public String getRand(boolean secure) {
			StringBuilder sb = new StringBuilder();
			try {
				long time = System.currentTimeMillis();
				long rand ;
				if (secure) {
					rand = mySecureRand.nextLong();
				} else {
					rand = myRand.nextLong();
				}

				sb.append(l_s_id);
				sb.append(":");
				sb.append(Long.toString(time));
				sb.append(":");
				sb.append(Long.toString(rand));
				
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				md5.update(sb.toString().getBytes());
				
				byte[] array = md5.digest();
				sb.setLength(0);
				for (int j = 0; j < array.length; ++j) {
					int b = array[j] & 0xFF;
					if (b < 0x10)
						sb.append('0');
					sb.append(Integer.toHexString(b));
				}

				return sb.toString();

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
}
