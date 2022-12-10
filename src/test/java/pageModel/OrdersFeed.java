package pageModel;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class OrdersFeed extends BasePage{

    private SelenideElement headerLabel = $(byXpath("//h1[text()='Лента заказов']"));

    public void waitForPageLoaded(){
        headerLabel.shouldBe(Condition.visible);
    }

    public boolean isPageLoaded(){
        return headerLabel.is(Condition.visible);
    }
}
