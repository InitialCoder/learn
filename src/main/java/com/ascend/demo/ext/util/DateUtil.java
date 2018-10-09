package com.ascend.demo.ext.util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/** 
 *
 * @ClassName: DateUtil 
 * @Description: 日期工具类
 * @author liulibo 
 * @date 2018年1月24日 下午3:03:21 
 *
 */	
public class DateUtil {

	/**
	 * 日期与时间
	 */
	public final static String BOTH = "yyyy-MM-dd HH:mm:ss";
	/** 
	 *
	 * @Fields  BOTH_MILLS :  包含毫秒的时间格式
	 *
	 */ 
	public final static String BOTH_MILLS = "yyyy-MM-dd HH:mm:ss:SSS";
	/**
	 * 日期与时间(用于udesk查询)
	 */
	public final static String BOTH_UDESK = "yyyyMMddHHmmss";
	/**
	 * 日期
	 */
	public final static String DATE = "yyyy-MM-dd";
	/**
	 * 时间
	 */
	public final static String TIME = "HH:mm:ss";
	/**
	 * 时分格式 如 12:30
	 */
	public final static String TMS = "HH:mm";
	/**
	 * 一日的毫秒数
	 */
	public static long MILLION_SECONDS_OF_DAYS = 24 * 60 * 60 * 1000L;// 86400000
	/**
	 * 
	 * 一小时的毫秒数
	 */
	public static long MILLION_SECONDS_OF_HOURS = 60 * 60 * 1000L;// 3600000;
	public static long MILLION_SECONDS_OF_MINUTES = 60 * 1000L;
	public static long MILLION_SECONDS = 1000L;

	private DateUtil() {

	}

	public static String currentDate() {
		return format(new Date(), DATE);
	}

	public static String currentTime() {
		return format(new Date(), TIME);
	}

	public static String currentDateTime() {
		return format(new Date(), BOTH);
	}
	public static String currentDateTimeMills() {
		return format(new Date(), BOTH_MILLS);
	}
	/** 
	 *
	 * @Title:  getLastDayOfMonth 
	 * @Description: 获取给定日期对应月份的最后一天
	 * @param dateString
	 * @return String     
	 *
	 */
	public static String getLastDayOfMonth(String dateString){
        Calendar cale = Calendar.getInstance();   
        if(!Assert.isEmpty(dateString)){
			cale.setTime(parse(dateString, DATE));
		}
        cale.set(Calendar.DAY_OF_MONTH,cale.getActualMaximum(Calendar.DAY_OF_MONTH)); 
        return format(cale.getTime(),DATE);
	}
	/** 
	 *
	 * 获取给定日期对应月份的第一天
	 *
	 */
	public static String getFirstDayOfMonth(String dateString){
		Calendar cale = Calendar.getInstance();   
        if(!Assert.isEmpty(dateString)){
			cale.setTime(parse(dateString, DATE));
		}
        cale.set(Calendar.DAY_OF_MONTH,1); 
        return format(cale.getTime(),DATE);
	}
	/** 
	 *
	 * 获取给定时间所在星期的周日的日期
	 * @return String     
	 *
	 */
	public static String getLastDayOfWeek(String dateString){
		Calendar cale = Calendar.getInstance();   
		if(!Assert.isEmpty(dateString)){
			cale.setTime(parse(dateString, DATE));
		}
        int d = 0;
        if (cale.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cale.get(Calendar.DAY_OF_WEEK);
        }
        // 所在周开始日期
        cale.add(Calendar.DAY_OF_WEEK, d);
        // 所在周结束日期
        cale.add(Calendar.DAY_OF_WEEK, 6);
		return format(cale.getTime(),DATE);
	}
	/** 
	 *
	 * @Title:  getFirstDayOfWeek 
	 * @Description: 获取给定时间所有星期的周一的日期
	 * @param dateString
	 * @return String     
	 *
	 */
	public static String getFirstDayOfWeek(String dateString){
		Calendar cale = Calendar.getInstance();   
		if(!Assert.isEmpty(dateString)){
			cale.setTime(parse(dateString, DATE));
		}
        int d = 0;
        if (cale.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cale.get(Calendar.DAY_OF_WEEK);
        }
        // 所在周开始日期
        cale.add(Calendar.DAY_OF_WEEK, d);
		return format(cale.getTime(),DATE);
	}
	/** 
	 *
	 * @Title:  获取上月的第一天和最后一天 
	 * @Description: 根据当前时间获取上个月的第一天与最后一天，以 英文逗号(,)隔开
	 * @param dateString 日期，若为空，用当前日期
	 * @return String     2018-04-01,2018-04-30
	 *
	 */
	public static String getSEDay4Month(String dateString){
        Calendar cale = Calendar.getInstance();   
        if(!Assert.isEmpty(dateString)){
			cale.setTime(parse(dateString, DATE));
		}
        //获取前月的最后一天
        // cale.set(Calendar.DAY_OF_MONTH,1);
        // 第二参数为 0 自动变为前月
        cale.set(Calendar.DAY_OF_MONTH,0); 
        String lastDay = format(cale.getTime(),DATE);
        
        //获取前月的第一天
        cale.set(Calendar.DAY_OF_MONTH,1);
        String firstDay = format(cale.getTime(),DATE);
        return firstDay+","+lastDay;
	}
	/** 
	 *
	 * @Title:  isFirstDayOfMonth 
	 * @Description: 根据日期判断是否是月初第一天
	 * @param dateString 日期，若为空，用当前日期
	 * @return Boolean     
	 *
	 */
	public static Boolean isFirstDayOfMonth(String dateString){
		Calendar cale = Calendar.getInstance();  
		if(!Assert.isEmpty(dateString)){
			cale.setTime(parse(dateString, DATE));
		}
		cale.set(Calendar.DAY_OF_MONTH,1);
		String firstDay = format(cale.getTime(),DATE);
		return firstDay.equals(Assert.isEmpty(dateString)?currentDate():dateString);
	}
	/** 
	 *
	 * @Title:  currentDateTime4Udesk 
	 * @Description: udesk插件通话详情查询用时间获取
	 * @return String     
	 *
	 */
	public static String currentDateTime4Udesk() {
		return format(new Date(), BOTH_UDESK);
	}
	
	/** 
	 *
	 * @Title:  currentTime4Udesk 
	 * @Description: 用于udesk插件获取当前时间unix值（当前时间毫秒/1000）
	 * @return String     
	 *
	 */
	public static String currentTime4Udesk() {
		return String.valueOf(System.currentTimeMillis()/1000);
	}

	public static String currentHM() {
		return format(new Date(), TMS);
	}

	/**
	 * 是否有效日期
	 * 
	 * @param str
	 * @param fmt
	 * @return boolean
	 */
	public static boolean isValidDate(String str, String fmt) {
		Date date = parse(str, fmt);
		if (date == null) {
			return false;
		}
		String dateStr = format(date, fmt);
		if (dateStr.equals(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 解析日期字符串
	 * 
	 * @param str
	 *            日期串
	 * @param fmt
	 *            日期格式
	 */
	public static Date parse(String str, String fmt) {
		Assert.notEmpty(str);
		Assert.notEmpty(fmt);
		SimpleDateFormat simDateFormat = new SimpleDateFormat(fmt);
		Date date = null;
		try {
			date = simDateFormat.parse(str);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return date;
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 *            日期
	 * @param fmt
	 *            日期格式
	 */
	public static String format(Date date, String fmt) {
		Assert.notEmpty(date);
		Assert.notEmpty(fmt);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fmt);
		return simpleDateFormat.format(date);
	}

	/**
	 * 计算日期加月
	 * 
	 * @param date
	 * @param months
	 * 
	 */
	public static Date addMonth(Date date, int months) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, months);
		return c.getTime();
	}
	public static String addMonth(String str, int months, String fmt) {
		Calendar c = Calendar.getInstance();
		c.setTime(parse(str, fmt));
		c.add(Calendar.MONTH, months);
		Date date = c.getTime();
		return format(date, fmt);
	}
	public static String addWeek(String str, int week, String fmt) {
		Calendar c = Calendar.getInstance();
		c.setTime(parse(str, fmt));
		c.add(Calendar.WEEK_OF_MONTH, week);
		Date date = c.getTime();
		return format(date, fmt);
	}

	/**
	 * 计算日期加小时
	 * 
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date addHour(Date date, int hour) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, hour);
		return c.getTime();
	}

	/** 
	 *
	 * @Title:  getWeek 
	 * @Description: 判断当前日期的星期 
	 * @return int     1-7
	 * @throws 
	 *
	 */
	public static int getWeek() {
		GregorianCalendar ca = new GregorianCalendar();
		return ca.get(7) - 1 == 0 ? 7 : ca.get(7) - 1;
	}
	
	/** 
	 *
	 * @Title:  getWeek 
	 * @Description: 判断给定日期的星期
	 * @param date
	 * @return int     1-7
	 * @throws 
	 *
	 */
	public static int getWeek(String date) {
		Date parse = parse(date, DATE);
		GregorianCalendar ca = new GregorianCalendar();
		ca.setTime(parse);
		return ca.get(7) - 1 == 0 ? 7 : ca.get(7) - 1;
	}

	/**
	 * 计算日期加天数
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDay(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}

	/**
	 * 计算日期加天数
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static String addDay(String str, int days, String fmt) {
		Calendar c = Calendar.getInstance();
		c.setTime(parse(str, fmt));
		c.add(Calendar.DAY_OF_MONTH, days);
		Date date = c.getTime();
		return format(date, fmt);
	}
	/**
	 * 计算日期加天数（专用于单据查询的时间）
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static String addDay4Query(String str, int days, String fmt) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(parse(str, fmt));
	    c.add(Calendar.DAY_OF_MONTH, days);
	    Date date = c.getTime();
	    return format(date, "yyyy/MM/dd");
	}

	/**
	 * 计算日期加分钟
	 * 
	 * @param date
	 * @param minutes
	 * @return
	 */
	public static Date addMinutes(Date date, int minutes) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, minutes);
		return c.getTime();
	}

	public static String addMinutes(String str, int minutes, String type) {
		Calendar c = Calendar.getInstance();
		c.setTime(parse(str, type));
		c.add(Calendar.MINUTE, minutes);
		return format(c.getTime(), type);
	}

	/** 
	 *
	 * @Title:  between 
	 * @Description: 用于判断给定时间是否在给定时间范围内，不含首尾边界
	 * @param now
	 * @param stime
	 * @param etime
	 * @param type
	 * @return boolean     
	 * @throws 
	 *
	 */
	public static boolean between(String now, String stime, String etime,
			String type) {
		if(Assert.isEmpty(stime) || Assert.isEmpty(etime)){
			return false;
		}
		return parse(now, type).after(parse(stime, type))
				&& parse(now, type).before(parse(etime, type));

	}
	/**
	 * 
	 *
	 * @Title:  timeBetween 
	 * @Description: 用于判断给定时间是否在给定时间范围内，含首尾边界
	 * @param now
	 * @param stime
	 * @param etime
	 * @param type
	 * @return boolean     
	 * @throws 
	 *
	 */
	public static boolean timeBetween(String now, String stime, String etime,
			String type) {
		if(Assert.isEmpty(stime) || Assert.isEmpty(etime)){
			return false;
		}
		return isSame(now, stime, type) || isSame(now, etime, type)
				|| parse(now, type).after(parse(stime, type)) && parse(now, type).before(parse(etime, type));

	}

	/**
	 * 计算天数差
	 * 
	 * @param sd
	 * @param ed
	 * @return
	 */
	public static int subDateDays(Date sd, Date ed, long type) {
		Assert.notEmpty(sd);
		Assert.notEmpty(ed);
		Long eds = ed.getTime();
		Long sds = sd.getTime();
		return (int) ((eds - sds) / type);
	}

	/**
	 * 计算天数差
	 * 
	 * @param sd
	 * @param ed
	 * @return
	 */
	public static int subDateDays(String sd, String ed, long type) {
		if (Assert.isEmpty(ed) || Assert.isEmpty(sd)) {
			return 0;
		}
		Long eds = parse(ed, DATE).getTime();
		Long sds = parse(sd, DATE).getTime();
		return (int) ((eds - sds) / type);
	}

	public static int subDatePars(String sd, String ed, long type) {
		if (Assert.isEmpty(ed) || Assert.isEmpty(sd)) {
			return 0;
		}
		Long eds = parse(ed.trim(), BOTH).getTime();
		Long sds = parse(sd.trim(), BOTH).getTime();
		return (int) ((eds - sds) / type);
	}
	
	public static int subDate(String sd, String ed, long type) {
		if (Assert.isEmpty(ed) || Assert.isEmpty(sd)) {
			return 0;
		}
		Long eds = parse(ed, TMS).getTime();
		Long sds = parse(sd, TMS).getTime();
		return (int) ((eds - sds) / type);
	}
	
	
	public static float subDates(String sd, String ed, long type) {
		if (Assert.isEmpty(ed) || Assert.isEmpty(sd)) {
			return 0;
		}
		Long eds = parse(ed, TMS).getTime();
		Long sds = parse(sd, TMS).getTime();
		return (eds - sds) / (float)type;
	}

	/**
	 * 判断某段时间是否在指定的时间段内(包含开始时间等于指定时间段的结束时间)
	 * 
	 * @param begin
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @param tBegin
	 *            开始时间段
	 * @param tEnd
	 *            结束时间段
	 * @param fmt
	 * @return
	 */
	public static boolean isContain(String begin, String end, String tBegin,
			String tEnd, String fmt) {
		long beginTime = parse(begin, fmt).getTime();
		long endTime = parse(end, fmt).getTime();
		long bTime = parse(tBegin, fmt).getTime();
		long eTime = parse(tEnd, fmt).getTime();
		if (bTime > endTime || eTime <= beginTime) {
			return false;
		}
		return true;
	}

	/**
	 * 将日期字符串,转换成XMLGregorianCalendar对象
	 * 
	 * @param str
	 *            日期字符串
	 * @param fmt
	 *            日期格式
	 * @return
	 */
	public static XMLGregorianCalendar toXMLGC(String str, String fmt) {
		GregorianCalendar cal = new GregorianCalendar();
		XMLGregorianCalendar gc = null;
		try {
			Date date = parse(str, fmt);
			cal.setTime(date);
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gc;
	}

	/**
	 * 
	 * @Title: isSame
	 * @Description: 开始时间是否和结束时间相同
	 * @param start
	 * @param end
	 * @param fmt
	 * @return boolean
	 * @throws
	 * 
	 */
	public static boolean isSame(String start, String end, String fmt) {
		boolean flag = false;
		flag = Assert.isEmpty(start);
		flag = Assert.isEmpty(end);
		if (flag)
			return false;
		return parse(start, fmt).equals(parse(end, fmt));
	}

	/**
	 * 
	 * @Title: isAfter
	 * @Description: 开始时间是在结束时间之后
	 * @param start
	 * @param end
	 * @param fmt
	 * @return boolean
	 * @throws
	 * 
	 */
	public static boolean isAfter(String start, String end, String fmt) {
		boolean flag = false;
		flag = Assert.isEmpty(start);
		flag = Assert.isEmpty(end);
		
		if (flag)
			return false;
		return parse(start, fmt).after(parse(end, fmt));
	}

	/** 
	 *
	 * @Title:  marginTime 
	 * @Description: 时间段合并，用于排班处工作时间段处理
	 * @param sList
	 * @param eList
	 * @param fmt
	 * @return String     
	 * @throws 
	 *
	 */
	public static String marginTime(List<String> sList, List<String> eList,
			String fmt) {
		Collections.sort(sList);
		Collections.sort(eList);
		for (int i = 1; i < sList.size(); i++) {
			String sp = sList.get(i - 1);
			String sn = sList.get(i);
			if (sp.compareTo(sn) <= 0 && sn.compareTo(eList.get(i - 1)) <= 0) {
				if (eList.get(i).compareTo(eList.get(i - 1)) >= 0) {
					eList.remove(i - 1);
				}
				sList.remove(i);
				i--;
			}
		}
		String result = "";
		for (int i = 0; i < sList.size(); i++) {
			result += sList.get(i) + "~" + eList.get(i)+",";
		}
		return result.substring(0,result.length()-1);
	}

	/**
	 * 将XMLGregorianCalendar日期字符串转换成java.util.Date对象
	 * 
	 * @param str
	 * @return
	 */
	public static Date toDate(String str) {
		return DatatypeConverter.parseDate(str).getTime();
	}
	/**
	 * 获取两个日期之间的日期
	 * @param start 开始日期
	 * @param end 结束日期
	 * @return 日期集合
	 */
	public static List<Date> getBetweenDates(Date start, Date end) {
	    List<Date> result = new ArrayList<Date>();
	    Calendar tempStart = Calendar.getInstance();
	    tempStart.setTime(start);
	    tempStart.add(Calendar.DAY_OF_YEAR, 1);
	    
	    Calendar tempEnd = Calendar.getInstance();
	    tempEnd.setTime(end);
	    while (tempStart.before(tempEnd)) {
	        result.add(tempStart.getTime());
	        tempStart.add(Calendar.DAY_OF_YEAR, 1);
	    }
	    return result;
	}
	private static StringBuffer sb = new StringBuffer();
	private static Integer millis=0;
	private static Integer temp=0;
	
	public static String getZHTime(String seconds){
		if(Assert.isEmpty(seconds))return null;
		sb.setLength(0);
		millis=0;
		temp=0;
		try{
			millis = Integer.parseInt(seconds);
		}catch (Exception e) {}
		temp = millis / 3600;
		sb.append((temp > 0) ? temp + "时" : "");
		temp = millis % 3600 / 60;
        sb.append((temp > 0) ? temp + "分" : "");
        temp = millis % 60;
        sb.append((temp > 0) ? temp + "秒" : "");
        return sb.toString();
	}
	
	/**
	 * 
	 * @Title: getRelWorkDay
	 * @Description: 用于匹配工作日
	 * @param str
	 *            类似{"1","2","3","4"}
	 * @return Map<Integer,Boolean>
	 * @throws
	 * 
	 */
	public static Map<Integer, Boolean> getRelWorkDay(String... str) {
		Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		map.put(1, false);
		map.put(2, false);
		map.put(3, false);
		map.put(4, false);
		map.put(5, false);
		map.put(6, false);
		map.put(7, false);
		for (String s : str) {
			if (map.containsKey(Integer.parseInt(s))) {
				map.put(Integer.parseInt(s), true);
			}
		}
		return map;
	}
}
