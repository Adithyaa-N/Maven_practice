package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Test1 {
    int id;
    String name;

    public Test1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

public class ReadExcel {
    public static void main(String[] args) throws IOException {
        List<Test1> data1List = getExcelData();

        // Print extracted data
        for (Test1 obj : data1List) {
            System.out.println("ID: " + obj.getId() + ", Name: " + obj.getName());
        }
    }

    public static List<Test1> getExcelData() throws IOException {
        List<Test1> list = new ArrayList<>();

        FileInputStream fis = new FileInputStream(new File("src/main/resources/ExcelData.xlsx"));
        XSSFWorkbook book = new XSSFWorkbook(fis);
        Sheet sheet = book.getSheetAt(0);
        Iterator<Row> rows = sheet.rowIterator();

        while (rows.hasNext()) {
            Row row = rows.next();
            Iterator<Cell> cells = row.cellIterator();

            int id = 0;
            String name = "";

            while (cells.hasNext()) {
                Cell cell = cells.next();

                switch (cell.getCellType()) {
                    case NUMERIC:
                        id = (int) cell.getNumericCellValue();
                        break;
                    case STRING:
                        name = cell.getStringCellValue();
                        break;
                }
            }
            list.add(new Test1(id, name));
        }
        return list;
    }
}
