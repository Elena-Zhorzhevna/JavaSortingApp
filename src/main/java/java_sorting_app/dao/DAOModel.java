package java_sorting_app.dao;

import java_sorting_app.util.BinarySearch;
import java_sorting_app.util.CustomArrayList;


public abstract class DAOModel<T> {
    private CustomArrayList<T> elements;

    public DAOModel() {
        elements = new CustomArrayList<>();
    }

    public void add(T element) {
        elements.add(element);
    }

    public void addAll(CustomArrayList<T> elements) {
        for (int i = 0; i < elements.size(); i++) {
            this.add(elements.get(i));
        }
    }

    public CustomArrayList<T> getElements() {
        return elements;
    }

    public abstract CustomArrayList<T> loadManual();

    public int findElement(T element) {
        //return BinarySearch.search(elements, element);
        return -1;
    }

    public abstract CustomArrayList<T> loadFromFile();

    public abstract CustomArrayList<T> loadRandom();

}