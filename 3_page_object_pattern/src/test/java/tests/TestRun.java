package tests;

import static org.testng.Assert.assertEquals;

public class TestRun extends SetupTest{

    @org.testng.annotations.Test
    public void signUp() {
        //Открытие страницы тестируемого сайта
        driver.get("http://blazedemo.com/");

        ChoiseFlight choiseFlight = new ChoiseFlight(driver);
        choiseFlight.selectFirstAndSecondCity("Portland", "Cairo");

        ReservePage reservePage = choiseFlight.submit();
        reservePage.selectFlightFromTable();

        PurchasePage purchasePage = reservePage.submit();

        //Разделил заполнение данных по принципу: персональные данные и данные кредитной карты отдельно.
        purchasePage.fillingFormPersonalityData();
        purchasePage.fillingFormCreditCardData();

        ConfirmationPage confirmationPage = purchasePage.submit();

        // Использовал assertEqual вместо assertTRue
        assertEquals(confirmationPage.viewPurchaseHasId(), true);
        assertEquals(confirmationPage.viewPurchaseHasStatus(), true);
        assertEquals(confirmationPage.viewPurchaseHasAmount(), true);
        assertEquals(confirmationPage.viewPurchaseHasExpDateCard(), true);
        assertEquals(confirmationPage.viewPurchaseHasLast4CardNumbers(), true);

    }
}
