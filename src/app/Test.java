package app;

import model.Priority;
import model.Task;
import model.TaskStatus;
import util.DateUtil;

public class Test {

    public static void main(String[] args) {

        Task task = new Task(
                1,
                "Study Java",
                TaskStatus.TODO,
                Priority.HIGH,
                DateUtil.now(),
                DateUtil.now()
        );

        System.out.println(task);
    }
}