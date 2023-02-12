package tests;

import org.junit.Test;

import lib.CoreTestCase;
import ui.SearchPageObject;

public class ElementHasTextTests extends CoreTestCase {

    @Test
    public void testElementHasText() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String expected_value = "Search Wikipedia";
        SearchPageObject.elementHasText(expected_value);
    }
}
