import com.codeborne.selenide.WebDriverRunner;
import core.TestBase;
import helpers.DataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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
            // verification for invalid creds and validation appearance
            $(StaticRegistrationPage.FLASH_ERROR).should(appear, text(errorMessage));
        } else {
            // validation for successful login goes here
        }
    }

    @AfterMethod
    public void quite() {
        WebDriverRunner.getWebDriver().quit();
    }
}