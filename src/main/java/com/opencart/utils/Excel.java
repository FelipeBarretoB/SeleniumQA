package com.opencart.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Excel {
    private  String filePath;

    private String outputPath;

    public Excel(String filePath) {
        this.filePath = filePath;
        this.outputPath = "";
    }

    public void setOutputPath(String outputPath){
        this.outputPath = outputPath;
    }

    public List<String[]> readData(int SheetIndex) {
        List<String[]> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheetAt(SheetIndex);
            DataFormatter formatter = new DataFormatter();

            // Agrega los encabezados como la primera fila
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                int cellCount = sheet.getRow(i).getLastCellNum();
                String[] rowData = new String[cellCount];
                for (int j = 0; j < cellCount; j++) {
                    rowData[j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
                }
                data.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void writeData(List<String[]> data, String log) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet(log);
            for (int i = 0; i < data.size(); i++) {
                String[] rowData = data.get(i);
                org.apache.poi.ss.usermodel.Row row = sheet.createRow(i);
                for (int j = 0; j < rowData.length; j++) {
                    row.createCell(j).setCellValue(rowData[j]);
                }
            }
            try (java.io.FileOutputStream fos = new java.io.FileOutputStream(outputPath)) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}