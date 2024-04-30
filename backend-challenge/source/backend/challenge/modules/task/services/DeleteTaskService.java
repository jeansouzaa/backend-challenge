package backend.challenge.modules.task.services;

import backend.challenge.modules.task.exceptions.DeleteException;
import backend.challenge.modules.task.interfaces.IDeleteTaskService;
import backend.challenge.modules.task.interfaces.ITaskRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DeleteTaskService implements IDeleteTaskService {

	private final ITaskRepository taskRepository;

	@Inject
	public DeleteTaskService(final ITaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public void execute(Long taskId) throws DeleteException {
		this.taskRepository.delete(taskId);
	}

}
