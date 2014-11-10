package net.github.rtc.app.service;

public interface ModelService<T> extends GenericService<T> {
    Class<T> getType();
}
