import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.SoftAsserts;
import core.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Issue;
import ru.yandex.qatools.allure.annotations.TestCaseId;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Admin on 09.12.2015.
 */
@Listeners(SoftAsserts.class)
public class SoftAssertExample extends TestBase {

    @BeforeMethod
    public void setup() {
        open("login");
        Configuration.assertionMode = Configuration.AssertionMode.SOFT;
        Configuration.timeout = 0;
    }

    @AfterMethod
    public void teardown() {
        open("login");
        Configuration.assertionMode = Configuration.AssertionMode.STRICT;
        Configuration.timeout = 10;
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
}
