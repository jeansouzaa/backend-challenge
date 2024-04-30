package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.factories.TaskDTOFactory;
import backend.challenge.modules.task.factories.TaskFactory;
import backend.challenge.modules.task.infra.http.controllers.TaskController;
import backend.challenge.modules.task.interfaces.*;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import kikaha.urouting.api.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RunWith( KikahaRunner.class )
public class UpdateTaskServiceTest {

	private static final int STATUS_BAD_REQUEST = 400;
	private static final String URL_UPDATE_BAD_REQUEST = "http://localhost:8080/tasks/single/9999";
	private static final String URL_UPDATE = "http://localhost:8080/tasks/single/";
	private static final int TEST_PROGRESS = 50;
	private  IUpdateTaskService updateTaskService;
	private  ICreateTaskService createTaskService;
	private  ITaskRepository taskRepository;
	private IDeleteTaskService deleteTaskService;
	private IRetrieveAllTasksService retrieveAllTasksService;
	private TaskController taskController;
	private static final String TEST_TITLE = "Test Title";
	private static final String TEST_DESCRIPTION = "Test Description";

	@Before
	public void init() {
		taskRepository = new TaskRepository();
		createTaskService = new CreateTaskService(taskRepository);
		updateTaskService = new UpdateTaskService(taskRepository);
		deleteTaskService = new DeleteTaskService(taskRepository);
		retrieveAllTasksService = new RetrieveAllTasksService(taskRepository);
		taskController = new TaskController(createTaskService, deleteTaskService, retrieveAllTasksService);
	}

	@Test
	public void shouldBeAbleToUpdateTask() {
		TaskDTO taskDTO = new TaskDTOFactory().build(TEST_TITLE, TEST_DESCRIPTION);
		Task task = this.createTaskService.execute(taskDTO);
		Task newTask = this.updateTaskService.execute(task);

		Assert.assertEquals(task.getTitle(), newTask.getTitle());
		Assert.assertEquals(task.getDescription(), newTask.getDescription());

	}

	@Test
	public void shouldNotBeAbleToUpdateATaskThatDoesNotExist() {
		TaskDTO taskDTO = new TaskDTOFactory().build(TEST_TITLE, TEST_DESCRIPTION);
		Task task = new TaskFactory().build(taskDTO);
		Response response = taskController.update(9999L, task);
		Assert.assertEquals(response.statusCode(), STATUS_BAD_REQUEST);

	}

	@Test
	public void shouldNotBeAbleToUpdateTaskStatusManually() {
		/*
			TODO:  Para que esse teste passe, você não deve permitir que sua rota de update
						 altere diretamente o `status` dessa tarefa, mantendo o mesmo status que a tarefa
						 já possuía antes da atualização. Isso porque o único lugar que deve atualizar essa informação
						 é a rota responsável por alterar o progresso da tarefa.

		 */
		TaskDTO taskDTO = new TaskDTOFactory().build(TEST_TITLE, TEST_DESCRIPTION);
		Task task = this.createTaskService.execute(taskDTO);
		task.setProgress(TEST_PROGRESS);
		task.setStatus(TaskStatus.COMPLETE);
		Task verifiedTask = this.updateTaskService.execute(task);
		Assert.assertEquals(task.getStatus(),verifiedTask.getStatus());

	}


}