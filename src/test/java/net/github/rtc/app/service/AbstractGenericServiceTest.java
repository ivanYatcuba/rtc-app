/*package net.github.rtc.app.service;
//todo
import net.github.rtc.app.dao.GenericDao;
import net.github.rtc.app.model.AbstractPersistenceObject;
import net.github.rtc.app.utils.datatable.search.AbstractSearchCommand;
import net.github.rtc.app.utils.datatable.search.SearchResults;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ContextConfiguration(locations = { "classpath:mvc-dao-test.xml" })
public abstract class AbstractGenericServiceTest {

    public static final String CODE = "X";
    @Mock
    private CodeGenerationService codeGenerationService;

    private GenericDao<AbstractPersistenceObject> genericDao;
    private GenericService<AbstractPersistenceObject> genericService;

    private AbstractPersistenceObject testEntity;

    protected abstract GenericService<AbstractPersistenceObject> getGenericService();
    protected abstract GenericDao<AbstractPersistenceObject> getGenericDao();
    protected abstract AbstractPersistenceObject getTestEntity();

    protected abstract AbstractSearchCommand getSearchCommand();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        when(codeGenerationService.generate()).thenReturn(CODE);
        genericDao = getGenericDao();
        testEntity = getTestEntity();
        genericService = getGenericService();
    }

    @After
    public void tearDown(){
        testEntity = null;
        genericDao = null;
        genericDao = getGenericDao();
        genericService = null;
    }


    @Test
    public void deleteByCode() {
       doAnswer(new Answer() {
            @Override
            public Object answer(final InvocationOnMock invocation) throws Throwable {
                testEntity = null;
                return null;
            }
        }).when(genericDao).deleteByCode(CODE);
        genericService.deleteByCode(CODE);
        assertEquals(testEntity, null);
        testEntity = getTestEntity();
    }

    @Test
    public void testFindByCode() {
        when(genericDao.findByCode(CODE)).thenReturn(testEntity);
        assertEquals(testEntity, genericService.findByCode(CODE));
    }

    @Test
    public void testCreate() {
        when(genericDao.create(testEntity)).thenReturn(testEntity);
        assertEquals(testEntity, genericService.create(testEntity));
    }

    @Test
    public void testUpdate() {
        when(genericDao.update(testEntity)).thenReturn(testEntity);
        assertEquals(testEntity, genericService.update(testEntity));
    }

    @Test
    public void testFindAll() {
        when(genericDao.findAll()).thenReturn(Arrays.asList(testEntity));
        assertEquals(Arrays.asList(testEntity), genericService.findAll());
    }

    @Test
    public void testSearch() {
        final SearchResults<AbstractPersistenceObject> results = new SearchResults();
        results.setResults(Arrays.asList(testEntity));
        AbstractSearchCommand command =  getSearchCommand();
        when(genericDao.search(command)).thenReturn(results);
        assertEquals(results, genericService.search(command));
    }
}*/
