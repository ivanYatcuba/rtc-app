package net.github.rtc.web.courses.model;

/**
 * @author Vladislav Pikus
 */
public class Page {
    private final int current;
    private final Integer prev;
    private final Integer next;
    private final int start = 1;
    private final int last;

    public Page(int current, Integer prev, Integer next, int last) {
        this.current = current;
        this.prev = prev;
        this.next = next;
        this.last = last;
    }

    public int getCurrent() {
        return current;
    }

    public Integer getPrev() {
        return prev;
    }

    public Integer getNext() {
        return next;
    }

    public int getStart() {
        return start;
    }

    public int getLast() {
        return last;
    }
}
