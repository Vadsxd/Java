import java.io.*;
import java.util.*;

class ArrayList<T> {
    private final int INIT_SIZE = 10;
    private Object[] array = new Object[INIT_SIZE];
    private int pointer = 0;

    public void add(T value) {
        if(pointer == array.length-1)
            resize(array.length*2);
        array[pointer++] = value;
    }

    public T get(int index) {
        return (T) array[index];
    }

    public void remove(int index) {
        if (pointer - index >= 0) System.arraycopy(array, index + 1, array, index, pointer - index);
        array[pointer] = null;
        pointer--;
        int CUT_RATE = 4;
        if (array.length > INIT_SIZE && pointer < array.length / CUT_RATE) {
            resize(array.length / 2);
        }
    }

    public void remove_all(){
        for(int i = 0; i < pointer; i++){
            array[i] = null;
        }
        pointer = 0;
    }

    public int size() {
        return pointer;
    }

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }
}

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Long> list = new ArrayList<>();

        while (true) {
            String command = scanner.next();
            if (Objects.equals(command, "exit")) {
                break;
            }
            if (Objects.equals(command, "add")) {
                long value = scanner.nextLong();
                list.add(value);
            } else if (Objects.equals(command, "remove")) {
                int index = scanner.nextInt();
                list.remove(index);
            } else if (Objects.equals(command, "get")) {
                int value = scanner.nextInt();
                System.out.println("Your value request is " + list.get(value));
            } else if (Objects.equals(command, "save")){
                Save_Data(list);
            } else if (Objects.equals(command, "load")){
                list.remove_all();
                Load_Data(list);
            } else {
                System.out.println("Unknown command!");
            }
            for(int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println();
        }
        
    }
    private static void Save_Data(ArrayList<Long> list) {
        File file = new File("/home/vadim/IdeaProjects/Task/src/Save.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            for(int i = 0; i < list.size(); i++){
                fr.write(String.valueOf(list.get(i)));
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

    private static void Load_Data(ArrayList<Long> list){
        try {
            FileReader fr = new FileReader("/home/vadim/IdeaProjects/Task/src/Save.txt");
            BufferedReader reader = new BufferedReader(fr);
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                long value = Long.parseLong(line);
                list.add(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
