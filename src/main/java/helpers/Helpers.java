package helpers;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Helpers {
    public static List<String> readAllLines(Path resourcePath) throws IOException {
        return Files.readAllLines(resourcePath, Charset.defaultCharset());
    }

    public static List<String> getUrls(ElementsCollection elements, String attribute) {
        List<String> urls = new ArrayList<>();
        for (SelenideElement url : elements) {
            urls.add(url.attr(attribute));
        }
        return urls;
    }

    public static String mapToString(Map<String, String> map) {
        StringBuilder message = new StringBuilder();
        for(String key : map.keySet()) {
            message.append(String.format("\nurl: %s, response code: %s", key, map.get(key)));
        }
        return message.toString();
    }
}
