package net.github.rtc.app.service.impl.genericService;

import net.github.rtc.app.controller.common.ResourceNotFoundException;
import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.model.AbstractPersistenceObject;
import net.github.rtc.app.service.CodeGenerationService;
import net.github.rtc.app.service.GenericService;
import net.github.rtc.app.utils.datatable.search.AbstractSearchCommand;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class AbstractGenericServiceImpl<T extends AbstractPersistenceObject> implements GenericService<T> {

    @Autowired
    private CodeGenerationService codeGenerationService;

    abstract protected GenericDao<T> getDao();

    protected String getCode() {
        String code = codeGenerationService.generate();
        while (true) {
            if (isExistCode(code)) {
                return code;
            }
            code = codeGenerationService.generate();
        }
    }

    @Override
    public void deleteByCode(String code) {
        getDao().deleteByCode(code);
    }

    @Override
    public T findByCode(String code) {
        final T obj = getDao().findByCode(code);
        if (obj == null) {
            throw new ResourceNotFoundException();
        }
        return obj;
    }

    @Override
    public T create(T t) {
        t.setCode(getCode());
        return getDao().create(t);
    }

    @Override
    public T update(T t) {
        return getDao().update(t);
    }

    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }

    @Override
    public SearchResults<T> search(AbstractSearchCommand searchCommand) {

        return getDao().search(searchCommand);
    }

    private boolean isExistCode(String code) { return getDao().findByCode(code) == null; }
}
