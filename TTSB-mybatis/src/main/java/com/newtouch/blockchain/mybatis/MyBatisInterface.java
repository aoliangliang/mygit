package com.newtouch.blockchain.mybatis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于mybatis的dao发现
 * @Package com.ghw.fs.mybatis
 * @ClassName: MyBatisInterface
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyBatisInterface {

}
