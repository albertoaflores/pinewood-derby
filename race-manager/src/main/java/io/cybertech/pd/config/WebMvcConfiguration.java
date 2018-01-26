package io.cybertech.pd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("login");
		registry.addViewController("/home").setViewName("login");
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/page/registration").setViewName("registration");
		registry.addViewController("/page/ranking").setViewName("ranking");
    }
}
