import core.TestBase;
import helpers.DataProviders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AuthenticatedPage;
import pages.StaticRegistrationPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthenticationFormTest extends TestBase {

    @BeforeMethod
    public void setup() {
        open("login");
    }

    @Test(dataProvider = "loginData", dataProviderClass = DataProviders.class)
    public void authenticationTest(String login, String pswd, String errorMessage) {
        StaticRegistrationPage.login(login, pswd);
               if (!errorMessage.isEmpty()) {
                                $(StaticRegistrationPage.FLASH_ERROR).should(appear, text(errorMessage));
                    } else {
                   $(AuthenticatedPage.FLASH_SUCCESS).should(appear,text("You logged into a secure area!"));
                   AuthenticatedPage.logOut();
        }

    }

}