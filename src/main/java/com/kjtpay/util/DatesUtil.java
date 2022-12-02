package com.kjtpay.util;

import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;
@SuppressWarnings("unused")
public class DatesUtil {
	public static java.time.format.DateTimeFormatter FORMATTER = java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	public static final DateTimeFormatter longFormat	  = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	public static final DateTimeFormatter format	      = DateTimeFormat.forPattern("yyyy-MM-dd");
	public static final DateTimeFormatter formatPoint	  = DateTimeFormat.forPattern("yyyy.MM.dd");
	public static final DateTimeFormatter formatNoPoint	  = DateTimeFormat.forPattern("yyyyMMdd");
	public static final DateTimeFormatter monthFormat	  = DateTimeFormat.forPattern("yyyyMM");
	public static final DateTimeFormatter quarterFormat	= DateTimeFormat.forPattern("yyyy\'Q\'M");
	public static String DEFAULT_DAY_HOUR_MINUTE_SECOND = "01000000";
	public static String DEFAULT_HOUR_MINUTE_SECOND = "000000";
	public static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/** 获取间隔几天后的 0时0分0秒0毫秒 */
	public static final Date getDateAfterDayNumber(int dayNumber) {
		DateTime dateTime = DateTime.now().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0)
		    .plusDays(dayNumber + 1);
		return dateTime.toDate();
	}

	/** 获取指定时间间隔几天后的 0时0分0秒0毫秒 */
	public static final Date getDateAfterDayNumber(Date date, int dayNumber) {
		DateTime dateTime = new DateTime(date.getTime()).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
		    .withMillisOfSecond(0).plusDays(dayNumber + 1);
		return dateTime.toDate();
	}

	/** 获取今天的 0时0分0秒0毫秒 */
	public static final Date getStartOfDay() {
		DateTime dateTime = DateTime.now().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
		return dateTime.toDate();
	}

	/** 获取指定时间的 0时0分0秒0毫秒 */
	public static final Date getStartOfDay(Date date) {
		DateTime dateTime = new DateTime(date.getTime()).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
		    .withMillisOfSecond(0);
		return dateTime.toDate();
	}

	/** 获取今天的 23时59分59秒999毫秒 */
	public static final Date getEndOfDay() {
		DateTime dateTime = DateTime.now().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59)
		    .withMillisOfSecond(999);
		return dateTime.toDate();
	}

	/** 获取指定时间的 23时59分59秒999毫秒 */
	public static final Date getEndOfDay(Date date) {
		DateTime dateTime = new DateTime(date.getTime()).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59)
		    .withMillisOfSecond(999);
		return dateTime.toDate();
	}

	/** 获取今天之后某周的 0时0分0秒0毫秒 */
	public static final Date getByDayOfWeek(int dayOfWeek) {
		DateTime dateTime = DateTime.now().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
		int nowDayOfWeek = dateTime.get(DateTimeFieldType.dayOfWeek());
		if (nowDayOfWeek < dayOfWeek) {
			dateTime = dateTime.withDayOfWeek(dayOfWeek);
		} else {
			dateTime = dateTime.plusWeeks(1).withDayOfWeek(dayOfWeek);
		}
		return dateTime.toDate();
	}

	/** 获取指定时间之后某周的 0时0分0秒0毫秒 */
	public static final Date getByDayOfWeek(Date date, int dayOfWeek) {
		DateTime dateTime = new DateTime(date.getTime()).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
		    .withMillisOfSecond(0);
		int nowDayOfWeek = dateTime.get(DateTimeFieldType.dayOfWeek());
		if (nowDayOfWeek < dayOfWeek) {
			dateTime = dateTime.withDayOfWeek(dayOfWeek);
		} else {
			dateTime = dateTime.plusWeeks(1).withDayOfWeek(dayOfWeek);
		}
		return dateTime.toDate();
	}

	/** 获取指定时间之后某周的 0时0分0秒0毫秒 */
	public static final Date getByDayOfWeekIncludeEqual(Date date, int dayOfWeek) {
		DateTime dateTime = new DateTime(date.getTime()).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
		    .withMillisOfSecond(0);
		int nowDayOfWeek = dateTime.get(DateTimeFieldType.dayOfWeek());
		if (nowDayOfWeek <= dayOfWeek) {
			dateTime = dateTime.withDayOfWeek(dayOfWeek);
		} else {
			dateTime = dateTime.plusWeeks(1).withDayOfWeek(dayOfWeek);
		}
		return dateTime.toDate();
	}

	/** 获取今天之后某天的 0时0分0秒0毫秒 */
	public static final Date getByDayOfMonth(int dayOfMonth) {
		DateTime dateTime = DateTime.now().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
		int nowDayOfMonth = dateTime.get(DateTimeFieldType.dayOfMonth());
		if (nowDayOfMonth < dayOfMonth) {
			dateTime = dateTime.withDayOfMonth(dayOfMonth);
		} else {
			dateTime = dateTime.plusMonths(1).withDayOfMonth(dayOfMonth);
		}
		return dateTime.toDate();
	}

	/** 获取指定时间之后某天的 0时0分0秒0毫秒 */
	public static final Date getByDayOfMonth(Date date, int dayOfMonth) {
		DateTime dateTime = new DateTime(date.getTime()).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
		    .withMillisOfSecond(0);
		int nowDayOfMonth = dateTime.get(DateTimeFieldType.dayOfMonth());
		if (nowDayOfMonth < dayOfMonth) {
			dateTime = dateTime.withDayOfMonth(dayOfMonth);
		} else {
			dateTime = dateTime.plusMonths(1).withDayOfMonth(dayOfMonth);
		}
		return dateTime.toDate();
	}

	public static final Date getByDayOfMonthIncludeEqual(Date date, int dayOfMonth) {
		DateTime dateTime = new DateTime(date.getTime()).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
		    .withMillisOfSecond(0);
		int nowDayOfMonth = dateTime.get(DateTimeFieldType.dayOfMonth());
		if (nowDayOfMonth <= dayOfMonth) {
			dateTime = dateTime.withDayOfMonth(dayOfMonth);
		} else {
			dateTime = dateTime.plusMonths(1).withDayOfMonth(dayOfMonth);
		}
		return dateTime.toDate();
	}



	/** 获取指定时间之后某天的 0时0分0秒0毫秒 */
	public static final Date getByDayOfNextMonth(Date date, int dayOfMonth) {
		DateTime dateTime = new DateTime(date.getTime()).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
				.withMillisOfSecond(0);
		int nowDayOfMonth = dateTime.get(DateTimeFieldType.dayOfMonth());

			dateTime = dateTime.plusMonths(1).withDayOfMonth(dayOfMonth);

		return dateTime.toDate();
	}






	public static final Date plusDay(int dayNumber) {
		DateTime dateTime = DateTime.now().plusDays(dayNumber);
		return dateTime.toDate();
	}

	public static final Date plusDay(Date date, int dayNumber) {
		DateTime dateTime = new DateTime(date.getTime()).plusDays(dayNumber);
		return dateTime.toDate();
	}
	
	public static final Date plusHour(Date date, int hourNumber) {
		DateTime dateTime = new DateTime(date.getTime()).plusHours(hourNumber);
		return dateTime.toDate();
	}
	
	public static final Date plusMonth(Date date, int monthNumber) {
		DateTime dateTime = new DateTime(date.getTime()).plusMonths(monthNumber);
		return dateTime.toDate();
	}
	
	public static final Date plusMinute(Date date, int minuteNumber) {
		DateTime dateTime = new DateTime(date.getTime()).plusMinutes(minuteNumber);
		return dateTime.toDate();
	}

	// public static final Date minusDay(Date date, int dayNumber) {
	// DateTime dateTime = new DateTime(date.getTime()).minusDays(dayNumber);
	// return dateTime.toDate();
	// }

	public static final Date minusMinute(Date date, int minuteNumber) {
		DateTime dateTime = new DateTime(date.getTime()).minusMinutes(minuteNumber);
		return dateTime.toDate();
	}

	public static final Date minusMinute(int minuteNumber) {
		DateTime dateTime = DateTime.now().minusMinutes(minuteNumber);
		return dateTime.toDate();
	}

	public static final Date plusSecond(Date date, int secondNumber) {
		DateTime dateTime = new DateTime(date.getTime()).plusSeconds(secondNumber);
		return dateTime.toDate();
	}

	public static final boolean isBetWeen(Date date, Date startDate, Date endDate) {
		if (date == null || startDate == null || endDate == null) {
			throw new IllegalArgumentException("参数错误");
		}
		return date.after(startDate) && date.before(endDate);
	}

	public static final boolean isOverlap(Date start, Date end, Date startDate, Date endDate) {
		if (start == null || end == null || startDate == null || endDate == null) {
			throw new IllegalArgumentException("参数错误");
		}
		return (start.getTime() < endDate.getTime() && end.getTime() > startDate.getTime());
	}
	
	public static final Date getFirstMonthDay(Date date) {
		DateTime dateTime =  new DateTime(date.getTime()).withDayOfMonth(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
		    .withMillisOfSecond(0);
		return dateTime.toDate();
	}



	public static final Date getNextQuarterDay() {
		DateTime dateTime = DateTime.now().withDayOfMonth(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
		    .withMillisOfSecond(0);
		int month = dateTime.get(DateTimeFieldType.monthOfYear());
		int rest = month % 3;
		if (rest == 1) {
			dateTime = dateTime.plusMonths(3);
		} else if (rest == 2) {
			dateTime = dateTime.plusMonths(2);
		} else {
			dateTime = dateTime.plusMonths(1);
		}
		return dateTime.toDate();
	}

	public static final Date getNextQuarterDay(Date day) {
		DateTime dateTime = new DateTime(day.getTime()).withDayOfMonth(1).withHourOfDay(0).withMinuteOfHour(0)
		    .withSecondOfMinute(0).withMillisOfSecond(0);
		int month = dateTime.get(DateTimeFieldType.monthOfYear());
		int rest = month % 3;
		if (rest == 0) {
			dateTime = dateTime.plusMonths(1);
		} else {
			dateTime = dateTime.plusMonths(4 - rest);
		}
		return dateTime.toDate();
	}

	public static final String getQuarterStr(Date day) {
		DateTime dateTime = new DateTime(day.getTime()).withDayOfMonth(1).withHourOfDay(0).withMinuteOfHour(0)
		    .withSecondOfMinute(0).withMillisOfSecond(0);
		int month = dateTime.get(DateTimeFieldType.monthOfYear());
		dateTime = dateTime.withMonthOfYear((month / 3) + (month % 3 > 0 ? 1 : 0));
		return quarterFormat.print(dateTime);
	}

	public static final Date getNextQuarterDay(Date day, int dayOfMonth) {
		DateTime dateTime = new DateTime(day.getTime()).withDayOfMonth(1).withHourOfDay(0).withMinuteOfHour(0)
		    .withSecondOfMinute(0).withMillisOfSecond(0);
		int month = dateTime.get(DateTimeFieldType.monthOfYear());
		int rest = month % 3;
		if (rest == 0) {
			dateTime = dateTime.plusMonths(1);
		} else {
			dateTime = dateTime.plusMonths(4 - rest);
		}
		dateTime = dateTime.withDayOfMonth(dayOfMonth);
		return dateTime.toDate();
	}

	public static final Date getNextHalfYearDay() {
		DateTime dateTime = DateTime.now().withDayOfMonth(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
		    .withMillisOfSecond(0);
		int month = dateTime.get(DateTimeFieldType.monthOfYear());
		int rest = month % 6;
		if (rest == 0) {
			dateTime = dateTime.plusMonths(1);
		} else {
			dateTime = dateTime.plusMonths(7 - rest);
		}
		return dateTime.toDate();
	}

	public static final Date getNextHalfYearDay(Date day) {
		DateTime dateTime = new DateTime(day.getTime()).withDayOfMonth(1).withHourOfDay(0).withMinuteOfHour(0)
		    .withSecondOfMinute(0).withMillisOfSecond(0);
		int month = dateTime.get(DateTimeFieldType.monthOfYear());
		int rest = month % 6;
		if (rest == 0) {
			dateTime = dateTime.plusMonths(1);
		} else {
			dateTime = dateTime.plusMonths(7 - rest);
		}
		return dateTime.toDate();
	}

	public static final Date getNextHalfYearDay(Date day, int dayOfMonth) {
		DateTime dateTime = new DateTime(day.getTime()).withDayOfMonth(1).withHourOfDay(0).withMinuteOfHour(0)
		    .withSecondOfMinute(0).withMillisOfSecond(0);
		int month = dateTime.get(DateTimeFieldType.monthOfYear());
		int rest = month % 6;
		if (rest == 0) {
			dateTime = dateTime.plusMonths(1);
		} else {
			dateTime = dateTime.plusMonths(7 - rest);
		}
		dateTime = dateTime.withDayOfMonth(dayOfMonth);
		return dateTime.toDate();
	}

	public static final Date getNextYearDay() {
		DateTime dateTime = DateTime.now().withMonthOfYear(1).withDayOfMonth(1).withHourOfDay(0).withMinuteOfHour(0)
		    .withSecondOfMinute(0).withMillisOfSecond(0);
		dateTime = dateTime.plusYears(1);
		return dateTime.toDate();
	}

	public static final Date getNextYearDay(Date day) {
		DateTime dateTime = new DateTime(day.getTime()).withMonthOfYear(1).withDayOfMonth(1).withHourOfDay(0)
		    .withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
		dateTime = dateTime.plusYears(1);
		return dateTime.toDate();
	}

	public static final Date getNextYearDay(Date day, int dayOfMonth) {
		DateTime dateTime = new DateTime(day.getTime()).withMonthOfYear(1).withDayOfMonth(1).withHourOfDay(0)
		    .withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
		dateTime = dateTime.plusYears(1);
		dateTime = dateTime.withDayOfMonth(dayOfMonth);
		return dateTime.toDate();
	}

	public static final int compareDay(Date first, Date second) {
		return new DateTime(first.getTime())
		    .withHourOfDay(0)
		    .withMinuteOfHour(0)
		    .withSecondOfMinute(0)
		    .withMillisOfSecond(0)
		    .compareTo(
		        new DateTime(second.getTime()).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
		            .withMillisOfSecond(0));
	}

	public static final int compareMills(Date first, Date second) {
		return Long.signum(first.getTime() - second.getTime());
	}

	public static final int compareMillsWithin(Date first, Date second, int mills) {// 时间相隔多少mills
		return Long.signum(Math.abs(first.getTime() - second.getTime()) - mills);
	}

	public static String print(Date date, String format) {// 时间相隔多少mills
		DateTime dt = new DateTime(date.getTime());
		DateTimeFormatter dtf = DateTimeFormat.forPattern(format);
		return dtf.print(dt);
	}

	public static String printLong(Date date) {// 时间相隔多少mills
		DateTime dt = new DateTime(date.getTime());
		return longFormat.print(dt);
	}

	public static String print(Date date) {// 时间相隔多少mills
		DateTime dt = new DateTime(date.getTime());
		return format.print(dt);
	}
	
	public static String printMonth(Date date) {// 时间相隔多少mills
		DateTime dt = new DateTime(date.getTime());
		return monthFormat.print(dt);
	}

	public static String printPoint(Date date) {// 时间相隔多少mills
		DateTime dt = new DateTime(date.getTime());
		return formatPoint.print(dt);
	}
	public static String printNoPoint(Date date) {// 时间相隔多少mills
		DateTime dt = new DateTime(date.getTime());
		return formatNoPoint.print(dt);
	}

	public static String printNoPoint() {// 时间相隔多少mills
		DateTime dt = new DateTime(System.currentTimeMillis());
		return formatNoPoint.print(dt);
	}


	public static Date parseNoPoint(String str) {// 时间相隔多少mills
		DateTime dt =  DateTime.parse(str, formatNoPoint);
		return dt.toDate();
	}

	public static Date parseNoDay(String str) {// 时间相隔多少mills
		DateTime dt =  DateTime.parse(str, monthFormat);
		return dt.toDate();
	}
	
	public static int getDayNumBetween(Date startDate, Date endDate) {// 时间相隔多少mills
		DateTime sd = new DateTime(startDate.getTime()).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
		    .withMillisOfSecond(0);
		DateTime ed = new DateTime(endDate.getTime()).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
		    .withMillisOfSecond(0);
		if (sd == null || ed == null) {
			throw new IllegalArgumentException("ReadableInstant objects must not be null");
		}
		Chronology chrono = DateTimeUtils.getInstantChronology(sd);
		int amount = DurationFieldType.days().getField(chrono).getDifference(sd.getMillis(), ed.getMillis());
		return amount;
	}
	
	public static int getMinuteNumBetween(Date startDate , Date endDate) {// 时间相隔多少mills
		DateTime sd = new DateTime(startDate.getTime()).withSecondOfMinute(0)
		    .withMillisOfSecond(0);
		DateTime ed = new DateTime(endDate.getTime()).withSecondOfMinute(0)
			    .withMillisOfSecond(0);
		if (sd == null || ed == null) {
			throw new IllegalArgumentException("ReadableInstant objects must not be null");
		}
		Chronology chrono = DateTimeUtils.getInstantChronology(sd);
		int amount = DurationFieldType.minutes().getField(chrono).getDifference(ed.getMillis(), sd.getMillis());
		return amount;
	}
	//new DateTime("2010-06-30T01:20")
	public static Date toDate(String dateStr) {// 时间相隔多少mills
		DateTime sd = new DateTime(dateStr);
		return sd.toDate();
	}

	public static Pair<Date, Date> getCurrentAndNextMonth(String yearMonth) {
		java.time.LocalDateTime current = LocalDateTime.parse(yearMonth+DEFAULT_DAY_HOUR_MINUTE_SECOND, FORMATTER);
		ZonedDateTime currentZoned = current.atZone(ZoneId.systemDefault());

		java.time.LocalDateTime next = current.plusMonths(1L);
		ZonedDateTime nextZoned = next.atZone(ZoneId.systemDefault());
		return Pair.of(Date.from(currentZoned.toInstant()), Date.from(nextZoned.toInstant()));
	}

	public static Pair<Date, Date> getCurrentAndNextDay(String yearMonthDay) {
		java.time.LocalDateTime current = LocalDateTime.parse(yearMonthDay+DEFAULT_HOUR_MINUTE_SECOND, FORMATTER);
		ZonedDateTime currentZoned = current.atZone(ZoneId.systemDefault());

		LocalDateTime next = current.plusDays(1L);
		ZonedDateTime nextZoned = next.atZone(ZoneId.systemDefault());
		return Pair.of(Date.from(currentZoned.toInstant()), Date.from(nextZoned.toInstant()));
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = DatesUtil.getDateAfterDayNumber(new Date(), 0);
		DateTime dateTime1 = new DateTime(date1.getTime());
		System.out.println(dateTime1.toString("yyyy年MM月dd日 HH:mm:ss e"));

		Date date2 = DatesUtil.getByDayOfMonth(new Date(), 11);
		DateTime dateTime2 = new DateTime(date2.getTime());
		System.out.println(dateTime2.toString("yyyy年MM月dd日 HH:mm:ss e"));

		System.out.println(print(new Date(), "yyyyMMdd"));

		DateTime date4 = new DateTime(2016, 9, 12, 3, 00);
		DateTime date5 = new DateTime(2016, 9, 19, 3, 00);
		
		Date date6=getNextQuarterDay(date4.toDate());
		System.out.println(print(date6));
		System.out.println(print(getNextYearDay(date6)));

		 System.out.println(getDayNumBetween(date4.toDate(), date5.toDate()));

		Date startDate = date4.toDate();
		Date endDate = date5.toDate();
		Date nowDate = new Date();


	}
}
