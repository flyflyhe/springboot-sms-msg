package sms.service;

import org.springframework.web.client.RestTemplate;

public class TxSms {

    private final static String apiUri = "https://yun.tim.qq.com/v5/tlssmssvr/sendsms?sdkappid=:appid&random=:random";

    private RestTemplate restTemplate;

    public TxSms(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public TxSmsResponse send(TxSmsRequest txSmsRequest) {
        String trueApiUri = apiUri.replaceFirst(":appid", String.valueOf(txSmsRequest.getAppId()));
        trueApiUri = trueApiUri.replaceFirst(":random", String.valueOf(txSmsRequest.getRandom()));
        return restTemplate.postForObject(trueApiUri, txSmsRequest, TxSmsResponse.class);

    }
}
