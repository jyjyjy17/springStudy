package hello.login.web;

import hello.login.web.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterrRegistrationBean = new FilterRegistrationBean<>();
        filterrRegistrationBean.setFilter(new LogFilter());
        filterrRegistrationBean.setOrder(1);
        filterrRegistrationBean.addUrlPatterns("/*");
        return filterrRegistrationBean;
    }

}
