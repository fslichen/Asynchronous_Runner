package evolution.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnyController {
	@PostMapping("/post")
	public AnyDto post() throws InterruptedException {
		Thread.sleep(3_000);
		String message = "Hello World";
		System.out.println(message);
		return new AnyDto(message);
	}
}
