package net.github.rtc.app.model.report;


import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
