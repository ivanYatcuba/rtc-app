package net.github.rtc.web.courses.utils;

import java.util.Iterator;
import java.util.Map;

/**
 * @author Vladislav Pikus
 */
public class QueryParametersBuilder {

    private StringBuffer query = new StringBuffer();

    private void createQuery(Map<String, String> map) {
        Iterator iterator = map.entrySet().iterator();
        String prefix = (query.length() > 0) ? "&" : "";
        while (iterator.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) iterator.next();
            query.append(prefix).append(mapEntry.getKey()).append("=").append(mapEntry.getValue());
            prefix = "&";
        }
    }

    public static QueryParametersBuilder fromMap(Map<String, String> map) {
        QueryParametersBuilder qpb = new QueryParametersBuilder();
        qpb.createQuery(map);
        return qpb;
    }

    public void addQueryParam(Map<String, String> map) {
        createQuery(map);
    }

    public String build() {
        return query.toString();
    }
}
