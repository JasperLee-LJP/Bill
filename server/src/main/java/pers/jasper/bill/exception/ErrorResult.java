package pers.jasper.bill.exception;

public class ErrorResult {
    private String message;
    private int errorCode;
    private int status;

    public ErrorResult(int status, String message) {
        this(status, ErrorCode.UN_KNOW.value(), message);
    }

    public ErrorResult(int status, ErrorCode code) {
        this(status, code.value(), code.getMessage());
    }

    public ErrorResult(int status, int errorCode, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
