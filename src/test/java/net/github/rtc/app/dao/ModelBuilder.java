package net.github.rtc.app.dao;

import net.github.rtc.app.model.AbstractPersistenceObject;

public interface ModelBuilder<T extends AbstractPersistenceObject> {
   T build();
}
