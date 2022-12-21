package hn.com.tigo.remision.exceptions;


import lombok.Getter;

@Getter
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

    public BadRequestException(String message, String internalMessage, String moreInfo){
        this.message = message;
        this.internalMessage = internalMessage;
        this.moreInfo = moreInfo;
    }
}
