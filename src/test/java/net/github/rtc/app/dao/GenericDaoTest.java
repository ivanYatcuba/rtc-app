package net.github.rtc.app.dao;


import net.github.rtc.app.util.EqualityChecker;
import net.github.rtc.app.util.ModelBuilder;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(locations = { "classpath:mvc-test.xml" })
public class GenericDaoTest<T> {

    private GenericDao<T> genericDao;
    private ModelBuilder<T> modelBuilder;
    private EqualityChecker<T> equalityChecker;

    public GenericDaoTest(
      final GenericDao<T> genericDao, final ModelBuilder<T> modelBuilder, final EqualityChecker<T> equalityChecker) {
        this.genericDao = genericDao;
        this.modelBuilder = modelBuilder;
        this.equalityChecker = equalityChecker;
    }


    public void testCreate() {
        T testEntity = modelBuilder.build();
        equalityChecker.check(testEntity, genericDao.create(testEntity));
    }
}
