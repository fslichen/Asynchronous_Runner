package evolution.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnyController {
	@PostMapping("/post")
	public AnyDto post() {
		String message = "Hello World";
		System.out.println(message);
		return new AnyDto(message);
	}
}
