package net.github.rtc.app.service.export;

import net.github.rtc.app.model.entity.export.ExportDetails;
import net.github.rtc.app.service.generic.GenericService;

import java.io.File;

public interface ExportService extends GenericService<ExportDetails> {
    ExportDetails compileExport(ExportDetails export);

    File getExport(ExportDetails details);

    String getCorrectlyEncodedNameFile(ExportDetails export, String agent);
}
