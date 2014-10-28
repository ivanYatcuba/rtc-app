package net.github.rtc.app.util;

public interface EqualityChecker<T> {
    void check(T sample, T another);
}
