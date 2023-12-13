package itemservice.exception;

import itemservice.exception.filter.LogFilter;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterBean = new FilterRegistrationBean<>();
        filterBean.setFilter(new LogFilter());
        filterBean.setOrder(1);
        filterBean.addUrlPatterns("/*");
//        filterBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);
        return filterBean;
    }

}
