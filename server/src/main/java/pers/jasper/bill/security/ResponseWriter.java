package pers.jasper.bill.security;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pers.jasper.bill.exception.ErrorResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class ResponseWriter {
    @Autowired
    private ObjectMapper mapper;

    public <T>void sendData(HttpServletResponse response, int status, T data) {
        write(response, status, data);
    }

    public void sendError(HttpServletResponse response, ErrorResult errorResult) {
        write(response, errorResult.getStatus(), errorResult);
    }

    private <T>void write(HttpServletResponse response, int httpStatus, T data) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.setStatus(httpStatus);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(mapper.writeValueAsString(data));
            writer.flush();
        } catch (IOException e) {

        } finally {
            if(writer != null) {
                writer.close();
            }
        }
    }
}
