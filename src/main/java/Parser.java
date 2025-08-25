public class Parser {

    public static Command parseCommand(String fullCommand) throws JeffException {
        
        String[] split = fullCommand.trim().split("\\s+", 2);
        
        Command cmd = Command.fromString(split[0]);
        if (cmd == null) {
            throw new JeffException("Invalid command!");
        }

        return cmd;
    }
}