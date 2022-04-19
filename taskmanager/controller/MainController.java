package ru.task.taskmanager.controller;

import org.springframework.web.bind.annotation.*;
import ru.task.taskmanager.database.DataBase;
import ru.task.taskmanager.domain.Task;
import ru.task.taskmanager.requests.TaskUpdateRequest;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class MainController {
    private final DataBase database = new DataBase();

    @GetMapping()
    public List<Task> getTasks() {
        return database.getAllTasks();
    }

    @GetMapping("/find")
    public Task getTask(@RequestParam("id") int id) {
        return database.findTask(id);
    }

    @PostMapping("/create")
    public void addTask(@RequestBody Task task) {
        database.add(task);
    }

    @PostMapping("/update")
    public void updateTask(@RequestParam("id") int id,
                           @RequestBody TaskUpdateRequest taskUpdateRequest) {
        database.updateName(id, taskUpdateRequest);
    }

    @PostMapping("/delete")
    public void deleteTask(@RequestParam("id") int id){
        database.remove(id);
    }

}
