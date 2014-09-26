package net.github.rtc.app.service.impl;

import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.model.WithCode;
import net.github.rtc.app.service.GenericService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
public abstract class AbstractGenericServiceImpl<T extends WithCode> implements GenericService<T> {
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
        t.setCode(UUID.randomUUID().toString());
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
