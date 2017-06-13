package evolution.api;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Future;

public class FuturePool {
	@SuppressWarnings("rawtypes")
	Map<String, Future> futureTasks;
	Map<String, Object> completedTasks;
	
	public FuturePool() {
		this.futureTasks = new LinkedHashMap<>();
		this.completedTasks = new LinkedHashMap<>(); 
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String taskId) {
		return (T) completedTasks.get(taskId);
	}
	
	@SuppressWarnings("rawtypes")
	public void set(String taskId, Future future, Class<?> clazz) {
		futureTasks.put(taskId, future);
	}
	
	@SuppressWarnings("rawtypes")
	public void get() {
		while (futureTasks.size() > 0) {
			Set<String> completedTaskIds = new LinkedHashSet<>();
			for (Entry<String, Future> entry : futureTasks.entrySet()) {
				String taskId = entry.getKey();
				Future futureTask = entry.getValue();
				if (futureTask.isDone()) {
					completedTaskIds.add(taskId);
					try {
						completedTasks.put(taskId, futureTask.get());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			for (String completedTaskId : completedTaskIds) {
				futureTasks.remove(completedTaskId);
			}
		}
	}
}
