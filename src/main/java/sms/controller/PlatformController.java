package sms.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sms.connect.MysqlConnection;
import sms.model.PlatformModel;

import java.util.List;

@RestController
@RequestMapping("/platforms")
public class PlatformController {

    private MysqlConnection mysqlConnection;

    @Autowired
    public PlatformController(MysqlConnection mysqlConnection) {
        this.mysqlConnection = mysqlConnection;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<PlatformModel> index() throws Exception {
        SqlSession session = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        return  session.getMapper(sms.mapper.Platform.class).selectAll();
    }
}