package sms.controller;

import org.apache.ibatis.javassist.NotFoundException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sms.connect.MysqlConnection;
import sms.mapper.PlatformMapper;
import sms.model.PlatformModel;
import sms.tool.DateTime;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/platform")
public class PlatformController {

    private MysqlConnection mysqlConnection;

    private DateTime dateTime;

    @Autowired
    public PlatformController(MysqlConnection mysqlConnection, DateTime dateTime) {
        this.mysqlConnection = mysqlConnection;
        this.dateTime = dateTime;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<PlatformModel> index() throws Exception {
        SqlSession session = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        return  session.getMapper(PlatformMapper.class).selectAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public PlatformModel create(@Valid @ModelAttribute PlatformModel platformModel) throws Exception {
        SqlSession session = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        long time = dateTime.getUnixTime();
        platformModel.setCreated_time(time);
        platformModel.setUpdated_time(time);
        int id = session.getMapper(PlatformMapper.class).insertOne(platformModel);
        platformModel.setId(id);
        return platformModel;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public PlatformModel update(@PathVariable int id, @ModelAttribute PlatformModel reqPlatformModel) throws Exception {
        SqlSession session = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        PlatformModel platformModel = session.getMapper(PlatformMapper.class).selectOne(id);
        if (platformModel == null) {
            throw new NotFoundException("不存在");
        }
        if (reqPlatformModel.getName() != null) {
            platformModel.setName(reqPlatformModel.getName());
        }
        if (reqPlatformModel.getType() != 0) {
            platformModel.setType(reqPlatformModel.getId());
        }
        platformModel.setUpdated_time(dateTime.getUnixTime());

        if (1 != session.getMapper(PlatformMapper.class).updateOne(platformModel)) {
            throw  new Exception("修改失败");
        }

        return  platformModel;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int delete(@PathVariable int id) throws  Exception {
        SqlSession session = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        PlatformModel platformModel = session.getMapper(PlatformMapper.class).selectOne(id);
        if (platformModel == null) {
            throw new NotFoundException("不存在");
        }

        return session.getMapper(PlatformMapper.class).deleteOne(id);
    }
}