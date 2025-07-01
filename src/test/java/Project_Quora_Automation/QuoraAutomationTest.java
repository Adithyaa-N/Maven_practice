package Project_Quora_Automation;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class QuoraAutomationTest extends BaseTest {

    @Test(priority = 1)
    public void testInvalidEmail() {
        QuoraPage quora = new QuoraPage(driver, wait);
        quora.openSignUpFlow();
        String error = quora.enterInvalidEmailAndGetError();
        System.out.println("Email error message: " + error);
        driver.navigate().refresh();
    }

    @Test(priority = 2)
    public void testLoginAndSearch() {
        QuoraPage quora = new QuoraPage(driver, wait);
        quora.openLoginFlow("mr********@gmail.com", "S*****i@2**3");
        driver.navigate().refresh();

        try {
            List<WebElement> results = quora.search("Testing");
            System.out.println("Results found: " + results.size());
        } catch (Exception e) {
            System.out.println("Search step failed: " + e.getMessage());
        }
    }
}
