package com.task.assesment.configurations;

import com.task.assesment.filters.ExceptionHandlerFilter;
import com.task.assesment.filters.StaffUuidValidationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final ExceptionHandlerFilter exceptionHandlerFilter;

  public SecurityConfiguration(ExceptionHandlerFilter exceptionHandlerFilter) {
    super(true);
    this.exceptionHandlerFilter = exceptionHandlerFilter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.addFilterBefore(exceptionHandlerFilter, BasicAuthenticationFilter.class);
  }
}
