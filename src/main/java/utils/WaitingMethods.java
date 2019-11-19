package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WaitingMethods {

    private static final int COMPONENT_TIMEOUT = 10;

    public static void waitUntilVisible(WebElement element){
        Wait<WebDriver> wait = new WebDriverWait(DriverManager.getWebDriver(), COMPONENT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilNotVisible(WebElement element){
        Wait<WebDriver> wait = new WebDriverWait(DriverManager.getWebDriver(), COMPONENT_TIMEOUT);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitUntilListIsVisible(List<WebElement> list){
        Wait<WebDriver> wait = new WebDriverWait(DriverManager.getWebDriver(), COMPONENT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfAllElements(list));
    }

    public static void waitUntilTextIsVisible(By by, String text){
        Wait<WebDriver> wait = new WebDriverWait(DriverManager.getWebDriver(), COMPONENT_TIMEOUT);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }
}
