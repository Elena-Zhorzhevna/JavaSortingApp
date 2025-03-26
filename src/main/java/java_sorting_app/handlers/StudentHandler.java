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
        menuController.addItem(2, "Сортировать коллекцию", this::sortCollection);
        menuController.addItem(3, "Найти студента", this::findStudent);
        menuController.addItem(4, "Сохранить коллекцию", this::saveToFile);
        menuController.addItem(5, "Вывести на экран коллекцию", this::printElements);
        menuController.addItem(0, "⮌ Назад", handler::getHandler);
    }

    @Override
    protected DAOModel getDAOModel() {
        return daoModelStudent;
    }

    private Handler sortCollection() {
        getDAOModel().sortElements();
        return this;
    }

    private Handler findStudent() {
        getDAOModel().findElement();
        return this;
    }

    private Handler saveToFile() {
        //getDAOModel().();
        return this;
    }

    private Handler printElements(){
        getDAOModel().printElements();
        return this;
    }

}
