package net.github.rtc.app.service.export;

import net.github.rtc.app.dao.export.ExportDao;
import net.github.rtc.app.dao.generic.GenericDao;
import net.github.rtc.app.model.entity.export.ExportDetails;
import net.github.rtc.app.service.builder.ExportBuilder;
import net.github.rtc.app.service.generic.CodeGenerationService;
import net.github.rtc.app.service.generic.ModelService;
import net.github.rtc.app.service.date.DateService;
import net.github.rtc.app.service.generic.AbstractGenericServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public class ExportServiceImpl extends AbstractGenericServiceImpl<ExportDetails> implements ExportService {

    private static final String REPORT = "Report: ";
    private static final String DOT = ".";
    private static Logger log = LoggerFactory.getLogger(ExportServiceImpl.class.getName());
    @Autowired
    private ExportDao exportDao;
    @Autowired
    private DateService dateService;
    @Autowired
    private CodeGenerationService codeGenerationService;
    @Resource(name = "serviceHolder")
    private Map<Class, ? extends ModelService> serviceHolder;
    @Value("${export.path}")
    private String exportPath;

    @Override
    protected GenericDao<ExportDetails> getDao() {
        return exportDao;
    }

    @Override
    @Transactional
    public ExportDetails create(final ExportDetails export) {
        log.info("Creating export: " + export);
        export.setCode(codeGenerationService.generate());
        export.setCreatedDate(dateService.getCurrentDate());
        try {
            compileExport(export);
            final ExportDetails result = exportDao.create(export);
            log.info(REPORT + export.getCode() + " created successfully!");
            return result;
        } catch (final Exception e) {
            log.info("Report creation failed: " + export.getCode());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public ExportDetails update(final ExportDetails export) {
        log.info("Updating export: " + export);
        try {
            compileExport(export);
            exportDao.update(export);
            log.info(REPORT + export.getCode() + " updated successfully!");
            return export;
        } catch (final Exception e) {
            log.info("Report update failed: " + export.getCode());
            e.printStackTrace();
            return null;
        }
    }

    public ExportDetails compileExport(ExportDetails export) {
        final String filePath = exportPath + export.getCode() + DOT + export.getExportFormat().toString().toLowerCase();
        final ModelService service = serviceHolder.get(export.getExportClass().getValue());
        final List<?> objects = service.findAll();
        try {
            ExportBuilder.build(export, objects, filePath);
            return export;
        } catch (final NoSuchFieldException e) {
            log.info("Report building failed: " + export.getCode());
            return null;
        }
    }

    @Override
    public File getExport(ExportDetails details) {
        final ExportDetails exportDetails = findByCode(details.getCode());
        final String filePath = new StringBuilder(exportPath).append("/").append(details.getCode()).append(DOT).
                append(exportDetails.getExportFormat().toString().toLowerCase()).toString();
        return new File(filePath);
    }
}
