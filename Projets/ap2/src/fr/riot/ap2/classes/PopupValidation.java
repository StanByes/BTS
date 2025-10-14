package fr.riot.ap2.classes;

public interface PopupValidation<T> {
    void validate(T t);
    void cancel();
}
