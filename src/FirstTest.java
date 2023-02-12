
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import java.util.List;

import lib.CoreTestCase;
import ui.ArticlePageObject;
import ui.MainPageObject;
import ui.MyListsPageObject;
import ui.NavigationUi;
import ui.SearchPageObject;

public class FirstTest extends CoreTestCase {
    //DELETE THIS
    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }
    //DELETE THIS

    @Test
    public void testElementHasText() {
        MainPageObject.assertElementHasText(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia",
                "Not expected text"
        );
    }

    @Test
    public void testSearchSomeText() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "vagina",
                "Cannot find search input",
                5
        );

        List<WebElement> my_elements = MainPageObject.waitForElementsPresents(
                By.id("org.wikipedia:id/search_results_list"),
                "Search results not found",
                30
        );

        assertTrue("Size of elements is 0", my_elements.size() > 1);


        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cansel search",
                5
        );

        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "List of search results is found",
                5
        );
    }

    @Test
    public void testAllOfSearchResultContainsText() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "java",
                "Cannot find search input",
                5
        );

        List<WebElement> myElements = MainPageObject.waitForElementsPresents(
                By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_title']"),
                "Cant find page list item title",
                5
        );

        for (WebElement myElement : myElements) {
            String desired_value = "java";
            assertTrue("Some elements do not contains the " + "'" + desired_value + "'",
                    myElement.getText().toLowerCase().contains(desired_value));
        }
    }

    @Test
    public void testTwoArticleToMyListThenDeleteOneOfThem() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_value = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_value,
                "Cannot find search input '" + search_value + "'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' search result",
                30
        );

        String article_page_title = "org.wikipedia:id/view_page_title_text";
        MainPageObject.waitForElementPresent(
                By.id(article_page_title),
                "Cant find title 'Java (programming language)'",
                15
        );

        String xpath_button_more_options = "//android.widget.ImageView[@content-desc='More options']";
        MainPageObject.waitForElementAndClick(
                By.xpath(xpath_button_more_options),
                "Cannot find button to open 'More options'",
                5
        );

        String xpath_add_to_reading_list = "//android.widget.TextView[@text='Add to reading list']";
        MainPageObject.waitForElementAndClick(
                By.xpath(xpath_add_to_reading_list),
                "Cannot find button 'Add to reading list' in to 'More options'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find button 'onboarding'",
                5
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find 'Name of list' input",
                5
        );

        String name_of_folder = "Learning programming";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text in to 'Name of list' input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press 'OK' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot press 'CLOSE' button, cannot find X link",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_value,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Wikimedia list article']"),
                "Cannot find 'Object-oriented programming language' search result",
                30
        );

        MainPageObject.waitForElementPresent(
                By.id(article_page_title),
                "Cant find title 'Java version history'",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath(xpath_button_more_options),
                "Cannot find button to open 'More options'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath(xpath_add_to_reading_list),
                "Cannot find button 'Add to reading list' in to 'More options'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Learning programming']"),
                "Cannot find folder '" + name_of_folder + "' for save to reading list",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot press 'CLOSE' button, cannot find X link",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to 'My list'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find '" + name_of_folder + "' in to 'My list'",
                5
        );

        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );

        String title_text_in_folder = MainPageObject.waitForElementAndGetText(
                By.xpath("//*[@text='Java version history']"),
                "Cannot get text in article 'Java version history' in folder",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Java version history']"),
                "Cannot find article 'Java version history' in folder",
                5
        );

        String title_text_inside_article = MainPageObject.waitForElementAndGetText(
                By.id(article_page_title),
                "Cannot get text in article 'Java version history' inside article",
                5
        );

        assertEquals(
                "Title text in folder and inside article doesn't match",
                title_text_in_folder,
                title_text_inside_article
        );
    }

    @Test
    public void testEx6assertElementPresent() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_value = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_value,
                "Cannot find search input '" + search_value + "'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' search result",
                30
        );

        MainPageObject.notWaitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Element 'page_title' not enabled"
        );
    }
}