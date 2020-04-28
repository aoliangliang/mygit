package com.newtouch.blockchain.mybatis;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据增、删、改 实体对象基本信息处理
 * @Package com.ghw.fs.mybatis
 * @ClassName: BaseEntityInfoFillPlugin
 */
@Slf4j
@Intercepts(
	{
		@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
	}
)
public class BaseEntityInfoFillPlugin implements Interceptor{
	
	private UserInfoObtain userInfoObtain;
	
	/**
	 * @param invocation
	 * @return
	 * @throws Throwable
	 * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		try {
			Object[] args = invocation.getArgs();
            Object parameter = args[1];
            if (parameter instanceof Map<?, ?>) {//批量保存更新
				for (Object temp : ((Map<?, ?>)parameter).values()) {
					if (temp instanceof Collection<?>) {
						for (Object t : (Collection<?>)temp) {
							if (t instanceof BaseEntity) {
								fillBaseEntity((BaseEntity)t);
							}
						}
					}
				}
			} else if (parameter instanceof BaseEntity) {//单个保存更新
				fillBaseEntity((BaseEntity)parameter);
			}
           
		} catch (Throwable e) {
			log.error("{}", e);
			throw new RuntimeException(e.getMessage());
		}
		
		return invocation.proceed();
	}
	
	/**
	 * 设置对象基础参数
	 * @param 
	 * @return void
	 */
	private void fillBaseEntity(BaseEntity be) {
    	//ID
    	be.setId(StringUtils.isBlank(be.getId()) ? UUID.randomUUID().toString() : be.getId());
    	//date
    	LocalDateTime date = LocalDateTime.now();
    	be.setFirstInsert(be.getFirstInsert() == null ? date : be.getFirstInsert());
    	be.setLastModified(date);
    	//user
    	if (userInfoObtain != null) {
    		be.setCreateUser(StringUtils.isBlank(be.getCreateUser()) ? userInfoObtain.userId() : be.getCreateUser());
        	be.setUpdateUser(userInfoObtain.userId());
		}
	}
	
	/**
	 * @param target
	 * @return
	 * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
	 */
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * @param properties
	 * @see org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
	 */
	@Override
	public void setProperties(Properties properties) {
		try {
			userInfoObtain = SpringBeanService.getBeans(UserInfoObtain.class, UserInfoObtain.BEAN_ID);
		} catch (Exception e) {
			log.error("userInfoObtain not defined, please impl it");
		}
	}

}
