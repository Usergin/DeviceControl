package com.shiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.GenericXmlApplicationContext;

@SpringBootApplication
@PropertySource("classpath:application.properties")
//@EnableTransactionManagement
@EnableAutoConfiguration(exclude = {
        JndiConnectionFactoryAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class,
//        DataSourceTransactionManagerAutoConfiguration.class,
//        TransactionAutoConfiguration.class
})
public class DeviceControlApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeviceControlApplication.class, args);
    }
}
