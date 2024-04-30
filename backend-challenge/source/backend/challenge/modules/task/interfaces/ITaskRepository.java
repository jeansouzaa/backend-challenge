package backend.challenge.modules.task.interfaces;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.models.Task;

import java.util.List;

public interface ITaskRepository {

	Task index(Long taskId);
	List<Task> show();
	Task create(TaskDTO taskDTO);
	Task update(Task task);
	void delete(Long taskId);
	Task updateProgress(TaskProgressDTO taskProgressDTO);
}
