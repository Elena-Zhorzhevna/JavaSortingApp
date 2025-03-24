package java_sorting_app.validator;

import java_sorting_app.loaders.FileLoader;
import java_sorting_app.model.Bus;
import java_sorting_app.model.Student;
import java_sorting_app.model.User;
import java_sorting_app.util.CustomArrayList;
/*
public class Main {
    public static void main(String[] args) {
        //проверяем валидацию
        boolean isBusDataValid = DataValidator.validateBusData("gghh", "Mercedes", 55896);
        System.out.println("Bus data valid: " + isBusDataValid);

        boolean isStudentDataValid = DataValidator.validateStudentData(158, 4.9,
                359985665);
        System.out.println("Student data valid: " + isStudentDataValid);

        boolean isUserDataValid = DataValidator.validateUserData("Vika vikova",
                "password123", "example.ru");
        System.out.println("User data valid: " + isUserDataValid);*/


//проверяем добавление
public class Main {
    public static void main(String[] args) {
        // Создаем список объектов Bus
        CustomArrayList<Bus> busList = new CustomArrayList<>();
        busList.add(Bus.create().withNumber("34555").withModel("kgjjgjg").withMileage(456665).build());
        busList.add(Bus.create().withNumber("254989").withModel("kgjjgjg").withMileage(456665).build());

        // Создаем объект FileLoader для записи и чтения данных
        FileLoader<CustomArrayList<Bus>> fileLoaderBus = new FileLoader<>();

        // Записываем список автобусов в файл
        fileLoaderBus.write(busList, Bus.class);

        // Читаем данные из файла
        CustomArrayList<Bus> loadedBusList = fileLoaderBus.read(Bus.class, 100);

        // Выводим данные из файла
        if (loadedBusList != null && loadedBusList.size() > 0) {
            for (int i = 0; i < loadedBusList.size(); i++) {
                System.out.println(loadedBusList.get(i));
            }
        } else {
            System.out.println("Ошибка при чтении данных из файла.");
        }

        // Пример для студентов
        CustomArrayList<Student> studentList = new CustomArrayList<>();
        studentList.add(Student.create().withGroupNumber(123).withAverageGrade(4.5)
                .withStudentBookNumber(987654321).build());

        // Записываем список студентов в файл
        FileLoader<CustomArrayList<Student>> fileLoaderStudent = new FileLoader<>();
        fileLoaderStudent.write(studentList, Student.class);

        // Читаем данные из файла
        CustomArrayList<Student> loadedStudentList = fileLoaderStudent.read(Student.class, 15);

        // Выводим данные из файла
        if (loadedStudentList != null && loadedStudentList.size() > 0) {
            for (int i = 0; i < loadedStudentList.size(); i++) {
                System.out.println(loadedStudentList.get(i));
            }
        } else {
            System.out.println("Ошибка при чтении данных студентов из файла.");
        }

        // Пример для пользователей
        CustomArrayList<User> usersList = new CustomArrayList<>();
        usersList.add(User.create().withName("Vika").withEmail("jhdsfju@ru").withPassword("ghjgjfkfk").build());
        usersList.add(User.create().withName("Dima").withEmail("jhdsfju@ru").withPassword("ghjgjfkfk").build());
        usersList.add(User.create().withName("Myrka").withEmail("jhdsfju@ru").withPassword("ghjgjfkfk").build());

        // Записываем список пользователей в файл
        FileLoader<CustomArrayList<User>> fileLoaderUser = new FileLoader<>();
        fileLoaderUser.write(usersList, User.class);

        // Читаем данные из файла
        CustomArrayList<User> loadedUserList = fileLoaderUser.read(User.class, 20);

        // Выводим данные из файла
        if (loadedUserList != null && loadedUserList.size() > 0) {
            for (int i = 0; i < loadedUserList.size(); i++) {
                System.out.println(loadedUserList.get(i));
            }
        } else {
            System.out.println("Ошибка при чтении данных пользователей из файла.");
        }
    }
}