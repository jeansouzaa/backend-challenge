package backend.challenge.modules.task.interfaces;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;

public interface ITaskFactory {
	Task build(TaskDTO taskDTO);
}
