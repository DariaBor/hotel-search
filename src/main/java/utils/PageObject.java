package utils;

import org.openqa.selenium.support.PageFactory;

public abstract class PageObject {
    public PageObject(){
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }
}
