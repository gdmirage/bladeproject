package com.blade.starter.redis;

public class PersonService {

    private PersonProperties personProperties;

    public PersonService(){}

    public PersonService(PersonProperties personProperties){
        this.personProperties = personProperties;
    }

    public void sayHello() {
        System.out.println("我叫" + personProperties.getName() + ", 年龄" + personProperties.getAge());
    }
}
