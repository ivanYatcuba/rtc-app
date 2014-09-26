package net.github.rtc.app.service;

import java.util.List;

public interface GenericService<T> {

    void deleteByCode(String code);

    T findByCode(String code);

    T create(T course);

    void update(T course);

    List<T> findAll();
}
