package sms.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sms.connect.MysqlConnection;
import sms.mapper.SendBatchMapper;
import sms.model.SendBatchModel;
import sms.tool.DateTime;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/send-batch")
public class SendBatchController {

    private MysqlConnection mysqlConnection;

    @Autowired
    public SendBatchController(MysqlConnection mysqlConnection) {
        this.mysqlConnection = mysqlConnection;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<SendBatchModel> index() throws Exception {
        SqlSession session = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        List<SendBatchModel> sendBatchModelList = session.getMapper(SendBatchMapper.class).selectAll();
        session.close();
        return sendBatchModelList;
    }

    @RequestMapping(method = RequestMethod.POST)
    public SendBatchModel create(@Valid @RequestBody SendBatchModel sendBatchModel) throws Exception {
        SqlSession session = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        DateTime dateTime = new DateTime();
        long time = dateTime.getUnixTime();
        sendBatchModel.setCreated_time(time);
        sendBatchModel.setUpdated_time(time);

        int id = session.getMapper(SendBatchMapper.class).insertOne(sendBatchModel);
        session.close();
        if (1 != id) {
            throw new Exception("插入失败");
        }
        sendBatchModel.setId(id);
        return sendBatchModel;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public SendBatchModel update(@PathVariable int id, @RequestBody SendBatchModel reqSendBatchModel) throws Exception {
        SqlSession session = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        SendBatchModel sendBatchModel = session.getMapper(SendBatchMapper.class).selectOne(id);
        if (null == sendBatchModel) {
            throw new Exception("不存在");
        }

        DateTime dateTime = new DateTime();
        sendBatchModel.setUpdated_time(dateTime.getUnixTime());

        if (reqSendBatchModel.getName() != null) {
            sendBatchModel.setName(reqSendBatchModel.getName());
        }

        if (reqSendBatchModel.getSecret_id() != 0) {
            sendBatchModel.setSecret_id(reqSendBatchModel.getSecret_id());
        }

        if (reqSendBatchModel.getStatus() != 0) {
            sendBatchModel.setStatus(reqSendBatchModel.getStatus());
        }

        int i = session.getMapper(SendBatchMapper.class).updateOne(sendBatchModel);
        session.close();

        if (1 != i) {
            throw new Exception("修改失败");
        }

        return  sendBatchModel;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int delete(@PathVariable int id) throws Exception {
        SqlSession session = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        if (null == session.getMapper(SendBatchMapper.class).selectOne(id)) {
            throw new Exception("不存在");
        }

        if (1 != session.getMapper(SendBatchMapper.class).deleteOne(id)) {
            throw new Exception("删除失败");
        }

        session.close();
        return  1;
    }
}
