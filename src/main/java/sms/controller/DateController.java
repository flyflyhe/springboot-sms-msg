package sms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sms.tool.DateTime;

@RestController
@RequestMapping("/date")
public class DateController {

    @RequestMapping("/get")
    public String get() {
        DateTime dateTime = new DateTime();
        return  dateTime.getAsiaShanghaiTime();
    }

    @RequestMapping("/unix")
    public long unix() {
        DateTime dateTime = new DateTime();
        return  dateTime.getUnixTime();
    }
}
