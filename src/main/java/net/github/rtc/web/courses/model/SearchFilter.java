package net.github.rtc.web.courses.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vladislav Pikus
 */
public class SearchFilter {
    private String title;
    private String startDate;
    private Collection<String> categories;
    private Collection<String> tags;

    public SearchFilter() {
    }

    public SearchFilter(int categoriesSize) {
        int idx;
        for (idx = 0; idx < categoriesSize; idx++) {
            //categories.add(new Boolean(false));
        }
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

    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap<String, String>();
        if (!title.equals("")) {
            map.put("name", title);
        }
        if (!startDate.equals(""))  {
            map.put("date", startDate);
        }
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
        sb = new StringBuffer();
        prefix = "";
        if (tags != null && tags.size() > 0) {
            for (String tag : tags) {
                sb.append(prefix);
                prefix = ";";
                sb.append(tag);
            }
            map.put("tags", sb.toString());
        }

        return map;
    }
}
