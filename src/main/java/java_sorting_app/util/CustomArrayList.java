package java_sorting_app.util;

import java.io.Serializable;

public class CustomArrayList<T> implements Serializable {
    //массив для листа
    private Object[] objects;

    //размер массива
    private int size;

    //размер списка
    public int size() {
        return size;
    }

    //стандартная емкость
    private static final int DEFAULT_CAPACITY = 10;

    //конструктор по умолчанию
    public CustomArrayList() {
        objects = new Object[DEFAULT_CAPACITY];
    }

    //добавляю объект в массив
    public void add(T object) {
        if (size == objects.length) {
            expandCapacity();
        }
        objects[size++] = object;
    }

    //расширяю массив если достиг предела в 1,5 раза
    private void expandCapacity() {
        int oldCapacity = objects.length;
        int newCapacity;

        if (oldCapacity == 0) {
            // Если массив был пустым
            newCapacity = DEFAULT_CAPACITY;
        } else {
            // Увеличивает лист в 1.5 раза
            newCapacity = oldCapacity + (oldCapacity / 2) + 1;
        }

        Object[] newArray = new Object[newCapacity];
        System.arraycopy(objects, 0, newArray, 0, size);
        objects = newArray;
    }

    //проврека индекса, входит ли он в диапазон
    private void checkIndex(int index) {
        if (index < 0 || index >= objects.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // поиск объекта по индексу
    public T get(int index) {
        checkIndex(index);
        return (T) objects[index];
    }

    public T set(int index, T element) {
        // Проверка корректности индекса
        checkIndex(index);
        // Получаем старый элемент
        T oldValue = (T) objects[index];
        // Заменяем элемент в массиве
        objects[index] = element;

        return oldValue;
    }
}