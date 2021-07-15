package com.example.ProgettoPennaWeb.model.utility;

public class MalformedFasciaOrariaException extends Exception{
    public MalformedFasciaOrariaException() {
        super();
    }

    public MalformedFasciaOrariaException(String message) {
        super(message);
    }

    public MalformedFasciaOrariaException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedFasciaOrariaException(Throwable cause) {
        super(cause);
    }

    protected MalformedFasciaOrariaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
