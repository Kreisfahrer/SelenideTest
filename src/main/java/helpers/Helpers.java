package helpers;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Helpers {
    public static List<String> readAllLines(Path resourcePath) throws IOException {
        return Files.readAllLines(resourcePath, Charset.defaultCharset());
    }

    @Attachment
    public static byte[] makeScreenshot() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static String mapToString(Map<String, String> map) {
        StringBuilder message = new StringBuilder();
        for(String key : map.keySet()) {
            message.append(String.format("url: %s, response code: %s\n", key, map.get(key)));
        }
        return message.toString();
    }

    public static List<String> getUrls(ElementsCollection elements, String attribute) {
        List<String> urls = new ArrayList<>();
        for (SelenideElement image : elements) {
            urls.add(image.attr(attribute));
        }
        return urls;
    }
}
