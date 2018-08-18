package sms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import sms.tool.DateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SecretModel {
    private int id;

    @NotNull
    private int platform_id;

    //appid
    private String key;

    //appkey
    private String secret;

    private int sign_id;

    private String sign_name;

    private String template;

    private PlatformModel platformModel;

    public PlatformModel getPlatformModel() {
        return platformModel;
    }

    public void setPlatformModel(PlatformModel platformModel) {
        this.platformModel = platformModel;
    }

    @JsonIgnore
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    private String extra;

    private long created_time;

    private long updated_time;

    private String created_date;

    private String updated_date;

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(int platform_id) {
        this.platform_id = platform_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getSign_id() {
        return sign_id;
    }

    public void setSign_id(int sign_id) {
        this.sign_id = sign_id;
    }

    public String getSign_name() {
        return sign_name;
    }

    public void setSign_name(String sign_name) {
        this.sign_name = sign_name;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(long create_time) {
        this.created_time = create_time;
        setCreated_date(new DateTime().getUnixTimeToStr(create_time));
    }

    public long getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(long update_time) {
        this.updated_time = update_time;
        setUpdated_date(new DateTime().getUnixTimeToStr(update_time));
    }

    public int create() {
        switch (type) {
            case "tx":
                break;
            case "ali":
                break;
        }
        return 1;
    }
}
