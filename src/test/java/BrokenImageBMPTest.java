import core.BmpTestBase;
import net.lightbody.bmp.core.har.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BrokenImageStaticPage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.Helpers.getUrls;
import static helpers.Helpers.mapToString;

public class BrokenImageBMPTest extends BmpTestBase {

    @BeforeMethod
    public void setup() {
        getServer().newHar("broken-image");
        open("broken_images");
    }

    @Test
    public void brokenImageBMPTest() {
        List<String> links = getUrls($$(BrokenImageStaticPage.IMAGE_LINK), "src");
        Har har = getServer().getHar();
        Map<String, String> brokenImage = new HashMap<>();
        for (HarEntry entry : har.getLog().getEntries()) {
            String url = entry.getRequest().getUrl();
            for(String link : links) {
                if (url.equals(link)) {
                    int responseCode = entry.getResponse().getStatus();
                    if (responseCode >= 400) {
                        brokenImage.put(url, String.valueOf(responseCode));
                    }
                }
            }
        }
        Assert.assertEquals(brokenImage.size(), 0, mapToString(brokenImage));
    }
}