package java_sorting_app.handlers;

import java_sorting_app.dao.DAOBus;
import java_sorting_app.dao.DAOModel;
import java_sorting_app.dao.DAOStudent;
import java_sorting_app.model.Student;

public class StudentHandler extends Handler<Student> {
    private final DAOModel<Student> daoModelStudent;

    public StudentHandler(Handler<Student> handler) {
        super("Меню работы со студентами", handler);
        daoModelStudent = new DAOStudent();
        menuController.addItem(1, "Добавить студента", new AddHandler<Student>(this));
        menuController.addItem(2, "Удалить студента", this);
        menuController.addItem(3, "Найти студента", this);
        menuController.addItem(4, "Где я?", this);
        menuController.addItem(0, "⮌ Назад", handler);
    }

    @Override
    protected DAOModel<Student> getDAOModel() {
        return daoModelStudent;
    }

    @Override
    protected void handle(int numberMenu) {
        switch (numberMenu) {
            case 1:
                break;
            case 2:
                break;
            case 4:
                System.out.println(getPWD());
                break;
        }
    }

}
/*

    private void deleteStudent() {
        // Логика
        System.out.println("Удаление студента...");
    }

    private void listStudents() {
      //логика
        System.out.println("Список студентов...");
    }

    private void searchStudent() {
        // Логика
        System.out.println("Поиск студента...");
    }
}
*/
