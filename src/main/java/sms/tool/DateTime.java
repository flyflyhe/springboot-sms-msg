package sms.tool;

import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

@Component
public class DateTime {
    private org.joda.time.DateTime jodaDateTime;
    private DateTimeZone dateTimeZone;

    private String format = "yyyy-MM-dd HH:mm:ss";

    public DateTime() {
        jodaDateTime = org.joda.time.DateTime.now();
        dateTimeZone = DateTimeZone.forID("Asia/Shanghai");
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
}
