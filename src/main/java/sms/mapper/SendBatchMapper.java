package sms.mapper;

import sms.model.SendBatchModel;

import java.util.List;

public interface SendBatchMapper {
    List<SendBatchModel> selectAll();

    int insertOne(SendBatchModel sendBatchModel);

    int updateOne(SendBatchModel sendBatchModel);

    int deleteOne(int id);

    SendBatchModel selectOne(int id);
}
