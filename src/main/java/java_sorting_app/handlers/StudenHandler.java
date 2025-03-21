package java_sorting_app.handlers;

import java_sorting_app.view.StudentMenu;

public class StudenHandler extends Handler {
    public StudenHandler(Handler handler) {
        super(handler, new StudentMenu());
        menuMap.put(1, this);
        menuMap.put(2, this);
        menuMap.put(3, this);
        menuMap.put(4, this);
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
