package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class SetupTest {
    protected WebDriver edgeDriver;
    protected WebElement webElement;
    protected WebDriverWait webDriverWait;
    int timeOut = 10;

    @BeforeClass
    public void beforeClass() {
        //инициализация Вебдрайвера
        edgeDriver = new InternetExplorerDriver();
        edgeDriver.manage().timeouts().implicitlyWait(timeOut,TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(edgeDriver, timeOut);

        //Разворачивание окна
        edgeDriver.manage().window().maximize();
        //Открытие страницы тестируемого сайта
        edgeDriver.get("http://blazedemo.com/");

    }

    @AfterClass
    public void afterClass() {
        edgeDriver.quit();
    }

}
