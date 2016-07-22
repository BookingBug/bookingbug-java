package bookingbugAPI.models;

import java.io.IOException;


public class HttpException extends IOException {
    int statusCode;
    String errorMessage;
    String rawResponse;


    public HttpException(String message) {
        super(message);
    }


    public HttpException(String message, Throwable throwable){
        super(message, throwable);
        errorMessage = throwable.getMessage();
    }


    public HttpException(String message, String resp, Throwable throwable){
        super(message, throwable);
        errorMessage = throwable.getMessage();
        rawResponse = resp;
    }


    public HttpException(String errorMessage, String resp, int statusCode){
        this.errorMessage = errorMessage;
        this.rawResponse = resp;
        this.statusCode = statusCode;
    }


    public int getStatusCode() {
        return statusCode;
    }


    public String getErrorMessage() {
        return errorMessage;
    }


    public String getRawResponse() {
        return rawResponse;
    }


    @Override
    public String toString(){
        return "Message: " + errorMessage + "\nBody: " + rawResponse;
    }

}
