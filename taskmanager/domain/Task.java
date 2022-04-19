package ru.task.taskmanager.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private int id;
    private String name;
    private String text;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date date;
}
