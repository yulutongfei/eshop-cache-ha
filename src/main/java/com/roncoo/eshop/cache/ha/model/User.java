package com.roncoo.eshop.cache.ha.model;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/6/23 23:33
 * 测试用户Model类
 */
public class User {

    /**
     * 测试用户名称
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
