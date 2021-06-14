package Utilities;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {
    private Sheet sheet;
    private final Map<String, Integer> columns = new HashMap<>();

    public ExcelReader() {
    }

    public void setExcelFile(String filePath, String fileName, String sheetName){
        try {
            //Create an object of File class to open xlsx file
            File file = new File(filePath + "\\" + fileName);

            //Create an object of FileInputStream class to read excel file
            FileInputStream fileIS = new FileInputStream(file);
            Workbook workbook = null;

            //Find the file extension by splitting file name in substring  and getting only extension name
            String fileExtensionName = fileName.substring(fileName.indexOf("."));

            //Check condition if the file is xlsx file
            if (fileExtensionName.equals(".xlsx")) {
                //If it is xlsx file then create object of XSSFWorkbook class
                workbook = new XSSFWorkbook(fileIS);
            }
            //Check condition if the file is xls file
            else if (fileExtensionName.equals(".xls")) {
                //If it is xls file then create object of HSSFWorkbook class
                workbook = new HSSFWorkbook(fileIS);
            }
            //Read sheet inside the workbook by its name
            assert workbook != null;
            sheet = workbook.getSheet(sheetName);


            //Adding all column header names to the map "columns"
            sheet.getRow(0).forEach(cell -> columns.put(cell.getStringCellValue(), cell.getColumnIndex()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getCellData(int colNum, int rowNum) {
        try {
            Cell cell = sheet.getRow(rowNum).getCell(colNum);
            String cellData = null;
            switch (cell.getCellType()) {
                case STRING:
                    cellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        cellData = String.valueOf((long) cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    cellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    cellData = "";
                    break;
            }
            return cellData;
        } catch (Exception e) {
            return "#ERROR";
        }
    }

    public String getCellData(String columnName,int rowNum) {
        return getCellData(columns.get(columnName),rowNum);
    }

    public Integer getRowNum(){
        int rowCount;
        return sheet.getLastRowNum()-sheet.getFirstRowNum();
    }

}
