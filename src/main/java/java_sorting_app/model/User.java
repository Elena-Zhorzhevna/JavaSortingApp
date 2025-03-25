package java_sorting_app.model;

import java_sorting_app.validator.DataValidator;

import java.util.Optional;

public class User implements SerializableToCSVString {
    private String name;
    private String password;
    private String email;

    private User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static UserBuilder create() {
        return new UserBuilder();
    }

    // Статический вложенный класс UserBuilder для паттерна "Строитель"
    public static class UserBuilder {
        private String name;
        private String password;
        private String email;

        // Методы для установки значений
        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        // Метод для создания объекта User
        public User build() {
            return new User(name, password, email);
        }
    }

    public static Optional<User> fromCSVString(String stringObjectCSV) {

        String[] userData = stringObjectCSV.split(";");
        if (userData.length != 3) {
            System.err.println("Ошибка в данных - строка не соответствует формату.");
            return Optional.empty();
        }

        User.UserBuilder userBuilder = User.create();

        if (DataValidator.isValidUserName(userData[0])) {
            userBuilder.withName(userData[0]);
        }
        else {
            System.err.println("У пользователя обязательно должно быть имя");
            return Optional.empty();
        }

        if (DataValidator.isValidEmail(userData[1])) {
            userBuilder.withEmail(userData[1]);
        }
        else {
            System.err.println("Некорректный email пользователя");
        }

        if (DataValidator.isValidPassword(userData[2])) {
            userBuilder.withPassword(userData[2]);
        }
        else {
            System.out.println("Некорректный пароль.\n" +
                    "Пароль должен содержать хотя бы один символ из этих категорий: \n" +
                    "Заглавную латинскую букву (A-Z)\n" +
                    "Строчную латинскую букву (a-z)\n" +
                    "Цифру\n" +
                    "Специальный символ (@$,!,%,*,?,&) и быть длиннее 8 символов.");
        }

        User user = userBuilder.build();

        return Optional.of(user);
    }

    @Override
    public String toCSVString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name)
                .append(";")
                .append(password)
                .append(";")
                .append(email);

        return stringBuilder.toString();
    }
}