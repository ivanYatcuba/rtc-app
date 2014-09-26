package net.github.rtc.app.service.impl;

import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.service.GenericService;

import java.util.List;

public abstract class AbstractGenericServiceImpl<T> implements GenericService<T> {
    @Override
    public void deleteByCode(String code) {
        getDao().deleteByCode(code);
    }

    @Override
    public T findByCode(String code) {
        return getDao().findByCode(code);
    }

    @Override
    public T create(T t) {
        return getDao().create(t);
    }

    @Override
    public void update(T t) {
        getDao().update(t);
    }

    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }

    abstract protected GenericDao<T> getDao();
}
