package list;

public class EmptyListException extends RuntimeException {
    public EmptyListException(String name) {
        super("The " + name + " is empty");
    }
}

