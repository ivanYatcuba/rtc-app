package net.github.rtc.app.service.log;

import net.github.rtc.app.model.entity.logs.Logs;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class LogServiceImpl implements LogService {

    //todo: maybe move it to property file and use @Value?
    private static final String PATH_FOLDER = "/var/log/rtc-app/logs/";
    private static final int BEGIN_INDEX = 22;
    private static final int BYTES = 1024;

    @Override
    public List<Logs> findAllLogs() {
        final File folder = new File(getPathFolder());
        final List<Logs> listOfLogs = new ArrayList<Logs>();
        if (folder.listFiles().length > 0) {
            for (final File fileEntry : folder.listFiles()) {
                if (fileEntry.isFile()) {
                    try {
                        listOfLogs.add(new Logs(fileEntry.getPath().substring(BEGIN_INDEX), getSize(fileEntry), getCreatedDate(fileEntry.getPath())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return listOfLogs;
    }

    @Override
    public String getLogData(final String fileName) {
        final StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(getPathFolder() + fileName), Charset.defaultCharset()))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                builder.append(currentLine);
                builder.append(System.lineSeparator());
            }
        } catch (IOException e) {
            builder.append(e);
        }
        return builder.toString();
    }

    /**
     * Get path of the folder that contains logs
     * @return path
     */
    public static String getPathFolder() {
        return PATH_FOLDER;
    }

    /**
     * Get log file size in Megabytes
     * @param file file for what size will be calculated
     * @return size in string with Mb suffix
     */
    private String getSize(final File file) {
        String size = "";
        if (file.exists()) {
            final int sizeInMegabytes = (int) ((file.length() / BYTES) / BYTES);
            size = sizeInMegabytes + "Mb";
        }
        return size;
    }

    /**
     * Get creation date of log file
     * @param filePath path of the file
     * @return
     * @throws IOException if file not found
     */
    private Date getCreatedDate(final String filePath) throws IOException {
        final Path file = Paths.get(filePath);
        final BasicFileAttributes attributes = Files.readAttributes(file, BasicFileAttributes.class);
        return new Date(attributes.creationTime().toMillis());
    }
}
