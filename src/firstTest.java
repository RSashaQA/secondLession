import com.sun.javafx.PlatformUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;



public class firstTest {
    private AppiumDriver driver;
//123
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        if (PlatformUtil.isWindows()){
            capabilities.setCapability("app", "D:\\LearnQA\\AutoQA\\secondLession\\apks\\org.wikipedia.apk");
        }
        if (PlatformUtil.isLinux()){
            capabilities.setCapability("app", "/home/sanek/IdeaProjects/JavaAutomation/apks/org.wikipedia.apk");
        }

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testFirstTest()
    {
        waitForElementByXpathAndClick(
                "//*[contains(@text,'Search Wikipedia')]",
                "Cannot find search Wikipedia input",
                5
        );

        waitForElementByXpathAndSendKeys(
                "//*[contains(@text,'Search…')]",
                "Java",
                "Cannot find search input",
                3
        );

        waitForElementPresentByXpath(
                "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']",
                "Cant find 'Object-oriented programming language'",
                15
        );
    }

    private WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementPresentByXpath(String xpath, String error_message) {
        return waitForElementPresentByXpath(xpath, error_message, 5);
    }

    private WebElement waitForElementByXpathAndClick(String xpath, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresentByXpath(xpath, error_message, timeoutInSeconds);
        element.click();
        return element;
    }
    private WebElement waitForElementByXpathAndSendKeys(String xpath, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresentByXpath(xpath, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
}