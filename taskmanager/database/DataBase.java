package ru.task.taskmanager.database;
import ru.task.taskmanager.domain.Task;
import ru.task.taskmanager.requests.TaskUpdateRequest;

import java.util.*;

public class DataBase {
    private final List<Task> data;
    private int counter;

    public DataBase() {
        data = new ArrayList<>();
        counter = 1;
    }

    public void add(Task task) {
        task.setId(counter);
        counter++;
        data.add(task);
    }

    public Task findTask(int id) {
        for (Task task : data) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public void remove(int id) {
        Task task = findTask(id);
        if (task == null){
            return;
        }
        data.remove(task);
    }

    public void removeAll(){
        data.clear();
    }

    public List<Task> getAllTasks(){
        return data;
    }

    public void updateName(int id, TaskUpdateRequest taskUpdateRequest){
        Task task = findTask(id);
        task.setText(taskUpdateRequest.getText());
        task.setDate(taskUpdateRequest.getDate());
        task.setName(taskUpdateRequest.getName());
    }
}
