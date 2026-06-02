package app;

import model.Priority;
import model.Task;
import model.TaskStatus;
import repository.HistoryRepository;
import repository.TaskRepository;
import util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class TestRepository {

    public static void main(String[] args) {

        TaskRepository repository =
                new TaskRepository();

        HistoryRepository history =
                new HistoryRepository();

        List<Task> tasks =
                new ArrayList<>();

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

        repository.saveAll(tasks);

        history.log("ADD Task #1");
        history.log("ADD Task #2");

        List<Task> loaded =
                repository.findAll();

        loaded.forEach(System.out::println);
    }
}