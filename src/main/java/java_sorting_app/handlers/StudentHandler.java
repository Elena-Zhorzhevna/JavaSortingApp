package java_sorting_app.handlers;

public class StudentHandler extends Handler {
    public StudentHandler(Handler handler) {
        super("Меню работы со студентами");
        menuController.addItem(1, "Добавить студента", new AddStudentHandler(this));
        menuController.addItem(2, "Удалить студента", this);
        menuController.addItem(3, "Найти студента", this);
        menuController.addItem(4, "Где я?", this);
        menuController.addItem(0, "⮌ Назад", handler);
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
