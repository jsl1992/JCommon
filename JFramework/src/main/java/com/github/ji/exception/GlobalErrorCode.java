package com.github.ji.exception;

public enum GlobalErrorCode {

    SUCCESS(0, "成功"),

    // ========== 客户端错误段 ==========

    BAD_REQUEST(400, "请求参数不正确"),
    UNAUTHORIZED(401, "账号未登录"),
    FORBIDDEN(403, "没有该操作权限"),
    NOT_FOUND(404, "请求未找到"),
    METHOD_NOT_ALLOWED(405, "请求方法不正确"),

    // ========== 服务端错误段 ==========

    INTERNAL_SERVER_ERROR(500, "系统异常"),
    SERVICE_ERROR(502, "请求方法不正确"),

    UNKNOWN(999, "未知错误");

    private final int code;
    private final String message;

    GlobalErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static boolean isMatch(int code) {
        return code >= SUCCESS.getCode() && code <= UNKNOWN.getCode();
    }
}
