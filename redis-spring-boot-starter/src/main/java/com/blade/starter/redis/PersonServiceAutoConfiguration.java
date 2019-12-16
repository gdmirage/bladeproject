package com.blade.starter.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PersonProperties.class)
@ConditionalOnClass(PersonService.class)
@ConditionalOnProperty(prefix = "spring.person", value = "enabled", matchIfMissing = true)
public class PersonServiceAutoConfiguration {
    @Autowired
    private PersonProperties personProperties;

    @Bean
    @ConditionalOnMissingBean(PersonService.class)
    public PersonService personService(){
        PersonService personService = new PersonService(personProperties);
        return personService;
    }
}
