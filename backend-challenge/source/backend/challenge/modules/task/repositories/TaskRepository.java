package backend.challenge.modules.task.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.exceptions.DeleteException;
import backend.challenge.modules.task.exceptions.UpdateException;
import backend.challenge.modules.task.factories.TaskFactory;
import backend.challenge.modules.task.interfaces.ITaskRepository;
import backend.challenge.modules.task.models.Task;

@Singleton
public class TaskRepository implements ITaskRepository {

	private static final String UPDATE_MESSAGE_ERROR = "An error occurred while trying to update this Task. Please contact technical support for more details!";
	private static final String DELETE_MESSAGE_ERROR = "An error occurred while trying to delete this Task. Please contact technical support for more details!";
	private static final int MAX_PROGRESS = 100;
	private List<Task> tasks = new ArrayList<>();
	@Override
	public Task index(final Long taskId) {
		List<Task> atualTasks = this.tasks.stream().filter(atualTask -> atualTask.getId().equals(taskId)).collect(Collectors.toList());
		return atualTasks != null && !atualTasks.isEmpty() ? atualTasks.get(0) : null;
	}

	@Override
	public List<Task> show() {
		return this.tasks != null ? this.tasks : new ArrayList<>();
	}

	@Override
	public Task create(final TaskDTO taskDTO) {
		Task task = new TaskFactory().build(taskDTO);
		this.tasks.add(task);
		return task;
	}

	@Override
	public Task update(final Task task) throws UpdateException {
		Task atualTask = this.index(task.getId());
		if(atualTask != null)
		{
			atualTask.setTitle(task.getTitle());
			atualTask.setDescription(task.getDescription());
		}
		else
		{
			throw new UpdateException(UPDATE_MESSAGE_ERROR);
		}
		return atualTask;
	}

	@Override
	public void delete(final Long taskId) {
		Task task = this.index(taskId);
		if(task != null)
		{
			this.tasks.remove(task);
		}
		else
		{
			throw new DeleteException(DELETE_MESSAGE_ERROR);
		}
	}

	@Override
	public Task updateProgress(TaskProgressDTO taskProgressDTO) {
		Task task = this.index(taskProgressDTO.getId());
		if(task != null)
		{
			if(checkMaxProgress(taskProgressDTO.getProgress()))
			{
				updateProgress(task, MAX_PROGRESS);
				updateStatus(task, TaskStatus.COMPLETE);
			}
			else
			{
				updateProgress(task, taskProgressDTO.getProgress());
				updateStatus(task, TaskStatus.PROGRESS);
			}
		}
		else
		{
			throw new UpdateException(UPDATE_MESSAGE_ERROR);
		}
		return task;
	}

	private boolean checkMaxProgress(int progress)
	{
		if(progress >= MAX_PROGRESS) return true;
		return false;
	}

	private void updateProgress(Task task, int progress)
	{
		task.setProgress(progress);
	}
	private void updateStatus(Task task, TaskStatus taskStatus)
	{
		task.setStatus(taskStatus);
	}

}
