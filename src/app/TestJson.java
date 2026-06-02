package app;

import model.Priority;
import model.Task;
import model.TaskStatus;
import util.DateUtil;
import util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class TestJson {

    public static void main(String[] args) {

        List<Task> tasks = new ArrayList<>();

        tasks.add(
                new Task(
                        1,
                        "Study Java",
                        TaskStatus.TODO,
                        Priority.HIGH,
                        DateUtil.now(),
                        DateUtil.now()
                )
        );

        tasks.add(
                new Task(
                        2,
                        "Read Clean Code",
                        TaskStatus.DONE,
                        Priority.MEDIUM,
                        DateUtil.now(),
                        DateUtil.now()
                )
        );

        String json =
                JsonUtil.tasksToJson(tasks);

        System.out.println(json);

        System.out.println();

        List<Task> restored =
                JsonUtil.jsonToTasks(json);

        restored.forEach(System.out::println);
    }
}