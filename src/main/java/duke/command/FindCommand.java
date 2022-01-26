package duke.command;

import duke.exception.DukeException;
import duke.manager.Storage;
import duke.manager.TaskList;
import duke.manager.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a command that finds and prints all the tasks
 * in the TaskList that matches the search term upon execution.
 */
public class FindCommand extends Command {
    private String searchTerm;

    /**
     * A constructor that stores the searchTerm.
     *
     * @param searchTerm The String to search through the TaskList.
     */
    public FindCommand(String searchTerm) {
        super();
        this.searchTerm = searchTerm;
    }

    /**
     * Executes the command by finding all Tasks that have descriptions that contain the search term
     * then lists them all out.
     *
     * @param taskList A TaskList that stores the tasks.
     * @param ui       An Ui object to handle user interaction.
     * @param storage  A Storage object to handle saving of data.
     * @throws DukeException If there is an issue retrieving the tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int numOfTasks = taskList.numOfTasks();
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (int i = 0; i < numOfTasks; i++) {
            String taskFullDescription = taskList.getTask(i).getTaskName();
            if (taskFullDescription.contains(searchTerm)) {
                matchedTasks.add(taskList.getTask(i));
            }
        }
        ui.print("Here are the matching tasks in your list:");
        for (int i = 0; i < matchedTasks.size(); i++) {
            ui.print(i + 1 + "." + matchedTasks.get(i).toString());
        }
    }

    /**
     * Returns true if it is an exit command and false otherwise.
     *
     * @return a boolean.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
