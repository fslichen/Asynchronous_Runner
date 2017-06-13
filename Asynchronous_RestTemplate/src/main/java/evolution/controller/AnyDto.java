package evolution.controller;

public class AnyDto {
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "AnyDto [message=" + message + "]";
	}
	public AnyDto() {

	}
	public AnyDto(String message) {
		this.message = message;
	}
}
