package com.honest.sdms.basedata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器
 * @author beisi
 *
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

	@Autowired
	private CommonInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/**")//拦截所有url
        //排除以下请求
        .excludePathPatterns("/emp/toLogin","/emp/login","/js/**","/css/**","/images/**");
    }
    

}
