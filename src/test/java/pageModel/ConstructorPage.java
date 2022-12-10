package pageModel;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ConstructorPage extends BasePage{
    private String url = "https://stellarburgers.nomoreparties.site/";
    private SelenideElement headerLabel = $(byXpath("//h1[text() = 'Соберите бургер']"));
    private SelenideElement loginButton = $(byXpath("//button[text() = 'Войти в аккаунт']"));
    public SelenideElement bunsTab = $(byXpath("//div[contains(span/text(), 'Булки')]"));
    public SelenideElement saucesTab = $(byXpath("//div[contains(span/text(), 'Соусы')]"));
    public SelenideElement fillingsTab = $(byXpath("//div[contains(span/text(), 'Начинки')]"));

    public void openPage(){
        open(url);
        waitForPageLoaded();
    }

    public void waitForPageLoaded(){
        headerLabel.shouldBe(Condition.visible);
    }

    public boolean isPageLoaded(){
        return headerLabel.is(Condition.visible);
    }

    public void loginButtonClick(){
        loginButton.shouldBe(Condition.interactable).hover().click();
    }

    public void bunsTabClick(){
        bunsTab.shouldBe(Condition.interactable).hover().click();
    }

    public void fillingsTabClick(){
        fillingsTab.shouldBe(Condition.interactable).hover().click();
    }

    public void saucesTabClick(){
        saucesTab.shouldBe(Condition.interactable).hover().click();
    }

    public boolean isBunsTabCurrent(){
       return bunsTab.getAttribute("class").contains("current");
    }

    public boolean isSaucesTabCurrent(){
        return saucesTab.getAttribute("class").contains("current");
    }

    public boolean isFillingsTabCurrent(){
        return fillingsTab.getAttribute("class").contains("current");
    }

    public boolean isTheTabCurrent(SelenideElement tab){
        return tab.getAttribute("class").contains("current");
    }

    public void tabClick(SelenideElement tab){
        tab.shouldBe(Condition.visible).click();
    }

}
