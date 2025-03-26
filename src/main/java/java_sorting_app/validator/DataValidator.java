package java_sorting_app.validator;

public class DataValidator {

    public static boolean validateBusData(String number, String model, int mileage) {
        return isValidBusNumber(number) && isValidBusModel(model) && isValidMileage(String.valueOf(mileage));
    }

    public static boolean validateStudentData(int groupNumber, double averageGrade, long studentBookNumber) {
        return isValidGroupNumber(String.valueOf(groupNumber)) && isValidAverageGrade(String.valueOf(averageGrade))
                && isValidStudentBookNumber(String.valueOf(studentBookNumber));
    }

    public static boolean validateUserData(String name, String password, String email) {
        return isValidUserName(name) && isValidPassword(password) && isValidEmail(email);
    }

    public static boolean isValidBusNumber(String number) {
        String busNumberRegex = "^[A-Za-z0-9]+$";
        return number != null && !number.isEmpty() && number.matches(busNumberRegex);
    }

    public static boolean isValidBusModel(String model) {
        return model != null && !model.isEmpty() && model.length() <= 50;
    }

    public static boolean isValidMileage(String mileageInput) {
        try {
            int mileage = Integer.parseInt(mileageInput);

            if (mileage <= 0) {
                System.err.println("Пробег должен быть больше нуля.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: введено не число. Пожалуйста, введите правильное значение для пробега.");
            return false;
        }
    }

    public static boolean isValidGroupNumber(String groupNumberString) {
        try {
            int groupNumber = Integer.parseInt(groupNumberString.trim());
            return groupNumber > 0;
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: введено не число. Пожалуйста, введите правильное значение для номера группы.");
            return false;
        }
    }

    public static boolean isValidAverageGrade(String averageGradeString) {
        try {
            double averageGrade = Double.parseDouble(averageGradeString.trim());
            return averageGrade >= 0.0 && averageGrade <= 5.0;
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: введено не число. Пожалуйста, введите правильное значение для среднего балла.");
            return false;
        }
    }

    public static boolean isValidStudentBookNumber(String studentBookNumberString) {
        try {
            long studentBookNumber = Long.parseLong(studentBookNumberString.trim());
            return studentBookNumber > 0;
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: введено не число. Пожалуйста, введите правильное значение для номера " +
                    "студенческого билета.");
            return false;
        }
    }

    public static boolean isValidUserName(String name) {
        return name != null && !name.isEmpty() && name.matches("[A-Za-zА-Яа-яЁё]+(?: [A-Za-zА-Яа-яЁё]+)*");
    }

    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password != null && !password.isEmpty() && password.matches(passwordRegex);
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && !email.isEmpty() && email.matches(emailRegex);
    }
}