package net.github.rtc.app.service.log;

import net.github.rtc.app.model.entity.logs.Logs;

import java.util.List;

/**
 * Service that is responsible for viewing logs on server
 */
public interface LogService {

    /**
     * Get info about all logs on server
     * @return list of objects that contain info about logs
     */
    List<Logs> findAllLogs();

    /**
     * Get text of the log file
     * @param fileName name of the log file
     * @return string that contains log data
     */
    String getLogData(final String fileName);
}
