package com.task.assesment.filters;

import com.task.assesment.exceptions.MissingMandatoryFieldsException;
import com.task.assesment.exceptions.UnauthorizedException;
import com.task.assesment.repository.StaffRepository;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

@AllArgsConstructor
public class StaffUuidValidationFilter implements Filter {

  public static final String STAFF_UUID_HEADER_NAME = "Staff-UUID";
  private final StaffRepository staffRepository;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    if(!"POST".equals(req.getMethod())) {
      String uuidHeader = req.getHeader(STAFF_UUID_HEADER_NAME);
      if(!StringUtils.hasText(uuidHeader)) {
        throw new MissingMandatoryFieldsException(String.format("Request missing required header: %s",
            STAFF_UUID_HEADER_NAME));
      }
      UUID staffUuid = UUID.fromString(uuidHeader);

      if(!staffRepository.existsByUuidEquals(staffUuid)) {
        throw new UnauthorizedException(String.format("Staff Uuid '%s' not valid!", staffUuid));
      }
    }

    chain.doFilter(request, response);
  }
}
