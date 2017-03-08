package app.exceptions;


public class WrongUserCredentialsException extends Exception {

    public WrongUserCredentialsException(String message) {
        super("Wrong credentials!!");
    }
}
