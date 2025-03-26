package java_sorting_app.util;
import java_sorting_app.model.Student;
import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

    @Override
    public int compare (Student obj1, Student obj2) {
        int variable1 = obj1.getGroupNumber();
        int variable2 = obj2.getGroupNumber();

        if (variable1 % 2 == 0 && variable2 % 2 == 0) {
            return Integer.compare(variable1, variable2);
        } else {
            return 0;
        }

    }
}
