package ru.artlebedev.test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.artlebedev.pages.MainPage;
import ru.artlebedev.pages.MatrixIdeaPage;
import ru.artlebedev.pages.ToolsPage;

import static com.codeborne.selenide.Configuration.*;
import static ru.artlebedev.pages.MainPage.goToMainPage;

public class ArtlebedevTest {
    @BeforeTest
    public void beforeTest() {
        timeout = 10000;
//        baseUrl = "/";
        startMaximized = true;
        browser = "chrome";
    }
    @Test
    public void testingSite() {
        MainPage mainPage = goToMainPage();
        ToolsPage toolsPage = mainPage.clickOnTools();
        MatrixIdeaPage matrixIdeaPage = toolsPage.clickOnMatrixIdea();
        matrixIdeaPage.searchMatrixIdea("совы кофе");
        Assert.assertTrue(matrixIdeaPage.checkOutSearchResult("совы кофе"));
    }

}
