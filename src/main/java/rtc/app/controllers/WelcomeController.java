package rtc.app.controllers;

import net.github.rtc.web.courses.model.CoursesDTO;
import net.github.rtc.web.courses.model.SearchFilter;
import net.github.rtc.web.courses.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ivan on 28.03.14.
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

    @Autowired
    private CoursesService coursesService;

    /**
     * Request to main page, get three coming soon courses
     * @return ModelAndView("welcome/welcomeLayout")
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome() {
        ModelAndView mav = new ModelAndView("welcome/welcomeLayout");

        Map<String, String> map = new HashMap<String, String>();
        map.put("maxResult", "3");

        SearchFilter searchFilter = new SearchFilter();
        Date date = new Date();
        String formattedDate = new SimpleDateFormat("dd.MM.yyyy").format(date);
        searchFilter.setStartDate(formattedDate);

        CoursesDTO coursesDTO = coursesService.findByFilter(searchFilter.createQuery(map).byDate().toString());
        mav.addObject("soonCourses", coursesDTO.getCourses());
        mav.addObject("content","content/welcomeContent");
        return mav;
    }
}
