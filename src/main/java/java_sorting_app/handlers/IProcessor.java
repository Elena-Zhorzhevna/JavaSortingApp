package java_sorting_app.handlers;

@FunctionalInterface
public interface IProcessor<T> {
    T process();
}
