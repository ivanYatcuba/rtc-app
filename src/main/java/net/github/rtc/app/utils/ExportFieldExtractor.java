package net.github.rtc.app.utils;

import net.github.rtc.util.annotation.ForExport;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ivan Yatcuba on 8/22/14.
 */
public class ExportFieldExtractor {

    public static List<Field> getFieldsFromClass(Class exportClass, List<String> fields) throws NoSuchFieldException {
        List<Field> classFields = new ArrayList<>();
        for(Field f : exportClass.getDeclaredFields()){
            if(f.isAnnotationPresent(ForExport.class) && fields.contains(f.getAnnotation(ForExport.class).value())){
                classFields.add(f);
                if(f.getAnnotation(ForExport.class).inculdeField().length != 0){
                    classFields.addAll(getFieldsFromClass(f.getType(), Arrays.asList(f.getAnnotation(ForExport.class).inculdeField())));
                }
            }
        }
        return classFields;
    }

    public static List<String> getAviableFieldList(Class aClass){
        List<String> classFields = new ArrayList<>();
        for(Field f : aClass.getDeclaredFields()){
            if(f.isAnnotationPresent(ForExport.class)){
                classFields.add( f.getAnnotation(ForExport.class).value());
                if(f.getAnnotation(ForExport.class).inculdeField().length != 0){
                    classFields.addAll(Arrays.asList(f.getAnnotation(ForExport.class).inculdeField()));
                }
            }
        }
        return classFields;
    }
}
