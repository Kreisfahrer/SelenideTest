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

    @Parameter("uploaded_file") private String UPLOAD_FILE_PATH = getClass().getResource("/uploadFileResources/smiley.jpg").getPath();

    @Test
    public void uploadFileTest() throws Exception {
        UploadFilePage.uploadFile(UPLOAD_FILE_PATH);
        $(UploadFilePage.FILE_UPLODED_MESSAGE).should(appear);
        $(UploadFilePage.FILE_UPLOADED_MESSAGE_HEADER).shouldHave(text("File Uploaded!"));
    }
}
