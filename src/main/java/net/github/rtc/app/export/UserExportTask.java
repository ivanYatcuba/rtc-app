package net.github.rtc.app.export;

import net.github.rtc.app.annotation.ForExport;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.service.UserService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan Yatcuba on 7/29/14.
 */

@Component
public class UserExportTask {
    @Autowired
    private UserService userService;

    public void exportData() {
        List<User> userList = userService.findAll();
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("users");

        CreationHelper createHelper = wb.getCreationHelper();
        Field[] fields = User.class.getDeclaredFields();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("dd/mm/yy"));

        int i = 0;
        Row row = sheet.createRow(0);
        for(Field f : fields){
            if(f.isAnnotationPresent(ForExport.class)){
                row.createCell(i).setCellValue(createHelper.createRichTextString(f.getName()));
                i++;
            }
        }

        i=1;
        for(User user : userList){
            sheet.createRow(i);
            for(int j =0; j < fields.length; j++){
                if(fields[j].isAnnotationPresent(ForExport.class)){
                    try {
                        fields[j].setAccessible(true);
                        if((fields[j].getType().equals(String.class))){
                            sheet.getRow(i).createCell(j).setCellValue(createHelper.createRichTextString((String)fields[j].get(user)));
                        }else if((fields[j].getType().equals(Date.class))){
                            sheet.getRow(i).createCell(j).setCellValue((Date) fields[j].get(user));
                            sheet.getRow(i).getCell(j).setCellStyle(cellStyle);
                        }else if((fields[j].getType().equals(Integer.class))){
                            sheet.getRow(i).createCell(j).setCellValue((Integer)fields[j].get(user));
                        }
                        fields[j].setAccessible(false);
                    } catch (IllegalAccessException | NullPointerException e) {
                        sheet.getRow(i).createCell(j).setCellValue("null");
                    }
                }
            }
            i++;
        }


        File file;
        FileOutputStream fileOut = null;
        try {
            file = new File("/var/log/rtc-app/users.xlsx");
            if (!file.exists()) {
                file.createNewFile();
            }
            fileOut = new FileOutputStream(file);
            wb.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
