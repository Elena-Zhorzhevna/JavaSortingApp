package java_sorting_app.controllers;

@FunctionalInterface
public interface IHandler<T> {
    T process();
}
