package app.exceptions;


public class UserAlreadyExistsException extends Exception {

    private String userName;

    public UserAlreadyExistsException(String name) {
        super("User with name " + name + " already exists, try another one");
        this.userName = name;
    }

    public String getUserName() {
        return userName;
    }
}
