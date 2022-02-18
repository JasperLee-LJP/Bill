package pers.jasper.bill.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import pers.jasper.bill.exception.ErrorCode;
import pers.jasper.bill.exception.ErrorResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Autowired
    private ResponseWriter writer;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // This is invoked when user tries to access a secured REST resource without the necessary authorization
        // We should just send a 403 Forbidden response because there is no 'error' page to redirect to
        // Here you can place any message you want
        ErrorCode errorCode = ErrorCode.FORBIDDEN;
        ErrorResult errorResult = new ErrorResult(HttpServletResponse.SC_FORBIDDEN, errorCode);
        writer.sendError(response, errorResult);
    }
}
