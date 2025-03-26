package java_sorting_app.util;
import java_sorting_app.model.User;
import java.util.Comparator;

public class UserComparator implements Comparator<User> {

    @Override
    public int compare (User obj1, User obj2) {
        int variable1 = obj1.getName().length();
        int variable2 = obj2.getName().length();

        if (variable1 % 2 == 0 && variable2 % 2 == 0) {
            return Integer.compare(variable1, variable2);
        } else {
            return 0;
        }

    }
}