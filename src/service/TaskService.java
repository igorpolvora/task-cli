package service;

import model.Priority;
import model.Task;
import model.TaskStatus;
import repository.HistoryRepository;
import repository.TaskRepository;
import util.ConsoleTable;
import util.DateUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TaskService {

    private final TaskRepository taskRepository;
    private final HistoryRepository historyRepository;

    public TaskService() {

        this.taskRepository =
                new TaskRepository();

        this.historyRepository =
                new HistoryRepository();
    }

    /*
     * ADD
     */

    public void addTask(
            String description,
            Priority priority) {

        List<Task> tasks =
                taskRepository.findAll();

        int nextId =
                tasks.stream()
                        .mapToInt(Task::getId)
                        .max()
                        .orElse(0) + 1;

        String now =
                DateUtil.now();

        Task task = new Task(
                nextId,
                description,
                TaskStatus.TODO,
                priority,
                now,
                now
        );

        tasks.add(task);

        taskRepository.saveAll(tasks);

        historyRepository.log(
                "ADD Task #" + nextId
        );

        System.out.println(
                "Task added successfully (ID: "
                        + nextId + ")"
        );
    }

    /*
     * UPDATE
     */

    public void updateTask(
            int id,
            String description) {

        List<Task> tasks =
                taskRepository.findAll();

        Optional<Task> optional =
                findById(tasks, id);

        if (optional.isEmpty()) {

            System.out.println(
                    "Task not found."
            );

            return;
        }

        Task task =
                optional.get();

        task.setDescription(description);
        task.setUpdatedAt(DateUtil.now());

        taskRepository.saveAll(tasks);

        historyRepository.log(
                "UPDATE Task #" + id
        );

        System.out.println(
                "Task updated."
        );
    }

    /*
     * DELETE
     */

    public void deleteTask(int id) {

        List<Task> tasks =
                taskRepository.findAll();

        boolean removed =
                tasks.removeIf(
                        task -> task.getId() == id
                );

        if (!removed) {

            System.out.println(
                    "Task not found."
            );

            return;
        }

        taskRepository.saveAll(tasks);

        historyRepository.log(
                "DELETE Task #" + id
        );

        System.out.println(
                "Task deleted."
        );
    }

    /*
     * STATUS
     */

    public void markDone(int id) {
        updateStatus(id, TaskStatus.DONE);
    }

    public void markInProgress(int id) {
        updateStatus(id,
                TaskStatus.IN_PROGRESS);
    }

    public void markTodo(int id) {
        updateStatus(id,
                TaskStatus.TODO);
    }

    private void updateStatus(
            int id,
            TaskStatus status) {

        List<Task> tasks =
                taskRepository.findAll();

        Optional<Task> optional =
                findById(tasks, id);

        if (optional.isEmpty()) {

            System.out.println(
                    "Task not found."
            );

            return;
        }

        Task task =
                optional.get();

        task.setStatus(status);
        task.setUpdatedAt(
                DateUtil.now()
        );

        taskRepository.saveAll(tasks);

        historyRepository.log(
                status + " Task #" + id
        );

        System.out.println(
                "Task updated."
        );
    }

    /*
     * LIST
     */

    public void listAll() {

        List<Task> tasks =
                taskRepository.findAll();

        ConsoleTable.printTasks(tasks);
    }

    public void listByStatus(
            TaskStatus status) {

        List<Task> tasks =
                taskRepository.findAll()
                        .stream()
                        .filter(
                                task ->
                                        task.getStatus()
                                                == status
                        )
                        .toList();

        ConsoleTable.printTasks(tasks);
    }

    /*
     * SEARCH
     */

    public void search(
            String keyword) {

        List<Task> tasks =
                taskRepository.findAll()
                        .stream()
                        .filter(task ->
                                task.getDescription()
                                        .toLowerCase()
                                        .contains(
                                                keyword
                                                        .toLowerCase()
                                        ))
                        .toList();

        ConsoleTable.printTasks(tasks);
    }

    /*
     * SORT
     */

    public void sortByDate() {

        List<Task> tasks =
                taskRepository.findAll()
                        .stream()
                        .sorted(
                                Comparator.comparing(
                                        Task::getCreatedAt
                                )
                        )
                        .toList();

        ConsoleTable.printTasks(tasks);
    }

    public void sortByPriority() {

        List<Task> tasks =
                taskRepository.findAll()
                        .stream()
                        .sorted(
                                Comparator.comparing(
                                        Task::getPriority
                                )
                        )
                        .toList();

        ConsoleTable.printTasks(tasks);
    }

    /*
     * STATS
     */

    public void stats() {

        List<Task> tasks =
                taskRepository.findAll();

        long todo =
                tasks.stream()
                        .filter(t ->
                                t.getStatus()
                                        == TaskStatus.TODO)
                        .count();

        long progress =
                tasks.stream()
                        .filter(t ->
                                t.getStatus()
                                        == TaskStatus.IN_PROGRESS)
                        .count();

        long done =
                tasks.stream()
                        .filter(t ->
                                t.getStatus()
                                        == TaskStatus.DONE)
                        .count();

        double completion = 0;

        if (!tasks.isEmpty()) {

            completion =
                    (done * 100.0)
                            / tasks.size();
        }

        System.out.println();

        System.out.println(
                "========== STATS =========="
        );

        System.out.println(
                "Total: " + tasks.size()
        );

        System.out.println(
                "TODO: " + todo
        );

        System.out.println(
                "IN_PROGRESS: "
                        + progress
        );

        System.out.println(
                "DONE: " + done
        );

        System.out.printf(
                "Completion Rate: %.2f%%%n",
                completion
        );

        System.out.println();
    }

    /*
     * EXPORT
     */

    public void exportReport() {

        List<Task> tasks =
                taskRepository.findAll();

        StringBuilder report =
                new StringBuilder();

        report.append(
                "TASK REPORT\n\n"
        );

        for (Task task : tasks) {

            report.append(
                    "ID: "
            ).append(task.getId())
                    .append("\n");

            report.append(
                    "Description: "
            ).append(task.getDescription())
                    .append("\n");

            report.append(
                    "Status: "
            ).append(task.getStatus())
                    .append("\n");

            report.append(
                    "Priority: "
            ).append(task.getPriority())
                    .append("\n");

            report.append(
                    "Created: "
            ).append(task.getCreatedAt())
                    .append("\n");

            report.append(
                    "Updated: "
            ).append(task.getUpdatedAt())
                    .append("\n\n");
        }

        try {

            Files.writeString(
                    Path.of(
                            "data/tasks-report.txt"
                    ),
                    report.toString()
            );

            System.out.println(
                    "Report exported."
            );

        } catch (IOException e) {

            System.out.println(
                    "Export failed."
            );
        }
    }

    /*
     * HELPERS
     */

    private Optional<Task> findById(
            List<Task> tasks,
            int id) {

        return tasks.stream()
                .filter(
                        task ->
                                task.getId() == id
                )
                .findFirst();
    }
}