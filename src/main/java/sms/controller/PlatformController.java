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
        List<PlatformModel> platformModelList =  session.getMapper(PlatformMapper.class).selectAll();
        session.close();
        return platformModelList;
    }

    @RequestMapping(method = RequestMethod.POST)
    public PlatformModel create(@Valid @RequestBody PlatformModel platformModel) throws Exception {
        SqlSession session = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        long time = dateTime.getUnixTime();
        platformModel.setCreated_time(time);
        platformModel.setUpdated_time(time);
        int id = session.getMapper(PlatformMapper.class).insertOne(platformModel);
        session.close();

        if (id < 1) {
            throw new Exception("插入失败");
        }

        platformModel.setId(id);
        return platformModel;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public PlatformModel update(@PathVariable int id, @RequestBody PlatformModel reqPlatformModel) throws Exception {
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

        int i = session.getMapper(PlatformMapper.class).updateOne(platformModel);
        session.close();

        if (1 != i) {
            throw  new Exception("修改失败");
        }

        return  platformModel;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int delete(@PathVariable int id) throws  Exception {
        SqlSession session = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        PlatformModel platformModel = session.getMapper(PlatformMapper.class).selectOne(id);
        if (platformModel == null) {
            session.close();
            throw new NotFoundException("不存在");
        }

        int i =  session.getMapper(PlatformMapper.class).deleteOne(id);
        session.close();

        if (i != 1) {
            throw new Exception("删除失败");
        }

        return i;
    }
}