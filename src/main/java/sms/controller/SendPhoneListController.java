package sms.controller;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sms.connect.MysqlConnection;
import sms.mapper.SendPhoneListMapper;
import sms.model.SendPhoneListModel;
import sms.tool.DateTime;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/send-phone-list")
public class SendPhoneListController {

    private MysqlConnection mysqlConnection;

    @Autowired
    public SendPhoneListController(MysqlConnection mysqlConnection) {
        this.mysqlConnection = mysqlConnection;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<SendPhoneListModel> index() throws Exception {
        SqlSession session = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        List<SendPhoneListModel> sendPhoneListModelList = session.getMapper(SendPhoneListMapper.class).selectAll();
        session.close();

        return  sendPhoneListModelList;
    }

    @RequestMapping(method = RequestMethod.POST)
    public SendPhoneListModel create(@Valid @RequestBody SendPhoneListModel sendPhoneListModel) throws Exception {
        SqlSession session = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        DateTime dateTime = new DateTime();
        long time = dateTime.getUnixTime();
        sendPhoneListModel.setCreated_time(time);
        sendPhoneListModel.setUpdated_time(time);

        int id = session.getMapper(SendPhoneListMapper.class).insertOne(sendPhoneListModel);
        session.close();

        if (id != 1) {
            throw new Exception("创建失败");
        }

        return  sendPhoneListModel;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public SendPhoneListModel update(@PathVariable int id, @Valid @RequestBody SendPhoneListModel reqSendPhoneListModel) throws Exception {
        SqlSession session = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        SendPhoneListModel sendPhoneListModel = session.getMapper(SendPhoneListMapper.class).selectOne(id);

        if (null == sendPhoneListModel) {
            session.close();
            throw new Exception("不存在");
        }

        DateTime dateTime = new DateTime();
        sendPhoneListModel.setUpdated_time(dateTime.getUnixTime());
        if (reqSendPhoneListModel.getCountry_code() != null) {
            sendPhoneListModel.setCountry_code(reqSendPhoneListModel.getCountry_code());
        }

        if (reqSendPhoneListModel.getPhone() != null) {
            sendPhoneListModel.setPhone(reqSendPhoneListModel.getPhone());
        }

        if (reqSendPhoneListModel.getSend_batch_id() != 0) {
            sendPhoneListModel.setSend_batch_id(reqSendPhoneListModel.getSend_batch_id());
        }

        if (1 != session.getMapper(SendPhoneListMapper.class).updateOne(sendPhoneListModel)) {
            session.close();
            throw new Exception("修改失败");
        }

        session.close();
        return sendPhoneListModel;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int delete(@PathVariable int id) throws Exception {
        SqlSession session = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        if (null == session.getMapper(SendPhoneListMapper.class).selectOne(id)) {
            session.close();
            throw new Exception("不存在");
        }

        if (1 != session.getMapper(SendPhoneListMapper.class).deleteOne(id)) {
            session.close();
            throw new Exception("删除失败");
        }

        session.close();
        return  1;
    }
}