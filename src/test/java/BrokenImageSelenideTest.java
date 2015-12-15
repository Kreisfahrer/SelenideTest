import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import core.TestBase;
import org.openqa.selenium.WebElement;
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
    public void BrokenImageSelenideTest() {
        ElementsCollection brokenImage = $$(BrokenImageStaticPage.IMAGE_LINK);
        List<String> brokenImageUrls = new ArrayList<>();
        for (SelenideElement aBrokenImage : brokenImage) {
            if (!aBrokenImage.isImage()) {
                String url = aBrokenImage.getAttribute("src");
                brokenImageUrls.add("\n" + url + " not loaded");
            }
        }
        Assert.assertEquals(brokenImageUrls.size(), 0, brokenImageUrls.toString());
    }
}
