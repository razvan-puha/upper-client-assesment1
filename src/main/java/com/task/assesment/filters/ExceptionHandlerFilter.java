package com.task.assesment.filters;

import com.task.assesment.exceptions.MissingMandatoryFieldsException;
import com.task.assesment.exceptions.UnauthorizedException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    }
    catch (MissingMandatoryFieldsException e) {
      response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
      response.getWriter().write(e.getMessage());
    }
    catch (UnauthorizedException e) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write(e.getMessage());
    }
  }
}
