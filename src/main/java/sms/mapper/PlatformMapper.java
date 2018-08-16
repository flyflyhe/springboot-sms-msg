package sms.mapper;

import sms.model.PlatformModel;

import java.util.List;

public interface PlatformMapper {
    List<PlatformModel> selectAll();

    int insertOne(PlatformModel platformModel);

    PlatformModel selectOne(int id);

    int updateOne(PlatformModel platformModel);

    int deleteOne(int id);
}
