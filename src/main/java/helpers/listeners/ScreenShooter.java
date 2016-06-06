package helpers.listeners;

import helpers.Helpers;
import org.testng.ITestResult;
import org.testng.reporters.ExitCodeListener;

public class ScreenShooter extends ExitCodeListener {

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        Helpers.takeScreenshot();
    }
}
