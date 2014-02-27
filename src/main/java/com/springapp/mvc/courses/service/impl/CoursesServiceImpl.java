package com.springapp.mvc.courses.service.impl;

import com.springapp.mvc.courses.dao.CoursesDao;
import com.springapp.mvc.courses.dao.impl.CoursesDaoImpl;
import com.springapp.mvc.courses.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is service
 *
 * @author Vladislav Pikus
 */
//Todo May be, need make as component
@Service("coursesService")
public class CoursesServiceImpl implements CoursesService {

    @Autowired
    public void setDao(CoursesDao dao) {
        this.dao = dao;
    }

    private CoursesDao dao;
}
