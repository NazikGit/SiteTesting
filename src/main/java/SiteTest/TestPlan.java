package SiteTest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestPlan {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeClass
    public static void main(String[] args) {
        // ChromeDriver location set up in Utils class
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test
    public void submitForm() {
        driver.get(Utils.BASE_URL2);
        WebForm webForm = new WebForm(driver);
        webForm.EnterSearchText("Selenium");
        //webForm.pressSubmitButton();
    }

    @AfterClass
    public static void cleanUp() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
