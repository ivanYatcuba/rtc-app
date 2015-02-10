package net.github.rtc.app.service.log;

import java.util.List;

public interface LogService {

    List<String> getListOfLogs();

    String readLogFile(final String fileName);
}
