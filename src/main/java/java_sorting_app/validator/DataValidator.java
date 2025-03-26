package java_sorting_app.validator;

import java.util.Optional;

public class DataValidator {

    public static Optional<String> validateAndReturnBusNumber(String number) {
        String busNumberRegex = "^[A-Za-zА-Яа-я0-9]+$";
        if (number != null && !number.isEmpty() && number.matches(busNumberRegex)) {
            return Optional.of(number);
        }
        return Optional.empty();
    }

    public static Optional<String> validateAndReturnBusModel(String model) {
        if (model != null && !model.isEmpty() && model.length() <= 50) {
            return Optional.of(model);
        }
        return Optional.empty();
    }

    public static Optional<Integer> validateAndReturnMileage(String mileageInput) {
        try {
            int mileage = Integer.parseInt(mileageInput);

            if (mileage > 0) {
                return Optional.of(mileage);
            } else {
                return Optional.empty();
            }
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Integer> validateAndReturnGroupNumber(String groupNumberString) {
        try {
            int groupNumber = Integer.parseInt(groupNumberString.trim());

            if (groupNumber > 0) {
                return Optional.of(groupNumber);
            } else {
                return Optional.empty();
            }
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Double> validateAndReturnAverageGrade(String averageGradeString) {
        try {
            double averageGrade = Double.parseDouble(averageGradeString.trim());

            if (averageGrade >= 0.0 && averageGrade <= 5.0) {
                return Optional.of(averageGrade);
            } else {
                return Optional.empty();
            }
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Long> validateAndReturnStudentBookNumber(String studentBookNumberString) {
        try {
            long studentBookNumber = Long.parseLong(studentBookNumberString.trim());

            if (studentBookNumber > 0) {
                return Optional.of(studentBookNumber);
            } else {
                return Optional.empty();
            }
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<String> validateAndReturnUserName(String name) {
        String userNameRegex = "[A-Za-zА-Яа-яЁё]+(?: [A-Za-zА-Яа-яЁё]+)*";
        if (name != null && !name.isEmpty() && name.matches(userNameRegex)) {
            return Optional.of(name);
        }
        return Optional.empty();
    }

    public static Optional<String> validateAndReturnPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        if (password != null && !password.isEmpty() && password.matches(passwordRegex)) {
            return Optional.of(password);
        }
        return Optional.empty();
    }

    public static Optional<String> validateAndReturnEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (email != null && !email.isEmpty() && email.matches(emailRegex)) {
            return Optional.of(email);
        }
        return Optional.empty();
    }
}