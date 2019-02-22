package com.honest.sdms;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.support.http.StatViewServlet;
import com.terran4j.commons.api2doc.config.EnableApi2Doc;


@SpringBootApplication
@EnableApi2Doc
@EnableTransactionManagement
@EnableCaching
@MapperScan("com.honest.sdms.*.dao")
@ServletComponentScan //识别工程中包含以下注解的类@WebServlet、@WebFilter、@WebListener
public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	@Bean
    public ServletRegistrationBean<StatViewServlet> druidStatViewServlet(){
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<StatViewServlet>(new StatViewServlet(),"/druid/*");

        //添加初始化参数：initParams
        //白名单：
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny","192.168.1.73");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","ccz@dbt888");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }
	
	public static void main(String[] args) {
		logger.info("**********start sdms project*************");
		
		SpringApplication.run(Application.class, args);
	}

}
