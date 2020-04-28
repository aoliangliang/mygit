package com.newtouch.blockchain.mybatis;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;

/**
 * 
 * 加载mybatis配置
 * @Package com.newtouch.blockchain.mybatis
 * @ClassName: MybatisConfiguration
 */
@Configuration
@ImportResource("classpath:/mybatis/spring-mybatis.xml")
@DependsOn({
	"dynamicDataSource"
})
public class MybatisConfiguration {
	

}
