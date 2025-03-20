public class CustomArrayList <T> {
    //массив для листа
    private Object[] objects;

    //размер массива
    private int size;

    //стандартная емкость
    private static int DEFAULT_CAPACITY = 10;

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

    // объект по индексу
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) objects[index];
    }

    //поиск элемента по индексу
    public int checkIndex(int index) {
          if (index < 0 || index >= objects.length)
                throw new IndexOutOfBoundsException();
            return index;

    }

    //размер списка
    public int size() {
        return size;
    }





}
