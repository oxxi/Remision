package hn.com.tigo.remision.exceptions;

public class BadRequestException extends RuntimeException {

    private String message;
    private String internalMessage;
    private String moreInfo;

    public BadRequestException() {
        super();
    }
    public BadRequestException(String message) {
        super(message);
    }
}
