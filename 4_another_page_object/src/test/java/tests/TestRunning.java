package tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class TestRunning extends SetupTest {
    @Test
    public void signUp() {
        driver.get("http://automationpractice.com/index.php");
        MainPage mainPage = new MainPage(driver);

        //Двигаем мышь в левый верхний угол экрана, чтобы ее положение случайно не оказалось на кнопке.
        action.moveByOffset(0, 0).perform();

        //Запоминаем размер кнопки до движения
        Dimension dimensionBefore = mainPage.getDimensionOfWE(mainPage.getWomenButton());

        //Двигаем мышь на кнопку
        action.moveToElement(mainPage.getWomenButton()).moveToElement(mainPage.getWomenButton()).perform();

        //Запонимаем размер кнопки после движения на нее мыши
        Dimension dimensionAfter = mainPage.getDimensionOfWE(mainPage.getWomenButton());

        //Сравниваем размеры кнопки до и после
        assertEquals(dimensionAfter, dimensionBefore);

        //Сравниваем цвет кнопки
        String colorRGBa = mainPage.getRGBa(mainPage.getWomenButton());
        assertEquals("51, 51, 51, 1", colorRGBa);

        //Ожидание и проверка видимости списка по наведению на кнопку и переход на страницу с T-Shirts
        TShirtsPage tShirtsPage = mainPage.waitForVisibilityOfElementAndGoToPage(mainPage.getShirtsUnderWomenButton());
        new WebDriverWait(driver, SetupTest.getTimeOut()).until(tShirtsPage.visibilityOfElement(tShirtsPage.getPageBanner()));
        assertEquals("T-shirts - My Store", tShirtsPage.driver.getTitle());
    }
}
