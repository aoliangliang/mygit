package com.newtouch.blockchain.mybatis;

import java.beans.Introspector;
import java.io.Serializable;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

/**
 * 方便从spring容器获取实例
 * @Package com.ghw.fs.spring
 * @ClassName: SpringBeanService
 */
@Service
public class SpringBeanService implements ApplicationContextAware,Serializable,PriorityOrdered,BeanFactoryPostProcessor {

	private static final long serialVersionUID = 5679269338390603558L;
	
	private static ApplicationContext context = null;
	
	/* 
	 * (non-Javadoc)
	 * @see org.springframework.getContext().ApplicationContextAware#setApplicationContext(org.springframework.getContext().ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringBeanService.context = applicationContext;
	}
	
	private static ApplicationContext getContext() {
		if (context == null) {
			throw new RuntimeException("ApplicationContext not installed");
		}
		return context;
	}

	public static Object getBean(String beanId) {
		return getContext().getBean(beanId);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz, String beanId) {
		return (T) getContext().getBean(beanId);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		String beanName = ClassUtils.getShortName(clazz.getName());
		beanName = Introspector.decapitalize(beanName);
		return (T) getContext().getBean(beanName);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBeans(Class<T> clazz) {
		String beanName = ClassUtils.getShortName(clazz.getName());
		beanName = Introspector.decapitalize(beanName);
		return (T) getContext().getBean(beanName);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBeans(Class<T> clazz, String beanId) {
		return (T) getContext().getBean(beanId);
	}

	/**
	 * @return
	 * @see org.springframework.core.Ordered#getOrder()
	 */
	@Override
	public int getOrder() {
		return LOWEST_PRECEDENCE;
	}

	/**
	 * @param beanFactory
	 * @throws BeansException
	 * @see org.springframework.beans.factory.config.BeanFactoryPostProcessor#postProcessBeanFactory(org.springframework.beans.factory.config.ConfigurableListableBeanFactory)
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// left intentionally blank
	}

}