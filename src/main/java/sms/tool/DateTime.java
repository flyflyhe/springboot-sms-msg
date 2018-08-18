package sms.tool;

import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

@Component
public class DateTime {
    private org.joda.time.DateTime jodaDateTime;
    private DateTimeZone dateTimeZone;
    private static final String defaultTimeZone = "Asia/Shanghai";

    private String format = "yyyy-MM-dd HH:mm:ss";

    public DateTime() {
        jodaDateTime = org.joda.time.DateTime.now();
        dateTimeZone = DateTimeZone.forID(defaultTimeZone);
    }

    public String getAsiaShanghaiTime() {
        return jodaDateTime.toDateTime(dateTimeZone).toString(format);
    }

    public long getUnixTime() {
        return jodaDateTime.getMillis();
    }

    public String getUnixTimeToStr(long unix) {
        return jodaDateTime.withMillis(unix).toDateTime(dateTimeZone).toString(format);
    }

    //单例获取 转换时间戳到时间字符串使用
    private static volatile DateTime instanceDateTime = null;

    public static String unixTimeToStr(long unix) {
        return getInstanceDateTime().getUnixTimeToStr(unix);
    }

    private static DateTime getInstanceDateTime() {
        //多线程同时赋值也无影响
        if (instanceDateTime == null) {
            instanceDateTime = new DateTime();
        }

        return  instanceDateTime;
    }
}
