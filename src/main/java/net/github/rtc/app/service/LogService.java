package net.github.rtc.app.service;

import java.util.List;

public interface LogService {

    List<String> getListOfLogs();

    String readLogFile(final String fileName);
}
