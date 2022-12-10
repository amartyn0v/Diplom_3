package pageModel;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BasePage {
    private final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    public static void configure(){
        //Uncomment following string to run tests on the Yandex browser
        //System.setProperty("webdriver.chrome.driver","resources/yandexdriver.exe");
        Configuration.browser = "Chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 5000;
        Configuration.headless = true;
    }

    private SelenideElement constructorTab = $(byXpath("//p[text()='Конструктор']"));
    private SelenideElement feedTab = $(byXpath("//p[text()='Лента Заказов']"));
    private SelenideElement burgerLogo = $(byXpath("//div[@class='AppHeader_header__logo__2D0X2']/a"));
    private SelenideElement profileTab = $(byXpath("//p[text()='Личный Кабинет']"));

    public ConstructorPage clickConstructorTab(){
        constructorTab.shouldBe(Condition.visible).click();
        ConstructorPage constructorPage = new ConstructorPage();
        constructorPage.waitForPageLoaded();
        return constructorPage;
    }

    public OrdersFeed clickFeedTab(){
        feedTab.shouldBe(Condition.visible).click();
        OrdersFeed feed = new OrdersFeed();
        feed.waitForPageLoaded();
        return feed;
    }

    public ConstructorPage clickBurgerLogo(){
        burgerLogo.shouldBe(Condition.interactable).hover().click();
        ConstructorPage constructorPage = new ConstructorPage();
        constructorPage.waitForPageLoaded();
        return constructorPage;
    }

    public LoginPage clickProfileTabWhenUnauthorized(){
        profileTab.shouldBe(Condition.interactable).hover().click();
        LoginPage loginPage = new LoginPage();
        loginPage.waitForPageLoaded();
        return loginPage;
    }

    public ProfilePage clickProfileTabWhenAuthorized(){
        profileTab.shouldBe(Condition.interactable).hover().click();
        ProfilePage profilePage = new ProfilePage();
        profilePage.waitForPageLoaded();
        return profilePage;
    }

    public void openBasePage(){
        open(BASE_URL);
    }

}
