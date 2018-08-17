package sms.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import sms.App;
import sms.service.TxSms;
import sms.service.TxSmsRequest;
import sms.service.TxSmsResponse;

import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class TxSmsTest {

    @Test
    public void testTxSms() {
        RestTemplate restTemplate = new RestTemplate();
        TxSms txSms = new TxSms(restTemplate);
        TxSmsRequest txSmsRequest = new TxSmsRequest();
        TxSmsResponse txSmsResponse;
        txSmsRequest.setAppId(1);
        txSmsRequest.setAppKey("abc");
        txSmsRequest.setMobile("11");
        txSmsRequest.setMsg("您的验证码为{12345}，十分钟内有效");
        txSmsRequest.setSig(txSmsRequest.generateSig());
        txSmsResponse = txSms.send(txSmsRequest);

        txSmsResponse = txSms.send(txSmsRequest);
    }
}
