package java_sorting_app.handlers;

public class MainHandler extends Handler {

    public MainHandler(){
        super("Главное меню");
        menuController.addItem(1, "Работа с автобусами", new BusHandler(this));
        menuController.addItem(2, "Работа со студентами", new StudenHandler(this));
        menuController.addItem(3, "Работа с пользователями", new UserHandler(this));
        menuController.addItem(0, "Выход", null);
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
