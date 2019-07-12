package com.cxy.util;

import java.security.MessageDigest;

import com.cloopen.rest.sdk.utils.encoder.BASE64Decoder;
import com.cloopen.rest.sdk.utils.encoder.BASE64Encoder;

public class EncodeOrDecodeUtil {

	/**
	 * @Description 字符串加密
	 * @author XianyongChen
	 * @date 2019年7月12日
	 * @param encodeManner 加密方式
	 * @param str          需要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String encode(int encodeManner, String str) {
		String result = "";
		switch (encodeManner) {
		case 1:// MD5
			result = stringEncode("MD5", str);
			break;
		case 2:// SHA1
			result = stringEncode("SHA1", str);
			break;
		case 3:// BASE64
			result = base64Encode(str);
			break;
		default:
			break;
		}
		return result;
	}

	/**
	 * @Description 字符串加密
	 * @author XianyongChen
	 * @date 2019年7月12日
	 * @param encodeManner 加密方式，参考枚举EncodeManner
	 * @param inStr        需要加密的字符串
	 * @return 加密后的字符串(MD5:32位;sha1:40位。
	 */
	static String stringEncode(String encodeManner, String inStr) {

		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance(encodeManner);
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
	static String base64Encode(String str) {
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
	static String decodeBase64Str(String base64Str) {
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
	 * @author : XianyongChen
	 * @data : 2019年7月12日 - 下午4:38:31
	 * @description :加密方式
	 */
	public static enum EncodeManner {
		MD5(1), SHA1(2), BASE64(3);
		int index;

		EncodeManner(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

	}

}
