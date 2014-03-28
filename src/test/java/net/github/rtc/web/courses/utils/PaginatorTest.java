package net.github.rtc.web.courses.utils;

import net.github.rtc.web.courses.model.Page;
import org.junit.Test;

/**
 * @author Vladislav Pikus
 */
public class PaginatorTest {

    @Test
    public void test1() throws Exception {
        Page page = new Paginator().getPage(1, 11);
        int i = 10;
    }
}
