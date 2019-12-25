package com.dmsdbj.team3.javaprojectbestpractices.mapperTest;

import com.dmsdbj.team3.javaprojectbestpractices.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author : angel-rmm
 * @Date 2019/12/20  9:23 上午
 * @Package com.dmsdbj.team3.javaprojectbestpractices.mapperTest
 * @Version v1.0
 * @Copyright
 * @Email 18232466045@163.com
 * @Contract https://github.com/ITManito
 */
@SpringBootTest
public class UserMapperTest extends AbstractTestNGSpringContextTests {
    @Autowired
//    @Resource
    private UserMapper userMapper;

    @Test
//    修饰符public 查：返回值：void， 用断言，提供参数，判断
    public void getUserByPhoneOne() {
        Assert.assertTrue(userMapper.getUserByPhone("18232466047").getId() == 2);
        Assert.assertTrue(userMapper.getUserByPhone("18232466047").getAge() == 1);
    }
}