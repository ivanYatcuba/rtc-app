package net.github.rtc.app.service.generic;

import net.github.rtc.app.controller.common.ResourceNotFoundException;
import net.github.rtc.app.dao.generic.GenericDao;
import net.github.rtc.app.model.entity.AbstractPersistenceObject;
import net.github.rtc.app.model.dto.filter.AbstractSearchCommand;
import net.github.rtc.app.model.dto.SearchResults;
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
            if (!isCodeExist(code)) {
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
        if (!isCodeExist(code)) {
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

    private boolean isCodeExist(String code) { return getDao().findByCode(code) != null; }
}
