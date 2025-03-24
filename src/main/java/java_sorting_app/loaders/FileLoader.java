package java_sorting_app.loaders;
import java_sorting_app.util.CustomArrayList;

import java.io.*;

public class FileLoader<T> {

    // Записываем в файл
    public void write(T object, Class<?> clazz) {
        String fileName = getFileName(clazz);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(object);
        } catch (IOException ex) {
            System.out.println("Ошибка при записи в файл: " + ex.getMessage());
        }
    }

    // Чтение из файла
    // Чтение из файла с проверкой количества записей
    public T read(Class<?> clazz, int count) {
        String fileName = getFileName(clazz); // Получаем имя файла по классу
        CustomArrayList<T> result = new CustomArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            for (int i = 0; i < count; i++) {
                try {
                    T obj = (T) ois.readObject();  // Читаем объект из файла
                    result.add(obj);
                } catch (EOFException eofEx) {
                    System.out.println("Достигнут конец файла. Прочитано всего " + result.size() + " объектов.");
                    break;  // Достигли конца файла, прерываем цикл
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println("Ошибка при чтении объекта: " + ex.getMessage());
                    continue;  // Пропускаем ошибочный объект и продолжаем чтение
                }
            }
        } catch (IOException ioEx) {
            System.out.println("Ошибка при открытии файла: " + ioEx.getMessage());
        }

        return (T) result;
    }

 /*   public T read(Class<?> clazz) {
        String fileName = getFileName(clazz); // Получаем имя файла по классу
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (T) ois.readObject();  // Читаем объект из файла
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Ошибка при чтении из файла: " + ex.getMessage());
        }
        return null;
    }*/

    // Генерируем имя файла на основе класса
    private String getFileName(Class<?> clazz) {
        return clazz.getSimpleName().toLowerCase() + ".dat";
    }
}








/*
import java.io.*;

public class FileLoader<T> implements Loader<T> {

    @Override
    public void write(T obs, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(obs);  // Запись объекта
        } catch (IOException ex) {
            System.out.println("Ошибка при записи данных в файл: " + ex.getMessage());
        }
    }

    @Override
    public T read(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (T) ois.readObject();  // Чтение объекта
        } catch (Exception ex) {
            System.out.println("Ошибка при чтении данных из файла: " + ex.getMessage());
        }
        return null;
    }

    public String getFileName(Class<T> clazz) {
        return clazz.getSimpleName().toLowerCase() + ".dat";  // Получаем имя файла по имени класса
    }
}*/
