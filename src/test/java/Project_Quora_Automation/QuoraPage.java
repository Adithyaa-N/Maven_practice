package Project_Quora_Automation;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class QuoraPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public QuoraPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openSignUpFlow() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/div/div/div[text()='Sign In']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Sign up with email']"))).click();
    }

    public String enterInvalidEmailAndGetError() {
        driver.findElement(By.xpath("//label[text()='Name']/following::input")).sendKeys("john");
        WebElement emailBox = driver.findElement(By.id("email"));
        emailBox.sendKeys("abc@abc");
        emailBox.sendKeys(Keys.TAB);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'red_error')]"))).getText();
    }

    public void openLoginFlow(String email, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/div/div/div[text()='Sign In']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Login']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys(email + Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(password + Keys.ENTER);
        driver.findElement(By.xpath("//input[@id='password']/following::button")).click();
    }

    public List<WebElement> search(String keyword) throws InterruptedException {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Search content']")));
        searchBox.sendKeys(keyword + Keys.ENTER);
        Thread.sleep(3000);
        return driver.findElements(By.xpath("//*[@id='mainContent']//div[contains(@class,'q-box')]"));
    }
}
