import core.TestBase;
import helpers.DataProviders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.StaticRegistrationPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthenticationFormTest extends TestBase {

    @BeforeTest
    public void Setup() {
        open("/login");
    }

    @Test(dataProvider = "loginData", dataProviderClass = DataProviders.class)
    public void authenticationTest(String login, String pswd) {
        if (login.equals(" ")) {
            login = "";
        }
        if (pswd.equals(" ")) {
            pswd = "";
        }
        StaticRegistrationPage.login(login, pswd);

        if (login.equals("tomsmith")) {
            if (pswd.equals("SuperSecretPassword!")) {
                $(StaticRegistrationPage.FLASH).should(appear, cssClass("success"), text("You logged into a secure area! "));
                open("/login");
            }
            if (pswd.equals("wrongPass")) {
                $(StaticRegistrationPage.FLASH).should(appear, cssClass("error"), text("Your password is invalid!"));
            }
            if (pswd.equals("")) {
                $(StaticRegistrationPage.FLASH).should(appear, cssClass("error"), text("Your password is invalid!"));
            }
        }
        if (pswd.equals("SuperSecretPassword!")) {
            if (login.equals("wrongLogin")) {
                $(StaticRegistrationPage.FLASH).should(appear, cssClass("error"), text("Your username is invalid!"));
            }
            if (login.equals("")) {
                $(StaticRegistrationPage.FLASH).should(appear, cssClass("error"), text("Your username is invalid!"));
            }
        }
        if (login.equals("")) {
            if (pswd.equals("")) {
                $(StaticRegistrationPage.FLASH).should(appear, cssClass("error"), text("Your username is invalid!"));
                open("/login");
            }
        }
    }

}
