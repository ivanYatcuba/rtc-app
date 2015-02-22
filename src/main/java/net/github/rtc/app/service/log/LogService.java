package net.github.rtc.app.service.log;

import net.github.rtc.app.model.entity.logs.Logs;

import java.util.List;

public interface LogService {

    List<Logs> findAllLogFileNames();

    String getLogData(final String fileName);
}
