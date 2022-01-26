public class Parser {

    public Command parse(String fullCommand) throws DukeException {
        String keyWord = fullCommand.split(" ")[0];
        try {
            switch (keyWord) {
            case "list": {
                return new ListCommand();
            }
            case "mark": {
                if (fullCommand.split(" ").length == 1) {
                    throw new DukeException("☹ OOPS!!! Please choose a task number");
                }
                int taskNo = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
                return new MarkCommand(true,taskNo);
            }
            case "unmark": {
                if (fullCommand.split(" ").length == 1) {
                    throw new DukeException("☹ OOPS!!! Please choose a task number");
                }
                int taskNo = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
                return new MarkCommand(false,taskNo);
            }
            case "todo": {
                String task = fullCommand.replaceFirst("todo", "");
                if (task.length() == 0 || task.trim().length() == 0) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty");
                }
                return new AddToDoCommand(task);
            }
            case "deadline": {
                String[] text = fullCommand.replaceFirst("deadline", "").split(" /by ");
                String task = text[0];
                if (task.trim().length() == 0) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                if (text.length == 1) {
                    throw new DukeException("☹ OOPS!!! The date/time of a deadline cannot be empty.");
                }
                String by = text[1];
                return new AddDeadlineCommand(task,by);
            }
            case "event": {
                String[] text = fullCommand.replaceFirst("event", "").split(" /at ");
                String task = text[0];
                if (task.trim().length() == 0) {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                }
                if (text.length == 1) {
                    throw new DukeException("☹ OOPS!!! The time of an event cannot be empty.");
                }
                String at = text[1];
                return new AddEventCommand(task,at);
            }
            case "delete": {
                if (fullCommand.split(" ").length == 1) {
                    throw new DukeException("☹ OOPS!!! Please choose a task number.");
                }
                int taskNo = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
                return new DeleteTaskCommand(taskNo);
            }
            case "bye": {
                return new ExitCommand();
            }
            default: {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a task number.");
        }
    }
}
