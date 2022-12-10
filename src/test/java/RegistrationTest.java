import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageModel.*;
import utils.UserActions;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegistrationTest {

    RegisterPage registerPage;
    String name = "MartyName";
    String email = "MartyEmail@yandex.ru";
    String password = "MartyPassword";

    @Before
    public void setUp(){
        registerPage = new RegisterPage();
        BasePage.configure();
    }

    @After
    public void tearDown(){
        UserActions.deleteUser(email,password);
    }

    @Test
    public void canRegisterWithValidUniqueCreds(){
        registerPage.openPage();
        LoginPage loginPage = registerPage.register(name,email,password);
        ConstructorPage constructorPage = loginPage.signIn(email,password);
        ProfilePage profile = constructorPage.clickProfileTabWhenAuthorized();
        assertTrue(profile.isPageLoaded());
    }

    @Test
    public void errorWhenPasswordLengthLessThan6(){
        registerPage.openPage();
        registerPage.setName(name);
        registerPage.setEmail(email);
        registerPage.setPassword("1234");
        assertFalse(registerPage.isWrongPasswordErrorVisible());
    }
}
