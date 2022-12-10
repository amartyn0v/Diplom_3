import com.codeborne.selenide.Selenide;
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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class LoginTest {

    LoginPage loginPage;
    private final String email = "amartymail@gmail.com";
    private final String password = "123123123";

    @Before
    public void setUp(){
        loginPage = new LoginPage();
        BasePage.configure();
    }

    @Test
    @Parameters({"openLoginPageFromConstructorPage", "openLoginPageFromProfileTab","openLoginPageFromRegisterPage","openLoginPageFromRestorePassPage"})
    public void loginFromDifferentPages(String method) throws Exception{
        Method entryPoint = LoginPage.class.getMethod(method);
        entryPoint.invoke(loginPage);
        ConstructorPage constructorPage = loginPage.signIn(email, password);
        assertTrue(constructorPage.isPageLoaded());
        ProfilePage profile = constructorPage.clickProfileTabWhenAuthorized();
        assertEquals(email, profile.getEmailFromInput());
        Selenide.closeWebDriver();
    }
}
