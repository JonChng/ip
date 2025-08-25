import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, String at) {
        super(description, "E");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        this.at = LocalDateTime.parse(at, formatter);
    }

    public String getAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        return at.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + description + " (from: " + getAt() + ")";
    }
    
}
