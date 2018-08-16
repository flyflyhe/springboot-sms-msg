package sms.controller;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sms.connect.MysqlConnection;
import sms.mapper.SecretMapper;
import sms.model.SecretModel;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/secrets")
public class SecretController {

    private MysqlConnection mysqlConnection;

    @Autowired
    public SecretController(MysqlConnection mysqlConnection) {
        this.mysqlConnection = mysqlConnection;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<SecretModel> index() throws Exception {
        SqlSession sqlSession = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        return  sqlSession.getMapper(SecretMapper.class).selectAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public SecretModel create(@Valid @ModelAttribute SecretModel secretModel) {
        return  secretModel;
    }
}