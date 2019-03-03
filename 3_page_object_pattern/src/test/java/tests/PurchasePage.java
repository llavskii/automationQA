package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PurchasePage extends PageObject {

    @FindBy(css = "#inputName")
    private WebElement name;
    @FindBy(css = "#address")
    private WebElement address;
    @FindBy(css = "#city")
    private WebElement city;
    @FindBy(css = "#state")
    private WebElement state;
    @FindBy(css = "#zipCode")
    private WebElement zipCode;
    @FindBy(css = "#cardType")
    private WebElement cardType;
    @FindBy(css = "#creditCardNumber")
    private WebElement creditCardNumber;
    @FindBy(css = "#creditCardMonth")
    private WebElement creditCardMonth;
    @FindBy(css = "#creditCardYear")
    private WebElement creditCardYear;
    @FindBy(css = "#nameOnCard")
    private WebElement nameOnCard;
    @FindBy(xpath = "//*[@id=\"rememberMe\"]")
    private WebElement rememberMe;
    @FindBy(xpath = "//div[11]/div/input")
    private WebElement submitButton;


    public PurchasePage(WebDriver driver) {
        super(driver);
    }

    public void fillingFormPersonalityData() {
        name.sendKeys(Person.getName());
        address.sendKeys(Person.getAddress());
        city.sendKeys(Person.getCity());
        state.sendKeys(Person.getState());
        zipCode.sendKeys(Person.getZipCode());

    }

    public void fillingFormCreditCardData() {
        List<WebElement> allOptions = cardType.findElements(By.cssSelector("#cardType > option"));
        for (WebElement we : allOptions) {
            if (we.getText().trim().equals(Person.getCardType())) {
                we.click();
                break;
            }
        }
        creditCardNumber.sendKeys(Person.getCreditCardNumber());
        creditCardMonth.clear();
        creditCardMonth.sendKeys(Person.getCreditCardMonth());
        creditCardYear.clear();
        creditCardYear.sendKeys(Person.getCreditCardYear());
        nameOnCard.sendKeys(Person.getNameOnCard());
    }
    public ConfirmationPage submit() {
        //Чекбокс для подтверждения "запомнить" и кнопка для подтверждения - в самом низу.
        //Поэтому сделал прокрутку вниз, хотя и без них все проходит. Сделал для визуальности действий вебдрайвера. Может быть, это избыточно.
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)", "");
        //Использовал класс Actions, цель - посмотреть его методы.
        Actions action = new Actions(driver);
        action.moveToElement(rememberMe).click().build().perform();
        action.moveToElement(submitButton).click().build().perform();
        return new ConfirmationPage(driver);
    }
}
