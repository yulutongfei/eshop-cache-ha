package com.roncoo.eshop.cache.ha.mapper;

import com.roncoo.eshop.cache.ha.model.User;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/6/23 23:36
 * 测试用户的mapper接口
 */
public interface UserMapper {

    /**
     * 查询测试用户信息
     *
     * @return
     */
    User findUserInfo();
}
