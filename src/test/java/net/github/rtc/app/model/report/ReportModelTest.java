package net.github.rtc.app.model.report;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(BlockJUnit4ClassRunner.class)
public class ReportModelTest {

    @Test
    public void findAllTest(){
        List<String> res = ExportFormat.findAll();
        assertEquals(ExportFormat.values().length, res.size());
        for (final ExportFormat format : ExportFormat.values()) {
            assertTrue(res.contains(format.name()));
        }
    }
}
