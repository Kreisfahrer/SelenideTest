package helpers.listeners;

import com.codeborne.selenide.impl.SelenideLogger;
import org.testng.ITestResult;
import org.testng.reporters.ExitCodeListener;

/**
 * Created by Admin on 11.12.2015.
 */
public class CustomSoftAsserts extends ExitCodeListener {
    private final CustomErrorsCollector errorsCollector = new CustomErrorsCollector();

    public CustomSoftAsserts() {
        SelenideLogger.addListener(this.errorsCollector);
    }

    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        this.failIfErrors(result);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        super.onTestFailedButWithinSuccessPercentage(result);
        this.failIfErrors(result);
    }

    public void onTestSuccess(ITestResult result) {
        super.onTestSuccess(result);
        this.failIfErrors(result);
    }

    public void onConfigurationFailure(ITestResult result) {
        super.onConfigurationFailure(result);
        this.failIfErrors(result);
    }

    private void failIfErrors(ITestResult result) {
        this.errorsCollector.failIfErrors(result.getTestClass().getName() + '.' + result.getName());
    }
}
