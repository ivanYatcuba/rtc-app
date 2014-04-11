package net.github.rtc.app.utils;

import net.github.rtc.app.model.Page;
import org.springframework.stereotype.Component;

/**
 * @author Vladislav Pikus
 */
@Component
public class Paginator {
    private int maxPerPage = 10;

    public void setMaxPerPage(int maxPerPage) {
        this.maxPerPage = maxPerPage;
    }

    public int getMaxPerPage() {
        return maxPerPage;
    }

    public Page getPage(int firstResult, int total) {
        int maxResult = getMaxResult(total);
        firstResult = getFirstResult(firstResult);
        return new Page(firstResult,
                getPrevResult(firstResult),
                getNextResult(firstResult, maxResult),
                maxResult);
    }

    private int getFirstResult(int firstResult) {
        return (firstResult < 1) ? 0 : firstResult;
    }

    private int getMaxResult(int total) {
        return  total / maxPerPage + ((total % 10 == 0) ? 0 : 1);
    }

    private Integer getPrevResult(int firstResult) {
        return (firstResult < 2) ? null : firstResult - 1;
    }

    private Integer getNextResult(int firstResult, int maxResult) {
        return (firstResult < maxResult) ? firstResult + 1 : null;
    }
}
