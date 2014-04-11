package net.github.rtc.app.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Vladislav Pikus
 */
public class SearchFilter {
    private String title;
    private String startDate;
    private Collection<String> categories;
    private Collection<String> tags;
    private String status;

    public SearchFilter() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Collection<String> getCategories() {
        return categories;
    }

    public void setCategories(Collection<String> categories) {
        this.categories = categories;
    }

    public Collection<String> getTags() {
        return tags;
    }

    public void setTags(Collection<String> tags) {
        this.tags = tags;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public class QueryBuilder {
        private Map<String, String> map;

        public QueryBuilder() {
            map = new HashMap<String, String>();
        }

        public QueryBuilder(Map<String, String> map) {
            this.map = map;
        }

        public QueryBuilder byTitle() {
            if (!title.equals("")) {
                map.put("name", title);
            }
            return this;
        }

        public QueryBuilder byDate() {
            if (!startDate.equals(""))  {
                map.put("date", startDate.replace('.', '-'));
            }
            return this;
        }

        public QueryBuilder byCategories() {
            StringBuffer sb = new StringBuffer();
            String prefix = "";
            if (categories != null && categories.size() > 0) {
                for (String cat : categories) {
                    sb.append(prefix);
                    prefix = ";";
                    sb.append(cat);
                }
                map.put("categories", sb.toString());
            }
            return this;
        }

        public QueryBuilder byTags() {
            StringBuffer sb = new StringBuffer();
            String prefix = "";
            if (tags != null && tags.size() > 0) {
                for (String tag : tags) {
                    sb.append(prefix);
                    prefix = ";";
                    sb.append(tag);
                }
                map.put("tags", sb.toString());
            }
            return this;
        }

        public QueryBuilder byStatus() {
            if (!status.equals("")) {
                map.put("status", status);
            }
            return this;
        }

        public Map<String, String> toMap() {
            return map;
        }

        public String toString() {
            StringBuffer query = new StringBuffer();
            Iterator iterator = map.entrySet().iterator();
            String prefix = (query.length() > 0) ? "&" : "";
            while (iterator.hasNext()) {
                Map.Entry mapEntry = (Map.Entry) iterator.next();
                query.append(prefix).append(mapEntry.getKey()).append("=").append(mapEntry.getValue());
                prefix = "&";
            }

            return query.toString();
        }
    }

    public QueryBuilder createQuery(Map<String, String> map) {
        return new QueryBuilder(map);
    }

    public QueryBuilder createQuery() {
        return new QueryBuilder();
    }
}
