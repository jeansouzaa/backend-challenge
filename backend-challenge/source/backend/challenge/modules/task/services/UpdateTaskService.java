package backend.challenge.modules.task.services;

import backend.challenge.modules.task.exceptions.UpdateException;
import backend.challenge.modules.task.interfaces.ITaskRepository;
import backend.challenge.modules.task.interfaces.IUpdateTaskService;
import backend.challenge.modules.task.models.Task;

import javax.inject.Inject;

public class UpdateTaskService implements IUpdateTaskService {
    private final ITaskRepository taskRepository;

    @Inject
    public UpdateTaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(Task task) throws UpdateException {
        return this.taskRepository.update(task);
    }
}
