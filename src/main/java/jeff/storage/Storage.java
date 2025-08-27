package jeff.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    private static final String FILE_PATH = "data/tasks.txt";

    public ArrayList<String> load() {
        ArrayList<String> lines = new ArrayList<>();
        try {
            Path path = Paths.get(FILE_PATH);
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                return lines;
            }

            lines.addAll(Files.readAllLines(path));

        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return lines;
    }

    public void save(ArrayList<String> lines) {
        try {
            Path path = Paths.get(FILE_PATH);
            Files.createDirectories(path.getParent());
            Files.write(path, lines);
            System.out.println("Tasks saved successfully!");

        } catch (IOException e) {

            System.out.println("File could not be saved: " + e.getMessage());
        }
    }
}
