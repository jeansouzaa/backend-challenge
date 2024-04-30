package backend.challenge.modules.task.factories;

import java.util.Date;
import java.util.UUID;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.interfaces.ITaskFactory;
import backend.challenge.modules.task.models.Task;

public class TaskFactory implements ITaskFactory {

	@Override
	public Task build(TaskDTO taskDTO) {
		Task task = new Task();
		task.setId(UUID.randomUUID().getLeastSignificantBits());
		task.setTitle(taskDTO.getTitle());
		task.setDescription(taskDTO.getDescription());
		task.setStatus(TaskStatus.PROGRESS);
		task.setProgress(0);
		task.setCreatedAt(new Date());
		return task;
	}

}
