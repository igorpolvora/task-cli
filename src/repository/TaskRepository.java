package repository;

import model.Task;
import util.JsonUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private static final String DATA_DIR = "data";
    private static final String TASKS_FILE = "data/tasks.json";

    public TaskRepository() {
        initialize();
    }

    private void initialize() {

        try {

            Path dataDir = Path.of(DATA_DIR);

            if (!Files.exists(dataDir)) {
                Files.createDirectories(dataDir);
            }

            Path taskFile = Path.of(TASKS_FILE);

            if (!Files.exists(taskFile)) {
                Files.writeString(taskFile, "[]");
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "Erro ao inicializar repositório",
                    e
            );
        }
    }

    public List<Task> findAll() {

        try {

            String json =
                    Files.readString(
                            Path.of(TASKS_FILE)
                    );

            return JsonUtil.jsonToTasks(json);

        } catch (IOException e) {

            System.out.println(
                    "Erro ao ler tarefas."
            );

            return new ArrayList<>();
        }
    }

    public void saveAll(List<Task> tasks) {

        try {

            String json =
                    JsonUtil.tasksToJson(tasks);

            Files.writeString(
                    Path.of(TASKS_FILE),
                    json
            );

        } catch (IOException e) {

            System.out.println(
                    "Erro ao salvar tarefas."
            );
        }
    }
}