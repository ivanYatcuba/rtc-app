package net.github.rtc.app.controller.admin;
import net.github.rtc.app.service.LogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/logs")
public class LogController {

    private static final String STRING_LOGS = "logs";
    private static final String STRING_LOG_CONTENTS = "logContents";
    private static final String ROOT = "portal/admin";
    private static final String LOGS_LIST_PAGE = "/logs/logsList";
    private static final String LOG_CONTENTS_PAGE = "/logs/logContents";

    @Autowired
    private LogService logService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getLogs() {
        final ModelAndView modelAndView = new ModelAndView(ROOT + LOGS_LIST_PAGE);
        modelAndView.addObject(STRING_LOGS, logService.getListOfLogs());
        return modelAndView;
    }

    @RequestMapping(value = "/{fileName:.+}", method = RequestMethod.GET)
    public ModelAndView getLogsContents(@PathVariable String fileName) {
        final ModelAndView modelAndView = new ModelAndView(ROOT + LOG_CONTENTS_PAGE);
        modelAndView.addObject(STRING_LOG_CONTENTS, logService.readLogFile(fileName));
        return modelAndView;
    }
}
