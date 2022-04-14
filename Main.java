import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hashtable<Long, String> ht1 = new Hashtable<>();
        while (true) {
            String command = scanner.next();
            if (Objects.equals(command, "exit")) {
                break;
            }
            if (Objects.equals(command, "put")) {
                Long key = scanner.nextLong();
                String value = scanner.next();
                ht1.put(key, value);
            } else if (Objects.equals(command, "remove")) {
                Long key = scanner.nextLong();
                ht1.remove(key);
            } else if (Objects.equals(command, "get")) {
                Long key = scanner.nextLong();
                System.out.println("Your value request is " + ht1.get(key));
            } else if (Objects.equals(command, "save")){
                Save_Data(ht1);
            } else if (Objects.equals(command, "load")){
                ht1.clear();
                Load_Data(ht1);
            } else {
                System.out.println("Unknown command!");
            }
            System.out.println(ht1);
        }
    }

    private static void Save_Data(Hashtable<Long, String> ht1) {
        File file = new File("/home/vadim/IdeaProjects/Task/src/Save.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            for(int i = 0; i < ht1.size(); i++){
                fr.write(String.valueOf(ht1.keySet().toArray()[i]));
                fr.write("\n");
                fr.write(String.valueOf(ht1.values().toArray()[i]));
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

    private static void Load_Data(Hashtable<Long, String> ht1){
        try {
            FileReader fr = new FileReader("/home/vadim/IdeaProjects/Task/src/Save.txt");
            BufferedReader reader = new BufferedReader(fr);
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                long key = Long.parseLong(line);
                line = reader.readLine();
                ht1.put(key, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}