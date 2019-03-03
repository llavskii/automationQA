package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends PageObject {

    @FindBy(xpath = "//*[@id=\"block_top_menu\"]/ul/li[1]/a")
    private WebElement womenButton;

    @FindBy (xpath = "//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[1]/ul/li[1]/a")
    private WebElement ShirtsUnderWomenButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getWomenButton() {
        return womenButton;
    }

    public WebElement getShirtsUnderWomenButton() {
        return ShirtsUnderWomenButton;
    }


}
