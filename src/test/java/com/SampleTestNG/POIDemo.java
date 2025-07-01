package com.SampleTestNG;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class POIDemo {
    public static void main(String[] args) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Test Data");
        /*for (int i = 0; i <5; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < 10; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue((i+1)*j);
            }
        }*/
        for (int i = 0; i < 10; i++) {
            Row row = sheet.createRow(i);
            row.createCell(0).setCellValue("Col 0");
            if (i == 0) {
                // Header row
                row.createCell(1).setCellValue("Col 1");
                row.createCell(2).setCellValue("Col 2");
                row.createCell(3).setCellValue("Col 3");
                row.createCell(4).setCellValue("Col 4");
            } else {
                // Data rows start from index 1
                for (int j = 0; j < 5; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue((j + 2) * i); // Fill with data
                }
            }
        }

        FileOutputStream out = new FileOutputStream("C:\\Users\\2407244\\IdeaProjects\\Maven_practice\\src\\main\\resources\\example.xlsx");
        workbook.write(out);
        out.close();
        workbook.close();

        FileInputStream fis = new FileInputStream("C:\\Users\\2407244\\IdeaProjects\\Maven_practice\\src\\main\\resources\\example.xlsx");
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheetAt(0);
        for (Row row1 : sheet) {
            for (Cell cell2 : row1) {
                switch (cell2.getCellType()) {
                    case NUMERIC:
                        System.out.print(cell2.getNumericCellValue() + "\t");
                        break;
                    case BOOLEAN:
                        System.out.print(cell2.getBooleanCellValue() + "\t");
                        break;
                    case FORMULA:
                        System.out.print(cell2.getCellFormula() + "\t");
                        break;
                    case STRING:
                        System.out.print(cell2.getStringCellValue() + "\t");
                        break;
                    default:
                        System.out.print(" \t");
                }
            }
            System.out.println();
        }

        workbook.close();
    }
}
