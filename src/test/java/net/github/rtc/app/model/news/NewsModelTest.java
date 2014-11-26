package net.github.rtc.app.model.news;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(BlockJUnit4ClassRunner.class)
public class NewsModelTest {

    @Test
    public void findAllTest(){
        List<String> res = NewsStatus.findAll();
        assertEquals(NewsStatus.values().length, res.size());
        for (final NewsStatus status : NewsStatus.values()) {
            assertTrue(res.contains(status.name()));
        }
    }

    @Test
    public void testNewsConstructor() {
        try {
            new News();
            new News("X", "Y");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
