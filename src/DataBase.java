import java.util.*;

public class DataBase {
    private List<User> data;
    private int counter;

    public DataBase() {
        data = new ArrayList<>();
        counter = 1;
    }

    public void add(User user) {
        user.setId(counter);
        counter++;
        data.add(user);
    }

    public User findUser(int id) {
        for (User user : data) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public void remove(int id) {
        User user = findUser(id);
        if (user == null){
            return;
        }
        data.remove(user);
    }

    public void removeAll(){
        data.clear();
    }

    public List<User> getAllUsers(){
        return data;
    }

    public void updatePassword(int id, String newPassword){
        User user = findUser(id);
        user.setPassword(newPassword);
    }

    public void updateName(int id, String newName){
        User user = findUser(id);
        user.setName(newName);
    }
}
