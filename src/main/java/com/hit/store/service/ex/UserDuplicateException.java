package com.hit.store.service.ex;

/**
 *  用户名字已经被占用异常
 */
public class UserDuplicateException extends ServiceException{
    public UserDuplicateException() {
        super();
    }

    public UserDuplicateException(String message) {
        super(message);
    }

    public UserDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDuplicateException(Throwable cause) {
        super(cause);
    }

    protected UserDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
