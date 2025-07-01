import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.List;

public class QuoraAutomation {
    public static WebDriver driver;
    public WebDriverWait wait;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\2407244\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.quora.com/profile/Quora");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void BeforeLogin() {
        try {
            WebElement signIn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button/div/div/div[text()='Sign In']")));
            signIn.click();

            WebElement emailLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[text()='Sign up with email']")));
            emailLink.click();

            WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[text()='Name']/following::input")));
            name.sendKeys("john");

            WebElement emailBox = driver.findElement(By.id("email"));
            emailBox.sendKeys("abc@abc");
            emailBox.sendKeys(Keys.TAB); // trigger validation

            WebElement errorBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'red_error')]")));
            System.out.println("Email error message: " + errorBox.getText());
            driver.navigate().refresh();
        } catch (Exception e) {
            System.out.println("Sign-up step failed: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void AfterTest() {
        try {
            WebElement signIn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button/div/div/div[text()='Sign In']")));
            signIn.click();

            WebElement login = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[text()='Login']")));
            login.click();

            WebElement emailBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@id='email']")));
            emailBox.sendKeys("mrlocalis420@gmail.com");
            emailBox.sendKeys(Keys.ENTER);

            WebElement passBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@id='password']")));
            passBox.sendKeys("Shakthi@2003");
            passBox.sendKeys(Keys.ENTER);

            WebElement buttonElm = driver.findElement(By.xpath("//input[@id='password']/following::button"));
            buttonElm.click();

            System.out.println("Login executed successfully");

        } catch (Exception e) {
            System.out.println("Login step failed: " + e.getMessage());
        }

        driver.navigate().refresh();

        try {
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@placeholder='Search content']")));
            searchBox.sendKeys("Testing");
            searchBox.sendKeys(Keys.ENTER);

            // Allow time for results to appear
            Thread.sleep(3000);

            // Locate result divs
            List<WebElement> resultDivs = driver.findElements(By.xpath("//*[@id='mainContent']//div[contains(@class,'q-box')]"));
            System.out.println("Results found: " + resultDivs.size());
//            for (WebElement div : resultDivs) {
//                System.out.println("Div Text: " + div.getText());
//            }
            // Prepare Excel
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            XSSFSheet sheet = workbook.createSheet("SearchResults");
//
//            int rowNum = 0;
//            for (WebElement div : resultDivs) {
//                String text = div.getText().trim();
//                if (!text.isEmpty()) {
//                    if (text.contains("We couldn't find")) {
//                        System.out.println("No more results — exiting loop.");
//                        break;
//                    }
//                    XSSFRow row = sheet.createRow(rowNum++);
//                    XSSFCell cell = row.createCell(0);
//                    cell.setCellValue(text);
//                }
            }

            // Save the Excel file
//            FileOutputStream out = new FileOutputStream("C:\\Users\\2407244\\IdeaProjects\\Maven_practice\\src\\main\\resources\\ExcelData.xlsx");
//            workbook.write(out);
//            out.close();
//            workbook.close();
//            driver.quit();
//
//            System.out.println("✅ Search results written to Excel successfully.");

        catch (Exception e) {
            System.out.println("Search step failed: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
