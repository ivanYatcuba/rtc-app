package net.github.rtc.app.service.impl.genericService;

import net.github.rtc.app.model.AbstractPersistenceObject;
import net.github.rtc.app.model.activity.ActivityAction;
import net.github.rtc.app.model.activity.ActivityEntity;
import net.github.rtc.app.model.activity.IActivity;
import net.github.rtc.app.model.course.Course;
import net.github.rtc.app.model.news.News;
import net.github.rtc.app.model.user.User;
import net.github.rtc.app.utils.EventCreator;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractGenericServiceWithCheckActivityImpl<T  extends AbstractPersistenceObject & IActivity>  extends AbstractGenericServiceImpl<T>  {

    @Autowired
    private EventCreator creator;

    @Override
    public T create(T t) {
        final T createdObj = super.create(t);
        creator.createAndPublishEvent(this, createdObj, getActivityEntity(createdObj), ActivityAction.SAVED);
        return createdObj;
    }

    @Override
    public T update(T t) {
        final T updatedObj = super.update(t);
        creator.createAndPublishEvent(this, updatedObj, getActivityEntity(updatedObj), ActivityAction.UPDATED);
        return updatedObj;
    }

    @Override
    public void deleteByCode(String code) {
        final T deletedObj = findByCode(code);
        super.deleteByCode(code);
        creator.createAndPublishEvent(this, deletedObj, getActivityEntity(deletedObj), ActivityAction.REMOVED);

    }


    private ActivityEntity getActivityEntity(IActivity entityObj) {
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
