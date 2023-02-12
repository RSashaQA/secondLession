package ui;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;

public class NavigationUi extends MainPageObject {

    private static final String
            MY_LISTS_LINK = "//android.widget.FrameLayout[@content-desc='My lists']",
            MY_FOLDER_TPL = "//*[@text='{FOLDER_NAME}']",
            MY_ARTICLE_IN_TO_MY_LIST_TPL = "//*[@text='{ARTICLE_NAME}']";

    private static String getFolderName(String substring) {
        return MY_FOLDER_TPL.replace("{FOLDER_NAME}", substring);
    }

    private static String getArticleName(String substring) {
        return MY_ARTICLE_IN_TO_MY_LIST_TPL.replace("{ARTICLE_NAME}", substring);
    }


    public NavigationUi(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyList() {
        this.waitForElementAndClick(
                By.xpath(MY_LISTS_LINK),
                "Cannot find navigation button to 'My list'",
                5
        );
    }

    public void clickMyFolder(String substring) {
        String folder_name_xpath = getFolderName(substring);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find folder with name '" + substring + "'",
                5
        );
    }

    public void swipeElementToLeftForDeleteArticleInMyList(String substring) {
        String article_name_xpath = getArticleName(substring);
        this.swipeElementToLeft(By.xpath(article_name_xpath),
                "Cant swipe to the left article with name '" + article_name_xpath + "'");
    }

    public void clickToArticleInMyList(String substring) {
        String article_name_xpath = getArticleName(substring);
        this.waitForElementAndClick(By.xpath(article_name_xpath),
                "Cant click to article with name '" + substring + "'", 5);
    }
}

