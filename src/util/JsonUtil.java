package util;

import model.Priority;
import model.Task;
import model.TaskStatus;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    private JsonUtil() {
    }

    /*
     * ========================================
     * SERIALIZAÇÃO
     * ========================================
     */

    public static String taskToJson(Task task) {

        return String.format("""
                {
                  "id": %d,
                  "description": "%s",
                  "status": "%s",
                  "priority": "%s",
                  "createdAt": "%s",
                  "updatedAt": "%s"
                }
                """,
                task.getId(),
                escape(task.getDescription()),
                task.getStatus(),
                task.getPriority(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    public static String tasksToJson(List<Task> tasks) {

        StringBuilder json = new StringBuilder();

        json.append("[\n");

        for (int i = 0; i < tasks.size(); i++) {

            json.append(taskToJson(tasks.get(i)));

            if (i < tasks.size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("]");

        return json.toString();
    }

    /*
     * ========================================
     * DESSERIALIZAÇÃO
     * ========================================
     */

    public static List<Task> jsonToTasks(String json) {

        List<Task> tasks = new ArrayList<>();

        if (json == null || json.isBlank()) {
            return tasks;
        }

        json = json.trim();

        if (json.equals("[]")) {
            return tasks;
        }

        if (json.startsWith("[")) {
            json = json.substring(1);
        }

        if (json.endsWith("]")) {
            json = json.substring(0, json.length() - 1);
        }

        String[] objects = json.split("\\},\\s*\\{");

        for (String object : objects) {

            object = object.trim();

            if (!object.startsWith("{")) {
                object = "{" + object;
            }

            if (!object.endsWith("}")) {
                object = object + "}";
            }

            Task task = parseTask(object);

            if (task != null) {
                tasks.add(task);
            }
        }

        return tasks;
    }

    private static Task parseTask(String json) {

        try {

            Task task = new Task();

            task.setId(
                    Integer.parseInt(
                            extract(json, "id")
                    )
            );

            task.setDescription(
                    extract(json, "description")
            );

            task.setStatus(
                    TaskStatus.valueOf(
                            extract(json, "status")
                    )
            );

            task.setPriority(
                    Priority.valueOf(
                            extract(json, "priority")
                    )
            );

            task.setCreatedAt(
                    extract(json, "createdAt")
            );

            task.setUpdatedAt(
                    extract(json, "updatedAt")
            );

            return task;

        } catch (Exception e) {

            System.out.println(
                    "Erro ao processar registro JSON."
            );

            return null;
        }
    }

    /*
     * ========================================
     * EXTRAÇÃO DE CAMPOS
     * ========================================
     */

    private static String extract(
            String json,
            String field) {

        String key = "\"" + field + "\"";

        int keyPos = json.indexOf(key);

        if (keyPos == -1) {
            return "";
        }

        int colonPos = json.indexOf(
                ":",
                keyPos
        );

        if (colonPos == -1) {
            return "";
        }

        int start = colonPos + 1;

        while (start < json.length()
                && Character.isWhitespace(
                json.charAt(start))) {

            start++;
        }

        if (json.charAt(start) == '"') {

            start++;

            int end = json.indexOf(
                    "\"",
                    start
            );

            return json.substring(
                    start,
                    end
            );
        }

        int end = start;

        while (end < json.length()
                && json.charAt(end) != ','
                && json.charAt(end) != '}') {

            end++;
        }

        return json.substring(
                start,
                end
        ).trim();
    }

    /*
     * ========================================
     * ESCAPE
     * ========================================
     */

    private static String escape(String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}