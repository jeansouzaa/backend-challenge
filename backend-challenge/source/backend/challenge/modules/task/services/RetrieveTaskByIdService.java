package backend.challenge.modules.task.services;

import backend.challenge.modules.task.interfaces.IRetrieveTaskByIdService;
import backend.challenge.modules.task.interfaces.ITaskRepository;
import backend.challenge.modules.task.models.Task;

import javax.inject.Inject;

public class RetrieveTaskByIdService implements IRetrieveTaskByIdService {

    private final ITaskRepository taskRepository;

    @Inject
    public RetrieveTaskByIdService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(Long taskId) {
        return taskRepository.index(taskId);
    }
}
