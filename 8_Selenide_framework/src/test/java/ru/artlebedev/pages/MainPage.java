package ru.artlebedev.pages;


import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private static final By TOOLS = By.xpath("//menu/li[2]/a");

    public static MainPage goToMainPage() {
        open("https://www.artlebedev.ru/");
        return page(MainPage.class);
    }

    public ToolsPage clickOnTools() {
        $(TOOLS).click();
        return page(ToolsPage.class);
    }
}
