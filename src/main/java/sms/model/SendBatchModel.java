package sms.model;

public class SendBatchModel {
    private int id;

    private int secret_id;

    private String name;

    private int exec_times;

    private int status;

    private long created_time;

    private long updated_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSecret_id() {
        return secret_id;
    }

    public void setSecret_id(int secret_id) {
        this.secret_id = secret_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExec_times() {
        return exec_times;
    }

    public void setExec_times(int exec_times) {
        this.exec_times = exec_times;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
