package com.cxy.util;

import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

import com.cloopen.rest.sdk.utils.encoder.BASE64Decoder;
import com.cloopen.rest.sdk.utils.encoder.BASE64Encoder;

public class EncodeOrDecodeUtil {

	/**
	 * @Description 字符串加密
	 * @author XianyongChen
	 * @date 2019年7月12日
	 * @param encodeManner 加密方式，参考枚举EncodeManner
	 * @param inStr        需要加密的字符串
	 * @return 加密后的字符串(MD5:32位;sha1:40位。
	 */
	public static String md5Encode(String inStr) {

		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * @Description BASE64加密
	 * @author XianyongChen
	 * @date 2019年7月12日
	 * @param str 需要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String base64Encode(String str) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(str.getBytes());
	}

	/**
	 * @Description BASE64解密
	 * @author XianyongChen
	 * @date 2019年7月12日
	 * @param base64Str 需要解密的字符串
	 * @return 解密后的字符串
	 */
	public static String base64Decode(String base64Str) {
		String result = "";
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] bytes = decoder.decodeBuffer(base64Str);
			result = new String(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @Description SHA加密
	 * @author XianyongChen
	 * @date 2019年7月12日
	 * @param str 要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String shaEncode(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA");
			mdTemp.update(str.getBytes("UTF-8"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	/**
	 * @Description SHA256加密
	 * @author XianyongChen
	 * @date 2019年7月12日
	 * @param str 要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String String2SHA256(String str) {
		MessageDigest messageDigest;
		String encdeStr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
			encdeStr = Hex.encodeHexString(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encdeStr;
	}

	/**
	 * @Description HMAC加密
	 * @author XianyongChen
	 * @date 2019年7月12日
	 * @param str 要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String hmacEncode(String str) {
		String result = "";
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");// 初始化KeyGenerator
			SecretKey secretKey = keyGenerator.generateKey();// 产生秘钥
			byte[] key = secretKey.getEncoded();// 获得秘钥(默认生成)
			SecretKey secretKey2 = new SecretKeySpec(key, "HmacMD5");// 还原秘钥
			Mac mac = Mac.getInstance(secretKey2.getAlgorithm());// 实例化mac
			// 初始化mac
			mac.init(secretKey);
			byte[] hmacMD5Bytes = mac.doFinal(str.getBytes());
			result = Hex.encodeHexString(hmacMD5Bytes);
		} catch (Exception e) {
		}
		return result;
	}
}
