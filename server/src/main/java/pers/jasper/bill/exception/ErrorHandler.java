package pers.jasper.bill.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorResult> customExceptionHandler
            (HttpServletRequest req, CustomException e) throws Exception {
        ErrorResult errorResult = new ErrorResult(e.getStatus().value(), e.getErrorCode());
        return new ResponseEntity<>(errorResult, e.getStatus());
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity<ErrorResult> noHandlerFoundExceptionHandler
            (HttpServletRequest req, NoHandlerFoundException e) throws Exception {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorCode errorCode = ErrorCode.INVALID_RESOURCE;
        ErrorResult errorResult = new ErrorResult(status.value(), errorCode);
        return new ResponseEntity<>(errorResult, status);
    }

    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorResult> emptyResultDataAccessExceptionHandler
            (HttpServletRequest req, Exception e) throws Exception {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorCode errorCode = ErrorCode.INVALID_DATA;
        ErrorResult errorResult = new ErrorResult(status.value(), errorCode);
        return new ResponseEntity<>(errorResult, status);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ErrorResult> badCredentialsExceptionHandler
            (HttpServletRequest req, Exception e) throws Exception {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorCode errorCode = ErrorCode.WRONG_PASSWORD;
        ErrorResult errorResult = new ErrorResult(status.value(), errorCode);
        return new ResponseEntity<>(errorResult, status);
    }

    @ExceptionHandler(value = ParseException.class)
    public ResponseEntity<ErrorResult> parseExceptionHandler
            (HttpServletRequest req, Exception e) throws Exception {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorCode errorCode = ErrorCode.DATE_PARSE_ERROR;
        ErrorResult errorResult = new ErrorResult(status.value(), errorCode);
        return new ResponseEntity<>(errorResult, status);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResult> dataIntegrityViolationExceptionHandler
            (HttpServletRequest req, DataIntegrityViolationException e) throws Exception {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorCode errorCode = ErrorCode.DATABASE_ERROR;
        if(e.getCause() instanceof SQLIntegrityConstraintViolationException) {
            errorCode = ErrorCode.SQL_CONSTRAINT_ERROR;
        }
        ErrorResult errorResult = new ErrorResult(status.value(), errorCode);
        return new ResponseEntity<>(errorResult, status);
    }

    /**
     *  默认异常处理，前面未处理
     */
    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<ErrorResult> defaultHandler
                (HttpServletRequest req, Exception e) throws Exception {
        System.out.print(e);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorCode errorCode = ErrorCode.UN_KNOW;
        ErrorResult res = new ErrorResult(status.value(), errorCode);
        return new ResponseEntity<>(res, status);
    }
}
