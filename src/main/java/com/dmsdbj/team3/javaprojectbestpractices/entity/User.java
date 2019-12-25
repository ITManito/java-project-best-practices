package com.dmsdbj.team3.javaprojectbestpractices.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 *
 * </p>
 *
 * @author sunshine
 * @since 2019-11-01
 */
//@TableName("User")
@TableName(autoResultMap = true)
//mybatis-plus类型处理器，JavaType 与 JdbcType 之间的转换,mybatis-plus-generator这个组件上的
//为使用该注解的类自动生成equals和hashCode方法 lombok注解
@EqualsAndHashCode(callSuper = false)
@Data

//配置lombok如何产生和显示getter和setter的方法
@Accessors(chain = true)
public class User implements Serializable {
    public static final long serialVersionUID = 1L;

    /**
     * 表主键 此处需要设置为数据库ID自增
     */
    //@TableId(type = IdType.AUTO)
    private long id;

    /**
     * 姓名 NotBlank：判空，String类型
     */
    @NotBlank(message = "用户名不能空")
//    @Length(min = 6, max = 20, message = "用户名需要为 6 - 20 个字符")
//    //和Java正则表达式匹配
//    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\*]*$", message = "用户昵称限制：最多20字符，包含文字、字母和数字")
    private String name;

    /**
     * 年龄 NotNull：判空，任何类型
     */
    @NotNull
    private int age;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String phone;

    //    逻辑删除
    @TableLogic
    private Integer deleted = 0;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    自动填充（mybatis-plus注解）
    @TableField(fill = FieldFill.INSERT)
    @Column(name = "create_time")
    private Date createTime;
//
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

//

    /**
     * 此处故意用了类字段名和数据库列明不相符
     * 可以用@TableField注解来表示 mybatis-plus注解
     */
    @TableField("userEvaluation")
    private String userEvaluation;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private OtherInfo otherInfo;

}