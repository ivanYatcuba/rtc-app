package net.github.rtc.app.service.impl;

import net.github.rtc.app.service.LogService;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LogServiceImpl implements LogService {

    private static final String PATH_FOLDER = "/var/log/rtc-app/logs/";
    private static final int BEGIN_INDEX = 22;

    @Override
    public List<String> getListOfLogs() {
        final File folder = new File(PATH_FOLDER);
        final List<String> listOfFiles = new ArrayList<String>();
        if (folder.listFiles().length > 0) {
            for (final File fileEntry : folder.listFiles()) {
                if (fileEntry.isFile()) {
                    listOfFiles.add(fileEntry.getPath().substring(BEGIN_INDEX));
                }
            }
        } else {
            listOfFiles.add("There are no files in current directory!");
        }
        return listOfFiles;
    }

    @Override
    public String readLogFile(final String fileName) {
        final StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_FOLDER + fileName))) {
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
}
