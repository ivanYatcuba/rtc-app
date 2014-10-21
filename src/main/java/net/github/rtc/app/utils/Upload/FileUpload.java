package net.github.rtc.app.utils.Upload;


import net.github.rtc.app.exception.ServiceProcessingException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * Created by Daria on 9.10.2014.
 */

@org.springframework.stereotype.Component
public class FileUpload implements java.io.Serializable {

    @Value("${img.save.folder}")
    private String imgfold;


    public String saveImage(String filename, MultipartFile image) {
        final String adr = filename + ".jpg";
        try {
            final File file = new File(imgfold + adr);
            if (file.exists()) {
                file.delete();
            }
         //   file= new File(adr);
            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (Exception e) {
            throw new ServiceProcessingException("Unable to save image: " + e.getMessage());
        }
        return adr;
    }

    @PostConstruct
    public void folderPhoto() {
        final File f = new File(imgfold);
        if (!f.exists()) {
            f.mkdir();
        }
    }

    public void deletePhoto(String code) throws Exception {
        final String adr =  code + ".jpg";

        final File file = new File(imgfold + adr);
        if (file.exists()) {
            file.delete();
        }
    }
}
