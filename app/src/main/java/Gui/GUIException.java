package Gui;

public class GUIException extends Exception{
    private static final String ERROR = "GUI Error! ";

    public GUIException(String message) {
        super(ERROR + message);
    }
}
