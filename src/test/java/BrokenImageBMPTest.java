import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import core.BmpTestBase;
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
        open("broken_images");
    }

    @Test
    public void brokenImageTest() {
        List<String> links = getUrls($$(".example>img"), "src");
        Map<String, String> brokenImage = new HashMap<>();
        for(String link : links) {
            getServer().newHar("broken-image");
            open(link);
            Har har = getServer().getHar();
            for (HarEntry entry : har.getLog().getEntries()) {
                if (entry.getRequest().getUrl().equals(link)) {
                    if (entry.getResponse().getStatus() >= 400) {
                        brokenImage.put(link, String.valueOf(entry.getResponse().getStatus()));
                    }
                }
            }
        }
        Assert.assertEquals(brokenImage.size(), 0, mapToString(brokenImage));
    }
}
