package Cardgame;

public class SpielException extends Exception{
    private static final String ERROR = "Error! ";

    public SpielException(String message) {
        super(ERROR + message);
    }
}
