package com.task.assesment.configurations;

import com.task.assesment.filters.StaffUuidValidationFilter;
import com.task.assesment.repository.StaffRepository;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

  @Bean
  public FilterRegistrationBean<StaffUuidValidationFilter> staffUuidValidationFilterRegistrationBean(
      StaffRepository staffRepository) {
    FilterRegistrationBean<StaffUuidValidationFilter> validationBean = new FilterRegistrationBean<>();

    validationBean.setFilter(new StaffUuidValidationFilter(staffRepository));
    validationBean.addUrlPatterns("/api/staff/*", "/api/patients/*");
    validationBean.setOrder(2);

    return validationBean;
  }

}
