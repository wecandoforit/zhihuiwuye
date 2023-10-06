package com.project.property.utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

/**
 * Excel工具类
 * @Author
 * @Date
 * @Description
 */
public class ExcelUtil {

    /**
     * 提供下载的方法
     * @param sheetName
     * @param title
     * @param values
     * @param wb
     * @return
     */
    public static XSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, XSSFWorkbook wb) {
        
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if (wb == null) {
            wb = new XSSFWorkbook();
        }
 
        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = wb.createSheet(sheetName);
 
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        XSSFRow row = sheet.createRow(0);
 
        // 第四步，创建单元格，并设置值表头 设置表头居中
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
 
        // 声明列对象
        XSSFCell cell = null;
 
        // 创建标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
 
        // 创建内容
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values[i].length; j++) {
                // 将内容按顺序赋给对应的列对象
                XSSFCell cell1 = row.createCell(j);
                cell1.setCellValue(values[i][j]);
                cell1.setCellStyle(style);
            }
        }
        setSizeColumn(sheet, title.length);
        return wb;
    }

    /**
     * 打印到本地桌面
     * @param sheetName sheet名
     * @param title     标题集合
     * @param values    值
     * @param wb        工作簿
     */
    public static void createExcel(String sheetName, String[] title, String[][] values, XSSFWorkbook wb) {
        try {
            FileOutputStream fout = new FileOutputStream("C:\\Users\\11134\\Desktop\\"+sheetName+".xlsx");
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 自适应宽度(中文支持)
    private static void setSizeColumn(XSSFSheet sheet, int size) {
        for (int columnNum = 0; columnNum < size; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                XSSFRow currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }

                if (currentRow.getCell(columnNum) != null) {
                    XSSFCell currentCell = currentRow.getCell(columnNum);
                    if (currentCell.getCellType() == CellType.STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            sheet.setColumnWidth(columnNum, columnWidth * 256);
        }
    }

    /**
     * 根据传入的sheet名字返回XSSFSheet对象
     * @param file
     * @param sheetName
     * @return
     */
    public static Sheet getSheetXlsx(File file, String sheetName) {
        // 定义一个数据格式化对象
        XSSFWorkbook wb = null;
        // 创建文件流
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            // 读取Excel模板
            wb = new XSSFWorkbook(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (wb != null) {
            // 获取sheet
            XSSFSheet sheet = wb.getSheet(sheetName);
            wb.getNumberOfSheets();
            try {
                wb.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sheet;
        }
        return null;
    }

    /**
     * 根据传入的sheet名字返回HSSFSheet对象
     * @param file
     * @param sheetName
     * @return
     */
    public static Sheet getSheetXls(File file, String sheetName) {
        // 定义一个数据格式化对象
        XSSFWorkbook wb = null;
        // 创建文件流
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            // 读取Excel模板
            wb = new XSSFWorkbook(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (wb != null) {
            // 获取sheet
            XSSFSheet sheet = wb.getSheet(sheetName);
            wb.getNumberOfSheets();
            try {
                wb.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sheet;
        }
        return null;
    }
}
