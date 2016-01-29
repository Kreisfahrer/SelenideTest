package helpers.listeners;

import org.testng.ITestResult;
import org.testng.reporters.ExitCodeListener;

public class CustomTextReport extends ExitCodeListener {
    protected CustomReport report = new CustomReport();

    public CustomTextReport() {
    }

    public void onTestStart(ITestResult result) {
        this.report.start();
    }

    public void onTestFailure(ITestResult result) {
        this.report.finish(result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        this.report.finish(result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        this.report.finish(result.getName());
    }
}
