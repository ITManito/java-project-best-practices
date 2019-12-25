package com.dmsdbj.team3.javaprojectbestpractices.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname MybatisPlusConfig
 * @Auther sunshinezhang
 * @Date 2019/11/1 12:13
 */
@Configuration
@MapperScan("com.dmsdbj.team3.javaprojectbestpractices.mapper*")
public class MybatisPlusConfig {

	@Bean
	public PaginationInterceptor paginationInterceptor(){
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		return paginationInterceptor;
	}
}
