package backend.challenge.modules.task.interfaces;

import backend.challenge.modules.task.dtos.TaskDTO;

public interface ITaskDTOFactory {
    TaskDTO build(String title, String description);
}
