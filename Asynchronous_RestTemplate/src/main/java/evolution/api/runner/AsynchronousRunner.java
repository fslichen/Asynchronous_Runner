package evolution.api.runner;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsynchronousRunner {
	private ExecutorService executorService;
	
	public AsynchronousRunner() {
		this.executorService = Executors.newCachedThreadPool();
	}
	
	public <T> void run(final Executor executor, final Success success, final Failure failure) {
		executorService.submit(new Callable<T>() {
			@Override
			public T call() throws Exception {
				try {
					executor.run();
					success.run();
				} catch (Exception e) {
					e.printStackTrace();
					failure.run();
				}
				return null;
			}
		});
	}
}
