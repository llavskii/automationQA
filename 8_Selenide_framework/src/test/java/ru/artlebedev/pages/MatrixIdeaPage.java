package ru.artlebedev.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MatrixIdeaPage {
    private static final By INPUT_SEARCH_FORM = By.xpath("//input[1]");
    private static final By INPUT_SEARCH_BUTTON = By.xpath("//a[text()='Инвентарь']");
    private static final By SEARCH_RESULT_TABLE = By.xpath("//div[contains(text(),'Матрица идей')]");


    public void searchMatrixIdea(String query) {
        $(INPUT_SEARCH_FORM).clear();
        $(INPUT_SEARCH_FORM).setValue(query);
        $(INPUT_SEARCH_BUTTON).click();
    }

    public String checkSearchResult(String text) {
        return $(SEARCH_RESULT_TABLE).innerText();
    }

    public boolean checkOutSearchResult(String text) {
        return checkSearchResult(text).contains(text);
    }
}
