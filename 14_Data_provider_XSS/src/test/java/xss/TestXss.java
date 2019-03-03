package xss;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestXss {

    public WebDriver driver;
    public WebDriverWait webDriverWait;

    @Test(dataProvider = "xss")
    public void xssTest(String xss) {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.get("http://mail.ru");
        WebElement searchElement = driver.findElement(By.xpath("//*[@id=\"q\"]"));
        searchElement.sendKeys(xss);
        WebElement buttonElement = driver.findElement(By.xpath("//*[@id=\"search:submit\"]"));
        buttonElement.click();
        if (driver.getCurrentUrl().contains(xss)) {
            System.out.println("found xss injection: " + xss);
        }
        driver.quit();
    }

    @DataProvider(name = "xss")
    public static Object[] getCheatSheet() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/xss.txt"));
        List<String> lines = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
        String[] data = lines.toArray(new String[]{});
        return data;
    }
}
