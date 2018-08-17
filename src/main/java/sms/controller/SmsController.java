package sms.controller;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sms.connect.MysqlConnection;
import sms.mapper.SecretMapper;
import sms.model.SecretModel;
import sms.service.TxSms;
import sms.service.TxSmsFormSingle;
import sms.service.TxSmsRequest;
import sms.service.TxSmsResponse;
import sms.tool.Secret;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/sms")
public class SmsController {

    private MysqlConnection mysqlConnection;

    @Autowired
    public SmsController(MysqlConnection mysqlConnection) {
        this.mysqlConnection = mysqlConnection;
    }

    @RequestMapping(value = "/tx/{id}", method = RequestMethod.POST)
    public TxSmsResponse index(@PathVariable int id, @Valid @RequestBody TxSmsFormSingle txSmsFormSingle) throws Exception {
        SqlSession session = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        SecretModel txSecretModel = session.getMapper(SecretMapper.class).selectOne(id);
        if (txSecretModel == null) {
            throw new Exception("不存在");
        }


        RestTemplate restTemplate = new RestTemplate();
        TxSms txSms = new TxSms(restTemplate);
        TxSmsRequest txSmsRequest = new TxSmsRequest();
        TxSmsResponse txSmsResponse;
        txSmsRequest.setAppId(Integer.parseInt(txSecretModel.getKey()));
        txSmsRequest.setAppKey(txSecretModel.getSecret());
        txSmsRequest.setMobile(txSmsFormSingle.getMobile(), txSmsFormSingle.getCountryCode());
        txSmsRequest.setMsg(txSecretModel.getTemplate().replaceFirst(":code:", txSmsFormSingle.getCode()));
        txSmsRequest.setType(Integer.parseInt(txSecretModel.getExtra()));
        txSmsRequest.setSig(txSmsRequest.generateSig());
        txSmsResponse = txSms.send(txSmsRequest);
        return txSmsResponse;
    }

    @RequestMapping(value = "/tx/more/{id}", method = RequestMethod.POST)
    public TxSmsFormSingle[] index(@PathVariable int id, @Valid @RequestBody TxSmsFormSingle[] txSmsFormSingle) throws Exception {
        return  txSmsFormSingle;
    }
}
