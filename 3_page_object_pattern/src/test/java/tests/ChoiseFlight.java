package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ChoiseFlight extends PageObject {
    @FindBy(xpath = "//form/select[1]")
    private WebElement firstCityForm;

    @FindBy(xpath = "//form/select[2]")
    private WebElement secondCityForm;

    @FindBy(css = ".btn-primary")
    private WebElement submitFindButton;


    public ChoiseFlight(WebDriver driver) {
        super(driver);
    }

    public void selectFirstAndSecondCity(String nameFirstCity, String nameSecondCity) {
        List<WebElement> allOptions = firstCityForm.findElements(By.cssSelector("form > select:nth-child(1) > option"));
        for (WebElement we :
                allOptions) {
            if (we.getAttribute("value").trim().equals(nameFirstCity)) we.click();
        }
        allOptions = secondCityForm.findElements(By.cssSelector("form > select:nth-child(4) > option"));
        for (WebElement we :
                allOptions) {
            if (we.getAttribute("value").trim().equals(nameSecondCity)) we.click();
        }
    }

    public ReservePage submit() {
        submitFindButton.click();
        return new ReservePage(driver);
    }


}
