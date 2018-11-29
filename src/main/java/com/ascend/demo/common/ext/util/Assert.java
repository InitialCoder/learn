package com.ascend.demo.common.ext.util;

/**
 * 对象工具类
 * 
 * @author liulibo
 * 
 */
public class Assert {
	/**
	 * 对象不能为null,否则抛IllegalArgumentException异常.message为错误提示信息
	 * 
	 * @param object
	 * @param message
	 */
	public static void notEmpty(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 对象不能为null,否则抛IllegalArgumentException异常.
	 * 
	 * @param object
	 */
	public static void notEmpty(Object object) {
		notEmpty(object, "this argument is required; it must not empty");
	}

	/**
	 * 字符串不能为空或null,否则抛IllegalArgumentException异常.message为错误提示信息
	 * 
	 * @param text
	 * @param message
	 */
	public static void notEmpty(String text, String message) {
		if (text == null || "".equals(text.trim())) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 字符串不能为空或null,否则抛IllegalArgumentException异常.
	 * 
	 * @param text
	 */
	public static void notEmpty(String text) {
		notEmpty(text, "this argument is required; it must not empty");
	}

	/**
	 * 判断字符串是否为空或null,否则返回false
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isEmpty(String text) {
		if (text == null || "".equals(text.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否为空或null,否则返回false
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isEmpty(Number number) {
		if (number == null || number.intValue() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 两个对象是否相等 主要防止null对象的出现，减少代码量
	 * 
	 * @param value1
	 * @param value2
	 * @return boolean
	 */
	public static boolean isEqual(Object value1, Object value2) {
		if(null==value1||null==value2){
			return value1==value2;
		}else{
			return value1.equals(value2);
		}
	}
	
}
