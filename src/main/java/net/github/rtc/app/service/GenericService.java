package net.github.rtc.app.service;

import net.github.rtc.app.model.WithCode;

import java.util.List;

public interface GenericService< T extends WithCode> {

    void deleteByCode(String code);

    T findByCode(String code);

    T create(T type);

    void update(T type);

    List<T> findAll();
}
