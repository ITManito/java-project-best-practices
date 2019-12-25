package com.dmsdbj.team3.javaprojectbestpractices.utils.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author : angel-rmm
 * @Date 2019/12/8  11:28 上午
 * @Package com.dmsdbj.team3.javaprojectbestpractices.utils.exception
 * @Version v1.0
 * @Copyright
 * @Email 18232466045@163.com
 * @Contract https://github.com/ITManito
 */
@Data
//对象有很多属性，通过json序列化全部返给客户端。但是一部分数据为null,同时客户端也不需要null数据。把为null的不参加序列化.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultBean<T>  {
    private int code;
    private String message;
    private Collection<T> data;

    private ResultBean() {
    }

    private ResultBean(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private ResultBean(int code, String message, Collection<T> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //    失败
    public static ResultBean error(int code, String message) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(code);
        resultBean.setMessage(message);
        return resultBean;
    }

    //    成功
    public static <V> ResultBean<V> success(Collection<V> data) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(0000);
        resultBean.setMessage("succeess");
        resultBean.setData(data);
        return resultBean;
    }

    public static <V> ResultBean<V> success(V data) {
        ResultBean resultBean = new ResultBean();
        Collection<V> temp = new ArrayList<V>();
        temp.add(data);
        resultBean.setCode(0000);
        resultBean.setMessage("succeess");
        resultBean.setData(temp);
        return resultBean;
    }

    @Override
    public String toString() {
      final  StringBuilder sb = new StringBuilder("com.dmsdbj.team3.javaprojectbestpractices.utils.exception.ResultBean{");
        sb.append("code=").append(code);
        sb.append(", message='").append(message).append('\"');
        sb.append(",data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}