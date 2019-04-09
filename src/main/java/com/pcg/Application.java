package com.pcg;


import com.pcg.filter.CrossDomainFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public FilterRegistrationBean apiOriginFilter() {
		FilterRegistrationBean reg = new FilterRegistrationBean();
		reg.setFilter(new CrossDomainFilter ());
		reg.setOrder(0);
		reg.addUrlPatterns("/websocket/*");
		return reg;
	}

}