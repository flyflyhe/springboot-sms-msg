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
    
    //双重检查锁 保证单例唯一
    public  SqlSessionFactory getMybatisSqlSessionFactory()
            throws Exception {
        
        //防止每次进入都执行同步代码块
        if (sqlSessionFactory == null) {
            synchronized(this){
                //防止多次创建实例
                if (sqlSessionFactory == null) {
                    String resource = "mybatis-config.xml";
                    InputStream inputStream = Resources.getResourceAsStream(resource);
                    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                } 
            }
        }

        return sqlSessionFactory;
    }

}
