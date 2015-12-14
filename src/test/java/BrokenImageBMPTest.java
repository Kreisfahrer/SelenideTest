import core.BmpTestBase;
import net.lightbody.bmp.core.har.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BrokenImageStaticPage;
import java.util.HashMap;
import java.util.Map;
import static com.codeborne.selenide.Selenide.open;
import static helpers.Helpers.mapToString;
import static pages.BrokenImageStaticPage.loadAllArfuments;

public class BrokenImageBMPTest extends BmpTestBase {

    @BeforeMethod
    public void setup() {
        server.newHar("broken-image");
        open("broken_images");
    }

    @Test
    public void brokenImageTest() {
        Har har = server.getHar();
        Map<String, String> brokenImage = new HashMap<>();
        for (HarEntry entry : har.getLog().getEntries()) {
            for(String link : BrokenImageStaticPage.loadImageUrls()) {
                if (entry.getRequest().getUrl().equals(link)) {
                    if (entry.getResponse().getStatus() >= 400) {
                        brokenImage.put(entry.getRequest().getUrl(), String.valueOf(entry.getResponse().getStatus()));
                    }
                }
            }
        }
        Assert.assertEquals(brokenImage.size(), 0, mapToString(brokenImage));
    }
}