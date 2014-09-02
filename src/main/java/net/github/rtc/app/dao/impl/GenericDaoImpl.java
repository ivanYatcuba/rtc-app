package net.github.rtc.app.dao.impl;

import net.github.rtc.app.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Created by Ivan Yatcuba on 8/12/14.
 */
@Component
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    @Autowired
    SessionFactory sessionFactory;

    private Class<T> type;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T create(T t){
        getCurrentSession().save(t);
        return t;
    }

    @Override
    public void delete(long id){
        getCurrentSession().delete(find(id));
    }

    @Override
    public void deleteByCode(String code){
        getCurrentSession().delete(findByCode(code));
    }

    @Override
    public T find(long id){
        return (T)getCurrentSession().get(type, id);
    }

    @Override
    public T findByCode(String code){
        return (T)getCurrentSession().createCriteria(type).add(Restrictions.eq("code", code)).uniqueResult();
    }

    @Override
    public T update(T t){
        getCurrentSession().merge(t);
        return t;
    }

    @Override
    public Collection<T> findAll(){
        return getCurrentSession().createCriteria(type).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
}
