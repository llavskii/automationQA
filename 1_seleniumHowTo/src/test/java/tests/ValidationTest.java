package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ValidationTest {


    //Тест для проверки правильности выбора первого в списке авиарейса из Филадельфии в Дублин.
    @Test
    public void testMethod() {
        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.get("http://blazedemo.com/");

        Select selectFromPort = new Select(chromeDriver.findElement(By.xpath("//form/select[1]")));
        selectFromPort.selectByIndex(1);

        Select selectToPort = new Select(chromeDriver.findElement(By.xpath("//form/select[2]")));
        selectToPort.selectByIndex(5);

        WebElement listFromPort = chromeDriver.findElement(By.cssSelector(".btn-primary"));
        listFromPort.click();

        //Выбор первого попавшегося авиарейса.
        WebElement selectFirstFlight = chromeDriver.findElement(By.xpath("//tbody/tr/td/input"));

        //Тред ожидает 2 секунды. Нужно, чтобы визуально оценить правильный выбор аэропортов.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectFirstFlight.click();
        WebElement nameFromToFlight = chromeDriver.findElement(By.xpath("//div/h2"));
        //Условие успешного прохождения теста: title страницы и название авиаперелета (из Филадельфии в Дублин) соответствуют ожидаемым.
        assertTrue(chromeDriver.getTitle().equals("BlazeDemo Purchase") & nameFromToFlight.getText().equals("Your flight from Philadelphia to Dublin has been reserved."), "Test passed!");

        //Тред ожидает 2 секунды: нужно оценить итоговый выбор пунктов авиаперелета.
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Закрытие браузера.
        chromeDriver.close();
        //Закрытие вебдрайвера
        chromeDriver.quit();
    }
}
