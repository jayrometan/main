package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Adds a task to the tasklist
 */
public class AddTaskCommand extends Command {
    public static final String COMMAND_WORD = "addtask";

    /**
     * String usage
     * Tells the user how to use this command
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task list."
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DEADLINE_DATE + "DEADLINE_DATE"
            + PREFIX_DEADLINE_TIME + "DEADLINE_TIME"
            + '[' + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2113T MILESTONE V5 "
            + PREFIX_DEADLINE_DATE + "290319 "
            + PREFIX_DEADLINE_TIME + "2359 "
            + PREFIX_TAG + "HIGH";


    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list";

    private final Task newTask;

    /**
     * @param task that is being added.
     * Creates an AddCommand to add the specified {@code task}
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        newTask = task;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(newTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        model.addTask(newTask);
        model.commitTaskList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, newTask));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && newTask.equals(((AddTaskCommand) other).newTask));
    }
}
