package org.test.elevator.Exception;

public class StatusMessageException extends Exception{

    public StatusMessageException() {
        super();
    }
    public StatusMessageException(String s) {
        super(s);
    }
    public StatusMessageException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public StatusMessageException(Throwable throwable) {
        super(throwable);
    }

}