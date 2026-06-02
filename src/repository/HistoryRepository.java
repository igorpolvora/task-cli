package repository;

import util.DateUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class HistoryRepository {

    private static final String DATA_DIR = "data";
    private static final String HISTORY_FILE =
            "data/history.log";

    public HistoryRepository() {
        initialize();
    }

    private void initialize() {

        try {

            Path dataDir = Path.of(DATA_DIR);

            if (!Files.exists(dataDir)) {
                Files.createDirectories(dataDir);
            }

            Path historyFile =
                    Path.of(HISTORY_FILE);

            if (!Files.exists(historyFile)) {
                Files.createFile(historyFile);
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "Erro ao criar history.log",
                    e
            );
        }
    }

    public void log(String action) {

        String entry = String.format(
                "[%s] %s%n",
                DateUtil.now(),
                action
        );

        try {

            Files.writeString(
                    Path.of(HISTORY_FILE),
                    entry,
                    StandardOpenOption.APPEND
            );

        } catch (IOException e) {

            System.out.println(
                    "Erro ao registrar histórico."
            );
        }
    }
}