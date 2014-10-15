package net.github.rtc.app.model.user;


import net.github.rtc.app.exception.ServiceProcessingException;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

/**
 * Created by Daria on 9.10.2014.
 */

@org.springframework.stereotype.Component
public class FileUpload implements java.io.Serializable {

    private static final String PATH_TO_PHOTO = System.getProperty("catalina.home") + "/images";

    public String saveImage(Long filename, MultipartFile image) {
        final String adr = PATH_TO_PHOTO + "/" + filename + ".jpg";
        try {
            final File file = new File(adr);
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


    public void folderPhoto() {
        final File f = new File(PATH_TO_PHOTO);
        if (!f.exists()) {
            f.mkdir();
        }
    }
}
