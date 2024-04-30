package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.exceptions.DeleteException;
import backend.challenge.modules.task.exceptions.UpdateException;
import backend.challenge.modules.task.factories.TaskDTOFactory;
import backend.challenge.modules.task.infra.http.views.TaskView;
import backend.challenge.modules.task.interfaces.*;
import backend.challenge.modules.task.models.Task;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Path("tasks")
public class TaskController {

	private static final String TASK_SUCCESSFULLY_DELETED = "Task successfully deleted!";
	private static final int ERROR_BAD_REQUEST = 400;
	private final ICreateTaskService createTaskService;
	private final IDeleteTaskService deleteTaskService;
	private final IRetrieveAllTasksService retrieveAllTasksService;
	private final IRetrieveTaskByIdService retrieveTaskByIdService;
	private final IUpdateTaskService updateTaskService;

	@Inject
	public TaskController(
		final ICreateTaskService createTaskService,
		final IDeleteTaskService deleteTaskService,
		final IRetrieveAllTasksService retrieveAllTasksService
	) {
		this.createTaskService = createTaskService;
		this.deleteTaskService = deleteTaskService;
		this.retrieveAllTasksService = retrieveAllTasksService;
		this.retrieveTaskByIdService = null;
		this.updateTaskService = null;
	}

	@GET
	public Response show() {
		return DefaultResponse.ok().entity(retrieveAllTasksService.execute());
	}

	@GET
	@Path("single/{taskId}")
	public Response index(@PathParam("taskId") Long taskId) {
		try
		{
			Task task = retrieveTaskByIdService.execute(taskId);
			return DefaultResponse.ok().entity(task);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			return DefaultResponse.notFound().entity(exception.getMessage());
		}
	}

	@POST
	public Response create(TaskView task) {
		return DefaultResponse.ok().entity(new TaskDTOFactory().build(task.getTitle(), task.getDescription()));
	}

	@PUT
	@Path("single/{taskId}")
	public Response update(@PathParam("taskId") Long taskId, Task task) {
		if(task.getId().equals(taskId))
		{
			try
			{
				this.updateTaskService.execute(task);
			}
			catch(UpdateException updateException)
			{
				updateException.printStackTrace();
				return DefaultResponse.badRequest().statusCode(ERROR_BAD_REQUEST);
			}
		}
		else
		{
				return DefaultResponse.badRequest().statusCode(ERROR_BAD_REQUEST);
		}
		return DefaultResponse.ok().entity(task);
	}

	@DELETE
	@Path("single/{taskId}")
	public Response delete(@PathParam("taskId") Long taskId) {
		try
		{
			this.deleteTaskService.execute(taskId);
		}
		catch(DeleteException deleteException)
		{
			deleteException.printStackTrace();
			return DefaultResponse.notFound().entity(deleteException.getMessage());
		}
		return DefaultResponse.ok().entity(TASK_SUCCESSFULLY_DELETED);
	}

}
