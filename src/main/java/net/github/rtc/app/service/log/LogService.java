package net.github.rtc.app.service.log;

import net.github.rtc.app.model.dto.Log;
import net.github.rtc.app.model.dto.filter.LogsSearchFilter;

import java.util.List;
import java.util.Map;

/**
 * Service that is responsible for viewing logs on server
 */
public interface LogService {

    /**
     * Get info about all logs on server
     * @return list of objects that contain info about logs
     */
    List<Log> findAllLogs();

    /**
     * Get text of the log file
     * @param fileName name of the log file
     * @return string that contains log data
     */
    String getLogData(final String fileName);

    /**
     * Get params of the logs table on logs search page
     * @param logsSearchFilter contains basic params for logs table on logs search page
     * @return maps of objects that contain params about logs table on logs search page
     */
    Map<String, Object> getLogsPageParams(LogsSearchFilter logsSearchFilter);
}
