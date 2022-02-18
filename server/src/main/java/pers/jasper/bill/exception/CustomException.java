package pers.jasper.bill.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends  RuntimeException {
    private HttpStatus status;
    private ErrorCode errorCode;

    public CustomException(HttpStatus status) {
        this(status, ErrorCode.UN_KNOW);
    }

    public CustomException(HttpStatus status, ErrorCode errorCode) {
        this.status = status;
        this.errorCode = errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
