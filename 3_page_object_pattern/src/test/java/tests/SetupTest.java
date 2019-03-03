package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class SetupTest {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    int timeOut = 10;

    @BeforeClass
    public void beforeClass() {
        //инициализация Вебдрайвера
        driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(timeOut,TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, timeOut);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
