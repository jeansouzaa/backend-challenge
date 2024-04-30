package backend.challenge.modules.task.factories;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.interfaces.ITaskProgressDTOFactory;

import java.util.UUID;

public class TaskProgressDTOFactory implements ITaskProgressDTOFactory {

    @Override
    public TaskProgressDTO build(Long id, int progress)
    {
        TaskProgressDTO taskProgressDTO = TaskProgressDTO.create();
        taskProgressDTO.setId(id);
        taskProgressDTO.setProgress(progress);
        return taskProgressDTO;
    }
}
