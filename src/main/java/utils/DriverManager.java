package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverManager {
    private static final Logger log = LoggerFactory.getLogger(DriverManager.class);
    private static final DriverManager driverManager = new DriverManager();
    private static String browser = System.getProperties().getProperty("browser");

    private static WebDriver webDriver;

    private DriverManager() {
    }

    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            setWebDriver();
        }

        return webDriver;
    }

    public static void quit() {
        webDriver.quit();
        webDriver = null;
    }

    private static void setWebDriver() {
        if (browser == null) {
            browser = "Chrome";
        }

        switch (browser) {
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;
            case "IE":
                WebDriverManager.iedriver().setup();
                webDriver = new InternetExplorerDriver();
                break;
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver(setChromeOptions());
                break;
            default:
                throw new IllegalArgumentException("Unknown browser parameter provided (" + browser + ")! " +
                        "Allowed parameters: Firefox, IE, Chrome.");
        }

        log.info("Initialized driver for " + browser);
    }

    private static ChromeOptions setChromeOptions() {

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", createDownloadRepo().getAbsolutePath());

        ChromeOptions options = new ChromeOptions();
        return options.setExperimentalOption("prefs", chromePrefs);
    }

    private static File createDownloadRepo(){
        File downloadsDir = new File("src/main/resources/download");

        if (!downloadsDir.exists() && !downloadsDir.mkdir()) {
            log.error("Could not create downloads directory in resources!");
        }
        return downloadsDir;
    }
}
