package evolution.api.rest.template;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Future;

public class FuturePool {
	@SuppressWarnings("rawtypes")
	private Map<String, Future> futureTasks;
	private Map<String, Object> completedTasks;
	private Map<String, Long> taskStartTimes; 
	private Integer maxTaskDuration;
	
	public void setMaxTaskDuration(Integer maxTaskDuration) {
		this.maxTaskDuration = maxTaskDuration;
	}

	public FuturePool() {
		this.futureTasks = new LinkedHashMap<>();
		this.completedTasks = new LinkedHashMap<>(); 
		this.taskStartTimes = new LinkedHashMap<>(); 
		this.maxTaskDuration = 3_000;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String taskId) {
		T t = (T) completedTasks.get(taskId);
		completedTasks.remove(taskId);
		return t;
	}
	
	@SuppressWarnings("rawtypes")
	public void set(String taskId, Future future, Class<?> clazz) {
		futureTasks.put(taskId, future);
		taskStartTimes.put(taskId, System.currentTimeMillis());
	}
	
	@SuppressWarnings("rawtypes")
	public void get() {
		while (futureTasks.size() > 0) {
			Set<String> completedTaskIds = new LinkedHashSet<>();
			Set<String> abortedTaskIds = new LinkedHashSet<>();
			long currentTime = System.currentTimeMillis();
			for (Entry<String, Future> entry : futureTasks.entrySet()) {
				String taskId = entry.getKey();
				Future futureTask = entry.getValue();
				if (currentTime - taskStartTimes.get(taskId) < maxTaskDuration) {
					if (futureTask.isDone()) {
						completedTaskIds.add(taskId);
						try {
							completedTasks.put(taskId, futureTask.get());
						} catch (Exception e) {}
					}
				} else {
					abortedTaskIds.add(taskId);
				}
			}
			for (String completedTaskId : completedTaskIds) {
				futureTasks.remove(completedTaskId);
			}
			for (String abortedTaskId : abortedTaskIds) {
				futureTasks.remove(abortedTaskId);
			}
		}
	}
}
