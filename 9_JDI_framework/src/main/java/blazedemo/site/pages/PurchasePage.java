package blazedemo.site.pages;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import org.openqa.selenium.support.FindBy;

@JPage(url = "/purchase.php", title = "BlazeDemo Purchase")
public class PurchasePage extends WebPage {
    @FindBy(xpath = "//div[11]/div/input")
    public Button submitButton;
}
