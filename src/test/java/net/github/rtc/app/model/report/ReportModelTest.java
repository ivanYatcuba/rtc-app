package net.github.rtc.app.model.report;


import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4ClassRunner.class)
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
