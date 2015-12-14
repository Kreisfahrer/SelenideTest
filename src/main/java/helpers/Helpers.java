package helpers;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Helpers {
    public static List<String> readAllLines(Path resourcePath) throws IOException {
        return Files.readAllLines(resourcePath, Charset.defaultCharset());
    }

    @Attachment
    public static byte[] makeScreenshot() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
