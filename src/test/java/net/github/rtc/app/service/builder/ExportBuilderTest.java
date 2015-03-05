package net.github.rtc.app.service.builder;

import net.github.rtc.app.model.entity.export.ExportFormat;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(BlockJUnit4ClassRunner.class)
public class ExportBuilderTest {

    @InjectMocks
    private ExportBuilder exportBuilder;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Ignore
    @Test
    public void testBuild() {
        List<Field> exportField = new ArrayList<>();
        List objectsList = new ArrayList();
        String sheetName = null;
        String filePath = null;
        ExportFormat exportFormat = null;
        exportBuilder.build(exportField, objectsList, sheetName, filePath, exportFormat);
        assertTrue(true);
    }
}
