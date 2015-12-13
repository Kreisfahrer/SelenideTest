package helpers;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DataProviders {

    private static final String REGISTRATION_DATA_FILE = "/RegistrationData.csv";
    private static final Path AUTHENTICATION_DATA_FILE = Paths.get("./src/main/resources/LoginExamples.csv");

    @DataProvider(name = "loginData")
    public static Object[][] getAuthenticationData() throws IOException {
        return getData(AUTHENTICATION_DATA_FILE, ",");
    }

    public static Object[][] getData(Path path, String delimetr) throws IOException {
        List<String> data = Helpers.readAllLines(path);
        Object[][] dataRows = new Object[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            dataRows[i] = convertSpaceIntoEmptyString(data.get(i).split(delimetr));
        }
        return dataRows;
    }

    private static String[] convertSpaceIntoEmptyString(String[] values) {
        String[] spaceToEmpty = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            String element = values[i];
            if (element.equals("NILL")) {
                element = "";
            }
            spaceToEmpty[i] = element;
        }
        return spaceToEmpty;
    }

    @DataProvider(name = "registrationData")
    public Object[][] getRegistrationData() {
        return new Object[][]{
                {},
                {}
        };
    }
}

