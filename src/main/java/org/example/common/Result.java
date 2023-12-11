package org.example.common;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;


@Data
@NoArgsConstructor
public class Result<T> {

    private int errCode;
    private String message;

    // make data to expose on top level, not expose on the data
    @JsonUnwrapped
    private T data;

    public Result(int errCode, String message, T data) {
        this.errCode = errCode;
        this.message = message;
        this.data = data;
        modify();
    }

    private void modify() {
        if (!StringUtils.hasLength(this.message)) {
            this.message = "ok";
        }
    }

    public static <T> Result<T> ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(T data) {
        return ok(0, null, data);
    }

    public static <T> Result<T> ok(String message) {
        return ok(0, message, null);
    }

    public static <T> Result<T> ok(int code, T data) {
        return ok(code, null, data);
    }

    public static <T> Result<T> ok(String message, T data) {
        return ok(0, message, data);
    }

    public static <T> Result<T> ok(int errCode, String message) {
        return ok(errCode, message, null);
    }

    public static <T> Result<T> ok(int errCode, String message, T data) {
        return new Result<>(errCode, message, data);
    }
}
