import com.codeborne.selenide.Selenide;
import io.qameta.allure.internal.shadowed.jackson.databind.ser.Serializers;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pageModel.BasePage;
import pageModel.ConstructorPage;
import pageModel.LoginPage;
import pageModel.ProfilePage;

import java.lang.reflect.Method;

import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class ProfileTest {

    BasePage basePage;
    LoginPage loginPage;
    private final String email = "amartymail@gmail.com";
    private final String password = "123123123";
    @Before
    public void setUp(){
        basePage = new BasePage();
        loginPage = new LoginPage();
        BasePage.configure();
    }

    @Test
    @Parameters({"clickConstructorTab","clickFeedTab"})
    public void openProfileUnauthorizedFromDifferentPages(String method) throws Exception{
        basePage.openBasePage();
        Method entryPoint = BasePage.class.getMethod(method);
        entryPoint.invoke(basePage);
        LoginPage loginPage = basePage.clickProfileTabWhenUnauthorized();
        assertTrue(loginPage.isPageLoaded());
        Selenide.closeWebDriver();
    }

    @Test
    @Parameters({"clickConstructorTab","clickFeedTab"})
    public void openProfileAuthorizedFromDifferentPages(String method) throws Exception{
        loginPage.openPage();
        loginPage.signIn(email, password);
        basePage.openBasePage();
        Method entryPoint = BasePage.class.getMethod(method);
        entryPoint.invoke(basePage);
        ProfilePage profilePage = basePage.clickProfileTabWhenAuthorized();
        assertTrue(profilePage.isPageLoaded());
        Selenide.closeWebDriver();
    }

    @Test
    @Parameters({"clickConstructorTab","clickBurgerLogo"})
    public void openConstructorPageFromProfile(String method) throws Exception{
        loginPage.openPage();
        ConstructorPage constructorPage = loginPage.signIn(email, password);
        ProfilePage profilePage = constructorPage.clickProfileTabWhenAuthorized();
        Method entryPoint = ProfilePage.class.getMethod(method);
        entryPoint.invoke(profilePage);
        assertTrue(constructorPage.isPageLoaded());
        Selenide.closeWebDriver();
    }

    @Test
    public void logOutFromProfilePage(){
        loginPage.openPage();
        ConstructorPage constructorPage = loginPage.signIn(email, password);
        ProfilePage profilePage = constructorPage.clickProfileTabWhenAuthorized();
        profilePage.logoutButtonCLick();
        assertTrue(loginPage.isPageLoaded());
    }
}
