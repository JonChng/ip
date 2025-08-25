import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected LocalDateTime by;
    

    public Deadline(String description, String by) {
        super(description, "D");

        this.by = parseDate(by.trim());
    }

    public String getBy() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        return by.format(formatter);
    }

    public String getForStorage() {
        return by.format(FORMAT);
    }

    private static LocalDateTime parseDate(String date) {

        String[] acceptableFormats = {"d/M/yyyy HHmm", "dd/MM/yyyy HHmm", "yyyy-MM-dd HHmm"};

        for (String format : acceptableFormats) {
            try {
                return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));
            } catch (Exception e) {
            }
        }
        return null;
    }
    

    @Override
    public String toString() {
        return "[D]" + super.toString() +  "(by: " + getBy() + ")";
    }
}
