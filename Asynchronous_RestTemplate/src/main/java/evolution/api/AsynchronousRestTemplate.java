package evolution.api;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.web.client.RestTemplate;

public class AsynchronousRestTemplate {
	private FuturePool futurePool;
	private RestTemplate restTemplate;
	private ExecutorService executorService;
	
	public AsynchronousRestTemplate() {
		this.restTemplate = new RestTemplate();
		this.executorService = Executors.newCachedThreadPool();
		this.futurePool = new FuturePool();
	}
	
	public <T> Future<T> post(String taskId, final String url, 
			final Object request, final Class<T> clazz) {
		Future<T> future = executorService.submit(new Callable<T>() {
			@Override
			public T call() throws Exception {
				return restTemplate.postForObject(url, request, clazz);
			}
		});
		futurePool.set(taskId, future, clazz);
		return future;
	}
	
	public <T> T get(String taskId) {
		futurePool.get();
		return futurePool.get(taskId);
	}
}
