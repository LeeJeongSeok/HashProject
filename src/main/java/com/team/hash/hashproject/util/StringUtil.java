package com.team.hash.hashproject.util;

import java.text.DecimalFormat;

import org.json.JSONException;
import org.json.JSONObject;


public class StringUtil {
	public static String replaceNull(String str) {
		return (str != null) ? str : "";
	}

	public static String replaceNullForServlet(String str) {
		String result = "";
		if(str != null) {
			result = str.replaceAll("'", "''");
		}
		return result;
	}
	public static String replaceNullForDB(String str) {
		String result = "";
		if(str != null) {
			result = str.replaceAll("''", "'");
		}
		return result;
	}

	public static int getStringNumber(String str) {
		int number = 0;
		if(str == null || str.length() == 0) {
			return number;
		}
		String temp = getOnlyDigitNumber(str);
		return Integer.parseInt(temp);
	}

	public static String getNumberStringTo10(int number) {
		String str = "";
		if(number < 10) {
			str = "0" + number;
		} else {
			str = String.valueOf(number);
		}
		return str;
	}
	
	public static String getOnlyDigitNumber(String str) {
		String result = "";
		char charInput = 0x00;
		int size = str.length();
		if(size > 1 && str.charAt(0) == '-') {
			result += '-';
		}
		for (int i = 0; i < size; i++) {
			charInput = str.charAt(i);
			if(charInput >= 0x30 && charInput <= 0x39) {
			    result += charInput;
			}
		}
		return result;
	}
	
	public static String getPhoneNumberFormat(String phoneNumber, boolean isMask) {
		String resultPhoneNumber = "";
		phoneNumber = phoneNumber.replaceAll("-", "");

		int size = phoneNumber.length();
		if (size == 11) {
			if (isMask) {
				resultPhoneNumber = phoneNumber.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-****-$3");
			} else {
				resultPhoneNumber = phoneNumber.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
			}
		} else if (size == 8) {
			resultPhoneNumber = phoneNumber.replaceAll("(\\d{4})(\\d{4})", "$1-$2");
		} else {
			if(phoneNumber.indexOf("02") == 0) {
				if (isMask) {
					resultPhoneNumber = phoneNumber.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1-****-$3");
				} else {
					resultPhoneNumber = phoneNumber.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1-$2-$3");
				}
			} else {
				if (isMask) {
					resultPhoneNumber = phoneNumber.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-****-$3");
				} else {
					resultPhoneNumber = phoneNumber.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
				}
			}
		}
		return resultPhoneNumber;
	}
	
	public static boolean isEmpty(String str) {
		if(str == null || str.length() == 0) {
			return true;
		}
		return false;
	}
	
	public static String getValidNoArray(String str) {
		if(!StringUtil.isEmpty(str) && str.endsWith(",")) {
			String temp = str.substring(0, str.length() - 1);
			str = temp;
		}
		return str;
	}
	
	public static String getPriceFormat(int price) {
		DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
		return decimalFormat.format(price);
	}
	public static String getPriceFormat(long price) {
		DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
		return decimalFormat.format(price);
	}
	
	public static boolean isOnlyDegitNumber(String str) {
		boolean isResult = true;
		char charInput = 0x00;
		int length = str.length();
		for(int i=0; i<length; i++) {
			charInput = str.charAt(i);
			if(charInput < 0x30 || charInput > 0x39) {
			    isResult = false;
				break;
			}
		}
		return isResult;
	}
	
	public static String getFailJSONString() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("isConfirm", "N");
		} catch (JSONException e) {
		}
		return jsonObject.toString();
	}
}
