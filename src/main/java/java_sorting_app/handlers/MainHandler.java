package java_sorting_app.handlers;

import java_sorting_app.view.MainMenu;

public class MainHandler extends Handler {

    public MainHandler(){
        super(null, new MainMenu());
        menuMap.put(1, new BusHandler(this));
        menuMap.put(2, new StudenHandler(this));
        menuMap.put(3, new UserHandler(this));
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
