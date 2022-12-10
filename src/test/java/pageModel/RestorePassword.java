package pageModel;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RestorePassword extends BasePage{

    private final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    private SelenideElement signInLink = $(byXpath("//a[text()='Войти']"));

    public void openPage(){
        open(URL);
    }

    public LoginPage signInLinkClick(){
        signInLink.shouldBe(Condition.interactable).hover().click();
        LoginPage loginPage = new LoginPage();
        loginPage.waitForPageLoaded();
        return loginPage;
    }
}
