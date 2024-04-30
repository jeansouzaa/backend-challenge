package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.factories.TaskDTOFactory;
import backend.challenge.modules.task.interfaces.ICreateTaskService;
import backend.challenge.modules.task.interfaces.ITaskRepository;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class CreateTaskServiceTest {

	private static final String TEST_TITLE = "Test Title";
	private static final String TEST_DESCRIPTION = "Test Description";
	private ICreateTaskService createTaskService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		createTaskService = new CreateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToCreateANewTask() {
		TaskDTO taskDTO = new TaskDTOFactory().build(TEST_TITLE, TEST_DESCRIPTION);
		Task task = this.createTaskService.execute(taskDTO);
		Assert.assertEquals(taskDTO.getTitle(), task.getTitle());
		Assert.assertEquals(taskDTO.getDescription(), task.getDescription());
	}




}