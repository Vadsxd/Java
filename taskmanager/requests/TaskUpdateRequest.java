package ru.task.taskmanager.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateRequest {
    private int id;
    private String name;
    private String text;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;
}
