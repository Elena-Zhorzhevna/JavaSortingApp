package java_sorting_app.model;

import java.io.Serializable;

public class User implements Serializable {
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
}