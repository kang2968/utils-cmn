package com.ie.common.utilities.cmnutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 目录， 文件处理工具类
 * 
 * @author bradly
 * @version 1.0
 *
 */
public class IEFileUtils {

	/**
	 * 默认处理的文件头长度
	 */
	private static final int HEADER_LENGTH = 20;

	private IEFileUtils() {
	}

	/**
	 * 创建文件
	 * 
	 * @param filepath
	 *            文件全路径
	 * @param delete
	 *            当文件已存在时是否删除
	 * @return 当发生错误时返回false
	 */
	public static boolean createFile(final String filepath, final boolean delete) {
		IEValidateUtils.notBlank(filepath, "The filePath can't be blank");
		return createFile(new File(filepath), delete);
	}

	/**
	 * 创建文件
	 * 
	 * @param parentPath
	 *            父目录路径
	 * @param fileName
	 *            文件名
	 * @param delete
	 *            当文件已存在时是否删除
	 * @return 当发生错误时返回false
	 */
	public static boolean createFile(final String parentPath, final String fileName, final boolean delete) {
		IEValidateUtils.notBlank(parentPath, "The parentPath can't be blank");
		IEValidateUtils.notBlank(fileName, "The fileName can't be blank");
		return createFile(new File(parentPath, fileName), delete);
	}

	/**
	 * 创建文件
	 * 
	 * @param file
	 *            目标文件
	 * @param delete
	 *            当文件已存在时是否删除
	 * @return 当发生错误时返回false
	 */
	public static boolean createFile(final File file, final boolean delete) {
		IEValidateUtils.notNull(file, "The file can't be null");
		try {
			if (delete && file.isFile() && file.exists()) {
				file.delete();
			}
			file.getParentFile().mkdirs();
			file.createNewFile();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 *            文件全路径
	 * @return return false when file doesn't exist or file is
	 *         directory.
	 */
	public static boolean deleteFile(final String filePath) {
		IEValidateUtils.notBlank(filePath,"The filePath can't be empty");
		return deleteFile(new File(filePath));
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 * @return return false when file doesn't exist or file is
	 *         directory.
	 */
	public static boolean deleteFile(final File file) {
		IEValidateUtils.notNull(file, "The file can't be null");
		if (!file.exists()) {
			return false;
		}
		if (!file.isFile()) {
			return false;
		}
		try {
			return file.delete();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 保存流到文件中（将自动关闭输入流）
	 * 
	 * @param input
	 *            输入流
	 * @param streamLength
	 *            数据流长度
	 * @param destDirectory
	 *            目标文件父目录
	 * @param destFilename
	 *            目标文件文件名
	 * @param append
	 *            是否追加
	 * @param deleteAfterFailed
	 *            失败后是否删除已保存的文件
	 * @return 当文件写入失败或写入的文件长度与输入流的长度不一致时返回false
	 * @throws IOException
	 */
	public static boolean saveToFile(final InputStream input, final long streamLength, final String destDirectory,
			final String destFilename, final boolean append, final boolean deleteAfterFailed) throws IOException {
		IEValidateUtils.notBlank(destDirectory, "The destDirectory can't be blank");
		IEValidateUtils.notBlank(destFilename, "The destFilename can't be blank");
		return saveToFile(input, streamLength, new File(destDirectory, destFilename), append, deleteAfterFailed);
	}

	/**
	 * 保存流到文件中（将自动关闭输入流）
	 * 
	 * @param input
	 *            输入流
	 * @param streamLength
	 *            数据流长度
	 * @param destPath
	 *            目标文件全路径
	 * @param append
	 *            是否以追加的形式
	 * @param deleteAfterFailed
	 *            当失败后是否删除目标文件
	 * @return 当文件写入失败或写入的文件长度与输入流的长度不一致时返回false
	 * @throws IOException
	 */
	public static boolean saveToFile(final InputStream input, final long streamLength, final String destPath, final boolean append,
			final boolean deleteAfterFailed) throws IOException {
		IEValidateUtils.notBlank(destPath, "The destPath can't be empty");
		return saveToFile(input, streamLength, new File(destPath), append, deleteAfterFailed);
	}

	/**
	 * 保存流到文件中（将自动关闭输入流）
	 * 
	 * @param input
	 *            输入流
	 * @param streamLength
	 *            输入流长度
	 * @param destFile
	 *            目标文件
	 * @param append
	 *            是否以追加的形式
	 * @param deleteAfterFailed
	 *            当失败后是否删除目标文件
	 * @return 当文件写入失败或写入的文件长度与输入流的长度不一致时返回false
	 * @throws IOException
	 */
	public static boolean saveToFile(final InputStream input, final long streamLength, final File destFile, final boolean append,
			final boolean deleteAfterFailed) throws IOException {
		if (streamLength == -1) {
			return false;
		}
		if (!createFile(destFile, !append)) {
			return false;
		}
		long rawLength = destFile.length();
		boolean result = false;
		try (FileOutputStream fos =new FileOutputStream(destFile, append);) {
			byte[] bs = new byte[100 * 1024];
			int len;
			while ((len = input.read(bs)) != -1) {
				fos.write(bs, 0, len);
			}
			fos.flush();
			result = destFile.exists() && destFile.length() > 0 && destFile.length() == rawLength + streamLength;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
				}
			}
			if ((!result) && deleteAfterFailed) {
				try {
					destFile.delete();
				} catch (Exception e) {
				}
			}
		}
		return result;
	}

	/**
	 * 下载文件
	 * 
	 * @param url
	 *            下载文件
	 * @param destDirectory
	 *            目标文件父目录
	 * @param destFilename
	 *            目标文件文件名
	 * @param connectTimeout
	 *            请求等待时长
	 * @param readTimeout
	 *            请求阅读等待时长
	 * @param append
	 *            当文件存在时，是否追加
	 * @param deleteAfterFailed
	 *            当文件保存失败后是否删除目标文件
	 * @return 当文件写入失败时返回false
	 * @throws IOException
	 */
	public static long download(final String url, final String destDirectory, final String destFilename, final int connectTimeout,
			final int readTimeout, final boolean append, final boolean deleteAfterFailed) throws IOException {
		IEValidateUtils.notBlank(destDirectory, "The destDirectory can't be blank");
		IEValidateUtils.notBlank(destFilename, "The destFilename can't be blank");
		return download(url, new File(destDirectory, destFilename), connectTimeout, readTimeout, append,
				deleteAfterFailed);
	}

	/**
	 * 下载文件
	 * 
	 * @param url
	 *            下载文件
	 * @param destPath
	 *            下载后目标文件全路径
	 * @param connectTimeout
	 *            请求等待时长
	 * @param readTimeout
	 *            请求阅读等待时长
	 * @param append
	 *            当文件存在时，是否追加
	 * @param deleteAfterFailed
	 *            当文件保存失败后是否删除目标文件
	 * @return 当文件写入失败时返回false
	 * @throws IOException
	 */
	public static long download(final String url, final String destPath, final int connectTimeout, final int readTimeout, final boolean append,
			final boolean deleteAfterFailed) throws IOException {
		IEValidateUtils.notBlank(destPath, "The destPath can't be blank");
		return download(url, new File(destPath), connectTimeout, readTimeout, append, deleteAfterFailed);
	}

	/**
	 * 下载文件
	 * 
	 * @param url
	 *            文件网络路径
	 * @param destFile
	 *            下载后目标文件
	 * @param connectTimeoutSecond
	 *            请求等待时长
	 * @param readTimeoutSecond
	 *            请求阅读等待时长
	 * @param append
	 *            当文件存在时，是否追加
	 * @param deleteAfterFailed
	 *            当保存失败后是否删除目标文件
	 * @return 当文件写入失败时返回false
	 * @throws IOException
	 */
	public static long download(final String url, final File destFile, final int connectTimeoutSecond, final int readTimeoutSecond, final boolean append,
			final boolean deleteAfterFailed) throws IOException {
		IEValidateUtils.notBlank(url, "The url can't be blank");
		IEValidateUtils.notNull(destFile, "The destFile can't be null");
		HttpURLConnection conn = null;
		boolean result = false;
		long contentLength = -1;
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setConnectTimeout(connectTimeoutSecond * 1000);
			conn.setReadTimeout(readTimeoutSecond * 1000);

			contentLength = conn.getContentLengthLong();
			result = saveToFile(conn.getInputStream(), contentLength, destFile, append, deleteAfterFailed);
		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception e) {
				}
			}
		}
		return result ? contentLength : -1;
	}

	/**
	 * 从文件全路径中获取文件名
	 * 
	 * @param filePath
	 *            文件全路径
	 * @return
	 */
	public static String getFilename(final String filePath) {
		IEValidateUtils.notBlank(filePath, "The filePath can't be blank");
		int index = Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\'));
		return index > 0 ? filePath.substring(index + 1) : filePath;
	}

	/**
	 * 从文件全路径中获取文件后缀
	 * 
	 * @param filePath
	 *            文件全路径
	 * @return
	 */
	public static String getExtensionViaFullFilename(final String filePath) {
		return getExtensionViaFilename(getFilename(filePath));
	}

	/**
	 * 通过文件名获取文件后缀
	 * 
	 * @param filename
	 *            文件名
	 * @return it will be null when extension is not found
	 */
	public static String getExtensionViaFilename(final String filename) {
		IEValidateUtils.notBlank(filename, "The filename can't be blank");
	    String t = null;
		if (t.endsWith(".")) {
			t = t.substring(0, t.length() - 1);
		}
		int index = t.lastIndexOf(".");
		if (index > 0) {
			return t.substring(index + 1);
		}
		return null;
	}

	/**
	 * 分析本地文件头，获取文件真实后缀
	 * 
	 * @param filePath
	 *            本地文件的全路径
	 * @return it will blank string when extension is not found
	 */
	public static String getExtensionViaFileHeader(final String filePath) {
		IEFileHeadTypeEnum type = getTypeLocal(filePath);
		if (type != null) {
			return type.getExtension();
		}
		return "";
	}

	/**
	 * 分析网路文件头， 获取文件真实后缀
	 * 
	 * @param fileUrl
	 *            文件的网络路径
	 * @param connectTimeoutSecond
	 *            网络请求等待时长
	 * @param readTimeoutSecond
	 *            网络响应阅读等待时长
	 * @return
	 */
	public static String getExtensionOnline(final String fileUrl, final int connectTimeoutSecond, final int readTimeoutSecond) {
		IEFileHeadTypeEnum type = getTypeOnline(fileUrl, connectTimeoutSecond, readTimeoutSecond);
		if (type != null) {
			return type.getExtension();
		}
		return "";
	}

	/**
	 * 分析本地文件头， 获取对应的文件头信息
	 * 
	 * @param filePath
	 *            文件的本地全路径
	 * @return
	 */
	public static IEFileHeadTypeEnum getTypeLocal(final String filePath) {
		IEValidateUtils.notBlank(filePath, "The filePath can't be blank");
		byte[] headByte = new byte[HEADER_LENGTH];
		try (InputStream is = new FileInputStream(filePath)) {
			is.read(headByte, 0, HEADER_LENGTH);
		} catch (Exception e) {
			headByte = null;
		}
		return analysisFileType(headByte);
	}

	/**
	 * 分析网络文件文件头， 获取对应的文件头信息
	 * 
	 * @param fileUrl
	 *            文件的网络路径
	 * @param connectTimeoutSecond
	 *            网络请求等待时长
	 * @param readTimeoutSecond
	 *            响应阅读等待时长
	 * @return
	 */
	public static IEFileHeadTypeEnum getTypeOnline(final String fileUrl, final int connectTimeoutSecond, final int readTimeoutSecond) {
		IEValidateUtils.notBlank(fileUrl, "The fileURL can't be blank");
		byte[] headByte = new byte[HEADER_LENGTH];
		HttpURLConnection conn = null;
		InputStream is = null;
		try {
			conn = (HttpURLConnection) new URL(fileUrl).openConnection();
			conn.setConnectTimeout(connectTimeoutSecond * 1000);
			conn.setReadTimeout(readTimeoutSecond * 1000);
			is = conn.getInputStream();
			is.read(headByte, 0, HEADER_LENGTH);
		} catch (Exception e) {
			headByte = null;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
				}
			}
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception e) {
				}
			}
		}
		return analysisFileType(headByte);
	}

	/**
	 * 通过文件头字节组，获取对应的文件头
	 * 
	 * @param heads
	 *            代表文件头的字节数据
	 * @return
	 */
	public static IEFileHeadTypeEnum analysisFileType(final byte[] heads) {
		if (heads == null || heads.length == 0) {
			return null;
		}
		// 将文件头转化为16进制字符串
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < heads.length; i++) {
			int v = heads[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv.toUpperCase());
		}
		String filehead = stringBuilder.toString();
		// 获取文件头类型
		for (IEFileHeadTypeEnum f : IEFileHeadTypeEnum.values()) {
			if (filehead.startsWith(f.getHex())) {
				return f;
			}
		}
		return null;
	}

	/**
	 * 获取文件匹配pattern行的内容
	 * 
	 * @param fileRelativePath
	 *            文件路径
	 * @param lastReadTime
	 *            上一次阅读时间， 如果在这之后没有更新，这返回null
	 * @param pattern
	 *            匹配pattern， 如果获取所有则设置为null
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public static String loadResourceContent(final String fileRelativePath, final Date lastReadTime, final String pattern)
			throws URISyntaxException, IOException {
		try (Stream<String> lines = loadResourceStream(fileRelativePath, lastReadTime)) {
			if (lines == null) {
				return null;
			}
			StringBuilder content = new StringBuilder();
			if (pattern != null) {
				lines.filter(s -> s.matches(pattern)).forEach(s -> content.append(s).append('\n'));
			} else {
				lines.forEach(s -> content.append(s).append('\n'));
			}
			if (content.length() > 0) {
				content.deleteCharAt(content.length() - 1);
			}
			return content.toString();
		}
	}

	/**
	 * 获取文件匹配pattern的每行内容， 内部为linkList
	 * 
	 * @param fileRelativePath
	 * @param lastReadTime
	 *            上一次读取的时间，如果未更新则返回null
	 * @param pattern
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static List<String> loadResourceList(final String fileRelativePath, final Date lastReadTime, final String pattern)
			throws IOException, URISyntaxException {
		try (Stream<String> lines = loadResourceStream(fileRelativePath, lastReadTime)) {
			if (lines == null) {
				return null;
			}
			List<String> result = new LinkedList<>();
			if (pattern != null) {
				lines.filter(s -> s.matches(pattern)).forEach(s -> result.add(s));
			} else {
				lines.forEach(s -> result.add(s));
			}
			return result;
		}
	}

	/**
	 * Note ： 使用完成后释放stream
	 * 
	 * @param fileRelativePath
	 * @param lastReadTime
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static Stream<String> loadResourceStream(final String fileRelativePath, final Date lastReadTime)
			throws IOException, URISyntaxException {
		IEValidateUtils.notBlank(fileRelativePath, "The fileRelativePath can't be blank");
		URL fileUrl = Thread.currentThread().getContextClassLoader().getResource(fileRelativePath);
		if (fileUrl == null) {
			throw new FileNotFoundException();
		}
		Path path = Paths.get(fileUrl.toURI());
		if (lastReadTime != null && Files.getLastModifiedTime(path).toMillis() <= lastReadTime.getTime()) {
			return null;
		}
		return Files.lines(path);
	}

}
