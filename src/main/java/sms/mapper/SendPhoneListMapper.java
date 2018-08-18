package sms.mapper;

import sms.model.SendPhoneListModel;

import java.util.List;

public interface SendPhoneListMapper {
    List<SendPhoneListModel> selectAll();

    SendPhoneListModel selectOne(int id);

    int insertOne(SendPhoneListModel sendPhoneListModel);

    int updateOne(SendPhoneListModel sendPhoneListModel);

    int deleteOne(int id);
}
