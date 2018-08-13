package win.smile.model;

/**
 * @author smilewei on 2018/8/10.
 * @since 1.0.0
 */
public class UserModel {

    private String username;
    private int age;

    public UserModel() {
    }

    public UserModel(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }
}
