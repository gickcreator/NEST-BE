package ssu.opensource.discord.config;

import ssu.opensource.discord.filter.MDCFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ssu.opensource.discord.filter.ServletWrappingFilter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<ServletWrappingFilter> secondFilter() {
        FilterRegistrationBean<ServletWrappingFilter> filterRegistrationBean = new FilterRegistrationBean<>(
                new ServletWrappingFilter());
        filterRegistrationBean.setOrder(0);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<MDCFilter> thirdFilter() {
        FilterRegistrationBean<MDCFilter> filterRegistrationBean = new FilterRegistrationBean<>(
                new MDCFilter());
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}