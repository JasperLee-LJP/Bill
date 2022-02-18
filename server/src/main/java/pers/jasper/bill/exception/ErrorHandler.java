package pers.jasper.bill.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorResult> customExceptionHandler
            (HttpServletRequest req, CustomException e) throws Exception {
        ErrorResult errorResult = new ErrorResult(e.getStatus().value(),
                e.getErrorCode().value(), e.getErrorCode().getMessage());
        return new ResponseEntity<>(errorResult, e.getStatus());
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity<ErrorResult> noHandlerFoundExceptionHandler
            (HttpServletRequest req, NoHandlerFoundException e) throws Exception {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorCode errorCode = ErrorCode.INVALID_RESOURCE;
        ErrorResult errorResult = new ErrorResult(status.value(), errorCode.value(),
                errorCode.getMessage());
        return new ResponseEntity<>(errorResult, status);
    }

    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorResult> EmptyResultDataAccessExceptionHandler
            (HttpServletRequest req, Exception e) throws Exception {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorCode errorCode = ErrorCode.INVALID_DATA;
        ErrorResult errorResult = new ErrorResult(status.value(), errorCode.value(),
                errorCode.getMessage());
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
        ErrorResult res = new ErrorResult(status.value(), errorCode.value(), errorCode.getMessage());
        return new ResponseEntity<>(res, status);
    }
}
