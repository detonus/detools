package com.detoworks.error;

/**
 * Created by Banach on 2017-02-07.
 */
public class RegExpNotFoundException extends Exception {

    public RegExpNotFoundException(String message) {
        super(message);
    }

    public RegExpNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
