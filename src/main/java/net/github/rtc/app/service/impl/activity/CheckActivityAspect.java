package net.github.rtc.app.service.impl.activity;

import net.github.rtc.app.model.activity.ActivityAction;
import net.github.rtc.app.model.activity.ActivityEntity;
import net.github.rtc.app.model.activity.IActivity;
import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.news.News;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.utils.EventCreator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import  net.github.rtc.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;

@Aspect
public class CheckActivityAspect {
    @Autowired
    private EventCreator creator;

    @AfterReturning(pointcut =
            "execution(* net.github.rtc.app.service.course.CourseService.create(..))"
                    + "|| execution(* net.github.rtc.app.service.user.UserService.create(..))"
                    + "|| execution(* net.github.rtc.app.service.news.NewsService.create(..))",
            returning = "entityResult")
    public void afterCreate(JoinPoint joinPoint, Object entityResult) {
        creator.createAndPublishEvent(this, (IActivity) entityResult, getActivityEntity(entityResult), ActivityAction.SAVED);
    }

    @AfterReturning(pointcut =
            "execution(* net.github.rtc.app.service.course.CourseService.update(..))"
                    + "|| execution(* net.github.rtc.app.service.user.UserService.update(..) )"
                    + "|| execution(* net.github.rtc.app.service.news.NewsService.update(..) )",
            returning = "entityResult")
    public void afterUpdate(JoinPoint joinPoint, Object entityResult) {
        creator.createAndPublishEvent(this, (IActivity) entityResult, getActivityEntity(entityResult), ActivityAction.UPDATED);
    }


    @Around("execution(* net.github.rtc.app.service.course.CourseService.deleteByCode(..))"
                    + "|| execution(* net.github.rtc.app.service.user.UserService.deleteByCode(..) )"
                    + "|| execution(* net.github.rtc.app.service.news.NewsService.deleteByCode(..) )")
    public void beforeDelete(ProceedingJoinPoint joinPoint) {
        final Object code = joinPoint.getArgs()[0];
        final Object entityObj = ((GenericService) joinPoint.getThis()).findByCode((String) code);
        try {
        joinPoint.proceed();
        } catch (Throwable  throwable) {
            throw new DataAccessResourceFailureException("Cannot delete this object ("
                    + entityObj.getClass() + "): "
                    + throwable.getMessage());
        }
        creator.createAndPublishEvent(this, (IActivity) entityObj, getActivityEntity(entityObj), ActivityAction.REMOVED);
    }

    private ActivityEntity getActivityEntity(Object entityObj) {
        if (entityObj instanceof User) {
            return ActivityEntity.USER;
        }
        if (entityObj instanceof Course) {
            return ActivityEntity.COURSE;
        }
        if (entityObj instanceof News) {
            return ActivityEntity.NEWS;
        }
        throw new IllegalArgumentException();
    }

}
