package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.MainPage;
import pages.ResultsPage;
import utils.DriverManager;

public class HotelSearchSteps {

    private static final String url = "https://www.phptravels.net/";
    private MainPage mainPage = new MainPage();
    private ResultsPage resultsPage = new ResultsPage();

    @Before
    public void testSetup() {
        DriverManager.getWebDriver().manage().deleteAllCookies();
        DriverManager.getWebDriver().manage().window().maximize();
    }

    @After
    public void tearDown() {
        DriverManager.quit();
    }

        @Given("^user opens main page$")
        public void iOpenGoogle() {
            DriverManager.getWebDriver().get(url);
            mainPage.synchronize();
        }

        @Then("^user selects destination to (.*)$")
        public void chooseDestionation(String destination){
            mainPage.chooseDestination(destination);
        }

        @And("^user selects the (-?\\d+) dates$")
        public void selectDates(int date) {
            mainPage.selectCheckInDate();
            mainPage.selectCheckOutDate(date);
        }

        @And("^user enters (.*) adults and (.*) kids$")
        public void selectAmountOfGuests(String adults, String kids) {
            mainPage.selectAdultAmout(adults);
            mainPage.selectKidAmount(kids);
        }

        @When("^user sends the search request$")
        public void searchForResult(){
        mainPage.sendRequest();
        }

        @Then("^there should be at least on item in (.*)$")
        public void checkResult(String destination){
        resultsPage.synchronize();
        resultsPage.verifyResults(destination);
        }
}
