package evolution;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import evolution.api.rest.template.AsynchronousRunner;
import evolution.api.rest.template.Failure;
import evolution.api.rest.template.Success;
import evolution.api.runner.Executor;
import evolution.controller.AnyDto;

public class TestApi {
	@Test
	public void runPost() {
		AsynchronousRunner template = new AsynchronousRunner();
		for (int i = 0; i < 100; i++) {
			template.post(i + "", "http://localhost:8080/post", 
					null, AnyDto.class);
		}
		// This is an example of return type inference.
		// You don't need to provide pass Class<T> as argument.
		// The compiler can dynamically determine the return type.
		for (int i = 0; i < 100; i++) {
			AnyDto anyDto = template.get(i + "");
			System.out.println(anyDto);
		}
	}
	
	@Test
	public void runPostWithCallBack() throws Exception {
		AsynchronousRunner template = new AsynchronousRunner();
		template.post("http://localhost:8080/post", null, AnyDto.class, 
				new Success<AnyDto>() {
					@Override
					public void run(AnyDto t) {
						System.out.println("Success. The response is " + t);
					}
		}, new Failure() {
			@Override
			public void run() {
				System.out.println("Failure");
			}
		});
	}
	
	@Test
	public void runExecutor() {
		
	}
}
