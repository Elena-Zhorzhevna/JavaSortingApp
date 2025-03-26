package java_sorting_app.util;
import java_sorting_app.model.Bus;

import java.util.Comparator;

public class BusComparator implements Comparator <Bus>{

    @Override
    public int compare (Bus obj1, Bus obj2) {
    int variable1 = obj1.getMileage();
    int variable2 = obj2.getMileage();

        if (variable1 % 2 == 0 && variable2 % 2 == 0) {
            return Integer.compare(variable1, variable2);
        } else {
            return 0;
        }

    }

}


