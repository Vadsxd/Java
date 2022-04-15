import java.io.*;
import java.util.*;


public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataBase database = new DataBase();

        while (true) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine();
            switch (command) {
                case "exit":
                    return;
                case "add":
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    User user = new User(password, name);
                    database.add(user);
                    break;
                case "remove":
                    System.out.print("Enter id to remove User: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    database.remove(id);
                    break;
                case "get":
                    System.out.println(database.getAllUsers().toString());
                    break;
                case "getid":
                    System.out.print("Enter id to get User: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(database.findUser(id).toString());
                    break;
                case "changeName":
                    System.out.print("Enter id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter newName: ");
                    String newName = scanner.nextLine();
                    database.updateName(id, newName);
                    break;
                case "changePassword":
                    System.out.print("Enter id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter newPassword: ");
                    String newPassword = scanner.nextLine();
                    database.updatePassword(id, newPassword);
                    break;
                case "save":
                    saveData(database.getAllUsers());
                    break;
                case "load":
                    database.removeAll();
                    loadData(database);
                    break;
                default:
                    System.out.println("Unknown command!");
                    break;
            }
        }

    }

    private static void saveData(List<User> data) {
        File file = new File("/home/vadim/IdeaProjects/Task/src/Save.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            for (User user : data) {
                fr.write(String.valueOf(user.getId()));
                fr.write("\n");
                fr.write(user.getName());
                fr.write("\n");
                fr.write(user.getPassword());
                fr.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fr != null;
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void loadData(DataBase database) {
        try {
            FileReader fr = new FileReader("/home/vadim/IdeaProjects/Task/src/Save.txt");
            BufferedReader reader = new BufferedReader(fr);
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                int id = Integer.parseInt(line);
                String name = reader.readLine();
                String password = reader.readLine();
                User user = new User(password, name);
                user.setId(id);
                database.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
