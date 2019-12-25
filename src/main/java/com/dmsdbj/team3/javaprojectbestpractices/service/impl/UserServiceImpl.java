package com.dmsdbj.team3.javaprojectbestpractices.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmsdbj.team3.javaprojectbestpractices.entity.User;
import com.dmsdbj.team3.javaprojectbestpractices.mapper.UserMapper;
import com.dmsdbj.team3.javaprojectbestpractices.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunshine
 * @since 2019-11-01
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional
    public boolean updateUserByPhone(String oldPhone, String newPhone) throws Exception {
//        why 加，不加不行么
        boolean debug = log.isDebugEnabled();
        if (debug) {
//            打印日志方式：参数=[{}]
            log.debug("用户输入的新旧手机号. args[oldPhone=[{}],newPhone=[{}]])", oldPhone, newPhone);
        }

        try {
            User userByPhone = userMapper.getUserByPhone(oldPhone);
            if (userByPhone != null && !userByPhone.equals("")) {
                log.info("根据手机号查询到用户信息. phone=[{}],user=[{}]", oldPhone, JSON.toJSONString(userByPhone));
                userByPhone.setPhone(newPhone);
            }
            userMapper.updateById(userByPhone);
            return true;
        } catch (Exception e) {
            log.error("用户更新手机号失败. phone=[{}]", oldPhone, e);
            return false;
        }
    }

    @Override
    public List<User> getUserByLikeName(String name) {
        //声明User泛型 QueryWrapper 对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        //将模糊查询的字段用like方法赋给wrapper对象
        queryWrapper.like("name", name);

        log.info("用户输入的模糊查询的内容:[{}]", name);

//      调用mp方法selectList,  将queryWrapper对象作为参数，
        List<User> userList = userMapper.selectList(queryWrapper);
        return userList;
    }

    @Override
    public List<User> getUserByEmail(String email) {
//        两种方式都可以
//        return this.baseMapper.selectList(new QueryWrapper<User>().lambda().eq(User::getEmail, email));
        return this.baseMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getEmail, email));
    }

}
