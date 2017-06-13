package evolution.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import evolution.api.rest.template.AsynchronousRunner;
import evolution.api.rest.template.Failure;
import evolution.api.rest.template.Success;

@RestController
public class TheOtherController {
	@GetMapping("/rest/template")
	public void restTemplate() throws Exception {
		AsynchronousRunner runner = new AsynchronousRunner();
		runner.post("http://localhost:8080/post", null, AnyDto.class, 
		new Success<AnyDto>() {
			@Override
			public void run(AnyDto anyDto) {
				System.out.println(anyDto);
				System.out.println("Success");
			}
		}, 
		new Failure() {
			@Override
			public void run() {
				System.out.println("Failure");
			}
		});
	}
}
