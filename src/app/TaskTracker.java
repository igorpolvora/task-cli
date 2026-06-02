package app;

import model.Priority;
import model.TaskStatus;
import service.TaskService;

public class TaskTracker {

    private static final TaskService service =
            new TaskService();

    public static void main(String[] args) {

        if (args.length == 0) {

            printHelp();
            return;
        }

        try {

            String command =
                    args[0].toLowerCase();

            switch (command) {

                case "add" -> add(args);

                case "update" -> update(args);

                case "delete" -> delete(args);

                case "mark-done" -> markDone(args);

                case "mark-in-progress" ->
                        markInProgress(args);

                case "mark-todo" ->
                        markTodo(args);

                case "list" -> list(args);

                case "search" -> search(args);

                case "sort" -> sort(args);

                case "stats" ->
                        service.stats();

                case "export" ->
                        service.exportReport();

                case "help" ->
                        printHelp();

                default -> {

                    System.out.println(
                            "Unknown command."
                    );

                    printHelp();
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Invalid command syntax."
            );

            System.out.println(
                    e.getMessage()
            );
        }
    }

    private static void add(String[] args) {

        if (args.length < 2) {

            System.out.println(
                    "Usage: add \"description\" [priority]"
            );

            return;
        }

        String description =
                args[1];

        Priority priority =
                Priority.MEDIUM;

        if (args.length >= 3) {

            priority =
                    Priority.valueOf(
                            args[2]
                                    .toUpperCase()
                    );
        }

        service.addTask(
                description,
                priority
        );
    }

    private static void update(
            String[] args) {

        if (args.length < 3) {

            System.out.println(
                    "Usage: update <id> \"description\""
            );

            return;
        }

        service.updateTask(
                Integer.parseInt(args[1]),
                args[2]
        );
    }

    private static void delete(
            String[] args) {

        if (args.length < 2) {

            System.out.println(
                    "Usage: delete <id>"
            );

            return;
        }

        service.deleteTask(
                Integer.parseInt(
                        args[1]
                )
        );
    }

    private static void markDone(
            String[] args) {

        if (args.length < 2) {

            System.out.println(
                    "Usage: mark-done <id>"
            );

            return;
        }

        service.markDone(
                Integer.parseInt(
                        args[1]
                )
        );
    }

    private static void markInProgress(
            String[] args) {

        if (args.length < 2) {

            System.out.println(
                    "Usage: mark-in-progress <id>"
            );

            return;
        }

        service.markInProgress(
                Integer.parseInt(
                        args[1]
                )
        );
    }

    private static void markTodo(
            String[] args) {

        if (args.length < 2) {

            System.out.println(
                    "Usage: mark-todo <id>"
            );

            return;
        }

        service.markTodo(
                Integer.parseInt(
                        args[1]
                )
        );
    }

    private static void list(
            String[] args) {

        if (args.length == 1) {

            service.listAll();
            return;
        }

        String status =
                args[1]
                        .toLowerCase();

        switch (status) {

            case "todo" ->
                    service.listByStatus(
                            TaskStatus.TODO
                    );

            case "done" ->
                    service.listByStatus(
                            TaskStatus.DONE
                    );

            case "in-progress" ->
                    service.listByStatus(
                            TaskStatus.IN_PROGRESS
                    );

            default ->
                    System.out.println(
                            "Invalid status."
                    );
        }
    }

    private static void search(
            String[] args) {

        if (args.length < 2) {

            System.out.println(
                    "Usage: search <keyword>"
            );

            return;
        }

        service.search(
                args[1]
        );
    }

    private static void sort(
            String[] args) {

        if (args.length < 2) {

            System.out.println(
                    "Usage: sort date|priority"
            );

            return;
        }

        switch (
                args[1]
                        .toLowerCase()
        ) {

            case "date" ->
                    service.sortByDate();

            case "priority" ->
                    service.sortByPriority();

            default ->
                    System.out.println(
                            "Invalid sort type."
                    );
        }
    }

    private static void printHelp() {

        System.out.println();

        System.out.println(
                "================================"
        );

        System.out.println(
                "       TASK TRACKER CLI"
        );

        System.out.println(
                "================================"
        );

        System.out.println();

        System.out.println(
                "ADD"
        );

        System.out.println(
                "  add \"description\" [priority]"
        );

        System.out.println();

        System.out.println(
                "UPDATE"
        );

        System.out.println(
                "  update <id> \"description\""
        );

        System.out.println();

        System.out.println(
                "DELETE"
        );

        System.out.println(
                "  delete <id>"
        );

        System.out.println();

        System.out.println(
                "STATUS"
        );

        System.out.println(
                "  mark-done <id>"
        );

        System.out.println(
                "  mark-in-progress <id>"
        );

        System.out.println(
                "  mark-todo <id>"
        );

        System.out.println();

        System.out.println(
                "LIST"
        );

        System.out.println(
                "  list"
        );

        System.out.println(
                "  list done"
        );

        System.out.println(
                "  list todo"
        );

        System.out.println(
                "  list in-progress"
        );

        System.out.println();

        System.out.println(
                "SEARCH"
        );

        System.out.println(
                "  search java"
        );

        System.out.println();

        System.out.println(
                "SORT"
        );

        System.out.println(
                "  sort date"
        );

        System.out.println(
                "  sort priority"
        );

        System.out.println();

        System.out.println(
                "STATS"
        );

        System.out.println(
                "  stats"
        );

        System.out.println();

        System.out.println(
                "EXPORT"
        );

        System.out.println(
                "  export"
        );

        System.out.println();

        System.out.println(
                "HELP"
        );

        System.out.println(
                "  help"
        );

        System.out.println();
    }
}