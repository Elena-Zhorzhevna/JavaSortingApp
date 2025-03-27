package java_sorting_app.dao;

import java_sorting_app.model.Student;
import java_sorting_app.util.CustomArrayList;

import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class DAOStudent implements DAOModel{

    CustomArrayList<Student> students;

    public DAOStudent() {
        students = new CustomArrayList<>();
    }

    @Override
    public void printElements(){
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println(student);
        }
    }

    public void add(Student element) {
        students.add(element);
    }

    public void addAll(CustomArrayList<Student> elements) {
        for (int i = 0; i < elements.size(); i++) {
            students.add(elements.get(i));
        }
    }

    public CustomArrayList<Student> getElements() {
        return students;
    }

    @Override
    public void sortElements() {
        CustomArrayList.selectionSort(students, Student::compareTo);
    }

    @Override
    public void magicSortElements(){
        CustomArrayList.selectionSort(students, Student::magicCompare);
    }

    @Override
    public void saveToFile(){

    }

    @Override
    public void findElement() {

    }

    @Override
    public void loadManual() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите студента в формате: номер группы ; средний бал ; номер читательского билета");
            System.out.println("Или введите 'exit' для завершения");
            System.out.print("? > ");

            String inputLine = scanner.nextLine();
            if (inputLine.equalsIgnoreCase("exit")) {
                break;
            }

            Optional<Student> studentOptional = Student.fromCSVString(inputLine);
            studentOptional.ifPresent(students::add);
            studentOptional.ifPresentOrElse(
                    student -> System.out.println("Вы добавили студента: " + student),
                    () -> System.out.println("Некорректные данные"));
        }
    }

    @Override
    public void loadFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество студентов для загрузки из файла:");
        int numberToLoad = scanner.nextInt();

        Optional<String[]> resultOptional = getRowsFromFile("students.csv", numberToLoad);
        if (resultOptional.isPresent()) {
            String[] rows = resultOptional.get();
            for (String stringObjectCSV : rows) {
                if (stringObjectCSV != null && !stringObjectCSV.trim().isEmpty()) {
                    Optional<Student> studentOptional = Student.fromCSVString(stringObjectCSV);
                    //busOptional.ifPresent(students::add);
                    studentOptional.ifPresent(student -> {
                        students.add(student);
                        System.out.println("Загружен студент: " + student.toString());
                    });
                }
            }
            System.out.println("Всего в файле студентов: " + students.size());
        } else {
            System.out.println("Не удалось загрузить данные из файла");
        }
    }

    @Override
    public void loadRandom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество студентов для генерации:");
        int numberToGenerate = scanner.nextInt();

        Random random = new Random();

        for (int i = 0; i < numberToGenerate; i++) {
            int groupNumber = random.nextInt(10) + 1;
            double averageGrade = random.nextDouble() * 5.0;
            long studentBookNumber = Math.abs(random.nextLong()) + 1L;

            Student student = Student.create()
                    .withGroupNumber(groupNumber)
                    .withAverageGrade(averageGrade)
                    .withStudentBookNumber(studentBookNumber)
                    .build();

            students.add(student);

            System.out.println("Сгенерирован студент: " + student.toString());
        }
        System.out.println("Всего сгенерировано " + students.size() + " студентов.");
    }
}