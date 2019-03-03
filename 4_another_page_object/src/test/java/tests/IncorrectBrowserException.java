package tests;

import org.openqa.selenium.WebDriverException;

public class IncorrectBrowserException extends WebDriverException {
    public IncorrectBrowserException() {
        super("Non valid browser!");
    }

    public IncorrectBrowserException(String message) {
        super(message);
    }
}
