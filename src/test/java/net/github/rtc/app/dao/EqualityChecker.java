package net.github.rtc.app.dao;

import net.github.rtc.app.model.AbstractPersistenceObject;

public interface EqualityChecker {
    void check(AbstractPersistenceObject sample, AbstractPersistenceObject another);
}
