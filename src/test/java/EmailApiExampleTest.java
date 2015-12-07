import core.TestBase;
import helpers.mail.Email;
import helpers.mail.GuerillaMail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.codeborne.selenide.Configuration;


import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.Wait;
import static com.codeborne.selenide.Selenide.open;

public class EmailApiExampleTest extends TestBase {
    GuerillaMail mailer;
    int mailId;

    @BeforeMethod
    public void setup() throws Exception {
        Configuration.timeout = 60000;
        Configuration.pollingInterval = 5000;
        mailer = new GuerillaMail();
        String mail = mailer.getEmailAddress();
        open("forgot_password");
        $("#email").val(mail);
        $("#form_submit").click();
    }

    // Added some comments
    @Test
    public void forgetPasswordTest() throws Exception {
        Wait().until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                boolean result = false;
                try {
                    for (Email mail : mailer.checkEmail()) {
                        if (mail.getSubject().equals("Forgot Password from the-internet")) {
                            result = true;
                            mailId = mail.getId();
                        }
                    }
                } catch (Exception e) {

                }
                return result;
            }
        });
        Email fetched = mailer.fetchEmail(mailId);
        Assert.assertTrue(fetched.getSubject().contains("Forgot Password from the-internet"));
        String body = fetched.getBody();
    }
}
