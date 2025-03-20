package java_sorting_app.handlers;

import java_sorting_app.view.StudentMenu;

public class StudenHandler extends Handler {
    public StudenHandler(Handler handler) {
        super(handler, new StudentMenu());
    }

    @Override
    public Handler process(int numberMenu) {
        Handler handler = this;
        switch (numberMenu) {
            case 0:
                handler = parentHandler;
                break;
            case 1:
                //addElement();
                break;
            case 2:
                break;
            case 4:
                System.out.println(getPWD());
                break;
        }
        return handler;
    }
}
