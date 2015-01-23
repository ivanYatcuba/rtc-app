package net.github.rtc.app.service.impl;

import net.github.rtc.app.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH_FOLDER + fileName));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                builder.append(currentLine);
                builder.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(reader);
        }
        return builder.toString();
    }

    private void closeQuietly(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
