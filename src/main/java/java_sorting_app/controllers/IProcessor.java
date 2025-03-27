package java_sorting_app.controllers;

@FunctionalInterface
public interface IProcessor<T> {
    T process();
}
