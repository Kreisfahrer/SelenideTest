import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import core.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FactoryRegistrationPage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class HttpExamplesTest extends TestBase {
    @BeforeMethod
    public void setup() {
        open("broken_images", FactoryRegistrationPage.class);
    }

    @Test
    public void imagesLoadedTest() throws IOException {
        List<String> urls = getUrls($$(".example img"));
        Map<String, String> brokenImages = new HashMap<>();
        for (String link : urls) {
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            if (responseCode >= 400) {
                brokenImages.put(link, String.valueOf(responseCode));
            }
            con.disconnect();
        }
        Assert.assertEquals(brokenImages.size(), 0, mapToString(brokenImages));
    }

    private String mapToString(Map<String, String> map) {
        StringBuilder message = new StringBuilder();
        for (String key : map.keySet()) {
            message.append(String.format("url: %s, response code: %s\n", key, map.get(key)));
        }
        return message.toString();
    }

    private List<String> getUrls(ElementsCollection elements) {
        List<String> urls = new ArrayList<>();
        for (SelenideElement image : elements) {
            urls.add(image.attr("src"));
        }
        return urls;
    }
}
