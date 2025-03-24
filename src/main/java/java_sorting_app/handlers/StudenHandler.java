package java_sorting_app.handlers;

public class StudenHandler extends Handler {
    public StudenHandler(Handler handler) {
        super("Меню работы со студентами");
        menuController.addItem(1, "Добавить студента", this);
        menuController.addItem(2, "Удалить студента", this);
        menuController.addItem(3, "Найти студента", this);
        menuController.addItem(4, "Где я?", this);
        menuController.addItem(0, "Назад", handler);
    }

    @Override
    protected void handle(int numberMenu) {
        switch (numberMenu) {
            case 1:
                //addElement();
                break;
            case 2:
                break;
            case 4:
                System.out.println(getPWD());
                break;
        }
    }
}
