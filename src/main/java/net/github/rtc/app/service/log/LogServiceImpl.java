package net.github.rtc.app.service.log;

import net.github.rtc.app.model.dto.Log;
import net.github.rtc.app.model.dto.filter.LogsSearchFilter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

@Component
public class LogServiceImpl implements LogService {

    //todo: maybe move it to property file and use @Value?
    private static final String PATH_FOLDER = "/var/log/rtc-app/logs/";
    private static final int BEGIN_INDEX = 22;
    private static final int BYTES = 1024;

    @Override
    public List<Log> findAllLogs() {
        final File folder = new File(getPathFolder());
        final List<Log> listOfLogs = new ArrayList<Log>();
        if (folder.listFiles().length > 0) {
            for (final File fileEntry : folder.listFiles()) {
                if (fileEntry.isFile()) {
                    try {
                        listOfLogs.add(new Log(fileEntry.getPath().substring(BEGIN_INDEX), getSize(fileEntry), getCreatedDate(fileEntry.getPath())));
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

    public Map<String, Object> getLogsPageParams(LogsSearchFilter logsSearchFilter) {
        int begin = (logsSearchFilter.getPage() - 1) * logsSearchFilter.getPerPage();
        int end = begin + logsSearchFilter.getPerPage();
        if (end > findAllLogs().size()) {
            end = findAllLogs().size();
        }
        final Map<String, Object> map = new HashMap<>();
        map.put("logs", findAllLogs().subList(begin, end));
        map.put("currentPage", logsSearchFilter.getPage());
        final int countPages = findAllLogs().size() / logsSearchFilter.getPerPage() + ((findAllLogs().size() % logsSearchFilter.getPerPage() == 0) ? 0 : 1);
        map.put("lastPage", countPages);
        if (logsSearchFilter.getPage() == countPages) {
            begin = Math.max(1, logsSearchFilter.getPage() - logsSearchFilter.getPageOffset() - 1);
            end = logsSearchFilter.getPage();
        } else {
            begin = Math.max(1, logsSearchFilter.getPage() - logsSearchFilter.getPageOffset());
            if (countPages == logsSearchFilter.getMaxOffset()) {
                end = logsSearchFilter.getMaxOffset();
            } else {
                end = Math.min(begin + logsSearchFilter.getPageOffset(), countPages);
            }
        }
        map.put("beginIndex", begin);
        map.put("endIndex", end);
        return map;
    }
}
