package fr.riot.ap.utils;

import javafx.util.StringConverter;

public class ChoosableEnumFormatter<T extends ChoosableEnum> extends StringConverter<T> {
    private final Class<T> clazz;

    public ChoosableEnumFormatter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString(T object) {
        return object == null ? "" : object.getDisplayValue();
    }

    @Override
    public T fromString(String string) {
        for (T object : clazz.getEnumConstants()) {
            if (object.getDisplayValue().equals(string))
                return object;
        }

        return null;
    }
}
