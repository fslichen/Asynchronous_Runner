package evolution.api.runner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsynchronousRunner {
	private ExecutorService executorService;
	
	public AsynchronousRunner() {
		this.executorService = Executors.newCachedThreadPool();
	}
	
	public <T> void run(final Executor executor, final Success success, final Failure failure) {
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				try {
					executor.run();
					success.run();
				} catch (Exception e) {
					e.printStackTrace();
					failure.run();
				}
			}
		});
	}
}
