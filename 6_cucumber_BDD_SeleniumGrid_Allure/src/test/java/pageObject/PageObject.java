package pageObject;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {

    protected WebDriver driver;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Dimension getDimensionOfWE(WebElement webElement) {
        return webElement.getSize();
    }

    public String getColor(WebElement webElement) {
        return webElement.getCssValue("background-color");

    }

    public String getOpacity(WebElement webElement) {
        return webElement.getCssValue("opacity");
    }

    public String getRGBa(WebElement webElement) {
        //Отдельная проверка на браузер, отличие в возвращаемом цвете rgb/rgba
        String color = getColor(webElement);
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = caps.getBrowserName();
        System.out.println(browserName + ":  " + color);
        if (browserName.equals("MicrosoftEdge")) {

            String opacity = getOpacity(webElement);
            String s1 = color.substring(4, color.length() - 1);
            return s1 + ", " + opacity;
        }
        return color.substring(5, color.length() - 1);

    }

    public ExpectedCondition<WebElement> visibilityOfElement(WebElement element) {
        return ExpectedConditions.visibilityOf(element);
    }

    public TShirtsPage waitForVisibilityOfElementAndGoToPage(WebElement element) {
        new WebDriverWait(driver, 5).until(visibilityOfElement(element));
        element.click();
        return new TShirtsPage(driver);
    }
}
