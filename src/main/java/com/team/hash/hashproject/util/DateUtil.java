package com.team.hash.hashproject.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class DateUtil {
	public static String getTodayDate(String format) {
		 SimpleDateFormat formatter = new SimpleDateFormat(format);
		 String today = formatter.format(new Date());
		 return today;
	}
	
	public static String getDisplayDate(String datetime) {
		String result = "";
		if(datetime != null && datetime.length() > 10 && !datetime.startsWith("1970-01-01")) {
			result = datetime.substring(0, 10);
		}
		return result;
	}
	
	public static String getDisplayDateTime(String datetime) {
		String result = "";
		if(datetime != null && datetime.length() > 16 && !datetime.startsWith("1970-01-01")) {
			result = datetime.substring(0, 16);
		}
		return result;
	}
	
	public static String getDisplayDateTimeSecond(String datetime) {
		String result = "";
		if(datetime != null && datetime.length() > 19) {
			if(datetime.startsWith("1970")) {
				result = "1970-01-01 00:00:00";
			} else {
				result = datetime.substring(0, 19);
			}
		} else {
			result = "1970-01-01 00:00:00";
		}
		return result;
	}
	
	public static String getPrevDate(int subDay) {
		String date = "";
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, subDay * -1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		date = formatter.format(calendar.getTime());
		return date;
	}
	
	public static String getPrevMonth(String year, String month, String day, int subMonth) {
		int tempMonth = Integer.parseInt(month);
		int resultMonth = tempMonth - subMonth;
		if(resultMonth < 1) {
			resultMonth = 13 - subMonth;
			int yearTemp = Integer.parseInt(year) - 1;
			year = String.valueOf(yearTemp);
		}
		month = resultMonth < 10 ? ("0" + resultMonth) : String.valueOf(resultMonth);
		return year + "-" + month + "-" + day;
	}
	
	public static String getPrevDate(String originalDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date = null;
		try {
			date = simpleDateFormat.parse(originalDate);
		} catch(ParseException e) {
		}
		
		if(date != null) {
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -1);
		}
		String prevDate = simpleDateFormat.format(calendar.getTime());
		return prevDate;
	}
	public static String getNextDate(String originalDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date = null;
		try {
			date = simpleDateFormat.parse(originalDate);
		} catch(ParseException e) {
		}
		
		if(date != null) {
			calendar.setTime(date);
			calendar.add(Calendar.DATE, 1);
		}
		String nextDate = simpleDateFormat.format(calendar.getTime());
		return nextDate;
	}
	public static String getNextDate(int subDay) {
		String date = "";
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, subDay);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		date = formatter.format(calendar.getTime());
		return date;
	}
	public static String getNextMonth(String year, String month, String day, int subMonth) {
		int tempMonth = Integer.parseInt(month);
		int resultMonth = tempMonth + subMonth;
		if(resultMonth > 12) {
			resultMonth = resultMonth - 12;
			int yearTemp = Integer.parseInt(year) + 1;
			year = String.valueOf(yearTemp);
		}
		month = resultMonth < 10 ? ("0" + resultMonth) : String.valueOf(resultMonth);
		return year + "-" + month + "-" + day;
	}
	
	public static long getDiffTimeForMiliSecond(String prevDate, String behindDate) throws ParseException {
		long prevTime = getDate("yyyy-MM-dd HH:mm:ss", prevDate).getTime();
		long nowTime = getDate("yyyy-MM-dd HH:mm:ss", behindDate).getTime();
		long diffTime = (nowTime - prevTime) / 1000;
		return diffTime;
	}
	
	public static String getDiffTime(long currentTime, long targetTime) {
		long diffTime = (currentTime - targetTime) / 1000;
		
		String result = "";
		if(diffTime < 60) {
			result = diffTime + "초전";
		} else if((diffTime /= 60) < 60) {
			result = diffTime + "분전";
		} else if((diffTime /= 60) < 24) {
			result = diffTime + "시간전";
		} else if((diffTime /= 24) < 30) {
			result = diffTime + "일전";
		} else if((diffTime /= 30) < 12) {
			result = diffTime + "달전";
		} else {
			if(diffTime < 10) {
				result = diffTime + "년전";
			} else {
				result = "";
			}
		}
		return result;
	}

	public static String getDiffTimeForTimer(SimpleDateFormat simpleDateFormat, String strStartDate, String strEndDate) throws ParseException {
		long startTime = simpleDateFormat.parse(strStartDate).getTime();
		long endTime = simpleDateFormat.parse(strEndDate).getTime();
		
		long diffTime = (endTime - startTime) / 1000;
		
		int hour = (int)TimeUnit.MILLISECONDS.toHours(diffTime);
		int minute = (int)TimeUnit.MILLISECONDS.toMinutes(diffTime);
		int second = (int)TimeUnit.MILLISECONDS.toSeconds(diffTime);
		
		String result = (hour<10 ? ("0"+hour):(""+hour))+":"+(minute<10 ? ("0"+minute):(""+minute))+":"+(second<10 ? ("0"+second):(""+second));
		return result;
	}
	
	public static Date getDate(String format, String strTargetDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = formatter.parse(strTargetDate);
		return date;
	}
	
	public static String getDiffTime(String format, long currentTime, String strTargetDate) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date date = simpleDateFormat.parse(strTargetDate);
		long targetTime = date.getTime();
		return getDiffTime(currentTime, targetTime);
	}
	
	public static String getDiffTime(SimpleDateFormat simpleDateFormat, long currentTime, String strTargetDate) throws ParseException {
		Date date = simpleDateFormat.parse(strTargetDate);
		long targetTime = date.getTime();
		return getDiffTime(currentTime, targetTime);
	}
	
	public static String getWeekDay(SimpleDateFormat simpleDateFormat, String targetDate) throws ParseException {
		String strDay = "";
		Date date = simpleDateFormat.parse(targetDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayNumber = calendar.get(Calendar.DAY_OF_WEEK);
		if(dayNumber == 1) {
			strDay = "SUN";
		} else if(dayNumber == 2) {
			strDay = "MON";
		} else if(dayNumber == 3) {
			strDay = "TUE";
		} else if(dayNumber == 4) {
			strDay = "WED";
		} else if(dayNumber == 5) {
			strDay = "TUR";
		} else if(dayNumber == 6) {
			strDay = "FRI";
		} else if(dayNumber == 7) {
			strDay = "SAT";
		}
		return strDay;
	}
	
	public static String getWeekOfYearMonth() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		String date = simpleDateFormat.format(new Date());
	    Calendar calendar = Calendar.getInstance();
	    String[] dates = date.split("-");
	    int year = Integer.parseInt(dates[0]);
	    int month = Integer.parseInt(dates[1]);
	    int day = Integer.parseInt(dates[2]);
	    calendar.set(year, month - 1, day);
	    int weekCount = calendar.get(Calendar.WEEK_OF_MONTH);
	    return "" + year + (month < 10 ? ("0" + month) : month) + weekCount;
	}
	
	public static int getWeekOfMonth() {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		int dayNumber = calendar.get(Calendar.DAY_OF_WEEK);
		int weekOfMonth = 0;
		if(dayNumber == 1) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
			String strDate = simpleDateFormat.format(date);
			String[] dates = strDate.split("-");
			int year = Integer.parseInt(dates[0]);
		    int month = Integer.parseInt(dates[1]);
		    int day = Integer.parseInt(dates[2]);
		    if(day == 1) {
		    	if(month == 1) {
		    		month = 12;
		    		year -= 1;
		    	} else {
		    		month -= 1;
		    	}
		    	calendar.set(year, month-1, 1);
				int dayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				
				calendar.set(year, month-1, dayOfMonth);
				weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
		    } else {
		    	day -= 1;
		    	calendar.set(year, month-1, day);
				weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
		    }
		    
		    calendar.set(year, month-1, day);
			weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
		} else {
			weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
		}
		return weekOfMonth;
	}
	
	public static int getFirstDayOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, 1);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	public static int getLastWeekOfMonth(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, day);
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}
	
	public static int getDayOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, 1);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public static String getFirstWeekDay(String strDate, String format) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date date = simpleDateFormat.parse(strDate);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1- calendar.get(Calendar.DAY_OF_WEEK));
		return simpleDateFormat.format(calendar.getTime());
	}
	
//	public static boolean isTodayWeek(String strDate) throws ParseException {
//		String dateString="2020-09-25";
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = simpleDateFormat.parse(dateString);
//		
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//
//		System.out.println("입력한 날짜:"+sdf.format(calendar.getTime()));
//		calendar.add(Calendar.DATE, 1- cal.get(Calendar.DAY_OF_WEEK));
//
//		System.out.println("일요일 날짜:"+sdf.format(calendar.getTime()));
//
//		calendar.setTime(date);
//
//		calendar.add(Calendar.DATE, 7 - cal.get(Calendar.DAY_OF_WEEK));
//		System.out.println("토요일 날짜:"+sdf.format(calendar.getTime()));
//	}
	
	public static Calendar parseDateTime(String str) {
		Calendar calendar = Calendar.getInstance();
		int year = Integer.parseInt(str.substring(0, 4));
		int month = Integer.parseInt(str.substring(5, 7));
		int day = Integer.parseInt(str.substring(8, 10));
		int hour = Integer.parseInt(str.substring(11, 13));
		int minute = Integer.parseInt(str.substring(14, 16));
		int second = 0;
		calendar.set(year, month - 1, day, hour, minute, second);
		return calendar;
	}

	public static boolean isPeriodDateTime(String startTime, String endTime, String nowTime) {
		Calendar startCalendar = parseDateTime(startTime);
		Calendar endCalendar = parseDateTime(endTime);
		Calendar nowCalendar = parseDateTime(nowTime);
		boolean isValid = false;
		if(startCalendar.before (nowCalendar) && endCalendar.after(nowCalendar)) {
			isValid = true;
		}
		return isValid;
	}
	
	public static String getWeekStartDay(String strDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try{
			date = simpleDateFormat.parse(strDate);
		} catch(ParseException e) {
		}
		
		Calendar calendar = Calendar.getInstance(Locale.KOREA);
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1 - calendar.get(Calendar.DAY_OF_WEEK));
		String result = simpleDateFormat.format(calendar.getTime());
		return result;
	}
	
	public static String getWeekEndDay(String strDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try{
			date = simpleDateFormat.parse(strDate);
		} catch(ParseException e) {
		}
		
		Calendar calendar = Calendar.getInstance(Locale.KOREA);
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 7 - calendar.get(Calendar.DAY_OF_WEEK));
		String result = simpleDateFormat.format(calendar.getTime());
		return result;
	}
}
