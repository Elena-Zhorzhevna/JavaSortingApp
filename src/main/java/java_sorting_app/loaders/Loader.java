package java_sorting_app.loaders;

public interface Loader <TYPE> {
    void write(TYPE obs, String fileName);
    TYPE read(String fileName);
}
