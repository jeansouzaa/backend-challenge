package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.exceptions.UpdateException;
import backend.challenge.modules.task.factories.TaskProgressDTOFactory;
import backend.challenge.modules.task.infra.http.views.TaskProgressView;
import backend.challenge.modules.task.interfaces.IUpdateTaskProgressService;
import backend.challenge.modules.task.models.Task;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
@Path("tasks/progress")
public class TaskProgressController {

	private final IUpdateTaskProgressService updateTaskProgressService;

	@Inject
	public TaskProgressController(final IUpdateTaskProgressService updateTaskProgressService) {
		this.updateTaskProgressService = updateTaskProgressService;
	}

	@PUT
	@Path("single/{taskId}")
	public Response updateProgress(@PathParam("taskId") Long taskId, TaskProgressView taskProgressView) {
		TaskProgressDTO taskProgressDTO = new TaskProgressDTOFactory().build(taskId, taskProgressView.getProgress());
		Task task = null;
		try
		{
			task = this.updateTaskProgressService.execute(taskProgressDTO);
		}
		catch (UpdateException updateException)
		{
			updateException.printStackTrace();
			return DefaultResponse.notFound().entity(updateException.getMessage());
		}
		return DefaultResponse.ok().entity(task);
	}

}
