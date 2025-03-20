package java_sorting_app.handlers;

import java_sorting_app.view.MainMenu;

public class MainHandler extends Handler {

    public MainHandler(){
        super(null, new MainMenu());
    }

    @Override
    public Handler process(int numberMenu) {
        Handler handler = this;
        switch (numberMenu) {
            case 0:
                handler = parentHandler;
                break;
            case 1:
                handler = new BusHandler(this);
                break;
            case 2:
                handler = new StudenHandler(this);
                break;
            case 3:
                handler = new UserHandler(this);
                break;
            default:
                System.out.println("Invalid number menu");
        }
        return handler;
    }
}
