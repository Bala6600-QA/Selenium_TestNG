package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    public static Object[][] getdata(String sheetname) throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetname);
        int rows = sheet.getPhysicalNumberOfRows();
        int columns = sheet.getRow(0).getPhysicalNumberOfCells();

        // Count only valid non-empty rows
        int validRows = 0;
        for (int i = 1; i < rows; i++) {
            if (sheet.getRow(i) != null &&
                sheet.getRow(i).getCell(0) != null &&
                !sheet.getRow(i).getCell(0).toString().trim().isEmpty()) {
                validRows++;
            }
        }

        Object[][] data = new Object[validRows][1];
        int dataIndex = 0;

        for (int i = 0; i < rows - 1; i++) {
            // Skip blank rows
            if (sheet.getRow(i + 1) == null ||
                sheet.getRow(i + 1).getCell(0) == null ||
                sheet.getRow(i + 1).getCell(0).toString().trim().isEmpty()) {
                continue;
            }
            HashMap<String, String> map = new HashMap<>();
            for (int j = 0; j < columns; j++) {
                String key = sheet.getRow(0).getCell(j).toString().trim();
                String value = sheet.getRow(i + 1).getCell(j) != null ?
                               sheet.getRow(i + 1).getCell(j).toString().trim() : "";
                map.put(key, value);
            }
            data[dataIndex][0] = map;
            dataIndex++;
        }
        workbook.close();
        return data;
    }
}