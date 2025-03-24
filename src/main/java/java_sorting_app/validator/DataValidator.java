package java_sorting_app.validator;

public class DataValidator {

    // Метод для валидации данных автобуса
    public static boolean validateBusData(String number, String model, int mileage) {
        return isValidBusNumber(number) && isValidBusModel(model) && isValidMileage(String.valueOf(mileage));
    }

    // Метод для валидации данных студента
    public static boolean validateStudentData(int groupNumber, double averageGrade, long studentBookNumber) {
        return isValidGroupNumber(groupNumber) && isValidAverageGrade(averageGrade)
                && isValidStudentBookNumber(studentBookNumber);
    }

    // Метод для валидации данных пользователя
    public static boolean validateUserData(String name, String password, String email) {
        return isValidUserName(name) && isValidPassword(password) && isValidEmail(email);
    }

    // Валидация номера автобуса
    public static boolean isValidBusNumber(String number) {
        String busNumberRegex = "^[A-Za-z0-9]+$";
        return number != null && !number.isEmpty() && number.matches(busNumberRegex);
    }

    // Валидация модели автобуса
    public static boolean isValidBusModel(String model) {
        return model != null && !model.isEmpty();
    }

    // Валидация пробега автобуса
    public static boolean isValidMileage(String mileageInput) throws IllegalArgumentException {
        int mileage;

        try {
            // Попытка преобразовать строку в число
            mileage = Integer.parseInt(mileageInput);
        } catch (Exception e) {

            throw new IllegalArgumentException("Ошибка: введено не число. Пожалуйста, " +
                    "введите правильное значение для пробега.");
        }

        // Проверяем, что пробег больше нуля
        if (mileage <= 0) {
            throw new IllegalArgumentException("Пробег должен быть больше нуля.");
        }

        return true;
    }

    // Валидация группового номера студента
    public static boolean isValidGroupNumber(int groupNumber) {
        return groupNumber > 0;
    }

    // Валидация среднего балла студента
    public static boolean isValidAverageGrade(double averageGrade) {
        return averageGrade >= 0.0 && averageGrade <= 5.0;
    }

    // Валидация номера студенческого билета
    public static boolean isValidStudentBookNumber(long studentBookNumber) {
        return studentBookNumber > 0;
    }

    // Валидация имени пользователя
    public static boolean isValidUserName(String name) {
        return name != null && !name.isEmpty() && name.matches("[A-Za-zА-Яа-яЁё]+(?: [A-Za-zА-Яа-яЁё]+)*");
    }

    // Валидация пароля пользователя
    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password != null && !password.isEmpty() && password.matches(passwordRegex);
    }

    // Валидация email пользователя
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && !email.isEmpty() && email.matches(emailRegex);
    }
}