import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KaoQinUtil {

	private static DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static DateTimeFormatter sdf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");


	private static final LocalDate startDate = LocalDate.parse("2019-04-01", sdf1);

	private static final LocalDate endDate = LocalDate.parse("2019-05-01", sdf1);

	//周末是工作日--应该工作
	private static List<String> workdays = Arrays.asList("2019-04-28");
	//工作日是节假日--应该休息
	private static List<String> holidays = Arrays.asList("2019-04-05");


	public static void main(String[] args) throws IOException {
		KaoQinUtil util = new KaoQinUtil();
		//读取考勤内容
		List<String> listStr = util.readFile(args != null ? args.length == 1 ? args[0] : null : null);
		//把考勤内容转为时间
		List<LocalDateTime> localDateTimes = toDate(listStr);
		System.out.println("总共打卡次数=" + listStr.size());

		LocalDate start=startDate;
		//判断每天是否考勤正常
		while (start.isBefore(endDate)) {
			operate(start, localDateTimes);
			start = start.plusDays(1);
		}

		System.out.println("--------9点之后---------");

		start=startDate;
		while (start.isBefore(endDate)) {
			operateNine(start, localDateTimes);
			start = start.plusDays(1);
		}
	}


	private static void operate(LocalDate startDate, List<LocalDateTime> localDateTimes) {
		int dayOfWeek = startDate.getDayOfWeek().getValue();
		//得到一天内打卡的最大时间和最小时间
		LocalDateTime minDate = getMinDate(startDate, localDateTimes);
		LocalDateTime maxDate = getMaxDate(startDate, localDateTimes);
		//获取当前的时间字符串
		String thisDay = sdf1.format(startDate);
		//描述
		String memoStartEnd = "";
		String memoWeek = "周" + dayOfWeek;

		//上班总的时数和分钟数
		int hour = -1, minute = -1;
		if (maxDate != null) {
			hour = (maxDate.getHour() - minDate.getHour());
		}
		if (minDate != null) {
			minute = maxDate.getMinute() - minDate.getMinute();
			if (minute < 0) {
				hour = hour - 1;
				minute = minute + 60;
			}
			memoStartEnd = "开始时间" + sdf.format(minDate) + "结束时间" + sdf.format(maxDate);
		}
		String memoJiaban = "";

		if ((dayOfWeek < 6&&!holidays.contains(thisDay))||workdays.contains(thisDay)) {
			memoJiaban = (hour - 9) + "小时" + minute + "分钟;";
		} else {
			memoJiaban = (hour - 1) + "小时" + minute + "分钟;";
		}


		if (hour == -1 && holidays.contains(thisDay)) {//节假日不加班
		} else if (hour > -1 && holidays.contains(thisDay)) {//节假日加班
			System.out.println(thisDay + "节假日加班--(--" + memoJiaban + memoStartEnd);
		} else if (hour == -1 && dayOfWeek >= 6) {//周末不加班
		} else if (hour >= 9 && hour < 12 && dayOfWeek >= 6 && workdays.contains(thisDay)) {//周末是工作日,正常上班
		} else if (hour >= 12 && dayOfWeek >= 6 && workdays.contains(thisDay)) {//周末是工作日,加班
			System.out.println(thisDay + "周末正常上班外加班-" + memoJiaban + memoStartEnd);
		} else if (hour >= -1 && dayOfWeek >= 6 && workdays.contains(thisDay)) {//周末是工作日,漏打卡
			System.out.println(thisDay + "周末是工作日漏打卡--(--" + memoJiaban + memoWeek + memoStartEnd);
		} else if (hour > -1 && dayOfWeek >= 6) {//周末加班
			System.out.println(thisDay + "周末加班--(--" + memoJiaban + memoWeek + memoStartEnd);
		} else if (hour == -1) {//漏打卡或者请假
			System.out.println(thisDay + "漏打卡或者请假");
		} else if (hour < 9) {//漏打卡
			System.out.println(thisDay + "漏打卡");
		} else if (hour >= 9 && hour < 12) {//正常下班
			if (minDate.getHour() >= 10) {
				System.out.println(thisDay + "晚于10点上班----" + memoJiaban + memoStartEnd);
			}
		} else if (hour >= 12) {
			System.out.println(thisDay + "加班-" + memoJiaban + memoStartEnd);
		} else {
			System.out.println(thisDay + "其它-" + memoJiaban + memoStartEnd);
		}

	}

	private static void operateNine(LocalDate startDate, List<LocalDateTime> localDateTimes) {
		int dayOfWeek = startDate.getDayOfWeek().getValue();
		//得到一天内打卡的最大时间和最小时间
		LocalDateTime minDate = getMinDate(startDate, localDateTimes);
		LocalDateTime orgiDate=minDate;

		LocalDateTime maxDate = getMaxDate(startDate, localDateTimes);
		String thisDay = sdf1.format(startDate);

		//获取当前的时间字符串
		//描述
		String memoStartEnd = "";

		if(minDate!=null&&minDate.getHour()==9&& minDate.getMinute()<=15){
			minDate=minDate.withMinute(0);
		}else if(minDate!=null&&minDate.getHour()==9&& minDate.getMinute()>15){
			minDate=minDate.withHour(10).withMinute(0);
		}
		//上班总的时数和分钟数
		int hour = -1, minute = -1;
		if (maxDate != null) {
			hour = (maxDate.getHour() - minDate.getHour());
		}
		if (minDate != null) {
			minute = maxDate.getMinute() - minDate.getMinute();
			if (minute < 0) {
				hour = hour - 1;
				minute = minute + 60;
			}
			memoStartEnd = "开始时间" + sdf.format(orgiDate) + "结束时间" + sdf.format(maxDate);
		}
		String memoJiaban = "";

		if ((dayOfWeek < 6&&!holidays.contains(thisDay))||workdays.contains(thisDay)) {
			memoJiaban = (hour - 9) + "小时" + minute + "分钟;";
		} else {
			memoJiaban = (hour - 1) + "小时" + minute + "分钟;";
		}

		if(maxDate!=null&&maxDate.getHour()>=21){
			System.out.println("9点之后下班"+ memoJiaban + memoStartEnd);
		}

	}

	private static List<LocalDateTime> toDate(List<String> listStr) {
		List<LocalDateTime> listDate = new ArrayList<LocalDateTime>();
		for (String str : listStr) {
			listDate.add(LocalDateTime.parse(str, sdf));
		}
		return listDate;
	}

	private static LocalDateTime getMaxDate(LocalDate dateTime, List<LocalDateTime> listDate) {
		LocalDateTime maxDate = null;
		for (LocalDateTime date : listDate) {
			if (date.getMonthValue() == dateTime.getMonthValue() && date.getDayOfMonth() == dateTime.getDayOfMonth()) {
				if (maxDate != null) {
					if (date.isAfter(maxDate)) {
						maxDate = date;
					}
				} else {
					maxDate = date;
				}
			}
		}
		if (maxDate != null) {
			if (maxDate.getHour() < 9) {
				maxDate = maxDate.withHour(9).withMinute(0).withSecond(0);
			}
		}
		return maxDate;
	}

	private static LocalDateTime getMinDate(LocalDate dateTime, List<LocalDateTime> listDate) {
		LocalDateTime maxDate = null;
		for (LocalDateTime date : listDate) {
			if (date.getMonthValue() == dateTime.getMonthValue() && date.getDayOfMonth() == dateTime.getDayOfMonth()) {
				if (maxDate != null) {
					if (date.isBefore(maxDate)) {
						maxDate = date;
					}
				} else {
					maxDate = date;
				}
			}
		}
		if (maxDate != null) {
			if (maxDate.getHour() < 9) {
				maxDate = maxDate.withHour(9).withMinute(0).withSecond(0);
			}
		}
		return maxDate;
	}


	public List<String> readFile(String fileName) throws IOException {
		if (fileName == null) {
			URL url = KaoQinUtil.class.getResource("/tempFile/kaoqin.txt");
			if (url != null) {
				fileName = url.getPath();
			} else {
				fileName = "D:/tempFile/kaoqin.txt";
				if (!new File(fileName).exists()) {
					System.out.println(fileName + "不存在");
				}
			}
		}
		List<String> lineStrs = new ArrayList<String>();
		FileInputStream fis;
		try {
			fis = new FileInputStream(fileName);
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String line = "";
			String str = null;
			while ((line = br.readLine()) != null) {
				str = line.trim();
				String[] array = str.split("\\s");
				lineStrs.add(array[2] + " " + array[3].substring(0, array[3].length() - 2));
			}
			br.close();
			isr.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lineStrs;
	}
}
