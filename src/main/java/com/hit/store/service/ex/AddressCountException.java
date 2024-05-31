package com.hit.store.service.ex;

/**
 * 地址数量过多
 */
public class AddressCountException extends ServiceException{
    public AddressCountException() {
    }

    public AddressCountException(String message) {
        super(message);
    }

    public AddressCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressCountException(Throwable cause) {
        super(cause);
    }

    public AddressCountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
