package SiteTest;

import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static SiteTest.Utils.BASE_URL2;
import static org.junit.Assert.assertEquals;

class TestPlanTest {
    private static final WebDriver driver = new ChromeDriver();

    WebForm webForm = new WebForm(driver);
    WebDriverWait waitForOne = new WebDriverWait(driver, 10);

    @SpringBootTest(properties = "spring.main.banner-mode=off")

    @Test
    void LoginWithWrongUserName() {
        webForm.openLearnThatSite();
        webForm.UserName("FakeUser");
        webForm.Password("www");
        webForm.LogInButton();
        String s = "Login failed. Invalid username or password.";
        assertEquals("Login failed. Invalid username or password.", s.substring(s.length() - "Login failed. Invalid username or password.".length()));

    }

    @Test
    void LoginWithWrongPassword() {
        webForm.openLearnThatSite();
        webForm.UserName("www");
        webForm.Password("FakePassword");
        webForm.LogInButton();
        String s = "Login failed. Invalid username or password.";
        assertEquals("Login failed. Invalid username or password.", s.substring(s.length() - "Login failed. Invalid username or password.".length()));
    }

    @Test
    void SuccessfulLoginTest() {
        webForm.openLearnThatSite();
        webForm.UserName("www");
        webForm.Password("www");
        webForm.LogInButton();
        String s = "Welcome, www!";
        assertEquals("Welcome, www!", s.substring(s.length() - "Welcome, www!".length()));

    }

    @Test
    void SuccessfulLogOutTest() {
        webForm.openLearnThatSite();
        webForm.UserName("www");
        webForm.Password("www");
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//input[@type='submit']")));
        webForm.LogInButton();
        String s = "Welcome, www!";
        assertEquals("Welcome, www!", s.substring(s.length() - "Welcome, www!".length()));
        Actions action = new Actions(driver);
        WebElement accmenu = driver.findElement(By.xpath("//a[contains(text(),'my account')]"));
        action.moveToElement(accmenu).perform();
        webForm.LogOutButton();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, BASE_URL2);
    }

    @Test
    void TryToChangePasswordProvidingWrongExistingOne() {
        webForm.openLearnThatSite();
        webForm.UserName("www");
        webForm.Password("www");
        webForm.LogInButton();
        String s = "Welcome, www!";
        assertEquals("Welcome, www!", s.substring(s.length() - "Welcome, www!".length()));
        Actions action = new Actions(driver);
        WebElement accmenu = driver.findElement(By.xpath("//a[contains(text(),'my account')]"));
        action.moveToElement(accmenu).perform();
        webForm.ChangePassword();
        webForm.ExistingPassword("WRONGPASSWORD");
        webForm.NewPassword("SomeNew");
        webForm.NewPasswordConfirm("SomeNew");
    }

    @AfterClass
    public void cleanUp() {
        driver.manage().deleteAllCookies();
        webForm.driver.quit();
    }
}