package com.hit.store.controller.ex;

public class FileNullException extends FileUploadException{
    public FileNullException() {
        super();
    }

    public FileNullException(String message) {
        super(message);
    }

    public FileNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileNullException(Throwable cause) {
        super(cause);
    }
}
