package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.DriverManager;
import utils.PageObject;

import java.util.List;

import static utils.WaitingMethods.waitUntilListIsVisible;

public class ResultsPage extends PageObject {

    @FindBy(id = "LIST")
    private List<WebElement> results;

    public ResultsPage synchronize() {
        waitUntilListIsVisible(results);
        return this;
    }

    public void verifyResults(String destination) {
        List<WebElement> list = DriverManager.getWebDriver().findElements(By.className("location"));
        Assert.assertTrue(list.stream().anyMatch(obj ->
                obj.getText().contains(destination)
        ));
    }
}
