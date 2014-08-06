package net.github.rtc.app.export;

import net.github.rtc.app.annotation.ForExport;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan Yatcuba on 7/29/14.
 *
 * This class helps to create xlsx report file of some model  class collection on disk
 */
public class XLSXReportBuilder {

    /**
     * Create xlsx report file
     *
     * @param reportFields report about this model class,
     *                  which fields that needs to be in report are annotated by @ForExport.
     * @param objectsList list of object that will be stored in report table.
     * @param sheetName name of sheet in xlsx file.
     * @param xlsxPath path where report file will be stored
     */
    public <T> void build(List<Field> reportFields, List<T> objectsList,
                            String sheetName ,String xlsxPath){
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet(sheetName);

        CellStyle cellStyle = wb.createCellStyle();
        CreationHelper createHelper = wb.getCreationHelper();
        cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("dd/mm/yy"));

        //Table header creation
        int currentCol = 0;
        int currentRow = 0;
        Row row = sheet.createRow(currentRow);
        for(Field field : reportFields){
            row.createCell(currentCol).setCellValue(createHelper.
                    createRichTextString(field.getAnnotation(ForExport.class).value()));
            currentCol++;
        }

        //Getting info from list
        currentRow++;
        for(T object : objectsList){
            sheet.createRow(currentRow);
            for(int j =0; j < reportFields.size(); j++){
                try {
                    writeField(reportFields.get(j), sheet, currentRow, j, object, cellStyle, createHelper);
                } catch (IllegalAccessException | NullPointerException e) {
                    sheet.getRow(currentRow).createCell(j).setCellValue("null");
                }
            }
            currentRow++;
        }

        try {
            writeToFile(wb, xlsxPath);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Write an object's field to report
     *
     * @param field what field?
     * @param sheet sheet in xlsx
     * @param currentRow current row in table
     * @param currentCol current col in table
     * @param cellStyle cell style for Date fields
     * @param createHelper helper
     * @param object what object?
     *
     */
    private void writeField(Field field, Sheet sheet, int currentRow, int currentCol,
                            Object object, CellStyle cellStyle, CreationHelper createHelper) throws IllegalAccessException {

        field.setAccessible(true);
        if((field.getType().equals(Date.class))){
            sheet.getRow(currentRow).createCell(currentCol).
                    setCellValue((Date) field.get(object));
            sheet.getRow(currentRow).getCell(currentCol).setCellStyle(cellStyle);
        }else {
            sheet.getRow(currentRow).createCell(currentCol).
                    setCellValue(createHelper.createRichTextString(field.get(object).toString()));
        }
        field.setAccessible(false);

    }

    /**
     * Create file on disk
     *
     * @param workbook xlsx workbook
     * @param xlsxPath path where report file will be stored
     *
     */
    private void writeToFile(Workbook workbook, String xlsxPath) throws IOException {
        File file = new File(xlsxPath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOut = new FileOutputStream(file);
        workbook.write(fileOut);
        fileOut.close();
    }
}
