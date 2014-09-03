package net.github.rtc.app.export;

import net.github.rtc.util.annotation.ForExport;
import net.github.rtc.app.export.table.CSVTable;
import net.github.rtc.app.export.table.ReportTable;
import net.github.rtc.app.export.table.XLSNXTable;
import net.github.rtc.app.model.report.ExportFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Ivan Yatcuba on 7/29/14.
 *
 * This class helps to create xlsx report file of some model  class collection on disk
 */
public class ReportBuilder {

    /**
     * Create xlsx report file
     *
     * @param reportFields report about this model class,
     *                  which fields that needs to be in report are annotated by @ForExport.
     * @param objectsList list of object that will be stored in report table.
     * @param sheetName name of sheet in xlsx file.
     * @param filePath path where report file will be stored
     */
    public <T> void build(List<Field> reportFields, List<T> objectsList,
                            String sheetName ,String filePath, ExportFormat exportFormat){

        ReportTable reportTable = null;
        if(exportFormat.equals(ExportFormat.XLSX)){
            reportTable = new XLSNXTable(new XSSFWorkbook(), sheetName);
        }else if(exportFormat.equals(ExportFormat.XLS)){
            reportTable = new XLSNXTable(new HSSFWorkbook(), sheetName);
        }else if(exportFormat.equals(ExportFormat.CSV)){
           reportTable = new CSVTable();
        }

        //Table header creation
        int currentCol = 0;
        int currentRow = 0;
        reportTable.createRow(currentRow);
        for(Field field : reportFields){
            reportTable.createCell(currentRow, currentCol, field.getAnnotation(ForExport.class).value());
            currentCol++;
        }
        //Getting info from list
        currentRow++;
        for(T object : objectsList){
            reportTable.createRow(currentRow);
            for(int j =0; j < reportFields.size(); j++){
                reportFields.get(j).setAccessible(true);
                try {
                    if(reportFields.get(j).getDeclaringClass() != object.getClass()){
                        for(Field f :object.getClass().getDeclaredFields()){
                            if(f.getType() == reportFields.get(j).getDeclaringClass()){
                                f.setAccessible(true);
                                reportTable.createCell(currentRow, j, reportFields.get(j).get(f.get(object)));
                                f.setAccessible(false);break;
                            }
                        }
                    }else{
                        reportTable.createCell(currentRow, j, reportFields.get(j).get(object));
                    }
                } catch (IllegalAccessException | NullPointerException e) {
                    reportTable.createCell(currentRow, j, "");
                }
                reportFields.get(j).setAccessible(false);
            }
            currentRow++;
        }
        try {reportTable.writeToFile(filePath);}
        catch (IOException e){e.printStackTrace();}
    }
}
