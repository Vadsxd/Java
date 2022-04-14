import java.io.*;
import java.util.*;

class DataBase{
    private final List<Long> data = new ArrayList<>();

    public void add(long value){
        data.add(value);
    }

    public void remove(int index){
        data.remove(index);
    }

    public void remove_all(){
        data.clear();
    }

    public int get_size(){
        return data.size();
    }

    public long get(int index){
        return data.get(index);
    }

    public Object[] get_all(){
        return data.toArray();
    }
}

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataBase database = new DataBase();

        while (true) {
            String command = scanner.next();
            if (Objects.equals(command, "exit")) {
                break;
            }
            if (Objects.equals(command, "add")) {
                long value = scanner.nextLong();
                database.add(value);
            } else if (Objects.equals(command, "remove")) {
                int index = scanner.nextInt();
                database.remove(index);
            } else if (Objects.equals(command, "get")) {
                int value = scanner.nextInt();
                System.out.println("Your value request is " + database.get(value));
            } else if (Objects.equals(command, "save")){
                Save_Data(database.get_all());
            } else if (Objects.equals(command, "load")){
                database.remove_all();
                Load_Data(database);
            } else {
                System.out.println("Unknown command!");
            }
            System.out.println(Arrays.toString(database.get_all()));
        }
        
    }
    private static void Save_Data(Object[] array) {
        File file = new File("/home/vadim/IdeaProjects/Task/src/Save.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            for (Object o : array) {
                fr.write(String.valueOf(o));
                fr.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                assert fr != null;
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void Load_Data(DataBase database){
        try {
            FileReader fr = new FileReader("/home/vadim/IdeaProjects/Task/src/Save.txt");
            BufferedReader reader = new BufferedReader(fr);
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                long value = Long.parseLong(line);
                database.add(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
