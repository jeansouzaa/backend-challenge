package backend.challenge.modules.task.services;


import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.factories.TaskDTOFactory;
import backend.challenge.modules.task.interfaces.ICreateTaskService;
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
public class RetrieveTaskByIdServiceTest {

	private ITaskRepository taskRepository;
	private IRetrieveTaskByIdService retrieveTaskByIdService;
	private ICreateTaskService createTaskService;
	private static final String TEST_TITLE = "Test Title";
	private static final String TEST_DESCRIPTION = "Test Description";

	@Before
	public void init() {
		this.taskRepository = new TaskRepository();
		this.createTaskService = new CreateTaskService(taskRepository);
		this.retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
	}

	@Test
	public void shouldBeAbleToListTheTaskById() {
		TaskDTO taskDTO = new TaskDTOFactory().build(TEST_TITLE, TEST_DESCRIPTION);
		Task task = this.createTaskService.execute(taskDTO);
		Task findTask = this.retrieveTaskByIdService.execute(task.getId());

		Assert.assertEquals(task.getId(), findTask.getId());
	}

}
