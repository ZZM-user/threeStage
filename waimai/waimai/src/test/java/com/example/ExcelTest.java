package com.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author： 17602
 * @Date： 2022/9/1 9:49
 * @Desc： excel测试
 **/
public class ExcelTest {
    
    @Test
    public void writeExcel() throws Exception {
        String[] headers = {"编号", "姓名", "性别", "年龄"};
        String[][] example = {
                {"1", "张三", "男", "18"},
                {"2", "王五", "男", "21"},
                {"3", "二麻子", "男", "18"},
                {"4", "如花", "女", "16"},
                {"5", "牛马", "女", "17"}};
        File file = new File("workbook.xlsx");
        generateXlsx(headers, example, file);
    }
    
    public void generateXlsx(String[] header, String[][] example, File file) throws Exception {
        try (
                // 创建文件流
                OutputStream os = Files.newOutputStream(file.toPath());
                // 创建工作文档对象
                Workbook wb = new XSSFWorkbook();
        ) {
            Map<StyleEnum, CellStyle> excelStyle = excelStyle(wb);
            CellStyle dateStyle = excelStyle.get(StyleEnum.YYYYMMDDHHMM);
            CellStyle borderStyle = excelStyle.get(StyleEnum.ALL_BORDER_STYLE);
            CellStyle youyuanStyle = excelStyle.get(StyleEnum.FONT_YOU_YUAN);
            
            // 创建sheet对象
            Sheet sheet = wb.createSheet("sheet1");
            // 循环写入行数据
            Row row = sheet.createRow(0);
            for (int i = 0; i < header.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(header[i]);
                cell.setCellStyle(youyuanStyle);
                cell.setCellStyle(borderStyle);
            }
            for (int i = 0; i < example.length; i++) {
                row = sheet.createRow(i + 1);
                // 循环写入列数据
                for (int j = 0; j < example[i].length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(example[i][j]);
                    cell.setCellStyle(borderStyle);
                }
            }
            // 写入数据
            wb.write(os);
        }
    }
    
    /**
     * 常用的格式放在这里
     *
     * @return
     */
    private Map<StyleEnum, CellStyle> excelStyle(Workbook wb) {
        // 辅导工具类
        CreationHelper createHelper = wb.getCreationHelper();
        
        Map<StyleEnum, CellStyle> mp = new HashMap<>();
        
        // 长格式 yyyy/m/d h:mm
        CellStyle cellStyle01 = wb.createCellStyle();
        cellStyle01.setDataFormat(createHelper.createDataFormat().getFormat("yyyy/m/d h:mm"));
        mp.put(StyleEnum.YYYYMMDDHHMM, cellStyle01);
        
        // 短格式 yyyy/m/d
        CellStyle cellStyle02 = wb.createCellStyle();
        cellStyle02.setDataFormat(createHelper.createDataFormat().getFormat("yyyy/m/d"));
        mp.put(StyleEnum.YYYYMMDD, cellStyle01);
        
        // 时间 h:mm
        CellStyle cellStyle03 = wb.createCellStyle();
        cellStyle03.setDataFormat(createHelper.createDataFormat().getFormat("h:mm"));
        mp.put(StyleEnum.HHMM, cellStyle03);
        
        // 全边框
        CellStyle cellStyle04 = wb.createCellStyle();
        cellStyle04.setBorderTop(BorderStyle.THIN);
        cellStyle04.setBorderBottom(BorderStyle.THIN);
        cellStyle04.setBorderLeft(BorderStyle.THIN);
        cellStyle04.setBorderRight(BorderStyle.THIN);
        mp.put(StyleEnum.ALL_BORDER_STYLE, cellStyle04);
        
        // 幼圆
        CellStyle cellStyle05 = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("幼圆");
        font.setBold(true);
        cellStyle05.setFont(font);
        mp.put(StyleEnum.FONT_YOU_YUAN, cellStyle05);
        return mp;
    }
    
    enum StyleEnum {
        YYYYMMDD,
        YYYYMMDDHHMM,
        HHMM,
        ALL_BORDER_STYLE,
        FONT_YOU_YUAN
    }
}
