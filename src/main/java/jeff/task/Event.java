package jeff.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected LocalDateTime at;

    public Event(String description, String at) {
        super(description, "E");

        this.at = parseDate(at.trim());
    }

    public String getAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        return at.format(formatter);
    }

    public String getForStorage() {
        return at.format(FORMAT);
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
        return "[E]" + super.toString() + description + " (at: " + getAt() + ")";
    }
}
