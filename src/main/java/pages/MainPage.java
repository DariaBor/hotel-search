package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.DriverManager;
import utils.PageObject;

import java.util.Calendar;

import static org.openqa.selenium.Keys.ENTER;
import static utils.WaitingMethods.*;

public class MainPage extends PageObject {

    @FindBy(name = "HOTELS")
    private WebElement hotelForm;

    @FindBy(id = "s2id_autogen1")
    private WebElement destinationField;

    @FindBy(name = "dest")
    private WebElement destinationInput;

    @FindBy(id = "checkin")
    private WebElement checkInInput;

    @FindBy(id = "checkout")
    private WebElement checkOutInput;

    @FindBy(xpath = "//div[@class = 'datepicker--days datepicker--body active']")
    private WebElement datePicker;

    @FindBy(xpath = "//*[@id='datepickers-container']/div[2]/*//div[@class = 'datepicker--days datepicker--body active']")
    private WebElement datePickerOut;

    @FindBy(name = "adults")
    private WebElement adultsAmount;

    @FindBy(name = "children")
    private WebElement kidsAmount;

    @FindBy(xpath = "//*[@class = 'btn btn-primary btn-block']")
    private WebElement submitButton;

    @FindBy(xpath = "//span[@class='select2-match']")
    private WebElement result;

    public MainPage synchronize() {
        waitUntilVisible(hotelForm);
        return this;
    }

    public void chooseDestination(String destination) {
        destinationField.click();
        destinationInput.sendKeys(destination);
        waitUntilVisible(result);
        destinationInput.sendKeys(ENTER);
        waitUntilTextIsVisible(By.xpath("//span[@class='select2-chosen']"), destination);
    }

    public void selectCheckInDate() {
        checkInInput.click();
        waitUntilVisible(datePicker);
        int currentDay = Calendar.getInstance().get(Calendar.DATE);
        int currentmonth = Calendar.getInstance().get(Calendar.MONTH);
        By dateLocator = By.xpath(String.format("//*[@id='datepickers-container']/div[1]/*//div[@data-date='%s' and @data-month='%s']", String.valueOf(currentDay), String.valueOf(currentmonth)));
        DriverManager.getWebDriver().findElement(dateLocator).click();
        waitUntilNotVisible(datePicker);
    }

    public void selectCheckOutDate(int checkOut) {
        checkOutInput.click();
        waitUntilVisible(datePickerOut);
        int futureDate = Calendar.getInstance().get(Calendar.DATE) + checkOut;
        int currentmonth = Calendar.getInstance().get(Calendar.MONTH);
        By dateLocator = By.xpath(String.format("//*[@id='datepickers-container']/div[2]/*//div[@data-date='%s' and @data-month='%s']", String.valueOf(futureDate), String.valueOf(currentmonth)));
        DriverManager.getWebDriver().findElement(dateLocator).click();
        waitUntilNotVisible(datePickerOut);
    }

    public void selectAdultAmout(String amount){
        adultsAmount.click();
        removeAtt(adultsAmount);
        adultsAmount.clear();
        adultsAmount.sendKeys(amount);
    }

    public void selectKidAmount(String amount) {
        kidsAmount.click();
        removeAtt(kidsAmount);
        kidsAmount.clear();
        kidsAmount.sendKeys(amount);
    }

    public void sendRequest() {
        submitButton.click();
    }

    private void removeAtt(WebElement input){
            ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript(
                    "arguments[0].removeAttribute('readonly','readonly')",input);

    }
}
