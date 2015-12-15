package pages;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static helpers.Locators.get;

/**
 * Created by tatsianabeliai on 12/9/15.
 */
public class UploadFilePage {
    public final static By BROWSE_BUTTON = get("browseButton");
    public final static By UPLOAD_BUTTON = get("uploadButton");
    public final static By FILE_UPLOADED_MESSAGE_HEADER = get("fileUploadedMessageHeader");
    public final static By FILE_UPLODED_MESSAGE = get("fileUploadedMessageWithFileName");

    @Step("User successfully uploaded the file {0}")
    public static void uploadFile(String path) {
        $(BROWSE_BUTTON).uploadFile(new File(path));
        $(UPLOAD_BUTTON).click();
    }
}
