package java_sorting_app.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<Bus> buses;
    private List<Student> students;
    private List<User> users;

    public Model() {
        buses = new ArrayList<>();
        students = new ArrayList<>();
        users = new ArrayList<>();
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addBus(Bus bus) {
        buses.add(bus);
    }
}
