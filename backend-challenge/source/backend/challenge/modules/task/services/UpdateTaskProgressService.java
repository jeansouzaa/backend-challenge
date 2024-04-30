package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.exceptions.UpdateException;
import backend.challenge.modules.task.interfaces.ITaskRepository;
import backend.challenge.modules.task.interfaces.IUpdateTaskProgressService;
import backend.challenge.modules.task.models.Task;

public class UpdateTaskProgressService implements IUpdateTaskProgressService {

    private final ITaskRepository taskRepository;

    public UpdateTaskProgressService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public Task execute(TaskProgressDTO taskProgressDTO) throws UpdateException {
        return this.taskRepository.updateProgress(taskProgressDTO);
    }


}
