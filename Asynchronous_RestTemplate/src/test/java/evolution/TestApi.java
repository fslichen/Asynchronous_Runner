package evolution;

import org.junit.Test;

import evolution.api.AsynchronousRestTemplate;
import evolution.controller.AnyDto;

public class TestApi {
	@Test
	public void run() {
		AsynchronousRestTemplate template = new AsynchronousRestTemplate();
		template.post("a", "http://localhost:8080/post", 
				null, AnyDto.class);
		// This is an example of return type inference.
		// You don't need to provide pass Class<T> as argument.
		// The compiler can dynamically determine the return type.
		AnyDto anyDto = template.get("a");
		System.out.println(anyDto);
	}
}
