import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pageModel.BasePage;
import pageModel.ConstructorPage;
import java.lang.reflect.Field;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class ConstructorTest {

    ConstructorPage constructorPage;

    @Before
    public void setUp(){
        constructorPage = new ConstructorPage();
        BasePage.configure();
    }

    @Test
    @Parameters({"fillingsTab", "saucesTab"})
    public void switchIngredientsTabs(String element) throws Exception{
        constructorPage.openPage();
        Field bunsTab = ConstructorPage.class.getField("bunsTab");
        assertTrue(constructorPage.isTheTabCurrent((SelenideElement) bunsTab.get(constructorPage)));
        Field tab = ConstructorPage.class.getField(element);
        constructorPage.tabClick((SelenideElement) tab.get(constructorPage));
        assertTrue(constructorPage.isTheTabCurrent((SelenideElement) tab.get(constructorPage)));
        Selenide.closeWebDriver();
    }
}
