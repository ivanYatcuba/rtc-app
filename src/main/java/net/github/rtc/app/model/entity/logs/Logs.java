package net.github.rtc.app.model.entity.logs;

import net.github.rtc.app.model.entity.AbstractPersistenceObject;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by Илья on 21.02.2015.
 */
@Entity
public class Logs extends AbstractPersistenceObject {

    private String file;

    private String size;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Logs() {

    }

    public Logs(String file, String size, Date createdDate) {
        this.file = file;
        this.size = size;
        if (createdDate != null) {
            this.createdDate = new Date(createdDate.getTime());
        }
    }

    public void setFile(final String file) {
        this.file = file;
    }

    public void setSize(final String size) {
        this.size = size;
    }

    public void setCreatedDate(final Date createdDate) {
        if (createdDate != null) {
            this.createdDate = new Date(createdDate.getTime());
        }
    }

    public String getFile() {
        return file;
    }

    public String getSize() {
        return size;
    }

    public Date getCreatedDate() {
        return createdDate == null ? null : new Date(createdDate.getTime());
    }
}
