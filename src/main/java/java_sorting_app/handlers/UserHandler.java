package java_sorting_app.handlers;

public class UserHandler extends Handler {
    public UserHandler(Handler handler) {
        super("Меню работы с пользователями");
        menuController.addItem(1, "Добавить пользователя", this);
        menuController.addItem(2, "Удалить пользователя", this);
        menuController.addItem(3, "Найти пользователя", this);
        menuController.addItem(4, "Где я?", this);
        menuController.addItem(0, "Назад", handler);
    }

    @Override
    protected void handle(int numberMenu) {
        //pass
    }
}
