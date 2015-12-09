import com.codeborne.selenide.testng.BrowserPerClass;
import core.TestBase;
import helpers.ScreenShooter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.FactoryHomePage;
import pages.FactoryRegistrationPage;
import pages.StaticHomePage;
import pages.StaticRegistrationPage;
import ru.yandex.qatools.allure.annotations.Issue;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import com.codeborne.selenide.testng.TextReport;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.*;

@Listeners(TextReport.class)
public class SelenideExamplesTest extends TestBase {
    private FactoryHomePage homePage;
    private FactoryRegistrationPage registrationPage;

    @BeforeMethod
    public void setup() {
        registrationPage = open("login", FactoryRegistrationPage.class);
    }

    @Issue("Some-issue-id-2435")
    @TestCaseId("test-1")
    @Test
    public void simpleLoginTest() {
        $("#username").val("tomsmith");
        $("#password").val("SuperSecretPassword!");
        $("#password").shouldHave(exactValue("SuperSecret"));
        $("button[type='submit']").click();
        $("#flash").should(appear, cssClass("fail"), text("You logged into a secure area!"));
    }

    @TestCaseId("test-2")
    @Test
    public void staticPageLoginTest() {
        StaticRegistrationPage.login("tomsmith", "SuperSecretPassword!");
        $(StaticHomePage.FLASH).should(appear, cssClass("success"), text("You logged into a secure area!"));
    }

    @TestCaseId("test-3")
    @Test
    public void staticPageLogoutTest() {
        StaticRegistrationPage.login("tomsmith", "SuperSecretPassword!");
        StaticHomePage.logout();
        $(StaticRegistrationPage.FLASH).should(appear, cssClass("success"), text("You logged out of the secure area!"));
    }

    @TestCaseId("test-4")
    @Test
    public void factoryPageLoginTest() {
        homePage = registrationPage.login("tomsmith", "SuperSecretPassword!");
        homePage.flash.should(appear, cssClass("success"), text("You logged into a secure area!"));
    }

    @TestCaseId("test-5")
    @Test
    public void factoryPageLogoutTest() {
        homePage = registrationPage.login("tomsmith", "SuperSecretPassword!");
        registrationPage = homePage.logout();
        registrationPage.flash.should(appear, cssClass("success"), text("You logged out of the secure area!"));
    }
}
