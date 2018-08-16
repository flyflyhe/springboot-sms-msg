package sms.connect;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class MysqlConnection {
    private static volatile SqlSessionFactory sqlSessionFactory;

    public  SqlSessionFactory getMybatisSqlSessionFactory()
            throws Exception {

        if (sqlSessionFactory == null) {
            synchronized(this){
                String resource = "mybatis-config.xml";
                InputStream inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            }
        }

        return sqlSessionFactory;
    }

}
