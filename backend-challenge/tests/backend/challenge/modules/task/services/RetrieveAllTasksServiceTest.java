package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.factories.TaskDTOFactory;
import backend.challenge.modules.task.interfaces.ICreateTaskService;
import backend.challenge.modules.task.interfaces.IRetrieveAllTasksService;
import backend.challenge.modules.task.interfaces.ITaskRepository;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith( KikahaRunner.class )
public class RetrieveAllTasksServiceTest {

	private static final String TEST_TITLE = "Test Title";
	private static final String TEST_DESCRIPTION = "Test Description";
	private IRetrieveAllTasksService retrieveAllTasksService;
	private ICreateTaskService createTaskService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		retrieveAllTasksService = new RetrieveAllTasksService(taskRepository);
		createTaskService = new CreateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToListTheTasks() {
		TaskDTO taskDTO = new TaskDTOFactory().build(TEST_TITLE, TEST_DESCRIPTION);
		Task task = this.createTaskService.execute(taskDTO);
		List<Task> newTasks = new ArrayList<>();
		newTasks.add(task);
		List<Task> tasks = this.retrieveAllTasksService.execute();
		Assert.assertEquals(tasks, newTasks);
	}

}