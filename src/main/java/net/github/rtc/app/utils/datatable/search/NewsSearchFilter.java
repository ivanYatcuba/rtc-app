package net.github.rtc.app.utils.datatable.search;

import net.github.rtc.app.model.news.News;
import net.github.rtc.app.model.news.NewsStatus;
import net.github.rtc.app.model.user.User;
import org.hibernate.criterion.DetachedCriteria;


public class NewsSearchFilter extends AbstractSearchCommand {

    private String title;

    private User author;

    private NewsStatus status;

    @Override
    public DetachedCriteria getCriteria() {
        return DetachedCriteria.forClass(News.class);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public NewsStatus getStatus() {
        return status;
    }

    public void setStatus(NewsStatus status) {
        this.status = status;
    }
}
