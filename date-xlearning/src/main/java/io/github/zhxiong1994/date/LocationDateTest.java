package io.github.zhxiong1994.date;

import java.time.*;
import java.time.chrono.Era;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.Locale;

/**
 * Java LocationDate 类的测试工具
 */
public class LocationDateTest {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2024, Month.JULY, 30);
        System.out.println(localDate); // 2024-07-30
        LocalDate localDate1 = LocalDate.of(2024, 6, 30);
        System.out.println(localDate1); // 2024-06-30

        // 注意：以上两个类 月份只能是1-12， 天只能是28-31，并且如果当天没有31而输入了31则会报错，
        // 如果需要回去具体月份的最后一天则可以使用以下方法
        LocalDate date = LocalDate.of(2024, 6, 1); // 生成置顶月份的第一天 （后面的操作可以是其他日期）
        int days = date.lengthOfMonth();
        date = date.with(ChronoField.DAY_OF_MONTH, days);
        System.out.println(date); // 2024-06-30

        LocalDate localDate2 = LocalDate.ofYearDay(2024, 342);
        System.out.println(localDate2); // 2024-12-07

        // 从1970年1月1日之后的第n天
        LocalDate localDate3 = LocalDate.ofEpochDay(1);
        System.out.println(localDate3); // 1970-01-02

        /* 日期 日期时间与字符串之间的转换 DateTimeFormatter */
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateFormatterLocale = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA);
        System.out.println(date.format(dateFormatterLocale));


        /* 和Date互相转换
        *
        * 1、转换成ZonedDateTime
        * 2、借助 ZoneId  一般情况下直接使用系统默认即可
        * 3、通过 ZoneId将Date转换为 ZonedDateTime 即可生成对应的 LocalDateTime
        *  */
        Date date1 = new Date();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = date1.toInstant().atZone(zoneId);
        LocalDateTime localTime = zonedDateTime.toLocalDateTime();
        System.out.println("LocalTime: " + localTime);

        /* LocalTime 转换为 Date */
        Date from = Date.from(date.atStartOfDay().atZone(zoneId).toInstant());
        System.out.println(from);

    }

}
