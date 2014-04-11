package net.github.rtc.app.controller.common;

import net.github.rtc.app.model.CourseDto;
import net.github.rtc.app.model.SearchFilter;
import net.github.rtc.app.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Calendar rightNow = Calendar.getInstance();

        searchFilter.setStartDate(sdf.format(rightNow.getTime()));
        searchFilter.setStatus("PUBLISHED");

        CourseDto courseDto = coursesService.findByFilter(searchFilter.createQuery(map).byDate().byStatus().toString());
        mav.addObject("soonCourses", courseDto.getCourses());
        mav.addObject("content","content/welcomeContent");
        return mav;
    }
}
