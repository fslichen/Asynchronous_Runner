package evolution.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import evolution.api.runner.AsynchronousRunner;
import evolution.api.runner.Executor;
import evolution.api.runner.Failure;
import evolution.api.runner.Success;

@RestController
public class AnotherController {
	@GetMapping("/runner")
	public void runner() {
		AsynchronousRunner runner = new AsynchronousRunner();
		runner.run(
		new Executor() {
			@Override
			public void run() {
				RestTemplate template = new RestTemplate();
				AnyDto anyDto = template.postForObject("http://localhost:8080/post", null, AnyDto.class);
				System.out.println(anyDto);
			}
		}, 
		new Success() {
			@Override
			public void run() {
				System.out.println("Success");
			}}, 
		new Failure() {
			@Override
			public void run() {
				System.out.println("Failure");
			}
		});
	}
}
