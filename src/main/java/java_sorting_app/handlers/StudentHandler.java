package java_sorting_app.handlers;
import java_sorting_app.dao.DAOModel;
import java_sorting_app.dao.DAOStudent;

public class StudentHandler extends Handler {
    private final DAOModel daoModelStudent;

    public StudentHandler(Handler handler) {
        super("Меню работы со студентами", handler);
        daoModelStudent = new DAOStudent();
        Handler addStudentHandler = new AddHandler(this);
        menuController.addItem(1, "Добавить студента", addStudentHandler::getHandler);
        menuController.addItem(2, "Удалить студента", this::deleteStudent);
        menuController.addItem(3, "Найти студента", this::searchStudent);
        menuController.addItem(0, "⮌ Назад", handler::getHandler);
    }

    @Override
    protected DAOModel getDAOModel() {
        return daoModelStudent;
    }

    private Handler deleteStudent() {
        System.out.println("Удаление студента...");
        return this;
    }

    private Handler searchStudent() {
        System.out.println("Поиск студента...");
        return this;
    }

    private Handler printStudent() {
        System.out.println("Печать списка студента...");
        return this;
    }

}
