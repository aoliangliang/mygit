package com.newtouch.blockchain.hibernate;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Configuration
public class HibernateTransactionAdviceInterceptor {

	private String AOP_POINTCUT_EXPRESSION = "execution(* *.kklazy..*.*.service..*.*(..))";

	@Autowired private PlatformTransactionManager transactionManager;

	/**
	 * @return
	 */
	public @Bean TransactionInterceptor txAdvice() {
		AnnotationTransactionAttributeSource source = new AnnotationTransactionAttributeSource();
		TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);
		return txAdvice;
	}

	/**
	 * @return
	 */
	public @Bean Advisor txAdviceAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
		return new DefaultPointcutAdvisor(pointcut, txAdvice());
	}
}
