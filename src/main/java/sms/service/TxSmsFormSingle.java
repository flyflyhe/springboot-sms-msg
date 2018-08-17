package sms.service;

import javax.validation.constraints.NotEmpty;

public class TxSmsFormSingle {

    @NotEmpty
    private String mobile;

    @NotEmpty
    private String code;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
