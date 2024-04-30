package backend.challenge.modules.task.services;


import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.factories.TaskDTOFactory;
import backend.challenge.modules.task.factories.TaskProgressDTOFactory;
import backend.challenge.modules.task.interfaces.ICreateTaskService;
import backend.challenge.modules.task.interfaces.ITaskRepository;
import backend.challenge.modules.task.interfaces.IUpdateTaskProgressService;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class UpdateTaskProgressServiceTest {
	private static final String TEST_TITLE = "Test Title";
	private static final String TEST_DESCRIPTION = "Test Description";
	private static final int TEST_PROGRESS = 50;
	private static final int ONE_HUNDRED = 100;
	private final ITaskRepository taskRepository = new TaskRepository();
	private final ICreateTaskService createTaskService = new CreateTaskService(taskRepository);
	private final IUpdateTaskProgressService updateTaskProgressService = new UpdateTaskProgressService(taskRepository);

	@Test
	public void shouldBeAbleToUpdateTaskProgress() {
		TaskDTO taskDTO = new TaskDTOFactory().build(TEST_TITLE, TEST_DESCRIPTION);
		Task firstTask = this.createTaskService.execute(taskDTO);

		System.out.println(firstTask.getProgress());
		System.out.println(firstTask.getStatus().name());
		System.out.println(firstTask.getId());

		TaskProgressDTO taskProgressDTO = new TaskProgressDTOFactory().build(firstTask.getId(), TEST_PROGRESS);
		System.out.println(taskProgressDTO.getProgress());
		Task task = this.updateTaskProgressService.execute(taskProgressDTO);
		Assert.assertEquals(firstTask.getProgress(), task.getProgress());
	}

	@Test
	public void shouldBeAbleToUpdateOnlyTaskStatusWhenProgressEqualsOneHundred() {
		TaskDTO taskDTO = new TaskDTOFactory().build(TEST_TITLE, TEST_DESCRIPTION);
		Task firstTask = this.createTaskService.execute(taskDTO);
		Assert.assertEquals(firstTask.getStatus(), TaskStatus.PROGRESS);

		TaskProgressDTO taskProgressDTO = new TaskProgressDTOFactory().build(firstTask.getId(), ONE_HUNDRED);
		Task newTask = this.updateTaskProgressService.execute(taskProgressDTO);

		Assert.assertEquals(newTask.getStatus(), TaskStatus.COMPLETE);
	}

}
