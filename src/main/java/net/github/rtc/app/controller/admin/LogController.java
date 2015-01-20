package net.github.rtc.app.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/admin/logs")
public class LogController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void listLogs() {

    }
}
