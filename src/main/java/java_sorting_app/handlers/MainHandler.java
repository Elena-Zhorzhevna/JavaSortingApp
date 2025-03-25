package java_sorting_app.handlers;

public class MainHandler extends Handler {

    public MainHandler(){
        super("Главное меню", null);
        menuController.addItem(1, "\uD83D\uDDC0 Автобусы", new BusHandler(this));
        menuController.addItem(2, "\uD83D\uDDC0 Студенты", new StudentHandler(this));
        menuController.addItem(3, "\uD83D\uDDC0 Пользователи", new UserHandler(this));
        menuController.addItem(0, "⮤ Выход", null);
        menuController.setAnnotation("Выберите коллекцию для работы:");
    }

    @Override
    protected void handle(int numberMenu){
        System.out.println("Вы в главном меню");
    }

    @Override
    public Handler process(int numberMenu) {
        return getItemHandler(numberMenu);
    }
}


