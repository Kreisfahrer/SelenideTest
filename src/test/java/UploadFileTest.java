import core.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.UploadFilePage;
import ru.yandex.qatools.allure.annotations.Parameter;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by tatsianabeliai on 12/9/15.
 */
public class UploadFileTest extends TestBase {

    @BeforeMethod
    public void setup() {
        open("upload");
    }

    @Parameter("Uploaded File name")
    private String uploadedFileName;

    @Test
    public void uploadFileTest() {
        UploadFilePage.uploadFile();
        $(UploadFilePage.FILE_UPLODED_MESSAGE).should(appear);
        $("h3").shouldHave(text("File Uploaded!"));
        uploadedFileName = UploadFilePage.fileName;
        $(UploadFilePage.UPLOADED_FILE_NAME).shouldHave(uploadedFileName);
    }
}
