package backend.challenge.modules.task.interfaces;

import backend.challenge.modules.task.dtos.TaskProgressDTO;

public interface ITaskProgressDTOFactory {

    TaskProgressDTO build(Long id, int progress);
}
