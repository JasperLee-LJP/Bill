package pers.jasper.bill.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import pers.jasper.bill.exception.ErrorCode;
import pers.jasper.bill.exception.ErrorResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
   @Autowired
   private ResponseWriter writer;

   @Override
   public void commence(HttpServletRequest request,
                        HttpServletResponse response,
                        AuthenticationException authException) throws IOException {
      // This is invoked when user tries to access a secured REST resource without supplying any credentials
      // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
      // Here you can place any message you want
      ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
      ErrorResult errorResult = new ErrorResult(HttpServletResponse.SC_UNAUTHORIZED, errorCode);
      writer.sendError(response, errorResult);
   }
}
