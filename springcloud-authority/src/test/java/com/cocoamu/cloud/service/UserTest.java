package com.cocoamu.cloud.service;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;import com.cocoamu.cloud.dao.UserDao;
import com.cocoamu.cloud.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>User 相关的测试</h1>
 * */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void createUserRecord() {
        User user = new User();
        user.setUsername("422943393@qq.com");
        user.setPassword(MD5.create().digestHex("12345678"));
        user.setExtraInfo("{}");
        log.info("save user: [{}]",
                JSON.toJSON(userDao.save(user)));
    }

    @Test
    public void findUser(){
        User user = userDao.findByUsername("422943393@qq.com");
        System.out.println(user.toString());
    }
}
