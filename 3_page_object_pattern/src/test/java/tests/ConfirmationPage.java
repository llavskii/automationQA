package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPage extends PageObject {
    @FindBy(xpath = "//div/h1")
    WebElement thankYou;
    @FindBy(xpath = "//tbody/tr[1]/td[2]")
    WebElement Id;
    @FindBy(xpath = "//tbody/tr[2]/td[2]")
    WebElement pendingCapture;
    @FindBy(xpath = "//tbody/tr[3]/td[2]")
    WebElement amount;
    @FindBy(xpath = "//tbody/tr[4]/td[2]")
    WebElement cardNumber;
    @FindBy(xpath = "//tbody/tr[5]/td[2]")
    WebElement expiration;


    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    //Проверки на соответствие отображаемых данных введенным ранее и их видимость.
    public boolean viewPurchaseHasId() {
        return Id.isDisplayed() & Id.getText().length() > 1;
    }

    public boolean viewPurchaseHasStatus() {
        return pendingCapture.isDisplayed() & pendingCapture.getText().equals("PendingCapture");
    }

    public boolean viewPurchaseHasAmount() {
        return amount.isDisplayed() & amount.getText().trim().equals("USD");
    }

    public boolean viewPurchaseHasLast4CardNumbers() {
        return cardNumber.isDisplayed() &
                cardNumber.getText().trim().endsWith(Person.getCreditCardNumber().substring(Person.getCreditCardNumber().length() - 4));
    }

    public boolean viewPurchaseHasExpDateCard() {
        String expDateInForm = expiration.getText().replaceAll("[\\s/]", "");
        return expiration.isDisplayed() &
                expDateInForm.equals(Person.getCreditCardMonth() + Person.getCreditCardYear());
    }
}
