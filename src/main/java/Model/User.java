package Model;

public class User {
    public String name;
    public int age;

    public User() {
        // Default constructor for Jackson
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + '}';
    }
}