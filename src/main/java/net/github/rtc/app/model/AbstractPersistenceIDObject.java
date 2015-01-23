package net.github.rtc.app.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractPersistenceIDObject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

}
