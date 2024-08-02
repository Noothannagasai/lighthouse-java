package org.utilities;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ExcelUtility {
    String Projectpath = System.getProperty("user.dir") + "/src/test/resources/Properties/";
    String Path;
    FileInputStream fis;
    ArrayList<String> headers = new ArrayList<>();
    ArrayList<String> footers = new ArrayList<>();
    XSSFWorkbook excel;
    XSSFSheet sheet;
    DataFormatter df = new DataFormatter();
    int headersCount;

    public ExcelUtility(String excelName, String SheetName) throws IOException {
        Path = Projectpath + excelName;
        this.Projectpath = Path;
        fis = new FileInputStream(Path);
        excel = new XSSFWorkbook(fis);
        headers = getHeaders(SheetName);
    }


    public int getRowNum(String sheetName, String testCaseID) {
        XSSFSheet sheet = excel.getSheet(sheetName);
        int rowNum = -1;
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (String.valueOf(cell).equalsIgnoreCase(testCaseID)) {
                    rowNum = row.getRowNum();
                }
            }
        }
        return rowNum;
    }

    public int getColumnNum(String sheetName, String columnName) throws IOException {
        ArrayList<String> columns = getHeaders(sheetName);
        int columnNum = 0;
        for (int i = 0; i < columns.size(); i++) {
            if (columns.get(i).equalsIgnoreCase(columnName)) {
                columnNum = i;
            }
        }
        return columnNum;
    }

    public ArrayList getRowData(String sheetName, String testCaseID) throws IOException {
        ArrayList footers = getFooters(getRowNum(sheetName, testCaseID), sheetName);
        System.out.println(footers);
        return footers;
    }

    private ArrayList getHeaders(String sheetName) throws IOException {
        ArrayList<String> headers = new ArrayList<>();
        XSSFSheet sheet = excel.getSheet(sheetName);
        XSSFRow row = sheet.getRow(0);
        for (Cell cell : row)
            headers.add(String.valueOf(cell));
        return headers;
    }

    public ArrayList getFooters(int rowNum, String sheetName) throws IOException {
        ArrayList<String> footers = new ArrayList<>();
        XSSFSheet sheet = excel.getSheet(sheetName);
        XSSFRow row = sheet.getRow(rowNum);
        for (Cell cell : row) {
            if (cell.getCellType() == CellType.NUMERIC)
                footers.add(df.formatCellValue(cell));
            else
                footers.add(String.valueOf(cell));
        }
        return footers;

    }
    public String getData(String sheetName, int testCaseNum, String columnName) throws IOException {
        XSSFSheet sheet = excel.getSheet(sheetName);
        int RowNum = sheet.getLastRowNum() - sheet.getFirstRowNum();
        for (int i=0; i<=RowNum; i++) {
            if (i == testCaseNum) {
                for (int j = 0; j <= getHeaders(sheetName).size(); j++) {
                    if (getHeaders(sheetName).get(j).toString().equalsIgnoreCase(columnName)) {
                        return sheet.getRow(i).getCell(j).toString();
                    }
                }
            }
        }return"";
    }
    public int getlastRowNum(String sheetName){
        XSSFSheet sheet = excel.getSheet(sheetName);
        return sheet.getLastRowNum();
    }
    public void writeStatus(String sheetName, int testCaseNum, String columnName, String status) throws IOException {
        XSSFSheet sheet = excel.getSheet(sheetName);
        int RowNum = sheet.getLastRowNum() - sheet.getFirstRowNum();
        for (int i=0; i<=RowNum; i++) {
            if (i == testCaseNum) {
                for (int j = 0; j < headers.size(); j++) {
                    if (headers.get(j).toString().equalsIgnoreCase(columnName)) {
                      sheet.getRow(i).createCell(j).setCellValue(status);
                    }
                }
            }
        }
    }

    public void closeSheet(){
        try {
            Workbook newWorkbook = new XSSFWorkbook();
            copyWorkbook(excel, newWorkbook);
            String timestamp = new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(new Date());
            FileOutputStream out = new FileOutputStream(new File("reports/ReportAt"+timestamp+".xlsx"));
            newWorkbook.write(out);
            out.close();
            System.out.println("ReportAt"+timestamp+".xlsx written successfully on disk.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void copyWorkbook(Workbook source, Workbook target) {
        for (int i = 0; i < source.getNumberOfSheets(); i++) {
            Sheet sourceSheet = source.getSheetAt(i);
            Sheet targetSheet;
            if(source.getNumberOfSheets()>1)
                targetSheet = target.createSheet("Accessibility report-"+i);
            else
                targetSheet = target.createSheet("Accessibility report");
            for (int j = 0; j <= sourceSheet.getLastRowNum(); j++) {
                Row sourceRow = sourceSheet.getRow(j);
                Row targetRow = targetSheet.createRow(j);

                if (sourceRow != null) {
                    for (int k = 0; k < sourceRow.getLastCellNum(); k++) {
                        Cell sourceCell = sourceRow.getCell(k);
                        Cell targetCell = targetRow.createCell(k);

                        if (sourceCell != null) {
                            copyCellValue(sourceCell, targetCell);
                        }
                    }
                }
            }
        }
    }

    private static void copyCellValue(Cell sourceCell, Cell targetCell) {
        switch (sourceCell.getCellType()) {
            case STRING:
                targetCell.setCellValue(sourceCell.getStringCellValue());
                break;
            case NUMERIC:
                targetCell.setCellValue(sourceCell.getNumericCellValue());
                break;
            case BOOLEAN:
                targetCell.setCellValue(sourceCell.getBooleanCellValue());
                break;
            case FORMULA:
                targetCell.setCellFormula(sourceCell.getCellFormula());
                break;
            case BLANK:
                targetCell.setBlank();
                break;
            default:
                break;
        }
    }
}