package net.github.rtc.app.service.generic;

public interface ModelService<T> extends GenericService<T> {
    Class<T> getType();
}
