package pers.jasper.bill.exception;

public enum ErrorCode {
    UN_KNOW(10000, "服务器错误，请联系管理员"),
    UNAUTHORIZED(10001, "未登录"),
    INVALID_DATA(10002, "未找到相关数据"),
    INVALID_RESOURCE(10003, "未找到相关资源"),
    FORBIDDEN(10003, "没有访问权限"),

    WRONG_PASSWORD(10100, "用户名或密码错误"),
    USER_NOT_FOUND(10101, "用户不存在"),
    USER_EXIST(10102, "用户已存在"),
    NOT_FUND(10103, "资源不存在"),
    DATE_PARSE_ERROR(10104, "日期格式错误"),
    DATABASE_ERROR(10105, "数据库错误"),
    SQL_CONSTRAINT_ERROR(10106, "请先删除关联的数据");

    private final int value;
    private final String message;

    ErrorCode(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int value() {
        return this.value;
    }

    public String getMessage() {
        return this.message;
    }
}
