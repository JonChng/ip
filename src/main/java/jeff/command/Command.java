package jeff.command;

public enum Command {
    LIST, BYE, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT;
    
    public static Command fromString(String command) {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
