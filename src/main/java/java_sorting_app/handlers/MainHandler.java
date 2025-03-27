package java_sorting_app.handlers;

public class MainHandler extends Handler {

    public MainHandler(){
        super("Главное меню", null);

        Handler busHandler = new BusHandler(this);
        Handler studentHandler = new StudentHandler(this);
        Handler userHandler = new UserHandler(this);

        menuController.addItem(1, "\uD83D\uDDC0 Автобусы", busHandler::getHandler);
        menuController.addItem(2, "\uD83D\uDDC0 Студенты", studentHandler::getHandler);
        menuController.addItem(3, "\uD83D\uDDC0 Пользователи", userHandler::getHandler);
        menuController.addItem(0, "⮤ Выход", this::exit);
        menuController.setAnnotation("Выберите коллекцию для работы:");
    }

    public Handler exit(){
        System.exit(0);
        return parentHandler;
    }

}


