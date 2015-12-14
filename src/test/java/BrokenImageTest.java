import core.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BrokenImageStaticPage;
import java.util.HashMap;
import java.util.Map;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class BrokenImageTest extends TestBase {

    @BeforeMethod
    public void setup() {
        open("broken_images");
    }

    @Test
    public void simpleLoginTest() {
        Map<String, String> brokenImage = new HashMap<>();
        int imageSize = ($$(BrokenImageStaticPage.IMAGE_LINK)).size();
        for (int i = 0; i < imageSize; i++) {
            if (!$(BrokenImageStaticPage.IMAGE_LINK, i).isImage()) {
                String url = $(BrokenImageStaticPage.IMAGE_LINK, i).attr(BrokenImageStaticPage.ATTRIBUTE);
                brokenImage.put("\n" + url + " ", " not loaded");
            }
        }
        Assert.assertEquals(brokenImage.size(), 0, brokenImage.toString());
    }
}
