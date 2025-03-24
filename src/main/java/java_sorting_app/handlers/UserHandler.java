package java_sorting_app.handlers;

public class UserHandler extends Handler {
    public UserHandler(Handler handler) {
        super("Меню работы с пользователями");
        menuController.addItem(1, "Добавить пользователя", new AddUserHandler(this));
        menuController.addItem(2, "Удалить пользователя", this);
        menuController.addItem(3, "Найти пользователя", this);
        menuController.addItem(4, "Где я?", this);
        menuController.addItem(0, "⮌ Назад", handler);
    }

    @Override
    public void handle(int numberMenu) {
        switch (numberMenu) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                System.out.println(getPWD());
                break;
        }
    }


}


/*

    private void deleteUser() {
        // Логика
        System.out.println("Удаление пользователя...");
    }

    private void listUsers() {
        // Логика
        System.out.println("Список пользователей...");
    }

    private void searchUser() {
        // Логика
        System.out.println("Поиск пользователя...");
    }*/
