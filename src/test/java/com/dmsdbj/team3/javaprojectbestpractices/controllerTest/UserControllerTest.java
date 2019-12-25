package com.dmsdbj.team3.javaprojectbestpractices.controllerTest;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dmsdbj.team3.javaprojectbestpractices.controller.impl.UserController;
import com.dmsdbj.team3.javaprojectbestpractices.entity.User;
import com.dmsdbj.team3.javaprojectbestpractices.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : angel-rmm
 * @Date 2019/12/20  9:22 上午
 * @Package com.dmsdbj.team3.javaprojectbestpractices.controllerTest
 * @Version v1.0
 * @Copyright
 * @Email 18232466045@163.com
 * @Contract https://github.com/ITManito
 */
//用于SpringMVC测试的注解，仅关注SpringMVC组件
@WebMvcTest
//定义类级别元数据，how加载配置上下文进行集成测试
@ContextConfiguration(classes = {UserController.class})
//定义了用于配置的类级元数据，应将TestExecutionListeners}注册到TestContextManager
@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
public class UserControllerTest extends AbstractTestNGSpringContextTests {
    //       @MockBean
//    private User user;
    @MockBean
    private IUserService userService;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private User user;

//    User user = new User(); 这种方式不mock也可以

    @BeforeClass
    public void initTestUserData() {
        user = new User();
        user.setAge(2);
        user.setName("Angel");
        user.setEmail("test@163.com");
        user.setPhone("18232456045");
        user.setId(2);
        System.out.println("User:" + user);
    }

    @Transactional
    @Test
    public void saveUserTest() throws Exception {
        when(userService.save(user)).thenReturn(true);
        this.mvc.perform(post("/v1/users").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(user)))
                .andDo(print()).andExpect(status().isOk());
    }

    @Transactional
    @Test
    public void removeUserTest() throws Exception {
        when(userService.removeById(anyInt())).thenReturn(true);
        this.mvc.perform(delete("/v1/users/id").param("id", "2"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getUserTest() throws Exception {
        when(userService.getById(anyInt())).thenReturn(user);
        this.mvc.perform(get("/v1/users/id").param("id", "2"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getUserListTest() throws Exception {
        IPage<User> page1 = null;
        when(userService.page(any())).thenReturn(page1);
        this.mvc.perform(get("/v1/users/actions/paging").param("page", "1"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void afterTest() {
        System.out.println("UserController AfterTest!");
    }

}