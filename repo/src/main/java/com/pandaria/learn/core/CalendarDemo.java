package com.pandaria.learn.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Java时间格式化时YYYY(大写)和yyyy(小写)的区别
 */
public class CalendarDemo {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        // 2018-12-31
        calendar.set(2018, Calendar.DECEMBER, 31);
        Date strDate1 = calendar.getTime();
        // 2018-01-01
        calendar.set(2018, Calendar.JANUARY, 1);
        Date strDate2 = calendar.getTime();
        // 大写YYYY(Y是Week Year)
        DateFormat formatUpperCase = new SimpleDateFormat("YYYY/MM/dd");
        System.out.println("2018-12-31 to YYYY/MM/dd: " + formatUpperCase.format(strDate1));
        System.out.println("2018-01-01 to YYYY/MM/dd: " + formatUpperCase.format(strDate2));
        // 小写yyyy(y是Year)
        DateFormat formatLowerCase = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("2018-12-31 to yyyy/MM/dd: " + formatLowerCase.format(strDate1));
        System.out.println("2018-01-01 to yyyy/MM/dd: " + formatLowerCase.format(strDate2));
    }
}
