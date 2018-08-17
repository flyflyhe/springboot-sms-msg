package sms.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sms.service.TxSms;
import sms.service.TxSmsFormSingle;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/form")
public class FormController {

    @RequestMapping("/post")
    public Map<String, String[]> post(HttpServletRequest request) {
        return  request.getParameterMap();
    }

    @RequestMapping(value = "/post2")
    public String post2(HttpServletRequest request) {
        return  request.getParameter("test");
    }

    @RequestMapping(value = "/post3")
    public TxSmsFormSingle post3(@RequestBody  TxSmsFormSingle txSmsFormSingle) {
        return  txSmsFormSingle;
    }

}
