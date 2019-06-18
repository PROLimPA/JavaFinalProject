package edu.handong.csee.java.Errors;

public class NotEnoughArgumentException extends Exception{
	
	public NotEnoughArgumentException() {
		super("No CLI argument Exception! Please put a file path.");
	}
	
	public NotEnoughArgumentException(String message) {
	
	super(message);
	}
}
