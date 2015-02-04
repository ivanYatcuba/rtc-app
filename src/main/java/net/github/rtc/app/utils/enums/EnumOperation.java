package net.github.rtc.app.utils.enums;

import java.util.*;

public final class EnumOperation {

    private EnumOperation() { }

    public static  <E extends Enum<E>>  List<E> findAll(Class<E>  enumClass) {
        return Arrays.asList(enumClass.getEnumConstants());
    }

    public static  <E extends Enum<E>>  List<String> findAllName(Class<E>  enumClass) {
        final List<String> res = new ArrayList<>();

        for (final  E value : enumClass.getEnumConstants()) {
            res.add(value.name());
        }
        return res;
    }

    public static  <E extends Enum<E>>  List<String> findAllValue(Class<E> enumClass) {
        final List<String> res = new ArrayList<>();

        for (final  E value : enumClass.getEnumConstants()) {
            res.add(value.toString());
        }
        return res;
    }



    public static  <E extends Enum<E>>  Map<String, String> getMapNameValue(Class<E> enumClass) {
        final Map<String, String> dictionary = new HashMap<>();
        for (E value : findAll(enumClass)) {
            dictionary.put(value.name(), value.toString());
        }
        return dictionary;
    }

    public static <E extends Enum<E>> boolean containsName(Class<E>  enumClass, String items) {
        for (E c : enumClass.getEnumConstants()) {
            if (c.name().equals(items)) {
                return true;
            }
        }
        return false;
    }

    public static <E extends Enum<E>> boolean containsValue(Class<E> enumClass, String items) {
        for (E c : enumClass.getEnumConstants()) {
            if (c.toString().equals(items)) {
                return true;
            }
        }
        return false;
    }

    public static <E extends Enum<E>> E getTypeByString(Class<E>  enumClass, String roleName) {
        for (E type :  enumClass.getEnumConstants()) {
            if (type.name().equals(roleName)) {
                return type;
            }
        }
        throw new IllegalArgumentException(enumClass.getSimpleName() + " doesn't contains " + roleName);
    }
}
