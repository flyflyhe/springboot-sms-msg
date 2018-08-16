package sms.mapper;

import sms.model.SecretModel;

import java.util.List;

public interface SecretMapper {
    List<SecretModel> selectAll();

    SecretModel selectOne(int id);

    int insertOne(SecretModel secretModel);

    int updateOne(SecretModel secretModel);

    int deleteOne(int id);
}
