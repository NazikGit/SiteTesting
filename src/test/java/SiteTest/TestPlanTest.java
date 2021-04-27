package SiteTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class TestPlanTest {
    private static final WebDriver driver = new ChromeDriver();

    WebForm webForm = new WebForm(driver);

    @Test
    void LoginWithWrongUserName() {
        webForm.openLearnThatSite();
        webForm.provideWrongUserName();
        webForm.checkErrorMessage();
    }

    @Test
    void LoginWithWrongPassword() {
        webForm.openLearnThatSite();
        webForm.provideWrongPassword();
        webForm.checkErrorMessage();
    }

    @Test
    void SuccessfulLoginTest() {
        webForm.openLearnThatSite();
        webForm.provideValidUser();
        webForm.checkGreetingMessage();
        //    driver.manage().deleteAllCookies();
    }

    @Test
    void SuccessfulLogOutTest() {
        webForm.openLearnThatSite();
        webForm.provideValidUser();
        webForm.LogOut();
    }

    @Test
    void TryToChangePasswordProvidingWrongExistingOne() {
        webForm.openLearnThatSite();
        webForm.provideValidUser();
        webForm.checkGreetingMessage();
        webForm.changePasswordProvidingWrongPassword();
        webForm.checkValidationError();
        //   driver.manage().deleteAllCookies();
    }

    @AfterEach
    public void cleanUp() {
        driver.manage().deleteAllCookies();
    }

    @AfterAll
    public static void close() {
        driver.close();
    }
}