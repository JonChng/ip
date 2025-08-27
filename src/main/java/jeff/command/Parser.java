package jeff.command;

import jeff.storage.JeffException;

public class Parser {

    public static class Result {

        public final Command command;
        public final String description;

        public Result(Command command, String description) {
            this.command = command;
            this.description = description;
        }
    }

    public static Result parseCommand(String fullCommand) throws JeffException {

        String[] split = fullCommand.trim().split("\\s+", 2);
        String description = split.length > 1 ? split[1] : "";
        Command cmd = Command.fromString(split[0]);

        if (cmd == null) {
            throw new JeffException("EXCUSEEE MEEEE. THIS IS A INVALID COMMAND??!!! Try again.");
        }

        return new Result(cmd, description);
    }
}
