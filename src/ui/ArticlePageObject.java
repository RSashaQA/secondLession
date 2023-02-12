package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "//*[@text='View page in browser']",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "//android.widget.TextView[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            MY_LAST_ARTICLE_IN_MY_LIST = "org.wikipedia:id/page_list_item_title",
            FOLDER_IN_MY_LIST_XPATH_TPL = "//*[@text='{FOLDER_NAME}']";

    private static String getFolderName(String name_of_folder) {
        return FOLDER_IN_MY_LIST_XPATH_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElementPresent() {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page!", 15);
    }

    public WebElement waitForArticleElementPresent() {
        return this.waitForElementPresent(By.id(MY_LAST_ARTICLE_IN_MY_LIST), "Cannot find article title on page!", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElementPresent();
        return title_element.getText();
    }

    public String getArticleTitleInMyList() {
        WebElement title_element = waitForArticleElementPresent();
        return title_element.getText();
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "Cannot find the end of article", 20);
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open 'More options'",
                5
        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find button 'Add to reading list' in to 'More options'",
                5
        );

        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find button 'onboarding'",
                5
        );

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find 'Name of list' input",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text in to 'Name of list' input",
                5
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press 'OK' button",
                5
        );
    }

    public void addSecondArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open 'More options'",
                5
        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find button 'Add to reading list' in to 'More options'",
                5
        );

        String enterFolderByName = getFolderName(name_of_folder);
        this.waitForElementAndClick(By.xpath(enterFolderByName),
                "Cannot add new article to my folder '" + name_of_folder + "'",
                5);
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot press 'CLOSE' button, cannot find X link",
                5
        );
    }

    public void notWaitTitlePresent() {
        this.notWaitForElementPresent(By.id(TITLE), "Element 'page_title' not enabled");
    }
}
