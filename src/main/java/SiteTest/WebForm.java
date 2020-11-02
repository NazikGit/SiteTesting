package SiteTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class WebForm extends PageObject {


    @FindBy(name = "search")
    private WebElement search_toolbar;

    public void EnterSearchText(String Parameter) {
        this.search_toolbar.sendKeys(Parameter);

    }

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

    @FindBy(xpath = "//body/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/form[1]/div[6]/input[1]")
    private WebElement SubmitButton;


    public WebForm(WebDriver driver) {
        super(driver);
    }

    public void openLearnThatSite() {
        driver.get(Utils.BASE_URL2);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void UserName(String Parameter) {
        this.UserName.sendKeys(Parameter);
    }

    public void Password(String Parameter) {
        this.Password.sendKeys(Parameter);
    }

    public void LogInButton() {
        this.LogInButton.click();
    }

    public void LogOutButton() {
        this.LogOutButton.click();
    }

    public void ChangePassword() {
        this.ChangePassword.click();
    }

    public void ExistingPassword(String Parameter) {
        this.ExistingPassword.click();
    }

    public void NewPassword(String Parameter) {
        this.NewPassword.click();
    }

    public void NewPasswordConfirm(String Parameter) {
        this.NewPasswordConfirm.click();
    }

    public void SubmitButton() {
        this.SubmitButton.click();
    }


}

