package net.github.rtc.app.model;


import javax.persistence.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumSet;


/**
 * Created by Alexey Samoylov on 23.01.2015.
 */

/*
Template (id,value)
* */
@MappedSuperclass
public class AbstractEnumTable<T extends java.lang.Enum> extends AbstractPersistenceIDObject{
    @Enumerated(EnumType.STRING)
    @Column
    private T name;

    private Class<T> initType() {
        final Type t = getClass().getGenericSuperclass();
        final ParameterizedType pt = (ParameterizedType) t;
        return (Class) pt.getActualTypeArguments()[0];
    }


    public AbstractEnumTable(final String strName) {
        Class<T> type=initType();
        for (Object role : EnumSet.allOf(type)) {
            if (role.toString().equals(strName)){
                this.name = (T)role;
                break;
            }
        }
        if(this.name==null) throw new IllegalArgumentException("Enum do not consist this string.");
    }

    public AbstractEnumTable(final T name) {
        this.name = name;
    }

    public T getName() {
        return name;
    }

    public void setName(final T name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name.toString();
    }
}



