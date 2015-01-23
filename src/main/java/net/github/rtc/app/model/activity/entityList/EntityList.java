package net.github.rtc.app.model.activity.entityList;

import net.github.rtc.app.model.AbstractEnumTable;

import javax.persistence.*;

/**
 * Created by Alexey Samoylov on 23.01.2015.
 */
@Entity
public class EntityList extends AbstractEnumTable<EntityType> {

    public EntityList(String name) {
        super(name);
    }

    public  EntityList(EntityType name){
        super(name);
    }
}
