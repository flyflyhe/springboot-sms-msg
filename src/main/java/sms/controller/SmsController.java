package sms.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sms.service.TxSms;
import sms.service.TxSmsRequest;
import sms.service.TxSmsResponse;

@RestController
public class SmsController {
    @RequestMapping("/sms")
    public TxSmsResponse index() {
        RestTemplate restTemplate = new RestTemplate();
        TxSms txSms = new TxSms(restTemplate);
        TxSmsRequest txSmsRequest = new TxSmsRequest();
        TxSmsResponse txSmsResponse;
        txSmsRequest.setAppId(1);
        txSmsRequest.setAppKey("11");
        txSmsRequest.setMobile("");
        txSmsRequest.setMsg("您的验证码为{12345}，十分钟内有效");
        txSmsRequest.setSig(txSmsRequest.generateSig());
        txSmsResponse = txSms.send(txSmsRequest);
        HttpEntity<TxSmsRequest> httpEntity = new HttpEntity<>(txSmsRequest);
        return txSmsResponse;
    }
}
