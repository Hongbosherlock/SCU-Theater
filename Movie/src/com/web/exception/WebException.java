/**
 * @author qiaoger@126.com
 */
package com.web.exception;

/**
 * 每个站点都应设计一个专门的异常类
 * @author qiaoger@126.com
 */
public class WebException extends Exception {
    public WebException() {
        super();
    }

    public WebException(String message) {
        super(message);
    }

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }
}
