package backend.challenge.modules.task.factories;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.interfaces.ITaskDTOFactory;

public class TaskDTOFactory implements ITaskDTOFactory {

    @Override
    public TaskDTO build(String title, String description) {
        TaskDTO taskDTO = TaskDTO.create();
        taskDTO.setTitle(title);
        taskDTO.setDescription(description);
        return taskDTO;
    }
}
