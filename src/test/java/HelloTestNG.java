import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class HelloTestNG {

    @Test
    public void testHello() {
        System.out.println("✅ TestNG is working!");
    }

    @Test
    public void sum() {
        int num = 10 / 0;
    }

    @DataProvider(name = "add")
    public Double[][] getNums() throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\2407244\\IdeaProjects\\Maven_practice\\src\\main\\resources\\ExcelData.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Double[][] data = new Double[rowCount - 1][colCount]; // Exclude header row

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                switch (cell.getCellType()) {
                    case NUMERIC:
                        data[i - 1][j] = cell.getNumericCellValue();
                        break;
                    default:
                        data[i - 1][j] = null;
                        break;
                }
            }
        }
        return data;
    }

    @Test(dataProvider = "add")
    public void add(double a, double b, double expected) {
        double sum = a + b;
        System.out.println("Computed Sum: " + sum);
        Assert.assertEquals(sum, expected, "Addition Test Failed");
    }
}