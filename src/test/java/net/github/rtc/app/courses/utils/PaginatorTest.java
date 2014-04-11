package net.github.rtc.app.courses.utils;

import net.github.rtc.app.model.Page;
import net.github.rtc.app.utils.Paginator;
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
