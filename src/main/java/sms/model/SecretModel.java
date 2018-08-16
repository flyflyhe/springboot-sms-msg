package sms.model;

public class SecretModel {
    private int id;

    private int platform_id;

    private String key;

    private String secret;

    private int sign_id;

    private String sign_name;

    private String template;

    private long created_time;

    private long updated_time;

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
    }

    public long getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(long update_time) {
        this.updated_time = update_time;
    }
}