package java_sorting_app.model;

import java_sorting_app.validator.DataValidator;

import java.util.Comparator;
import java.util.Optional;

public class User implements Comparable<User>, SerializableToCSVString {
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

    @Override
    public int compareTo(User o) {
        return Comparator.comparing(User::getName)
                .thenComparing(User::getEmail)
                .thenComparing(User::getPassword)
                .compare(this, o);
    }

    public int magicCompare(User o) {
        int thisNameLength = this.getName().length();
        int oNameLength = o.getName().length();

        if (thisNameLength % 2 == 0 && oNameLength % 2 == 0) {
            return Integer.compare(thisNameLength, oNameLength);
        } else {
            return 0;
        }
    }

    public static UserBuilder create() {
        return new UserBuilder();
    }

    // Статический вложенный класс UserBuilder для паттерна "Строитель"
    public static class UserBuilder {
        private String name = "";
        private String password = "";
        private String email = "";

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

        Optional<String> nameOptional = DataValidator.validateAndReturnUserName(userData[0].trim());
        nameOptional.ifPresentOrElse(userBuilder::withName,
                () -> System.err.println("У пользователя обязательно должно быть имя"));

        Optional<String> emailOptional = DataValidator.validateAndReturnEmail(userData[1].trim());
        emailOptional.ifPresentOrElse(userBuilder::withEmail,
                () -> System.err.println("Некорректный email пользователя"));

        Optional<String> passwordOptional = DataValidator.validateAndReturnPassword(userData[2]);
        passwordOptional.ifPresentOrElse(userBuilder::withPassword,
                () -> System.out.println("Некорректный пароль.\n" +
                        "Пароль должен содержать хотя бы один \n" +
                        "специальный символ (@$,!,%,*,?,&) и быть длиннее 8 символов.")
        );

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