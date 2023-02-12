package tests;

import org.junit.Test;

import lib.CoreTestCase;
import ui.ArticlePageObject;
import ui.SearchPageObject;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResults() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        String title_before_rotation = ArticlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String title_after_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation);

        this.rotateScreenPortrait();

        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
