package sms.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sms.connect.MysqlConnection;
import sms.mapper.SecretMapper;
import sms.model.SecretModel;
import sms.tool.DateTime;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/secret")
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
    public SecretModel create(@Valid @RequestBody SecretModel secretModel) throws Exception {
        SqlSession session = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        DateTime dateTime = new DateTime();
        long time = dateTime.getUnixTime();
        secretModel.setCreated_time(time);
        secretModel.setUpdated_time(time);
        if (1 != session.getMapper(SecretMapper.class).insertOne(secretModel)) {
            throw new Exception("写入失败");
        }
        return  secretModel;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public SecretModel update(@PathVariable int id, HttpServletRequest request) throws Exception {
        SqlSession session = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        SecretModel secretModel = session.getMapper(SecretMapper.class).selectOne(id);
        if (secretModel == null) {
            throw new Exception("不存在");
        }
        DateTime dateTime = new DateTime();
        String platformId = request.getParameter("platform_id");
        String key = request.getParameter("key");
        String secret = request.getParameter("secret");
        String extra = request.getParameter("extra");
        String template = request.getParameter("template");

        if (platformId != null && !platformId.isEmpty()) {
            secretModel.setPlatform_id(Integer.parseInt(platformId));
        }

        if (key != null && !key.isEmpty()) {
            secretModel.setKey(key);
        }

        if (secret != null && !secret.isEmpty()) {
            secretModel.setSecret(secret);
        }

        if (extra != null && !extra.isEmpty()) {
            secretModel.setExtra(extra);
        }

        if (template != null && !template.isEmpty()) {
            secretModel.setTemplate(template);
        }

        secretModel.setUpdated_time(dateTime.getUnixTime());

        if (1 != session.getMapper(SecretMapper.class).updateOne(secretModel)) {
            throw new Exception("修改失败");
        }

        return secretModel;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int delete(@PathVariable int id) throws Exception {
        SqlSession session = mysqlConnection.getMybatisSqlSessionFactory().openSession(true);
        SecretModel secretModel = session.getMapper(SecretMapper.class).selectOne(id);
        if (secretModel == null) {
            throw new Exception("不存在");
        }

        return session.getMapper(SecretMapper.class).deleteOne(id);
    }
}