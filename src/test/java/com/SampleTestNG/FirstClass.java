package com.SampleTestNG;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class FirstClass {

    WebDriver driver; // Class-level WebDriver reference
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\2407244\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.bing.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("Page Title is: " + driver.getTitle());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "add")
    public Object[][] getNums() {
        return new Object[][] {
                {10, 20, 30},
                {100, 200, 300},
                {34, 43, 77}
        };
    }

    @Test(dataProvider = "add")
    public void testAddition(int a, int b, int expected) {
        int sum = a + b;
        Assert.assertEquals(sum, expected, "Addition Test Failed");
    }

    @Test
    public void testSubtraction() {
        int a = 10, b = 20;
        int result = a - b;
        Assert.assertEquals(result, -10, "Subtraction Test Failed");
    }

    @Test
    public void checkTitle() {
        String title = driver.getTitle();
        Assert.assertEquals(title, "Search - Microsoft Bing", "Title did not match expected");
    }


    @DataProvider(name="check")
    public String[] getSearch() throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\2407244\\IdeaProjects\\Maven_practice\\src\\main\\resources\\ExcelData.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(1);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        String[] data = new String[rowCount]; // Exclude header row

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                switch (cell.getCellType()) {
                    case STRING:
                        data[i] = cell.getStringCellValue();
                        break;
                    default:
                        data[i] = null;
                        break;
                }
            }
        }
        return data;
    }
    @Test(dataProvider = "check")
    public void checkResults(String input) {
        WebElement searchBar = driver.findElement(By.id("q"));
        searchBar.sendKeys(input);
        searchBar.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3")));

        List<WebElement> results = driver.findElements(By.xpath("//h3"));

        for (WebElement result : results) {
            String text = result.getText().toLowerCase();
            Assert.assertTrue(text.contains(input.toLowerCase()), "Missing " + input + " in: " + result.getText());
        }

    }

}
