package uz.pdp.appshortlink.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestException extends RuntimeException {
    private String message;

    public static RestException restThrow(String message) {
        return new RestException(message);
    }

    public static RestException alreadyExist(String key) {
        return new RestException(key + " already exists");
    }
}
