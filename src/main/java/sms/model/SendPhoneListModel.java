package sms.model;

public class SendPhoneListModel {
    private int id;

    private int send_batch_id;

    private String phone;

    private String country_code;

    private int exec_times;

    private long created_time;

    private long updated_time;

    private SendBatchModel sendBatchModel;

    public SendBatchModel getSendBatchModel() {
        return sendBatchModel;
    }

    public void setSendBatchModel(SendBatchModel sendBatchModel) {
        this.sendBatchModel = sendBatchModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSend_batch_id() {
        return send_batch_id;
    }

    public void setSend_batch_id(int send_batch_id) {
        this.send_batch_id = send_batch_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public int getExec_times() {
        return exec_times;
    }

    public void setExec_times(int exec_times) {
        this.exec_times = exec_times;
    }

    public long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(long created_time) {
        this.created_time = created_time;
    }

    public long getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(long updated_time) {
        this.updated_time = updated_time;
    }
}
