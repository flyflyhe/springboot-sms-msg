package sms.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import sms.tool.DateTime;

import javax.validation.constraints.NotEmpty;

public class PlatformModel {
    private int id;

    @NotEmpty
    private String name;

    private int type;

    private long created_time;

    private long updated_time;

    private String created;

    private String updated;

    @JsonIgnore
    private DateTime dateTime;

    public String getCreated() {
        return created;
    }

    private void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    private void setUpdated(String updated) {
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(long create_time) {
        this.created_time = create_time;
        setCreated(getDateTime().getUnixTimeToStr(create_time));
    }

    public long getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(long update_time) {
        this.updated_time = update_time;
        setUpdated(getDateTime().getUnixTimeToStr(update_time));
    }

    private DateTime getDateTime() {
        if (dateTime == null) {
            dateTime = new DateTime();
        }
        return dateTime;
    }
}
