package jeff.command;

import jeff.storage.JeffException;

/**
 * Parses user input commands and converts them into executable Command objects.
 */
public class Parser {

    /**
     * Result object containing the parsed command and its description.
     */
    public static class Result {

        public final Command command;
        public final String description;

        /**
         * Constructor for Result with specified command and description.
         *
         * @param command the parsed command enum value
         * @param description the command description/arguments
         */
        public Result(Command command, String description) {
            this.command = command;
            this.description = description;
        }
    }

    /**
     * Parse full command string into a Result object. Split input into command
     * and description parts.
     *
     * @param fullCommand the complete command string from user input
     * @return a Result object containing the parsed command and description
     * @throws JeffException if the command is invalid or cannot be parsed
     */
    public static Result parseCommand(String fullCommand) throws JeffException {

        String[] split = fullCommand.trim().split("\\s+", 2);
        String description = split.length > 1 ? split[1] : "";
        Command command = Command.fromString(split[0]);

        if (command == null) {
            throw new JeffException("EXCUSEEE MEEEE. THIS IS A INVALID COMMAND??!!! Try again.");
        }

        return new Result(command, description);
    }
}
