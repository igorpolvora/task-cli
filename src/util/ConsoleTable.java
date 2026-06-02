package util;

import model.Task;

import java.util.List;

public class ConsoleTable {

    private ConsoleTable() {
    }

    public static void printTasks(List<Task> tasks) {

        if (tasks.isEmpty()) {

            System.out.println();
            System.out.println("No tasks found.");
            System.out.println();

            return;
        }

        System.out.println();

        System.out.printf(
                "%-5s %-40s %-15s %-10s%n",
                "ID",
                "DESCRIPTION",
                "STATUS",
                "PRIORITY"
        );

        System.out.println(
                "--------------------------------------------------------------------------"
        );

        for (Task task : tasks) {

            System.out.printf(
                    "%-5d %-40s %-15s %-10s%n",
                    task.getId(),
                    task.getDescription(),
                    task.getStatus(),
                    task.getPriority()
            );
        }

        System.out.println();
    }
}