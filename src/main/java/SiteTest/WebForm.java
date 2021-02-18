package SiteTest;

import UsersData.UserData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static SiteTest.Utils.BASE_URL2;
import static org.junit.Assert.assertEquals;

public class WebForm extends PageObject {


    @FindBy(xpath = "//input[@id='UserUsername']")
    private WebElement UserName;

    @FindBy(xpath = "//input[@id='UserPassword']")
    private WebElement Password;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement LogInButton;

    @FindBy(xpath = "//a[contains(text(),'logout')]")
    private WebElement LogOutButton;

    @FindBy(xpath = "//a[contains(text(),'password')]")
    private WebElement ChangePassword;

    @FindBy(xpath = "//input[@id='UserCurrentPassword']")
    private WebElement ExistingPassword;

    @FindBy(xpath = "//input[@id='UserPasswd']")
    private WebElement NewPassword;

    @FindBy(xpath = "//input[@id='UserPasswdConfirm']")
    private WebElement NewPasswordConfirm;

    @FindBy(xpath = "//div[@class='submit']")
    private WebElement SubmitButton;

    @FindBy(xpath = "//div[@id='authMessage']")
    private WebElement ErrorMessage;

    @FindBy(xpath = "//div[@class='bullet']")
    private WebElement CheckTitle;

    @FindBy(xpath = "//a[contains(text(),'my account')]")
    private WebElement MyAccountButton;

    @FindBy(xpath = "//a[contains(text(),'Join now')]")
    private WebElement JoinNowButton;


    public WebForm(WebDriver driver) {
        super(driver);
    }

    public void openLearnThatSite() {
        driver.get(Utils.BASE_URL2);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String URL = driver.getCurrentUrl();
        assertEquals(URL, BASE_URL2);
    }

    public void checkErrorMessage() {
        WebElement bodyText = ErrorMessage;
        String text = bodyText.getText();
        String errorText = "Login failed. Invalid username or password.";
        Assert.assertEquals(errorText, text);
    }

    public void LogOut() {
        Actions action = new Actions(driver);
        WebElement accmenu = MyAccountButton;
        action.moveToElement(accmenu).perform();
        ClickLogOutButton();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, BASE_URL2);
        JoinNowButton.isDisplayed();
    }

    public void checkGreetingMessage() {
        WebElement bodyText = CheckTitle;
        String text = bodyText.getText();
        String welcomeText = "Welcome, www!";
        Assert.assertEquals(welcomeText, text);
    }

    public void checkValidationError() {
        String errorText = "Validation error.";
        WebElement bodyText = CheckTitle;
        String text = bodyText.getText();
        Assert.assertEquals(errorText, text);
    }


    public void provideWrongPassword() {
        EnterUserName(UserData.validUser);
        EnterPassword(UserData.fakePass);
        ClickLogInButton();
    }

    public void provideWrongUserName() {
        EnterUserName(UserData.fakeUser);
        EnterPassword(UserData.validPass);
        ClickLogInButton();
    }

    public void provideValidUser() {
        EnterUserName(UserData.validUser);
        EnterPassword(UserData.validPass);
        ClickLogInButton();
    }

    public void changePasswordProvidingWrongPassword() {
        Actions action = new Actions(driver);
        WebElement accmenu = driver.findElement(By.xpath("//a[contains(text(),'my account')]"));
        action.moveToElement(accmenu).perform();
        ClickChangePassword();
        provideWrongPasswordInExistingPasswordField();
    }

    public void provideWrongPasswordInExistingPasswordField() {
        ExistingPassword.sendKeys("WRONGPASSWORD");
        ExistingPassword("WRONGPASSWORD");
        NewPassword.sendKeys("SomeNew");
        NewPasswordConfirm.sendKeys("SomeNew");
        SubmitButton.submit();
    }


    public void EnterUserName(String Parameter) {
        this.UserName.sendKeys(Parameter);
    }

    public void EnterPassword(String Parameter) {
        this.Password.sendKeys(Parameter);
    }

    public void ClickLogInButton() {
        this.LogInButton.click();
    }

    public void ClickLogOutButton() {
        this.LogOutButton.click();
    }

    public void ClickChangePassword() {
        this.ChangePassword.click();
    }

    public void ExistingPassword(String Parameter) {
        this.ExistingPassword.click();
    }


}

