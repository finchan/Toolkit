package com.xavier.pandora.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Arrays;
import java.util.Date;

public class POIExport {
    public static void main(String[] args) throws IOException {
        //Parameters
        String sourceDirectory = "D:\\Files";
        String generatedDirectory = "D:\\Files\\1104";
        int sheetNumber = 0;
        int[][] cells = {{6, 4}, {7, 4}, {8, 4}, {10, 4}, {11, 4}, {12, 4}};
        //Generate File
        export(sourceDirectory, generatedDirectory, sheetNumber, cells);
    }

    public static void export(String sourceDirectory, String generatedDirectory, int sheetNumber, int[][] cells)  throws IOException {
        File[] files = getFilesPath(sourceDirectory);
        files = Arrays.stream(files)
                .sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
                .toArray(File[] :: new);
        FileOutputStream fos = null;

        File destinationFolder = new File(generatedDirectory);
        if (!destinationFolder.exists()) {
            destinationFolder.mkdir();
        }
        //Generate xlsx File
        Workbook gwb = new XSSFWorkbook();
        Sheet gsheet = gwb.createSheet("Generated 1104");

        fos = new FileOutputStream(new File(generatedDirectory + "\\" + new Date().getHours() + "-" +new Date().getMinutes() + ".xlsx"));
        int column = 0;
        Row headerRow = gsheet.createRow(0);
        for (int i = 0; i < cells.length; i++) {
            System.out.println("===========================" + cells[i][0]+"," + cells[i][1]);
            headerRow.createCell(column).setCellValue(cells[i][0]+"," + cells[i][1]);
            column++;
            int months = 0;
            for(File file: files) {
                //Read File
                System.out.println(file.getName());
                FileInputStream fis = new FileInputStream(file);
                Workbook workbook = null;
                if (file.getName().endsWith(".xlsx")) {
                    workbook = new XSSFWorkbook(fis);
                } else {
                    workbook = new HSSFWorkbook(fis);
                }
                fis.close();
                //Deal with Data
                Sheet sheet = workbook.getSheetAt(sheetNumber);
                Row row = sheet.getRow(cells[i][0]);
                Cell cell = row.getCell(cells[i][1]);
                months++;
                if (gsheet.getRow(months) == null) {
                    gsheet.createRow(months);
                }
                switch (cell.getCellType()){
                    case STRING:
                        System.out.println(cell.getStringCellValue());
                        gsheet.getRow(months).createCell(i).setCellValue(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        System.out.println(cell.getNumericCellValue());
                        gsheet.getRow(months).createCell(i).setCellValue(cell.getNumericCellValue());
                        break;
                    case FORMULA:
                        System.out.println(cell.getCellType());
                        FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
                        CellValue cellValue = evaluator.evaluate(cell);
                        CellType type = cellValue.getCellTypeEnum();
                        switch (type) {
                            case NUMERIC:
                                gsheet.getRow(months).createCell(i).setCellValue(cell.getNumericCellValue());
                                break;
                            case STRING:
                                gsheet.getRow(months).createCell(i).setCellValue(cell.getStringCellValue());
                                break;
                            case BOOLEAN:
                                gsheet.getRow(months).createCell(i).setCellValue(cell.getBooleanCellValue());
                                break;
                            case BLANK:
                                gsheet.getRow(months).createCell(i).setCellValue(0);
                                break;
                            case _NONE:
                                gsheet.getRow(months).createCell(i).setCellValue(0);
                                break;
                            case ERROR:
                                gsheet.getRow(months).createCell(i).setCellValue("ERROR");
                                break;
                        }
                }
                workbook.close();
            }
        }
        //write to file
        gwb.write(fos);
        gwb.close();
        if(fos != null) {
            fos.close();
        }
    }

    public static File[]  getFilesPath(String FileDirectory) {
        File directory = new File(FileDirectory);
        File[] files = new File[] {};
        if (directory.isDirectory()) {
            files = directory.listFiles();
            return files;
        }
        throw  new RuntimeException("This is not a directory!");
    }
}
