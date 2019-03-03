package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Test extends SetupTest {
    @org.testng.annotations.Test
    public void test() {
        //Выбор из выпадающего списка города Portland, если такой город в списке есть.
        webElement = edgeDriver.findElement(By.xpath("//form/select[1]"));
        List<WebElement> allOptions = webElement.findElements(By.cssSelector("form > select:nth-child(1) > option"));
        for (WebElement we :
                allOptions) {
            //Вопрос. Вот на этом шаге просится проверка "А действительно ли мы выбрали город Portland Видно ли его название или нет?
            if (we.getAttribute("value").trim().equals("Portland")) we.click();
        }

        //Выбор из выпадающего списка города Каир, если такой город в списке есть.
        webElement = edgeDriver.findElement(By.xpath("//form/select[2]"));
        allOptions = webElement.findElements(By.cssSelector("form > select:nth-child(4) > option"));
        for (WebElement we : allOptions) {
            if (we.getAttribute("value").trim().equals("Cairo")) {
                we.click();
                break;
            }
            //Вопрос. Вот на этом шаге просится проверка "А действительно ли мы выбрали город Каир?
            // Видно ли его название или нет? А если какой нибудь прозрачный фрейм его перекрыл? Как проверить функционал для пользователя?
        }
        webElement = edgeDriver.findElement(By.cssSelector(".btn-primary"));
        webElement.click();

        //Вопрос. В этом месте, если нет вызова ожидания (manage().timeouts().implicitlyWait(timeOut,TimeUnit.SECONDS),
        // ошибка вида NoSuchElementException. Почему? Страница не до конца загрузилась, а поиск уже завершился неудачей?
        webElement = edgeDriver.findElement(By.cssSelector("div.container > h3"));
        webDriverWait.until(
                ExpectedConditions.textToBePresentInElement
                        (webElement, "Flights from Portland to Cairo: "));

        webElement = edgeDriver.findElement(By.xpath("//h3"));
        assertEquals(webElement.getText().trim(), "Flights from Portland to Cairo:");

        webElement = edgeDriver.findElement(By.xpath("//table/tbody"));
        List<WebElement> tableElementsPriceList = webElement.findElements(By.xpath("//table/tbody/tr/td[6]"));
        assertTrue(tableElementsPriceList.size() != 0);
        ArrayList<Double> prices = new ArrayList<Double>();
        for (WebElement we : tableElementsPriceList) {
            prices.add(Double.parseDouble(we.getText().substring(1)));
        }
        System.out.println(prices);

        //Находим рейс с минимальной стоимостью.
        //Запоминаем цену рейса с минимальной стоимостью.
        double minPrice = Collections.min(prices);
        int minRow = prices.indexOf(minPrice) + 1;
        //Запоминаем номер выбранного рейса с минимальной стоимостью.
        String flightNumber = edgeDriver.findElement(By.xpath(String.format("//tr[%d]/td[2]", minRow))).getText();
        //Запоминаем название авиакомпании выбранного рейса.
        String flightCompany = edgeDriver.findElement(By.xpath(String.format("//tr[%d]/td[3]", minRow))).getText();

        webElement = edgeDriver.findElement(By.xpath(String.format("//tr[%d]/td[1]/input", minRow)));
        webElement.click();

        //Проверки правильности выбранного рейса, названия авиакомпании и конечной цены.
        String flightCompanyInForm = edgeDriver.findElement(By.xpath("//p[1]")).getText();
        System.out.println(flightCompanyInForm);
        assertEquals(flightCompanyInForm.substring(flightCompanyInForm.lastIndexOf(": ") + 2), flightCompany);

        String flightNumberInForm = edgeDriver.findElement(By.xpath("//p[2]")).getText();
        assertEquals(flightNumberInForm.substring(flightNumberInForm.lastIndexOf(": ") + 2), flightNumber);

        String taxesStringInForm = edgeDriver.findElement(By.xpath("//p[4]")).getText();
        double taxesDouble = Double.parseDouble(taxesStringInForm.substring(taxesStringInForm.lastIndexOf(": ") + 2));
        assertEquals(Double.parseDouble(edgeDriver.findElement(By.xpath("//p[5]/em")).getText().trim()),
                minPrice + taxesDouble);

        //Заполнение полей, используя класс Person.
        Person person = new Person();
        webElement = edgeDriver.findElement(By.cssSelector("#inputName"));
        webElement.sendKeys(person.getName());

        webElement = edgeDriver.findElement(By.cssSelector("#address"));
        webElement.sendKeys(person.getAddress());

        webElement = edgeDriver.findElement(By.cssSelector("#city"));
        webElement.sendKeys(person.getCity());

        webElement = edgeDriver.findElement(By.cssSelector("#state"));
        webElement.sendKeys(person.getState());

        webElement = edgeDriver.findElement(By.cssSelector("#zipCode"));
        webElement.sendKeys(person.getZipCode());

        webElement = edgeDriver.findElement(By.cssSelector("#cardType"));
        allOptions = webElement.findElements(By.cssSelector("#cardType > option"));
        for (WebElement we : allOptions) {
            if (we.getText().trim().equals(person.getCardType())) {
                we.click();
                break;
            }
        }

        webElement = edgeDriver.findElement(By.cssSelector("#creditCardNumber"));
        webElement.sendKeys(person.getCreditCardNumber());

        webElement = edgeDriver.findElement(By.cssSelector("#creditCardMonth"));
        webElement.clear();
        webElement.sendKeys(person.getCreditCardMonth());

        webElement = edgeDriver.findElement(By.cssSelector("#creditCardYear"));
        webElement.clear();
        webElement.sendKeys(person.getCreditCardYear());

        webElement = edgeDriver.findElement(By.cssSelector("#nameOnCard"));
        webElement.sendKeys(person.getNameOnCard());

        //Чекбокс для подтверждения "запомнить" и кнопка для подтверждения - в самом низу.
        //Поэтому сделал прокрутку вниз, хотя и без них все проходит. Сделал для визуальности действий вебдрайвера. Может быть, это избыточно.
        JavascriptExecutor jse = (JavascriptExecutor) edgeDriver;
        jse.executeScript("window.scrollBy(0,250)", "");

        //Использовал класс Actions, цель - посмотреть его методы.
        Actions action = new Actions(edgeDriver);
        action.moveToElement(edgeDriver.findElement(By.xpath("//*[@id=\"rememberMe\"]"))).click().build().perform();
        action.moveToElement(edgeDriver.findElement(By.xpath("//div[11]/div/input"))).click().build().perform();

        webElement = edgeDriver.findElement(By.xpath("//div/h1"));
        webDriverWait.until(
                ExpectedConditions.textToBePresentInElement
                        (webElement, "Thank you for your purchase today!"));

        //Проверки на соответствие отображаемых данных введенным ранее и их видимость.
        webElement = edgeDriver.findElement(By.xpath("//tbody/tr[1]/td[2]"));
        boolean hasId = webElement.isDisplayed() & webElement.getText().length() > 1;

        webElement = edgeDriver.findElement(By.xpath("//tbody/tr[2]/td[2]"));
        boolean hasStatus = webElement.isDisplayed() & webElement.getText().equals("PendingCapture");

        webElement = edgeDriver.findElement(By.xpath("//tbody/tr[3]/td[2]"));
        boolean hasAmount = webElement.isDisplayed() & webElement.getText().trim().equals("USD");

        webElement = edgeDriver.findElement(By.xpath("//tbody/tr[4]/td[2]"));
        boolean hasLast4CardNumbers = webElement.isDisplayed() &
                webElement.getText().trim().endsWith(person.getCreditCardNumber().substring(person.getCreditCardNumber().length() - 4));
        System.out.println(person.getCreditCardNumber().substring(person.getCreditCardNumber().length() - 4));

        webElement = edgeDriver.findElement(By.xpath("//tbody/tr[5]/td[2]"));
        String expDateInForm = webElement.getText().replaceAll("[\\s/]", "");
        System.out.println(expDateInForm);
        boolean hasExpDateCard = webElement.isDisplayed() &
                expDateInForm.equals(person.getCreditCardMonth() + person.getCreditCardYear());

        assertTrue(hasId & hasStatus & hasAmount & hasLast4CardNumbers & hasExpDateCard);

    }
}
