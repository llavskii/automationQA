package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TShirtsPage extends PageObject {

    @FindBy (xpath = "//*[@id=\"center_column\"]/div[1]/div/div/span")
    private WebElement pageBanner;

    public TShirtsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageBanner() {
        return pageBanner;
    }
}
