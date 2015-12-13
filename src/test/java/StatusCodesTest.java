import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.testng.SoftAsserts;
import core.BmpTestBase;
import net.lightbody.bmp.core.har.*;

import org.testng.annotations.*;
import pages.StatusCodesPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Configuration.AssertionMode.SOFT;
import static com.codeborne.selenide.Configuration.AssertionMode.STRICT;
import static org.testng.Assert.assertTrue;

@Listeners(SoftAsserts.class)
public class StatusCodesTest extends BmpTestBase{

    @BeforeMethod
    public void setup(){
        open("status_codes");
        Configuration.assertionMode = SOFT;
        Configuration.timeout = 0;
    }

    @Test
    public void responseCodePageTest() {
        int countLinks = $$(StatusCodesPage.CODE_PAGE_LINKS).size();
        for (int i = 0; i < countLinks; i++) {
            server.newHar("the-internet");
            $(StatusCodesPage.CODE_PAGE_LINKS, i).click();
            Har har = server.getHar();
            for (HarEntry entry : har.getLog().getEntries()) {
                assertTrue(entry.getResponse().getStatus() < 400);
            }
            back();
        }
    }

    @AfterMethod
    public void teardown() {
        Configuration.assertionMode = STRICT;
    }
}
