package net.github.rtc.app.model.report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Yatcuba on 8/5/14.
 */
public enum ExportFormat {
    XLSX;

    public static List<String> findAll() {
        List<String> res = new ArrayList<>();
        for(ExportFormat type : ExportFormat.values()){
            res.add(type.name());
        }
        return res;
    }
}
