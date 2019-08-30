package com.blade.practice.mongodb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author blade
 * 2019/8/29 17:33
 */
public class SpringDataMongoDbTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-mongo.xml");
        MongoTemplate mongoTemplate = (MongoTemplate)applicationContext.getBean("mongoTemplate");
        System.out.println(mongoTemplate.getDb().getName());

    }
}
