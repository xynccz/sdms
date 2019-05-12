package com.honest.sdms;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.terran4j.commons.api2doc.config.EnableApi2Doc;


@SpringBootApplication
@EnableApi2Doc
@EnableCaching
@MapperScan("com.honest.sdms.*.dao")
@ServletComponentScan //识别工程中包含以下注解的类@WebServlet、@WebFilter、@WebListener
public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	@Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> connector.setProperty("relaxedQueryChars", "|{}[]\\"));
        return factory;
    }
	
	public static void main(String[] args) {
		logger.info("**********start sdms project*************");
		
		SpringApplication.run(Application.class, args);
	}

}
