package pageModel;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.ObjectUtils;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends BasePage{

    private final String URL = "https://stellarburgers.nomoreparties.site/login";
    private SelenideElement emailInput = $(byXpath("//label[text()='Email']/following::input"));
    private SelenideElement passwordInput = $(byXpath("//label[text()='Пароль']/following::input"));
    private SelenideElement submitBtn = $(byXpath("//button[text()='Войти']"));
    private SelenideElement registerLink = $(byXpath("//a[text()='Зарегистрироваться']"));
    private SelenideElement headerLabel = $(byXpath("//div/h2[text()='Вход']"));

    public void openPage(){
        open(URL);
    }
    public void setEmail(String text){
        emailInput.shouldBe(Condition.editable).sendKeys(text);
    }

    public void setPassword(String text){
        passwordInput.shouldBe(Condition.editable).sendKeys(text);
    }

    public void clickSignInButton(){
        Selenide.sleep(1000);
        submitBtn.shouldBe(Condition.interactable).hover().click();
    }

    public ConstructorPage signIn(String email, String password){
        setEmail(email);
        setPassword(password);
        clickSignInButton();
        ConstructorPage constructorPage = new ConstructorPage();
        constructorPage.waitForPageLoaded();
        return constructorPage;
    }

    public void waitForPageLoaded(){
        headerLabel.shouldBe(Condition.visible);
    }

    public boolean isPageLoaded(){
        return headerLabel.is(Condition.visible);
    }


    public RegisterPage openRegisterPage(){
        registerLink.shouldBe(Condition.visible).click();
        RegisterPage registerPage = new RegisterPage();
        registerPage.waitForPageLoaded();
        return registerPage;
    }

    public void openLoginPageFromConstructorPage(){
        ConstructorPage constructorPage = new ConstructorPage();
        constructorPage.openPage();
        constructorPage.loginButtonClick();
        LoginPage loginPage = new LoginPage();
        loginPage.waitForPageLoaded();
    }

    public void openLoginPageFromProfileTab(){
        ConstructorPage constructorPage = new ConstructorPage();
        constructorPage.openPage();
        LoginPage loginPage = constructorPage.clickProfileTabWhenUnauthorized();
        loginPage.waitForPageLoaded();
    }

    public void openLoginPageFromRegisterPage(){
        RegisterPage registerPage = new RegisterPage();
        registerPage.openPage();
        LoginPage loginPage = registerPage.openLoginPage();
        loginPage.waitForPageLoaded();
    }

    public void openLoginPageFromRestorePassPage(){
        RestorePassword restorePasswordPage = new RestorePassword();
        restorePasswordPage.openPage();
        LoginPage loginPage = restorePasswordPage.signInLinkClick();
        loginPage.waitForPageLoaded();
    }
}
