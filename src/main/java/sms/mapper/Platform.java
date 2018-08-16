package sms.mapper;

import sms.model.PlatformModel;

import java.util.List;

public interface Platform {
    public List<PlatformModel> selectAll();
}
