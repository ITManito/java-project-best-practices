package com.dmsdbj.team3.javaprojectbestpractices.serviceImplTest;

import com.dmsdbj.team3.javaprojectbestpractices.entity.User;
import com.dmsdbj.team3.javaprojectbestpractices.mapper.UserMapper;
import com.dmsdbj.team3.javaprojectbestpractices.service.impl.UserServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;

/**
 * @author : angel-rmm
 * @Date 2019/12/20  9:26 上午
 * @Package com.dmsdbj.team3.javaprojectbestpractices.serviceImplTest
 * @Version v1.0
 * @Copyright
 * @Email 18232466045@163.com
 * @Contract https://github.com/ITManito
 */
@SpringBootTest
@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
public class UserServiceImplTest extends AbstractTestNGSpringContextTests {
    @Mock
//    定义被mock的组件
    private UserMapper userMapper;

    @InjectMocks
//    定义待测试的组件
    private UserServiceImpl userServiceImpl;

    @Test
    public void updateUserByPhone() throws Exception {
//        模拟实体： 以前是从数据库查询获得，现在造从数据库获得的数据
        User user = new User();
        user.setPhone("18232466047");
        user.setAge(1);
        user.setEmail("18232466047@163.com");
        user.setId(2);
        user.setName("angel");

//mock UserMapper的方法
        when(userMapper.getUserByPhone("18232466047")).thenReturn(user);
        when(userMapper.updateById(user)).thenReturn(1);
        boolean isUpdated = this.userServiceImpl.updateUserByPhone("18232466047", "18232466048");
        Assert.assertTrue(isUpdated == true);
        Assert.assertTrue(user.getPhone().equals("18232466048"));
    }
}