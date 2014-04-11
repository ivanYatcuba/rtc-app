package net.github.rtc.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("adminController")
@RequestMapping("/admin")
public class AdminController {

    /**
     * Redirect to admin page and view all courses list
     *
     * @return redirect to "/admin/courses"
     */
    @RequestMapping(method = RequestMethod.GET)
    public String adminMain() {
        return "redirect:/admin/courses";
    }


}
