package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import io.appium.java_client.AppiumDriver;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text,'Search…')]",
            SEARCH_CLOSE_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULTS = "org.wikipedia:id/search_empty_text",
            SEARCH_ITEM_TITLE = "//*[@resource-id ='org.wikipedia:id/page_list_item_title']";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /*TEMPLATES METHODS */
    private static String getSearchResultElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search element after clicking search init element");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getSearchResultElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubString(String substring) {
        String search_result_xpath = getSearchResultElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 15);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CLOSE_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CLOSE_BUTTON), "Search cancel button is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CLOSE_BUTTON), "Cannot find and click search cancel button", 5);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by request, cant get amount of article",
                15
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public List<WebElement> titleOfFoundedArticles() {
        return this.waitForElementsPresents(By.xpath(SEARCH_ITEM_TITLE), "Cant get title of search results", 15);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(By.id(SEARCH_EMPTY_RESULTS), "Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not fond ant results");
    }

    public void elementHasText(String expected_value) {
        this.assertElementHasText(
                By.xpath(SEARCH_INIT_ELEMENT),
                expected_value,
                "Not expected text. Expected value is " + expected_value
        );
    }
}
