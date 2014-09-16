package net.github.rtc.app.service;

import java.util.List;

/**
 * Created by Ivan Yatcuba on 7/30/14.
 */
public interface ModelService<T> {

    List<T> findAll();

    T create(T var);

    void update(T var);

    Class<T> getType();

}
