package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.factories.TaskDTOFactory;
import backend.challenge.modules.task.interfaces.ICreateTaskService;
import backend.challenge.modules.task.interfaces.IDeleteTaskService;
import backend.challenge.modules.task.interfaces.IRetrieveTaskByIdService;
import backend.challenge.modules.task.interfaces.ITaskRepository;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class DeleteTaskServiceTest {

	private IDeleteTaskService deleteTaskService;
	private ICreateTaskService createTaskService;
	private IRetrieveTaskByIdService retrieveTaskByIdService;
	private static final String TEST_TITLE = "Test Title";
	private static final String TEST_DESCRIPTION = "Test Description";

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		deleteTaskService = new DeleteTaskService(taskRepository);
		createTaskService = new CreateTaskService(taskRepository);
		retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
	}

	@Test
	public void shouldBeAbleToDeleteTaskById() {
		TaskDTO taskDTO = new TaskDTOFactory().build(TEST_TITLE, TEST_DESCRIPTION);
		Task task = this.createTaskService.execute(taskDTO);
		this.deleteTaskService.execute(task.getId());
		Task removedTask = this.retrieveTaskByIdService.execute(task.getId());
		Assert.assertNull(null, removedTask);
	}




}