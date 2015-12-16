import com.codeborne.selenide.SelenideElement;
import core.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BrokenImageStaticPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class BrokenImageSelenideTest extends TestBase {

    @BeforeMethod
    public void setup() {
        open("broken_images");
    }

    @Test
    public void brokenImageSelenideTest() {
        List<String> brokenImageUrls = new ArrayList<>();
        for (SelenideElement aBrokenImage : $$(BrokenImageStaticPage.IMAGE_LINK)) {
            if (!aBrokenImage.isImage()) {
                brokenImageUrls.add("\n" + aBrokenImage.toString() + " not loaded");
            }
        }
        Assert.assertEquals(brokenImageUrls.size(), 0, brokenImageUrls.toString());
    }
}
