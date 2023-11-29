package com.backend.travel.common;

/**
 * 错误码
 */
public enum ErrorCode {

    SUCCESS(0, "ok"),
    ACCOUNT_BLOCK(30000, "账号已被封禁"),
    PARAMS_ERROR(40000, "请求参数错误"),
    PARAMS_EMPTY_ERROR(40001, "参数不能为空"),
    DATA_EXIST_ERROR(40002, "数据已存在"),
    DATA_INSERT_ERROR(40003, "数据插入错误"),
    DATA_UPDATE_ERROR(40004, "数据修改错误"),
    DATA_FORBIDDEN_WRITE_ERROR(40005, "数据禁止修改"),
    DATA_DELETE_ERROR(40006, "数据删除失败"),
    NOT_LOGIN_ERROR(40100, "未登录"),
    AUTHENTICATION_FAILED(40102, "认证失败"),
    NO_AUTH_ERROR(40101, "无权限"),
    NOT_FOUND_ERROR(40400, "请求数据不存在"),
    FORBIDDEN_ERROR(40300, "禁止访问"),
    SYSTEM_ERROR(50000, "系统内部异常"),
    OPERATION_ERROR(50001, "操作失败"),
    TOO_MANY_REQUEST(50002, "请求次数太多");


    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
