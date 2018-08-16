package sms.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import sms.tool.DateTime;
import sms.tool.Secret;

import java.util.HashMap;

public class TxSmsRequest {

    @JsonIgnore
    private int appId;

    @JsonIgnore
    private String appKey;

    private String mobile;

    @JsonIgnore
    private String random;

    //模板
    private String msg;

    //类型0:普通短信 1:营销短信
    private int type;

    private String sig;

    private String extend;

    private HashMap<String, String> tel;

    public HashMap<String, String> getTel() {
        return tel;
    }

    public TxSmsRequest() {
        DateTime dateTime = new DateTime();
        random = String.valueOf(dateTime.getUnixTime()).substring(0, 9);
    }

    public void setTel(HashMap<String, String> tel) {
        this.tel = tel;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getRandom() {
        return random;
    }


    public String generateSig() {
        String sig = "appkey=" +
                appKey +
                "&random=" +
                random +
                "&time=" +
                random +
                "&mobile=" +
                mobile;
        return Secret.getSHA256StrJava(sig);
    }
}
