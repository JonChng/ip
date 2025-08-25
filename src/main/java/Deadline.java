import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description, "D");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        this.by = LocalDateTime.parse(by, formatter);
    }

    public String getBy() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        return by.format(formatter);
    }
    

    @Override
    public String toString() {
        return "[D]" + super.toString() +  "(by: " + getBy() + ")";
    }
}
