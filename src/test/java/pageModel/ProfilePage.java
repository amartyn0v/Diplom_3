package pageModel;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage extends BasePage{

    private SelenideElement editProfileLabel = $(byXpath("//p[text()='В этом разделе вы можете изменить свои персональные данные']"));
    private SelenideElement emailInput = $(byXpath("//label[text()='Логин']/following::input"));
    private SelenideElement nameInput = $(byXpath("//label[text()='Имя']/following::input"));
    private SelenideElement passwordInput = $(byXpath("//label[text()='Пароль']/following::input"));
    private SelenideElement logoutButton = $(byXpath("//button[text()='Выход']"));

    public void waitForPageLoaded(){
        editProfileLabel.shouldBe(Condition.visible);
    }

    public boolean isPageLoaded(){
        return editProfileLabel.is(Condition.visible);
    }

    public String getEmailFromInput(){
        return emailInput.getAttribute("value");
    }

    public LoginPage logoutButtonCLick(){
        logoutButton.shouldBe(Condition.interactable).hover().click();
        LoginPage loginPage = new LoginPage();
        loginPage.waitForPageLoaded();
        return loginPage;
    }
}
