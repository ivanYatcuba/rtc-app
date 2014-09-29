package net.github.rtc.app.service;

import java.util.List;

public interface GenericService<T> {

    void deleteByCode(String code);

    T findByCode(String code);

    T create(T type);

    void update(T type);

    List<T> findAll();
}
