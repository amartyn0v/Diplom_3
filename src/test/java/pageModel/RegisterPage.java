package pageModel;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegisterPage extends BasePage{

    private final String URL = "https://stellarburgers.nomoreparties.site/register";

    private SelenideElement nameInput = $(byXpath("//label[text()='Имя']/following::input"));
    private SelenideElement emailInput = $(byXpath("//label[text()='Email']/following::input"));
    private SelenideElement passwordInput = $(byXpath("//label[text()='Пароль']/following::input"));
    private SelenideElement submitBtn = $(byXpath("//button[text()='Зарегистрироваться']"));
    private SelenideElement headerLabel = $(byXpath("//div/h2[text()='Регистрация']"));
    private SelenideElement loginLink = $(byXpath("//a[text()='Войти']"));
    private SelenideElement wrongPasswordErrorLabel = $(byXpath("//p[text()='Некорректный пароль']"));

    public void openPage(){
        open(URL);
        waitForPageLoaded();
    }

    public void setName(String text){
        nameInput.shouldBe(Condition.editable).sendKeys(text);
    }

    public void setEmail(String text){
        emailInput.shouldBe(Condition.editable).sendKeys(text);
    }

    public void setPassword(String text){
        passwordInput.shouldBe(Condition.editable).sendKeys(text);
    }

    public void registerButtonClick(){
        submitBtn.shouldBe(Condition.visible).click();
    }

    public LoginPage openLoginPage(){
        loginLink.shouldBe(Condition.visible).click();
        LoginPage loginPage = new LoginPage();
        loginPage.waitForPageLoaded();
        return loginPage;
    }

    public LoginPage register(String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(password);
        registerButtonClick();
        LoginPage loginPage = new LoginPage();
        loginPage.waitForPageLoaded();
        return loginPage;
    }

    public void waitForPageLoaded(){
        headerLabel.shouldBe(Condition.visible);
    }

    public boolean isPageLoaded(){
        return headerLabel.is(Condition.visible);
    }

    public boolean isWrongPasswordErrorVisible(){
        return wrongPasswordErrorLabel.is(Condition.visible);
    }
}
