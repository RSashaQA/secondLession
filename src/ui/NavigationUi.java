package ui;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;

public class NavigationUi extends MainPageObject{

    private static final String
    MY_LISTS_LINK = "//android.widget.FrameLayout[@content-desc='My lists']";

    public NavigationUi(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyList()
    {
        this.waitForElementAndClick(
                By.xpath(MY_LISTS_LINK),
                "Cannot find navigation button to 'My list'",
                5
        );
    }
}
