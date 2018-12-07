package com.blade.archetype.module.provider;

import com.blade.archetype.module.api.ITestApi;

/**
 * TODO:
 *
 * @author chenjiangxin
 * @date 2018/12/5 12:14
 */
public class TestService implements ITestApi {

    @Override
    public void sayHello() {
        System.out.println("Hello World!");
    }
}
