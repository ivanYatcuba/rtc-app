package net.github.rtc.app.service.impl;

import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.model.AbstractPersistenceObject;
import net.github.rtc.app.service.CodeGenerationService;
import net.github.rtc.app.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class AbstractGenericServiceImpl<T extends AbstractPersistenceObject> implements GenericService<T> {

    @Autowired
    private CodeGenerationService codeGenerationService;

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
        t.setCode(codeGenerationService.generate());
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
