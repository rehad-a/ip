package duke.command;

import duke.Storage;
import duke.UI;
import duke.error.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        isExit = false;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException("OOPS!! index is invalid");
        }
        Task task = tasks.deleteTask(index);
        ui.showDelete(task, tasks.getSize());

        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException("OOPS!! something went wrong while trying to update tasks");
        }
    }
}