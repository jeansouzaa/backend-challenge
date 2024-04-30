package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.interfaces.ICreateTaskService;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.interfaces.ITaskRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CreateTaskService implements ICreateTaskService {

	private final ITaskRepository taskRepository;

	@Inject
	public CreateTaskService(final ITaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public Task execute(TaskDTO taskDTO) {
		Task task = this.taskRepository.create(taskDTO);
		return task;
	}

}
