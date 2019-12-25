package com.dmsdbj.team3.javaprojectbestpractices.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmsdbj.team3.javaprojectbestpractices.entity.User;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author sunshine
 * @since 2019-11-01
 */
//@Service 标注业务层组件
//    标注数据访问组件
@Repository
public interface UserMapper extends BaseMapper<User> {
    User getUserByPhone(String phone);
}
