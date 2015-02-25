package net.github.rtc.app.service.generic;

import net.github.rtc.app.model.entity.AbstractPersistenceObject;
import net.github.rtc.app.model.entity.activity.ActivityAction;
import net.github.rtc.app.model.entity.activity.ActivityEntity;
import net.github.rtc.app.model.entity.activity.Auditable;
import net.github.rtc.app.model.entity.course.Course;
import net.github.rtc.app.model.entity.news.News;
import net.github.rtc.app.model.entity.user.User;
import net.github.rtc.app.utils.activity.events.EventCreator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class that implements commonly used CRUD operations and creates activity events
 * if operation performed successfully
 * @see net.github.rtc.app.model.event.ActivityEvent
 * @param <T>
 */
public abstract class AbstractCRUDEventsService<T  extends AbstractPersistenceObject & Auditable>  extends AbstractGenericServiceImpl<T>  {

    @Autowired
    private EventCreator creator;

    /**
     * Persist object to db and creates event about this operations
     * @param object object that needs to be persisted
     * @return persisted object
     */
    @Override
    public T create(T object) {
        final T createdObj = super.create(object);
        creator.createAndPublishEvent(this, createdObj, getActivityEntity(createdObj), ActivityAction.SAVED);
        return createdObj;
    }
    /**
     * Update object in db and creates event about this operations
     * @param object object that needs to be updated
     * @return updated object
     */
    @Override
    public T update(T object) {
        final T updatedObj = super.update(object);
        creator.createAndPublishEvent(this, updatedObj, getActivityEntity(updatedObj), ActivityAction.UPDATED);
        return updatedObj;
    }

    /**
     * Remove entity from db by it's code identifier and creates event about this operations
     * @see net.github.rtc.app.model.entity.AbstractPersistenceObject
     * @param code
     */
    @Override
    public void deleteByCode(String code) {
        final T deletedObj = findByCode(code);
        super.deleteByCode(code);
        creator.createAndPublishEvent(this, deletedObj, getActivityEntity(deletedObj), ActivityAction.REMOVED);

    }

    /**
     * Get activity entity type
     * @see net.github.rtc.app.model.entity.activity.ActivityEntity
     * @param entityObj object on what CRUD operation was performed
     * @return activity entity that corresponds to selected object
     */
    private ActivityEntity getActivityEntity(Auditable entityObj) {
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
