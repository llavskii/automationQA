package ru.artlebedev.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;


public class ToolsPage {
    private static final By MATRIX_IDEA_BUTTON = By.id("item-matrix");

    public MatrixIdeaPage clickOnMatrixIdea() {
        $(MATRIX_IDEA_BUTTON).click();
        return page(MatrixIdeaPage.class);
    }
}
