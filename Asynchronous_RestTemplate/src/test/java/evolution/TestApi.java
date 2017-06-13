package evolution;

import org.junit.Test;

import evolution.api.AsynchronousRestTemplate;
import evolution.controller.AnyDto;

public class TestApi {
	@Test
	public void run() {
		AsynchronousRestTemplate template = new AsynchronousRestTemplate();
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
}
