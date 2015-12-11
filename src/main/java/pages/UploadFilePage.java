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
    public final static By FILE_UPLODED_MESSAGE = get("fileUploadedMessage");
    public final static By UPLOADED_FILE_NAME = get("uploadedFileName");
    private static final String UPLOAD_FILE_PATH = "./src/main/resources/uploadFileResources/smiley.jpg";
    public static String fileName = new File(UPLOAD_FILE_PATH).getName();

    @Step("User successfully uploaded the file")
    public static void uploadFile() {
        String absolutePath = new File(UPLOAD_FILE_PATH).getAbsolutePath();
        $(BROWSE_BUTTON).uploadFile(new File(absolutePath));
        $(UPLOAD_BUTTON).click();
    }
}
