package net.github.rtc.app.model;

import java.util.HashMap;
import java.util.Map;

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

    public class MapBuilder {
        private Map<String, Object> map = new HashMap<String, Object>();

        public MapBuilder byCurrentPage() {
            map.put("currentPage", current);
            return this;
        }

        public MapBuilder byPrevPage() {
            map.put("prevPage", prev);
            return this;
        }

        public MapBuilder byNextPage() {
            map.put("nextPage", next);
            return this;
        }

        public MapBuilder byStartPage() {
            map.put("startPage", start);
            return this;
        }

        public MapBuilder byLastPage() {
            map.put("lastPage", last);
            return this;
        }

        public Map<String, Object> toMap() {
            return map;
        }
    }

    public MapBuilder createMap() {
        return new MapBuilder();
    }
}
