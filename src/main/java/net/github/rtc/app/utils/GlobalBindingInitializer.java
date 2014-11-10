package net.github.rtc.app.utils;

;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by denis on 10.11.14.
 */
@ControllerAdvice
public class GlobalBindingInitializer {
    @InitBinder
    public void registerCustomEditors(WebDataBinder binder, WebRequest request) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
         }
}
