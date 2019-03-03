package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ReservePage extends PageObject {
    @FindBy(xpath = "//table/tbody")
    private WebElement tableFlights;
    private int minRow;

    public ReservePage(WebDriver driver) {
        super(driver);
    }

    public void selectFlightFromTable() {
        List<WebElement> tableElementsPriceList = tableFlights.findElements(By.xpath("//table/tbody/tr/td[6]"));
        assertTrue(tableElementsPriceList.size() != 0);
        ArrayList<Double> prices = new ArrayList<Double>();
        for (WebElement we : tableElementsPriceList) {
            prices.add(Double.parseDouble(we.getText().substring(1)));
        }
        double minPrice = Collections.min(prices);
        minRow = prices.indexOf(minPrice) + 1;
        //Запоминаем номер выбранного рейса с минимальной стоимостью.
        String flightNumber = driver.findElement(By.xpath(String.format("//tr[%d]/td[2]", minRow))).getText();
        Person.setFlightNumber(flightNumber);
    }

    public PurchasePage submit() {
        driver.findElement(By.xpath(String.format("//tr[%d]/td[1]/input",minRow))).click();
        return new PurchasePage(driver);
    }

}
