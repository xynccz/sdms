package com.honest.sdms.basedata.config;

import java.util.Collections;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 面向切面的全局事物管理
 * @author beisi
 * 注意：
 * Spring 事务 readOnly 到底是怎么回事？
 * 	1 readonly并不是所有数据库都支持的，不同的数据库下会有不同的结果。
 *	2 设置了readonly后，connection都会被赋予readonly，效果取决于数据库的实现。
 *	3 在ORM中，设置了readonly会赋予一些额外的优化，例如在Hibernate中，会被禁止flush等。
 *  “只读事务”仅仅是一个性能优化的推荐配置而已,意思是在只读事物里还是可以做保存数据的操作，
 *  只有在hibernate框架是readonly=true是有效的，save会失败
 */
@Aspect
@Configuration
public class TransactionAdviceConfig {
	
	private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.honest.sdms.*.service.*.*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {
    	
    	/*
    	 * 创建事物管理对象
    	 */
    	RuleBasedTransactionAttribute txAttr_REQUIRED = new RuleBasedTransactionAttribute();
    	//抛出异常后执行切点回滚
    	txAttr_REQUIRED.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
    	//PROPAGATION_REQUIRED:事务隔离性为0，若当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务
    	txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    	
       /*
        * 创建只读事物管理对象
    	*/
    	RuleBasedTransactionAttribute txAttr_REQUIRED_READONLY = new RuleBasedTransactionAttribute();
    	//设置当前事务是否为只读事务，true为只读
    	txAttr_REQUIRED_READONLY.setReadOnly(true);
    	//transactiondefinition 定义事务的隔离级别；
    	txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
    	
    	/*事务管理规则，声明具备事务管理的方法名*/
    	NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("add*", txAttr_REQUIRED);
        source.addTransactionalMethod("save*", txAttr_REQUIRED);
        source.addTransactionalMethod("delete*", txAttr_REQUIRED);
        source.addTransactionalMethod("update*", txAttr_REQUIRED);
        source.addTransactionalMethod("modify*", txAttr_REQUIRED);
        source.addTransactionalMethod("insert*", txAttr_REQUIRED);
        source.addTransactionalMethod("execute*", txAttr_REQUIRED);
        source.addTransactionalMethod("set*", txAttr_REQUIRED);
        source.addTransactionalMethod("get*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("query*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("find*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("list*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("count*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("select*", txAttr_REQUIRED_READONLY);
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
